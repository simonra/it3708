package ann;

import java.util.ArrayList;

public abstract class AbstractLayer {
	ArrayList<Double> inputToLayer;
	ArrayList<Double> outputFromLayer;
	ArrayList<Double> weights;
	ArrayList<AbstractNeuron> neurons;
	int numberOfNeuronsInLayer;
	LayerType typeOfLayer;
	
	public AbstractLayer(int numberOfNeuronsInLayer, LayerType typeOfLayer){
		inputToLayer = new ArrayList<Double>();
		outputFromLayer = new ArrayList<Double>();
		weights = new ArrayList<Double>();
		neurons = new ArrayList<AbstractNeuron>();
		this.numberOfNeuronsInLayer = numberOfNeuronsInLayer;
		this.typeOfLayer = typeOfLayer;
	}
	
	public void initializeRandomWeights(){
		for (Double input : inputToLayer) {
			for (AbstractNeuron neuron : neurons) {
				weights.add(Math.random());
			}
		}
	}
	
	public void setWeights(ArrayList<Double> newWeights){
		weights.clear();
		for (Double weight : newWeights) {
			weights.add(0.0 + weight);
		}
	}
	
	public ArrayList<Double> getWeights(){
		ArrayList<Double> copyOfWeights = new ArrayList<Double>();
		for (Double weight : weights) {
			copyOfWeights.add(0.0 + weight);
		}
		return copyOfWeights;
	}
	
	public ArrayList<Double> getOutput(){
		return this.outputFromLayer;
	}
}
