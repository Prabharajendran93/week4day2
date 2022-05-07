
package week4day2Assignment;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Action1 {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		// get the url
		driver.get("https://jqueryui.com/resizable");
		
		// maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// handle with frames
		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.xpath("//div[@Class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
		
		// handle with actions
		Actions action = new Actions(driver);
		action.clickAndHold(element).moveByOffset(10, 80).perform();
		
		// close the window
		driver.close();
	}

}