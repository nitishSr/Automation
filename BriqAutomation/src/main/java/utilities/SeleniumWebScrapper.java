package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumWebScrapper {

	public void seleniumWebScrapper (WebDriver driver, String url) {
		
		driver.get(url);
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		System.out.println("Opening the Maps ... \n");
		
		String urlToMapsPage = "https://www.bizjournals.com/milwaukee/maps/project-watch";
		driver.navigate().to(urlToMapsPage);
		
		driver.manage().window().fullscreen();
		
		//locate the map frame		
		WebElement frame = driver.findElement(By.className("cw-map"));
		driver.switchTo().frame(frame);
		
		System.out.println("Map located");
		
		driver.close();
	}
}
