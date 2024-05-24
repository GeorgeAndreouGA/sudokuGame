
public class Board {/*
					 * Private Fields:
					 * 
					 * N: An integer representing the size of the Sudoku board (default is 9).
					 * tableau: A 2D array of integers representing the Sudoku board.
					 */
	private int N;
	private int[][] tableau;

	public Board(int N) {/*
							 * Constructors:
							 * 
							 * Board(int N): Initializes the Sudoku board with the specified size.
							 */
		this.N = N;
		this.tableau = new int[N][N];
	}

	public Board() {/*
					 * Default constructor that initializes the board with a default size of 9.
					 * Methods:
					 */
		this(9);
	}

	public boolean readBoard(
			String filename) {/*
								 * Input:
								 * 
								 * filename: A string representing the name of the file from which the Sudoku
								 * board is to be read. Processing:
								 * 
								 * The method uses an input class (In) to read data from the specified file. It
								 * counts the total number of integers in the file, incrementing a counter for
								 * each integer read. After counting, it closes the input stream. Validation:
								 * 
								 * It checks if the total number of integers read is consistent with the
								 * expected size of the Sudoku board. If not, it prints an error message based
								 * on the size of the board (4x4 or 9x9). Reading Board:
								 * 
								 * It reopens the input stream for reading the actual Sudoku board values. It
								 * uses nested loops to iterate over the rows and columns of the board, reading
								 * each integer from the file and populating the tableau array. Final
								 * Validation:
								 * 
								 * After populating the tableau array, it calls the checkValidity() method to
								 * perform additional validation on the read board. Output:
								 * 
								 * If any errors are detected during the process (incorrect number of integers
								 * or validity issues), appropriate error messages are printed. If the board is
								 * valid, the method returns false to indicate successful board reading and
								 * validation. Note: The specific implementation details and error messages are
								 * not provided in this description, as requested. The method ensures that the
								 * board is read correctly, the number of integers is consistent with the
								 * expected size, and the board is valid according to the rules of Sudoku.
								 */

		In give = new In(filename);
		int plithosArithmonArxioy = 0;
		while (!give.isEmpty()) {
			int number = give.readInt();
			plithosArithmonArxioy++;
		}
		give.close();
		if (N != Math.sqrt(plithosArithmonArxioy)) {
			if (N == 4)
				System.out.println("Error: Illegal number in input file!");
			else if (N == 9)
				System.out.println("Missing values from file!");
			return true;
		}
		give = new In(filename);
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				tableau[i][j] = give.readInt();
		give.close();
		if (checkValidity())
			return true;
		return false;
	}

	private boolean checkValidity() {/*
										 * Row and Column Check:
										 * 
										 * The method iterates through each cell in the Sudoku board. For each non-empty
										 * cell, it checks if there are duplicate values in the same row and column. If
										 * it finds any duplicate values, it indicates that the Sudoku board is not
										 * valid. Subgrid Check:
										 * 
										 * The method checks each subgrid (also known as a box or region) within the
										 * Sudoku board. For a 4x4 board, there are four subgrids, and for a 9x9 board,
										 * there are nine subgrids. It ensures that there are no duplicate values within
										 * each subgrid. If it finds any duplicate values in a subgrid, it indicates
										 * that the Sudoku board is not valid. Overall Check:
										 * 
										 * The method combines the row, column, and subgrid checks to determine the
										 * overall validity of the Sudoku board. If any of the checks indicate that the
										 * board is not valid (i.e., there are duplicate values), the method returns
										 * true. If all checks pass, the method returns false, indicating that the
										 * Sudoku board is valid. Error Messages:
										 * 
										 * If the method finds any errors, it may print appropriate error messages
										 * indicating which specific rules are violated. For example, it might print
										 * messages like "Error: Duplicate values in the same row,"
										 * "Error: Duplicate values in the same column," or
										 * "Error: Duplicate values in the same subgrid." Special Considerations:
										 * 
										 * The method may have different rules for checking validity based on the size
										 * of the Sudoku board (4x4 or 9x9), as indicated by the use of different
										 * methods for checking each type. In summary, the checkValidity() method
										 * performs a comprehensive check on the Sudoku board, ensuring that it adheres
										 * to the rules of the game. It considers rows, columns, and subgrids to
										 * identify any duplicate values that would make the board invalid.
										 * 
										 * 
										 * 
										 * 
										 */
		int plithSeras = 0;
		int plithStili = 0;
		int plith = 0;
		if (elexosSerasKaiStilisGiaOloTonPinaka(tableau, plithSeras, plithStili)) {
			System.out.println("Error: This is not a valid sudoku!rule not met! ");
			return true;
		}
		if (tableau.length == 4) {
			if (elexosSerasKaiStilisGiaPanoAristera4(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaPanoDexia4(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaKatoAristera4(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			}
		} // if
		else if (tableau.length == 9) {
			if (elexosSerasKaiStilisGiaPanoAristera9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaPanoMesi9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaPanoDexia9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaMesiAristera9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaMesiMesi9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaMesiDexia9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaKatoAristera9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaKatoMesi9(tableau, plith)) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				return true;
			} else if (elexosSerasKaiStilisGiaKatoDexia9(tableau, plith)) {
				return true;
			}
		}
		System.out.println("The sudoku of the file is correct! ");
		return false;
	}

	public boolean elexosSerasKaiStilisGiaOloTonPinaka(int[][] tableau, int plithSeras, int plithStili) {

		for (int i = 0; i < tableau.length; i++) {
			for (int j = 0; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plithSeras = 0;
					plithStili = 0;
					for (int s = 0; s < tableau.length; s++)
						if (tableau[i][s] == tableau[i][j])
							plithSeras++;
					if (plithSeras >= 2) {
						return true;
					}
					for (int k = 0; k < tableau.length; k++)
						if (tableau[k][j] == tableau[i][j])
							plithStili++;

					if (plithStili >= 2) {
						return true;
					}
				}
			}
		}
		return false;
	}

//gia length pinaka 4
	public boolean elexosSerasKaiStilisGiaPanoAristera4(int[][] tableau, int plith) {
		for (int i = 0; i < Math.sqrt(tableau.length); i++) {
			for (int j = 0; j < Math.sqrt(tableau[i].length); j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 0; k < Math.sqrt(tableau.length); k++)
						for (int l = 0; l < Math.sqrt(tableau[k].length); l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}

		return false;
	}

	public boolean elexosSerasKaiStilisGiaPanoDexia4(int[][] tableau, int plith) {
		for (int i = 0; i < Math.sqrt(tableau.length); i++) {
			for (int j = 2; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 0; k < Math.sqrt(tableau.length); k++)
						for (int l = 2; l < tableau[k].length; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public static boolean elexosSerasKaiStilisGiaKatoAristera4(int[][] tableau, int plith) {
		for (int i = 2; i < tableau.length; i++) {
			for (int j = 0; j < Math.sqrt(tableau[i].length); j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 2; k < tableau.length; k++)
						for (int l = 0; l < Math.sqrt(tableau[k].length); l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaKatoDexia4(int[][] tableau, int plith) {
		for (int i = 2; i < tableau.length; i++) {
			for (int j = 2; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 2; k < tableau.length; k++)
						for (int l = 2; l < tableau[k].length; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

//gia length pinaka 9
	public boolean elexosSerasKaiStilisGiaPanoAristera9(int[][] tableau, int plith) {
		for (int i = 0; i < Math.sqrt(tableau.length); i++) {
			for (int j = 0; j < Math.sqrt(tableau[i].length); j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 0; k < Math.sqrt(tableau.length); k++)
						for (int l = 0; l < Math.sqrt(tableau[k].length); l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaPanoMesi9(int[][] tableau, int plith) {
		for (int i = 0; i < Math.sqrt(tableau.length); i++) {
			for (int j = 3; j < Math.sqrt(tableau[i].length) + 3; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 0; k < Math.sqrt(tableau.length); k++)
						for (int l = 3; l < Math.sqrt(tableau[k].length) + 3; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaPanoDexia9(int[][] tableau, int plith) {
		for (int i = 0; i < Math.sqrt(tableau.length); i++) {
			for (int j = 6; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 0; k < Math.sqrt(tableau.length); k++)
						for (int l = 6; l < tableau[k].length; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaMesiAristera9(int[][] tableau, int plith) {
		for (int i = 3; i < Math.sqrt(tableau.length) + 3; i++) {
			for (int j = 0; j < Math.sqrt(tableau[i].length); j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 3; k < Math.sqrt(tableau.length) + 3; k++)
						for (int l = 0; l < Math.sqrt(tableau[k].length); l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}

		return false;
	}

	public boolean elexosSerasKaiStilisGiaMesiMesi9(int[][] tableau, int plith) {
		for (int i = 3; i < Math.sqrt(tableau.length) + 3; i++) {
			for (int j = 3; j < Math.sqrt(tableau[i].length) + 3; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 3; k < Math.sqrt(tableau.length) + 3; k++)
						for (int l = 3; l < Math.sqrt(tableau[k].length) + 3; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaMesiDexia9(int[][] tableau, int plith) {
		for (int i = 3; i < Math.sqrt(tableau.length) + 3; i++) {
			for (int j = 6; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 3; k < Math.sqrt(tableau.length) + 3; k++)
						for (int l = 6; l < tableau[k].length; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaKatoAristera9(int[][] tableau, int plith) {
		for (int i = 6; i < tableau.length; i++) {
			for (int j = 0; j < Math.sqrt(tableau[i].length); j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 6; k < tableau.length; k++)
						for (int l = 0; l < Math.sqrt(tableau[k].length); l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaKatoMesi9(int[][] tableau, int plith) {
		for (int i = 6; i < tableau.length; i++) {
			for (int j = 3; j < Math.sqrt(tableau[i].length) + 3; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 6; k < tableau.length; k++)
						for (int l = 3; l < Math.sqrt(tableau[k].length) + 3; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public boolean elexosSerasKaiStilisGiaKatoDexia9(int[][] tableau, int plith) {
		for (int i = 6; i < tableau.length; i++) {
			for (int j = 6; j < tableau[i].length; j++) {
				if ((tableau[i][j] != 0) && (tableau[i][j] >= -tableau.length && tableau[i][j] <= tableau.length)) {
					plith = 0;
					for (int k = 6; k < tableau.length; k++)
						for (int l = 6; l < tableau[k].length; l++)
							if (tableau[i][j] == tableau[l][k])
								plith++;
					if (plith >= 2)
						return true;
				}
			}
		}
		return false;
	}

	public void displayBoard() {/*
								 * displayBoard(): Displays the Sudoku board in a human-readable format. The
								 * formatting is adjusted based on the size of the Sudoku board (4x4 or 9x9).
								 */
		if (N == 4) {
			System.out.println("+------+------+");
			for (int i = 0; i < N; i++) {
				if (i == 2) {
					System.out.print("+------+------+");
					System.out.println();
					System.out.print("|");
				} else
					System.out.print("|");
				for (int j = 0; j < N; j++) {
					int displayValue = tableau[i][j];
					if (displayValue < 0) {
						displayValue = Math.abs(displayValue);
						System.out.print("(" + displayValue + ")");
					} else if (displayValue == 0) {
						System.out.print(" " + "." + " ");
					} else if (displayValue > 0)
						System.out.print(" " + displayValue + " ");
					if (j == 1)
						System.out.print("|");
				}
				System.out.print("|");
				System.out.println();

			}
			System.out.println("+------+------+");
		} else {
			System.out.println("+---------+---------+---------+");
			for (int i = 0; i < N; i++) {
				if (i == 3 || i == 6) {
					System.out.print("+---------+---------+---------+");
					System.out.println();
					System.out.print("|");
				} else
					System.out.print("|");
				for (int j = 0; j < N; j++) {
					int displayValue = tableau[i][j];
					if (displayValue < 0) {
						displayValue = Math.abs(displayValue);
						System.out.print("(" + displayValue + ")");
					} else if (displayValue == 0) {
						System.out.print(" " + "." + " ");
					} else if (displayValue > 0)
						System.out.print(" " + displayValue + " ");
					if (j == 2 || j == 5)
						System.out.print("|");
				}
				System.out.print("|");
				System.out.println();

			}
			System.out.println("+---------+---------+---------+");
		}
	}

	public int[][] getTable() {/* getTable(): Returns the 2D array representing the Sudoku board. */
		return tableau;
	}

	public void setTable(
			int[][] board) {/* setTable(int[][] board): Sets the Sudoku board with the provided 2D array. */
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				tableau[i][j] = board[i][j];
	}
}
