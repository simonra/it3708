package exercise2;

import java.util.ArrayList;
import java.util.Comparator;

public class SurprisingSequencesGenotype implements Genotype {
	boolean[] genotype;
	int[] phenotype;
	double fitness;
	Comparator<Genotype> comparator;
	
	public SurprisingSequencesGenotype(){
		genotype = new boolean[(Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE) * Params.SYMBOL_SET_SIZE];
		phenotype = new int[Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE];
		for (int i = 0; i < genotype.length; i++) {
			genotype[i] = Math.random() > 0.5 ? true : false;
		}
		comparator = new surprisingSequencesComplarator();
	}
	public SurprisingSequencesGenotype(boolean[] genotype){
		this.genotype = new boolean[genotype.length];
		System.arraycopy(genotype, 0, this.genotype, 0, genotype.length);
		phenotype = new int[Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE];
		comparator = new surprisingSequencesComplarator();
	}
	
	@SuppressWarnings({ "unused" })
	@Override
	public ArrayList<Genotype> generateChildren(Genotype mate) {
		ArrayList<Genotype> children = new ArrayList<Genotype>();
		
		boolean[] firstChild = new boolean[genotype.length];
		boolean[] secondChild = new boolean[genotype.length];
		
//		int previousCrossoverPoint = 0;
		if(Params.CROSSOVER_POINTS == 1){
			int crossoverPoint = (int) (1 + Math.random() * (genotype.length - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, crossoverPoint);
			System.arraycopy(mate.getGenotype(), crossoverPoint, firstChild, crossoverPoint, genotype.length - crossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, crossoverPoint);
			System.arraycopy(genotype, crossoverPoint, secondChild, crossoverPoint, genotype.length - crossoverPoint);
		}else if (Params.CROSSOVER_POINTS == 2){
			int firstCrossoverPoint = (int) (1 + Math.random() * (genotype.length - 2));
			int secondCrossoverPoint = (int) (firstCrossoverPoint + Math.random() * (genotype.length - firstCrossoverPoint - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), firstCrossoverPoint, firstChild, firstCrossoverPoint, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(genotype, secondCrossoverPoint, firstChild, secondCrossoverPoint, genotype.length - secondCrossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, firstCrossoverPoint);
			System.arraycopy(genotype, firstCrossoverPoint, secondChild, 0, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), secondCrossoverPoint, secondChild, secondCrossoverPoint, genotype.length - secondCrossoverPoint);
			
		}
		children.add(new SurprisingSequencesGenotype(firstChild));
		children.add(new SurprisingSequencesGenotype(secondChild));

		return children;
	}

	@Override
	public void mutate() {
		for (int i = 0; i < genotype.length; i++) {
			if(Math.random() < Params.MUTATION_CHANCE)
				genotype[i] = !genotype[i];
		}
	}

	public String toString(){
		String representation = "";
		for (int symbol : phenotype) {
			representation += symbol + " ";
		}
		return representation;
	}
	
	@Override
	public void generatePhenotype() {
//		System.arraycopy(genotype, 0, phenotype, 0, genotype.length);
		for (int i = 0; i < genotype.length / Params.SYMBOL_SET_SIZE; i++) {
			int counter = 0;
			for (int j = 0; j < Params.SYMBOL_SET_SIZE; j++) {
				if(genotype[i*Params.SYMBOL_SET_SIZE + j])
					counter++;
			}
			phenotype[i] = counter;
		}
	}

	@Override
	public double getFitness() {
		return fitness;
	}

	@Override
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	@Override
	public boolean[] getGenotype() {
		return genotype;
	}

	@Override
	public Comparator<Genotype> getComparator() {
		return comparator;
	}
	
	private class surprisingSequencesComplarator implements Comparator<Genotype>{
		@Override
		public int compare(Genotype g1, Genotype g2) {
			if(g1.getFitness() == g2.getFitness())
				return 0;
			else
				return (int) Math.signum(g1.getFitness() - g2.getFitness());
		}
		
	}
	
}


























