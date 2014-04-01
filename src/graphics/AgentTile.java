package graphics;

import ann.FlatlandAnn;
import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.jgamegrid.Location.CompassDirection;
import flatland.Board;

public class AgentTile extends Actor{
	Board board;
	FlatlandAnn ann;
	public AgentTile(Board world, FlatlandAnn ann){
		super("sprites/turtle4.gif");
		this.board = world;
		this.ann = ann;
	}
	
	public void act(){
//		System.out.println("stuff");
		this.setDirection(CompassDirection.NORTH);
		Location locationMovedFrom = new Location(board.agent.xPosition, board.agent.yPosition);
		board.moveAgent(ann.chooseDirection(board));
		//find the agents new position in the board, update the jgame grid,
		Location locationMovedTo = new Location(board.agent.xPosition, board.agent.yPosition);
		Actor tileMovedTo = gameGrid.getOneActorAt(locationMovedTo);
		if(tileMovedTo != null) tileMovedTo.hide();
		this.setLocation(locationMovedTo);
//		this.move();
//		this.move(-1);
//		this.setDirection(0);
		this.turn(180);
		this.set
//		this.setLocation(board.agent.xPosition, board.agent.yPosition);
		//remember to turn the agent
		
	}
}
