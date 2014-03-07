package flatland;

public class Agent {
	//Primarily used for making drawing the agent easier
	Direction orientation;
	//What the agent has eaten so far
	int poisonEaten;
	int foodEaten;
	//Used to determine the agents current position on the board
	int xPosition;
	int yPosition;
	/*Used for fitness evaluation (an agent that runs around in circles
	 *  and doesn't interact with its environment is a boring agent)*/
	int movesDone;
	
	
	public void performMove(Direction movementDirection, TileContent contentOfDestinationTile){
		//Update the orientation of the agent
		if(movementDirection == Direction.LEFT){
			if(orientation == Direction.UP) orientation = Direction.LEFT;
			else if(orientation == Direction.LEFT) orientation = Direction.DOWN;
			else if(orientation == Direction.DOWN) orientation = Direction.RIGHT;
			else orientation = Direction.UP;
		}else if(movementDirection == Direction.RIGHT){
			if(orientation == Direction.UP) orientation = Direction.RIGHT;
			else if(orientation == Direction.RIGHT) orientation = Direction.DOWN;
			else if(orientation == Direction.DOWN) orientation = Direction.LEFT;
			else orientation = Direction.UP;
		}//Else movementDirection = ahead and we need not update the orientation of the agent
		
		//Update the counters of what the agent has eaten
		if(contentOfDestinationTile == TileContent.FOOD) foodEaten++;
		if(contentOfDestinationTile == TileContent.POISON) poisonEaten++;
		
		//Update position
		if(orientation == Direction.UP){
			yPosition = (yPosition - 1) % 8;
			if(yPosition == -1) yPosition = 7;
		}else if(orientation == Direction.DOWN){
			yPosition = (yPosition + 1) % 8;
		}else if(orientation == Direction.LEFT){
			xPosition = (xPosition - 1) % 8;
			if(xPosition == -1) xPosition = 7;
		}else{
			xPosition = (xPosition + 1) % 8;
		}
		
		//Update movesDone counter
		movesDone++;
		
		//TODO: oppdatere fitness-counter her?
	}
}
