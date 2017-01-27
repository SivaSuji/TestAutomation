package com.selenium.customlibrary;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportTestNG implements IReporter{

	private ExtentReports extent;
	
	public void generateReport(List<XmlSuite> arg0,List<ISuite> suites, String outputDirectory){
	String reportPath = outputDirectory + File.separator + "ExtentSeleniumReport.html";
	System.out.println("Report Location: " + reportPath);
	extent = new ExtentReports(reportPath, false);
	
	for(ISuite suite: suites){
	  Map<String, ISuiteResult>	result = suite.getResults();
		for(ISuiteResult r: result.values()){
			ITestContext context = r.getTestContext();
			
			buildTextNodes(context.getPassedTests(),LogStatus.PASS);
			buildTextNodes(context.getPassedTests(),LogStatus.FAIL);
			buildTextNodes(context.getPassedTests(),LogStatus.SKIP);
		}
	}
	   extent.flush();
	   extent.close();
	}
	
private void buildTextNodes(IResultMap tests, LogStatus status)
{
	ExtentTest test = null;
	if(tests.size() > 0)
	{
		for(ITestResult result: tests.getAllResults()){
			test = extent.startTest(result.getMethod().getMethodName());
			test.getTest().setStartedTime(getTime(result.getStartMillis()));
			test.getTest().setEndedTime(getTime(result.getEndMillis()));
			for(String group: result.getMethod().getGroups())
			{
				test.assignCategory(group);
			}
			String message = "Test " + status.toString().toLowerCase() + "ed";
			if(result.getThrowable() !=null)
			{
				test.log(status, message);
				//extent.endTest(test);//error with this somove it down
			}
			extent.endTest(test);
		}
	}
}

private Date getTime(long millis){
	Calendar calendar = Calendar.getInstance();
	calendar.setTimeInMillis(millis);
	return calendar.getTime();
	
}
	
	
	
	
	
	
}