package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitionHondaBike {

	public RemoteWebDriver driver;
	public WebDriverWait wait;
	Actions builder;
	JavascriptExecutor js;
	
	List<WebElement> keyElement;
	List<WebElement> valueElement;
	Map<String, String> dioEngineDetails;
	Map<String, String> activa125EngineDetails;
	
	File src;
	File dest;

	@Given("Open the Browser and set Properties")
	public void openTheBrowserAndSetProperties() {
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

	@Given("Go to the honda url")
	public void goToTheHondaUrl() {
		driver.get("https://www.honda2wheelersindia.com/");
		driver.findElement(By.className("close")).click();
	}

	@Given("Click on scooters and click Dio")
	public void clickOnScootersAndClickDio() throws InterruptedException {
		driver.findElement(By.id("link_Scooter")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//img[@src='/assets/images/thumb/dioBS6-icon.png']")).click();
		Thread.sleep(1500);
	}

	@Given("Click on Specifications and mouseover on Engine")
	public void clickOnSpecificationsAndMouseoverOnEngine() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Specifications']"))));
		driver.findElement(By.xpath("//a[text()='Specifications']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("ENGINE"))));
		builder.moveToElement(driver.findElement(By.linkText("ENGINE"))).perform();
	}

	@Given("Put all the engine details as key and value into Map")
	public void putAllTheDioEngineDetailsAsKeyAndValueIntoMap() throws InterruptedException, IOException {
		Thread.sleep(1000);
//		WebElement engine = driver.findElement(By.xpath("//div[@class='engine part-2 axx']//ul"));
		
		String title = driver.getTitle();
		
		if (title.contains("DIO")) {
			src = driver.getScreenshotAs(OutputType.FILE); 
		    dest = new File("./screens/Honda/Engine_Spec_Dio.png"); 
		    FileUtils.copyFile(src, dest);
			dioEngineDetails = new LinkedHashMap<>();
			keyElement = driver.findElementsByXPath("//div[@class='engine part-2 axx']//li//span[1]");
			valueElement = driver.findElementsByXPath("//div[@class='engine part-2 axx']//li//span[2]");
			valueElement.add(0, driver.findElementByXPath("//div[@class='engine part-2 axx']//li//span[2]"));
		}
		else {
			src = driver.getScreenshotAs(OutputType.FILE); 
		    dest = new File("./screens/Honda/Engine_Spec_Activa125.png"); 
		    FileUtils.copyFile(src, dest);
			activa125EngineDetails = new LinkedHashMap<>();
			keyElement = driver.findElementsByXPath("//div[@class='engine part-4 axx']//li//span[1]");
			valueElement = driver.findElementsByXPath("//div[@class='engine part-4 axx']//li//span[2]");
			valueElement.add(0, driver.findElementByXPath("//div[@class='engine part-4 axx']//li//span[2]"));
		}
		
		
		Thread.sleep(1500);
		for (int i = 1; i < keyElement.size(); i++) {
			String keyText = keyElement.get(i).getText();
			String valueText = valueElement.get(i).getText();
			
//			System.out.println("Key-> "+keyText+" - Value-> "+valueText);
//			String title = driver.getTitle();
			if (title.contains("DIO")) {
				dioEngineDetails.put(keyText, valueText);
			}
			else {
				activa125EngineDetails.put(keyText, valueText);
			}
		}
//		System.out.println(dioEngineDetails);
//		System.out.println(activa125EngineDetails);
	}

	@Given("Click on scooters and click Activa{int}")
	public void clickOnScootersAndClickActiva(Integer int1) throws InterruptedException {
		driver.findElement(By.id("link_Scooter")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//img[@src='/assets/images/thumb/activa-125new-icon.png']")).click();
		Thread.sleep(1500);
	}

	@Given("Compare Maps and print the different values of the samekeys")
	public void compareMapsAndPrintTheDifferentValuesOfTheSamekeys() {
		System.out.println(dioEngineDetails.size()+"="+activa125EngineDetails.size());
		MapDifference<String, String> difference = Maps.difference(dioEngineDetails, activa125EngineDetails);
		Map<String, ValueDifference<String>> entriesDiffering = difference.entriesDiffering();
//		System.out.println(entriesDiffering);
//		dioEngineDetails.values().equals(activa125EngineDetails.values());
		System.out.println("--Key--"+"--Dio Engine Spec--"+"--Activa 125 Engine Spec--");
		for (Map.Entry<String, ValueDifference<String>> entry : entriesDiffering.entrySet()) {
//			System.out.println(entry.getKey()+"--"+entry.getValue().leftValue()+"--"+entry.getValue().rightValue());
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}

	@Given("Click FAQ from Menu")
	public void clickFAQFromMenu() {
		driver.findElement(By.linkText("FAQ")).click();
	}

	@Given("Click Dio under Browse By Product")
	public void clickDioUnderBrowseByProduct() {
		driver.findElement(By.linkText("Dio BS-VI")).click();
	}

	@Given("Click  Vehicle Price and Select scooter, Dio BS-VI from the dropdown and click submit")
	public void clickVehiclePriceAndSelectScooterDioBSVIFromTheDropdownAndClickSubmit() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@href='#6a']"))));
		driver.findElement(By.xpath("//a[@href='#6a']")).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("SegmentID6"))));
		Select dd = new Select(driver.findElement(By.id("SegmentID6")));
		dd.selectByVisibleText("Scooter");
		
		dd = new Select(driver.findElement(By.id("ModelID6")));
		dd.selectByVisibleText("Dio BS-VI");
		
		driver.findElement(By.id("submit6")).click();
		Thread.sleep(500);
	}

	@Given("Click the price link")
	public void clickThePriceLink() {
		driver.findElement(By.partialLinkText("Click here to know the price of Dio BS-VI")).click();
	}

	@Given("Go to the new Window")
	public void goToTheNewWindow() throws InterruptedException {
		Thread.sleep(1500);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandler = new LinkedList<>(windowHandles);
		driver.switchTo().window(windowHandler.get(1));
	}

	@Given("Select the state, city")
	public void selectTheStateCity() {
		Select dd = new Select(driver.findElement(By.id("StateID")));
		dd.selectByVisibleText("Tamil Nadu");
		
		dd = new Select(driver.findElement(By.id("CityID")));
		dd.selectByVisibleText("Chennai");
		
		driver.findElement(By.xpath("//button[text()='Search']")).click();
	}

	@Given("Print the price and model")
	public void printThePriceAndModel() {
		List<WebElement> dioPrice = driver.findElements(By.xpath("//tbody[@style='background-color:white']//td"));
		for (int i=1; i<dioPrice.size(); i++) {
			if (i==1|| ((i/2) !=0)) {
				System.out.print(dioPrice.get(i).getText()+" --> ");
			}
			else {
				System.out.print(dioPrice.get(i).getText());
			}
		}
		System.out.println();
	}

	@When("Click Product Enquiry and Fill all the mandatory field except Mobile, check the terms and conditions box and click submit")
	public void clickProductEnquiryAndFillAllTheMandatoryFieldExceptMobileCheckTheTermsAndConditionsBoxAndClickSubmit() throws InterruptedException {
		driver.findElement(By.xpath("//span[text()='Product Enquiry ']")).click();
		Thread.sleep(2000);
		
		Select dd = new Select(driver.findElement(By.id("ModelID")));
		dd.selectByVisibleText("Dio BS-VI");
		
		dd = new Select(driver.findElement(By.id("StateID")));
		dd.selectByVisibleText("Tamil Nadu");
		
		dd = new Select(driver.findElement(By.id("CityID")));
		dd.selectByVisibleText("Chennai");
		
		dd = new Select(driver.findElement(By.id("DealerID")));
		dd.selectByVisibleText("Maansarovar Honda");
		
		dd = new Select(driver.findElement(By.id("TitleID")));
		dd.selectByVisibleText("Mr.");
		
		driver.findElement(By.id("Name")).sendKeys("Muralee");
		
		driver.findElement(By.id("Email")).sendKeys("muralee@gmail.com");
		
		driver.findElement(By.id("TermsAndConditions")).click();
		
		driver.findElement(By.id("submit")).click();
	}

	@Then("Verify the error message under the mobile number field")
	public void verifyTheErrorMessageUnderTheMobileNumberField() throws IOException {
		boolean displayed = driver.findElement(By.xpath("//span[text()='Please enter mobile no.']")).isDisplayed();
		if (displayed) {
			src = driver.getScreenshotAs(OutputType.FILE); 
		    dest = new File("./screens/Honda/Honda_Mandatory_field_Error.png"); 
		    FileUtils.copyFile(src, dest);
			System.out.println(driver.findElement(By.xpath("//span[text()='Please enter mobile no.']")).getText());
		}
		
		else {
			System.err.println("Retry");
		}
	}
	
	@Then("Close the honda bike enquiry browser")
	public void closeTheHondaBikeEnquiryBrowser() {
		driver.quit();
	}

}
