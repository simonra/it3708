package flatland;

import java.util.ArrayList;
import java.util.Collections;

import commons.Params;


public class Board {
	/** Contains the board itself */
	public TileContent[][] board;
	/**The agent operating in the flatland world*/
	public Agent agent;

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
		int ammountOfPoisonToMake = (int) (0.5 * numberOfFlatlandSquares * Params.flatlandPoisnonRatio);
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
	
	
	/**Checks the agents current position and orientation, 
	 * calculates target square, 
	 * updates the square the agent moved from to contain empty,
	 * calls agents performMove with the direction given and the content of the tile it's moving to,
	 * updates the square tha agent moves to to contain agent */
	public void moveAgent(Direction direction){
		int targetXPos = -1;
		int targetYPos = -1;
		if( (agent.orientation == Direction.UP && direction == Direction.AHEAD ) 
			|| (agent.orientation == Direction.LEFT && direction == Direction.RIGHT )
			|| (agent.orientation == Direction.RIGHT && direction == Direction.LEFT ) 
			){
			targetXPos = agent.xPosition;
			targetYPos = agent.yPosition - 1;
		}else if( (agent.orientation == Direction.DOWN && direction == Direction.AHEAD )
				|| ( agent.orientation == Direction.LEFT && direction == Direction.LEFT )
				|| ( agent.orientation == Direction.RIGHT && direction == Direction.RIGHT )
				){
			targetXPos = agent.xPosition;
			targetYPos = agent.yPosition + 1;
		}else if( ( agent.orientation == Direction.LEFT && direction == Direction.AHEAD )
				|| ( agent.orientation == Direction.UP && direction == Direction.LEFT )
				|| ( agent.orientation == Direction.DOWN && direction == Direction.RIGHT )
				){
			targetXPos = agent.xPosition - 1;
			targetYPos = agent.yPosition;
		}else{
			targetXPos = agent.xPosition + 1;
			targetYPos = agent.yPosition;
		}
		targetXPos = targetXPos % Params.flatlandBoardSizeX;
		targetYPos = targetYPos % Params.flatlandBoardSizeY;
		if(targetXPos < 0) targetXPos = Params.flatlandBoardSizeX - 1;
		if(targetYPos < 0) targetYPos = Params.flatlandBoardSizeY - 1;
		board[agent.xPosition][agent.yPosition] = TileContent.EMPTY;
		agent.performMove(direction, board[targetXPos][targetYPos]);
		board[targetXPos][targetYPos] = TileContent.AGENT;
	}
	
	
	
	
	/**ToString for testing purposes*/
	public String toString(){
		String outString = "";
		for (int i = 0; i < Params.flatlandBoardSizeX; i++) {
			for (int j = 0; j < Params.flatlandBoardSizeY; j++) {
				if(board[i][j] == TileContent.EMPTY)
					outString += "#";
				else if(board[i][j] == TileContent.FOOD)
					outString += "+";
				else if(board[i][j] == TileContent.POISON)
					outString += "-";
				else if(board[i][j] == TileContent.AGENT)
					outString += "A";
			}
			outString += "\n";
		}
		return outString;
	}
	
	/**Makes a deep copy of the board*/
	public Board copyBoard(){
		Board copy = new Board();
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[0].length; j++) {
				copy.board[i][j] = this.board[i][j];
			}
		}
		copy.agent = this.agent.copyAgent();
		return copy;
	}
}
