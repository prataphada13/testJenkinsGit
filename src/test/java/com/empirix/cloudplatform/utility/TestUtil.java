package com.empirix.cloudplatform.utility;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.empirix.cloudplatform.core.Core;

public class TestUtil {

	public static Alert webAlert;

	public static boolean isElementPresent(String locator) {
		if (Core.driver.findElements(By.xpath(locator)).size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static WebElement getWebElement(String locator) {
		return Core.driver.findElement(By.xpath(locator));
	}

	public static String getElementAttribute(String locator, String attributeName) {
		return Core.driver.findElement(By.xpath(locator)).getAttribute(attributeName);
	}

	public static void enterText(String locator, String inputText) {
		Core.driver.findElement(By.xpath(locator)).sendKeys(inputText);
	}

	public static void clickElement(String locator) {
		Core.driver.findElement(By.xpath(locator)).click();
	}

	public static void getElementText(String locator) {
		System.out.println(Core.driver.findElement(By.xpath(locator)).getText());
	}

	public static boolean verifyCurrentURL(String url) {
		if (Core.driver.getCurrentUrl().equalsIgnoreCase(url)) {
			return true;
		} else {
			return false;
		}
	}

	public static void acceptAlert() {
		webAlert = Core.driver.switchTo().alert();
		webAlert.accept();
	}

	public static void declineAlert() {
		webAlert = Core.driver.switchTo().alert();
		webAlert.dismiss();
	}

	public static void explicityWaitForElementVisible(String locator) {
		Core.explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
	}

	public static void verifyAndClickTab(String currentURL, String tabLocator) {
		try {
			explicityWaitForElementVisible(tabLocator);
			clickElement(tabLocator);
			Thread.sleep(Integer.parseInt(Core.config.getProperty("threadSleep").trim()));
			verifyCurrentURL(currentURL);
			System.out.println("passed - URL " + currentURL);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void handleDropdown(String locator, String selectBy, String selectedInput) {
		Select dropdown = new Select(Core.driver.findElement(By.xpath(locator)));
		if (selectBy.equalsIgnoreCase("visible")) {
			dropdown.selectByVisibleText(selectedInput);
		} else if (selectBy.equalsIgnoreCase("value")) {
			dropdown.selectByVisibleText(selectedInput);
		} else if (selectBy.equalsIgnoreCase("index")) {
			dropdown.selectByVisibleText(selectedInput);
		}
	}
}
