package graphics;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class Plot {
	
	public void showStats(){
		double[] x;
		double[] y;
		Plot2DPanel plot = new Plot2DPanel();
		x = getBestFitness();
		y = getAverageFitness();
		
		plot.addScatterPlot("Best fitness", x);
		plot.addScatterPlot("Average fitness", y);
		
		plot.setAxisLabel(0, "Number of generations");
		plot.setAxisLabel(1, "Fitness");
		
		JFrame frame = new JFrame("Fitness vs Generations");
		frame.setSize(600,600);
		frame.setContentPane(plot);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Plot plot = new Plot();
		plot.showStats();
	}
	
	public double[] getBestFitness(){
		double[] output;
		String input = FlatlandGrid.readTextFromFile("MytextFile.txt");
		String[] pairs = input.split("s");
		output = new double[pairs.length];
		for (int i = 0; i < pairs.length; i++) {
			String[] tempString = pairs[i].split(",");
			output[i] = Double.parseDouble(tempString[0]);
		}
		
		return output;
	}
	
	public double[] getAverageFitness(){
		double[] output;
		String input = FlatlandGrid.readTextFromFile("MytextFile.txt");
		String[] pairs = input.split("s");
		output = new double[pairs.length];
		for (int i = 0; i < pairs.length; i++) {
			String[] tempString = pairs[i].split(",");
			output[i] = Double.parseDouble(tempString[1]);
		}
		
		return output;
	}
}
