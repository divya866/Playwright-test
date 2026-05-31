package testcase;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;

import base.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void dologin() {
		
		Browser browser = getBrowser("chrome");
		navigate(browser, "https://www.google.com/");
		type("searchBox","Hello Playwright");
				
	}
	@Test
public void doGmailLogin() {
		
		Browser browser = getBrowser("chrome");
		navigate(browser, "https://gmail.com/");
		type("username","trainer@way2automation.com");

	}
		
}

