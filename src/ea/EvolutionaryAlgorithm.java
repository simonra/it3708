package ea;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;

import commons.Params;
import commons.Params.Problem;

public class EvolutionaryAlgorithm {

	public static void main(String[] args) {
		createFile("MytextFile.txt");
		createFile("Surprising sequences log file");
		evolutionaryAlgorithm();
//		Map<String, String> mymap = new HashMap<String, String>();
//		mymap.put("asdf", "qwer");
//		//contains key
//		if(mymap.containsKey("asdf"))
//			System.out.println(mymap.get("a"));
	}
	static boolean loopCheckAndOtherMagic(Population population){
		if(Params.CURRENT_PROBLEM == Problem.GlobalSurprisingSequences || Params.CURRENT_PROBLEM == Problem.LocalSurprisingSequences ){
			if(population.isSolved()){
				String writeString = "";
				writeString = "Size of symbol set: " + Params.SYMBOL_SET_SIZE;
				writeToFile("Surprising sequences log file", writeString);
				writeString = "Population size: " + Params.SIZE_OF_POPULATION;
				writeToFile("Surprising sequences log file", writeString);
				writeString = "Generations: " + numberOfGenerations;
				writeToFile("Surprising sequences log file", writeString);
				writeString = "Sequence length: " + Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE;
				writeToFile("Surprising sequences log file", writeString);
				writeString = "Sequence: " + population.bestIndividual.toString();
				writeToFile("Surprising sequences log file", writeString);
				
				Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE++;
				System.out.println("Sequence size: "+Params.SURPRISING_SEQUENCES_SEQUENCE_SIZE);
//				Population theNewPopulation = new Population();
//				population = theNewPopulation;
				System.out.println("Size of sequences in the population: " + population.children.get(0).toString());
				numberOfGenerations = 0;
				evolutionaryAlgorithm();
				return false;
			}else if(numberOfGenerations > Params.MAX_NUMBER_OF_GENERATIONS){
				return false;
			}
			else
				return true;
		}
		else{
			return !population.isSolved();
		}
		
//		return true;
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
			writeToFile("MytextFile.txt", "Best fittness: " + population.bestFitness);
			writeToFile("MytextFile.txt", "Best individual: " + population.bestIndividual);
			writeToFile("MytextFile.txt", "Average fitness of populaion: " + population.averageFitness);
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
