package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javafx.collections.ObservableList;
/** 
 * <h1>Import Order<h1/>
 * <p>Contains only one method in order to read the order values from a .csv file.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public abstract class ImportOrder {

	/** Takes a file as an argument.mThis file is read in by the buffered reader 
	 * and broken into the components for a restaurant order. The components are
	 *  passed to the order constructor and the resulting order is stored in the 
	 *  Restaurant order list.
	 * @param toImport
	 */
	public static void importedOrders(File toImport){
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(toImport));
			br.readLine();
			
			boolean isWriting = true;
			
			while(isWriting){
				String newLine = br.readLine();
				if(newLine == null){
					isWriting = false; // obselete variable
					break;
				}
				String[] firstBreak = newLine.split("\",\"");
				String comments = firstBreak[1].substring(0, (firstBreak[1].length()-1));
				String [] secondBreak = firstBreak[0].split(",\"");
				String specialRequests = secondBreak[2];
				String[] secondBreakA = secondBreak[1].split("\",");
				String items = secondBreakA[0];
				String cost = secondBreakA[1];
				String[] secondBreakB = secondBreak[0].split(",");
				String table = secondBreakB[0];
				String timeDate = secondBreakB[1];
				
				Order newInputOrder = new Order(table, items,cost,specialRequests,comments);
				newInputOrder.setDateTime(timeDate);
				Restaurant.orderList.add(newInputOrder);
				
				System.out.println(newLine);
			
			} 
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
