package com.selenium.week8.greatcourses.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class ChooseCourseFormatPage extends BasePage{

	public ChooseCourseFormatPage waitUntilPageLoadComplete()
	{
		WebElement element = myLib.fluentWait(By.id("product-options-wrapper"));
		Assert.assertNotNull(element);
		
		return this;
	}
	
public ChooseCourseFormatPage chooseCourseFormatType(int typeIndex)//parameters because choosing radio button by index
{
	WebElement parentSection = driver.findElement(By.id("media-format-radio"));//gives whole radio buttons section 
	List<WebElement> typesElems = parentSection.findElements(By.tagName("input"));
	if(typeIndex < 2)
	{
		
		//typeElems.get(typeIndex).click(); //couldn't click element hidden 
		
		WebElement targetElem = typesElems.get(typeIndex);
		myLib.clickOnHiddenElement(targetElem);
	}else
	{
		System.out.println("TypeIndex range is between 0 and 1, you are using: " + typeIndex);
	}
	return this;
}

public ChooseCourseFormatPage click_onAddtoCartBtn()
{
	myLib.clickButton(By.id("add-to-cart-btn"));
	return this;
}

public ChooseCourseFormatPage verifySuccessMessage(String expectedMsg)
{
	WebElement messageElem = driver.findElement(By.id("messages_product_view"));
	List<WebElement> spanElems = messageElem.findElements(By.tagName("span"));
	String actualText = spanElems.get(1).getText();
	Assert.assertEquals(actualText, expectedMsg);//from user - parameter
	
	return this;
}

public ChooseCourseFormatPage click_CheckOutNow_button()
{
	
	driver.findElement(By.id("checkout-now")).click();
	return this;
}






}
