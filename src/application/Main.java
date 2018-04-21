package application;
	
import data.Restaurant;

import data.SaveRestaurantData;
import data.WriteFile;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/** 
 * <h1>Main Class<h1/>
 * <p>This class contains the main method which calls the start method. This initiates a login screen.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */

// "/loginwindows/LoginPage.fxml"
public class Main extends Application {
	public static Stage primaryStage;
	/** This main method opens the login pane. */
	@Override public void start(Stage primaryStage) {
		try {
			Main.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/loginwindows/LoginPage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			 SaveRestaurantData.loadData();
			 WriteFile.makeTableMap(Restaurant.getNumberOfTables(), Restaurant.getRows(), Restaurant.getColumns(), "bin/managertabs/Layout.fxml", "managertabs.LayoutController");
			 WriteFile.makeTableMap(Restaurant.getNumberOfTables(), Restaurant.getRows(), Restaurant.getColumns(), "bin/employeetabs/EmployeeLayout.fxml", "employeetabs.EmployeeLayoutController");
		} catch(Exception e) {
			e.printStackTrace();
		} // The main class loads the login page and links it to its own LoginPage.FXML doc
	}
	
	/** Calls the start() method. */
	public static void main(String[] args) {
		launch(args);
	}
}
