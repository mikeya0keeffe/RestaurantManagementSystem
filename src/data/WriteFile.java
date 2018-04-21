package data;

import java.io.File;
import java.io.PrintWriter;
/** 
 * <h1><h1/>
 * <p><p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public class WriteFile{
 public static void makeTableMap(int noOfTables, int height, int width, String fileName, String controller){
	 try{
		 
		 	//String fileName = "bin/managertabs/Layout.fxml";
		 	//String controller = "managertabs.LayoutController";
		 	int side = (int) Math.ceil((Math.sqrt(noOfTables)));
		 	/* This finds the number one larger than the square of the number of tables to determine the 
		 	size of the AnchorPane. Each table is represented by a 75*75 pixel square with 20 pixels spacing.
		 	the square side pixels are calculated below. */
		 	double widthPixels = 20 + (95*width);
		 	double heightPixels = 20 + (95*height);
		 
		 	/* Prints the surrounding fxml elements to a layout.fxml file, in the bin folder,
		 	 ready to run with the table layout window before it opens. */

		 	File myFile = new File(fileName);
		 	PrintWriter writer = new PrintWriter(myFile, "UTF-8");
		    writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		    writer.println("<?import javafx.scene.control.Button?>");
		    writer.println("<?import javafx.scene.layout.AnchorPane?>");
		    writer.println("<?import javafx.scene.control.ComboBox?>");
		    writer.println("<?import javafx.scene.control.Label?>");
		    writer.println("<?import javafx.scene.control.TableColumn?>");
		    writer.println("<?import javafx.scene.control.TableView?>");
		    writer.println("<?import javafx.scene.control.TextArea?>");
		    String anchorPaneSize = "<AnchorPane prefHeight=\"" + (heightPixels + 365) + "\" prefWidth=\"" + (widthPixels +440) + "\" xmlns=\"http://javafx.com/javafx/8.0.65\" xmlns:fx=\"http://javafx.com/fxml/1\" fx:controller=\"" + controller + "\">";
		    writer.println(anchorPaneSize);
		    writer.println("<children>");
		    
		    int tableIndex = 0;
		    
		    /* These nested for loops iterate over the tables placing them in a  square at 20 pixel intervals. */
		    
		    for(int j = 1; j <= height; j++){
		    for(int i = 1; i <= width; i++){
		    String theString = "<Button layoutX=\"" + (20.0 + 95.0*(i-1)) + "\" layoutY=\"" + (20.0 + 95.0*(j-1)) + "\" mnemonicParsing=\"false\" onAction=\"#buttonPushed\" prefHeight=\"75.0\" prefWidth=\"75.0\" text=\"Table" + (tableIndex + 1) + "\" />";
		    writer.println(theString);
		    tableIndex++;
		    if(tableIndex == noOfTables){
		    	break;
		    } // Breaks the inner loop once the number of tables has been reached
		    }
		    if(tableIndex == noOfTables){
		    	break;
		    } // Breaks the outer loop once the number of tables has been reached
		    }
		    writer.println("<ComboBox fx:id=\"menuItems\" layoutX=\"" + (widthPixels + 40 ) + "\" layoutY=\"" + (heightPixels + 40 ) + "\" prefHeight=\"25.0\" prefWidth=\"170.0\" />");
		    writer.println("<Button layoutX=\"" + (widthPixels + 40 ) + "\" layoutY=\"" + (heightPixels + 85 ) + "\" mnemonicParsing=\"false\" onAction=\"#addItem\" prefHeight=\"25.0\" prefWidth=\"170.0\" text=\"Add item\" />");
		    writer.println("<Button layoutX=\"" + (widthPixels + 250 ) + "\" layoutY=\"" + (heightPixels + 40 ) + "\" mnemonicParsing=\"false\" onAction=\"#removeItem\" prefHeight=\"25.0\" prefWidth=\"170.0\" text=\"Remove selected\" />");
		    writer.println("<Label fx:id=\"total\" layoutX=\"" + (widthPixels + 250 ) + "\" layoutY=\"" + (heightPixels + 85 ) + "\" prefHeight=\"25.0\" prefWidth=\"75.0\" text=\"Total:\" />");	
		    writer.println("<TableView fx:id=\"orderView\" layoutX=\"" + (widthPixels + 40 ) + "\" layoutY=\"20.0\" prefHeight=\"" + heightPixels + "\" prefWidth=\"380.0\">");
		    writer.println("<columns>");
		    writer.println("<TableColumn fx:id=\"item\" prefWidth=\"117.0\" text=\"Item\" />");
		    writer.println("<TableColumn fx:id=\"price\" prefWidth=\"68.0\" text=\"Price\" />");
		    writer.println("<TableColumn fx:id=\"description\" prefWidth=\"193.0\" text=\"Description\" />");
		    writer.println("</columns>");
		    writer.println("</TableView>");
		    writer.println("<Button layoutX=\"" + (widthPixels + 250 ) + "\" layoutY=\"" + (heightPixels + 320 ) + "\" onAction=\"#newOrder\" mnemonicParsing=\"false\" prefHeight=\"25.0\" prefWidth=\"170.0\" text=\"Register order\" />");
		    writer.println("<TextArea fx:id=\"specialRequests\" layoutX=\"" + (widthPixels+ 40 ) + "\" layoutY=\"" + (heightPixels + 130 ) + "\" prefHeight=\"75.0\" prefWidth=\"380.0\" promptText=\"Special requests\" />");
		    writer.println("<TextArea fx:id=\"comments\" layoutX=\"" + (widthPixels + 40 ) + "\" layoutY=\"" + (heightPixels + 225 ) + "\" prefHeight=\"75.0\" prefWidth=\"380.0\" promptText=\"Comments\" />");
		    writer.println("</children>");
		    writer.println("</AnchorPane>");
		    
		    writer.close();
		} catch (Exception a) {
		   a.printStackTrace();
		}

}
}