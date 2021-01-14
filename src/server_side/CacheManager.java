package server_side;

public interface CacheManager<Problem, Solution> {
	boolean isSolutionCached(Problem problem);
	Solution getCachedSolution(Problem problem);
	void saveSolution(Problem problem, Solution solution);
}
