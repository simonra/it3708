package commons;

import ann.NeuronType;
import flatland.Direction;

public class Params {
	/**Number of layers in the artificial neural netowrk, including input and output layer*/
	public static int totalNumberOfLayers = 2;
	/**Number of hidden layers in the ann*/
	public static int hiddenLayers = 0;
	/**The number of neurons in each layer*/
	public static int neuronsPerLayer = 10;
	//Types of neurons to use:
	/**Types of neurons used in the ann*/
	public static NeuronType neuronTypeToUse = NeuronType.LINEAR;
	//ANN activation function constants:
	/**Bias neuron weight*/
	public static double biasNeuronWeight = 0.0;
	/**The scaling constant for sigmoids*/
	public static double sigmoidScalingConstant = 1.0;
	/**The threshold for the step neurons*/
	public static double stepFunctionNeuronThreshold = 0.5;
	/**The cutoff below which the ramp neuron always fires 0*/
	public static double rampNeuronLowerThreshold = 0.2;
	/**The cutoff above which the ramp neuron always fires 1*/
	public static double rampNeuronUpperThreshold = 0.8;
	
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
	
	/**The maximal number of steps the flatland agent makes in each scenario */
	public static int flatlandMaxNumberOfMovesPerScenario = 50;
	
	/**The number of inputs to the flatland ann*/
	public static int numberOfFlatlandInputNeurons = 6;
	/**The number of outputs from the flatland ann*/
	public static int numberOfFlatlandOutputNeurons = 3;
	
	public static int maxNumberOfGenerations = 500;
}
