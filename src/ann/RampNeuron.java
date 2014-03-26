package ann;

import commons.Params;

public class RampNeuron {
	
	
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
		if(weightedInput < Params.rampNeuronLowerThreshold){
			output = 0.0;
		}else if(weightedInput < Params.rampNeuronUpperThreshold){
			output = weightedInput;
		}else{
			output = 1;
		}
		return output;
	}
	
	
}
