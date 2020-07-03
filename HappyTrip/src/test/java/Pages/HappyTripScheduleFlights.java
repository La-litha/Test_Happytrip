package Pages;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Base.BaseClass;
public class HappyTripScheduleFlights{
	WebDriver driver;			
	public Logger log;
    ExtentReports extent;
	ExtentHtmlReporter reporter;
	
	@BeforeSuite
	public void setup() {
   		
   		reporter = new ExtentHtmlReporter("./reports/results.html");
   		
   		extent = new ExtentReports();
		
		extent.attachReporter(reporter);
   
	}
	@BeforeTest			
	public void setupTest()			
	{			
		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\HappyTrip\\drivers\\chromedriver.exe");		
		driver = new ChromeDriver();		
				
	}			
				
	@AfterTest			
	public void tearDownTest()			
	{			
		driver.close();		
				
	}	
	
	@Test
	public void adminlogintest() {
		
		// Starts Report
		
		ExtentTest logger =	extent.createTest("TestAdmin_LogIn","Login to the admin page");
		
		driver.navigate().to("http://43.254.161.195:8085/happytripcrclean1/loginAdmin.html");
		
		driver.findElement(By.id("username")).sendKeys("admin@mindtree.com");
		driver.findElement(By.id("password")).sendKeys("admin");
		
		driver.findElement(By.xpath("//*[@id=\"signInButton\"]")).click();
	
		logger.log(Status.PASS, "Admin LoggedIn");
		logger.log(Status.INFO, "Admin Login Success");
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("F://Selenium//HappyTrip//screenshots//loginSS.png"));
			System.out.println("Test Case Passed Successfully");
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
	
		
		
	}
	
	@Test(description="This test is for testing schedule Flight")
	public void scheduleFlight() throws InterruptedException {
		
	
		ExtentTest logger1 = extent.createTest("Schedule Flight","Login to the schedule Flight page");
		
		//Navigate to Schedule flights
        driver.findElement(By.linkText(("Schedule Flight"))).click();
        logger1.log(Status.PASS, "Schedule Flight Navigation ");
        logger1.log(Status.INFO, "Clicked to Schedule Flight Successfully");
		
		//Choose Flights
		Select flight = new Select(driver.findElement(By.id("flight")));
		flight.selectByVisibleText("Vistara101");
	    logger1.log(Status.PASS, " Flight Been Choosed");
	    logger1.log(Status.INFO, " Selected Flight Successfully ");
	    
	    
	    //Choose Routes
	    Select Route = new Select(driver.findElement(By.id("route")));
		Route.selectByIndex(7);
        logger1.log(Status.PASS, " Flight Selected from Dropdown");
	    logger1.log(Status.INFO, " Selected Flight Successfully");
	    	    
	  //Distance
	    driver.findElement(By.id("distance")).sendKeys("20000");
	    logger1.log(Status.PASS, " Enter the distance to be Travelled");
	    logger1.log(Status.INFO, "Distance Added Successfully ");
	    Thread.sleep(5000);
	    
	    //Departure Date
	    driver.findElement(By.id("departureDate")).click();
	    driver.findElement(By.xpath("//*[@id=\"AddSchedule\"]/dl/dd[6]/img")).click();
	    driver.findElement(By.partialLinkText("18")).click();
	    logger1.log(Status.PASS, " Enter The Departure Date");
	    logger1.log(Status.INFO, " departure Date added Successfully ");
	    Thread.sleep(5000);
	    
	    //departure Time
	    Select DepartureTime = new Select(driver.findElement(By.id("departureTime")));
	    DepartureTime.selectByIndex(3);
        logger1.log(Status.PASS, " Departure Time Select from the Dropdown");
	    logger1.log(Status.INFO, "Departure Time Successfull");
	    Thread.sleep(5000);
	    
	    //Arrival Date
	    driver.findElement(By.id("arrivalDate")).click();
	    driver.findElement(By.xpath("//*[@id=\"AddSchedule\"]/dl/dd[8]/img")).click();
	    driver.findElement(By.partialLinkText("30")).click();
	    logger1.log(Status.PASS, " Enter The Arrival Date");
	    logger1.log(Status.INFO, " Arrival Date added Successfully");
	    Thread.sleep(5000);
	  
	    
	    //Arrival Time
	    Select ArrivalTime = new Select(driver.findElement(By.id("arrivalTime")));
	    ArrivalTime.selectByIndex(5);
        logger1.log(Status.PASS, " Arrival Time Selected from the Dropdown");
	    logger1.log(Status.INFO, " Arrival Time Successfull ");
	    Thread.sleep(5000);
	     
		
	  // Test Case 9 : Enter the Economy cost
        driver.findElement(By.id("classEconomy")).sendKeys("1800");
	    logger1.log(Status.PASS, " Enter the Economy Cost ");
	    logger1.log(Status.INFO, " Cost added Successfully ");
	    Thread.sleep(5000);
	    
	    
	    
	    //Test Case10: Clicking Add Button
	    driver.findElement(By.id("signInButton")).click();
	    logger1.log(Status.PASS, "Clicking On Add Button");
	    logger1.log(Status.INFO, " Add Button Clicked Successfully");
	    Thread.sleep(5000);

	    
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("F://Selenium//HappyTrip//screenshots//scheduleSS.png"));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		extent.flush();
	}
	@AfterSuite
	public void tearDown() {
		extent.flush();
	}
}
