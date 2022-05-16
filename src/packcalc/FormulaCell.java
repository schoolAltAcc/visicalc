package packcalc;

import java.util.ArrayList;
import java.util.Scanner;

public class FormulaCell extends Cell{
	
	//note to mulvaney
	//in future classes please put more emphasis on the fact that formula cell(actually maybe even cell) must deal with DECIMALS
	//sincerly me at 12 am
	
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
	
	//WHY IS THIS METHOD NON EXISTANT I REMEBER WRITING IT BEFORE >:(
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

	//some kind of infinite loop occurs
	public Double computeValue(ArrayList<String> formulaStrList) {
		Double output = 0.0;
		while(formulaStrList.toString().contains("/") || formulaStrList.toString().contains("*")) {
			//find first / or *
			formulaStrListMultDivOperation(formulaStrList);
		}
		while(formulaStrList.toString().contains("+") || (formulaStrList.toString().contains("-") && formulaStrList.size() >= 3)) {
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
				if(formulaStrList.get(i).equals("+")) {
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) + valuefy(formulaStrList.get(i+1)))).toString());
						formulaStrList.remove(i-1);
						formulaStrList.remove(i);
					}
				}else {
					if(isValue(formulaStrList.get(i-1)) && isValue(formulaStrList.get(i+1))) {
						formulaStrList.set(i,((Double)(valuefy(formulaStrList.get(i-1)) - valuefy(formulaStrList.get(i+1)))).toString());
						formulaStrList.remove(i-1);
						formulaStrList.remove(i);
					}
				}
			}
		}
		//error
	}

	private void formulaStrListMultDivOperation(ArrayList<String> formulaStrList) {	
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
	
	private double valuefy(String string) {
		if(string.contains("AVG") || string.contains("SUM")) {
			String[] strings = string.split(" ");
			int[] index = {-1,-1};
			int[] index2 = {-1,-1};
			for(int i = 0; i < strings.length; i++) {
				if(Grid.isIndex(strings[i])) {
					if(index[0] == -1) {
						index = Grid.strToIndex(strings[i]);
					}else if(index2[0] == -1) {
						index2 = Grid.strToIndex(strings[i]);
					}else {
						System.out.println("AHHHHHHHHHHH");
						return 0.0;
					}
				}
			}
			double[] values = Grid.fetchElements(index,index2);
			double total = 0;
			for(Double e : values) {
				total += e;
			}
			if(string.contains("SUM")) {
				return total;
			}else{
				return total/values.length;
			}
		}
		if(!isValue(string)) {
			return 0;
		}
		//is value
		int[] index = Grid.strToIndex(string);
		if(index[0] != -1 && index[1] != -1) {
			return VisiCalc.CellGrid.grid[index[0]][index[1]].getValue();
		}else {
			return Double.parseDouble(string);
		}
	}

	private boolean isValue(String string) {
		int[] index = Grid.strToIndex(string);
		if(index[0] == -1 || index[1] == -1) {
			for(char e : string.toCharArray()) {
				if(!Character.isDigit(e) && e != '-'&& e != '.') { //can cause error for #s like 1230-32
					return false;
				}
			}
		}
		return true;
	}
}
