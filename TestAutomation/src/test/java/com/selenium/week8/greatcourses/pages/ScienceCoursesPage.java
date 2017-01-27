package com.selenium.week8.greatcourses.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class ScienceCoursesPage extends BasePage{ //1 after clicking Science link

	//this method is for every single page
	public ScienceCoursesPage waitUntilPageLoadComplete()
	{
		WebElement element = myLib.fluentWait(By.cssSelector(".category-products"));
		Assert.assertNotNull(element);
		verifyPageTitle(); //helper methods and this is for my own verification
		return this;
	}
	
	public ScienceCoursesPage select_OurNightSky_Course(String courseTitleText)
	{
		WebElement parentCategory = driver.findElement(By.cssSelector(".category-products"));
		List<WebElement> coursesElems = parentCategory.findElements(By.className("product-name"));
		for(WebElement temp: coursesElems)
		{
			//System.out.println("course name: '" + temp.getText() +"'");
			if(temp.getText().contains(courseTitleText))
			{
				System.out.println("Selecting Course: " + temp.getText());
				myLib.highlightElement(temp);//pop up element - choose temp here
				temp.click();
				break;
			}
		}
		return this;
	}
	

///////////////////////Helper Methods //////////////////
private void verifyPageTitle()
{
String expected = "Science Courses | The Great Courses : Courses";
String actual = myLib.verifyPageTitle();		
Assert.assertEquals(actual, expected);
}






}
