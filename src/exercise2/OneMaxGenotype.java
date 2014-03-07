package exercise2;

import java.util.ArrayList;
import java.util.Comparator;

public class OneMaxGenotype implements Genotype{
	
	boolean[] genotype;
	public boolean[] phenotype;
	double fitness;
	Comparator<Genotype> comparator;
	
	//Make a new genotype
	public OneMaxGenotype(){
		genotype = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		phenotype = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		for (int i = 0; i < Params.ONE_MAX_BIT_VECTOR_SIZE; i++){
			genotype[i] = Math.random() > 0.5 ? true : false;			
		}
		comparator = new OneMaxGenotypeComparator();
	}
	//Make a child genotype
	public OneMaxGenotype(boolean[] genotype){
		this.genotype = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		System.arraycopy(genotype, 0, this.genotype, 0, Params.ONE_MAX_BIT_VECTOR_SIZE);
		phenotype = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		comparator = new OneMaxGenotypeComparator();
	}
	//Makes a deep copy
//	public OneMaxGenotype(OneMaxGenotype oneMaxGenotype){
//		System.arraycopy(oneMaxGenotype.genotype, 0, genotype, 0, Params.ONE_MAX_BIT_VECTOR_SIZE);
//		System.arraycopy(oneMaxGenotype.phenotype, 0, phenotype, 0, Params.ONE_MAX_BIT_VECTOR_SIZE);
//		this.fitness = oneMaxGenotype.fitness;
//		comparator = new OneMaxGenotypeComparator();
//	}
	
	
	@SuppressWarnings({ "unused" })
	@Override
	public ArrayList<Genotype> generateChildren(Genotype mate) {
		ArrayList<Genotype> children = new ArrayList<Genotype>();
		
		boolean[] firstChild = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		boolean[] secondChild = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
		
//		int previousCrossoverPoint = 0;
		if(Params.CROSSOVER_POINTS == 1){
			int crossoverPoint = (int) (1 + Math.random() * (Params.ONE_MAX_BIT_VECTOR_SIZE - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, crossoverPoint);
			System.arraycopy(mate.getGenotype(), crossoverPoint, firstChild, crossoverPoint, Params.ONE_MAX_BIT_VECTOR_SIZE - crossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, crossoverPoint);
			System.arraycopy(genotype, crossoverPoint, secondChild, crossoverPoint, Params.ONE_MAX_BIT_VECTOR_SIZE - crossoverPoint);
		}else if (Params.CROSSOVER_POINTS == 2){
			int firstCrossoverPoint = (int) (1 + Math.random() * (Params.ONE_MAX_BIT_VECTOR_SIZE - 2));
			int secondCrossoverPoint = (int) (firstCrossoverPoint + Math.random() * (Params.ONE_MAX_BIT_VECTOR_SIZE - firstCrossoverPoint - 1));
			//Cross firstChild
			System.arraycopy(genotype, 0, firstChild, 0, firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), firstCrossoverPoint, firstChild, firstCrossoverPoint, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(genotype, secondCrossoverPoint, firstChild, secondCrossoverPoint, Params.ONE_MAX_BIT_VECTOR_SIZE - secondCrossoverPoint);
			//Cross secondChild
			System.arraycopy(mate.getGenotype(), 0, secondChild, 0, firstCrossoverPoint);
			System.arraycopy(genotype, firstCrossoverPoint, secondChild, 0, secondCrossoverPoint - firstCrossoverPoint);
			System.arraycopy(mate.getGenotype(), secondCrossoverPoint, secondChild, secondCrossoverPoint, Params.ONE_MAX_BIT_VECTOR_SIZE - secondCrossoverPoint);
			
		}
		children.add(new OneMaxGenotype(firstChild));
		children.add(new OneMaxGenotype(secondChild));
//		for (int j = 0; j < Params.CROSSOVER_POINTS; j++) {
//			//Picks a crossover point thats neither the first nor the last bit
//			crossoverPoint = (int) (1 + Math.random() * (Params.ONE_MAX_BIT_VECTOR_SIZE - 1)) + previousCrossoverPoint;
//			firstChild
//		}
		return children;
	}

	@Override
	public void mutate() {
		for (int i = 0; i < Params.ONE_MAX_BIT_VECTOR_SIZE; i++) {
			if(Math.random() < Params.MUTATION_CHANCE)
				genotype[i] = !genotype[i];
		}
	}
	
	public String toString(){
		String representation = "";
		for (boolean bit : genotype) {
			representation += bit ? "1" : "0";
		}
		return representation;
	}

	@Override
	public void generatePhenotype() {
		System.arraycopy(genotype, 0, phenotype, 0, Params.ONE_MAX_BIT_VECTOR_SIZE);
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
	public Comparator<Genotype> getComparator() {
		return comparator;
	}
	
	
	private class OneMaxGenotypeComparator implements Comparator<Genotype> {
		
		@Override
		public int compare(Genotype g1, Genotype g2) {
			if(g1.getFitness() == g2.getFitness())
				return 0;
			else
				return (int) Math.signum(g1.getFitness() - g2.getFitness());
		}
		
	}


	@Override
	public boolean[] getGenotype() {
		return genotype;
	}
}



















