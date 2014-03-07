package exercise2;

public class OneMaxHeuristic implements EAHeuristic {
	
	static boolean[] targetBooleanString;
	
	@Override
	public double getFitness(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getGenotypeFitness(Genotype genotype) {
		OneMaxGenotype omGenotype = (OneMaxGenotype) genotype;

//		if(Params.ONE_MAX_RANDOM_BIT_VECTOR && targetBooleanString == null){
//			targetBooleanString = new boolean[Params.ONE_MAX_BIT_VECTOR_SIZE];
//			for (int i = 0; i < Params.ONE_MAX_BIT_VECTOR_SIZE; i++) {
//				if(Math.random()< 0.5)
//					targetBooleanString[i] = true;
//				else
//					targetBooleanString[i] = false;
//			}
//		}
		
		int counter = 0;
		
//		if(Params.ONE_MAX_RANDOM_BIT_VECTOR){
//			for (int i = 0; i < Params.ONE_MAX_BIT_VECTOR_SIZE; i++) {
//				counter += omGenotype.phenotype[i] ? 1 : 0;
//				if(omGenotype.phenotype[i] == targetBooleanString[i])
//					counter++;
//			}
//			return (double) counter / (double) Params.ONE_MAX_BIT_VECTOR_SIZE;
//		}
		for (int i = 0; i < Params.ONE_MAX_BIT_VECTOR_SIZE; i++) {
			counter += omGenotype.phenotype[i] ? 1 : 0;
		}
		return (double) counter / (double) Params.ONE_MAX_BIT_VECTOR_SIZE;
	}

	@Override
	public double getPopulationFitness(Population population) {
		
		return 0;
	}

}
