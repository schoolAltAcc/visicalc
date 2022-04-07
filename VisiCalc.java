//Artem S.
//Ap comp sci
//Visicalc
//Checkpoint 2

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
				if(input.equalsIgnoreCase("print")) {
					//print grid
					System.out.println(CellGrid);
				}else if(input.indexOf(" = ") > -1) {
					//not done
					if(CellGrid.cmdType(input).equals("define")){
						//yet to be implemented
						System.out.println("Cell Defined");
					}else {
						//echo if not expression
						System.out.println(input+"\n");
					}
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
					System.out.println(input+"\n");
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

}
