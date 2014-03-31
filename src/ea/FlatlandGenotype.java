package ea;

import java.util.ArrayList;
import java.util.Comparator;

import commons.Params;

public class FlatlandGenotype implements Genotype {

	boolean[] genotype;
	public double[] phenotype;
	double fitness;
	Comparator<Genotype> comparator;
	
	//Make a new genotype
	public FlatlandGenotype(){
		genotype = new boolean[Params.GENOTYPE_SIZE_FLATLAND];
		phenotype = new double[Params.numberOfWeights];
		for (int i = 0; i < Params.GENOTYPE_SIZE_FLATLAND; i++){
			genotype[i] = Math.random() > 0.5 ? true : false;			
		}
		comparator = new FlatlandGenotypeComparator();
	}
	//Make a child genotype
	public FlatlandGenotype(boolean[] genotype){
		this.genotype = new boolean[Params.GENOTYPE_SIZE_FLATLAND];
		System.arraycopy(genotype, 0, this.genotype, 0, Params.GENOTYPE_SIZE_FLATLAND);
		phenotype = new double[Params.numberOfWeights];
		comparator = new FlatlandGenotypeComparator();
	}
	
	@Override
	public ArrayList<Genotype> generateChildren(Genotype mate) {
		ArrayList<Genotype> children = new ArrayList<Genotype>();
		
		boolean[] firstChild = new boolean[Params.GENOTYPE_SIZE_FLATLAND];
		boolean[] secondChild = new boolean[Params.GENOTYPE_SIZE_FLATLAND];
		
		if(Params.CROSSOVER_POINTS == 1){
			int crossoverPoint = (int) (1 + Math.random() * (Params.GENOTYPE_SIZE_FLATLAND - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, crossoverPoint);
			System.arraycopy(mate.getGenotype(), crossoverPoint, firstChild, crossoverPoint, Params.GENOTYPE_SIZE_FLATLAND - crossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, crossoverPoint);
			System.arraycopy(genotype, crossoverPoint, secondChild, crossoverPoint, Params.GENOTYPE_SIZE_FLATLAND - crossoverPoint);
		}else if (Params.CROSSOVER_POINTS == 2){
			int firstCrossoverPoint = (int) (1 + Math.random() * (Params.GENOTYPE_SIZE_FLATLAND - 2));
			int secondCrossoverPoint = (int) (firstCrossoverPoint + Math.random() * (Params.GENOTYPE_SIZE_FLATLAND - firstCrossoverPoint - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), firstCrossoverPoint, firstChild, firstCrossoverPoint, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(genotype, secondCrossoverPoint, firstChild, secondCrossoverPoint, Params.GENOTYPE_SIZE_FLATLAND - secondCrossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, firstCrossoverPoint);
			System.arraycopy(genotype, firstCrossoverPoint, secondChild, 0, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), secondCrossoverPoint, secondChild, secondCrossoverPoint, Params.GENOTYPE_SIZE_FLATLAND - secondCrossoverPoint);
			
		}
		children.add(new FlatlandGenotype(firstChild));
		children.add(new FlatlandGenotype(secondChild));
		return children;
	}

	@Override
	public void mutate() {
		for (int i = 0; i < Params.GENOTYPE_SIZE_FLATLAND; i++) {
			if(Math.random() < Params.MUTATION_CHANCE)
				genotype[i] = !genotype[i];
		}
	}

	@Override
	public void generatePhenotype() {
		for (int i = 0; i < Params.numberOfWeights; i++) {
			for (int j = 0; j < Params.GENOTYPE_BITS_PER_WEIGHT; j++) {
				if(genotype[j + i * Params.GENOTYPE_BITS_PER_WEIGHT]) 
					phenotype[i] += 1.0/Params.GENOTYPE_BITS_PER_WEIGHT;				
			}
		}
	}

	@Override
	/*Returns the fitness of the final state of the flatland this set of weights has run on*/
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
	
	private class FlatlandGenotypeComparator implements Comparator<Genotype> {
		@Override
		public int compare(Genotype g1, Genotype g2) {
			if(g1.getFitness() == g2.getFitness())
				return 0;
			else
				return (int) Math.signum(g1.getFitness() - g2.getFitness());
		}
		
	}

}
