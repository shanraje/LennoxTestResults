package lennoxPros.lennoxProsTest.PageObjectFiles;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NavigationToAddLeadElements {
	
	@FindBy(xpath = "//a[@href=\"#navigation\"]")
	public static WebElement sideMenuHamburgerIcon;
	@FindBy(linkText ="Sales Tools")
	public static WebElement salesToolsOption;
	@FindBy(linkText ="Build A Proposal")
	public static WebElement proposalBuildOption;
	@FindBy(id ="select-lead-model")
	public static WebElement leadSelectionDialog;
	@FindBy(xpath = "//a[text()=\"SELECT LEAD\"]")
	public static WebElement leadSelectButton;
	@FindBy(xpath="//a[@class=\"btn btn-primary hide-mobile introjs-l-9 introjs-l-83\"]//span[text()=\"ADD LEAD\"]")
	public static WebElement adddLeadButton;
	@FindBy(xpath="//h1[text()=\"Add Leads\"]")
	public static WebElement addLeadLabel;
}