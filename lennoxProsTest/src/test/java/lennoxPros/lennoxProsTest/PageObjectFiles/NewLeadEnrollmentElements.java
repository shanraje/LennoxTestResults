package lennoxPros.lennoxProsTest.PageObjectFiles;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class NewLeadEnrollmentElements {
	
	@FindBy(id = "firstName")
	public static WebElement firstNameField;
	@FindBy(id = "lastName")
	public static WebElement lastNameField;
	@FindBy(id = "phNo")
	public static WebElement phoneNumberField;
	@FindBy(id = "email")
	public static WebElement eMailField;
	@FindBy(id = "calender1")
	public static WebElement calenderField;
	@FindBy(xpath = "//table[@class=\"ui-datepicker-calendar\"]//td")
	public static List<WebElement> datePicker;
	@FindBy(linkText = "Add Document")
	public static WebElement documentAddField;
	@FindBy(xpath = "//select[@name=\"documents[1].documentType\"]")
	public static WebElement documentSelectField;
	@FindBy(id = "multipleFileSelect-1")
	public static WebElement documentuploadField;
	@FindBy(xpath = "//span[text()=\"Add To Lead\"]//parent::a[@href=\"javascript:void(0);\"]")
	public static WebElement addToLeadButton;
	@FindBy(id = "multipleImageSelect[0]")
	public static WebElement imagefield;
	@FindBy(id = "btn-addLeadsForm")
	public static WebElement leadSubmitButton;
	@FindBy(xpath = "//li[contains(text(),\"Lead Saved Successfully\")]")
	public static WebElement successMessage;

}