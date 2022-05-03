package packcalc;

import java.util.ArrayList;
import java.util.Scanner;

public class FormulaCell extends Cell{
	
	//note to mulvaney
	//in future classes please put more emphasis on the fact that formula cell(actually maybe even cell) must deal with DECIMALS
	//sincerly me at 12 am
	
	ArrayList<String> formula = new ArrayList<String>();
	
	public String toString() {
		Integer value = computeValue(formula);
		return value.toString();
	}

	public FormulaCell(String substring) {
		formula = strToArrayList(substring);
		super.setValue(computeValue(formula));
	}
	
	//WHY IS THIS METHOD NON EXISTANT I REMEBER WRITING IT BEFORE >:(
	private ArrayList<String> strToArrayList(String substring) {
		ArrayList<String> output = new ArrayList<String>();
		Scanner parser = new Scanner(substring);
		
		while(parser.hasNext()) {
			output.add(parser.next());
		}
		parser.close();
		
		return output;
	}

	//some kind of infinite loop occurs
	public int computeValue(ArrayList<String> formulaStrList) {
		int output = 0;
		while(formulaStrList.toString().contains("/") || formulaStrList.toString().contains("*")) {
			//find first / or *
			formulaStrListMultDivOperation(formulaStrList);
		}
		while(formulaStrList.toString().contains("+") || formulaStrList.toString().contains("-")) {
			//then find + or - 
			formulaStrListAddSubOperation(formulaStrList);
		}
		output = valuefy(formulaStrList.get(0));
		return output;
	}
	
	//methods of horror lie beyond -----------------------------------------------
	private void formulaStrListAddSubOperation(ArrayList<String> formulaStrList) {
		for(int i = 0; i < formulaStrList.size(); i++) {
			if(formulaStrList.get(i).equals("+") || formulaStrList.get(i).equals("-")) {
				if(formulaStrList.get(i) == "+") {
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						formulaStrList.set(i,((Integer)(valuefy(formulaStrList.get(i-1)) + valuefy(formulaStrList.get(i-1)))).toString());
						//[a,a,a,A,B,C,a,a]
						// 0 1 2 3 4 5 6 7
						//         ^
						//
						formulaStrList.remove(i-1);
						//[a,a,a,B,C,a,a]
						// 0 1 2 3 4 5 6
						//         ^
						//
						formulaStrList.remove(i-1);
						//[a,a,a,C,a,a]
						// 0 1 2 3 4 5
						//         ^
						//
						formulaStrList.remove(i-1);
						//[a,a,a,a,a]
						// 0 1 2 3 4
						//         ^
						//
					}
				}else {
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						formulaStrList.set(i,((Integer)(valuefy(formulaStrList.get(i-1)) - valuefy(formulaStrList.get(i-1)))).toString());
						//formDemo error in logic bellow, middle element i gets removed due to being middle and only having 1 element before it. Fix issue at school
						formulaStrList.remove(i-1);
						formulaStrList.remove(i-1);
						//formulaStrList.remove(i-1);
					}
				}
			}
		}
		//error
	}

	private void formulaStrListMultDivOperation(ArrayList<String> formulaStrList) {	
			for(int i = 0; i < formulaStrList.size(); i++) {
				if(formulaStrList.get(i).equals("*") || formulaStrList.get(i).equals("/")) {
					if(formulaStrList.get(i) == "*") {
						if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
							formulaStrList.set(i,((Integer)(valuefy(formulaStrList.get(i-1)) * valuefy(formulaStrList.get(i-1)))).toString());
							//[a,a,a,A,B,C,a,a]
							// 0 1 2 3 4 5 6 7
							//         ^
							//
							formulaStrList.remove(i-1);
							//[a,a,a,B,C,a,a]
							// 0 1 2 3 4 5 6
							//         ^
							//
							formulaStrList.remove(i-1);
							//[a,a,a,C,a,a]
							// 0 1 2 3 4 5
							//         ^
							//
							formulaStrList.remove(i-1);
							//[a,a,a,a,a]
							// 0 1 2 3 4
							//         ^
							//
						}
					}else {
						if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
							formulaStrList.set(i,((Integer)(valuefy(formulaStrList.get(i-1)) / valuefy(formulaStrList.get(i-1)))).toString());
							formulaStrList.remove(i-1);
							formulaStrList.remove(i-1);
							//formulaStrList.remove(i-1);
						}
					}
				}
			}
		}
	//methods of horror lie above ------------------------------------------------
	
	private Integer valuefy(String string) {
		if(!isValue(string)) {
			return 0;
		}
		//is value
		int[] index = Grid.strToIndex(string);
		if(index[0] != -1 && index[1] != -1) {
			return VisiCalc.CellGrid.grid[index[0]][index[1]].getValue();
		}else {
			return Integer.parseInt(string);
		}
	}

	private boolean isValue(String string) {
		int[] index = Grid.strToIndex(string);
		if(index[0] == -1 || index[1] == -1) {
			for(char e : string.toCharArray()) {
				if(!Character.isDigit(e)) {
					return false;
				}
			}
		}
		return true;
	}
}
