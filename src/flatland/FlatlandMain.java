package flatland;

import java.util.ArrayList;
import java.util.Arrays;

import ann.FlatlandAnn;
import ch.aplu.jgamegrid.GameGrid;

import com.google.gson.Gson;
import commons.Params;

@SuppressWarnings("serial")
public class FlatlandMain{
	
	public static void main(String[] args) {
		Board world = new Board();
		System.out.println(new Gson().toJson(world));
		int[] myInts = new int[18];
		for (int i = 0; i < myInts.length; i++) {
			myInts[i] = i;
		}
//		System.out.println(new Gson().toJson( Arrays.copyOfRange(myInts, 2
//							* Params.numberOfFlatlandInputNeurons, 2
//							* Params.numberOfFlatlandInputNeurons
//							+ Params.numberOfFlatlandInputNeurons)));
		System.out.println(new Gson().toJson(world.agent));
		System.out.println();
		System.out.println(world.toString());
		
//		world.moveAgent(Direction.RIGHT);
//		System.out.println(world.toString());
//		
//		world.moveAgent(Direction.RIGHT);
//		System.out.println(new Gson().toJson(world.agent));
//		System.out.println(world.toString());
//		
		for (int i = 0; i < 9; i++) {
			world.moveAgent(Direction.AHEAD);
			System.out.println(world.toString());
		}
		
		System.out.println(new Gson().toJson(world.agent));
		double[] weights = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		FlatlandAnn ann = new FlatlandAnn(weights);
		System.out.println(new Gson().toJson(ann.chooseDirection(world)));
	}
	
	
	/**Runs a single flatland scenario untill the maximall number of steps has been made, 
	 * or the agent has eaten all the food (unlikely to happen) */
	public static void runSingleFlatland(FlatlandAnn ann, Board world){
		double foodInFlatlandScenario = Params.flatlandFoodRatio * Params.flatlandBoardSizeX * Params.flatlandBoardSizeY;
		while(world.agent.movesDone < Params.flatlandMaxNumberOfMovesPerScenario && world.agent.foodEaten < foodInFlatlandScenario){
			world.moveAgent(ann.chooseDirection(world));
		}
	}
	
	/**Takes an ann and a set of flatland-worlds and runs them with the runSingleFlatland() method.
	 * Meant for use by the ea. */
	public static void runFlatlandSet(FlatlandAnn ann, Board[] worlds){
		for (Board world : worlds) {
			runSingleFlatland(ann, world);
		}
	}
}



































