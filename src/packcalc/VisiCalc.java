//don't forget header later
//https://ask4knowledgebase.com/questions/30421875/can-t-connect-to-any-uri-error-while-commiting-code-from-eclipse-to-git-repository
//https://www.jitendrazaa.com/blog/salesforce/authenticate-git-using-ssh-protocol-with-eclipse-and-egit-salesforce/
//https://www.jitendrazaa.com/blog/salesforce/salesforce-git-eclipse-egit-better-and-distributed-source-control/
package packcalc;
import java.util.*;
public class VisiCalc {

	public static void main(String[] args) {
		Boolean done = false;
		System.out.println("Welcome to Visual Calculator.");
		//scanner and grid define
		Scanner in = new Scanner(System.in);
		String input = "";
		Grid CellGrid = new Grid();
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
				}else if(input.equalsIgnoreCase("strDemo")) {
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
				}else {
					//echo input if not print or has =
					parseInput(input);
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
		if(input.contains(" = ")) {
			//have quotes? text cell
			String[] cmdArray = input.split(" ");
			if(input.contains(" \" ")) {
				
			}else if(input.contains("//")){//contains /? date cell
			}else if(input.contains("(")&&input.contains(")")){//contains ()? formula cell
			}else{//else cell
				
			}
		//else check if its a cell index
			//if is then we fetch display value from cell and print
		}
		return input;//echo input if all else fails
	}

}
