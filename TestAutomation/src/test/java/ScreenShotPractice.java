import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScreenShotPractice {
	public static void main(String[] args) throws Exception {
		
	
	System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");WebDriver driver = new FirefoxDriver();
	driver.get("http://www.google.com");
	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(scrFile, new File("c:\\temp\\screenshot.png"));
	
	}
}
