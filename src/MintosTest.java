import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;

public class MintosTest {
	private WebDriver driver;
	private List<WebElement> listOfFoundItems;

//--------------------------------------------------------------------------------------------------------------------------------------    
//--------------------------------------------------------------------------------------------------------------------------------------	
	@Test (priority = 1)
	@Parameters ({"url", "searchString"})
	
	public void testSearchFromMainPage(String url, String searchString) {
		Reporter.log("TEST: searching for items from main page without signing in.");
		//Init required pages
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        SearchResultPageNotSignedIn pageShowingWhatFound = PageFactory.initElements(driver, SearchResultPageNotSignedIn.class);
        //Init required pages
        
        Reporter.log("Steps: Search using keyword, then get found items.");
        mainPage.searchFor(searchString);
        listOfFoundItems = pageShowingWhatFound.getFoundItems();
        Reporter.log("Check list of items is not empty.");
        assertTrue(listOfFoundItems.size() > 0, "Found something for the search string " + searchString);
        Reporter.log("List of found items is " + listOfFoundItems.size() + " pcs long.");
	}
//--------------------------------------------------------------------------------------------------------------------------------------    
//--------------------------------------------------------------------------------------------------------------------------------------	
	@Test (priority = 20)
    @Parameters ({"url", "email", "pwd"})
	
	public void testLogin(String url, String email, String pwd) {
		Reporter.log("TEST: signing in with correct credentials.");
		//Init required pages
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        AccountStartingPage accountPage = PageFactory.initElements(driver, AccountStartingPage.class);
        //Init required pages
          
        signIn(email, pwd, url);
        assertTrue(accountPage.isOpen(), "Account starting page is loaded.");
        signOut(url); 
        assertTrue(mainPage.isOpen(), "Main page is loaded - and signed out successfully.");	
	}	
//--------------------------------------------------------------------------------------------------------------------------------------    
//--------------------------------------------------------------------------------------------------------------------------------------	
	
	@Test (priority = 30)
    @Parameters ({"url","email", "pwd", "searchString"})
	//Check it is possible to add and remove one item from the cart.
	
	public void testAddToCart(String url, String email, String pwd, String searchString) {
		Reporter.log("TEST: add and then remove one random item from the bag.");
		// Init required pages
        AccountStartingPage accountStartingPage = PageFactory.initElements(driver, AccountStartingPage.class);
        SearchResultPageSignedIn pageShowingWhatFound = PageFactory.initElements(driver, SearchResultPageSignedIn.class);
        ItemPage itemPage = PageFactory.initElements(driver, ItemPage.class);
        BagPage bagPage = PageFactory.initElements(driver, BagPage.class);
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        // Init required pages
        
        driver.get(url);
        
        Reporter.log("Check initially bag is empty when not signed in. Clean up if not.");
        mainPage.goToBag();
        bagPage.clearBag();
        assertEquals(bagPage.getBagSize(), 0);
        
        Reporter.log("Sign in as '" + email +"' / '" +pwd + "' and check bag is empty for the account. Clean up if not.");
        signIn(email, pwd, url);
        accountStartingPage.goToBag();
        bagPage.clearBag();
        assertEquals(bagPage.getBagSize(), 0);

        Reporter.log("Search for some items with keyword '" + searchString + "', and make sure resulting list is not empty.");
        driver.get(url);
        accountStartingPage.searchFor(searchString);
        listOfFoundItems = pageShowingWhatFound.getFoundItems();
        assertTrue(listOfFoundItems.size() > 0, "Expected to find mopre than 0 items with the search string " + searchString);
        Reporter.log("List of found items is " + listOfFoundItems.size() + " pcs long.");

        listOfFoundItems.get((int)(Math.random() * listOfFoundItems.size() + 1)).click();
        
        Reporter.log("Add one random item to bag.");
        itemPage.chooseSize();
        itemPage.addItem();
        itemPage.goToBag();  
        
        assertEquals( bagPage.getBagSize(), 1, "Expected one item in the bag.");
        
        Reporter.log("Clear bag and check it is empty.");
        bagPage.clearBag();
        assertEquals( bagPage.getBagSize(), 0, "Expected empty bag.");
        
	}	
//--------------------------------------------------------------------------------------------------------------------------------------    
//--------------------------------------------------------------------------------------------------------------------------------------	
	
//-----HELPER METHODS-------------------------------------------------------------------------------------------------------------------	
	public void signIn(String email, String pwd, String url) {
		//Init required pages
        MainPage mainPage = PageFactory.initElements(driver, MainPage.class);
        SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
        //Init required pages
        
        driver.get(url);
        
        mainPage.switchToSignInPage();
        signInPage.login(email, pwd);        

        driver.get(url);
	}


	public void signOut(String url) {
        AccountStartingPage accountPage = PageFactory.initElements(driver, AccountStartingPage.class);   
        AccountMainPage accountMainPage = PageFactory.initElements(driver, AccountMainPage.class);    	
		
        driver.get(url);
		
	    accountPage.goToAccountMainPage();
	    accountMainPage.signOut();		
	    
        driver.get(url);

	}
	
	
	// Prepare for tests + cleanup.
	@BeforeTest
    @Parameters ({"url"})
	public void profileSetup(String url) {
		System.setProperty("webdriver.chrome.driver", "C://ChromeDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(url);
	}
	
	@AfterTest
	public void cleanup() {
		driver.close();
	}
	
}
