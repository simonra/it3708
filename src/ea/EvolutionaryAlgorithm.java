package ea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.google.gson.Gson;

import commons.Params;

public class EvolutionaryAlgorithm {
	static Gson gson = new Gson();

	public static void main(String[] args) {
		createFile("MytextFile.txt");
		createFile("Surprising sequences log file");
		evolutionaryAlgorithm();
	}
	static boolean loopCheckAndOtherMagic(Population population){
		return !population.isSolved();
	}
	
	static int numberOfGenerations;
	public static void evolutionaryAlgorithm(){
		Population population = new Population();
		numberOfGenerations = 0;
//		while(loopCheckAndOtherMagic(population)){
		while(!population.isSolved()){
			//Evolves phenotypes for the children
			population.developPopulation();
			//Creates and evaluates the fitnesses of phenotypes
			population.createFitnesses();
			//Performs adult selection (who gets to survive)
			population.adultSelection();
			//Selects who mates, generate their children
			population.reproduction();
			System.out.println("The best fitness was: " + population.bestFitness + ", and it was achieved by the individual:");
			System.out.println(population.bestIndividual.toString());
			writeToFile("MytextFile.txt", "Best fitness: " + population.bestFitness);
//			writeToFile("MytextFile.txt", "Best individual: " +  gson.toJson(population.bestIndividual) );
			writeToFile("MytextFile.txt", "Average fitness of populaion: " + population.averageFitness);
			createFile("bestFlatlandAnnPhenotype.txt");
			FlatlandGenotype bestGenotype = (FlatlandGenotype) population.bestIndividual;
			writeToFile("bestFlatlandAnnPhenotype.txt", gson.toJson(bestGenotype.phenotype));
			numberOfGenerations ++;
		}
		System.out.println(numberOfGenerations);
	}
	
	public static void createFile(String filename){
		try {
			PrintWriter writer = new PrintWriter(filename, "UTF-8");
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void writeToFile(String filename, String textToWrite){
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
		    out.println(textToWrite);
		    out.close();
		} catch (Exception e) {
		    //exception handling left as an exercise for the reader
			e.printStackTrace();
		}
	}

}
