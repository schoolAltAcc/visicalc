package packcalc;

public class TextCell extends Cell{
	String textValue = "";
	
	public TextCell(String input) {
		textValue = input;
		displayValue = TextCell.cutOrPad(input);
	}
	
	public TextCell() {
		this(Cell.generateSpaces(textLength));
	}
	
	public static String cutOrPad(String input) {
		return(TextCell.cutOrPad(input,textLength));
	}
	
	public static String cutOrPad(String input,int length) {
		//lmao figure out what happened later, error lurking around here
		String output = input;
		if(input.length() > length || input.length() < length) {
			if(input.length() > length) {
				//cutoff string for display text.substring(0, text.length() - 2)
				int difference = input.length() - length;
				difference += 2;
				output = '\"' + input.substring(0, input.length() - difference) + '\"';
			}else {
				//pad string for display String.format("%1$-" + length + "s", inputString).replace(' ', '0')
				int difference = length - input.length();
				difference -= 2;
				output = '\"' +input + '\"' + Cell.generateSpaces(difference);
			}
		}else {
			output = '\"' +input+ '\"';
		}
		return output;
	}
}
