import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SearchResultPageNotSignedIn {
    @FindBy(how = How.CLASS_NAME, using = "login")
    private WebElement signInLink;
    
    @FindBy(how = How.ID_OR_NAME, using = "txtSearch")
    private WebElement searchBox;
    
    @FindBy(how = How.ID_OR_NAME, using = "cmdSearch")
    private WebElement searchMagnifier;
    
    @FindBy(how = How.XPATH, using="/html/body/div[6]/div/div/div[2]/div/div[4]/section/div[2]/div[3]/div/div[2]/div[2]/ul/li")
    private List<WebElement> foundItems;
    
    public void switchToSignInPage() {
    	signInLink.click();
    }
    
    public void searchFor(String text) {
        searchBox.sendKeys(text);
        searchMagnifier.click();
    }
    
    public List<WebElement> getFoundItems() {
    	return foundItems;
    }
    
}
