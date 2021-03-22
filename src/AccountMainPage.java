import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AccountMainPage {
    
    @FindBy(how = How.ID, using = "accountMenu")
    private WebElement accountMenu;
    
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div/div[2]/div/div[5]/section/div/div[2]/div[2]/div/ul/li[12]/a")
    private WebElement signOutButton;
    
    public boolean isOpen() {
    	try {
    		if (accountMenu.isDisplayed()) {
    			System.out.println("Account main page is open");
    			return true;
    		}
    	} catch  (NoSuchElementException ignored) {}
    	return false;
    } 
    
    public void signOut() {
    	signOutButton.click();
    }
}
