package data;

import java.io.File;
import java.io.PrintWriter;

import javafx.collections.ObservableList;

/** 
 * <h1>Export Order<h1/>
 * <p>This class has only one method to write a list of orders to a .csv file. <p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public abstract class ExportOrder {

	
	/** Method accepts a file name/path and an observable list of orders to write to that location.
	 * @param orders
	 * @param fileName
	 */
	public static void WriteCSVFile(ObservableList<Order> orders, File fileName){
		File myFile = fileName; // new File("/Users/michaelokeeffe/Desktop/ExportOrders.csv");
		try{
			PrintWriter writer = new PrintWriter(myFile, "UTF-8");
			writer.println("Table,Date/Time,Items,Total cost,Special requests,Comments");
			for(Order eachOrder: orders){
				System.out.println(eachOrder.getItems());
			writer.println(eachOrder.getTable() +"," + eachOrder.getDateTime() +",\"" + eachOrder.getItems() +"\"," + eachOrder.getCost() +",\"" + eachOrder.getRequests() +"\",\"" + eachOrder.getComments() + "\"");
			}
			
			writer.close();
		}catch(Exception e){
		e.printStackTrace();
	}
		
	}
}
