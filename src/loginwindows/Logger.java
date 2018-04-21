package loginwindows;

import java.io.IOException;

import application.Main;
import data.Restaurant;
import data.SaveRestaurantData;
import data.Staff;
import data.WriteFile;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/** 
 * <h1>Logger<h1/>
 * <p>Class which controls the login window. Depending on the staff login details entered it opens the
 * standard staff windows or the manger's tab windows.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class Logger{
	@FXML
	private Label loginStatus;
	@FXML
	private TextField UserName;
	@FXML
	private PasswordField Password;
	
	/**
	 * Activated by pressing the login button. Checks the user name and password and then directs them to the appropriate version
	 * of the application.
	 * @throws IOException
	 */
	@FXML
	public void loginAttempt() throws IOException{
		 SaveRestaurantData.loadData();
		 WriteFile.makeTableMap(Restaurant.getNumberOfTables(), Restaurant.getRows(), Restaurant.getColumns(), "bin/managertabs/Layout.fxml", "managertabs.LayoutController");
		 WriteFile.makeTableMap(Restaurant.getNumberOfTables(), Restaurant.getRows(), Restaurant.getColumns(), "bin/employeetabs/EmployeeLayout.fxml", "employeetabs.EmployeeLayoutController");
		for(Staff thisMember: Restaurant.staffList){
		if(Password.getText().equals(thisMember.getPassword()) && UserName.getText().equals(thisMember.getUsername())){
			Restaurant.setActiveStaffMember(thisMember);
			loginStatus.setText("Login successful");
			Stage primaryStage = new Stage();
			Parent root;
			if(thisMember.getPosition().equals("Employee")){
				root = FXMLLoader.load(getClass().getResource("/employeetabs/EmployeeTabsController.fxml"));
			}else{
				root = FXMLLoader.load(getClass().getResource("/managertabs/Tab.fxml"));
			}
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Application");
			primaryStage.show();
			Main.primaryStage.close();
		}else{
			loginStatus.setText("Login failed");
			}; 
			/*The if else statement checks the username and password against the stored values
			and if correct opens the layout for the manager to set the restaurant details. If the 
			details are incorrect the status login failed is displayed. The window opened is controlled 
			by the SetLayoutController class and is linked to theSetLayout.fxml document*/
		}
		}
	
	/**
	 * If the user wants to set up a new restaurant this will take them to a set up window to work from.
	 */
	@FXML public void setUpNewRestaurant(){
		try{
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/loginwindows/SetLayout.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main Application");
			primaryStage.show();
			Main.primaryStage.close();
			}catch(Exception e){
			
			}
	}
		
	}
