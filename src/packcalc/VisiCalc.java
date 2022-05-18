//Artem Semenov
//Period 7 Ap CS
//Visicalc - Finished
package packcalc;
import java.util.*;
public class VisiCalc {

	public static Grid CellGrid = new Grid();
	
	public static void main(String[] args) {
		Boolean done = false;
		System.out.println("Welcome to Visual Calculator.");
		//scanner and grid define
		Scanner in = new Scanner(System.in);
		String input = "";
		//main loop
		while(!done) {
			System.out.print("Enter: ");
			//get input
			input = in.nextLine();
			//if quit then stop, otherwise go to main program
			if(!input.equalsIgnoreCase("quit")) {
				//main program
				
				//first we check for simple cmds like print, then we go to parse input method for complex cmds
				if(input.equalsIgnoreCase("print")) {
					//print grid
					System.out.println(CellGrid);
				}else if(input.equalsIgnoreCase("strDemo")) {//haha OLD
					//temp code to manaully create grid
					Cell abe = new Cell();
					TextCell abe2 = new TextCell("12345");
					TextCell abe3 = new TextCell("123");
					TextCell abe4 = new TextCell("1234567");
					TextCell abe5 = new TextCell("12345678910");
					CellGrid.grid[0][0] = abe;
					CellGrid.grid[0][1] = abe2;
					CellGrid.grid[0][2] = abe3;
					CellGrid.grid[0][3] = abe4;
					CellGrid.grid[0][4] = abe5;
					//print grid
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("help")){
					help();
				}else if(input.equalsIgnoreCase("formDemo")){
					//demo code to call parse input for tests
					System.out.println(parseInput("a1 = 8"));
					System.out.println(parseInput("a2 = 7"));
					System.out.println(parseInput("a3 = ( A1 + A2 )"));
					System.out.println(parseInput("C1 = ( A3 - 14 / 7 + 8 * 3.14 )"));
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("formDemo2")) {
					//demo code to call parse input for tests
					System.out.println(parseInput("A1 = 3"));
					System.out.println(parseInput("A2 = 5"));
					System.out.println(parseInput("A3 = -10"));
					System.out.println(parseInput("B1 = ( A1 )"));
					System.out.println(parseInput("B2 = ( A2 + 100 )"));
					System.out.println(parseInput("B3 = ( A2 - A3 )"));
					System.out.println(parseInput("C9 = ( A1 * A2 * A3 )"));
					System.out.println(parseInput("C10 = ( A3 / A2 + A3 )")); //C10 = ( A1 / A2 + A3 )
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("sumavgDemo")) {
					//demo code to call parse input for tests
					System.out.println(parseInput("B5 = 11"));
					System.out.println(parseInput("B6 = 39"));
					System.out.println(parseInput("B7 = -400"));
					System.out.println(parseInput("C5 = 0"));
					System.out.println(parseInput("D5 = 7"));
					System.out.println(CellGrid);
					System.out.println(parseInput("E7 = ( SUM B5 - B7 )"));
					System.out.println(parseInput("E8 = ( AVG B5 - D5 )"));
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("sortademo")){ 
					//demo code to call parse input for tests
					System.out.println(parseInput("C1 = 5"));
					System.out.println(parseInput("C2 = 13"));
					System.out.println(parseInput("C3 = -2"));
					System.out.println(parseInput("D10 = 10/11/2000"));
					System.out.println(parseInput("E10 = 10/4/2013"));
					System.out.println(parseInput("F10 = 9/13/2006"));
					System.out.println(parseInput("G5 = \" yolo \""));
					System.out.println(parseInput("G6 = \" swagaswag \""));
					System.out.println(parseInput("G7 = \" sha zaam \""));
					System.out.println(CellGrid);
					System.out.println(parseInput("SORTA C1 - C3"));
					System.out.println(parseInput("SORTD D10 - F10"));
					System.out.println(parseInput("SORTA G5 - G7"));
					System.out.println(CellGrid);
					System.out.println(parseInput("SORTA A1 - G10"));
					System.out.println(CellGrid);
					System.out.println(parseInput("SORTD A1 - G10"));
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("sortbdemo")){ 
					//demo code to call parse input for tests
					System.out.println(parseInput("a1 = 100.00"));
					System.out.println(parseInput("b1 = 06/13/2020"));
					System.out.println(parseInput("C1 = \" sha zaam \""));
					System.out.println(parseInput("d1 = \" yolo \""));
					System.out.println(parseInput("e1 = 10/11/2000"));
					System.out.println(parseInput("f1 = \" swagaswag \""));
					System.out.println(parseInput("g1 = -2"));
					System.out.println(CellGrid);
					System.out.println(parseInput("SORTD a1 - g1"));
					System.out.println(CellGrid);
					System.out.println(parseInput("SORTA a1 - g1"));
					System.out.println(CellGrid);
					CellGrid.clearAll();
				}else if(input.equalsIgnoreCase("clear")) {
					CellGrid.clearAll();
					System.out.println("Cleared the grid!");
				}else {
					//echo input if not print or has =
					System.out.println(parseInput(input));
				}
			}else {
				//if we type quit, tell main loop to start and print goodbye
				done = true;
				System.out.println("Goodbye!");
			}
		}
		//close scanner for resource preservation or something
		in.close();
	}
	
	public static void help() {
		System.out.println("\n--------------\nWelcome to Visicalc!\nHere are some available cmds (no case sensitivity available for those who like to live on the edge, but not recommended)");
		System.out.println("A1 = 1\nA1");
		System.out.println("A1 = -12.0");
		System.out.println("A1 = \" text\"");
		System.out.println("A1 = 06//20//2020");
		System.out.println("A1 = ( A2 + A3 * A4 / A5 - A6 )");
		System.out.println("A1 = ( SUM A2 - A3 )");
		System.out.println("A1 = ( AVG A2 - A3 )");
		System.out.println("SORTA A1 - A3");
		System.out.println("SORTD A1 - A3");
		System.out.println("PRINT");
		System.out.println("CLEAR A1");
		System.out.println("CLEAR");
		System.out.println("PRINT");
		System.out.println("QUIT");
		System.out.println("\n--------------\nAlso have some demos:\n--------------\n");
		System.out.println("strDemo");
		System.out.println("formDemo");
		System.out.println("formDemo2");
		System.out.println("sumavgDemo");
		System.out.println("sortademo");
		System.out.println("sortbdemo\n--------------\n");
	}

	//main input parser for complicated methods (cant be evauluated with .equalsignorecase echos input if error
	public static String parseInput(String input){
		//if we have equals
		String upercaseInput = input.toUpperCase();//to allow for stuff like ClEar
		if(upercaseInput.contains(" = ")) {
			//have quotes? text cell
			String[] cmdArray = input.split(" ");
			if(input.contains(" \" ")) {
				//send constructor string between the "
				TextCell newtext = new TextCell(input.substring(input.indexOf(" \" ")+3,input.lastIndexOf("\"")));
				//echo input if invalid loc given
				if(!Grid.isIndex(cmdArray[0])) {
					return(input);
				}
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newtext;
				return("Defined text cell at " + cmdArray[0]);
				
			}else if(input.contains("/") && !input.contains("(")){//contains /? date cell
				
				String date = cmdArray[2];
				DateCell newdate = new DateCell(date);
				//test index
				if(!Grid.isIndex(cmdArray[0])) {
					return(input);
				}
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newdate;
				return("Defined date cell at " + cmdArray[0]);
				
			}else if(input.contains("(")&&input.contains(")")){//contains ()? formula cell
				int[] loc = Grid.strToIndex(cmdArray[0]);
				//legacy test index
				if(loc[0] != -1 &&loc[1] != -1) {
					//get text between ()
					String a = input.substring(input.indexOf("(")+1, input.lastIndexOf(")"));
					FormulaCell newform = new FormulaCell(a);
					CellGrid.grid[loc[0]][loc[1]] = newform;
					return("Defined formula cell at " + cmdArray[0]);
				}else {
					return(input);
				}
			}else{//else cell
				
				String num = cmdArray[2];
				Cell newcell = new Cell();
				//if double use double parser, else use int parser
				if(num.contains(".")) {
					newcell.setValue(Double.parseDouble(num));
				}else {
					newcell.setValue(Integer.parseInt(num));
				}
				//test index
				if(!Grid.isIndex(cmdArray[0])) {
					return(input);
				}
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newcell;
				return("Defined cell at " + cmdArray[0]);
				
			}
		//else check if its a cell index
		//if is then we fetch display value from cell and print
		}else if(upercaseInput.contains("CLEAR")) {
			String[] cmdArray = input.split(" ");
			if(!Grid.isIndex(cmdArray[1])) {
				return(input);
			}
			int[] loc = Grid.strToIndex(cmdArray[1]);
			CellGrid.grid[loc[0]][loc[1]] = new Cell();
			return("Cleared cell at " + cmdArray[0]);
		}else if(upercaseInput.contains("SORT")) {
			//time to get our 2 indexes
			String[] strings = upercaseInput.split(" ");
			int[] index = {-1,-1};
			int[] index2 = {-1,-1};
			for(int i = 0; i < strings.length; i++) {
				if(Grid.isIndex(strings[i])) {
					if(index[0] == -1) {
						index = Grid.strToIndex(strings[i]);
					}else if(index2[0] == -1) {
						index2 = Grid.strToIndex(strings[i]);
					}else {
						return(input);
					}
				}
			}
			if(!Grid.isIndex(index)||!Grid.isIndex(index)) {
				return(input);
			}
			//we hopefully have 2 indexs.
			//now if sort a then we do thing else we do sort b
			if(upercaseInput.contains("SORTA")){
				Grid.sorta(index, index2);
			}else {
				Grid.sortd(index, index2);
			}
			return("Sorted Elements!");
		}else if(Grid.isIndex(upercaseInput)){
			int[] loc = Grid.strToIndex(upercaseInput);
			return(Cell.printLikeCell(CellGrid.grid[loc[0]][loc[1]].displayValue));
		}
		return input;//echo input if all else fails
	}
}