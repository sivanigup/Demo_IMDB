package demo;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IMDB {

	public static void main(String[] args) throws Exception {
		String expectedTitle = "Episode Guide";
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\test\\resources\\DriverFile\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.imdb.com/");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement serchbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("suggestion-search")));
		serchbox.sendKeys("games of thrones");
		WebElement Selectoption = driver.findElementByXPath("//div[text()='Game of Thrones']");
		Selectoption.click();
		Thread.sleep(3000);
		WebElement optionsToSelect = driver.findElementByXPath("//div[text()='Episode Guide']");
		if (optionsToSelect.getText().equals(expectedTitle)) {
			System.out.println("correct page is opened: " + expectedTitle);
		} else {
			System.out.println("correct page is not opened");
		}
		TakesScreenshot Scrnshot = ((TakesScreenshot) driver);
		File sorcefile = Scrnshot.getScreenshotAs(OutputType.FILE);
		File Imagelocation = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshot\\test.png");
		try {
			FileUtils.copyFile(sorcefile, Imagelocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
	}
}
