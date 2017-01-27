package com.selenium.customlibrary;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;



public class BasePage {
	final static Logger logger = Logger.getLogger(BasePage.class);
	public static WebDriver driver; 
	public static UtilityLibrary myLib;//initialize the Utility Library
	
	private static JavaPropertiesManager property;
	private static JavaPropertiesManager property2;//for adding property2 below
	private static String browser;
	private static String isAutoSendEmail;
	private static String isDemoMode;
	private static String sessionTime = "SessionTimeForScreenshot"; //Declare new variable - add for automaticallyAttachErrorImgToEmail()in utilityLibrary
	
	
	
//after set up the javapropertiesmanager class
	
	@BeforeClass
	public void beforeClass() throws Exception
	
	{
		property = new JavaPropertiesManager("src/test/resources/config.properties");
		browser = property.readProperty("browserType");
		isDemoMode = property.readProperty("isDemoMode");
		isAutoSendEmail = property.readProperty("sendEmail");
		myLib = new UtilityLibrary();
		if(isDemoMode.contains("true"))
		{
			myLib.isDemoMode = true;
			logger.info("Test running on demo mode is ON");
		}else
		{
			myLib.isDemoMode = false;
			logger.info("Test running demo mode is OFF");
		}
		//after adding private - sessionTime on top 
		property2 = new JavaPropertiesManager("src/test/resources/dynamicConfig.properties");
		property2.setProperty("sessionTime", myLib.getCurrentTime());
	
	}
	
	//type this after the EmailManager 
	
	@AfterClass
	public void afterAllTests() throws Exception {
		List<String> screenshots = new ArrayList<>();
		EmailManager emailSender = new EmailManager();
		emailSender.attachmentFiles.add("target/logs/Selenium-Report.html");
		emailSender.attachmentFiles.add("target/logs/log4j-selenium.log");

		screenshots = myLib.automaticallyAttachErrorImgToEmail();
		if (screenshots.size() != 0)
		{
			for (String temp : screenshots) 
			{
				emailSender.attachmentFiles.add(temp);
			}
		}
		if (isAutoSendEmail.contains("true")) {
			emailSender.sendEmail(emailSender.attachmentFiles);
		}

	}

	
	/*@AfterClass
	public void afterAllTests() throws Exception {
		List<String> screenshots = new ArrayList<>();
		EmailManager emailSender = new EmailManager();
		emailSender.attachmentFiles.add("target/logs/Selenium-Report.html");
		emailSender.attachmentFiles.add("target/logs/log4j-selenium.log");

		screenshots = myLib.automaticallyAttachErrorScreenShotsToEmail();
		if (screenshots.size() != 0)
		{
			for (String temp : screenshots) 
			{
				emailSender.attachmentFiles.add(temp);
			}
		}
		if (isAutoSendEmail.contains("true")) {
			emailSender.sendEmail(emailSender.attachmentFiles);
		}

	}*/
	
	
	
	
	@BeforeMethod
	//public void setup() { //changed to beforeEachTest after Emailmanager
	public void beforeEachTest() {
		logger.info("Starting  Test...");
		//myLib = new UtilityLibrary(); //comment out as we initialized this after adding @beforeClass
		//driver = myLib.startBrowser("firefox");
		//driver = myLib.startBrowser("chrome");
		driver = myLib.startBrowser(browser);//after set up JavaPropertiesFile - notepad++ and added "broswser" in this BasePage on top
	}

	@AfterMethod
	//public void teardown() {
	public void afterEachTest(ITestResult result){
		if(ITestResult.FAILURE == result.getStatus())
		{
			myLib.captureScreenshot(result.getName(), "target/images/");
		}
		
		
		
		myLib.customWait(10);
		// Thread.sleep(10*1000);
		driver.close();// just close the current browser
		driver.quit();// it closes the driver instance and process. It kills it.
		logger.info("Closing browser");
		logger.info("Ending test...");
	}

}






