package hw3;

public class Board {
	private char[][] connectBoard;
	private int spacesCount;
    private char firstPlayer;
    private char secondPlayer;
    
	// TODO
	// Add whatever private fields you need here.
	// Remember, only variables of type int, char, boolean, and 1D and 2D arrays
	// of these types are allowed.
	//
	// As always, you may also add private helper methods to the class.  That will
	// likely be very useful on this assignment.

	/**
	 * Constructs a new empty connect 4 game board with player X being the first player
	 * and player 'O' being the second player.
	 */
	public Board() {
		char newEmpty = '0';
		connectBoard = new char[6][7];
        for (int p = 0; p < connectBoard.length; p++) {
            for (int y = 0; y < connectBoard[p].length; y++) {
                connectBoard[p][y] = newEmpty;
            }
        }
        firstPlayer = 'X';
        secondPlayer = 'O';
    }

	/**
	 * Gets the current player (either 'X' or 'O')
	 * 
	 * @return the current player
	 */
	public char currentPlayer() {
		int spacesCount = 0;
        int spacesCount1 = 0;
        for (int m = 0; m < 6; m++) {
            for (int t = 0; t < 7; t++) {
                if (connectBoard[m][t] == firstPlayer) {
                    spacesCount= spacesCount + 1;
                } else if (connectBoard[m][t] == secondPlayer) {
                    spacesCount1= spacesCount1 + 1;
                }
            }
        }
        if (spacesCount == spacesCount1) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }
	
	

	/**
	 * The current player makes a move in a given column if it is a valid move.
	 * Throws an exception if the game is already over.
	 * 
	 * @param column the column in which to make a move.  For the move to be valid,
	 * the column value must an {@code int} between 1 and 7 inclusive, and
	 * there must have been fewer than 6 moves already made in the given column.
	 * @return {@code true} if the move is valid and false if it is not valid.
	 * @throws RuntimeException if the game is already over.
	 */
	public boolean play(int column) {
		if (gameStatus() == 'U' && spacesCount == 6*7)
            throw new RuntimeException("Game is already over.");
		if (gameStatus() != 'U')
            throw new RuntimeException("Game is already over.");
        if (column < 1)
            return false;
        if (column > 7)
            return false;
        for (int w = 5; w >= 0; w--) {
            if (connectBoard[w][column - 1] == '0') {
                connectBoard[w][column - 1] = currentPlayer();
                spacesCount= spacesCount + 1;
                return true;
            }
        }
        return false;
    }
			
		
	
	

	/**
	 * Determine the status of the game.
	 * 
	 * @return {@code 'X'} or {@code 'O'} if either player has won, {@code 'D'} if
	 * the game is a draw, and {@code 'U'} if the game is still undecided.
	 */
	public char gameStatus() {
		char victory = 'U';
	    int checkRows = connectBoard.length;
	    int checkColumns = connectBoard[0].length;
	    
	    for (int b = 0; b < checkRows - 3; b++) {
	        for (int u = 0; u < checkColumns; u++) {
	            if (connectBoard[b][u] == 'X' && connectBoard[b + 1][u] == 'X' && connectBoard[b + 2][u] == 'X' && connectBoard[b + 3][u] == 'X')
	                victory = 'X';
	            if (connectBoard[b][u] == 'O' && connectBoard[b + 1][u] == 'O' && connectBoard[b + 2][u] == 'O' && connectBoard[b + 3][u] == 'O')
	                victory = 'O';
	        }
	    }
	    
	    for (int r = 3; r < checkRows; r++) {
	        for (int g = 0; g < checkColumns - 3; g++) {
	            if (connectBoard[r][g] == 'X' && connectBoard[r - 1][g + 1] == 'X' && connectBoard[r - 2][g + 2] == 'X' && connectBoard[r - 3][g + 3] == 'X')
	                victory = 'X';
	            if (connectBoard[r][g] == 'O' && connectBoard[r - 1][g + 1] == 'O' && connectBoard[r - 2][g + 2] == 'O' && connectBoard[r - 3][g + 3] == 'O')
	                victory = 'O';
	        }
	    }
	    
	    for (int a = 0; a < checkRows - 3; a++) {
	        for (int c = 0; c < checkColumns - 3; c++) {
	            if (connectBoard[a][c] == 'X' && connectBoard[a + 1][c + 1] == 'X' && connectBoard[a + 2][c + 2] == 'X' && connectBoard[a + 3][c + 3] == 'X')
	                victory = 'X';
	            if (connectBoard[a][c] == 'O' && connectBoard[a + 1][c + 1] == 'O' && connectBoard[a + 2][c + 2] == 'O' && connectBoard[a + 3][c + 3] == 'O')
	                victory = 'O';
	        }
	    }

	    for (int v = 0; v < checkRows; v++) {
	        for (int z = 0; z < checkColumns - 3; z++) {
	            if (connectBoard[v][z] == 'X' && connectBoard[v][z + 1] == 'X' && connectBoard[v][z + 2] == 'X' && connectBoard[v][z + 3] == 'X')
	                victory = 'X';
	            if (connectBoard[v][z] == 'O' && connectBoard[v][z + 1] == 'O' && connectBoard[v][z + 2] == 'O' && connectBoard[v][z + 3] == 'O')
	                victory = 'O';
	        }
	    }
	
	    

	    if (victory != 'U')
	        return victory;
	    if (spacesCount == checkRows * checkColumns)
	        return 'D';
	    
	    return 'U'; 
		    }

	

	/**
	 * Construct a string that depicts the sate of the game.
	 * (See the writeup for what that string should look like.)
	 * 
	 * @return a string depicting the game board
	 */
	public String toString() {
		String output = "";
	    for (int h = 0; h < connectBoard.length; h++) {
	        for (int g = 0; g < connectBoard[h].length; g++) {
	            if (connectBoard[h][g] == '0') {
	                output= output + "0 ";
	            } else {
	                output= output + connectBoard[h][g] + " ";
	            }
	        }
	        output= output + "\n";
	    }
	    
	    output= output + "---------------\n"; 
	    output= output + "1 2 3 4 5 6 7 \n";
	    return output;
	    }
		
		
	}

