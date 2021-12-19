package Activities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Activity2 {

	    WebDriverWait wait;
	    AppiumDriver<MobileElement> driver = null;

	    @BeforeTest
	    public void setup() throws MalformedURLException {

	        // Set the Desired Capabilities
	        DesiredCapabilities caps = new DesiredCapabilities();
	        caps.setCapability("deviceName", "testfstemulator");
	        caps.setCapability("platformName", "android");
	        caps.setCapability("appPackage", "com.android.chrome");
	        caps.setCapability("appActivity", "com.google.android.apps.chrome.Main");
	        caps.setCapability("noReset", true);

	        // Instantiate Appium Driver
	        URL appServer = new URL("http://0.0.0.0:4723/wd/hub");
	        driver = new AndroidDriver<MobileElement>(appServer, caps);
	        wait = new WebDriverWait(driver, 20);
	    }

	    @Test
	    public void testSearchAppium() {
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        driver.get("https://www.training-support.net/");

	        String pageTitle = driver.findElementByXPath("//*[@text='Training Support']").getText();
	        System.out.println("Title on Homepage: " + pageTitle);

	        MobileElement aboutButton = driver.findElementByXPath("//*[@text='About Us']");
	       aboutButton.click();
	       wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath("//*[@text='About Us']")));
	        
	       String newPageTitle = driver.findElementByXPath("//*[@text='About Us']").getText();

	        System.out.println("Title on new page: " + newPageTitle);

	        // Assertions
	        Assert.assertEquals(pageTitle, "Training Support");
	        Assert.assertEquals(newPageTitle, "About Us");
	    }

	    @AfterTest
	    public void tearDown() {
	        driver.quit();
	    }
	}

