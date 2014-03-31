package flatland;

import java.util.Arrays;

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
		System.out.println();
		System.out.println(world.toString());
		
		world.moveAgent(Direction.RIGHT);
		System.out.println(world.toString());
		world.moveAgent(Direction.RIGHT);
		System.out.println(world.toString());
		for (int i = 0; i < 9; i++) {
			world.moveAgent(Direction.AHEAD);
			System.out.println(world.toString());
		}
	}
}
