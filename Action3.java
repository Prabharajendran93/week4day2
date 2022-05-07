package week4day2Assignment;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Action3 {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// get the url
		driver.get("http://www.leafground.com/pages/drop.html");

		// maximize the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// take the Xpath of the image
		WebElement elementsource = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement elementdestination = driver.findElement(By.xpath("//div[@id='droppable']"));

		// handle with actions
		Actions builder = new Actions(driver);
		builder.dragAndDrop(elementsource, elementdestination).pause(3000).perform();

		// close the window
		driver.close();
	}

}
