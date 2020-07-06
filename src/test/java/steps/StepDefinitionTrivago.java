package steps;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionTrivago {
	
	public RemoteWebDriver driver;
	public WebDriverWait wait;
	Actions builder;
	JavascriptExecutor js;
	Set<String> windowHandler;
	List<String> windowHandles;
	
	@Given("Open Browser and set Properties")
	public void openBrowserAndSetProperties() {
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

	@Given("Open URLT")
	public void openURLT() {
		driver.get("https://www.trivago.in/");
	}

	@Given("Type Destination")
	public void typeDestination() throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,600)");
		driver.findElement(By.id("querytext")).sendKeys("Agra", Keys.DOWN, Keys.TAB);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(1500);
	}

	@Given("Choose Date")
	public void chooseDate() throws InterruptedException {
		driver.findElement(By.xpath("//button[@class='cal-btn-next']//span")).click();
		builder.moveToElement(driver.findElement(By.xpath("//time[@datetime='2020-08-16']"))).click().perform();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@class='cal-btn-next']//span")).click();
		Thread.sleep(1000);
		builder.moveToElement(driver.findElement(By.xpath("//time[@datetime='2020-09-16']"))).click().perform();
		Thread.sleep(1500);
	}

	@Given("Select Room")
	public void selectRoom() {
		driver.findElement(By.xpath("//span[text()='Family rooms']")).click();
	}

	@Given("Choose occupants")
	public void chooseOccupants() {
		Select selectDD = new Select(driver.findElement(By.id("select-num-adults-2")));
		selectDD.selectByVisibleText("2");
		
		selectDD = new Select(driver.findElement(By.id("select-num-children-2")));
		selectDD.selectByVisibleText("1");
		
		selectDD = new Select(driver.findElement(By.id("select-ages-children-2-3")));
		selectDD.selectByVisibleText("4");
	}

	@Given("Click Confirm button and click Search")
	public void clickConfirmButtonAndClickSearch() throws InterruptedException {
		Thread.sleep(1500);
		driver.findElement(By.xpath("//span[text()='Confirm']")).click();
		Thread.sleep(1500);
		driver.findElement(By.xpath("//button[@data-qa='search-button']")).click();
	}

	@Given("Select Accommodation type as Hotels only and choose {int} stars")
	public void selectAccommodationTypeAsHotelsOnlyAndChooseStars(Integer int1) {
		builder.moveToElement(driver.findElement(By.xpath("//strong[text()='Accommodation']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath("//label[@for='acc-type-filter-1']"))).click().perform();
		builder.moveToElement(driver.findElement(By.xpath("//button[@value='1320/105']//span[1]"))).click().perform();
	}

	@Given("Select Guest rating as Very Good")
	public void selectGuestRatingAsVeryGood() throws InterruptedException {
		Thread.sleep(1500);
		builder.moveToElement(driver.findElement(By.xpath("//strong[text()='Guest rating']"))).perform();
		builder.moveToElement(driver.findElement(By.xpath("//span[text()='Very good']"))).click().perform();
		Thread.sleep(1500);
	}

	@Given("Set Hotel Location as Agra Fort and click Done")
	public void setHotelLocationAsAgraFortAndClickDone() {
		builder.moveToElement(driver.findElement(By.xpath("//strong[text()='Hotel location']"))).perform();
		Select selDD = new Select(driver.findElement(By.id("pois")));
		selDD.selectByVisibleText("Agra Fort");
		builder.moveToElement(driver.findElement(By.id("filter-popover-done-button"))).click().perform();
	}

	@Given("Select Air conditioning, Restaurant and WiFi and click Done")
	public void selectAirConditioningRestaurantAndWiFiAndClickDone() throws InterruptedException {
		Thread.sleep(1500);
		builder.moveToElement(driver.findElement(By.xpath("//strong[text()='More filters']"))).perform();
		builder.moveToElement(driver.findElement(By.id("more-filters-search"))).click().sendKeys("Air conditioning").perform();
		Thread.sleep(500);
		builder.moveToElement(driver.findElement(By.xpath("//mark[text()='conditioning']"))).click().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("more-filters-search")).clear();
		builder.moveToElement(driver.findElement(By.id("more-filters-search"))).click().sendKeys("Restaurant").perform();
		Thread.sleep(500);
		builder.moveToElement(driver.findElement(By.xpath("//mark[text()='Restaurant']"))).click().perform();
		Thread.sleep(1000);
		driver.findElement(By.id("more-filters-search")).clear();
		builder.moveToElement(driver.findElement(By.id("more-filters-search"))).click().sendKeys("WiFi").perform();
		Thread.sleep(500);
		builder.moveToElement(driver.findElement(By.xpath("(//mark[text()='WiFi'])[1]"))).click().perform();
		Thread.sleep(500);
		builder.moveToElement(driver.findElement(By.id("filter-popover-done-button"))).click().perform();
		Thread.sleep(2500);
	}

	@When("Sort the result as Rating & Recommended")
	public void sortTheResultAsRatingRecommended() throws InterruptedException {
		Select selDD = new Select(driver.findElement(By.id("mf-select-sortby")));
		selDD.selectByVisibleText("Rating & Recommended");
		Thread.sleep(2000);
	}

	@Then("Print the Hotel name, Rating, Number of Reviews and Click View Deal")
	public void printTheHotelNameRatingNumberOfReviewsAndClickViewDeal() {
		System.out.println("Hotel Name -> "+driver.findElement(By.xpath("(//span[@data-qa='item-name'])[1]")).getText());
		System.out.println("Rating -> "+driver.findElement(By.xpath("//div[@itemprop='starRating']/meta")).getAttribute("content"));
		System.out.println("Number of Reviews -> "+driver.findElement(By.xpath("//span[@class='review']/meta[2]")).getAttribute("content"));
		driver.manage().deleteAllCookies();
		driver.findElement(By.xpath("(//span[text()='View Deal'])[1]")).click();
	}

	@Then("Print the URL of the Page")
	public void printTheURLOfThePage() throws InterruptedException {
		System.out.println();
		Thread.sleep(2000);
		windowHandler = driver.getWindowHandles();
		windowHandles = new LinkedList<>(windowHandler);
		driver.switchTo().window(windowHandles.get(1));
		System.out.println("Url of forwarded page -> "+driver.getCurrentUrl());
	}

	@Then("Print the Price of the Room and click Choose Your Room")
	public void printThePriceOfTheRoomAndClickChooseYourRoom() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='PriceSummary']"))));
		String hotelPrice = driver.findElement(By.xpath("//div[@class='HitInfo__price']//span[@class='PriceSummary']//strong")).getText();
		System.out.println("Hotel price per night is "+hotelPrice);
		driver.findElement(By.xpath("(//div[@class='media-flex__body']//h2)[1]")).click();
		windowHandler.clear();
		windowHandles.clear();
		windowHandler = driver.getWindowHandles();
		windowHandles = new LinkedList<>(windowHandler);
		driver.switchTo().window(windowHandles.get(2));
	}

	@Then("Click Reserve and I'll Reserve")
	public void clickReserveAndILlReserve() throws IOException, InterruptedException {
		Thread.sleep(2000);
		File src = driver.getScreenshotAs(OutputType.FILE); 
	    File dest = new File("./screens/Trivago_Price.png"); 
	    FileUtils.copyFile(src, dest);
	    driver.findElement(By.xpath("//span[contains(text(),'Book Now')]")).click();
	}

	@Then("Close the browser")
	public void closeTheBrowser() {
		driver.quit();
	}

}
