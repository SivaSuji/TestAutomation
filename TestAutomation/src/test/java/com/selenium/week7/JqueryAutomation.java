package com.selenium.week7;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.selenium.customlibrary.BasePage;

public class JqueryAutomation extends BasePage
{

	@Test
	public void mouseHoverOverActionTesting() 
	{
		driver.get("https://jqueryui.com/");
		driver.findElements(By.linkText("Menu")).get(0).click();
		//locate iFrame
		WebElement iframeElem = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iframeElem); //choose from pop up - frame(WebElement)
			
		WebElement menuElem = driver.findElement(By.id("menu"));
		List<WebElement>liElems = menuElem.findElements(By.tagName("li"));
		for(WebElement temp : liElems)
		{
			if(temp.getText().contains("Electronics")) //WebElement temp contains Electronics here
			{
				//move the mouse pointer to this element
				
				myLib.moveMouseToElement(temp); //replaces the below 3 lines
				
				/*Actions action = new Actions(driver); //driver is your browser
				action.moveToElement(temp).build().perform(); //choose moveToElement(webElement toElement)
				myLib.customWait(10);*/
				
				WebElement hifi = driver.findElement(By.cssSelector("ul.ui-menu.ui-widget.ui-widget-content.ui-front"));
				myLib.moveMouseToElement(temp, hifi); //replace the below
				
				/*action.moveToElement(hifi).build().perform();*/ 
			}
		}
		
		
		
		
		
		
		
		
	}
	
}
