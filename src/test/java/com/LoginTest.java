package com;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageobject.LoginPage;
import pageobject.model.AccountLogin;
import utilities.ExcelHelper;

public class LoginTest extends BaseTest {
	private WebDriver driver;
	private LoginPage loginPage;
	private Map<String, AccountLogin> accountLoginList = new HashMap<>();	
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		loginPage = PageGeneratorManager.getLoginPage(driver);
		readData("LoginTest.xlsx", "Sheet1");
	}
	
	@BeforeMethod
	public void openPage() {
		loginPage.openPageUrl(driver, domain + "account/login");
	}
	
	@Test
	public void test_Login_01() {
		AccountLogin accountLogin = accountLoginList.get("1");
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Để trông trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Để trống trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		assert2List(loginPage.getMsg(), expectedMsg);
		loginPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Login_02() {
		AccountLogin accountLogin = accountLoginList.get("2");;
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Nhập ký tự khoảng trắng vào trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Nhập ký tự khoảng trắng vào trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		assert2List(loginPage.getMsg(), expectedMsg);
		loginPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Login_03() {
		AccountLogin accountLogin = accountLoginList.get("3");;
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Mật khẩu");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Để trống trường Mật khẩu", accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		assert2List(loginPage.getMsg(), expectedMsg);
		loginPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Login_04() {
		AccountLogin accountLogin = accountLoginList.get("4");;
		List<String> expectedMsg = Arrays.asList("Vui lòng nhập Email");
		loginPage.inputTextToEmail("Để trống trường Email", accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		assert2List(loginPage.getMsg(), expectedMsg);
		loginPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Login_05() {
		AccountLogin accountLogin = accountLoginList.get("5");;
		List<String> expectedMsg = Arrays.asList("Thông tin đăng nhập không chính xác.");
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		assert2List(loginPage.getMsg(), expectedMsg);
		loginPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Login_06() {
		AccountLogin accountLogin = accountLoginList.get("6");;
		loginPage.inputTextToEmail("Nhập trường Email: " + accountLogin.email, accountLogin.email);
		loginPage.inputTextToPassword("Nhập trường Mật khẩu: " + accountLogin.password, accountLogin.password);
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập");
		loginPage.clickToLoginBtn();
		loginPage.sleepInSecond(5);
		assertEquals(loginPage.getCurrentUrl(driver), "https://samtech.vn/account");
		loginPage.takeScreenshot(driver, "Thông tin đăng nhập thành công");
		loginPage.clickToElement(driver, "xpath=//a[text()='Đăng xuất']");
	}
	
	public void readData(String fileName, String sheetName) {
		ExcelHelper excelHelper = new ExcelHelper();
		Map<String, List<String>> data = excelHelper.getExcelDataAsMap(GlobalConstants.dataTest + File.separator + fileName, sheetName);
		List<String> testCaseIdList = data.get("TC_ID");
		List<String> emailList = data.get("Email");
		List<String> passwordList = data.get("Password");
		for (int i = 0; i < testCaseIdList.size(); i++) {
			accountLoginList.put(testCaseIdList.get(i), new AccountLogin(emailList.get(i), passwordList.get(i)));
		}
	}
}
