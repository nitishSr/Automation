package tools;

import utilities.RESTAPIConnector;

public class APIConnectorImpl {

	public static void main(String[] args) {
		
		RESTAPIConnector apiConnector = new RESTAPIConnector();
		String urlPath = "https://data.sfgov.org/resource/p4e4-a5a7.json";
		apiConnector.readEndPoint(urlPath);
		System.out.println("REST API endpoint parsing completed successfully !");
	}

}
