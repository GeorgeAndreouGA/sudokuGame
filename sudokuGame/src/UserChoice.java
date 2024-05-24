
public class UserChoice {/*
							 * Private Fields:
							 * 
							 * row: An integer representing the row value chosen by the user. xaraktiras1: A
							 * string representing a character (possibly a comma) used in the user's input.
							 * column: An integer representing the column value chosen by the user.
							 * xaraktiras2: A string representing another character (possibly an equal sign)
							 * used in the user's input. value: An integer representing the value chosen by
							 * the user.
							 */
	private int row;
	private String xaraktiras1;
	private int column;
	private String xaraktiras2;
	private int value;

	public UserChoice() {/*
							 * Default Constructor:
							 * 
							 * Initializes the private fields with default values (0 for integers, an empty
							 * string for strings).
							 */
		row = 0;
		xaraktiras1 = "";
		column = 0;
		xaraktiras2 = "";
		value = 0;
	}

	public void setRow(int num1) {/*
									 * Setter Methods:
									 * 
									 * setRow(int num1): Sets the row field to the provided integer value.
									 * setXaraktiras1(String char1): Sets the xaraktiras1 field to the provided
									 * string value. setColumn(int num2): Sets the column field to the provided
									 * integer value. setXaraktiras2(String char2): Sets the xaraktiras2 field to
									 * the provided string value. setValue(int num3): Sets the value field to the
									 * provided integer value.
									 */
		row = num1;
	}

	public void setXaraktiras1(String char1) {
		xaraktiras1 = char1;
	}

	public void setColumn(int num2) {
		column = num2;
	}

	public void setXaraktiras2(String char2) {
		xaraktiras2 = char2;
	}

	public void setValue(int num3) {
		value = num3;
	}

	public int getRow() {/*
							 * Getter Methods:
							 * 
							 * getRow(): Returns the value of the row field. getXaraktiras1(): Returns the
							 * value of the xaraktiras1 field. getColumn(): Returns the value of the column
							 * field. getXaraktiras2(): Returns the value of the xaraktiras2 field.
							 * getValue(): Returns the value of the value field.
							 */
		return row;
	}

	public String getXaraktiras1() {
		return xaraktiras1;
	}

	public int getColumn() {
		return column;
	}

	public String getXaraktiras2() {
		return xaraktiras2;
	}

	public int getValue() {
		return value;
	}
}
