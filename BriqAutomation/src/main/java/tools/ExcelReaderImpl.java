package tools;

import org.json.JSONException;
import org.json.JSONObject;
import utilities.ExcelReader;

public class ExcelReaderImpl {

	public static void main(String[] args) throws JSONException {
		
		//Path to the excel file. The file that is intended to be processed must be placed under resources folder
		String excelFilePath = "./resources/leads.xlsx";
	    ExcelReader excelReader = new ExcelReader();
	    JSONObject sheetData = excelReader.readExcel(excelFilePath);
	    /*This prints an array of json values. Array is used because, if in future we want to scale up and workbook contains more than one sheet, 
	    then we can easily read them as well.*/
	    System.out.println("Sheet Data - " + sheetData.toString(0));

	}

}
