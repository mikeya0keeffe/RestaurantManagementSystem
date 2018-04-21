package employeetabs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import managertabs.OrdersController;
/** 
 * <h1>EmployeeOrdersController<h1/>
 * <p>Inherits from OrderController and controls the functionality for the EmployeeOrders.fxml<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class EmployeeOrdersController extends OrdersController {

	@FXML EmployeeTabController employeeTabController;
	@FXML Button importer;
	@FXML Button exporter;
	
	/**
	 * Accepts and initializes the EmployeeTabController so the tabs can communicate.
	 * @param employeeTabController
	 */
	public void init(EmployeeTabController employeeTabController) {
		this.employeeTabController = employeeTabController;
		this.importer.setVisible(false);
		this.exporter.setVisible(false);
		
	}

}
