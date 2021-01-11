package lennoxPros.lennoxProsTest.CommonFunctionalities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class CommonFunctions {

	public static WebDriver driver;
	public static WebDriverWait explicitWait;
	Properties propertiesObject = new Properties();
	public static FileInputStream fileInputObject; 
	public static HSSFWorkbook workbookObject;
	public static HSSFSheet sheetObject;
	public static HSSFRow rowObject ;
	public static HSSFCell columnObject ;
	public static ExtentReports extentReportObject;
	public static ExtentHtmlReporter htmlExtentReporter;
	public static ExtentTest extentReportTestCase;

	private void loadPropertyFile() throws IOException {

		FileInputStream inputObject = new FileInputStream("C:\\Users\\SHANATHRAJ\\eclipse-workspace\\lennoxProsTest\\src\\test\\java\\lennoxPros\\lennoxProsTest\\ConfigurationFiles\\config.properties");
		propertiesObject.load(inputObject);
	}

	@BeforeSuite
	public void executeBeforeSuite() throws IOException
	{
		loadPropertyFile();
		if((propertiesObject.getProperty("driver")).equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "E:\\selenium backup\\chromedriver_win32\\chromedriver.exe");
		}
		extentReportObject = new ExtentReports();
		htmlExtentReporter = new ExtentHtmlReporter("LeadEnrollmentReport.html");
		extentReportObject.attachReporter(htmlExtentReporter);
		driver = new ChromeDriver();
		driver.navigate().to(propertiesObject.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 20);
	}

	@BeforeTest
	public void executeBeforeTest()
	{
		driver.manage().deleteAllCookies();
	}

	@AfterTest
	public void executeAfterTest()
	{
		driver.close();
	}
	
	@AfterSuite
	public void executeAfterSuite()
	{
		driver.quit();
		extentReportObject.flush();
	}
}
