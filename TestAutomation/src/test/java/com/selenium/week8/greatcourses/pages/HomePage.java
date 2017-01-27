package com.selenium.week8.greatcourses.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.selenium.customlibrary.BasePage;

public class HomePage extends BasePage { // 1

	private String homepageURL = "http://www.thegreatcourses.com/"; // 2

	// It returns HomePage class
	public HomePage goto_theGreatCoursesWebsite() // 3
	{

		driver.get(homepageURL); // 5
		String pageTitle = driver.getTitle();// 6
		System.out.println("Website Title: " + pageTitle); // 8
		// Assert.assertEquals(actual, expected);//testng
		Assert.assertEquals(pageTitle, "The Great Courses");// 7
		return this; // 4 - without this there will be redline under the
						// greatCoursesWebsite
	}

	public HomePage click_CategoryScienceLink() // 9
	{

		/*WebElement scienceLink = driver.findElement(By.xpath("//span[(@class='category-name' and text()='Science')]"));
		scienceLink.click();*/ // using xpath to click the science link
		
		// myLib.clickLink(By.linkText("Science")); //using linktext.. it didn't work for me
		
		myLib.clickOnHiddenElement(By.linkText("Science")); //using hidden element method from lib
		return this; // 10
	}

}