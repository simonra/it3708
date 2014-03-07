package exercise2;

public interface EAHeuristic {
	
	/**Added for future expandability. Probably not needed.*/
	public double getFitness(Object object);
	/** Takes a genotype and returns its fitness */
	public double getGenotypeFitness(Genotype genotype);
	/** Takes a population and returns its fitness */
	public double getPopulationFitness(Population population);
}
