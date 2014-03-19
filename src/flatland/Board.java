package flatland;

import java.util.ArrayList;
import java.util.Collections;

import ann.Params;

public class Board {
	/** Contains the board itself */
	TileContent[][] board;
	/**The agent operating in the flatland world*/
	Agent agent;

	/**
	 * The constructor for the flatland world. Fills the tiles according to the
	 * food to poison ratio, and places the agent.
	 */
	public Board() {
		//Initializes the board
		board = new TileContent[Params.flatlandBoardSizeX][Params.flatlandBoardSizeY];
		
		//Holds food and poison to be distributed
		ArrayList<TileContent> contentToBeDistributedOnBoard = new ArrayList<TileContent>();
		int numberOfFlatlandSquares = Params.flatlandBoardSizeX * Params.flatlandBoardSizeY;
		int ammountOfFoodToMake = (int) (numberOfFlatlandSquares * Params.flatlandFoodRatio); //int-cast rounds down
		int ammountOfPoisonToMake = (int) (numberOfFlatlandSquares * Params.flatlandPoisnonRatio);
		int ammountOfEmptySquaresToMake = numberOfFlatlandSquares - ammountOfFoodToMake - ammountOfPoisonToMake;
		for (int i = 0; i < ammountOfFoodToMake; i++) {
			contentToBeDistributedOnBoard.add(TileContent.FOOD);
		}
		for (int i = 0; i < ammountOfPoisonToMake; i++) {
			contentToBeDistributedOnBoard.add(TileContent.POISON);
		}
		for (int i = 0; i < ammountOfEmptySquaresToMake; i++) {
			contentToBeDistributedOnBoard.add(TileContent.EMPTY);
		}
		//Shuffles the content to be added, so than when we add it by linearly iterating over the board we always add a unique random element
		Collections.shuffle(contentToBeDistributedOnBoard);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = contentToBeDistributedOnBoard.remove(0);
			}
		}
		
		//Initializes the agent and adds it to the board
		TileContent contentOfAgentStartingTile = board[Params.flatlandAgentStartingPosX][Params.flatlandAgentStartingPosY];
		this.agent = new Agent(contentOfAgentStartingTile, Params.flatlandAgentStartingDirection, Params.flatlandAgentStartingPosX, Params.flatlandAgentStartingPosY);
		board[Params.flatlandAgentStartingPosX][Params.flatlandAgentStartingPosY] = TileContent.AGENT;
	}
	
	
}
