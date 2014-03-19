package ann;

import flatland.Direction;

public class Params {
	/**Number of layers in the artificial neural netowrk, including input and output layer*/
	public static int totalNumberOfLayers = 2;
	/**Number of hidden layers in the ann*/
	public static int HiddenLayers = 0;
	/**The number of neurons in each layer*/
	public static int NeuronsPerLayer = 10;
	
	/**Percentage of the flatland world to be covered in food*/
	public static double flatlandFoodRatio = 0.5;
	/**Percentage of the flatland world to be covered with poison*/
	public static double flatlandPoisnonRatio = 0.5;
	/**Size of the flatland world in the x direction*/
	public static int flatlandBoardSizeX = 8;
	/**Size of the flatland world in the y direction*/
	public static int flatlandBoardSizeY = 8;
	/**The agetns starting point in the flatland worlds x direction*/
	public static int flatlandAgentStartingPosX = 0;
	/**The agetns starting point in the flatland worlds y direction*/
	public static int flatlandAgentStartingPosY = 0;
	/**The agents heading when it is initialized in the flatland world*/
	public static Direction flatlandAgentStartingDirection = Direction.LEFT;
}
