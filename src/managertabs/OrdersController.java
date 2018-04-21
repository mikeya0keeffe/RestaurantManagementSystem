package managertabs;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import data.ExportOrder;
import data.ImportOrder;
import data.Order;
import data.Restaurant;
import data.SearchOrders;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
/** 
 * <h1>OrderController<h1/>
 * <p>Controls the functionality for the OrderController.fxml class. The methods allow it to 
 * export orders to the Layout View for editing, delete them or write orders to file or accept 
 * orders from a file.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class OrdersController implements Initializable{

	private TabController tab;
	@FXML private TextField dateFrom;
	@FXML private TextField dateUntil;
	@FXML private TextField timeFrom;
	@FXML private TextField timeUntil;
	@FXML private TextField keywords;
	@FXML private TextField lowerCost;
	@FXML private TextField upperCost;
	@FXML private ComboBox<String> searchOptions;
	
	@FXML private TableView<Order> orderData;
	@FXML private TableColumn<Order, String> table;
	@FXML private TableColumn<Order, String> dateTime;
	@FXML private TableColumn<Order, String> items;
	@FXML private TableColumn<Order, String> cost;
	@FXML private TableColumn<Order, String> requests;
	@FXML private TableColumn<Order, String> comments;
	
	private ObservableList<String> options = FXCollections.observableArrayList("Table","Date/Time","Items","Total cost","Special requests","Comments");
	public static ObservableList<Order> searchResults = FXCollections.observableArrayList();
	
	/**
	 * Removes an order from the list after producing a confirmation popup window.
	 */
	@FXML public void deleteOrder(){
	 Alert alert = new Alert(AlertType.CONFIRMATION);
	 alert.setTitle("Confirm delete order");
	 alert.setHeaderText(null);
	 alert.setContentText("Are you sure you want to delete this order?");
	 Optional <ButtonType> action = alert.showAndWait();
	 
	 if(action.get() == ButtonType.OK){
		 Restaurant.orderList.remove(orderData.getSelectionModel().getSelectedItem());
		 updateOrder();
	 }
	}
	
	/**
	 * Takes an order and splits it up to its individual parts and reconstructs the orderItems for export
	 * to the Layout view. If the menu item is not found, for example if the menu has changed, an alert will request
	 * that the menu item is added in settings before trying again.
	 */
	@FXML public void editOrder(){
		Order orderToEdit = orderData.getSelectionModel().getSelectedItem();
		String charBlock = orderToEdit.getItems();
		String[] itemNames = charBlock.split(" ");
		//Gets an array of item numbers and names in the form (number of item)x(item name).
		for(String eachItem: itemNames){
			String[] noAndName = eachItem.split("x");
			noAndName[1] = noAndName[1].replace('-',' ');
			if(Restaurant.getMenu().contains(noAndName[1])){
				int noOfItem = Integer.parseInt(noAndName[0]);
				for(int i = 0; i < noOfItem; i++){
				Restaurant.addOrder(Integer.parseInt(orderToEdit.getTable()), Restaurant.getMenu().indexOf(noAndName[1]));
				Restaurant.tables[Integer.parseInt(orderToEdit.getTable())-1].switchOrderstatus();
				} // If the item is contained in the menu then it calls the restaurant class to create a new order item
			}else{
				Alert alert = new Alert(AlertType.WARNING);
				 alert.setTitle("Item not found");
				 alert.setHeaderText(null);
				 alert.setContentText("The item " + noAndName[1] + " is not in the menu. Please add the item under Settings and reload the order.");
				 alert.showAndWait();
			} // If the item is not on the menu a dialogue box explains this
		}
	}
	
	/**
	 * Opens a file selector and sends the selected file to ExportOrder for writing into a .csv file.
	 */
	@FXML public void exportOrders(){
		
		if(orderData.getSelectionModel().getSelectedItems() != null){
			FileChooser fc = new FileChooser();
			
			File selectedFile = fc.showOpenDialog(null);
			
		ExportOrder.WriteCSVFile(orderData.getSelectionModel().getSelectedItems(),selectedFile);
		}else{
			Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("No selection made");
			 alert.setHeaderText(null);
			 alert.setContentText("Please Select the orders to export from the list.");
			 alert.showAndWait();
		}
	}
	
	/**
	 * Opens a file selector and sends the selected file to ImportOrder so that the .csv file can be read in.
	 */
	@FXML public void importOrders(){
		
		FileChooser fc = new FileChooser();
		
		File selectedFile = fc.showOpenDialog(null);
		
		ImportOrder.importedOrders(selectedFile);
		orderData.setItems(Restaurant.orderList);
	}
	
	/**
	 * Finds the selected search field and either performs the search in the method or, in the case of
	 * searching by date, sends it to SearchOrders. If no field has been selected an alert window is 
	 * produced and the method quits.
	 */
	@FXML public void searchOrders(){
		searchResults.clear();
		if(searchOptions.getValue()==null){
			Alert alert = new Alert(AlertType.WARNING);
			 alert.setTitle("No selection made");
			 alert.setHeaderText(null);
			 alert.setContentText("Please select a search field.");
			 alert.showAndWait();
			 return;
		}else if(searchOptions.getValue().equals("Date/Time")){
			
			String TimeDateFrom = timeFrom.getText() + " on " + dateFrom.getText();
			String TimeDateUntil = timeUntil.getText() + " on " + dateUntil.getText();
			searchResults = SearchOrders.searchByDate(TimeDateFrom, TimeDateUntil);
		}else if(searchOptions.getValue().equals("Table")){
			for(Order eachOne: Restaurant.orderList){
				if(eachOne.getTable().equals(keywords.getText())){
					searchResults.add(eachOne);
				}
			}
		}else if(searchOptions.getValue().equals("Items")){
			for(Order eachOne: Restaurant.orderList){
				if(eachOne.getItems().toLowerCase().contains(keywords.getText().toLowerCase())){
					searchResults.add(eachOne);
				}
			}
		}else if(searchOptions.getValue().equals("Total cost")){
			Double lower = Double.parseDouble(lowerCost.getText().substring(1));
			Double upper = Double.parseDouble(upperCost.getText().substring(1));
			
			for(Order eachOne: Restaurant.orderList){

				String withPoundSign = eachOne.getCost().substring(1);
				Double thisCost = Double.parseDouble(withPoundSign);
				
				if(thisCost > lower && thisCost < upper){
					searchResults.add(eachOne);
				}
			}
		}else if(searchOptions.getValue().equals("Special requests")){
			for(Order eachOne: Restaurant.orderList){
				if(eachOne.getRequests().toLowerCase().contains(keywords.getText().toLowerCase())){
					searchResults.add(eachOne);
				}
			}
		}else if(searchOptions.getValue().equals("Comments")){
			for(Order eachOne: Restaurant.orderList){
				if(eachOne.getComments().toLowerCase().contains(keywords.getText().toLowerCase())){
					searchResults.add(eachOne);
				}
			}
		}
		
		orderData.setItems(searchResults);
		
	}
	
	/**
	 * Initializes the tabController for communication between the tab controllers.
	 * @param tabController
	 */
	public void init(TabController tabController) {
		this.tab = tabController;
		
	}
	
	/**
	 * Refreshes the orders when they have been updated.
	 */
	public void updateOrder(){
		orderData.setItems(Restaurant.orderList);
	}

	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		table.setCellValueFactory(new PropertyValueFactory<Order, String>("table"));
		dateTime.setCellValueFactory(new PropertyValueFactory<Order, String>("dateTime"));
		items.setCellValueFactory(new PropertyValueFactory<Order, String>("items"));
		cost.setCellValueFactory(new PropertyValueFactory<Order, String>("cost"));
		requests.setCellValueFactory(new PropertyValueFactory<Order, String>("requests"));
		comments.setCellValueFactory(new PropertyValueFactory<Order, String>("comments"));
		orderData.setItems(Restaurant.orderList);
		orderData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		searchOptions.setItems(options);
		
	}
	
	

}
