package graphics;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location.CompassDirection;

public class FoodTile extends Actor{
	public FoodTile(){
		super("sprites/appel.gif");
	}
	
	public void act(){
		this.setDirection(CompassDirection.EAST);
	}
}
