package packcalc;

public class Cell implements Comparable<Cell>{
	static int textLength = 10;
	String displayValue = generateSpaces(textLength);
	private double value = 0;
	
	public Cell() {
		//we dont do anything
	}
	
	public Cell(int value) {
		setValue(value);
	}
	
	public Cell(double d) {
		setValue(d);
	}

	public String toString(){
		return("|"+displayValue+"|");
	}
	
	public static String generateSpaces(int length) {
		return String.format("%"+ length +"s", " ");
	}
	
	public static String cutOrPad(String input) {
		return(cutOrPad(input,textLength));
	}
	
	public static String cutOrPad(String input,int length) {
		String output = input;
		if(input.length() > length || input.length() < length) {
			if(input.length() > length) {
				//cutoff string for display text.substring(0, text.length() - 2)
				int difference = input.length() - length;
				output = input.substring(0, input.length() - difference);
			}else {
				//pad string for display String.format("%1$-" + length + "s", inputString).replace(' ', '0')
				int difference = length - input.length();
				output = input + Cell.generateSpaces(difference);
				//cell.generatespaces.ceil, cell.generatespaces.floor
			}
		}
		return output;
	}
	
	public static String printLikeCell(String input) {
		return("|"+cutOrPad(input)+"|");
	}
	
	public void setValue(Integer x) {
		this.value = x;
		this.displayValue = cutOrPad(x.toString());
	}
	
	public void setValue(int x) {
		Integer y = x;
		this.value = y;
		this.displayValue = cutOrPad(y.toString());
	}
	
	public double getValue() {
		return(value);
	}
	
	public void setValue(Double x) {
		if(x%1==0) {
			setValue(x.intValue());
		}else {
			this.value = x;
			this.displayValue = cutOrPad(x.toString());
		}
	}
	
	public int type() {
		if(this.displayValue.equals(generateSpaces(textLength))){
			return 4;
		}
		return 0;
	}
	
	public int compareTo(Cell other) {
		//number 0, text 1, date 2, formula 3, empty 4
		int x = this.type();
		int y = other.type();
		//return pos if in order
		//zero if equal
		//-1 if out of order
		if(x==y) {
			if(x==4) {
				return 0;
			}
			if(x == 0) {
				return (int) (other.value-this.value);
			}
			if(x == 1) {
				TextCell t = (TextCell)this;
				TextCell r = (TextCell)other;
				return (int) (r.textValue.length()-t.textValue.length());
			}
			if(x == 2) {
				DateCell t = (DateCell)this;
				DateCell r = (DateCell)other;
				if(r.year-t.year == 0) {
					if(r.month-t.month == 0) {
						return(r.day-t.day);
					}
					return(r.month-t.month);
				}
				return(r.year-t.year);
			}
			if(x ==3){
				FormulaCell t = (FormulaCell)this;
				FormulaCell r = (FormulaCell)other;
				return(r.displayValue.length()-t.displayValue.length());//checks previously evaluated value, not evaluating new value
			}
		}
		
		return(x-y);
	}
}
