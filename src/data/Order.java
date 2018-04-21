package data;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
/** 
 * <h1>Order<h1/>
 * <p>This class contains a setDate method and 2 overloaded constructors. It can be instantiated to record the orders made 
 * in the restaurant.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class Order {
	
	private SimpleStringProperty table = new SimpleStringProperty("2");
	private SimpleStringProperty dateTime = new SimpleStringProperty("12/16/16 at 06:20:29");
	private SimpleStringProperty items = new SimpleStringProperty("1xSteak 2xSoup 2xCocaCola");
	private SimpleStringProperty cost = new SimpleStringProperty("£24.00");
	private SimpleStringProperty requests = new SimpleStringProperty("I would like the steak done very well, burned to a cinder.");
	private SimpleStringProperty comments = new SimpleStringProperty("Very Tastey meal, we really really enjoyed it, it was so damn good. Yum yum!");
	
	public Order(){
		super();
	}
	
	/** Takes the table number, ordered items, cost, special requests and the comments from the
	 *  LayoutController when an order has been placed.
	 * @param table
	 * @param items
	 * @param cost
	 * @param requests
	 * @param comments
	 */
	public Order(String table, String items, String cost, String requests, String comments){
		super();
		this.table = new SimpleStringProperty(table);
		setDate(); 
		this.items = new SimpleStringProperty(items);
		this.cost = new SimpleStringProperty(cost);
		this.requests = new SimpleStringProperty(requests);
		this.comments = new SimpleStringProperty(comments);
	}
			
	public String getTable() {
		return table.get();
	}



	public void setTable(String table) {
		this.table = new SimpleStringProperty(table);
	}



	public String getDateTime() {
		return dateTime.get();
	}



	public void setDateTime(String dateTime) {
		this.dateTime = new SimpleStringProperty(dateTime);
	}



	public String getItems() {
		return items.get();
	}



	public void setItems(String items) {
		this.items = new SimpleStringProperty(items);
	}



	public String getCost() {
		return cost.get();
	}



	public void setCost(String cost) {
		this.cost = new SimpleStringProperty(cost);
	}



	public String getRequests() {
		return requests.get();
	}



	public void setRequests(String requests) {
		this.requests = new SimpleStringProperty(requests);
	}



	public String getComments() {
		return comments.get();
	}



	public void setComments(String comments) {
		this.comments = new SimpleStringProperty(comments);
	}


/** Creates a date object and uses it to time-stamp the order. */
	void setDate(){
		
		Date rightNow = new Date();
		String date = String.format("%tD%n", rightNow);
		date = date.substring(0, (date.length()-1));
		String[] timeFormat= (rightNow).toString().split(" ");
		
		
		 String time = timeFormat[3];
		 this.dateTime = new SimpleStringProperty(time+" on " + date);
		 
		 
	}
}
