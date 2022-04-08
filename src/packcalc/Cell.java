package packcalc;

public class Cell {
	static final int textLength = 10;
	String displayValue = generateSpaces(textLength);
	private int value = 0;
	
	public Cell() {
		//we dont do anything
	}
	
	public Cell(int value) {
		setValue(value);
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
	
	public int getValue() {
		return(value);
	}
	/*just in case i messed up later
	if(input.length() > textLength || input.length() < textLength) {
		if(input.length() > textLength) {
			//cutoff string for display text.substring(0, text.length() - 2)
			int difference = input.length() - textLength;
			displayValue = input.substring(0, input.length() - difference);
		}else {
			//pad string for display String.format("%1$-" + length + "s", inputString).replace(' ', '0')
			int difference = textLength - input.length();
			displayValue = input + Cell.generateSpaces(difference);
		}
	}else {
		displayValue = input;
	}
	*/
}
