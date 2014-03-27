package flatland;

import java.util.Arrays;

import com.google.gson.Gson;
import commons.Params;

public class FlatlandMain {
	
	public static void main(String[] args) {
		Board world = new Board();
		System.out.println(new Gson().toJson(world));
		int[] myInts = new int[18];
		for (int i = 0; i < myInts.length; i++) {
			myInts[i] = i;
		}
		System.out.println(new Gson().toJson( Arrays.copyOfRange(myInts, 2
							* Params.numberOfFlatlandInputNeurons, 2
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons)));
	}
}
