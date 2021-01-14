package server_side;

public class BoardCell {
	public int row;
	public int col;
	public Double value;
	
	public BoardCell(int row, int col, Double value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	
	public boolean equals(BoardCell obj) {
		return row == obj.row && col == obj.col;
	}
}
