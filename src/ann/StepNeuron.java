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
		return weightedInput > Params.stepFunctionNeuronThreshold ? 1.0 : 0.0;
	}
}
