package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

public class RESTAPIConnector {
	
	public void readEndPoint (String urlPath) {
		
		String result = null;
		try
		{
			URL url = new URL(urlPath);
			//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			//Set the request to GET the contents
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");
			connection.connect();
			//Get the response status of the Rest API
			int responseCode = connection.getResponseCode();
			System.out.println("Response code received : " +responseCode);
			//Iterating condition to if response code is not 200 then throw a runtime exception
			//else continue the actual process of getting the JSON data
			if(responseCode != 200) {
				throw new RuntimeException("Failed to get the response from server ! | HttpResponseCode: " + responseCode);
			}
			else {
				//Open a buffered reader and get the input as stream
				BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			    System.out.println("Reading content from server .... \n");
				
			    StringBuffer response = new StringBuffer();
				while ((result = br.readLine()) != null) { 
					response.append(result);

				}
				br.close();

				//Using JSON array, since the API end point returns an array
				JSONArray jsonArray = new JSONArray(response.toString());
				
				//Storing the received contents into a json file
				String jsonFilePath = "./output/parsedAPI.json";
				FileWriter file = new FileWriter(jsonFilePath);
				file.write(jsonArray.toString());
				file.close();
				
				System.out.println("JSON data from REST API endpoint has been successfully written to a json file ! \n");
				System.out.println("Proceeding to write the json data to an excel file ... \n");
				
				//Using the utility created for writing an excel file in the project
				ExcelWriter excelWriter = new ExcelWriter();
				String writeToPath = "./output/parsedAPI.xlsx";
				excelWriter.writeExcel("parsedAPI", jsonFilePath, writeToPath);
				
			    connection.disconnect();
			}
		
		}
		catch (FileNotFoundException fileNotFoundExc) {
			
			System.out.println("The given file path could not be resolved to find a valid file ! \n");
			fileNotFoundExc.printStackTrace();
		}
		catch (IOException ioExc) {
			
			System.out.println("An error occurred during file read operation ! \n");
			ioExc.printStackTrace();
		}
		catch(Exception e) {
			
			System.out.println("An error occured while parsing the Data from REST API end point ! \n");
			e.printStackTrace();
		}
			
	}

}
