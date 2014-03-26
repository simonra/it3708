package ann;

import commons.Params;

public class StepNeuron {
	
	public static double trigger(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Step function neuron parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double weightedInput = 0;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		//Normalizes the weighted input to be between 0 and 1 assuming the inputs are in the range 0 to 1
		weightedInput /= input.length + Params.biasNeuronWeight != 0 ? 1.0 : 0.0;
		return weightedInput > Params.stepFunctionNeuronThreshold ? 1.0 : 0.0;
	}
	
	public static double derivative(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Step function neuron derivative parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double weightedInput = 0;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		//Normalizes the weighted input to be between 0 and 1 assuming the inputs are in the range 0 to 1
		weightedInput /= input.length + Params.biasNeuronWeight != 0 ? 1.0 : 0.0;
		//The derivative of the heaviside function, the dirac delta function
		return weightedInput == Params.stepFunctionNeuronThreshold ? 1.0 : 0.0;
	}
}
