
/*Go to https://www.nykaa.com/
2) Mouseover on Brands and Search L'Oreal Paris
3) Click L'Oreal Paris
4) Check the title contains L'Oreal Paris(Hint-GetTitle)
5) Click sort By and select customer top rated
6) Click Category and click Hair->Click haircare->Shampoo
7) Click->Concern->Color Protection
8)check whether the Filter is applied with Shampoo
9) Click on L'Oreal Paris Colour Protect Shampoo
10) GO to the new window and select size as 175ml
11) Print the MRP of the product
12) Click on ADD to BAG
13) Go to Shopping Bag 
14) Print the Grand Total amount
15) Click Proceed
16) Click on Continue as Guest
17) Check if this grand total is the same in step 14
18) Close all windows
 */
package week4day2Assignment;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2Nykaa {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		// get the url
		driver.get("https://www.nykaa.com/");
		// maximize the window
		driver.manage().window().maximize();
		// mouse over on brands
		WebElement brand = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		// click loreal paris
		driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
		// check whether the title contains loreal paris
		driver.findElement(By.xpath("//div[@id='scroller-container']/div[2]/a[1]")).click();
		String title = driver.getTitle();
		System.out.println(title);
		if (title.contains("L'Oreal Paris")) {
			System.out.println("THE TITLE IS SIMILAR");
		} else {
			System.out.println("THE TITLE IS NOT SIMILAR");
		}
		// click sortby
		driver.findElement(By.xpath("//span[@class='sort-name']")).click();
		// select customer top rated
		driver.findElement(By.xpath("//span[text()='customer top rated']")).click();
		// click category
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		// click hair
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		// click haircare
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		// click shampoo
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		Thread.sleep(5000);
		// click concern-> color protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
		// check whether the filter is applied with shampoo
		String filterresult = driver.findElement(By.xpath("(//button[@class='cross'])[2]/preceding-sibling::span"))
				.getText();
		System.out.println(filterresult);
		if (filterresult.contains("Shampoo")) {
			System.out.println("the filter is not applied");
		} else {
			System.out.println("the filter is applied");
		}
		Thread.sleep(5000);
		// click on loreal paris color protect shampoo
		driver.findElement(By.xpath("//div[contains(@class,'productWrapper')]/div")).click();
		// go to new window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window1.get(1));
		Thread.sleep(5000);
		// click 175ml
		driver.findElement(By.xpath("//select[@title='SIZE']")).click();
		// print the MRP
		String MRP = driver.findElement(By.xpath("(//span[text()='MRP:']/following-sibling::span)[1]")).getText();
		System.out.println(MRP);
		// click on add to bag
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		// click on shopping bag
		driver.findElement(By.xpath("//span[text()='Account']/following::div")).click();
		// print the grand total
		Thread.sleep(5000);
		WebElement iframe = driver.findElement(By.xpath("//div[@id='portal-root']//iframe"));
		driver.switchTo().frame(iframe);
		String gtotal = driver.findElement(By.xpath("//span[text()='Grand Total']/following-sibling::div")).getText();
		System.out.println(gtotal.substring(1));
		gtotal = gtotal.substring(1);
		// click proceed
		driver.findElement(By.xpath("//span[text()='PROCEED']/ancestor::button")).click();
		// click on continue as guest
		driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
		// check the grand total is same
		String total = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		System.out.println(total);
		if (gtotal.equals(total)) {
			System.out.println("the total is not same");
		} else {
			System.out.println("the total is same");

		}
		// close the window
		driver.close();

	}

}
