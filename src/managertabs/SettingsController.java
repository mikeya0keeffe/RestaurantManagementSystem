package managertabs;

import java.net.URL;
import java.util.ResourceBundle;

import data.Restaurant;
import data.SaveRestaurantData;
import data.Staff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
/** 
 * <h1>Controls the functionality of the Settings tab for manager use only. Allows menu editing, 
 * adding and removing of staff members and displays the activity log. <h1/>
 * <p><p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class SettingsController implements Initializable{
	
	private TabController tab;
	@FXML private TextField tableNo;
	@FXML private TextField newLastName;
	@FXML private TextField newFirstName;
	@FXML private TextField newSalary;
	@FXML private TextField rows;
	@FXML private TextField columns;
	@FXML private TextField name;
	@FXML private TextField userTxt;
	@FXML private TextField passTxt;
	
	@FXML private TextField priceSet;
	@FXML private TextField itemSet;
	@FXML private TextArea descriptionSet;
	@FXML private TextArea dataTracker;
	
	@FXML private ComboBox<String> courseSet;
	@FXML private ComboBox<String> menuItems;
	@FXML private ComboBox<String> Position;
	
	@FXML private Button saveContinue;
	@FXML private Button newStaff;
	@FXML private Button deleteStaff;
	
	@FXML private TableView<Staff> staffData;
	@FXML private TableColumn<Staff, String> lastName;
	@FXML private TableColumn<Staff, String> firstName;
	@FXML private TableColumn<Staff, String> position;
	@FXML private TableColumn<Staff, String> salary;

	private int numberOfTables;
	private int rowNo;
	private int colNo;
	
	private ObservableList<String> menu = FXCollections.observableArrayList(Restaurant.getMenu());
	

	/**
	 * Initializes the tabController so that the tabs can communicate.
	 * @param tabController
	 */
	public void init(TabController tabController) {
		this.tab = tabController;
		
	}
	
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lastName.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
		firstName.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
		position.setCellValueFactory(new PropertyValueFactory<Staff, String>("position"));
		salary.setCellValueFactory(new PropertyValueFactory<Staff, String>("salary"));
		staffData.setItems(Restaurant.staffList);
		ObservableList<String> courses = FXCollections.observableArrayList("Starter","Main","Dessert","Drink");
		courseSet.setItems(courses);
		menuItems.setItems(menu);
		
		ObservableList<String> roles = FXCollections.observableArrayList("Employee","Manager");
		Position.setItems(roles);
		
	}
	
/**
 * Adds a staff member to the list of data. If any field is not selected a warning message appears and the 
 * method exits.
 * @param event
 */
@FXML private void addStaffMember(ActionEvent event){
	if(newLastName.getText() == null || newFirstName.getText() == null || newSalary.getText() == null || Position.getSelectionModel().getSelectedItem() == null){
		Alert alert = new Alert(AlertType.WARNING);
		 alert.setTitle("No selection made");
		 alert.setHeaderText(null);
		 alert.setContentText("Please give a first and last name, a salary and select a position.");
		 alert.showAndWait();
		return;
	}
		
		Restaurant.staffList.add(new Staff(newLastName.getText(), newFirstName.getText(),Position.getSelectionModel().getSelectedItem(),newSalary.getText()));
		staffData.setItems(Restaurant.staffList); 
	}

/**
 * Deletes a selected staff member from the list. If a staff member is not selected a warning message
 * appears and the method quits.
 * @param event
 */
@FXML private void deleteSelectedStaff(ActionEvent event){
	
	if(staffData.getSelectionModel().getSelectedItem() == null){
		Alert alert = new Alert(AlertType.WARNING);
		 alert.setTitle("No selection made");
		 alert.setHeaderText(null);
		 alert.setContentText("Please select a staff member.");
		 alert.showAndWait();
		return;
	}
	Staff toDelete = (Staff) staffData.getSelectionModel().getSelectedItem();
	Restaurant.staffList.remove(toDelete);
	staffData.setItems(Restaurant.staffList); 
}

/**
 * Sets the name of the restaurant to the textField as it is typed.
 * @param event
 */
@FXML private void setName(ActionEvent event){
	Restaurant.setName(name.getText());
	name.setText(Restaurant.getName());
}

/**
 * If a menu item is selected that item is removed from the Restaurant observable List. Otherwise the method quits after showing an
 * alert.
 */
@FXML private void removeMenuItem(){
	if(menuItems.getSelectionModel().getSelectedItem() == null){
		Alert alert = new Alert(AlertType.WARNING);
		 alert.setTitle("No selection made");
		 alert.setHeaderText(null);
		 alert.setContentText("Please Select the item to remove from the list.");
		 alert.showAndWait();
		return;
	}
	
	Restaurant.removeMenuItem(menuItems.getSelectionModel().getSelectedItem());
	
	menuItems.setItems(Restaurant.getMenu());
	tab.refreshMenu();
}

/**
 * If all the fields for a new menu item have been filled in, a new menu item is produced and added to 
 * the observable lists in Restaurant. If any field is blank an alert is shown and the method quits.
 */
@FXML private void addMenuItem(){
	
	
	if(priceSet.getText() == null || descriptionSet.getText() == null || courseSet.getSelectionModel().getSelectedItem() == null){
		Alert alert = new Alert(AlertType.WARNING);
		 alert.setTitle("No selection made");
		 alert.setHeaderText(null);
		 alert.setContentText("Please give a price, description and course.");
		 alert.showAndWait();
		return;
	}
	Restaurant.appendMenu(itemSet.getText(), Double.parseDouble(priceSet.getText()), descriptionSet.getText(), courseSet.getSelectionModel().getSelectedItem().toString());
	menuItems.setItems(Restaurant.getMenu());
	tab.refreshMenu();
	
}

/**
 * Every time a new staff member is selected in the the TableView, their data is shown in the TextArea,
 * dataTracker.
 * @param event
 */
public void showDataLog(MouseEvent event){
	dataTracker.setText(staffData.getSelectionModel().getSelectedItem().getDataLog());
}

/**
 * Collects and sets the user name and password for the selected staff member
 */
public void setUserPass(){
	staffData.getSelectionModel().getSelectedItem().setUsername(userTxt.getText());
	staffData.getSelectionModel().getSelectedItem().setPassword(passTxt.getText());
}

/**
 * Saves the Restaurant data via the SaveRestaurantData class.
 */
public void saveData(){
	SaveRestaurantData.SaveRestaurant();
}

}
