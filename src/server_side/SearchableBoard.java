package server_side;

import java.util.ArrayList;
import java.util.List;

public class SearchableBoard implements Searchable<BoardCell> {

	List<List<State<BoardCell>>> board;
	State<BoardCell> initialState;
	State<BoardCell> goalState;
	
	public SearchableBoard(List<List<Double>> scoresBoard, BoardCell initialLocation, BoardCell goalLocation) {
		
		initialState = new State<BoardCell>(initialLocation);
		initialState.cost = initialState.state.value;
		goalState = new State<BoardCell>(goalLocation);
		this.board = new ArrayList<List<State<BoardCell>>>();
		for (int row= 0; row < scoresBoard.size(); row++) {
			List<State<BoardCell>> curr = new ArrayList<State<BoardCell>>();
			for (int col = 0; col < scoresBoard.get(0).size(); col++) {
				curr.add(new State<BoardCell>(new BoardCell(row, col, scoresBoard.get(row).get(col))));
			}
			board.add(curr);
		}
		
		board.get(initialState.state.row).get(initialState.state.col).cost = initialState.state.value;
		
	}
	
	@Override
	public State<BoardCell> getInitialState() {
		// TODO Auto-generated method stub
		return initialState;
	}

	@Override
	public boolean isGoalState(State<BoardCell> s) {
		// TODO Auto-generated method stub`
		return goalState.state.equals(s.state);
	}

	@Override
	public List<State<BoardCell>> getAllPossibleStates(State<BoardCell> s) {
		List<State<BoardCell>> possibleMoveStates = new ArrayList<State<BoardCell>>();
		
		// go up
		if (s.state.row > 0)
		{
			possibleMoveStates.add(board.get(s.state.row - 1).get(s.state.col));
		}
		// go down
		if (s.state.row < board.size() - 1)
		{
			possibleMoveStates.add(board.get(s.state.row + 1).get(s.state.col));
		}
		// go left
		if (s.state.col > 0)
		{
			possibleMoveStates.add(board.get(s.state.row).get(s.state.col - 1));
		}
		// go right
		if (s.state.col < board.get(s.state.row).size() - 1)
		{
			possibleMoveStates.add(board.get(s.state.row).get(s.state.col + 1));
		}	
		if (s.cameFrom != null)
			possibleMoveStates.remove(s.cameFrom);
		return possibleMoveStates;
	}

}
