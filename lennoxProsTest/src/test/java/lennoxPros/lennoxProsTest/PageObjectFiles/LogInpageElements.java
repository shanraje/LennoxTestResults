package lennoxPros.lennoxProsTest.PageObjectFiles;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LogInpageElements {
	
	@FindBy(how = How.PARTIAL_LINK_TEXT, using = "Sign")
	public static WebElement signInLink;
	@FindBy(id="j_username")
	public static WebElement userNameTextBox;
	@FindBy(id="j_password")
	public static WebElement passwordTextBox;
	@FindBy(id="loginSubmit")
	public static WebElement loginSubmitButton;
	@FindBy(className="signedin-user")
	public static WebElement signedInUser;
}
