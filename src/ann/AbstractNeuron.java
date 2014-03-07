package ann;

import java.util.ArrayList;

public class AbstractNeuron {
	NeuronType typetypeOfNeuron;
	double activationThreshold;
	
	public AbstractNeuron(NeuronType typeOfNeuron, double threshold){
		this.typetypeOfNeuron = typeOfNeuron;
		this.activationThreshold = threshold;
	}
	
	public double getOutput(ArrayList<Double> input, ArrayList<Double> weights){
		double output = 0.0;
		double weightedInput = 0.0;
		for (int i = 0; i < input.size(); i++) {
			weightedInput = input.get(i) + weights.get(i);
		}
		if(weightedInput >= activationThreshold){
			output = 1.0/(1.0 + (Math.expm1(-weightedInput) + 1.0) );
		}
		return output;
	}
}