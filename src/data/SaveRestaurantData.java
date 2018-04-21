package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/** 
 * <h1>SaveRestaurantData<h1/>
 * <p>This class contains one method for writing the details from restaurant class into a .csv file. 
 * It can read files it creates and return the values to the restaurant class. <p/>
 *  
 *  @author Michael O'Keeffe
 *  @created 19/12/16
 *  @version 1
 *  */
public abstract class SaveRestaurantData {

	/**
	 * Writes the restaurant data to a .csv file with the column headings: "name,tables,rows,columns,menu,menuDescription,course,price"
	 * 
	 */
	public static void SaveRestaurant (){
		File myFile = new File("RestaurantData.csv");
		
		try{
			PrintWriter writer = new PrintWriter(myFile, "UTF-8");
			writer.println("name,tables,rows,columns,menu,menuDescription,course,price");
			
			Object[] myMenu = Restaurant.getMenu().toArray();
			writer.print(Restaurant.getName()+",");
			writer.print(Restaurant.getNumberOfTables()+",");
			writer.print(Restaurant.getRows()+",");
			writer.print(Restaurant.getColumns()+",\"");
			for(Object s: myMenu){
			 writer.print(";"+ s);
			}
			writer.print("\",\"");
			Object[] myDescriptions = Restaurant.getMenuDescription().toArray();
			for(Object o: myDescriptions){
				writer.print(";"+ o);
			}
			writer.print("\",\"");
			Object[] myCourses = Restaurant.getCourse().toArray();
			for(Object c: myCourses){
				writer.print(";"+ c);
			}
			writer.print("\",\"");
			Object[] myPrices = Restaurant.getPrice().toArray();
			for(Object p: myPrices){
				writer.print(";"+ p);
			}
			writer.print("\"\n");
			
			writer.close();
		}catch(Exception e){
		e.printStackTrace();
	}
		
	}
	
	/**
	 * Reads the restaurant data from a .csv file and breaks each line up into the individual restaurant fields.
	 * These are sent to the Restaurant class for saving.
	 */
	public static void loadData(){
		try{
			
			BufferedReader br = new BufferedReader(new FileReader("RestaurantData.csv"));
			br.readLine();

				String newLine = br.readLine();
			
				String[] firstSplit = newLine.split("\",\"");
				firstSplit[1] = firstSplit[1].substring(1);
				String[] descriptions = firstSplit[1].split(";");
				
				Restaurant.setMenuDescription(FXCollections.observableArrayList(descriptions));
				
				firstSplit[2] = firstSplit[2].substring(2);
				String[] courses = firstSplit[2].split(";");
				Restaurant.setCourse(FXCollections.observableArrayList(courses));
				firstSplit[3] = firstSplit[3].substring(1, (firstSplit[3].length()-1));
				String[] prices = firstSplit[3].split(";");
				Double[] myPrices = new Double[prices.length];
				
				for(int i = 0; i < prices.length;i++){
					myPrices[i] = Double.parseDouble(prices[i]);
				}
				Restaurant.setPrice(FXCollections.observableArrayList(myPrices));
				String[] secondSplit = firstSplit[0].split(",\"");
				secondSplit[1] = secondSplit[1].substring(1);
				String[] items = secondSplit[1].split(";");
				Restaurant.setmenu(FXCollections.observableArrayList(items));
				
				String[] finalSplit = secondSplit[0].split(",");
				int tables = Integer.parseInt(finalSplit[1]);
				int rows = Integer.parseInt(finalSplit[2]);
				int columns = Integer.parseInt(finalSplit[3]);
				Restaurant.setRows(rows);
				Restaurant.setColumns(columns);
				Restaurant.setNumberOfTables(tables);
				Restaurant.setName(finalSplit[0]);
			
			br.close();
		}catch(Exception e){
			
		}
	}
	
	/**
	 * 
	 */
	public void saveStaffData(){
		File staffFile = new File("StaffDetails.csv");
		try{
		PrintWriter writer = new PrintWriter(staffFile, "UTF-8");
		writer.println("LastName,FirstName,Position,Salary,UserName,Password,Datalog");
		}catch(Exception e){
			
		}
		
	}
		
	}

