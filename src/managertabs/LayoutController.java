package managertabs;

import java.net.URL;
import java.util.ResourceBundle;

import data.Order;
import data.OrderItem;
import data.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
/** 
 * <h1>LayoutController<h1/>
 * <p>Class controls the Layout for Layout.fxml. Shows the table view and allows registering of orders
 * and the display of individual table orders in the TableView 'orderView'. Contains methods to add
 * and remove orders from this view as well as to collect and set the TextArea and Label values.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class LayoutController implements Initializable {

	private TabController tab;
	@FXML private TextField txtl;
	@FXML private Button btnl;
	
	private int CurrentTable = 0;
	
	@FXML private ComboBox<String> menuItems;
	
	@FXML private TableView<OrderItem> orderView;
	@FXML private TableColumn<OrderItem, String> item;
	@FXML private TableColumn<OrderItem, Double> price;
	@FXML private TableColumn<OrderItem, String> description;
	@FXML private Label total;
	@FXML private TextArea specialRequests;
	@FXML private TextArea comments;
	
	private ObservableList<OrderItem> CurrentOrder;
	
	private ObservableList<String> menu = Restaurant.getMenu();
	private ObservableList<OrderItem> blankList = FXCollections.observableArrayList();
	
	/**
	 * @param event
	 */
	@FXML public void buttonPushed(ActionEvent event) {
		String[] name = event.getSource().toString().split("'");
		
		CurrentTable = Integer.parseInt(name[1].substring(5));
		if(Restaurant.tables[CurrentTable-1].getOrderStatus()){
		resetOrderList();
		}else{
			orderView.setItems(blankList);
		}
	}
	
	/**
	 * Adds the selected menu item to the table. If no table is selected the a warning is displayed and the 
	 * method is cancelled via return. Appends the dataLog in Staff.
	 * @param order
	 */
	@FXML public void addItem(ActionEvent order){
		
		 if(CurrentTable == 0){
	            Alert alert = new Alert(AlertType.WARNING);
	             alert.setTitle("No selection made");
	             alert.setHeaderText(null);
	             alert.setContentText("Please select a table");
	             alert.showAndWait();
	             return;
	        }
		Restaurant.getActiveStaffMember().appendDataLog("added 1 " + menuItems.getSelectionModel().getSelectedItem() + " to table " + CurrentTable);
		Restaurant.tables[CurrentTable-1].switchOrderstatus();
		
		for(int i = 0; i < Restaurant.getMenu().size(); i++){
			if(Restaurant.getMenu().get(i).equals(menuItems.getValue())){
				Restaurant.addOrder(CurrentTable, i);
			};
		} // ends for loop
		resetOrderList();
	} 
	
	/**
	 * Removes an item from the orderList box. If no item is selected a warning is shown and the method 
	 * ends. Appends the dataLog in Staff.
	 * @param order
	 */
	@FXML public void removeItem(ActionEvent order){
		if(orderView.getSelectionModel().getSelectedItem()==null){
            Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("No selection made");
             alert.setHeaderText(null);
             alert.setContentText("Please select an item to remove.");
             alert.showAndWait();
             return;
        }
		OrderItem itemToRemove = orderView.getSelectionModel().getSelectedItem();
		
		Restaurant.getActiveStaffMember().appendDataLog("removed 1 " + itemToRemove.getItem() + " from table " + CurrentTable); 
		
			Restaurant.removeOrder(CurrentTable, itemToRemove);
		
		resetOrderList();
	} 
	
	/**
	 * Resets the orderList after a change has been made.
	 */
	public void resetOrderList(){
		CurrentOrder = Restaurant.getTableOrder(CurrentTable);
		setTotal();
		orderView.setItems(CurrentOrder);
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		item.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("item"));
		price.setCellValueFactory(new PropertyValueFactory<OrderItem, Double>("price"));
		description.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("description"));
		
		menuItems.setItems(menu);
		Restaurant.setTables();
		specialRequests.setWrapText(true);
		
	}
	
	/**
	 * Sets the total label to the total cost calculated from adding the individual menuItem prices.
	 */
	@FXML public void setTotal(){
		Double totalCost = 0.00;
		for(OrderItem each: CurrentOrder){
			totalCost = totalCost + each.getPrice();
		}
		total.setText("Total: " + totalCost);
		
	}
	
	/**
	 * Stores the list of items as a new order by iterating through the menu and counting the number of each item 
	 * present in the order. The items and numbers are collated into a list which is sent with other relevant details
	 * to the Order constructor. The new Order is added to the order list in Restaurant. The staff log is appended.
	 * @param event
	 */
	@FXML public void newOrder(ActionEvent event){
		
		if(CurrentOrder==null){
            Alert alert = new Alert(AlertType.WARNING);
             alert.setTitle("No selection made");
             alert.setHeaderText(null);
             alert.setContentText("No items selected in this order.");
             alert.showAndWait();
             return;
        }
		
		Restaurant.getActiveStaffMember().appendDataLog("made a new order for table " + CurrentTable);
		String tableItems = "";
		
		for(int i = 0; i < Restaurant.getMenu().size(); i++){
			int numberOfItem = 0;
		for(OrderItem each: CurrentOrder){
			if(each.getItem().equals(Restaurant.getMenu().get(i))){
				numberOfItem++;
			}
		}
			if(numberOfItem != 0){
				String toAdd =Restaurant.getMenu().get(i);
				
				if(toAdd.contains(" ")){
					toAdd = toAdd.replace(' ', '-');
				}
				tableItems = tableItems + " " + numberOfItem + "x" + toAdd;
			}
		}
		tableItems = tableItems.substring(1);
		String totalAmount = "£" + total.getText().substring(7);
		
		Restaurant.orderList.add(new Order(Integer.toString(CurrentTable), tableItems, totalAmount, comments.getText(), specialRequests.getText()));
		CurrentOrder.clear();
		specialRequests.clear();
		comments.clear();
		try{
		tab.updateOrders();
		}catch(NullPointerException e){
			
		}
	}

	/**
	 * Initializes the tab instance variable for communication with the tabController.
	 * @param tabController
	 */
	public void init(TabController tabController) {
		this.tab = tabController;
		
	}

	/**
	 * Refreshes the menu when a change is made.
	 */
	public void refreshMenu() {
		menuItems.setItems(menu);
	}

}
