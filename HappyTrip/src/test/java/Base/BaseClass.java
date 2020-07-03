package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseClass { 
	static WebDriver driver;

	@BeforeMethod

	public void setup()

	{

	System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\HappyTrip\\drivers\\chromedriver.exe");

	driver = new ChromeDriver();

	}

	public WebDriver getDriver()

	{

	return driver;

	}

	@AfterMethod

	public  void tearDown()

	{

	driver.close();

	}

	}

