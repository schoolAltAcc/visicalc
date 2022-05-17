package packcalc;

public class DateCell extends Cell{
	int month = 0;
	int day = 0;
	int year = 0000;
	public DateCell(){
		this(0,0,0);
	}
	public DateCell(int month,int day, int year) {
		this.month = month;
		this.day = day;
		this.year = year;
		super.displayValue = cutOrPad();
	}
	public DateCell(String input) {
		String[] epic = input.split("/");
		if(epic.length >= 3) {
			this.month = Integer.parseInt(epic[0]);
			this.day = Integer.parseInt(epic[1]);
			this.year = Integer.parseInt(epic[2]);
		}else {
			System.out.println("Error assigning date cell: split operation failure.");
		}
		super.displayValue = cutOrPad();
	}
	//make function to take in month day and year and give display string
	public String cutOrPad() {
		return String.format("%02d/%02d/%04d",this.month,this.day,this.year);
	}
	
	public int type() {
		return 2;
	}
}
