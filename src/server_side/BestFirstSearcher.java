package server_side;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class BestFirstSearcher implements Searcher {
	private PriorityQueue<State> openList;
	private int evaluatedNodes;
	
	public BestFirstSearcher() {
		openList = new PriorityQueue<State>();
		evaluatedNodes = 0;
	}
	
	@Override
	public Solution search(Searchable s) {
		openList.add(s.getInitialState());
		HashSet<State> closedSet = new HashSet<State>();
		
		while(openList.size()>0) {
			State n=popOpenList();
			closedSet.add(n);
			
			if(s.isGoalState(n))
				return new Solution(n);
			
			List<State> successors = s.getAllPossibleStates(n);
			
			for (State<BoardCell> state : successors) {
				if (!closedSet.contains(state) && !openList.contains(state)) {
					state.setCameFrom(n);
					state.cost = n.cost + state.state.value;
					openList.add(state);
				} else if (n.cost + state.state.value < state.cost) {
					if (!openList.contains(state)) {
						openList.add(state);
					} else {
						openList.remove(state);
						state.cost = n.cost + state.state.value;
						openList.add(state);
					}
				}
			}
		}
		
		throw new RuntimeException("error! couldnt find path!!!");
	}

	@Override
	public int getNumberOfNodesEvaluate() {
		// TODO Auto-generated method stub
		return evaluatedNodes;
	}
	
	private State popOpenList() {
		evaluatedNodes++;
		return openList.poll();
	}

}
