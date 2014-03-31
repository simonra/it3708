package ea;

import java.util.ArrayList;

import ann.FlatlandAnn;

import commons.Params;

import flatland.Board;
import flatland.FlatlandMain;

public class FlatlandHeuristic implements EAHeuristic {

	Board[] stockWorlds;
	
	public FlatlandHeuristic(){
		stockWorlds = new Board[Params.NUMBER_OF_FLATLAND_WORLDS];
		for (int i = 0; i < Params.NUMBER_OF_FLATLAND_WORLDS; i++) {
			stockWorlds[i] = new Board();
		}
	}
	
	@Override
	public double getGenotypeFitness(Genotype genotype) {
		FlatlandGenotype flatlandGenotype = (FlatlandGenotype) genotype;
		
		Board[] worlds = new Board[Params.NUMBER_OF_FLATLAND_WORLDS];
		for (int i = 0; i < worlds.length; i++) {
			worlds[i] = stockWorlds[i].copyBoard();
		}
		
		FlatlandAnn ann = new FlatlandAnn(flatlandGenotype.phenotype);
		FlatlandMain.runFlatlandSet(ann, worlds);
		
		double averageFitness = 0;
		for (Board world : worlds) {
			averageFitness += world.agent.getFitness();
		}
		averageFitness /= Params.NUMBER_OF_FLATLAND_WORLDS;
		return averageFitness;
	}
	
	
	public void refreshStockWorlds(){
		if(!Params.staticWorlds){
			for (int i = 0; i < Params.NUMBER_OF_FLATLAND_WORLDS; i++) {
				stockWorlds[i] = new Board();
			}				
		}
	}

}


























