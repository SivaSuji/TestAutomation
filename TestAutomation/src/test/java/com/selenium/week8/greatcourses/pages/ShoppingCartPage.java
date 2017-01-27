package com.selenium.week8.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class ShoppingCartPage extends BasePage{

	public ShoppingCartPage waitUntilPageLoadComplete()
	{
		WebElement element = myLib.fluentWait(By.cssSelector(".page-title.title-buttons>h1"));
		//System.out.println("h1 tag text: " + element.getText());
		verifyPageTitle();
		Assert.assertNotNull(element);
		return this;
	}
	
	
	public ShoppingCartPage click_CheckOutNow_Button()
	{
		WebElement button1 = driver.findElement(By.cssSelector(".button.btn-proceed-checkout.btn-checkout"));
		myLib.clickButton(button1);
		return this;
	}
	
	

	
	
///////////////////////Helper Methods //////////////////
private void verifyPageTitle()
{
String expected = "Our Night Sky | The Great Courses";
String actual = myLib.verifyPageTitle();		
Assert.assertEquals(actual, expected);
}
	
}
