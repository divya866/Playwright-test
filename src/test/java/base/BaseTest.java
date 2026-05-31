package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import extentlisteners.ExtentListeners;

public class BaseTest{
	
	private Playwright playwright;
	public Browser browser;
	public Page page;
	private static Properties OR = new Properties();
	private static FileInputStream fis;
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@BeforeSuite
	public void setup() {
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
		log.info("Test Execution started !!!");
		
		
		try {
			fis=new FileInputStream("./src/test/resources/properties/OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			OR.load(fis);
			log.info("OR properties file loaded");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}	
	
	public void click(String locatorkey) {
		
		try {
		page.locator(OR.getProperty(locatorkey)).click();
		log.info("clicking on Element:+Locator");
		ExtentListeners.test.info("clicking on Element:+Locator ");
		}catch(Throwable t) {
			log.error("Error while typing in an element:  " +t.getMessage());
			ExtentListeners.test.info("Error while typing in an element:  " +t.getMessage());
			Assert.fail(t.getMessage());
		}
	}
		
	
	public void type(String locatorkey, String value) {
		
		try {
		page.locator(OR.getProperty(locatorkey)).fill(value);
		log.info("clicking on Element:+Locator");
		ExtentListeners.test.info("clicking on Element:+Locator ");
		}catch(Throwable t) {
			log.error("Error while typing in an element:  " +t.getMessage());
			ExtentListeners.test.info("Error while typing in an element:  " +t.getMessage());
			Assert.fail(t.getMessage());
		
		}
		
	}
		public Browser getBrowser(String browserName) {
			
			playwright= Playwright.create();
			switch(browserName) {
			case "chrome":
				log.info("Launching chrome Browser");
				return playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
			case "setHeadless":
				log.info("Launching Headless Browser");
				return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
			case "firefox":
				log.info("Launching firefox Browser");
				return playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("firefox").setHeadless(false));
			case "Webkit":
				log.info("Launching Webkit Browser");
				return playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			default:
				throw new IllegalArgumentException();
			
		}
	}
		public void navigate(Browser browser,String URL) {
			this.browser=browser;
			page=browser.newPage();
			page.navigate(URL);
			log.info("Navigate to :"+URL);
			
		}
		@AfterMethod
		public void quit() {
			
			browser.close();
			page.close();
			playwright.close();
		}
}


