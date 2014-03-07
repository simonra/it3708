package exercise2;

public class Params {
	public enum Problem{
		OneMax, GlobalSurprisingSequences, LocalSurprisingSequences;
	}
	public enum AdultSelectionType{
		FullGenerationalReplacement, OverProductionReplacement, GenerationalMixing;
	}
	public enum ParentSelectionType{
		TournamentSelection, UniformSelection, FitnessProportionateSelection, SigmaScalingSelection;
	}
	
	public static int SIZE_OF_POPULATION = 50;
	public static int NUMBER_OF_PAIRS_THAT_GET_TO_REPRODUCE = 50;
	
	public static double MUTATION_CHANCE = 0.0002;
	public static double CROSSOVER_CHANCE = 0.999;
	public static int CROSSOVER_POINTS = 1;
	
	public static int TOURNAMENT_SELECTION_GROUP_SIZE = 4;
	public static double TOURNAMENT_SELECTION_THRESHOLD = 0.1;
	
	public static int ONE_MAX_BIT_VECTOR_SIZE = 40;
	public static boolean ONE_MAX_RANDOM_BIT_VECTOR = false;
	public static int SYMBOL_SET_SIZE = 4;
	public static int SURPRISING_SEQUENCES_SEQUENCE_SIZE = 11;
	
	public static Problem CURRENT_PROBLEM = Problem.OneMax;
	public static int MAX_NUMBER_OF_GENERATIONS = 10000;
	
	public static AdultSelectionType ADULT_SELECTION_TYPE = AdultSelectionType.OverProductionReplacement;
	public static ParentSelectionType PARENT_SELECTION_TYPE = ParentSelectionType.SigmaScalingSelection;
}
