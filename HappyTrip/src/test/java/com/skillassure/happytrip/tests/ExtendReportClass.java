package com.skillassure.happytrip.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendReportClass {
	WebDriver driver;
	ExtentReports extent;
	ExtentHtmlReporter repoter;

	@BeforeSuite
	public void setup() {

		repoter = new ExtentHtmlReporter("./reports/results.html");

		extent = new ExtentReports();

		extent.attachReporter(repoter);

//   		String projectpath = System.getProperty("user.dir");	
	}

	@BeforeTest
	public void setUpTest() {

		System.setProperty("webdriver.chrome.driver",
				"F:\\Selenium\\HappyTrip\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();

	}

	@Test
	public void adminlogintest() {

		// Starts Report

		ExtentTest logger = extent.createTest("TestAdmin_LogIn", "Login to the admin page");

		driver.navigate().to("http://43.254.161.195:8085/happytripcrclean1/loginAdmin.html");

		driver.findElement(By.id("username")).sendKeys("admin@mindtree.com");
		driver.findElement(By.id("password")).sendKeys("admin");

		driver.findElement(By.xpath("//*[@id=\"signInButton\"]")).click();

		logger.log(Status.PASS, "Admin LoggedIn");
		logger.log(Status.INFO, "LogIn to Admin");

		extent.flush();

	}

	@AfterTest
	public void tearDownTest() {
		driver.close();
		driver.quit();
		System.out.println("Test Complete Sucessfully");
	}

	@AfterSuite
	public void tearDown() {
		extent.flush();
	}

}