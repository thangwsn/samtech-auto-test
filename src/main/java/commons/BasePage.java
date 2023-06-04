package commons;

import java.io.ByteArrayInputStream;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Allure;

public class BasePage {

	/**
	 * The URL to load. It is best to use a fully qualified URL
	 *
	 * @param driver  driver of browser
	 * @param pageUrl The URL to load
	 */
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
		sleepInSecond(5);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	/**
	 * Refresh the current page
	 *
	 * @param driver driver of browser
	 */
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * Get By locator
	 *
	 * @param locatorType used to find the element
	 * @return A By which locates elements by the value of the "id" or "class" or "name" or "css" or "xpath".
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("ID=") || locatorType.startsWith("Id=") || locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("CLASS=") || locatorType.startsWith("Class=") || locatorType.startsWith("class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") || locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") || locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported!");
		}
		return by;
	}

	/**
	 * Returns a formatted string using the specified format string and arguments. The locale always used is the one returned by Locale.getDefault().
	 *
	 * @param locatorType   used to find the element contains arguments.
	 * @param dynamicValues arguments of @param locatorType
	 * @return A formatted string locatorType
	 */
	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=") || locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	/**
	 * Find the first WebElement using the given method
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return The first matching element on the current page
	 */
	public WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	/**
	 * Find all elements within the current page using the given mechanism.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return A list of all WebElements, or an empty list if nothing matches
	 */
	public List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	/**
	 * Click to Element
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void clickToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		getWebElement(driver, locatorType).click();
	}

	/**
	 * Click to Element with dynamic values
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element contains arguments.
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	/**
	 * Double click to Element
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void doubleClickToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		Actions actions = new Actions(driver);
		WebElement element = getWebElement(driver, locatorType);
		actions.doubleClick(element).perform();
	}

	/**
	 * Double click to Element with dynamic values
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element contains arguments.
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void doubleClickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		Actions actions = new Actions(driver);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		actions.doubleClick(element).perform();
	}

	/**
	 * Send key to Element
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @param textValue   value to input
	 */
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		highlightElement(driver, locatorType);
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * Send key to Element with dynamic values
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element contains arguments.
	 * @param textValue     value to input
	 * @param dynamicValues arguments of @param locatorType.
	 */
	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param attributeName The name of the attribute.
	 * @return The attribute/property's current value or null if the value is not set.
	 */
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param attributeName The name of the attribute.
	 * @param dynamicValues arguments of @param locatorType
	 * @return The attribute/property's current value or null if the value is not set.
	 */
	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	/**
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return The visible text of this element.
	 */
	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return The visible text of this element.
	 */
	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	/**
	 * @param driver       driver of browser
	 * @param locatorType  used to find the element
	 * @param propertyName the css property name of the element
	 * @return The current, computed value of the property.
	 */
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param propertyName  the css property name of the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return The current, computed value of the property.
	 */
	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	/**
	 * Get Hex color from RGBA color
	 *
	 * @param rgbaValue value of rgba
	 * @return color converted Hex color
	 */
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	/**
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return the number of elements in this list.
	 */
	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return the number of elements in this list.
	 */
	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	/**
	 * Is this element displayed or not?
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return Whether or not the element is displayed
	 */
	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	/**
	 * Is this element displayed or not?
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return Whether or not the element is displayed
	 */
	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	/**
	 * Is this element Undisplayed or not?
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return Whether or not the element is Undisplayed
	 */
	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return Whether or not the element is Undisplayed
	 */
	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Override timeout
	 *
	 * @param driver  driver of browser
	 * @param timeOut time wait element do something
	 */
	public void overrideImplicitTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}

	/**
	 * Is the element currently enabled or not?
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		return getWebElement(driver, locatorType).isEnabled();
	}

	/**
	 * Is the element currently enabled or not?
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return True if the element is enabled, false otherwise.
	 */
	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}

	/**
	 * Determine whether or not this element is selected or not?
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @return True if the element is currently selected or checked, false otherwise.
	 */
	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	/**
	 * Determine whether or not this element is selected or not?
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 * @return True if the element is currently selected or checked, false otherwise.
	 */
	public boolean isElementSelected(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isSelected();
	}

	/**
	 * Select a frame using its previously located WebElement.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element to switch to.
	 */
	public void switchToFrameIframe(WebDriver driver, String locatorType, String... dynamicValues) {
		waitForElementVisible(driver, getDynamicXpath(locatorType, dynamicValues));
		driver.switchTo().frame(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	/**
	 * Selects either the first frame on the page, or the main document when a page contains iframes.
	 *
	 * @param driver driver of browser
	 */
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * Moves the mouse to the element
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	/**
	 * Moves the mouse to the element
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}
	
	public void hoverToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * Press the key to interact with the element
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 * @param key         The keys to send from keyboard
	 */
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	/**
	 * Press the key to interact with the element
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param key           The keys to send from keyboard
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	/**
	 * Highlight element before interact
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	/**
	 * Highlight element before interact
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	/**
	 * Click to element using Javascript
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void clickToElementByJS(WebDriver driver, String locatorType) {
		highlightElement(driver, locatorType);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	/**
	 * Click to element using Javascript
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		highlightElement(driver, locatorType, dynamicValues);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	/**
	 * Scroll to element before interact
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	/**
	 * Scroll to element before interact
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void scrollToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}
	
	public void scrollToElement(WebDriver driver, WebElement element) {
		new Actions(driver).scrollToElement(element).perform();
	}

	/**
	 * Remove attribute of element in DOM
	 *
	 * @param driver          driver of browser
	 * @param locatorType     used to find the element
	 * @param attributeRemove Attribute name
	 */
	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	/**
	 * Wait for element is present on the DOM of a page and visible.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	/**
	 * Wait for dynamic element is present on the DOM of a page and visible.
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Wait for all elements present on the web page that match the locator are visible.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	/**
	 * Wait for all elements present on the web page that match the locator are visible.
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Wait for element is either invisible or not present on the DOM.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/**
	 * Wait for dynamic element is either invisible or not present on the DOM.
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  Duration.ofSeconds(shortTimeout));
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	/**
	 * Wait for dynamic element undisplayed in DOM or not in DOM and override implicit timeout
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
		overrideImplicitTimeout(driver, longTimeout);
	}

	/**
	 * Wait for all elements from given list to be invisible
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorType)));
	}

	/**
	 * Wait for all elements from given list to be invisible
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Wait for element is visible and enabled such that you can click it.
	 *
	 * @param driver      driver of browser
	 * @param locatorType used to find the element
	 */
	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	/**
	 * Wait for dynamic element is visible and enabled such that you can click it.
	 *
	 * @param driver        driver of browser
	 * @param locatorType   used to find the element
	 * @param dynamicValues arguments of @param locatorType
	 */
	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	/**
	 * Screenshot attachments for Allure
	 *
	 * @param driver driver of browser
	 * @param name   name of picture
	 */
	public static void takeScreenshot(WebDriver driver, String name) {
		Allure.addAttachment(name, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
	}
	
	public static void attachTextContent(String text) {
		Allure.addAttachment(text, "");
	}

	/**
	 * @param timeoutInSecond
	 */
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private long longTimeout = GlobalConstants.longTimeout;
	private long shortTimeout = GlobalConstants.shortTimeout;
}
