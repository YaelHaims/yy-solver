package server_side;

import java.util.Comparator;

public class State<T> implements Comparator<State<T>>, Comparable<State<T>> {
	public T state;
	public double cost;
	public State<T> cameFrom;
	
	public State(T state) {
		this.state = state;
		this.cost = Double.MAX_VALUE;
		this.cameFrom = null;
	}
	
	@Override
	public boolean equals(Object s) {
		if (s == null) {
			return false;
		}
		
		State<T> s2 = (State<T>)s;
		
		return state.equals(s2.state);
	}
	
	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	

	@Override
	public int compareTo(State<T> o) {
		return this.compare(this, o);
	}

	@Override
	public int compare(State<T> o1, State<T> o2) {
		if (o1.cost - o2.cost < 0) {
			return -1;
		}
		if (o1.cost - o2.cost == 0) {
			return 0;
		}
		return 1;
	}
}
