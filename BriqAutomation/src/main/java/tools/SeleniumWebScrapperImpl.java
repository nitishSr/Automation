package tools;

import org.openqa.selenium.WebDriver;

import utilities.SeleniumWebDriver;
import utilities.SeleniumWebScrapper;

public class SeleniumWebScrapperImpl {

	public static void main(String[] args) {
		
		String browserName = "Chrome";
		SeleniumWebDriver seleniumDriver = new SeleniumWebDriver();
		
		//Configuring the web driver to use specific browser
		WebDriver driver = seleniumDriver.setBrowser(browserName);
		if (driver != null) {
			
			SeleniumWebScrapper webScrapper = new SeleniumWebScrapper();
			String baseUrl = "https://www.bizjournals.com/milwaukee/feature/crane-watch";
			webScrapper.seleniumWebScrapper(driver, baseUrl);
			System.out.println("Selenium script execution completed !");
		}
		else {
			System.out.println("The webdriver could not be set !");
		}
	}

}
