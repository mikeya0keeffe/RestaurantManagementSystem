package loginwindows;

import java.net.URL;
import java.util.ResourceBundle;

import data.Restaurant;
import data.Staff;
import data.WriteFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/** 
 * <h1>SetLayoutController<h1/>
 * <p>Class contains the functionality for the SetLayout.fxml class. Contains 3 button triggered methods and 
 * an initialization method.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class SetLayoutController implements Initializable{
	@FXML
	private TextField tableNo;
	@FXML private TextField newLastName;
	@FXML private TextField newFirstName;
	@FXML private TextField newSalary;
	@FXML private TextField rows;
	@FXML private TextField columns;
	
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
	
	
	/**
	 * Takes the number, rows and columns of the table area and passes them to FileWrite for building into 
	 * an FXML document. It then sets up a new set of manger tabs.
	 * @param event
	 */
	@FXML public void setUpRestaurant(ActionEvent event) {
		numberOfTables = Integer.parseInt(tableNo.getText());
		rowNo = Integer.parseInt(rows.getText());
		colNo = Integer.parseInt(columns.getText());
		
		
		if( (rowNo*colNo < numberOfTables)){
			return;
		}
		
		Restaurant.setNumberOfTables(numberOfTables);
		WriteFile.makeTableMap(numberOfTables, rowNo, colNo, "bin/managertabs/Layout.fxml", "managertabs.LayoutController");
		WriteFile.makeTableMap(Restaurant.getNumberOfTables(), Restaurant.getRows(), Restaurant.getColumns(), "bin/employeetabs/EmployeeLayout.fxml", "employeetabs.EmployeeLayoutController");
		try {
			Stage mapStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/managertabs/Tab.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			mapStage.setScene(scene);
			mapStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		  
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lastName.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
		firstName.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
		position.setCellValueFactory(new PropertyValueFactory<Staff, String>("position"));
		salary.setCellValueFactory(new PropertyValueFactory<Staff, String>("salary"));
		staffData.setItems(Restaurant.staffList); 
		
		ObservableList<String> roles = FXCollections.observableArrayList("Employee","Manager");
		Position.setItems(roles);
		
	}
	
	/**
	 * Adds a new staff member to the Restaurants observable array list.
	 * @param event
	 */
	@FXML private void addStaffMember(ActionEvent event){
		
		Restaurant.staffList.add(new Staff(newLastName.getText(), newFirstName.getText(),Position.getSelectionModel().getSelectedItem(),newSalary.getText()));
		staffData.setItems(Restaurant.staffList); 
	}
	
	/**
	 * Removes a staff member from the selected observable array list in the Restaurant class.
	 * @param event
	 */
	@FXML private void deleteSelectedStaff(ActionEvent event){
		Staff toDelete = (Staff) staffData.getSelectionModel().getSelectedItem();
		Restaurant.staffList.remove(toDelete);
		staffData.setItems(Restaurant.staffList); 
	}
	
	
} // 
