package ann;

import commons.Params;

public class HyperbolicTangentNeuron {
	public static double trigger(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Hyperbolic tangent neuron parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double output = 0;
		double weightedInput = 0;
		double positiveExponential;
		double negativeExponential;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		//Normalizes the weighted input to be between 0 and 1 assuming the inputs are in the range 0 to 1
		weightedInput /= input.length + Params.biasNeuronWeight != 0 ? 1.0 : 0.0;
		positiveExponential = Math.exp(weightedInput);
		negativeExponential = Math.exp(-weightedInput);
		output = ( positiveExponential - negativeExponential ) / ( positiveExponential + negativeExponential );
		return output;
	}
	
	public static double derivative(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Hyperbolic tangent neuron derivative parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double output = 0;
		double weightedInput = 0;
		double positiveExponential;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		//Normalizes the weighted input to be between 0 and 1 assuming the inputs are in the range 0 to 1
		weightedInput /= input.length + Params.biasNeuronWeight != 0 ? 1.0 : 0.0;
		positiveExponential = Math.exp(weightedInput);
		output = 1.0 - positiveExponential * positiveExponential;
		return output;
	}
}
