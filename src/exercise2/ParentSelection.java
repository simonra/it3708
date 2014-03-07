package exercise2;

import java.util.ArrayList;
import java.util.Collections;

import exercise2.Params.ParentSelectionType;

public class ParentSelection {

	ArrayList<Genotype> adults;
	ArrayList<Genotype> pairs;

	public ArrayList<Genotype> selectParentPairs(ArrayList<Genotype> adults) {
		this.adults = new ArrayList<Genotype>();
		this.adults.addAll(adults);
		pairs = new ArrayList<Genotype>();
		
		if (Params.PARENT_SELECTION_TYPE == ParentSelectionType.TournamentSelection) {
			tournamentSelection();
		} else if (Params.PARENT_SELECTION_TYPE == ParentSelectionType.UniformSelection) {
			uniformSelection();
		} else if (Params.PARENT_SELECTION_TYPE == ParentSelectionType.FitnessProportionateSelection) {
			fitnessProportionateSelection();
		} else if (Params.PARENT_SELECTION_TYPE == ParentSelectionType.SigmaScalingSelection) {
			sigmaScalingSelection();
		} else
			System.out.println("No parent selection type selected");

		return pairs;
	}
	
	boolean noMatingWithSelf(Genotype genotype){
//		System.out.println("I do a lot of self-mating");
		if(pairs.size()%2 == 1 && pairs.get(pairs.size() - 1) == genotype)
			return false;
		else return true;
//		return pairs.size()%2 == 1 && pairs.get(pairs.size() - 1) != genotype;
	}
	
	void tournamentSelection(){
		//The group of genotypes competing to be a part of the next mating pair
		ArrayList<Genotype> currentGroup;
		//Copy of adults so that we can pop from the list without damaging it
		ArrayList<Genotype> copyOfAdults = new ArrayList<Genotype>();
		//Adds new genotypes to mate untill a sufficient number of pairs has been added
		while(pairs.size() < 2 * Params.NUMBER_OF_PAIRS_THAT_GET_TO_REPRODUCE){
			//Clears currentgroup and resets copyOfAdults:
			currentGroup = new ArrayList<Genotype>();
//			Collections.copy(copyOfAdults, adults);
			copyOfAdults.addAll(adults);
			//Create a group with k random members
			while(currentGroup.size() < Params.TOURNAMENT_SELECTION_GROUP_SIZE){
				currentGroup.add(copyOfAdults.remove( (int) Math.random() * copyOfAdults.size() ) );
			}
			Collections.sort(currentGroup, currentGroup.get(0).getComparator());
			//Check if random number is less than 1 - €
			if(Math.random() < 1 - Params.TOURNAMENT_SELECTION_THRESHOLD){
				//No mating with self
				if(noMatingWithSelf(currentGroup.get(currentGroup.size() - 1) )){
					pairs.add(currentGroup.get(currentGroup.size() - 1));
				}
				else{
//					System.out.println("Im stuck here");
					continue;
				}
			}
			//If random from group instead of best:
			else{
				//Choose random
				Genotype randomGenotype = currentGroup.get( (int) Math.random() * currentGroup.size() );
				//No mating with self
				if(noMatingWithSelf(randomGenotype)){
					pairs.add(randomGenotype);
				}
			}
		}
	}
	
	void uniformSelection(){
		//Adds new genotypes to mate untill a sufficient number of pairs has been added
		ArrayList<Genotype> copyOfAdults = new ArrayList<Genotype>();
		while(pairs.size() < 2 * Params.NUMBER_OF_PAIRS_THAT_GET_TO_REPRODUCE){
			Genotype randomGenotype;
			if(pairs.size()%2 == 0){
				copyOfAdults.addAll(adults);
				randomGenotype = copyOfAdults.remove( (int) Math.random() * adults.size() );
			}
			randomGenotype = copyOfAdults.get( (int) Math.random() * adults.size() );
			//No mating with self
			if(noMatingWithSelf(randomGenotype)){
			}
				pairs.add(randomGenotype);
		}
//		System.out.println("I end the while");
	}
	
	void fitnessProportionateSelection(){
		double sumOfFitnesses = 0;
		for (Genotype adult : adults) {
			sumOfFitnesses += adult.getFitness(); 
		}
		//Adds new genotypes to mate untill a sufficient number of pairs has been added
		while(pairs.size() < 2 * Params.NUMBER_OF_PAIRS_THAT_GET_TO_REPRODUCE){
			Genotype randomGenotype = wheelOfFortune(0, Math.random() * sumOfFitnesses);
			//No mating with self
			if(noMatingWithSelf(randomGenotype)){
				pairs.add(randomGenotype);
			}
		}
	}
	
	/*Første remaining = random*summen av fitnesses*/
	Genotype wheelOfFortune(int id, double remaining) {
		if(id >= Params.SIZE_OF_POPULATION)
			if(noMatingWithSelf( adults.get(Params.SIZE_OF_POPULATION - 1) ))
				return adults.get(Params.SIZE_OF_POPULATION - 1);
			else
				return adults.get(Params.SIZE_OF_POPULATION - 2);
		if(adults.get(id).getFitness() > remaining) {
			return adults.get(id);
		} else {
			return wheelOfFortune(id+1, remaining - adults.get(id).getFitness());
		}
//		return adults.get(id).getFitness() > remaining ?    adults.get(id)    :     wheelOfFortune(id+1, remaining - adults.get(id).getFitness()  );
	}
	
	void sigmaScalingSelection(){
		// Expected value =  1 + ( (fitness of individual - average fitness of population) / (2 * standard deviation of population fitness) )
		double averageFitnessOfPopulation = 0;
		double standardDeviationOfPopulationFitness = 0;
		
		//Find the average fitness:
		for (Genotype adult : adults) {
			averageFitnessOfPopulation += adult.getFitness();
		}
		averageFitnessOfPopulation /= adults.size();
		
		//Find the standard deviation
		for (Genotype adult : adults) {
			standardDeviationOfPopulationFitness += (adult.getFitness() - averageFitnessOfPopulation) * (adult.getFitness() - averageFitnessOfPopulation);
		}
		standardDeviationOfPopulationFitness = Math.sqrt(standardDeviationOfPopulationFitness / adults.size());
		
		//Set the fintess values of each individual to the sigma scaled expected values so that you can use 
		//fitnessPoroportionalSelection with wheelOfFortune to wheelOfFortune style select with the new weights 
		for (Genotype adult : adults) {
			adult.setFitness(1 + (adult.getFitness() - averageFitnessOfPopulation) / (2 * standardDeviationOfPopulationFitness) );
		}
		
		fitnessProportionateSelection();
	}
}









