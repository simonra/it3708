package ea;

import java.util.ArrayList;
import java.util.Collections;

import commons.Params;
import commons.Params.AdultSelectionType;


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
			children.add(new FlatlandGenotype());
		}
		eaHeuristic = new FlatlandHeuristic();
	}

	public void developPopulation() {
		for (Genotype child : children) {
			child.generatePhenotype();
		}
	}

	public void createFitnesses() {
		//TODO gjør alt
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
		
		eaHeuristic.refreshStockWorlds();
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
		return false;
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
























