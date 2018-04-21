package data;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** 
 * <h1>Table<h1/>
 * <p>Stores simple data about each table such as the table number and the order list for that table. 
 * The boolean hasOrdered records whether or not this table has ordered.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class Table {
	/**This static variable is incremented each time a Table object is constructed.*/
	private static int noOfTables = 0;
	private int tableNo;
	private ObservableList<OrderItem> orders = FXCollections.observableArrayList();
	private boolean hasOrdered = false;
	
	/** */
	public Table(){
		this.tableNo = ++noOfTables;
	}
	
	/**Adds the menuItem to this tables list of orders.
	 * @param menuItem
	 */
	public void addToBill(OrderItem menuItem){
		this.orders.add(menuItem);
	}
	
	/**Removes the menuItem from this table's list of orders.
	 * @param menuItem
	 */
	public void removeFromBill(OrderItem menuItem){
		    this.orders.removeAll(menuItem);
	
	}
	
	public ObservableList<OrderItem> getOrders(){
		return this.orders;
	}
	
	/**Switches the value of the boolean once an item is added to this table's order list.
	 * 
	 */
	public void switchOrderstatus(){
		this.hasOrdered = true;
	}
	
	public boolean getOrderStatus(){
		return this.hasOrdered;
	}

}
