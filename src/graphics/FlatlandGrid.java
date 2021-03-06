package graphics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

import ann.FlatlandAnn;

import com.google.gson.Gson;

import commons.Params;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;
import ea.FlatlandGenotype;
import flatland.Board;
import flatland.TileContent;

public class FlatlandGrid extends GameGrid{
	static Gson gson = new Gson();;
	
	
	public FlatlandGrid() {
		super(Params.flatlandBoardSizeX, Params.flatlandBoardSizeY, 60, java.awt.Color.CYAN);
		
		
		
		
//		FlatlandGenotype genotype = gson.fromJson(bestFlatlandAnnPhenotype, FlatlandGenotype.class);
//		FlatlandGenotype genotype = gson.fromJson(readTextFromFile("bestFlatlandAnnPhenotype.txt"), FlatlandGenotype.class);
//		addActor(new FlatlandAgent(), new Location(0,0));
//		show();
	}
	
	public static void main(String[] args) {
		FlatlandGrid[] grids;
		double[] weights;
		Board[] worlds;
		FlatlandAnn ann;
		
//		addActor(new AgentTile(), new Location(0, 0));
//		addActor(new PoisonTile(), new Location(0, 1));
//		addActor(new FoodTile(), new Location(0, 2));
		
		String bestFlatlandAnnPhenotype = readTextFromFile("bestFlatlandAnnPhenotype.txt");
		weights = gson.fromJson(bestFlatlandAnnPhenotype, double[].class);
		
		if(Params.staticWorlds){
			String flatlandWorlds = readTextFromFile("Worlds.txt");
			worlds = gson.fromJson(flatlandWorlds, Board[].class);			
		}else{
			worlds = new Board[5];
			for (int i = 0; i < 5; i++) {
				worlds[i] = new Board();
			}
		}
		
		ann = new FlatlandAnn(weights);
		
		//Make flatland game grids, and for each game grid, traverse worlds and annd acctor accordingly
		grids = new FlatlandGrid[worlds.length];
		for (int i = 0; i < worlds.length; i++) {
			FlatlandGrid grid = new FlatlandGrid();
			for (int j = 0; j < worlds[i].board.length; j++) {
				for (int k = 0; k < worlds[i].board[0].length; k++) {
					if(worlds[i].board[j][k] == TileContent.EMPTY){
						continue;
					}else if(worlds[i].board[j][k] == TileContent.FOOD){
						grid.addActor(new FoodTile(), new Location(j, k));
					}else if(worlds[i].board[j][k] == TileContent.POISON){
						grid.addActor(new PoisonTile(), new Location(j, k));
					}else if(worlds[i].board[j][k] == TileContent.AGENT){
						grid.addActor(new AgentTile(worlds[i], ann), new Location(j, k));
					}
				}
			}
			grids[i] = grid;
		}
		
		for (FlatlandGrid grid : grids) {
			grid.show();
		}
		
		Plot plot = new Plot();
		plot.showStats();
		
		System.out.println(gson.toJson(worlds[4].agent));
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
