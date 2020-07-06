package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SeleniumWebDriver {

	public WebDriver setBrowser(String browserName) {
		
		String firefox = "Firefox";
		String chrome = "Chrome";
		
		WebDriver driver = null;
		
		if (browserName.toLowerCase().contains(firefox.toLowerCase())) {
			
			System.setProperty("webdriver.gecko.driver","./resources/geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Configured to use Firefox driver !");
		}
		
		else if (browserName.toLowerCase().contains(chrome.toLowerCase())) {
			
			System.setProperty("webdriver.chrome.driver","./resources/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Configured to use Chrome driver !");
		}
		else {
			
			System.out.println("The value for argument browserName must be one of Chrome or Firefox !");
		}
		
		return driver;
	}
}
