package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ExcelWriter {
	
	@SuppressWarnings("unchecked")
	public void writeExcel(String sheetName, String jsonFilePath, String outputFilePath) {
	
		//Initialize a new blank workbook
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		//Creating a new blank sheet with sheet name provided in the argument
		Sheet sheet = workbook.createSheet(sheetName);
		
		//Initializing json parser to read the json file
		JSONParser parser = new JSONParser();
		
		try {
			//Retrieving json data from file and casting it to JSON Array, since json array exists in sample file
			JSONArray jsonData = (JSONArray) parser.parse(new FileReader(jsonFilePath));
			
			//Reading first element from retrieved json data to get the keys
			JSONObject jsonObj = (JSONObject) jsonData.get(0);
			
			//Storing the keys in a Set (to have unique keys)
			Set<String> keySet = jsonObj.keySet();
			
			int cellNumber = 0;
			Row row = sheet.createRow(0);
			
			//Setting cell style to keep the heading bold and increase font
			CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		    Font font = sheet.getWorkbook().createFont();
		    font.setBold(true);
		    font.setFontHeightInPoints((short) 16);
		    cellStyle.setFont(font);
		    
		    //looping through the keys obtained in set, in order to set first row as header from json data
			for (String key : keySet) {
				
				Cell cellTitle = row.createCell(cellNumber);
				cellTitle.setCellStyle(cellStyle);
				cellTitle.setCellValue(key);
				
				cellNumber++;
			}
			
			//Increased row number and initialized as 1, since 0th row contains headers
			int rowNumber = 1;
			
			//Getting an iterator to loop through the json array
			Iterator<Object> iterator = jsonData.iterator();
			while (iterator.hasNext()) {
				int columnNumber = 0;
				row = sheet.createRow(rowNumber++);
				JSONObject dataForRow = (JSONObject) iterator.next();
				
				//for each key loop through the json object fetched in last step and create a new cell entry in sheet
				for (String key : keySet) {
					Cell cell = row.createCell(columnNumber++);
					Object value = dataForRow.get(key);
					if (value != null) {
						
						if (value instanceof String) {
							cell.setCellValue((String) value);
						}
						else if (value instanceof Long) {
							cell.setCellValue((Long) value);
						} 
						else if (value instanceof Integer) {
							cell.setCellValue((Integer) value);
						} 
						else if (value instanceof Double) {
							cell.setCellValue((Double) value);
						}
					}
				}
			}
			
			FileOutputStream out = new FileOutputStream(new File(outputFilePath));
            workbook.write(out);
            out.close();
			System.out.println("The json data has been written successfully to excel file !");
		} 
		catch (FileNotFoundException fileNotFoundExc) {
			
			System.out.println("The given file path could not be resolved to find a valid file ! \n");
			fileNotFoundExc.printStackTrace();
		} 
		catch (IOException ioExc) {
			
			System.out.println("An error occurred during file read operation ! \n");
			ioExc.printStackTrace();

		} 
		catch (ParseException pe) {
			
			System.out.println("An error occurred during parsing of json file ! \n");
			pe.printStackTrace();
		}
		catch (Exception exc) {
			
			System.out.println("An error occurred while performing excel write operation ! \n");
			exc.printStackTrace();
		}	
	}
	
	
}
