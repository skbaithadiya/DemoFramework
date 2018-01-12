package com.AutoMaven.Base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.annotations.AfterSuite;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.AutoMaven.CustomListener.WebEventListener;
import com.AutoMaven.ExcelReader.ExcelReader;

public class TestBase {

	public static com.relevantcodes.extentreports.ExtentReports extent;
	public static com.relevantcodes.extentreports.ExtentTest test;
	public static WebDriver wDriver;
	public static EventFiringWebDriver driver;
	public WebEventListener eventListener;
	public static final Logger log = Logger.getLogger(TestBase.class.getName());

	// String url = "http://automationpractice.com/index.php";
	String browser = "chrome";
	public ExcelReader excel;
	WebDriverWait wait;

	@Parameters({ "browser", "url", "excelFilePath" })
	@BeforeSuite
	public void init(String browser, String url, String excelFilePath) {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		excel = new ExcelReader(excelFilePath);
		selectBrowser(browser);
		getUrl(url);

	}

	@AfterSuite
	public void end() {
		driver.close();
		driver.quit();
	}

	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			if (System.getProperty("os.name").contains("Win")) {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
			}
			ChromeOptions option = new ChromeOptions();
			//option.addArguments("disable-infobars");
			wDriver = new ChromeDriver(option);
			//wDriver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {

			if (System.getProperty("os.name").contains("Win")) {
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");

			} else {
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/driver/geckodriver");
				wDriver = new FirefoxDriver();
			}
		} else {
			log.error("Browser [" + browser + "]");
			log.error("Wrong browser selection!");
		}
		log.info("Launching [" + browser + "] browser!!!!");
		driver = new EventFiringWebDriver(wDriver);
		eventListener = new WebEventListener();
		driver.register(eventListener);
	}

	public void getUrl(String url) {
		log.info("Navigating to: " + url);
		driver.get(url);
		// driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

}
