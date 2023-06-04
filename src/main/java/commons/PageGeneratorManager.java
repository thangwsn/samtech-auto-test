package commons;

import org.openqa.selenium.WebDriver;

import pageobject.RegisterPage;

public class PageGeneratorManager {
	
	public static RegisterPage getRegisterPage(WebDriver driver) {
		return new RegisterPage(driver);
	}
}
