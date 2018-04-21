package employeetabs;

import javafx.fxml.FXML;
import managertabs.TabController;
/** 
 * <h1>EmployeeTabController<h1/>
 * <p>Controls the two employee tabs.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class EmployeeTabController extends TabController{
	
	@FXML EmployeeLayoutController employeeLayoutController;
	@FXML EmployeeOrdersController employeeOrdersController;
	
	
	/* (non-Javadoc)
	 * @see managertabs.TabController#initialize()
	 */
	@FXML public void initialize(){
		employeeLayoutController.init(this);
		employeeOrdersController.init(this);
	}

}
