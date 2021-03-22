import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MainPage {
    @FindBy(how = How.CLASS_NAME, using = "login")
    private WebElement signInLink;
    
    @FindBy(how = How.ID_OR_NAME, using = "txtSearch")
    private WebElement searchBox;
    
    @FindBy(how = How.ID_OR_NAME, using = "cmdSearch")
    private WebElement searchMagnifier;
    
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div[1]/div/div[2]/div/div[4]/section/div[3]/div/div/div[1]/a/picture/img")
    private WebElement mainLargePicture;
    
    @FindBy(how = How.ID, using = "bagQuantityContainer")
    private WebElement goToBagLink;
    
    
    public void switchToSignInPage() {
    	signInLink.click();
    }
    
    public void searchFor(String text) {
        searchBox.sendKeys(text);
        searchMagnifier.click();
    }
    
    public boolean isOpen() {
    	try {
    		if (signInLink.isDisplayed() && mainLargePicture.isDisplayed()) return true;
    	} catch (NoSuchElementException ignored) {}
		return false;
	}
    
    public void goToBag() {
    	goToBagLink.click();
    }
}
