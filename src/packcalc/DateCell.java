package packcalc;

public class DateCell extends Cell{
	int month = 0;
	int day = 0;
	int year = 0;
	//this should just be a text cell but whatever
	
	public DateCell() {
		this(0, 0, 0);
	}
	
	public DateCell(int month,int day,int year){
		this.month = month;
		this.day = day;
		this.year = year;
		String format = month%100 + "//"+ day%100 + "//"+ year; //cut or pad not called, string by default is too small
		super.displayValue = format;
	}
}
