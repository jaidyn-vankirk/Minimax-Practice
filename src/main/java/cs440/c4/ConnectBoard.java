package cs440.c4;

public class ConnectBoard implements GameBoard{
	int[][] board;
	int depth;
	
	/**
	 * Takes in a certain number of rows and columns to make a board with those dimensions.
	 * Initializes all of them to be available.
	 * @param row int value associated with the x
	 * @param column int value associated with the y
	 */
	public ConnectBoard(int row, int column) {
		board = new int[row][column];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = AVAIL;
			}
		}
		
	}
	
	public ConnectBoard(int[][] board, int depth) {
		this.board = board;
		this.depth = depth;
	}

	/**
	 * Checks to make sure the current row is not full then looks for the next available slot to enter
	 * the disk.
	 */
	@Override
	public int addDisk(int c, int player) {
		int row = 0;
		if(isColumnFull(c) == false) {
			while(board[row][c] != 0) {	//Goes until there is available slot.
				row++;
			}
			board[row][c] = player;
		}
		return row;
	}

	/**
	 * Sees if there are any 4 connected across the board. If so, returns the player that has the
	 * 4 connected.
	 */
	@Override
	public int connected() {
		for(int i = 0; i < board.length; i++) { 
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] != 0) {
					if(i+3 < board.length) {
						//Checks horizontal connections.
						if(board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]) {
							return board[i][j];
						}
						//Tests right and up connections.
						if(j+3 < board[i].length) {
							if(board[i][j] == board[i+1][j+1] && board[i][j] == board[i+2][j+2] && board[i][j] == board[i+3][j+3]) {
								return board[i][j];
							}
						}
					}
					//Tests left and down connections.
					if(i-3 >= 0) {
						if(j+3 < board[i].length) {
							if(board[i][j] == board[i-1][j+1] && board[i][j] == board[i-2][j+2] && board[i][j] == board[i-3][j+3]) {
								return board[i][j];
							}
						}
					}
					//Tests vertical connections.
					if(j+3 < board[i].length) {
						if(board[i][j] == board[i][j+1] && board[i][j] == board[i][j+2] && board[i][j] == board[i][j+3]) {
							return board[i][j];
						}
					}
				}
			}
		}
		return 0;
	}
	/*
	 * First checks to see if there are any 4 in a row then checks to see if all the slots are filled.
	 */
	@Override
	public boolean gameOver() {
		if(connected() != 0) {
			return true;
		}
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				if(board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * Returns the board in its current state.
	 */
	@Override
	public int[][] getBoard() {
		return board;
	}

	/**
	 * Looks at the top of the specified column to see if it is empty.
	 */
	@Override
	public boolean isColumnFull(int c) {
		if(board[board.length-1][c] == 0) {
			return false;
		}
		return true;
	}

}
