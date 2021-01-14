package server_side;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	private State<BoardCell> goalState;
	
	public Solution(State<BoardCell> state) {
		this.goalState = state;
	}
	
	@Override
	public String toString() {
		State<BoardCell> currState = goalState;
		String solution = "";
		String direction;
		direction = this.getDirection(currState, currState.cameFrom);
		solution = direction;
		currState = currState.cameFrom;
		
		while(currState.cameFrom != null) {
			direction = this.getDirection(currState, currState.cameFrom);
			solution = direction + "," + solution;
			currState = currState.cameFrom;
		}
		
		
		
		return solution;
	}
	
	private String getDirection(State<BoardCell> curr, State<BoardCell> cameFrom)
	{
		int rowDiff = curr.state.row - cameFrom.state.row;
		int colDiff = curr.state.col - cameFrom.state.col;
		
		if (rowDiff == -1) {
			return "Up";
		}
		
		if (rowDiff == 1) {
			return "Down";
		}
		
		if (colDiff == -1) {
			return "Left";
		}
		
		return "Right";
	}
}
