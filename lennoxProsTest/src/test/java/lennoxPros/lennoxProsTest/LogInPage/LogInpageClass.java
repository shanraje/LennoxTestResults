package lennoxPros.lennoxProsTest.LogInPage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import io.cucumber.messages.internal.com.google.protobuf.ByteString.Output;
import lennoxPros.lennoxProsTest.CommonFunctionalities.CommonFunctions;
import lennoxPros.lennoxProsTest.PageObjectFiles.LogInpageElements;
import lennoxPros.lennoxProsTest.PageObjectFiles.NavigationToAddLeadElements;
import lennoxPros.lennoxProsTest.PageObjectFiles.NewLeadEnrollmentElements;



public class LogInpageClass extends CommonFunctions {
	@Test
	public void loginPage() throws InterruptedException, IOException
	{
		extentReportTestCase= extentReportObject.createTest("Login to the LennoxPros site with valid credentials");
		extentReportTestCase.log(Status.INFO, "Trying to signin to the LennoxPros website");
		PageFactory.initElements(driver,LogInpageElements.class);
		explicitWait.until(ExpectedConditions.elementToBeClickable(LogInpageElements.signInLink));
		LogInpageElements.signInLink.click();
		extentReportTestCase.log(Status.INFO, "Providing the username to sigin");
		explicitWait.until(ExpectedConditions.visibilityOf(LogInpageElements.userNameTextBox));
		LogInpageElements.userNameTextBox.sendKeys("lenproautomation8@lenqat.com");
		extentReportTestCase.log(Status.INFO, "Providing the respective password");
		explicitWait.until(ExpectedConditions.visibilityOf(LogInpageElements.passwordTextBox));
		LogInpageElements.passwordTextBox.sendKeys("Community17");
		explicitWait.until(ExpectedConditions.visibilityOf(LogInpageElements.loginSubmitButton));
		LogInpageElements.loginSubmitButton.click();
		explicitWait.until(ExpectedConditions.visibilityOf(LogInpageElements.signedInUser));
		String expectedUrl = LogInpageElements.signedInUser.getText();
		String actualUrl = "Lenproautomation8";
		Assert.assertEquals(actualUrl, expectedUrl);
		if(StringUtils.equals(expectedUrl, actualUrl)) {
			extentReportTestCase.log(Status.INFO, "Yayyy !!! Given the valid credentails.Hence, Sigining in !!");
			takeScreenShotMethod("Positive_login");
		}else {
			extentReportTestCase.log(Status.INFO, "Oops !! Given the Invalid credentails.Hence, test failed.");
			takeScreenShotMethod("Negative_login");
		}
	}

	@Test(dependsOnMethods = "loginPage")
	public void navigateToAddLeadOption() throws InterruptedException, IOException
	{
		extentReportTestCase.log(Status.INFO, "Navigation to the add lead section to add a new lead.");
		extentReportTestCase= extentReportObject.createTest("Navigation to the lead adding section ");
		PageFactory.initElements(driver,NavigationToAddLeadElements.class);
		explicitWait.until(ExpectedConditions.elementToBeClickable(NavigationToAddLeadElements.sideMenuHamburgerIcon));
		NavigationToAddLeadElements.sideMenuHamburgerIcon.click();
		extentReportTestCase.log(Status.INFO, "Clicking the Sales option in sidemenu");
		explicitWait.until(ExpectedConditions.elementToBeClickable(NavigationToAddLeadElements.salesToolsOption));
		NavigationToAddLeadElements.salesToolsOption.click();
		extentReportTestCase.log(Status.INFO, "Clicking Build A proposal option");
		explicitWait.until(ExpectedConditions.elementToBeClickable(NavigationToAddLeadElements.proposalBuildOption));
		NavigationToAddLeadElements.proposalBuildOption.click();
		explicitWait.until(ExpectedConditions.visibilityOf(NavigationToAddLeadElements.leadSelectionDialog));
		explicitWait.until(ExpectedConditions.elementToBeClickable(NavigationToAddLeadElements.leadSelectButton));
		NavigationToAddLeadElements.leadSelectButton.sendKeys("\n");;
		explicitWait.until(ExpectedConditions.elementToBeClickable(NavigationToAddLeadElements.adddLeadButton));
		NavigationToAddLeadElements.adddLeadButton.click();		
		String expectedLabel = NavigationToAddLeadElements.addLeadLabel.getText();
		String actualLabel = "Add Leads";
		Assert.assertEquals(actualLabel, expectedLabel);
		if(StringUtils.equals(actualLabel, expectedLabel)) {
			extentReportTestCase.log(Status.INFO, "Reached the enrollment page successfully.");
			takeScreenShotMethod("Positive_Navigation");
		}else {
			extentReportTestCase.log(Status.INFO, "Some navigation issues.Thus, test case failed.");
			takeScreenShotMethod("Negative_Navigation");
		}
	}

	@Test(dependsOnMethods = "navigateToAddLeadOption",dataProvider = "testDataFromExcel")
	public void newLeadEnrollment(String firstName,String lastName,String phoneNumber,String emailId,String date,String doc,String image) throws InterruptedException, AWTException, ParseException, IOException
	{
		extentReportTestCase= extentReportObject.createTest("Enrollment of new leads using multiple data sets");
		extentReportTestCase.log(Status.INFO, "In the beginning of  new lead enrollment process");
		Random rand = new Random(); 
		int randomName = rand.nextInt(1000);
		PageFactory.initElements(driver,NewLeadEnrollmentElements.class);
		extentReportTestCase.log(Status.INFO, "Loading the values from external excel file");
		explicitWait.until(ExpectedConditions.visibilityOf(NewLeadEnrollmentElements.firstNameField));
		NewLeadEnrollmentElements.firstNameField.sendKeys(firstName+randomName);
		explicitWait.until(ExpectedConditions.visibilityOf(NewLeadEnrollmentElements.lastNameField));
		NewLeadEnrollmentElements.lastNameField.sendKeys(lastName+randomName);
		explicitWait.until(ExpectedConditions.visibilityOf(NewLeadEnrollmentElements.phoneNumberField));
		NewLeadEnrollmentElements.phoneNumberField.sendKeys(phoneNumber+randomName);
		explicitWait.until(ExpectedConditions.visibilityOf(NewLeadEnrollmentElements.eMailField));
		NewLeadEnrollmentElements.eMailField.sendKeys(emailId);
		JavascriptExecutor	executer = (JavascriptExecutor) driver;
		executer.executeScript("window.scrollBy(0,1800)");
		new Actions(driver).moveToElement(NewLeadEnrollmentElements.calenderField).click().perform();
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date datetobeSelected = (Date)formatter.parse(date);
		date= String.valueOf(datetobeSelected.getDate());
		for(WebElement dateElement:NewLeadEnrollmentElements.datePicker)
		{
			String dateOnCalender =dateElement.getText();
			if(dateOnCalender.equalsIgnoreCase(date))
			{
				dateElement.click();
				break;
			}
		}
		executer.executeScript("window.scrollBy(0,2500)");
		explicitWait.until(ExpectedConditions.elementToBeClickable(NewLeadEnrollmentElements.documentAddField));
		NewLeadEnrollmentElements.documentAddField.click();
		Select selectDocumentType = new Select(NewLeadEnrollmentElements.documentSelectField);
		selectDocumentType.selectByValue("OTHER");
		NewLeadEnrollmentElements.documentuploadField.sendKeys(doc);
		extentReportTestCase.log(Status.INFO, "Uploading document and image files");
		explicitWait.until(ExpectedConditions.elementToBeClickable(NewLeadEnrollmentElements.addToLeadButton));
		NewLeadEnrollmentElements.addToLeadButton.sendKeys("\n");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class=\"choose-image\"]")));
		NewLeadEnrollmentElements.imagefield.sendKeys(image);
		NewLeadEnrollmentElements.leadSubmitButton.click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[contains(text(),\"Lead Saved Successfully\")]")));
		String requiredMessage = "Lead Saved Successfully";
		String actualMessage = NewLeadEnrollmentElements.successMessage.getAttribute("textContent");
		Assert.assertEquals(actualMessage.trim(), requiredMessage);
		if(StringUtils.equals(actualMessage.trim(), requiredMessage)) {
			extentReportTestCase.log(Status.INFO, "Added the lead Successfully");
			takeScreenShotMethod("Success_leadEnrollment");
		}else {
			extentReportTestCase.log(Status.INFO, "Lead is not added due to some issue.Please check !");
			takeScreenShotMethod("Failure_leadEnrollment");
		}
		driver.navigate().back();
		driver.navigate().refresh();


	}

	@DataProvider(name="testDataFromExcel")
	public Object[][] testDataFromExcel() throws InterruptedException, IOException
	{
		fileInputObject = new FileInputStream("C:\\Users\\SHANATHRAJ\\Desktop\\TestData.xls"); 
		workbookObject = new HSSFWorkbook(fileInputObject);
		sheetObject = workbookObject.getSheetAt(0);
		int rowCount = sheetObject.getPhysicalNumberOfRows();
		int colCount = sheetObject.getRow(0).getLastCellNum();
		Object[][] leadData = new Object[rowCount-1][colCount];
		for (int i=1; i<rowCount;i++){
			for (int j=0; j<colCount;j++){
				leadData[i-1][j] = getCellData(i, j);
			}
		}
		return leadData;
	}

	public  String getCellData(int row, int col){

		try {
			rowObject = sheetObject.getRow(row);
			columnObject = rowObject.getCell(col);
			if (rowObject == null || columnObject == null)
			{
				return "";
			}
			else if(columnObject.getCellType() == CellType.NUMERIC) {
				return String.valueOf(columnObject.getDateCellValue()); 
			}else {
				return columnObject.getStringCellValue();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return "Searched row " + row + " or column " + col+ " does not exist in the given file";
		}

	}
	
	
	public  void takeScreenShotMethod(String fileName) throws IOException{
		
		fileName=fileName+".png";
		TakesScreenshot screenshotObject = (TakesScreenshot) driver;
		File sourceFile = screenshotObject.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(fileName);
		FileHandler.copy(sourceFile,destinationFile);
		String loggingInformation = "The image shows"+fileName;
		extentReportTestCase.addScreenCaptureFromPath(fileName, loggingInformation)	;
		
	}
	
	
	

}
