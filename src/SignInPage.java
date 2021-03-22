import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SignInPage {
    @FindBy(how = How.NAME, using = "Login.EmailAddress")
    private WebElement loginField;
    
    @FindBy(how = How.NAME, using = "Login.Password")
    private WebElement pwdField;

    @FindBy(how = How.ID_OR_NAME, using = "LoginButton")
    private WebElement signInButton;

    @FindBy(how = How.CLASS_NAME, using = "dnnFormMessage dnnFormValidationSummary field-validation-error")
    private WebElement wrongInputMessage;
    
    @FindBy(how = How.ID_OR_NAME, using = "txtSearch")
    private WebElement searchBox;
    
    public boolean isOpen() {
    	try {
    		if (signInButton.isDisplayed()) {
    			return true;
    		}
    	} catch  (NoSuchElementException ignored) {}
    	return false;
    }
    
    public void login(String email, String pwd) {
    	loginField.sendKeys(email);
    	pwdField.sendKeys(pwd);
    	signInButton.click();
    }
    
    public void searchFor(String text) {
        searchBox.sendKeys(text);
        searchBox.submit();
    }
}
