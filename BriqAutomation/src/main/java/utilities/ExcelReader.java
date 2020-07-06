package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.json.JSONArray;
import org.json.JSONObject;


public class ExcelReader {
	
	public JSONObject readExcel(String filePath) {
		
		JSONObject sheetJsonObjects = new JSONObject();
		try {
			FileInputStream fileInputStream = new FileInputStream(filePath);
			
			//Initializing Workbook object to handle the .xlsx sheet operations
			XSSFWorkbook excelBook = new XSSFWorkbook(fileInputStream);
			
			//Work on first sheet in the excel workbook
			/* For the purpose of this assignment, considering only 1 sheet, 
			 	but this code can be easily scaled up by putting a loop to read more than one sheets. */
			Sheet sheetOne = excelBook.getSheetAt(0);
			
			//Initializing array to store multiple objects from each row
			JSONArray sheetArray = new JSONArray();
			
			//Initializing an array list to hold all the sheet columns
			ArrayList<String> columnNames = new ArrayList<String>();
			
			//We need to iterate through each row to read data
			Iterator<Row> sheetIterator = sheetOne.iterator();
			while (sheetIterator.hasNext()) {
				Row row = sheetIterator.next();
				
				//Initialize a new json object to hold each row's data
				JSONObject jsonObj = new JSONObject();
				
				//Assuming that first row in sheet is column header, so storing that
				if (row.getRowNum() == 0) {
					for (int j=0; j<row.getPhysicalNumberOfCells(); j++) {
						columnNames.add(row.getCell(j).getStringCellValue());
					}
				}
				else {
					//Loop till the end of all columns, hence size of columns is used
					for (int k=0; k<columnNames.size(); k++) {
						if (row.getCell(k) != null) {
							
							//Format the values according to cell valuue types (String, Date, Numeric)
							DataFormatter formatter = new DataFormatter();
							//Replace at the end is for removing the new line character and replace u printed in string with hyphen
							String cellValue = formatter.formatCellValue(row.getCell(k)).replaceAll("\n", "").replaceAll("[^\\x00-\\x7F]", "-");
							jsonObj.put(columnNames.get(k), cellValue);

						}
						else {
							jsonObj.put(columnNames.get(k), "");
						}
					}
					
					sheetArray.put(jsonObj);
				}
		
			}
			sheetJsonObjects.put(excelBook.getSheetName(0), sheetArray);
			fileInputStream.close();
			System.out.println("Excel read operation completed successfully ! \n");
		}
		catch (FileNotFoundException fileNotFoundExc) {
			
			System.out.println("The given file path could not be resolved to find a valid file ! \n");
			fileNotFoundExc.printStackTrace();
		}
		catch (IOException ioExc) {
			
			System.out.println("An error occurred during file read operation ! \n");
			ioExc.printStackTrace();
		}
		catch (Exception exc) {
			
			System.out.println("An error occurred while performing excel read operation ! \n");
			exc.printStackTrace();
		}

		return sheetJsonObjects;
	}
}
