package packcalc;

public class TextCell extends Cell{
	//self explanatory, no comments needed in my eyes
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
	
	public int type() {
		return 1;
	}
}
