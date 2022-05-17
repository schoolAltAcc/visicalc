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
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				grid[i][j] = new Cell();
			}
		}
	}
	
	//returns the index given from string
	public static int[] strToIndex(String string) {
		string = string.toUpperCase();
		int[] index = {-1,-1};
		if(string.length() == 1 ||string.length() > 3) {
			return index;
		}
		Character letter = string.charAt(0);
		index[1] = "ABCDEFG".indexOf(letter);
		//get last string to num
		if(string.substring(1).contains(".")) {
			return index;
		}
		letter = string.charAt(1);
		if(Character.isDigit(letter)) {
			index[0] = Integer.parseInt(string.substring(1))-1;
		}
		return index;
	}

	public static boolean isIndex(String string) {
		int[] index = strToIndex(string);
		return(index[0] != -1 && index[1] != -1);
	}
	
	public static boolean isIndex(int[] index) {
		if(index.length != 2) {
			return false;
		}else if(index[0] == -1 || index[1] == -1) {
			return false;
		}
		return true;
	}
	public static Cell[] fetchElements(int[] sindex, int[] lindex) {//{4,1},{6,1} y,x because i had error in strtoindex when i first wrote it and it continues to this day
		Cell[] output = new Cell[(lindex[0]-sindex[0]+1)*((lindex[1]-sindex[1])+1)];//+1 ?
		int x = 0;
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				output[x] = VisiCalc.CellGrid.grid[j][i];
				x++;
			}
		}
		return output;
	}
	
	public static double[] fetchElementsValues(int[] sindex, int[] lindex) {//{4,1},{6,1} y,x because i had error in strtoindex when i first wrote it and it continues to this day
		double[] output = new double[(lindex[0]-sindex[0]+1)*((lindex[1]-sindex[1])+1)];//+1 ?
		int x = 0;
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				output[x] = (VisiCalc.CellGrid.grid[j][i].getValue());
				x++;
			}
		}
		return output;
	}
	
	public static void sorta(int[] sindex, int[] lindex) {
		Cell[] list = fetchElements(sindex,lindex);
		Arrays.sort(list);
		int x = 0;
		for(int i = sindex[1]; i <= lindex[1];i++) {
			for(int j = sindex[0]; j <= lindex[0];j++) {
				VisiCalc.CellGrid.grid[j][i] = list[x];
				x++;
			}
		}
		
	}
	
	public static void sortd(int[] sindex, int[] lindex) {
		Cell[] list = fetchElements(sindex,lindex);
		Arrays.sort(list);
		int x = 0;
		for(int i = lindex[1]; i >= sindex[1];i--) {
			for(int j = lindex[0]; j >= sindex[0];j--) {
				VisiCalc.CellGrid.grid[j][i] = list[x];
				x++;
			}
		}
		
	}		
}
