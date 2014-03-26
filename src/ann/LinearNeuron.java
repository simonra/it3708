package ann;

import commons.Params;

public class LinearNeuron {
	
	
	public static double  trigger(double[] input, double weight[]){
		if(input.length != weight.length){
			System.out.println("Linear neuron parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		double output = 0;
		for (int i = 0; i < input.length; i++) {
			output += input[i] * weight[i];
		}
		output += Params.biasNeuronWeight;
		return output;
	}
}
