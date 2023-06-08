package com;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageobject.CartPage;
import pageobject.CollectionPage;
import pageobject.ContactPage;
import pageobject.NewsPage;
import pageobject.RegisterAgentPage;
import pageobject.model.Contact;
import pageobject.model.RegisterAgent;
import pageui.CartPageUi;
import pageui.CollectionPageUi;
import pageui.NewsPageUi;
import utilities.ExcelHelper;

public class ThreadTest extends BaseTest {
	private WebDriver driver;
	private CollectionPage collectionPage;
	private CartPage cartPage;
	private NewsPage newsPage;
	private RegisterAgentPage registerAgentPage;
	private ContactPage contactPage;
	private RegisterAgent registerAgent;
	private Contact contact;
	
	@BeforeClass
	public void setup() {
		driver = getBrowserDriver();
		collectionPage = PageGeneratorManager.getCollectionPage(driver);
		cartPage = PageGeneratorManager.getCartPage(driver);
		newsPage = PageGeneratorManager.getNewsPage(driver);
		registerAgentPage = PageGeneratorManager.getRegisterAgentPage(driver);
		contactPage = PageGeneratorManager.getContactPage(driver);
		readData("ThreadTest.xlsx");
	}
	
	@Test
	public void test_01_viewDetailProduct() {
		collectionPage.openPageUrl(driver, domain + "collections/all");
		String itemName = collectionPage.viewDetailProduct(1);
		assertEquals(collectionPage.getElementText(driver, CollectionPageUi.DETAIL_PRODUCT_NAME), itemName);
		collectionPage.takeScreenshot(driver, "Chi tiết sản phẩm");
	}
	
	@Test
	public void test_02_addToCartFromDetailProductView() {
		collectionPage.inputQuantity(3);
		collectionPage.takeScreenshot(driver, "Nhập số lượng");
		collectionPage.clickAddBtn();
		collectionPage.openPageUrl(driver, domain + "cart");
		assertEquals(cartPage.getElementSize(driver, CartPageUi.ROW_IN_TABLE), 1);
		cartPage.takeScreenshot(driver, "Thêm mặt hàng vào giỏ hàng thành công");;
	}
	
	@Test
	public void test_03_viewDetailNews() {
		newsPage.openPageUrl(driver, domain + "blogs/all");
		String newsTitle = newsPage.clickToFirstNews();
		newsPage.takeScreenshot(driver, "Chi tiết tin tức");
		assertEquals(newsPage.getElementText(driver, NewsPageUi.NEWS_TITLE), newsTitle);
	}
	
	@Test
	public void test_04_registerAgent() {
		registerAgentPage.openPageUrl(driver, domain + "dang-ki-dai-ly");
		registerAgentPage.inputData(registerAgent);
		registerAgentPage.takeScreenshot(driver, "Thông tin đã nhập");
		registerAgentPage.clickSendBtn();
		registerAgentPage.takeScreenshot(driver, "Đăng ký thành công");
	}
	
	@Test
	public void test_05_contact() {
		contactPage.openPageUrl(driver, domain + "lien-he");
		contactPage.inputData(contact);
		contactPage.takeScreenshot(driver, "Thông tin đã nhập");
		contactPage.clickSendBtn();
		contactPage.takeScreenshot(driver, "Gửi thành công");
	}
	
	public void readData(String fileName) {
		ExcelHelper excelHelper = new ExcelHelper();
		Map<String, List<String>> data = excelHelper.getExcelDataAsMap(GlobalConstants.dataTest + File.separator + fileName, "Sheet1");
		registerAgent = new RegisterAgent(data.get("Name").get(0), data.get("StoreName").get(0), data.get("Address").get(0));
		Map<String, List<String>> data1 = excelHelper.getExcelDataAsMap(GlobalConstants.dataTest + File.separator + fileName, "Sheet2");
		contact = new Contact(data1.get("Name").get(0), data1.get("Email").get(0), data1.get("Comment").get(0));

	}
}
