package exercise2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import exercise2.Params.Problem;

public class SurprisingSequencesHeuristic implements EAHeuristic {

	@Override
	public double getFitness(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//return 1 if and only if 
	
	@Override
	public double getGenotypeFitness(Genotype genotype) {
		SurprisingSequencesGenotype ssGenotype = (SurprisingSequencesGenotype) genotype;
		
		ArrayList<String> pairsAndDistances = new ArrayList<String>();
		String pairString;
		int errorCounter = 0;
		
		if (Params.CURRENT_PROBLEM == Problem.GlobalSurprisingSequences) {
			for (int i = 0; i < ssGenotype.phenotype.length - 1; i++) {
				for (int j = i + 1; j < ssGenotype.phenotype.length; j++) {
					pairString = "" + ssGenotype.phenotype[i] + "" + ssGenotype.phenotype[j] + "" + (j - i - 1);
					for (String string : pairsAndDistances) {
						if (pairString.equals(string)) {
							errorCounter++;							
						}
					}
					pairsAndDistances.add(pairString);
				}
			}
			
		}else if(Params.CURRENT_PROBLEM == Problem.LocalSurprisingSequences){
			for (int i = 0; i < ssGenotype.phenotype.length - 1; i++) {
				pairString = "" + ssGenotype.phenotype[i] + "" + ssGenotype.phenotype[i + 1];
				for (String string : pairsAndDistances) {
					if (pairString.equals(string)) {
						errorCounter++;							
					}
				}
				pairsAndDistances.add(pairString);
			}
		}
		
		return 1.0 / ((double) (1 + errorCounter));
	}

	@Override
	public double getPopulationFitness(Population population) {
		// TODO Auto-generated method stub
		return 0;
	}

}
