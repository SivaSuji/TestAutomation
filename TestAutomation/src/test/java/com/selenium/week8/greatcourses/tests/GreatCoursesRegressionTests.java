package com.selenium.week8.greatcourses.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.selenium.customlibrary.BasePage;
import com.selenium.week8.greatcourses.pages.CheckoutProgressPage;
import com.selenium.week8.greatcourses.pages.CheckoutProgressPage;
import com.selenium.week8.greatcourses.pages.ChooseCourseFormatPage;
import com.selenium.week8.greatcourses.pages.HomePage;
import com.selenium.week8.greatcourses.pages.ScienceCoursesPage;
import com.selenium.week8.greatcourses.pages.ShoppingCartPage;

public class GreatCoursesRegressionTests extends BasePage {

		final static Logger logger = Logger.getLogger(GreatCoursesRegressionTests.class);
		
		// create new instance -1
		HomePage myHomePage = new HomePage();
		ScienceCoursesPage SCpage = new ScienceCoursesPage();
		ChooseCourseFormatPage CCFpage = new ChooseCourseFormatPage();
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
		CheckoutProgressPage checkoutPage = new CheckoutProgressPage();

		
			
			@Test
			public void buyCourseTest()
			{
				   //Step1: open web site // Replace this to below
				logger.info("Step1:open website");
				myHomePage.goto_theGreatCoursesWebsite();
				//Step2: click on Category Science section // Replace this to below
				logger.info("Step2: click on Category Science section");
				myHomePage.click_CategoryScienceLink();
				//Step3: wait until page load complete // Replace this to below
				logger.info("Step3: wait until page load complete");
				SCpage.waitUntilPageLoadComplete();
				//Step4: select "Our Night Sky" // Replace this to below
				logger.info("Step4: select 'Our Night Sky'");
				SCpage.select_OurNightSky_Course("Our Night Sky");
				
				//Step5: wait until page load complete
				CCFpage.waitUntilPageLoadComplete();
				//Step6: select 'Video Download' radio button
				CCFpage.chooseCourseFormatType(0);
				//Step7: click on "Add To Cart"
				CCFpage.click_onAddtoCartBtn();
				//Step8: verify Success Message
				CCFpage.verifySuccessMessage("was added to your Shopping Cart.");
				//Step9: click on 'Check Out Now' button
				CCFpage.click_CheckOutNow_button();
				//Step10: wait until page load complete
				//shoppingCartPage.waitUntilPageLoadComplete();
				//Step11: click on 'Check Out Now' button
				shoppingCartPage.click_CheckOutNow_Button();
				//Step12: enter email
				checkoutPage.enterEmailAddress("test@frank.com");
				//Step13: select "no, i am new customer" radio button
				checkoutPage.select_NoYes_RadioButton(0);
				//Step14: select 'Continue' button
				checkoutPage.click_ContinueButton();
				//Step15: complete billing section
				checkoutPage.complete_BillingAddress("Frank1", "Frank2", "Address1", "City1", "Virginia",
						"45686", "789789");
				checkoutPage.complete_CreditCardInfo("378282246310005", "02 - February", "2020");
				
			}
			
		
}

