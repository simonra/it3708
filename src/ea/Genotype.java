package ea;

import java.util.ArrayList;
import java.util.Comparator;

public interface Genotype {
	public ArrayList<Genotype> generateChildren(Genotype mate);
	public void mutate();
	public String toString();
	public void generatePhenotype();
	public double getFitness();
	public void setFitness(double fitness);
	public boolean[] getGenotype();
	public Comparator<Genotype> getComparator();
}
