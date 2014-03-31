package ann;

import java.util.Arrays;

import commons.Params;
import flatland.Board;
import flatland.Direction;

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
	 * assigns a weight for each input to every output. Hence its size is the 
	 * number of input neurons * the number of output neurons
	 */
	double[] weights;
	/** The type of each neuron in the input layer */
	NeuronType[] outputNeurons;

	/** The constructor of the flatland ann. */
	public FlatlandAnn() {
		inputs = new double[Params.numberOfFlatlandInputNeurons];
		outputs = new double[Params.numberOfFlatlandOutputNeurons];
		weights = new double[Params.numberOfFlatlandInputNeurons
				* Params.numberOfFlatlandOutputNeurons];
		outputNeurons = new NeuronType[Params.numberOfFlatlandOutputNeurons];
	}

	/** Constructor that sets weithts and initiates neuron types to use */
	public FlatlandAnn(double[] weights) {
		inputs = new double[Params.numberOfFlatlandInputNeurons];
		outputs = new double[Params.numberOfFlatlandOutputNeurons];
		this.weights = new double[Params.numberOfFlatlandInputNeurons
				* Params.numberOfFlatlandOutputNeurons];
		for (int i = 0; i < weights.length; i++) {
			this.weights[i] = weights[i];
		}
		outputNeurons = new NeuronType[Params.numberOfFlatlandOutputNeurons];
		for (int i = 0; i < outputNeurons.length; i++) {
			outputNeurons[i] = Params.neuronTypeToUse;
		}
	}

	/** Get the direction the ANN recomends the agent to move */
	public Direction chooseDirection(Board board) {
		board.agent.evaluateNextMove(board);
		inputs[0] = board.agent.foodLeft() ? 1.0 : 0.0;
		inputs[1] = board.agent.foodAhead() ? 1.0 : 0.0;
		inputs[2] = board.agent.foodRight() ? 1.0 : 0.0;
		inputs[3] = board.agent.poisonLeft() ? 1.0 : 0.0;
		inputs[4] = board.agent.poisonAhead() ? 1.0 : 0.0;
		inputs[5] = board.agent.poisonRight() ? 1.0 : 0.0;
		
		switch (Params.neuronTypeToUse) {
		case LINEAR:
			generateLinearNeuronOutput();
			break;
		case RAMP:
			generateRampNeuronOutput();
			break;
		case STEP:
			generateStepNeuronOutput();
			break;
		case SIGMOID:
			generateSigmoidNeuronOutput();
			break;
		case HYPERBOLIC_TANGENT:
			generateHyperbolicTangentOutput();
			break;
		default:
			System.out.println("No recognized neuron type entered to flatlandAnn.chooseDirection(board board)!");
			break;
		}
		Direction bestDirection;
		if( outputs[0] > outputs[1] && outputs[0] > outputs[2] ){
			bestDirection = Direction.LEFT;
		}else if( outputs[1] > outputs[0] && outputs[1] > outputs[2] ){
			bestDirection = Direction.AHEAD;
		}else if( outputs[2] > outputs[0] && outputs[2] > outputs[1] ){
			bestDirection = Direction.RIGHT;
		}else{
			//no best direction was found so choose a random one
			double random = Math.random();
			if(random < 1.0/3.0) bestDirection = Direction.LEFT;
			else if(random < 2.0/3.0) bestDirection = Direction.AHEAD;
			else bestDirection = Direction.RIGHT;
		}
		
		return bestDirection;
	}

	void generateLinearNeuronOutput() {
		for (int i = 0; i < this.outputs.length; i++) {
			this.outputs[i] = LinearNeuron.trigger(
					this.inputs,
					Arrays.copyOfRange(this.weights, i
							* Params.numberOfFlatlandInputNeurons, i
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons));
		}
	}

	void generateRampNeuronOutput() {
		for (int i = 0; i < this.outputs.length; i++) {
			this.outputs[i] = RampNeuron.trigger(
					this.inputs,
					Arrays.copyOfRange(this.weights, i
							* Params.numberOfFlatlandInputNeurons, i
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons));
		}
	}

	void generateStepNeuronOutput() {
		for (int i = 0; i < this.outputs.length; i++) {
			this.outputs[i] = StepNeuron.trigger(
					this.inputs,
					Arrays.copyOfRange(this.weights, i
							* Params.numberOfFlatlandInputNeurons, i
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons));
		}
	}

	void generateSigmoidNeuronOutput() {
		for (int i = 0; i < this.outputs.length; i++) {
			this.outputs[i] = SigmoidNeuron.trigger(
					this.inputs,
					Arrays.copyOfRange(this.weights, i
							* Params.numberOfFlatlandInputNeurons, i
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons));
		}
	}

	void generateHyperbolicTangentOutput() {
		for (int i = 0; i < this.outputs.length; i++) {
			this.outputs[i] = HyperbolicTangentNeuron.trigger(
					this.inputs,
					Arrays.copyOfRange(this.weights, i
							* Params.numberOfFlatlandInputNeurons, i
							* Params.numberOfFlatlandInputNeurons
							+ Params.numberOfFlatlandInputNeurons));
		}
	}
}
