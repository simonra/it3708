package ea;

public interface EAHeuristic {
	
	/** Takes a genotype and returns its fitness */
	public double getGenotypeFitness(Genotype genotype);
	public void refreshStockWorlds();
}
