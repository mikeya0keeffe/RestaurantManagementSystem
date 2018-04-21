package data;

import data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** 
 * <h1>Restaurant<h1/>
 * <p>Abstract class holding the data for the restaurant as well as its getters and setters. There are methods to add
 * and remove items from the menu and for setting up the number of tables.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public abstract class Restaurant {
	private static String name;
	
	
	static private ObservableList<String> menu = FXCollections.observableArrayList("Pea Soup","Carrots","Rump Steak","Chocolate Cake","CocaCola");
	static private ObservableList<String> menuDescription = FXCollections.observableArrayList("Soup of the day","Vegetables","12 day aged beef","Chocolate cake with cream;330ml can");
	static private ObservableList<String> course = FXCollections.observableArrayList("Starter","Starter","Main","Dessert","Drink");
	static private ObservableList<Double> price = FXCollections.observableArrayList(5.0,6.5,13.0,4.0,2.5);;


	public static ObservableList<Staff> staffList = FXCollections.observableArrayList(new Staff("Jones","Tim","Employee","£20,000","TJones","Past4"), new Staff("Magoo","Michael","Manager","£40,000","MMagoo","First5"));
	static Order oldOrder = new Order();
	static Order oldOrder2 = new Order();
	static Order oldOrder3 = new Order();
	static Order oldOrder4 = new Order();
	
	static{ oldOrder.setDateTime("17:55:13 on 12/17/16");
	oldOrder2.setDateTime("13:55:13 on 10/01/16");
	oldOrder3.setDateTime("01:42:45 on 09/10/15");
	oldOrder4.setDateTime("19:15:01 on 12/17/16");
	}
	/** Order list is public access as the list is regularly referenced. The individual fields in the objects contained are private
	 * and have getters and setters. */
	public static ObservableList<Order> orderList = FXCollections.observableArrayList(oldOrder, oldOrder2, oldOrder3, oldOrder4);
	private static Staff activeStaffMember = (staffList.get(0));
	private static int numberOfTables = 14;
	private static int rows = 4;
	private static int columns = 4;
	
	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		Restaurant.rows = rows;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		Restaurant.columns = columns;
	}

	public static int getNumberOfTables() {
		return numberOfTables;
	}

	public static void setNumberOfTables(int numberOfTables) {
		Restaurant.numberOfTables = numberOfTables;
		setTables();
	}


	public static Table[] tables;
	
	public static Staff getActiveStaffMember() {
		return activeStaffMember;
	}

	public static void setActiveStaffMember(Staff activeStaffMember) {
		Restaurant.activeStaffMember = activeStaffMember;
	}
	
	public static ObservableList<String> getMenu(){
		return menu;
	}
	
	public static void setmenu(ObservableList<String> menu){
		Restaurant.menu = menu;
	}

	public static Table[] getTables() {
		return tables;
	}

	/** Creates an array for the tables in the restaurant by calling the Table class constructor. Stores them
	 * as an instance variable of this class.
	 */
	public static void setTables() {
		tables = new Table[numberOfTables];
		for(int t = 0; t < numberOfTables; t++){
		tables[t] = new Table();
		}
	}
	
	/**
	 * Adds a menu item of to the order list of the specified table in the tables array.
	 * @param tableNumber
	 * @param menuItem
	 */
	public static void addOrder(int tableNumber, int menuItem){
		tables[tableNumber-1].addToBill(new OrderItem(tableNumber, menu.get(menuItem), price.get(menuItem), menuDescription.get(menuItem)));
	}
	
	/**
	 * Removes the specified menu item form the specified table in the Table array.
	 * @param tableNumber
	 * @param menuItem
	 */
	public static void removeOrder(int tableNumber, OrderItem menuItem){
		tables[tableNumber-1].removeFromBill(menuItem);
	}
	
	
	public static ObservableList<OrderItem> getTableOrder(int tableNumber){
		return tables[tableNumber-1].getOrders();
	}
	
	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Restaurant.name = name;
	}
	
	public static ObservableList<String> getMenuDescription() {
		return menuDescription;
	}

	public static void setMenuDescription(ObservableList<String> menuDescription) {
		Restaurant.menuDescription = menuDescription;
	}
	
	public static ObservableList<String> getCourse() {
		return course;
	}

	public static void setCourse(ObservableList<String> course) {
		Restaurant.course = course;
	}
	
	public static ObservableList<Double> getPrice() {
		return price;
	}

	public static void setPrice(ObservableList<Double> price) {
		Restaurant.price = price;
	}

 /** Accepts the details of the new menu item from the layout controller. The item will be placed in the 
  * correct portion of the menu depending on its course label. The order is starters, mains, desserts, drinks.
 * @param newItem
 * @param newPrice
 * @param newDescription
 * @param newCourse
 */
public static void appendMenu(String newItem, Double newPrice, String newDescription, String newCourse) {
	int ind = 0;
	 if(!course.contains(newCourse)){
		 if(newCourse.equals("Starter")){
			 ind = 0;
		 }else if(newCourse.equals("Main")){
			 if(course.contains("Starter")){
				 ind = course.lastIndexOf("Starter")+1;
			 }else{
				 ind = 0;
			 }
		 }else if(newCourse.equals("Dessert")){
			 if(course.contains("Drink")){
				 ind = course.indexOf("Drink");
			 }else{
				 ind = course.size();
			 }
		 }else if(newCourse.equals("Drink")){
			 ind = course.size();
		 }
		 menu.add(ind, newItem);
		 course.add(ind,newCourse);
		 price.add(ind,newPrice);
		 menuDescription.add(ind,newDescription);
	 }else{
		 ind = course.indexOf(newCourse);
		menu.add(ind, newItem);
		course.add(ind, newCourse);
	 	price.add(ind, newPrice);
	 	menuDescription.add(ind, newDescription);
	 } /* The logic ensures that items are added in the order starter,
	  	main, dessert, drinks as per the industry standard. */ 
}

/**Accepts a string containing the item name from the menu and finds the index of that item before
 * removing its fields.
 * @param oldItem
 */
public static void removeMenuItem(String oldItem) {
	int indrem = menu.indexOf(oldItem);
	menu.remove(indrem);
	course.remove(indrem);
	price.remove(indrem);
	menuDescription.remove(indrem);
}


}
	
	

