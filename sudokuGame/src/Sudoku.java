/**
 * Author: George Andreou Written: 12/11/2023 Last updated: 14/11/2023
 *
 * Compilation: javac Sudoku.java .;./stdlib.jar Board.java UserChoice.java
 * Sudoku.java Execution: java -Sudoku/stdlib.jar Sudoku <N> <game-file>
 *
 * You play Sudoku in a 4x4 or 9x9 table
 *
 */
public class Sudoku {
	private Board sudokuBoard; /*
								 * thTe program defines a public class named Sudoku. The class has a private
								 * instance variable sudokuBoard of type Board and an integer variable N. The
								 * Sudoku class has a constructor that takes an integer N and initializes the
								 * sudokuBoard using a Board object.
								 */
	private int N;

	public Sudoku(int N) {
		this.N = N;
		this.sudokuBoard = new Board(N);
	}

	public void play() { /*
							 * The method begins with initializing several variables, including a boolean
							 * flag (i) and variables for the current row, column, and value to be placed on
							 * the Sudoku board. Two additional variables (xaraktiras1 and xaraktiras2) are
							 * used to store characters from the user input. Main Loop:
							 * 
							 * The method enters a main loop, where the user is prompted for input until the
							 * game is solved or terminated. Inside the loop, the current state of the
							 * Sudoku board is displayed. The user is prompted to enter a command in a
							 * specific format, indicating the row, column, and value to be placed on the
							 * board or other actions (e.g., clearing a cell, saving and ending the game).
							 * User Input Processing:
							 * 
							 * User input is processed and validated using the UserChoice class. The input
							 * is expected to be in a specific format, and any deviations from this format
							 * are handled with appropriate error messages. The method checks whether the
							 * user input is valid and adheres to the Sudoku rules. Sudoku Rules Validation:
							 * 
							 * Depending on the user's input, the method checks various conditions to ensure
							 * the move is valid according to Sudoku rules. This includes checking for
							 * duplicates in rows, columns, and subgrids (boxes). Game Progression:
							 * 
							 * If the move is valid, the value is inserted into the Sudoku board, and the
							 * game state is updated. The method checks whether the Sudoku puzzle is solved
							 * by examining the filled cells. If the puzzle is solved, it displays a victory
							 * message and the final state of the board. Loop Termination:
							 * 
							 * The loop continues until the user chooses to save and end the game or until
							 * an invalid move is made. The termination conditions include the user
							 * successfully solving the puzzle, saving and ending the game, or making an
							 * invalid move. The play() method effectively manages the flow of the Sudoku
							 * game, interacting with the user, processing input, and ensuring that the game
							 * progresses according to the rules.
							 */
		boolean i = true;
		int[][] board = sudokuBoard.getTable();
		boolean didYouSolveIt = false;
		int row = 0, column = 0, value = 0;
		String xaraktiras1 = "", xaraktiras2 = "";
		while (didYouSolveIt == false) {
			i = true;
			while (i) {
				sudokuBoard.displayBoard();
				System.out.println("Enter your command in the following format:\n"
						+ "+ i,j=val: for entering val at position (i,j)\n" + "+ i,j=0 : for clearing cell (i,j)\n"
						+ "+ 0,0=0 : for saving and ending the game\n" + "Notice: i,j,val numbering is from [1..4]"
						+ ">");
				UserChoice choise = new UserChoice();
				String detomena = StdIn.readLine();
				choise.setRow((int) (detomena.charAt(0) - '0'));
				choise.setXaraktiras1(detomena.charAt(1) + "");
				choise.setColumn((int) (detomena.charAt(2) - '0'));
				choise.setXaraktiras2(detomena.charAt(3) + "");
				choise.setValue((int) (detomena.charAt(4) - '0'));
				row = choise.getRow();
				column = choise.getColumn();
				value = choise.getValue();
				xaraktiras1 = choise.getXaraktiras1();
				xaraktiras2 = choise.getXaraktiras2();
				if (getUserInput(row, xaraktiras1, column, xaraktiras2, value))
					i = true;
				else
					i = false;
			}
			if (row == 0 && column == 0 && value > 0) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				didYouSolveIt = false;
			} else if (row == 0 && column > 0 && value > 0) {
				System.out.println("Error: This is not a valid sudoku!rule not met! ");
				didYouSolveIt = false;
			} else if (row > 0 && column > 0 && value > 0) {
				if (isValidMove(row, column, value, board) == false)
					didYouSolveIt = false;
				else {
					didYouSolveIt = true;
					System.out.println("Value inserted!");
					sudokuBoard.setTable(board);
					for (int k = 0; k < board.length; k++)
						for (int j = 0; j < board.length; j++)
							if (board[k][j] == 0)
								didYouSolveIt = false;
					if (didYouSolveIt == true) {
						System.out.println("WOOOOWWWWOOOOOWWWWW!!!!!!!");
						sudokuBoard.displayBoard();
					}
				}
			} else if (row > 0 && column > 0 && value == 0) {
				if (board[row - 1][column - 1] > 0) {
					board[row - 1][column - 1] = value;
					System.out.println("Value has been deleted");
				} else if (board[row - 1][column - 1] < 0)
					System.out.println("You are not allowed to delete the already given number!!!");
			} else if (row == 0 && column == 0 && value == 0)
				didYouSolveIt = true;
		}

	}

	public boolean isValidMove(int row, int column, int value,
			int[][] board) { /*
								 * The method takes four parameters: row, column, value, and the Sudoku board.
								 * row and column represent the position on the board where the user is
								 * attempting to place a value. value is the number the user is trying to place
								 * at the specified position. board is the current state of the Sudoku board.
								 * Checking Existing Values:
								 * 
								 * It first checks if there is already a value present at the specified position
								 * (row, column). If there is, it indicates that the user is trying to overwrite
								 * an existing value, and this move is considered invalid. Checking Row and
								 * Column Constraints:
								 * 
								 * It checks the entire row and column of the specified position to ensure that
								 * the same value does not already exist in that row or column. If the value is
								 * found in either the row or column, the move is considered invalid. Checking
								 * Subgrid (Box) Constraints:
								 * 
								 * For a valid Sudoku move, the value must not already exist in the 3x3 subgrid
								 * (box) to which the specified position belongs. The method checks this
								 * condition, and if the value is found in the subgrid, the move is considered
								 * invalid. Returning Validity:
								 * 
								 * The method returns a boolean value indicating whether the move is valid
								 * (true) or invalid (false). Side Effects:
								 * 
								 * If the move is valid, the method might update the board with the new value at
								 * the specified position. In summary, isValidMove() encapsulates the rules of
								 * Sudoku, checking whether a proposed move adheres to these rules. It ensures
								 * that the move does not violate any constraints within the row, column, and
								 * subgrid of the specified position on the Sudoku board.
								 */
		int plithSeras = 0;
		int plithStili = 0;
		int plith = 0;
		int plithNegSeras = 0;
		int plithNegStilis = 0;
		int plithNegMikrouPin = 0;
		if (board[row - 1][column - 1] != 0) {
			System.out.println("Thare is a number there.Insert again!!!");
			return false;
		}
		board[row - 1][column - 1] = value;

		for (int s = 0; s < board.length; s++) {
			if (board[row - 1][s] == board[row - 1][column - 1])
				plithSeras++;
			if (board[row - 1][s] == -board[row - 1][column - 1])
				plithNegSeras++;
		}
		if (plithSeras >= 2 || plithNegSeras >= 1) {
			System.out.println("There is the same number in this row !!!");
			board[row - 1][column - 1] = 0;
			return false;
		}
		for (int k = 0; k < board.length; k++) {
			if (board[k][column - 1] == board[row - 1][column - 1])
				plithStili++;
			if (board[k][column - 1] == -board[row - 1][column - 1])
				plithNegStilis++;
		}
		if (plithStili >= 2 || plithNegStilis >= 1) {
			System.out.println("There is the same number in this column !!!");
			board[row - 1][column - 1] = 0;
			return false;
		}
		if (board.length == 4) {
			if ((row - 1 == 0 || row - 1 == 1) && (column - 1 == 0 || column - 1 == 1)) {
				for (int i = 0; i < Math.sqrt(board.length); i++)
					for (int j = 0; j < Math.sqrt(board[i].length); j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 0 || row - 1 == 1) && (column - 1 == 2 || column - 1 == 3)) {
				for (int i = 0; i < Math.sqrt(board.length); i++)
					for (int j = 2; j < board[i].length; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}
			} else if ((row - 1 == 2 || row - 1 == 3) && (column - 1 == 0 || column - 1 == 1)) {
				for (int i = 2; i < board.length; i++)
					for (int j = 0; j < Math.sqrt(board[i].length); j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 2 || row - 1 == 3) && (column - 1 == 2 || column - 1 == 3)) {
				for (int i = 2; i < board.length; i++)
					for (int j = 2; j < board[i].length; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			}
		} else if (board.length == 9) {
			if ((row - 1 == 0 || row - 1 == 1 || row - 1 == 2)
					&& (column - 1 == 0 || column - 1 == 1 || column - 1 == 2)) {
				for (int i = 0; i < Math.sqrt(board.length); i++)
					for (int j = 0; j < Math.sqrt(board[i].length); j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 0 || row - 1 == 1 || row - 1 == 2)
					&& (column - 1 == 3 || column - 1 == 4 || column - 1 == 5)) {
				for (int i = 0; i < Math.sqrt(board.length); i++)
					for (int j = 3; j < Math.sqrt(board[i].length) + 3; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 0 || row - 1 == 1 || row - 1 == 2)
					&& (column - 1 == 6 || column - 1 == 7 || column - 1 == 8)) {
				for (int i = 0; i < Math.sqrt(board.length); i++)
					for (int j = 6; j < board[i].length; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 3 || row - 1 == 4 || row - 1 == 5)
					&& (column - 1 == 0 || column - 1 == 1 || column - 1 == 2)) {
				for (int i = 3; i < Math.sqrt(board.length) + 3; i++)
					for (int j = 0; j < Math.sqrt(board[i].length); j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 3 || row - 1 == 4 || row - 1 == 5)
					&& (column - 1 == 3 || column - 1 == 4 || column - 1 == 5)) {
				for (int i = 3; i < Math.sqrt(board.length) + 3; i++)
					for (int j = 3; j < Math.sqrt(board[i].length) + 3; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 3 || row - 1 == 4 || row - 1 == 5)
					&& (column - 1 == 6 || column - 1 == 7 || column - 1 == 8)) {
				for (int i = 3; i < Math.sqrt(board.length) + 3; i++)
					for (int j = 6; j < board[i].length; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 6 || row - 1 == 7 || row - 1 == 8)
					&& (column - 1 == 0 || column - 1 == 1 || column - 1 == 2)) {
				for (int i = 6; i < board.length; i++)
					for (int j = 0; j < Math.sqrt(board[i].length); j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 6 || row - 1 == 7 || row - 1 == 8)
					&& (column - 1 == 3 || column - 1 == 4 || column - 1 == 5)) {
				for (int i = 6; i < board.length; i++)
					for (int j = 3; j < Math.sqrt(board[i].length) + 3; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			} else if ((row - 1 == 6 || row - 1 == 7 || row - 1 == 8)
					&& (column - 1 == 6 || column - 1 == 7 || column - 1 == 8)) {
				for (int i = 6; i < board.length; i++)
					for (int j = 6; j < board[i].length; j++) {
						if (board[i][j] == board[row - 1][column - 1])
							plith++;
						if (board[i][j] == -board[row - 1][column - 1])
							plithNegMikrouPin++;
					}
				if (plith >= 2 || plithNegMikrouPin >= 1) {
					System.out.println("Error: Illegal value insertion! Same box rule not met!");
					board[row - 1][column - 1] = 0;
					return false;
				}

			}
		}

		return true;
	}

	public boolean getUserInput(int row, String xaraktiras1, int column, String xaraktiras2,
			int value) {/*
						 * This method interacts with the user to get input. It calls
						 * areTheInputsCorrect() to check the validity of user input.
						 */
		if (areTheInputsCorrect(row, xaraktiras1, column, xaraktiras2, value))
			return true;
		return false;
	}

	public boolean areTheInputsCorrect(int row, String xaraktiras1, int column, String xaraktiras2,
			int value) {/*
						 * Row Value (row):
						 * 
						 * Verifies if the row value is within the valid range (0 to N-1 for a 4x4
						 * Sudoku or 0 to N-1 for a 9x9 Sudoku). Prints an error message if the row
						 * value is outside the valid range. Comma Separator (xaraktiras1):
						 * 
						 * Checks if the comma (,) separator is present in the correct position in the
						 * input. Prints an error message if the comma is missing or misplaced. Column
						 * Value (column):
						 * 
						 * Verifies if the column value is within the valid range (0 to N-1 for a 4x4
						 * Sudoku or 0 to N-1 for a 9x9 Sudoku). Prints an error message if the column
						 * value is outside the valid range. Equal Sign (xaraktiras2):
						 * 
						 * Checks if the equal sign (=) is present in the correct position in the input.
						 * Prints an error message if the equal sign is missing or misplaced. Value
						 * (value):
						 * 
						 * Depending on the size of the Sudoku board (4x4 or 9x9), it verifies if the
						 * value is within the valid range (0 to 4 for a 4x4 Sudoku or 0 to 9 for a 9x9
						 * Sudoku). Prints an error message if the value is outside the valid range. The
						 * areTheInputsCorrect() method essentially ensures that the user's input
						 * follows the expected format and that the provided values are within the
						 * allowed range for the given Sudoku board size. If any of these conditions are
						 * not met, the method prints an error message indicating the issue.
						 */
		boolean[] isValid = new boolean[5];
		if (row < 0 || row > N) {
			isValid[0] = true;
			System.out.println("Wrong i:Please insert a valid number 0-8(gia 9x9) i 0-3(gia 4x4)");
		} else if (!(xaraktiras1.equals(","))) {
			isValid[1] = true;
			System.out.println("Error: wrong format of command!");
		} else if (column < 0 || column > N) {
			isValid[2] = true;
			System.out.println("Wrong j:Please insert a valid number 0-8(gia 9x9) i 0-3(gia 4x4)");
		} else if (!(xaraktiras2.equals("="))) {
			isValid[3] = true;
			System.out.println("Error: wrong format of command!");
		} else if (N == 4) {
			if (value < 0 || value >= 5) {
				isValid[4] = true;
				System.out.println("Wrong val:Please insert a valid number 0-4");
			}
		} else if (N == 9) {
			if (value < 0 || value >= 10) {
				isValid[4] = true;
				System.out.println("Wrong val:Please insert a valid number 0-9");
			}
		}
		for (int i = 0; i < 5; i++)
			if (isValid[i] == true)
				return true;
		return false;

	}

	public static void main(
			String[] args) {/*
							 * Command Line Argument Handling:
							 * 
							 * The method first checks whether the correct number of command-line arguments
							 * is provided (exactly two arguments: dimension N and the game file). It
							 * validates that the dimension N is either 4 or 9, as Sudoku boards typically
							 * come in 4x4 or 9x9 dimensions. If the arguments are not valid, it prints an
							 * appropriate error message and exits the program. Sudoku Initialization:
							 * 
							 * Once the command-line arguments are validated, the program creates a Sudoku
							 * object, initializing it with the specified dimension N. The Sudoku board is
							 * then read from the game file provided as the second command-line argument. If
							 * successful, the game proceeds; otherwise, an appropriate message is printed.
							 * Game Execution:
							 * 
							 * The play() method of the Sudoku object is called, initiating the game. The
							 * player is prompted to make moves and input commands until the game is either
							 * solved or terminated. Output File Generation:
							 * 
							 * After the game is completed, the final state of the Sudoku board is obtained.
							 * The program generates an output file name based on the input game file name.
							 * The final Sudoku board state is saved to this output file. User Feedback:
							 * 
							 * Throughout the execution, the program provides feedback to the user, such as
							 * error messages for invalid input, information about the progress of the game,
							 * and a message indicating that the game is saved upon completion. In summary,
							 * the main() method orchestrates the Sudoku game, handling input validation,
							 * game initialization, execution, and the generation of the final output file.
							 * It acts as the control flow for the entire Sudoku application.
							 */
		if (args.length != 2) {
			System.out.println("Please give the dimension N followed by a <game-file> as the only 2 arguments");
			return;
		}
		int N = Integer.parseInt(args[0]);
		if (N != 9 && N != 4) {
			System.out.print("The allowed value for N is either 4 or 9!\n"
					+ "Please re run the program with a valid value for N");
			return;
		}

		Sudoku game = new Sudoku(N);
		if (game.sudokuBoard.readBoard(args[1]))
			return;
		game.play();

		int[][] finalTable = game.sudokuBoard.getTable();
		String varyNearCloseOutputFile = "out-";
		String outputFile = "";
		String closeToRealOutputFile = "";
		String realOutputFile = "";
		for (int i = args[1].length() - 1; i >= 0; i--) {
			outputFile += args[1].charAt(i);
			if (outputFile.length() == 10)
				i = -1;
		}
		for (int i = outputFile.length() - 1; i >= 0; i--)
			closeToRealOutputFile += outputFile.charAt(i);
		varyNearCloseOutputFile += closeToRealOutputFile;
		int miosi = args[1].length() - closeToRealOutputFile.length();
		for (int i = 0; i < miosi; i++)
			realOutputFile += args[1].charAt(i);
		String trueRealOutputFile = realOutputFile + varyNearCloseOutputFile;
		Out output = new Out(trueRealOutputFile);
		System.out.println("Your game is saved");
		for (int i = 0; i < finalTable.length; i++) {
			for (int j = 0; j < finalTable.length; j++)
				output.print(" " + finalTable[i][j]);
			output.println();

		}
	}
}
