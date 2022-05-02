package packcalc;

import java.util.ArrayList;

public class FormulaCell extends Cell{
	
	ArrayList<String> formula = new ArrayList<String>();
	
	public String toString() {
		Integer value = computeValue();
		return value.toString();
	}

	public FormulaCell(String substring) {
		
	}

	public int computeValue() {
		int output = 0;
		//find first / or *
		for(int i = 0; i < formula.size(); i++) {
			if(formula.get(i) == "*" || formula.get(i) == "/") {
				if(formula.get(i) == "*") {
					if(isValue(formula.get(i-1)) && isValue(formula.get(i+1))) {
						formula.set(i,((Integer)(valuefy(formula.get(i-1)) * valuefy(formula.get(i-1)))).toString());
						//[a,a,a,A,B,C,a,a]
						// 0 1 2 3 4 5 6 7
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,B,C,a,a]
						// 0 1 2 3 4 5 6
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,C,a,a]
						// 0 1 2 3 4 5
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,a,a]
						// 0 1 2 3 4
						//         ^
						//
					}
				}else {
					if(isValue(formula.get(i-1)) && isValue(formula.get(i+1))) {
						formula.set(i,((Integer)(valuefy(formula.get(i-1)) / valuefy(formula.get(i-1)))).toString());
						formula.remove(i-1);
						formula.remove(i-1);
						formula.remove(i-1);
					}
				}
			}
		}
		//then find + or - 
		for(int i = 0; i < formula.size(); i++) {
			if(formula.get(i) == "+" || formula.get(i) == "-") {
				if(formula.get(i) == "+") {
					if(isValue(formula.get(i-1)) && isValue(formula.get(i+1))) {
						formula.set(i,((Integer)(valuefy(formula.get(i-1)) + valuefy(formula.get(i-1)))).toString());
						//[a,a,a,A,B,C,a,a]
						// 0 1 2 3 4 5 6 7
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,B,C,a,a]
						// 0 1 2 3 4 5 6
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,C,a,a]
						// 0 1 2 3 4 5
						//         ^
						//
						formula.remove(i-1);
						//[a,a,a,a,a]
						// 0 1 2 3 4
						//         ^
						//
					}
				}else {
					if(isValue(formula.get(i-1)) && isValue(formula.get(i+1))) {
						formula.set(i,((Integer)(valuefy(formula.get(i-1)) - valuefy(formula.get(i-1)))).toString());
						formula.remove(i-1);
						formula.remove(i-1);
						formula.remove(i-1);
					}
				}
			}
		}
		output = ((Integer)formula.get(0)).toString();
		return output;
	}

	private Integer valuefy(String string) {
		return 0;
	}

	private boolean isValue(String string) {
		int[] index = Grid.strToIndex(string);
		if(index[0] != -1 &&index[1] != -1) {
			return(true);
		}else if(string.parsa){
			
		}
		return false;
	}
}
