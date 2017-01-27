package com.selenium.week4.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MortgageCalcTest {
	// 1
	private WebDriver driver;

	// 2
	@BeforeMethod
	public void setup() {
		//3 - open Firefox Browser
		driver = new FirefoxDriver();

		//5 - maximize the window
		driver.manage().window().maximize();
		//6 - wait until the page load
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		//7 - wait for WebElement on the web site up to given seconds
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		
		//4 - go to url
		driver.get("http://www.mortgagecalculator.org/");

	}

	@Test
	public void testing_MortgageCalc_Function()
	{
		//step1: get the page title and print it in console
		String websiteTitle = driver.getTitle();
		System.out.println("Title:" + websiteTitle);
	}
	
	


}
