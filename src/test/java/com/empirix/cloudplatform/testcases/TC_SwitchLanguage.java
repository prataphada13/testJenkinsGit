package com.empirix.cloudplatform.testcases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.empirix.cloudplatform.core.Core;
import com.empirix.cloudplatform.utility.TestUtil;

public class TC_SwitchLanguage {

	@BeforeClass
	public void testCaseDetails() {
		System.out.println("Switch languange to English and japanease");
	}

	@Test(priority = 1)
	public void switchToJapanease() {
		TestUtil.clickElement(Core.or.getProperty("menu_profile"));
		if (TestUtil.getElementAttribute(Core.or.getProperty("div_lang"), "ng-if").contains("en-US")) {
			System.out.println("en-US is selected");
			TestUtil.clickElement(Core.or.getProperty("lbl_japanease"));
			TestUtil.acceptAlert();
		} else {
			System.out.println("japanease is selected");
			TestUtil.clickElement(Core.or.getProperty("lbl_english"));
			TestUtil.acceptAlert();
		}
	}

	@Test(priority = 2)
	public void verifyDashboard() {

		TestUtil.verifyAndClickTab(Core.config.getProperty("url_dashboard"), Core.or.getProperty("tab_dashboard"));
	}

	@Test(priority = 3)
	public void verifyAlerts() {
		TestUtil.verifyAndClickTab(Core.config.getProperty("url_alerts"), Core.or.getProperty("tab_alerts"));
	}

	@Test(priority = 4)
	public void verifyTests() {
		TestUtil.verifyAndClickTab(Core.config.getProperty("url_tests"), Core.or.getProperty("tab_tests"));
	}

	@Test(priority = 5)
	public void verifyVariables() {
		TestUtil.verifyAndClickTab(Core.config.getProperty("url_variables"), Core.or.getProperty("tab_variables"));
	}

	@Test(priority = 6)
	public void verifyNotifications() {
		TestUtil.verifyAndClickTab(Core.config.getProperty("url_notifications"),
				Core.or.getProperty("tab_notifications"));
	}

}
