package backend_classes;

public class CustomDate {
	private int minute;
	private int hour;	
	private int day;
	private int month;
	private int year;
	private boolean isLeapYear;
	
	public CustomDate(int day, int month, int year) {
		this.year = year;
		
		//Calculate if year is leap year
		if(year % 4 == 0) {
			if(year % 100 != 0) {
				if(year % 400 == 0) {
					this.isLeapYear = true;
				}
			}
		} else {
			this.isLeapYear = false;
		}
		
		if(monthCheck(month) == true) {
			this.month = month;
			
			//Checks day
			switch(month) {
				case 2:
					if(this.isLeapYear) {
						if(dayCheck(day, 29))
							this.day = day;
						else
							this.day = 0;	//Signifies invalid day
					} else {
						if(dayCheck(day, 28))
							this.day = day;
						else
							this.day = 0;
						
						break;
					}
					
					break;
				
				case 4:
					if(dayCheck(day, 30))
						this.day = day;
					else
						this.day = 0;
					
					break;
					
				case 6:
					if(dayCheck(day, 30))
						this.day = day;
					else
						this.day = 0;
					
					break;
					
				case 9:
					if(dayCheck(day, 30))
						this.day = day;
					else
						this.day = 0;
					
					break;
					
				case 11:
					if(dayCheck(day, 30))
						this.day = day;
					else
						this.day = 0;
					
					break;
					
				default:
					if(dayCheck(day, 31))
						this.day = day;
					else
						this.day = 0;
					
					break;
			}
		} else {
			this.month = 0;		//Signifies invalid month
		}
		
	}
	
	//Setters
	
	//
	
	
	//Getters
	
	//
	
	
	//General Methods
	public boolean monthCheck(int month) {
		if(month >= 1 && month <= 12)
			return true;
		else
			return false;	
	}
	
	public boolean dayCheck(int day, int max) {
		if(day >= 1 && day <= max)
			return true;
		else
			return false;
	}
	
}
