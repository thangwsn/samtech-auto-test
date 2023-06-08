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
import pageobject.RegisterPage;
import pageobject.model.AccountRegister;
import utilities.ExcelHelper;

public class RegisterTest extends BaseTest{
	
	private WebDriver driver;
	private RegisterPage registerPage;
	private Map<String, AccountRegister> accountRegisterMap = new HashMap<>();
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		readData("RegisterTest.xlsx", "Sheet1");
	}
	
	@BeforeMethod
	public void openPage() {
		registerPage.openPageUrl(driver, domain + "account/register");
	}
	
	@Test
	public void test_Register_01() {
		AccountRegister accountRegister = accountRegisterMap.get("1");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu", "Vui lòng nhập Tên");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName , accountRegister.lastName);
		registerPage.inputTextToFirstName("Để trống trường Tên", accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Để trống trường Mật khẩu", accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_02() {
		AccountRegister accountRegister = accountRegisterMap.get("2");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Vui lòng nhập Mật khẩu");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Để trống trường Mật khẩu", accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_03() {
		AccountRegister accountRegister = accountRegisterMap.get("3");
		List<String> expectedMessage = Arrays.asList("Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_04() {
		AccountRegister accountRegister = accountRegisterMap.get("4");
		List<String> expectedMessage = Arrays.asList("Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_05() {
		AccountRegister accountRegister = accountRegisterMap.get("5");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_06() {
		AccountRegister accountRegister = accountRegisterMap.get("6");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email", "Mật khẩu dài từ 6 đến 50 ký tự");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_07() {
		AccountRegister accountRegister = accountRegisterMap.get("7");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Email");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Để trống trường Email", accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_08() {
		AccountRegister accountRegister = accountRegisterMap.get("8");
		List<String> expectedMessage = Arrays.asList("Vui lòng nhập Tên");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Để trống trường Tên", accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_09() {
		AccountRegister accountRegister = accountRegisterMap.get("9");
		List<String> expectedMessage = Arrays.asList("Email đã tồn tại.");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		assert2List(registerPage.getMsg(), expectedMessage);
		registerPage.takeScreenshot(driver, "Message hiển thị");
	}
	
	@Test
	public void test_Register_10() {
		AccountRegister accountRegister = accountRegisterMap.get("10");
		registerPage.inputTextToLastName("Nhập trường Họ: " + accountRegister.lastName, accountRegister.lastName);
		registerPage.inputTextToFirstName("Nhập trường Tên: " + accountRegister.firstName, accountRegister.firstName);
		registerPage.inputTextToEmail("Nhập trường Email: " + accountRegister.email, accountRegister.email);
		registerPage.inputTextToPassword("Nhập Mật khẩu: " + accountRegister.password, accountRegister.password);
		registerPage.takeScreenshot(driver, "Thông tin đăng ký");
		registerPage.clickToResgisterBtn();
		registerPage.sleepInSecond(5);
		assertEquals(registerPage.getCurrentUrl(driver), "https://samtech.vn/");
		registerPage.takeScreenshot(driver, "Thông tin đăng ký thành công");
		registerPage.clickToElement(driver, "xpath=//a[text()='Đăng xuất']");

	}
	
	public void readData(String fileName, String sheetName) {
		ExcelHelper excelHelper = new ExcelHelper();
		Map<String, List<String>> data = excelHelper.getExcelDataAsMap(GlobalConstants.dataTest + File.separator + fileName, sheetName);
		List<String> testCaseIdList = data.get("TC_ID");
		List<String> lastNameList = data.get("LastName");
		List<String> firstNameList = data.get("FirstName");
		List<String> emailList = data.get("Email");
		List<String> passwordList = data.get("Password");
		for (int i = 0; i < testCaseIdList.size(); i++) {
			String email = emailList.get(i);
			if (email.equals("ramdom email")) {
				email = faker.generateFakeAlphaNumeric(20) + "@gmail.com";
			}
			accountRegisterMap.put(testCaseIdList.get(i), new AccountRegister(lastNameList.get(i), firstNameList.get(i), email, passwordList.get(i)));
		}
	}
}
