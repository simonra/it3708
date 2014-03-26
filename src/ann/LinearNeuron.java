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
		//Normalizes the weighted input to be between 0 and 1 assuming the inputs are in the range 0 to 1
		output /= input.length + Params.biasNeuronWeight != 0 ? 1.0 : 0.0;
		return output;
	}
	
	public static double derivative(double[] input, double weight[]){
		if(input.length != weight.length){
			System.out.println("Linear neuron derivative parameters don't match in size. You fucked something up.");
			return -Integer.MAX_VALUE;
		}
		return 1;
	}
}
