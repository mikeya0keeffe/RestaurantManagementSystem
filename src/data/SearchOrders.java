package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import data.Order;
/** 
 * <h1>SearchOrders<h1/>
 * <p>This class contains the code for searching orders by date. Consists of two methods.<p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public abstract class SearchOrders {
	
	static long formattedStartDate;
	static long formattedEndDate;
	static long formattedCheckDate;
	static ObservableList<Order> theOrder = FXCollections.observableArrayList();
	
	ObservableList<Order> searchResults = FXCollections.observableArrayList();
	
	/**Method has two Strings as parameters which it converts into millisecond values using
	 * the method below. Then all restaurant orders are iterated across and each one's time converted to a 
	 * millisecond value. Any values matching the time period are appended to the observable list 'theOrder'
	 * which is then displayed in the Orders tab.
	 * @param dateFrom
	 * @param dateUntil
	 * @return
	 */
	public static ObservableList<Order> searchByDate(String dateFrom, String dateUntil){
		formattedStartDate = getMilliSeconds(dateFrom);
		formattedEndDate = getMilliSeconds(dateUntil);
		
		for(Order thisOrder: Restaurant.orderList){
			String checkTime = thisOrder.getDateTime().substring(0,20);
			formattedCheckDate = getMilliSeconds(checkTime);
			if(formattedCheckDate > formattedStartDate && formattedCheckDate<formattedEndDate){
				theOrder.add(thisOrder);
			}
			
		}
		return theOrder;
	} /* The start date and end date are compared in milliseconds to the date of the order.
	If the order date is in between them it is added to the list. */
	
	/**This converts a string in the form "hh:mm:ss on mm/dd/yy" into the correct format 
	 * "yyyy-MM-dd HH:mm:ss" for the simple date format class to convert it into a millisecond
	 *  value since 1970.
	 * @param aDate
	 * @return
	 */
	public static long getMilliSeconds(String aDate){
		long timeInMilli = 0;
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String correctFormat = "20" + aDate.substring(18) + "-";
		correctFormat = correctFormat + aDate.substring(12, 14) + "-";
		correctFormat = correctFormat + aDate.substring(15, 17) + " ";
		correctFormat = correctFormat + aDate.substring(0,8);
		try{
	        Date date = simpleFormat.parse(correctFormat);
	        timeInMilli = date.getTime(); 
	        }catch(ParseException e){
	        	e.printStackTrace();
	        }
		
		return timeInMilli;
	} /* This class puts the Dates into the correct format fr the simpleDateFormat class
	to convert into milliseconds. */
}


