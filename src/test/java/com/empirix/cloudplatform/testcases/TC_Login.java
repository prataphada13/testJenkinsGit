package com.empirix.cloudplatform.testcases;

import java.io.IOException;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import com.empirix.cloudplatform.core.Core;
import com.empirix.cloudplatform.utility.TestUtil;

public class TC_Login extends Core{

	@BeforeClass
	public void testCaseDetails(String browserName) throws IOException {
		System.out.println("Login to portal via password and verify client details");
	}

	@Test(priority = 1)
	public void loginToPortal() {
		if (TestUtil.isElementPresent(Core.or.getProperty("txt_username"))
				&& TestUtil.isElementPresent(Core.or.getProperty("txt_password"))
				&& TestUtil.isElementPresent(Core.or.getProperty("btn_login"))) {

			TestUtil.enterText(Core.or.getProperty("txt_username"), Core.config.getProperty("username"));
			TestUtil.enterText(Core.or.getProperty("txt_password"), Core.config.getProperty("password"));
			TestUtil.clickElement(Core.or.getProperty("btn_login"));

			if (TestUtil.isElementPresent(Core.or.getProperty("lbl_help"))
					&& TestUtil.verifyCurrentURL(Core.config.getProperty("url_dashboard"))) {
				System.out.println("passed - logged in successfully");
			} else {
				System.out.println("failed - login failed");
			}
		} else {
			System.out.println("failed - login Elements not present");
		}
	}

	@Test(priority = 2)
	public void verifyClientDetails() {

		if (TestUtil.isElementPresent(Core.or.getProperty("menu_profile"))) {

			TestUtil.clickElement(Core.or.getProperty("menu_profile"));

			Core.explicitWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Core.or.getProperty("lbl_client"))));

			if (TestUtil.isElementPresent(Core.or.getProperty("lbl_client"))) {

				TestUtil.clickElement(Core.or.getProperty("lbl_client"));

				if (TestUtil.isElementPresent(Core.or.getProperty("lbl_cleintName"))) {
					TestUtil.getElementText(Core.or.getProperty("lbl_cleintName"));
					TestUtil.getElementText(Core.or.getProperty("lbl_description"));
					TestUtil.getElementText(Core.or.getProperty("lbl_subscriptionStartDate"));
					TestUtil.getElementText(Core.or.getProperty("lbl_subscriptionEndDate"));
					TestUtil.getElementText(Core.or.getProperty("lbl_maxLoadTest"));
					TestUtil.getElementText(Core.or.getProperty("lbl_maxVoiceWatchTest"));
					TestUtil.getElementText(Core.or.getProperty("lbl_maxScenario"));
					TestUtil.getElementText(Core.or.getProperty("lbl_minTestSchedulePeriod"));

				}
			}
		}

	}
}
