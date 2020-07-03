package Util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseClass;

public class HappyTripLogIn extends BaseClass{
	WebDriver driver;
//
//	@BeforeTest
//	public void setup() {
//		System.setProperty("webdriver.chrome.driver", "F:\\Selenium\\HappyTrip\\drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//
//	}
//
//	@AfterMethod
//	public void teardown() {
//		driver.quit();
//	}

	@Test(dataProvider = "Login")
	public void logintoAdmin(String userName, String Password) {

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("http://43.254.161.195:8085/happytripcrclean1/");

		driver.findElement(By.linkText("Log in as admin")).click();
		WebElement username = driver.findElement(By.id("username"));
		username.click();
		username.clear();
		username.sendKeys(userName);

		WebElement password = driver.findElement(By.id("password"));
		password.click();
		password.clear();
		password.sendKeys(Password);

		driver.findElement(By.id("signInButton")).click();
		
		
		driver.findElement(By.linkText(("Schedule Flight"))).click();
		
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(src, new File("F://Selenium//HappyTrip//screenshots//screenshots.png"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	static String projectpath;
	static XSSFWorkbook workbook = null;
	static XSSFSheet sheet = null;
	static XSSFRow row = null;
	static XSSFCell cell = null;

	public static void main(String args[]) {

	}

	@DataProvider(name = "Login")
	public static Object[][] testData() throws IOException {
		projectpath = System.getProperty("user.dir");
		workbook = new XSSFWorkbook("F:\\Selenium\\HappyTrip\\src\\test\\resources\\TestData.xlsx");

		sheet = workbook.getSheet("Sheet1");

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum() + 1;

		int colCount = sheet.getRow(0).getLastCellNum();

		Object data[][] = new Object[rowCount - 1][colCount];

		for (int rNum = 2; rNum <= rowCount; rNum++) {
			for (int cNum = 0; cNum < colCount; cNum++) {
				System.out.print(getCellData("Sheet1", cNum, rNum) + " "); // Your sheet name
				data[rNum - 2][cNum] = getCellData("Sheet1", cNum, rNum); // Your sheet name
			}
			System.out.println();
		}

		return data;

	}

	public static String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == CellType.STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA) {
				String cellText = String.valueOf(cell.getNumericCellValue());
				return cellText;
			} else if (cell.getCellType() == CellType.BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {
			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist in xls";
		}
	}
}