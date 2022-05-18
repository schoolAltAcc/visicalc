package packcalc;

import java.util.ArrayList;
import java.util.Scanner;

public class FormulaCell extends Cell{
	
	//note to mulvaney
	//in future classes please put more emphasis on the fact that formula cell(actually maybe even cell) must deal with DECIMALS
	//sincerly me at a disagreable hour
	
	//note 2 to mulvaney, please emphasise that changing your entire program from int to double is OPTIONAL
	
	ArrayList<String> formula = new ArrayList<String>();
	
	public String toString() {
		Double value = computeValue(formula);
		super.setValue(value);
		return super.toString();
	}

	public FormulaCell(String substring) {
		formula = strToArrayList(substring);
		super.setValue(computeValue(formula));
	}
	
	//takes a string and makes it a array list of commands
	private ArrayList<String> strToArrayList(String substring) {
		ArrayList<String> output = new ArrayList<String>();
		Scanner parser = new Scanner(substring);
		
		while(parser.hasNext()) {
			String temp = parser.next();
			if(temp.equals("SUM") || temp.equals("AVG")) {
				output.add(temp + " " + parser.next() + " " + parser.next() + " " + parser.next()); //no input error check, not my fault if issue
			}else {
				output.add(temp);
			}
			
		}
		parser.close();
		
		return output;
	}

	//evaluates expression
	public Double computeValue(ArrayList<String> formulaStrList) {
		Double output = 0.0;
		while(formulaStrList.toString().contains("/") || formulaStrList.toString().contains("*")) {
			//find first / or * and evaluates
			mult_Div(formulaStrList);
		}
		while(formulaStrList.toString().contains("+") || (formulaStrList.toString().contains("-") && formulaStrList.size() >= 3)) {
			//then find + or -  and evaluates
			add_Sub(formulaStrList);
		}
		output = valuefy(formulaStrList.get(0));
		return output;
	}
	
	//methods of horror lie beyond -----------------------------------------------
	//written a few days ago without comments, im not sure i can explain what happens
	private void add_Sub(ArrayList<String> formulaStrList) {
		//loop through formulastrlist
		for(int i = 0; i < formulaStrList.size(); i++) {
			//if operator detected
			if(formulaStrList.get(i).equals("+") || formulaStrList.get(i).equals("-")) {
				//check which operater it is
				if(formulaStrList.get(i).equals("+")) {
					//if plus and there are 2 values besides it
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						//set the + operator to the evaluation of value1 + value2
						formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) + valuefy(formulaStrList.get(i+1)))).toString());
						//remove value1
						formulaStrList.remove(i-1);
						//remove value2
						formulaStrList.remove(i);
					}
				}else {
					//if minus and there are 2 values besides it
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						//set the + operator to the evaluation of value1 - value2
						formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) - valuefy(formulaStrList.get(i+1)))).toString());
						//remove redundant values
						formulaStrList.remove(i-1);
						formulaStrList.remove(i);
					}
				}
			}
		}
	}

	private void mult_Div(ArrayList<String> formulaStrList) {	
			//same as add_sub so look there for comments, but instead of +- its */
			for(int i = 0; i < formulaStrList.size(); i++) {
				if(formulaStrList.get(i).equals("*") || formulaStrList.get(i).equals("/")) {
					if(formulaStrList.get(i).equals("*")) {
						if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
							formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) * valuefy(formulaStrList.get(i+1)))).toString());
							formulaStrList.remove(i-1);
							formulaStrList.remove(i);
						}
					}else {
						if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
							formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) / valuefy(formulaStrList.get(i+1)))).toString());
							formulaStrList.remove(i-1);
							formulaStrList.remove(i);
						}
					}
				}
			}
		}
	//methods of horror lie above ------------------------------------------------
	
	//it takes a index string or an avg sum expression and either gets an elements value or evaluates sum/avg
	private double valuefy(String string) {
		if(string.contains("AVG") || string.contains("SUM")) {
			String[] strings = string.split(" ");
			int[] index = {-1,-1};
			int[] index2 = {-1,-1};
			//for loop to find 2 indexs
			for(int i = 0; i < strings.length; i++) {
				if(Grid.isIndex(strings[i])) {
					if(index[0] == -1) {
						index = Grid.strToIndex(strings[i]);
					}else if(index2[0] == -1) {
						index2 = Grid.strToIndex(strings[i]);
					}else {
						//third index, time to error
						System.out.println("Error: Inserted too many indexes");
						return 0.0;
					}
				}
			}
			//gets the values of elements
			double[] values = Grid.fetchElementsValues(index,index2);
			//get total
			double total = 0;
			for(Double e : values) {
				total += e;
			}
			//if sum return total otherwise return average
			if(string.contains("SUM")) {
				return total;
			}else{
				return total/values.length;
			}
		}
		//if not value or sum/avg then it has no place here
		if(!isValue(string)) {
			return 0;
		}
		//is value
		int[] index = Grid.strToIndex(string);
		if(index[0] != -1 && index[1] != -1) {
			//return value
			return VisiCalc.CellGrid.grid[index[0]][index[1]].getValue();
		}else {
			//this fixed a error at some point, probably redundant now but im too scared to take it out
			return Double.parseDouble(string);
		}
	}

	//does to index and sees if its valid
	private boolean isValue(String string) {
		int[] index = Grid.strToIndex(string);
		if(index[0] == -1 || index[1] == -1) {
			//dont remeber why this is here
			for(char e : string.toCharArray()) {
				if(!Character.isDigit(e) && e != '-'&& e != '.') {
					//if foreign character (doesn't belong in number)
					return false;
				}
			}
		}
		return true;
	}
	
	public int type() {
		return 3;
	}
}
