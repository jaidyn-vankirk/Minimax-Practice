package cs440.c4;

public class MiniMaxSearchAgent implements Agent{
	ConnectBoard gb = null;
	int nodes = 0;
	


	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {
		int[][] currentBoard = board.getBoard();
		int[][] newBoard = new int[currentBoard.length][currentBoard[0].length];
		for(int i = 0; i < newBoard.length; i++) {
			for(int j = 0; j < newBoard[i].length; j++) {
				newBoard[i][j] = currentBoard[i][j];
			}
		}
		gb = new ConnectBoard(newBoard, 0);
	}
	
	/**
	 * Creates a deep copy of the game board.
	 * @param board
	 * @return
	 * @throws Exception
	 */
	private ConnectBoard initializeWithConnectBoard(ConnectBoard c) throws Exception {
		int[][] currentBoard = c.getBoard();
		int[][] newBoard = new int[currentBoard.length][currentBoard[0].length];
		for(int i = 0; i < newBoard.length; i++) {
			for(int j = 0; j < newBoard[i].length; j++) {
				newBoard[i][j] = currentBoard[i][j];
			}
		}
		return new ConnectBoard(newBoard, (c.depth) + 1);
	}

	/**
	 * Takes in the current state of the board and returns which column the agent chooses to
	 * put it's next disk in, which is the second thing in the array.
	 * 
	 * Transports info through
	 * @param board
	 * @return
	 */
	@Override
	public int nextAction() throws Exception {
		double begin = System.nanoTime();
		if (gb.gameOver()) {
			return -1;
		}
		int bestAct = -1;	//Holds the action we should take
		int bestU = -100;		//Holds the utility of the best action
		int[][] state = gb.getBoard();	//Gets state.
		for(int i = 0; i < state[0].length; i++) {	//Goes through all of the columns
			int ans = 0; //Holds utility function of the current column
			if(!gb.isColumnFull(i)) {
				gb.depth = 0;
				ConnectBoard give = initializeWithConnectBoard(gb);	//Copy of the original board
				nodes ++;
				give.addDisk(i, GameBoard.AGENT);	//Adds a disk to the copy
				ans = minValue(give);	//Calls min to get a utility value.
				
				
				if(ans > bestU) {	//Compares the utility, if bigger it makes the current column the best action.
					bestAct = i;
					bestU = ans;
				}
			}
		}
		double end = System.nanoTime();

		return(bestAct);
	}
	
	private int maxValue(ConnectBoard c) throws Exception{
		//If game is over then returns the utility value of the end state.
		if(c.gameOver()) {
			int ans = utility(c);
			
			return ans;
		}
		//Else goes through all of the possible children nodes.
		int max = -100;
		int[][] state = c.getBoard();
		for(int i = 0; i < state[0].length; i++) {	//Goes through all of the columns
			if(!c.isColumnFull(i)) {	//If the column is full we skip
				int ans = 0;
				ConnectBoard give = initializeWithConnectBoard(c);	//Creates a copy of the board called give
				nodes ++;
				give.addDisk(i, GameBoard.AGENT);	//Adds a disk to the copy.
				dump(give);
				
				ans = minValue(give);
				if(ans > max) {	//If the current is bigger than the max, then we switch the values.
					max = ans;
				}
			}
		}
		return max;
	}
	
	
	private int minValue(ConnectBoard c) throws Exception{
		//If game is over then returns the utility value of the end state.
		if(c.gameOver()) {
			int ans = utility(c);
			return ans;
		}
		int min = 100;
		int[][] state = c.getBoard();
		//Else goes through all possible children states.
		for(int i = 0; i < state[0].length; i++) {	//Goes through all of the columns
			if(!c.isColumnFull(i)) {
				int ans = 0;
				ConnectBoard give = initializeWithConnectBoard(c);	//Creates copy
				nodes ++;
				give.addDisk(i, GameBoard.USER);	//Puts a disk in the copy
				
				
				dump(give);
				
				ans = maxValue(give);
				if(ans < min) {
					min = ans;
				}
			}
		}
		return min;
	}

	private void dump(ConnectBoard give) {
		if(give.depth > 0) {
			return;
		}
		System.out.println("Depth:" + give.depth);
		for(int k = 0; k < give.board.length; k++) {
			for(int l = 0; l < give.board[k].length; l++) {
				System.out.print(String.format("%2d",give.board[k][l]));
			}
			System.out.println();
		}
		System.out.println();
	}
	
	private int utility(ConnectBoard c) {
		
		return c.connected() * -1;
		
	}
}
