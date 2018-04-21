package data;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
/** 
 * <h1>Staff<h1/>
 * <p>This class contains overloaded constructors for instantiating a new staff member with or without 
 * a user name and password. It contains getters and setters for the instance variables of a member of 
 * staff and a method for adding data to their data log. <p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class Staff {
	private SimpleStringProperty lastName;
	private SimpleStringProperty firstName;
	private SimpleStringProperty position;
	private SimpleStringProperty salary;
	private SimpleStringProperty userName;
	private SimpleStringProperty password;
	private SimpleStringProperty dataLog;
	 
	public String getUsername() {
		return userName.get();
	}

	public void setUsername(String userName) {
		this.userName = new SimpleStringProperty(userName);
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password = new SimpleStringProperty(password);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(SimpleStringProperty lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstname(SimpleStringProperty firstname) {
		this.firstName = firstname;
	}

	public String getPosition() {
		return position.get();
	}

	public void setPosition(SimpleStringProperty position) {
		this.position = position;
	}

	public String getSalary() {
		return salary.get();
	}

	public void setSalary(SimpleStringProperty salary) {
		this.salary = salary;
	}
	
	/**Adds a new entry to the data log variable which can be displayed in the Settings window.
	 * @param action
	 */
	public void appendDataLog(String action){
		Date now = new Date();
		String newEntry = dataLog.get() + lastName.get() + ", " + firstName.get() + " " + action + " at " + String.format("%tr", now) + " on "+ String.format("%tD%n", now);
		dataLog = new SimpleStringProperty(newEntry);
	} // Adds the activity to the DataLog String and makes a new SimpleStringProperty.
	
	public String getDataLog(){
		return dataLog.get();
	}

	/**Creates a new member of staff without login details.
	 * @param lastName
	 * @param firstName
	 * @param position
	 * @param salary
	 */
	public Staff(String lastName, String firstName, String position, String salary) {
		super();
		this.lastName = new SimpleStringProperty(lastName);
		this.firstName =  new SimpleStringProperty(firstName);
		this.position =  new SimpleStringProperty(position);
		this.salary =  new SimpleStringProperty(salary);
	}
	/**Creates a new member of staff with login details.
	 * @param lastName
	 * @param firstName
	 * @param position
	 * @param salary
	 * @param userName
	 * @param password
	 */
	public Staff(String lastName, String firstName, String position, String salary,String userName, String password) {
		super();
		this.lastName = new SimpleStringProperty(lastName);
		this.firstName =  new SimpleStringProperty(firstName);
		this.position =  new SimpleStringProperty(position);
		this.salary =  new SimpleStringProperty(salary);
		this.userName =  new SimpleStringProperty(userName);
		this.password =  new SimpleStringProperty(password);
		this.dataLog = new SimpleStringProperty("");
	}
	
}
