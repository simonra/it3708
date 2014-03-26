package ann;

import commons.Params;

public class FlatlandAnn {
	/**
	 * The inputs the ann takes. The three first are the left, front, and right
	 * food sensors, the three last are the left, front, and right poison
	 * sensors
	 */
	double[] inputs;
	/**
	 * The outpputs the ann gives. How much it wants to move left, front, and
	 * right, given as values between 0 and 1. 0 means it doesn't like going
	 * that direction, 1 means it loves going that direction.
	 */
	double[] outputs;
	/**
	 * The internal weights of the flatland network. The way it works is that it
	 * assigns a weight for each input to every output
	 */
	double[] weights;
	/**The type of each neuron in the input layer*/
	NeuronType[] inputNeurons;

	/**The constructor of the flatland ann. */
	public FlatlandAnn() {
		inputs = new double[Params.numberOfFlatlandInputNeurons];
		outputs = new double[Params.numberOfFlatlandOutputNeurons];
		weights = new double[Params.numberOfFlatlandInputNeurons
				* Params.numberOfFlatlandOutputNeurons];
		inputNeurons = new NeuronType[Params.numberOfFlatlandInputNeurons];
	}
	
	
}
