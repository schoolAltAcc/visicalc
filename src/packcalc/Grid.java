package packcalc;

import java.util.Arrays;

public class Grid {
	// class that is an array of cells, has to string
	final int columns = 7;
	final int rows = 10;
	Cell[][] grid = new Cell[rows][columns];

	//makes grid from an existing cell array
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
	
	//makes empty grid
	public Grid() {
		clearAll();
	}
	
	//to string method for all cells
	public String toString() {
		String output = "";
		String[] letters = {"A","B","C","D","E","F", "G"}; 
		//if you make letters match with columns you can make scalable solution Integer.toCharacter or something for unicode scalable characters
		//i didn't im too lazy

		//this prints the top row of letters
		output += Cell.printLikeCell("");
		for(String e : letters) {
			output += Cell.printLikeCell(e);
		}
		output += "\n";
		output += columnsToDashLine();
		//loop through all rows in grid and print them
		Integer i = 1;
		for(Cell[] e : grid) {
			//honestly dont remember why i dont use cell.tostring, but this is more efficient some how
			output += Cell.printLikeCell(i.toString());
			i++;
			//loop through all cells in row and print them
			for(Cell c : e) {
				output += c.toString();
			}
			output += "\n";
			output += columnsToDashLine();
		}
		return output;
	}
	
	//dash line helper method for to string
	private String columnsToDashLine() {
		String output = "";
		for(int i = 0; i < (columns+1) * (Cell.textLength+2); i++) {
			output += "-";
		}
		output += "\n";
		return output;
	}
	
	//clears whole array of cells
	public void clearAll() {
		//loop through all cells and make values the default
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
	}
	
	//returns the index given from string
	public static int[] strToIndex(String string) {
		//to allow for a1 we make upercase
		string = string.toUpperCase();
		int[] index = {-1,-1};
		//check that we are good length
		if(string.length() == 1 ||string.length() > 3) {
			return index;
		}
		Character letter = string.charAt(0);
		//first index of collumn
		index[1] = "ABCDEFG".indexOf(letter);
		//check if double, if yes then we give bad index
		if(string.substring(1).contains(".")) {
			return index;
		}
		//get the value of the number
		letter = string.charAt(1);
		if(Character.isDigit(letter)) {
			index[0] = Integer.parseInt(string.substring(1))-1;
		}
		return index;
	}

	//check if index has a -1 anywhere
	public static boolean isIndex(String string) {
		int[] index = strToIndex(string);
		return(index[0] != -1 && index[1] != -1);
	}
	
	//same as above
	public static boolean isIndex(int[] index) {
		if(index.length != 2) {
			return false;
		}else if(index[0] == -1 || index[1] == -1) {
			return false;
		}
		return true;
	}
	
	//loop through rectangular region and get the elements within into a cell[] array, also first time i realized i was using the
	//y,x system instead of x,y 
	public static Cell[] fetchElements(int[] sindex, int[] lindex) {
		Cell[] output = new Cell[(lindex[0]-sindex[0]+1)*((lindex[1]-sindex[1])+1)];
		int x = 0;
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				output[x] = VisiCalc.CellGrid.grid[j][i];
				x++;
			}
		}
		return output;
	}
	
	//same as above but give me double values of cell instead of cell objects
	public static double[] fetchElementsValues(int[] sindex, int[] lindex) {
		double[] output = new double[(lindex[0]-sindex[0]+1)*((lindex[1]-sindex[1])+1)];
		int x = 0;
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				output[x] = (VisiCalc.CellGrid.grid[j][i].getValue());
				x++;
			}
		}
		return output;
	}
	
	//sort decending
	public static void sortd(int[] sindex, int[] lindex) {
		Cell[] list = fetchElements(sindex,lindex);
		Arrays.sort(list);
		int x = 0;
		//from small index to large index
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				VisiCalc.CellGrid.grid[j][i] = list[x];
				x++;
			}
		}
		
	}
	
	public static void sorta(int[] sindex, int[] lindex) {
		Cell[] list = fetchElements(sindex,lindex);
		Arrays.sort(list);
		int x = 0;
		//from large index to small
		for(int i = lindex[1]; i >= sindex[1];i--) {
			for(int j = lindex[0]; j >= sindex[0];j--) {
				VisiCalc.CellGrid.grid[j][i] = list[x];
				x++;
			}
		}
		
	}		
}
