package data;

import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** 
 * <h1>OrderItem<h1/>
 * <p>This class provides the code to instantiate an individual item for display on the layout view.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class OrderItem {
	private SimpleIntegerProperty tableNumber;
	private SimpleStringProperty item;
	private SimpleIntegerProperty number;
	private SimpleDoubleProperty price;
	private SimpleStringProperty time;
	private SimpleStringProperty description;
	
	public String getDescription() {
		return description.get();
	}
	public void setDescription(String description) {
		this.description = new SimpleStringProperty(description);
	}
	
	/**Constructor takes 4 arguments to instantiate the order item and display it in the Layout tab for each
	 * of the tables.
	 * @param tableNumber
	 * @param item
	 * @param price
	 * @param description
	 */
	public OrderItem(Integer tableNumber, String item, Double price, String description) {
		super();
		this.tableNumber = new SimpleIntegerProperty(tableNumber);
		this.item = new SimpleStringProperty(item);
		this.price = new SimpleDoubleProperty(price);
		this.description = new SimpleStringProperty(description);
	}
	public String getItem() {
		return item.get();
	}
	public void setItem(String item) {
		this.item = new SimpleStringProperty(item);
	}
	
	public Double getPrice() {
		return price.get();
	}
	public void setPrice(Double price) {
		this.price = new SimpleDoubleProperty(price);
	}
	public String getTime() {
		return time.get();
	}
	
	public Integer gettableNumber(){
		return tableNumber.get();
	}
	
	
}
