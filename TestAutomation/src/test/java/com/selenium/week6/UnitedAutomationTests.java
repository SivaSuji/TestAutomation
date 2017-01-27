package com.selenium.week6;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.customlibrary.UtilityLibrary;

public class UnitedAutomationTests {
	private WebDriver driver;
	// initialize library
	private UtilityLibrary myLib;

	@BeforeMethod
	public void setup() {
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "src/test/resources/chromedriver.exe"); driver = new ChromeDriver();
		 */
		myLib = new UtilityLibrary();
		driver = myLib.startBrowser("chrome");
	}

	@AfterMethod
	public void teardown() {
		myLib.customWait(10);
		// Thread.sleep(10*1000);
		driver.close();// just close browser
		driver.quit();// it closes the whole session
	}

	@Test
	public void searchTeaket() {
		driver.get("https://www.united.com/");

		// select Hotel //"ui-id-10"

		// we use parent element select child element,we look for parent element
		// and search child,
		// because parent elem is static
		WebElement navTabParent = driver.findElement(By.className("tab-container"));
		List<WebElement> childelems = navTabParent.findElements(By.tagName("a"));
		for (WebElement temp : childelems) {
			String elemtxt = temp.getText();
			System.out.println("nav tabs text: " + elemtxt);
			if (elemtxt.contains("Hotel")) {
				
				//temp.click();
				myLib.clickButton(temp);
				break;// exit out this loop only
			}
		}

		// we want click flight again
		for (WebElement temp : childelems) {
			String elemtxt = temp.getText();
			System.out.println("nav tabs text: " + elemtxt);
			if (elemtxt.contains("Flight")) {
				temp.click();
				break;// exit out this loop only
			}
		}

		myLib.clickButton(By.cssSelector("#SearchTypeMain_oneWay")); // one way
		myLib.customWait(2);
		myLib.selectCheckBox(By.id("uniform-SearchTypeMain_roundTrip"), true);// we
																				// select
																				// roudway

		myLib.enterTextField(By.id("Origin"), "IAD");
		myLib.customWait(1);
		WebElement fromresult = driver.findElement(By.cssSelector(".tt-suggestion.tt-selectable"));
		fromresult.click();

		myLib.enterTextField(By.id("Destination"), "Urumqi");
		myLib.customWait(2);
		/*
		 * WebElement to result
		 * =driver.findElement(By.cssSelector(".tt-suggestion.tt-selectable"));
		 * toresult.click();
		 */
		List<WebElement> Results = driver.findElements(By.cssSelector(".tt-suggestion.tt-selectable"));
		for (WebElement temp : Results) {
			System.out.println("text: " + temp.getText());
			if (temp.getText().contains("Urumqi")) {
				temp.click();
				break;
			}
		}

		myLib.enterTextField(By.id("DepartDate"), "01/01/2017");
		myLib.enterTextField(By.id("ReturnDate"), "03/01/2017");

		moveMouseAway();
		
		clearPreselectedTravelers(); // clearing preselected values before
		                             // selecting new value
       selectTravelTypeNumber("Adults (18-64)", 2); // Adult
       selectTravelTypeNumber("Seniors (65+)", 1); // Senior
       selectTravelTypeNumber("Children (5-11)", 3); // Children

        myLib.customWait(3);
       
		myLib.selectDropDown(By.id("cabinType"), "Business or First");
		myLib.customWait(1);
		//myLib.selectDropDown(By.id("cabinType"), "Cabin");

		myLib.selectCheckBox(By.id("NonStopOnly"), true);

		// click on search button
		myLib.clickButton(By.id("flightBookingSubmit"));

		WebElement resultelem = myLib.fluentWait(By.className("flight-block-summary-container"));
		if (resultelem != null) {
			Assert.assertEquals(true, true);

		} else {
			Assert.assertEquals(true, false);
		}
		
		String image = myLib.captureScreenshot("Cheap-Ticket-United-IAD-to-Urumqi", "target/images/");
		System.out.println("image location: " + image);


		/*String image = myLib.captureScreenshot("Cheap-Ticket-United-IAD-to-Urumqi","/target/images/");
		System.out.println("Image location: " + image);
		*/
	}

	private void moveMouseAway() 
	{
		driver.findElement(By.id("ui-id-2")).click();
	}
	
	private void clearPreselectedTravelers() {
		WebElement travelorSelectElem = driver.findElement(By.id("travelers-selector"));
		travelorSelectElem.click();
		myLib.customWait(1);

		WebElement dropdownElems = driver.findElement(By.id("travelers-select"));
		List<WebElement> stepperElems = dropdownElems.findElements(By.className("stepper-wrap"));
		for (WebElement temp : stepperElems) {
			List<WebElement> icons = temp.findElements(By.tagName("a"));
			WebElement minusIcon = icons.get(0);
			String classText = minusIcon.getAttribute("class");

			do {
				// System.out.println("before Text: " + classText);
				minusIcon.click();
				myLib.customWait(1);
				classText = minusIcon.getAttribute("class");
				// System.out.println("after Text: " + classText);
			} while (!classText.contains("disabled"));

		}
		moveMouseAway();
		myLib.customWait(1);
	}

	private void selectTravelTypeNumber(String travelerType, int travelerNumber) {
		// we are skipping travelers selector
		WebElement travelorSelectElem = driver.findElement(By.id("travelers-selector"));
		travelorSelectElem.click();
		myLib.customWait(1);

		// locate all the values in the drop-down
		WebElement dropdownElems = driver.findElement(By.id("travelers-select"));

		List<WebElement> stepperElems = dropdownElems.findElements(By.className("stepper-wrap"));
		for (WebElement temp : stepperElems) {
			if (temp.getText().contains(travelerType)) {
				List<WebElement> icons = temp.findElements(By.tagName("a"));
				for (int i = 0; i < travelerNumber; i++) {
					icons.get(1).click();
					myLib.customWait(1);
				}
				moveMouseAway();
				myLib.customWait(1);
				break;
			}
		}
	}

	

	/*
	 * WebElement searchParentElem =
	 * driver.findElement(By.id("bookTravelFlight")); List<WebElement>
	 * childrensElem2 =searchParentElem.findElements(By.cssSelector(
	 * "div.form-group.airport-autocomplete.autocomplete-on-color")); WebElement
	 * fromElem = childrensElem2.get(0); fromElem.sendKeys("IAD");
	 */

}