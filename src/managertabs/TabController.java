package managertabs;

import javafx.fxml.FXML;
/** 
 * <h1>Allows communication between the tabs.<h1/>
 * <p><p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class TabController {

	@FXML LayoutController layoutController;
	@FXML SettingsController settingsController;
	@FXML OrdersController ordersController;
	
	/**
	 * Sets up the instance variables for tab communication.
	 */
	@FXML public void initialize(){
		layoutController.init(this);
		settingsController.init(this);
		ordersController.init(this);
	}

	/**
	 * Calls the Layout tab and refreshes it's order item list.
	 */
	public void refreshMenu() {
		layoutController.refreshMenu();
		
	}

	/**
	 * Calls the Orders tab and refreshes its list of orders.
	 */
	public void updateOrders() {
		ordersController.updateOrder();
		
	}

}
