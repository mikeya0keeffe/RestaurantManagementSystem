package employeetabs;

import javafx.fxml.FXML;
import managertabs.LayoutController;
/** 
 * <h1>EmployeeLayout<h1/>
 * <p>Provides the controls for dealing with the EmployeeLayout.fxml. Extends LayoutController
 * and receives all functionality from there.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class EmployeeLayoutController extends LayoutController{

	@FXML EmployeeTabController employeeTabController;
	
	/**
	 * Accepts and initializes the controller tab so that the windows can communicate.
	 * @param employeeTabController
	 */
	public void init(EmployeeTabController employeeTabController) {
		this.employeeTabController = employeeTabController;
		System.out.println(this.getClass() + " 1st message");
		
	}

}
