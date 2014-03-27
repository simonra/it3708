package flatland;

import commons.Params;

public class Agent {
	// Primarily used for making drawing the agent easier
	Direction orientation;
	// What the agent has eaten so far
	int poisonEaten;
	int foodEaten;
	// Used to determine the agents current position on the board
	int xPosition;
	int yPosition;
	/**
	 * Used for fitness evaluation (an agent that runs around in circles and
	 * doesn't interact with its environment is a boring agent)
	 */
	int movesDone;
	/** The current fitness of the agent */
	double fitness;

	TileContent contentLeft;
	TileContent contentAhead;
	TileContent contentRight;
	
	
	/**
	 * Constructor of the flatland agent. Initializes it in a certain direction
	 * on a certain position, sets the food and poison consumed to 0, and sets
	 * the number of moves it has done to 0. Then increases counters based on
	 * the content of the tile it starts on (consumes it)
	 * 
	 * @param contentOfStartingTile
	 *            The content of the tile the agent starts on. Is consumed when
	 *            the agent is initialized.
	 * @param orientation
	 *            The direction the agent faces when it is initialized
	 * @param xPosition
	 *            The agents position in the x direction when it is initialized
	 * @param yPosition
	 *            The agents position in the y direction when it is initialized
	 */
	public Agent(TileContent contentOfStartingTile, Direction orientation,
			int xPosition, int yPosition) {
		this.orientation = orientation;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		this.movesDone = 0;
		this.poisonEaten = 0;
		this.foodEaten = 0;
		if (contentOfStartingTile == TileContent.FOOD)
			foodEaten++;
		else if (contentOfStartingTile == TileContent.POISON)
			poisonEaten++;
		this.fitness = getFitness();
	}

	/** Alternative constructor for copying, don't use outside class */
	public Agent() {

	}

	/**
	 * Takes the direction it moves and the content of the tile it moves to, and
	 * updates itself accordingly
	 */
	public void performMove(Direction movementDirection,
			TileContent contentOfDestinationTile) {
		// Update the orientation of the agent
		if (movementDirection == Direction.LEFT) {
			if (orientation == Direction.UP)
				orientation = Direction.LEFT;
			else if (orientation == Direction.LEFT)
				orientation = Direction.DOWN;
			else if (orientation == Direction.DOWN)
				orientation = Direction.RIGHT;
			else
				orientation = Direction.UP;
		} else if (movementDirection == Direction.RIGHT) {
			if (orientation == Direction.UP)
				orientation = Direction.RIGHT;
			else if (orientation == Direction.RIGHT)
				orientation = Direction.DOWN;
			else if (orientation == Direction.DOWN)
				orientation = Direction.LEFT;
			else
				orientation = Direction.UP;
		}// Else movementDirection = ahead and we need not update the
			// orientation of the agent

		// Update the counters of what the agent has eaten
		if (contentOfDestinationTile == TileContent.FOOD)
			foodEaten++;
		else if (contentOfDestinationTile == TileContent.POISON)
			poisonEaten++;

		// Update position
		if (orientation == Direction.UP) {
			yPosition = (yPosition - 1) % 8;
			if (yPosition == -1)
				yPosition = 7;
		} else if (orientation == Direction.DOWN) {
			yPosition = (yPosition + 1) % 8;
		} else if (orientation == Direction.LEFT) {
			xPosition = (xPosition - 1) % 8;
			if (xPosition == -1)
				xPosition = 7;
		} else {
			xPosition = (xPosition + 1) % 8;
		}

		// Update movesDone counter
		movesDone++;

		// TODO: oppdatere fitness-counter her?
	}

	/**
	 * Calculates the fintess of the agent in its current state, and returns its
	 * and sets it in the agent
	 */
	public double getFitness() {
		double fitness = this.foodEaten - this.poisonEaten;
		// Sannsynligvis ikke bra å ha da dette er et punishement som avhenger
		// veldig av hvordan layouten til verdenen er, og som uansett vises
		// sammen med andre sluttsats når agenten er ferdig
		// //Punishes the agent for doing lots of moves wihtout eating
		// fitness -= this.movesDone - this.foodEaten;
		this.fitness = fitness;
		return fitness;
	}

	
	/**
	 * Takes a board and tries to predict what would be the best move to have as
	 * the agents next move
	 */
	public Direction evaluateNextMove(Board world) {
		if (!(world.board[this.xPosition][this.yPosition] == TileContent.AGENT)) {
			System.out
					.println("The board does not contain the agent in the position the agent believes to be! \nPlease troubleshoot, aborting evaluation of next move");
			return null;
		}

		Direction bestDirection = null;
		double bestTileFintess = -2;
		int xPosLeft;
		int xPosRight;
		int xPosAhead;
		int yPosLeft;
		int yPosRight;
		int yPosAhead;
		switch (this.orientation) {
		case UP:
			xPosLeft = this.xPosition - 1 < 0 ? Params.flatlandBoardSizeX - 1
					: this.xPosition - 1;
			xPosRight = (this.xPosition + 1) % Params.flatlandBoardSizeX;
			xPosAhead = this.xPosition;

			yPosLeft = this.yPosition;
			yPosRight = this.yPosition;
			yPosAhead = this.yPosition - 1 < 0 ? Params.flatlandBoardSizeY - 1
					: this.yPosition - 1;
			break;
		case DOWN:
			xPosLeft = (this.xPosition + 1) % Params.flatlandBoardSizeX;
			xPosRight = this.xPosition - 1 < 0 ? Params.flatlandBoardSizeX - 1
					: this.xPosition - 1;
			xPosAhead = this.xPosition;

			yPosLeft = this.yPosition;
			yPosRight = this.yPosition;
			yPosAhead = (this.yPosition + 1) % Params.flatlandBoardSizeY;
			break;
		case LEFT:
			xPosLeft = this.xPosition;
			xPosRight = this.xPosition;
			xPosAhead = this.xPosition - 1 < 0 ? Params.flatlandBoardSizeX - 1
					: this.xPosition - 1;

			yPosLeft = (this.yPosition + 1) % Params.flatlandBoardSizeY;
			yPosRight = this.yPosition - 1 < 0 ? Params.flatlandBoardSizeY - 1
					: this.yPosition - 1;
			yPosAhead = this.yPosition;
			break;
		case RIGHT:
			xPosLeft = this.xPosition;
			xPosRight = this.xPosition;
			xPosAhead = (this.xPosition + 1) % Params.flatlandBoardSizeX;

			yPosLeft = this.yPosition - 1 < 0 ? Params.flatlandBoardSizeY - 1
					: this.yPosition - 1;
			yPosRight = (this.yPosition + 1) % Params.flatlandBoardSizeY;
			yPosAhead = this.yPosition;
			break;
		default:
			xPosLeft = -1;
			xPosRight = -1;
			xPosAhead = -1;

			yPosLeft = -1;
			yPosRight = -1;
			yPosAhead = -1;
			break;
		}
		// Check which direction gives best result, and return it. Else return
		// random
		switch (world.board[xPosLeft][yPosLeft]) {
		case FOOD:
			this.contentLeft = TileContent.FOOD;
			bestTileFintess = 1;
			bestDirection = Direction.LEFT;
//			return Direction.LEFT;
		case POISON:
			this.contentLeft = TileContent.POISON;
			if (bestTileFintess < 0) {
				bestTileFintess = -1;
				bestDirection = Direction.LEFT;
			}
			break;
		default:
			this.contentLeft = TileContent.EMPTY;
			if (bestTileFintess < 1) {
				bestTileFintess = 0;
				bestDirection = Direction.LEFT;
			}
			break;
		}
		switch (world.board[xPosRight][yPosRight]) {
		case FOOD:
			this.contentRight = TileContent.FOOD;
			bestTileFintess = 1;
			bestDirection = Direction.RIGHT;
//			return Direction.RIGHT;
		case POISON:
			this.contentRight = TileContent.POISON;
			if (bestTileFintess < 0) {
				bestTileFintess = -1;
				bestDirection = Direction.RIGHT;
			}
			break;
		default:
			this.contentRight = TileContent.EMPTY;
			if (bestTileFintess < 1) {
				bestTileFintess = 0;
				bestDirection = Direction.RIGHT;
			}
			break;
		}
		switch (world.board[xPosAhead][yPosAhead]) {
		case FOOD:
			this.contentAhead = TileContent.FOOD;
			bestTileFintess = 1;
			bestDirection = Direction.AHEAD;
//			return Direction.AHEAD;
		case POISON:
			this.contentAhead = TileContent.POISON;
			if (bestTileFintess < 0) {
				bestTileFintess = -1;
				bestDirection = Direction.AHEAD;
			}
			break;
		default:
			this.contentAhead = TileContent.EMPTY;
			if (bestTileFintess < 1) {
				bestTileFintess = 0;
				bestDirection = Direction.AHEAD;
			}
			break;
		}
		return bestDirection;
		// If no optimum is found, returns a random direction, with a sligt bias
		// towards the direction it is already heading;
		// return Direction.values()[(int)
		// Math.random()*Direction.values().length];
	}
	
	public boolean foodLeft(){
		return this.contentLeft == TileContent.FOOD ? true : false;
	}
	public boolean foodAhead(){
		return this.contentAhead == TileContent.FOOD ? true : false;
	}
	public boolean foodRight(){
		return this.contentRight == TileContent.FOOD ? true : false;
	}
	public boolean poisonLeft(){
		return this.contentLeft == TileContent.POISON ? true : false;
	}
	public boolean poisonAhead(){
		return this.contentAhead == TileContent.POISON ? true : false;
	}
	public boolean poisonRight(){
		return this.contentRight == TileContent.POISON ? true : false;
	}

	/** Makes a deep copy of this agent */
	public Agent copyAgent() {
		Agent copyAgent = new Agent();
		copyAgent.xPosition = this.xPosition;
		copyAgent.yPosition = this.yPosition;
		copyAgent.orientation = this.orientation;
		copyAgent.movesDone = this.movesDone;
		copyAgent.foodEaten = this.foodEaten;
		copyAgent.poisonEaten = this.poisonEaten;
		return copyAgent;
	}
}
