package com.selenium.gmail;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.selenium.customlibrary.UtilityLibrary;

public class GmailFunction {

  private WebDriver driver;
  private UtilityLibrary myLib;
  

	@BeforeMethod
	public void setup() {
		myLib = new UtilityLibrary();
		driver = myLib.startBrowser("firefox");
		//driver = myLib.startBrowser("chrome");
		//driver = myLib.startBrowser("ie");
		
		
		// Go to url
		driver.get("https://www.google.com/gmail/about/");
		//myLib.customWait(3);
	}

	@AfterMethod
	public void teardown() {
		driver.close(); // close the browser but WebDriver object is still alive
		driver.quit(); // kills the WebDriver object and it's value is equal to
						// 'null'
	}
	
	
	@Test(enabled=false)
	public void testing_gmail_Title() {
		// Step1: get the page title and print it in console
		String websiteTitle = driver.getTitle();
		System.out.println("Title: " + websiteTitle);

		// Validation or Assertion in java
		Assert.assertEquals(websiteTitle, "Gmail - Free Storage and Email from Google");
	}
	
	@Test
	public void click_Signin() {
		//Step1: click SignIn
		driver.findElement(By.cssSelector(".gmail-nav__nav-link.gmail-nav__nav-link__sign-in")).click();
		myLib.customWait(3);
		//Step2: enter user name
		myLib.enterTextField(By.id("Email"), "sujisiva.test");
		//Step 3: click next
		driver.findElement(By.id("next")).click();
		//Step4: enter password
		myLib.enterTextField(By.id("Passwd"), "TestTest");
		myLib.customWait(2);
		//step5:click signIn
		driver.findElement(By.id("signIn")).click();
		myLib.customWait(3);
		//step6: click compose button
		driver.findElement(By.xpath("//div[@gh='cm']")).click();
		myLib.customWait(3);
		
		//step7: Enter email id
				
		myLib.highlightElement(By.className("vO"));
		myLib.customWait(3);
		driver.findElement(By.className("vO")).sendKeys("sujisiva.test@gmail.com");
		
		//Step8: Enter the subject
		myLib.highlightElement(By.name("subjectbox"));
		driver.findElement(By.name("subjectbox")).sendKeys("hello");
		
		//Step 9: Click send button
		myLib.highlightElement(By.className("J-J5-Ji"));
		
		
		driver.findElement(By.cssSelector("div[role='button']"));
		myLib.customWait(1);
		
		
		//step10: take screen shot of email confirmation sent
		String imageEmail = myLib.captureScreenshot("emailConfirmation", "target/images");
		System.out.println("Screen shot: " + imageEmail);
		
	}
	
	


}
		
		
		
	
	
	

