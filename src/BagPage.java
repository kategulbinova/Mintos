import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BagPage {
	WebDriver driver;
	
    @FindBy(how = How.ID, using = "aBagLink")
    private WebElement bag;
    
    @FindBy(how = How.ID_OR_NAME, using = "txtSearch")
    private WebElement searchBox;
    
    @FindBy(how = How.ID_OR_NAME, using = "cmdSearch")
    private WebElement searchMagnifier;

    @FindBy(how = How.XPATH, using = " //*[@id=\"main-content\"]")
    private WebElement wholePage; 
    
    @FindBy(how = How.XPATH, using = "/html/body/div[4]/div/div/div[2]/div/div[4]/section/form/div/div/div[3]/div[1]/div[3]/div/div/table/tbody/tr")
    private List<WebElement> itemsToBuy;
    
    @FindBy(how = How.ID, using = "lbtnUpdateQtyAndVariants")
    private WebElement updateBagButton; 
    
    public int getBagSize() { //Click all available sizes, chooses last of them.
    	if ( wholePage.getText().contains("Your bag is empty") ) {
    		return 0;
    	} else {
    	return itemsToBuy.size();
    	}
    }
    
    public void clearBag() {
    	if (getBagSize()>0) {
    		int i = 0;
    		for (WebElement item : itemsToBuy) {
    			item.findElement(By.name("Lines["+i+"].Quantity")).clear();
    			item.findElement(By.name("Lines["+i+"].Quantity")).sendKeys("0");;
    			i++;
    		}
    		updateBagButton.click();
    	}
    }
    
}
//List<WebElement> rows = driver.findElements(by.tagname("tr")); rows.size()