import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Reporter;

public class ItemPage {
	//private List<Integer> listOfSize;

    @FindBy(how = How.ID, using = "ulSizes")
    private List<WebElement> listOfSizeElements;

    @FindBy(how = How.ID, using = "aAddToBag")
    private WebElement addToBagButton;
    
    @FindBy(how = How.ID, using = "ProductQty")
    private WebElement productQuantity;
    
    @FindBy(how = How.ID, using = "lblSellingPrice")
    private WebElement price;
    
    @FindBy(how = How.ID, using = "aBagLink")
    private WebElement bag;
    
    
    public List<WebElement> getAllSizes() {
    	return this.listOfSizeElements;
    }
    
    public String getPrice() {   	
    	Reporter.log("Price is " + price.getText());
    	return price.getText();
    }
    
    public void addItem() {
       	addToBagButton.click();
    }
    public void goToBag () {
    	bag.click();
    }
    
    public void chooseSize() { //Click all available sizes, chooses last of them.
        for (int i=0; i<getAllSizes().size(); i++) { //Click all available and choose last non-greyed-out size for the item.
        	if	(!getAllSizes().get(i).getAttribute("class").contains("greyOut")) {
        		getAllSizes().get(i).click(); 
        	}
        }
    }
    
}
