package backend_classes;

import java.util.Date;
//import java.util.Calendar;

public class UserProfile {
	private final double inchesToCm = 2.54;
	private final double lbsToGrams = 454;
	
	private static int accountID = 0;
	
	private String firstName;
	private String lastName;
	private Date DOB;
	//private Date currentDate;
	private int age;
	private double height;			//Measured in cm
	private double weight;			//Measured in kg
	//Add picture?
	
	public UserProfile() {
		accountID++;
		//Make currentDate using CustomDate class
		
	}
	
	//Setters
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public void setDOB(Date date) {
		this.DOB = date;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
	
	public void setWeight(double weight) {
		this.weight = weight;
	}
	//
	
	
	//Getters
	public int getAccountID() {
		return accountID;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public Date getDOB() {
		return this.DOB;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public double getHeight() {
		return this.height;
	}
	
	public double getWeight() {
		return this.weight;
	}
	//
	
	
	//General Methods
	
	//Converts height from imperial to metric
	public double convertHeightToMetric(double feet, double inches) {
		double heightCm;
		
		inches += feet*12;
		heightCm = inches*this.inchesToCm;
		
		return heightCm;
	}
	
	//Converts weight from imperial to metric
	public double convertWeightToMetric(double weightLbs) {
		double weightKg = weightLbs*this.lbsToGrams;
		return weightKg;
	}
	
	//Gets age based on DOB
	public int calculateAge() {
		
		return 0;
	}
}
