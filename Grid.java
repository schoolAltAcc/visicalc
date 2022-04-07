package packcalc;

import java.util.ArrayList;
import java.util.Scanner;

public class Grid {
	// class that is an array of cells, has to string
	final int columns = 7;
	final int rows = 10;
	Cell[][] grid = new Cell[rows][columns];

	public Grid(Cell[][] in) {
		if (in.length <= rows) {
			Boolean error = false;
			for (int i = 0; i < in.length; i++) {
				if (in[i].length > columns) {
					//System.out.print("Too many columns given");
					error = true;
				}
			}
			if (!error) {
				this.grid = in;
			} else {
				System.out.print("Too many columns given");
				clearAll();
			}

		} else {
			System.out.print("Too many rows given");
			clearAll();
		}
	}
	
	public Grid() {
		clearAll();
	}
	
	public String toString() {
		String output = "";
		String[] letters = {"A","B","C","D","E","F", "G"}; //if you make letters match with columns you can make scalable solution Integer.toCharacter or something for unicode scalable characters
		output += Cell.printLikeCell("");
		for(String e : letters) {
			output += Cell.printLikeCell(e);
		}
		output += "\n";
		output += columnsToDashLine();
		Integer i = 1;
		for(Cell[] e : grid) {
			output += Cell.printLikeCell(i.toString());
			i++;
			for(Cell c : e) {
				output += c.toString();
			}
			output += "\n";
			output += columnsToDashLine();
		}
		return output;
	}
	
	private String columnsToDashLine() {
		String output = "";
		for(int i = 0; i < (columns+1) * (Cell.textLength+2); i++) {
			output += "-";
		}
		output += "\n";
		return output;
	}
	
	public void clearAll() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
	}

	//returns if input valid or cmd type
	public String cmdType(String input) {
		ArrayList<String> tokens = tokenify(input);
		if(tokens.size() >= 3 && tokens.get(1).equalsIgnoreCase("=")) {
			//this means we doing a formula or defining cell
			//lets check if its a cell
			//copied code from strto index, maybe make seperate method
			// { is so possible cell, letter, and isNumeric dont get in way
			String possibleCell = tokens.get(0);
			Character letter = possibleCell.charAt(0);
			if(!(Character.getNumericValue('A') <= Character.getNumericValue(letter) && Character.getNumericValue('G') >= Character.getNumericValue(letter))) {
				//not a cell error
				return "error";
			}
			//lazy to define easy method that checks if character is digit with for loop so lambda expression go brrt
			boolean isNumeric = possibleCell.substring(1).chars().allMatch( Character::isDigit );
			if(!isNumeric && Integer.parseInt(possibleCell.substring(1)) > rows) {
				//not a cell error
				return "error";
			}
			//get index
			int[] index = strToIndex(possibleCell);
			if(index[0] < 0 ||index[1] < 0) {
				//invalid index
				return "error";
			}
			
			//time to check other side of equals sign to check for valid input (NUMERIC VALUE ONLY, NO EXPRESSIONS YET >_<)
			String possibleValue = tokens.get(2);
			isNumeric = possibleValue.chars().allMatch( Character::isDigit );
			if(!isNumeric) {
				//text cell time
				TextCell newCell = new TextCell(possibleValue);
				grid[index[0]][index[1]] = newCell;
				return "define";
			}
			//if we reached this point that means that we have a valid cell, an = expression, and a number after it.
			//so its safe to assume this is an assignment command
			Cell newCell = new Cell(Integer.parseInt(possibleValue));
			grid[index[0]][index[1]] = newCell;
			//after we do assignment we return define so we can print to console that cell has been defined
			return "define";
		}
		return "error";
	}
	
	//doesn't check input fix soon
	public int[] strToIndex(String string) {
		int[] index = {-1,-1};
		Character letter = string.charAt(0);
		if(Character.getNumericValue('A') <= Character.getNumericValue(letter) && Character.getNumericValue('G') >= Character.getNumericValue(letter)) {
			index[1] = "ABCDEFG".indexOf(letter);
		}
		//get last string to num
		index[0] = Integer.parseInt(string.substring(1))-1;
		return index;
	}

	public ArrayList<String> tokenify(String input) {
		Scanner parser = new Scanner(input);
		ArrayList<String> tokens = new ArrayList<String>();
		do {
			tokens.add(parser.next());
		}while(parser.hasNext());
		parser.close();
		return tokens;
		
	}
			
}
