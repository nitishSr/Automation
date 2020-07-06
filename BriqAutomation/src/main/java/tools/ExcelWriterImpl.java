package tools;

import utilities.ExcelWriter;

public class ExcelWriterImpl {

	public static void main(String[] args) {
		
		//Path to the excel file. The file that is intended to be processed must be placed under resources folder
		String jsonFilePath = "./resources/input_for_excel.json";
		String outputFilePath = "./output/SampleExcel.xlsx";
		String sheetName = "Sample_Sheet";
	    ExcelWriter excelWriter = new ExcelWriter();
	    excelWriter.writeExcel(sheetName, jsonFilePath, outputFilePath);
	    System.out.println("Excel file written at location - " + outputFilePath);
	}

}
