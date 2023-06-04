package com;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageobject.RegisterPage;

public class RegisterTest extends BaseTest{
	
	private WebDriver driver;
	private RegisterPage registerPage;
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}
	
	public void openPage() {
		registerPage.openPageUrl(driver, domain + "account/register");
	}
	
	@Test
	public void test_Register_01() {
		
	}

}
