package server_side;

public interface Searcher {
	public Solution search(Searchable s);
	public int getNumberOfNodesEvaluate();

}
