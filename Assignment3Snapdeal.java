package week4day2Assignment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

/*1. Launch https://www.snapdeal.com/
2. Go to Mens Fashion
3. Go to Sports Shoes
4. Get the count of the sports shoes
5. Click Training shoes
6. Sort by Low to High
7. Check if the items displayed are sorted correctly
8.Select the price range (900-1200)
9.Filter with color Navy 
10 verify the all applied filters 
11. Mouse Hover on first resulting Training shoes
12. click QuickView button
13. Print the cost and the discount percentage
14. Take the snapshot of the shoes.
15. Close the current window
16. Close the main window package week4day2Assignment*/
public class Assignment3Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// get the url
		driver.get(" https://www.snapdeal.com/");
		// maximize the window
		driver.manage().window().maximize();

		// mouseover on mens fashion
		WebElement Element = driver.findElement(By.xpath("//span[@class='labelIcon']/following-sibling::span"));
		Actions builder = new Actions(driver);
		builder.moveToElement(Element).perform();

		// mouseover on sports shoes
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();

		// Get the count of the sports shoes
		String count = driver.findElement(By.xpath("//a[@class='sub-cat-node dp-widget-link hashAdded']")).getText();
		System.out.println("the count of sports shoes is" + count);

		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

		// Sort by Low to High
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']/following-sibling::ul/li[@data-index='1']"))
				.click();

		// check if the items are sorted correctly
		List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		String firstPrice = priceElements.get(0).getAttribute("data-price");
		String lastPrice = priceElements.get(priceElements.size() - 1).getAttribute("data-price");

		if (Integer.parseInt(firstPrice) < Integer.parseInt(lastPrice)) {
			System.out.println("The Items are Sorted correctly");
		} else {
			System.out.println("The Items are not Sorted correctly");
		}
		Thread.sleep(3000);

		// Select the price range (900-1200)
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow')]")).click();

		// Filter with color Navy
		driver.findElement(
				By.xpath("//div[@data-name='Color_s']/following-sibling::button[contains(text(),'View More')]"))
				.click();
		WebElement navy = driver.findElement(By.xpath("//label[@for='Color_s-Navy']/preceding-sibling::input"));
		Boolean navycolour = navy.isEnabled();
		if (navycolour) {
			navy.click();
		} else {
			System.out.println("navy is not enabled");

		}
		// verify the all applied filters
		String filterprice = driver.findElement(By.xpath("//a[@class='clear-filter-pill']")).getText();
		if (filterprice.equals("Rs.900-RS1200")) {
			System.out.println("THE FILTER IS APPLIED CORRECTLY");

		} else {
			System.out.println("THE FILTER IS  NOT APPLIED CORRECTLY");
		}

		// 11. Mouse Hover on first resulting Training shoes
		WebElement firsttrainingshoe = driver.findElement(By.xpath("(//img[@class='product-image wooble'])[1]"));
		Actions builder1 = new Actions(driver);
		builder1.moveToElement(firsttrainingshoe).pause(5000).perform();

		// 12. click QuickView button
		driver.findElement(By.xpath("(//div[@class='clearfix row-disc']/div)[1]")).click();

		// 13. Print the cost and the discount percentage

		String priceValue = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("The Price of the Shoe is Rs " + priceValue);
		System.out.println("The Discount of the Shoe is " + discount);
		Thread.sleep(2000);

		// 14. Take the snapshot of the shoes
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./screenshots/shoe.png");
		FileUtils.copyFile(source, destination);

		// 15. Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i[contains(@class,'icon-delete-sign')]"))
				.click();

		// 16.Close the main window
		driver.close();

	}

}
