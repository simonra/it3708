package graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import commons.Params;

import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;
import ea.FlatlandGenotype;

public class FlatlandGrid extends GameGrid{
	Gson gson;
	
	
	public FlatlandGrid() {
		super(Params.flatlandBoardSizeX, Params.flatlandBoardSizeY, 60, java.awt.Color.red);
		addActor(new AgentTile(), new Location(0, 0));
		addActor(new PoisonTile(), new Location(0, 1));
		addActor(new FoodTile(), new Location(0, 2));
//		gson = new Gson();
//		FlatlandGenotype genotype = gson.fromJson(readTextFromFile("bestFlatlandAnnPhenotype.txt"), FlatlandGenotype.class);
//		addActor(new FlatlandAgent(), new Location(0,0));
		show();
	}
	
	public static void main(String[] args) {
		FlatlandGrid flg = new FlatlandGrid();
	}
	
	public static String readTextFromFile(String fileName){
		BufferedReader br = null;
		String readString = "";
		 
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(fileName));
 
			while ((sCurrentLine = br.readLine()) != null) {
				readString += sCurrentLine;
//				System.out.println(sCurrentLine);
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return readString;
	}
}
