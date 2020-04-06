package com.empirix.cloudplatform.core;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Parameters;


public class Core {

	public static WebDriver driver = null;
	public static Properties config = null;
	public static Properties or = null;
	public FileInputStream inputFile = null;
	public static WebDriverWait explicitWait = null;

	@Parameters({ "Browser" })
	@BeforeSuite
	public void initDriver(String browserName) throws IOException {
		if (driver == null) {
			config = new Properties();
			or = new Properties();

			inputFile = new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/java/com/empirix/cloudplatform/properties/Config.properties");
			config.load(inputFile);

			inputFile = new FileInputStream(System.getProperty("user.dir")
					+ "/src/test/java/com/empirix/cloudplatform/properties/OR.properties");
			or.load(inputFile);

			if (browserName.equalsIgnoreCase(config.getProperty("browser1"))) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver 3");
				driver = new ChromeDriver();

			} else if (browserName.equalsIgnoreCase(config.getProperty("browser2"))) {
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
				driver = new FirefoxDriver();
			}
			//driver.manage().window().maximize();
			driver.get(config.getProperty("webURL"));
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Core.config.getProperty("implicitWait").trim()), TimeUnit.SECONDS);
			explicitWait = new WebDriverWait(driver, Integer.parseInt(Core.config.getProperty("explicitWait").trim()));
		}
	}
	
	
	@AfterTest
	public void endSuite() {
		System.out.println("Automation test suite has been executed");
	}

}
