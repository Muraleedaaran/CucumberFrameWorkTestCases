package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionBigBasket {
	
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	Actions builder;
	
	@Given("Open Browser and set wait")
	public void openBrowserAndSetWait() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.silentOutput", "true"); 
		ChromeOptions options = new ChromeOptions(); 
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		builder = new Actions(driver);
	}

	@Given("Open URLBB")
	public void openURLBB() {
		driver.get("https://www.bigbasket.com");
	}

	@Given("Open Shop By Category tab")
	public void openShopByCategoryTab() {
		//Mouse over to Shop by Category to peek into dropdown list
		//driver.findElement(By.xpath("//li[@class='dropdown full-wid hvr-drop']//a[contains(text(),'Shop by Category')]"))
		
		builder.moveToElement(driver.findElement(By.xpath("//li[@class='dropdown full-wid hvr-drop']//a[contains(text(),'Shop by Category')]"))).perform();
	}

	@Given("Hover to FOODGRAINS, OIL & MASALA -> RICE & RICE PRODUCTS")
	public void hoverToFOODGRAINSOILMASALARICERICEPRODUCTS() throws InterruptedException {
		//Mouse over to FOODGRAINS, OIL & MASALA
		//driver.findElement(By.xpath("(//li[@data-submenu-id='foodgrains-oil-masala'])[2]//a[contains(text(),'Foodgrains, Oil & Masala')]"))
		Thread.sleep(1500);
		builder.moveToElement(driver.findElement(By.xpath("(//li[@data-submenu-id='foodgrains-oil-masala'])[2]//a[contains(text(),'Foodgrains, Oil & Masala')]"))).perform();
		
		//Mouse over to RICE & RICE PRODUCTS
		//driver.findElement(By.xpath("(//a[contains(text(),'Rice & Rice Products')])[2]"))
		
		builder.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Rice & Rice Products')])[2]"))).perform();
		Thread.sleep(1500);
	}

	@Given("Click on BOILED & STEAM RICE")
	public void clickOnBOILEDSTEAMRICE() {
		//Click on BOILED & STEAM RICE
		//driver.findElement(By.xpath("(//a[contains(text(),'Boiled & Steam Rice')])[2]"))
		
		builder.moveToElement(driver.findElement(By.xpath("(//a[contains(text(),'Boiled & Steam Rice')])[2]"))).click().perform();
	}

	@Given("Check the URL of the page with the site Navigation link")
	public void checkTheURLOfThePageWithTheSiteNavigationLink() {
		String uriPath = "foodgrains-oil-masala/rice-rice-products/boiled-steam-rice";
		
		String titleOfPage = driver.getCurrentUrl();
		
		if (titleOfPage.contains(uriPath)) {
			System.out.println("Landed in correct page");
		}
		
		else {
			System.err.println("Landed in wrong page");
		}
		
	}

	@Given("Choose the Brand as bb Royal")
	public void chooseTheBrandAsBbRoyal() throws InterruptedException {
		//Wait till the elements are loaded
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='bb Royal']"))));
		
		
		//Select bbRoyal as desired brand
		Thread.sleep(1500);
		
		driver.findElement(By.xpath("//span[text()='bb Royal']")).click();
	}

	@Given("Go to Ponni Bolied Rice and select {int} kg bag from Dropdown")
	public void goToPonniBoliedRiceAndSelectKgBagFromDropdown(Integer int1) throws InterruptedException {
		//Wait till the elements are visible
		
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@qa='product_name']/a[contains(text(),'Ponni Boiled Rice/Puzhungal Arisi')]/following::div/div//button"))));
//		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@qa='product_name']/a[contains(text(),'Ponni Boiled Rice/Puzhungal Arisi')]/following::div/div//button"))));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("\"//div[@qa='product_name']/a[contains(text(),'Ponni Boiled Rice/Puzhungal Arisi')]/following::div/div//button\"")));
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@qa='product_name']/a[contains(text(),'Ponni Boiled Rice/Puzhungal Arisi')]/following::div/div//button")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//div[@qa='product_name']/a[contains(text(),'Ponni Boiled Rice/Puzhungal Arisi')]/following::div/div//ul[@class='dropdown-menu drop-select']//span[text()='10 kg']")).click();
		
		
	}

	@Given("Click Add button")
	public void clickAddButton() throws InterruptedException {
		driver.findElement(By.xpath("//button[@qa='add']")).click();
		Thread.sleep(3500);
		driver.findElement(By.className("arrow-marker")).click();
	}

	@Given("Go to search box and type Dal")
	public void goToSearchBoxAndTypeDal() {
		driver.findElement(By.xpath("//input[@placeholder='Search for Products..']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Search for Products..']")).sendKeys("Dal");
	}

	@Given("Add Toor\\/Arhar Dal {int}kg and set Qty {int} from the list")
	public void addToorArharDalKgAndSetQtyFromTheList(Integer int1, Integer int2) throws InterruptedException {
		driver.findElement(By.xpath("//div[contains(text(),'2 kg')]/preceding-sibling::div[2]/a/p[text()='Toor/Arhar ']/parent::a/parent::div/following-sibling::div[5]//input")).clear();
		driver.findElement(By.xpath("//div[contains(text(),'2 kg')]/preceding-sibling::div[2]/a/p[text()='Toor/Arhar ']/parent::a/parent::div/following-sibling::div[5]//input")).sendKeys("2");
		driver.findElement(By.xpath("//div[contains(text(),'2 kg')]/preceding-sibling::div[2]/a/p[text()='Toor/Arhar ']/parent::a/parent::div/following-sibling::div[6]//span[text()='ADD']")).click();
		Thread.sleep(3500);
		driver.findElement(By.className("arrow-marker")).click();
		System.out.println("Before before clicking");
		Thread.sleep(1000);
	}

	@Given("Click Address")
	public void clickAddress() {
		System.out.println("Before clicking");
		driver.findElement(By.className("arrow-marker")).click();
		System.out.println("After clicking");
		driver.findElement(By.className("arrow-marker")).click();

	}

	@Given("Select Chennai as City, Alandur{int},Chennai as Area and click Continue")
	public void selectChennaiAsCityAlandurChennaiAsAreaAndClickContinue(Integer int1) {
//		driver.findElement(By.xpath("//input[@type='search' and contains(@class,'form-control ui-select-search ng-valid ng-dirty ng-empty ng-touched')]")).click();
//		driver.findElement(By.xpath("(//span[@class='ui-select-match-text pull-left'])[1]/following-sibling::i[@class='caret pull-right']")).click();
		driver.findElement(By.xpath("//a[text()='Change Location']")).click();
		driver.findElement(By.xpath("(//span[@class='ui-select-match-text pull-left'])[1]/following-sibling::i[@class='caret pull-right']")).click();
//		driver.findElement(By.xpath("//input[@type='search' and contains(@class,'form-control ui-select-search ng-valid ng-dirty ng-empty ng-touched')]")).sendKeys("Chennai", Keys.TAB);
		driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Chennai", Keys.TAB);
//		driver.findElement(By.id("areaselect")).sendKeys("Alandur", Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.DOWN, Keys.ENTER);
//		driver.findElement(By.id("areaselect")).sendKeys("Alandur", Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
		driver.findElement(By.id("areaselect")).sendKeys("Alandur", Keys.TAB);
//		driver.findElement(By.xpath("//button[@type='submit' and @name='continue']")).click();
		driver.findElement(By.name("skipandexplore")).click();
	}

	@When("Mouse over on My Basket")
	public void mouseOverOnMyBasket() {
		//driver.findElement(By.xpath("//a[@qa='myBasket' and @type='button']"))
		builder.moveToElement(driver.findElement(By.xpath("//a[@qa='myBasket' and @type='button']"))).click().perform();
	}

	@Then("Take a screen shot")
	public void takeAScreenShot() throws IOException {
		
		File src = driver.getScreenshotAs(OutputType.FILE); 
	    File dest = new File("./screens/BigBasket_MyBasket.png"); 
	    FileUtils.copyFile(src, dest);
	}

	@Then("Click view Basket and Checkout")
	public void clickViewBasketAndCheckout() throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@qa='myBasket' and @type='button']")).click();
		driver.findElement(By.xpath("//button[@qa='viewBasketMB' and @type='button']")).click();
		
	}

	@Then("Click the close button and close the browser")
	public void clickTheCloseButtonAndCloseTheBrowser() {
		driver.close();
	}

}
