//don't forget header later
//https://ask4knowledgebase.com/questions/30421875/can-t-connect-to-any-uri-error-while-commiting-code-from-eclipse-to-git-repository
//https://www.jitendrazaa.com/blog/salesforce/authenticate-git-using-ssh-protocol-with-eclipse-and-egit-salesforce/
//https://www.jitendrazaa.com/blog/salesforce/salesforce-git-eclipse-egit-better-and-distributed-source-control/
//WARNING NEED TO ADD A DECIMAL COMPATIBILITY AHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHGHG
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
				}else if(input.equalsIgnoreCase("formDemo")) {
					System.out.println(parseInput("a1 = 8"));
					System.out.println(parseInput("a2 = 7"));
					System.out.println(parseInput("a3 = ( A1 + A2 )"));
					System.out.println(parseInput("C1 = ( A3 - 14 / 7 + 8 * 3.14 )"));
					System.out.println(CellGrid);
				}else if(input.equalsIgnoreCase("formDemo2")) {
					System.out.println(parseInput("A1 = 3"));
					System.out.println(parseInput("A2 = 5"));
					System.out.println(parseInput("A3 = -10"));
					System.out.println(parseInput("B1 = ( A1 )"));
					System.out.println(parseInput("B2 = ( A2 + 100 )"));
					System.out.println(parseInput("B3 = ( A2 - A3 )"));
					System.out.println(parseInput("C9 = ( A1 * A2 * A3 )"));
					System.out.println(parseInput("C10 = ( A3 / A2 + A3 )")); //C10 = ( A1 / A2 + A3 )
					System.out.println(CellGrid);
				}else if(input.equalsIgnoreCase("sumavgDemo")) {
					System.out.println(parseInput("B5 = 11"));
					System.out.println(parseInput("B6 = 39"));
					System.out.println(parseInput("B7 = -400"));
					System.out.println(parseInput("C5 = 0"));
					System.out.println(parseInput("D5 = 7"));
					System.out.println(parseInput("E7 = ( SUM B5 - B7 )"));
					System.out.println(parseInput("E8 = ( AVG B5 - D5 )"));
					System.out.println(CellGrid);
					
				}else if(input.equalsIgnoreCase("clear")) {
					CellGrid.clearAll();
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
	
	public static String parseInput(String input){
		//if we have equals
		String upercaseInput = input.toUpperCase();//to allow for stuff like ClEar
		if(upercaseInput.contains(" = ")) {
			//have quotes? text cell
			String[] cmdArray = input.split(" ");
			if(input.contains(" \" ")) {
				
				TextCell newtext = new TextCell(input.substring(input.indexOf(" \" ")+3,input.lastIndexOf("\"")));
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newtext;
				return("Defined text cell at " + cmdArray[0]);
				
			}else if(input.contains("/") && !input.contains("(")){//contains /? date cell
				
				String date = cmdArray[2];
				DateCell newdate = new DateCell(date);
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newdate;
				return("Defined date cell at " + cmdArray[0]);
				
			}else if(input.contains("(")&&input.contains(")")){//contains ()? formula cell
				int[] loc = Grid.strToIndex(cmdArray[0]);
				if(loc[0] != -1 &&loc[1] != -1) {
					FormulaCell newform = new FormulaCell(input.substring(input.indexOf("(")+1, input.lastIndexOf(")")));
					CellGrid.grid[loc[0]][loc[1]] = newform;
					return("Defined formula cell at " + cmdArray[0]);
				}else {
					return("Index Error");
				}
			}else{//else cell
				
				String num = cmdArray[2];
				Cell newcell = new Cell(Integer.parseInt(num));
				int[] loc = Grid.strToIndex(cmdArray[0]);
				CellGrid.grid[loc[0]][loc[1]] = newcell;
				return("Defined cell at " + cmdArray[0]);
				
			}
		//else check if its a cell index
			//if is then we fetch display value from cell and print
		}else if(upercaseInput.contains("CLEAR")) {
			String[] cmdArray = input.split(" ");
			int[] loc = Grid.strToIndex(cmdArray[1]);
			CellGrid.grid[loc[0]][loc[1]] = new Cell();
			return("Cleared cell at " + cmdArray[0]);
		}
		return input;//echo input if all else fails
	}
}
