package ann;

import commons.Params;

public class SigmoidNeuron {
	
	public static double trigger(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Sigmoid neuron parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double output = 0;
		double weightedInput = 0;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		output = 1.0 / (1.0 + Math.exp(- Params.sigmoidScalingConstant * weightedInput ) );
		return output;
	}
	
	public static double derivative(double[] input, double[] weight){
		if(input.length != weight.length){
			System.out.println("Sigmoid neuron derivative parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double output = 0;
		double weightedInput = 0;
		for (int i = 0; i < input.length; i++) {
			weightedInput += input[i] * weight[i];
		}
		weightedInput += Params.biasNeuronWeight;
		output = 1.0 / (1.0 + Math.exp(- Params.sigmoidScalingConstant * weightedInput ) );
		output = output * ( 1 - output );
		return output;
	}
}
