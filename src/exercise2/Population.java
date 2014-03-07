package exercise2;

import java.util.ArrayList;
import java.util.Collections;

import exercise2.Params.AdultSelectionType;
import exercise2.Params.Problem;

public class Population {
	EAHeuristic eaHeuristic;
	ParentSelection parentSelection;
	ArrayList<Genotype> adults;
	ArrayList<Genotype> children;
	int sizeOfPopulation;
	double bestFitness;
	double averageFitness;
	Genotype bestIndividual;

	public Population() {
		parentSelection = new ParentSelection();
		adults = new ArrayList<Genotype>();
		children = new ArrayList<Genotype>();
		sizeOfPopulation = Params.SIZE_OF_POPULATION;

		for (int i = 0; i < sizeOfPopulation; i++) {
			if (Params.CURRENT_PROBLEM == Problem.OneMax)
				children.add(new OneMaxGenotype());
			else if (Params.CURRENT_PROBLEM == Problem.GlobalSurprisingSequences)
				children.add(new SurprisingSequencesGenotype());
			else if (Params.CURRENT_PROBLEM == Problem.LocalSurprisingSequences)
				children.add(new SurprisingSequencesGenotype());
			else
				children.add(null);
		}
		
		if(Params.CURRENT_PROBLEM == Problem.OneMax)
			eaHeuristic = new OneMaxHeuristic();
		else if(Params.CURRENT_PROBLEM == Problem.GlobalSurprisingSequences)
			eaHeuristic = new SurprisingSequencesHeuristic();
		else if(Params.CURRENT_PROBLEM == Problem.LocalSurprisingSequences)
			eaHeuristic = new SurprisingSequencesHeuristic();
	}

	public void developPopulation() {
		for (Genotype child : children) {
			child.generatePhenotype();
		}
	}

	public void createFitnesses() {
		averageFitness = 0;
		for (Genotype child : children) {
			child.setFitness(eaHeuristic.getGenotypeFitness(child));
			averageFitness += child.getFitness();
			if(child.getFitness() > bestFitness){
				bestFitness = child.getFitness();
				bestIndividual = child;
			}
		}
		averageFitness /= Params.SIZE_OF_POPULATION;
	}

	public void adultSelection() {
		if(Params.ADULT_SELECTION_TYPE == AdultSelectionType.FullGenerationalReplacement)
			fullGenerationalReplacement();
		else if(Params.ADULT_SELECTION_TYPE == AdultSelectionType.OverProductionReplacement)
			overProductionReplacement();
		else
			generationalMixing();
	}

	public void reproduction() {
//		System.out.println("I get this far");
		// select parent pairs
		ArrayList<Genotype> parentPairs = parentSelection.selectParentPairs(adults);
		// do reproduction
		children.clear();
		for (int i = 0; i < Params.NUMBER_OF_PAIRS_THAT_GET_TO_REPRODUCE; i++) {
			if(Math.random() < Params.CROSSOVER_CHANCE){
				children.addAll(parentPairs.get(2 * i).generateChildren(parentPairs.get(2 * i + 1)));				
			}
			else{
				children.add(parentPairs.get(2 * i));
				children.add(parentPairs.get(2 * i + 1));
			}
		}
		for (Genotype child : children) {
			child.mutate();
		}
	}

	public boolean isSolved() {
		return bestFitness == 1;
	}
	
	void fullGenerationalReplacement(){
		adults.clear();
//		adults = new ArrayList<Genotype>(sizeOfPopulation);
//		Collections.copy(adults, children);
		adults.addAll(children);
		children.clear();
	}
	
	void overProductionReplacement(){
		adults.clear();
		Collections.sort(children, children.get(0).getComparator());
//		Collections.copy(adults, children.subList(children.size() - Params.SIZE_OF_POPULATION, children.size()));
		adults.addAll(children.subList(children.size() - Params.SIZE_OF_POPULATION, children.size()));
		children.clear();
	}
	
	void generationalMixing(){
		children.addAll(adults);
		overProductionReplacement();
	}
}
























