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

	//parameters in @Parameters annotation are being passed from testng.xml file
	@Parameters({ "browser", "url", "excelFilePath" })
	@BeforeSuite
	/***
	 * this method is to initialize WebDriver and WebEvent Listener.
	 * all the parameters passed below will be in same order as @Parameters Annotation above the method
	 * @param browser name of the browser "chrome/firefox", not case-sensitive
	 * @param url URL to be navigated on first invocation of Webdriver
	 * @param excelFilePath path of the Excel DataFile
	 */
	public void init(String browser, String url, String excelFilePath) {
		//set log4j.proerties file path 
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		//set excel file path and initialize ExcelReader
		excel = new ExcelReader(excelFilePath);
		selectBrowser(browser);
		getUrl(url);

	}

	/***
	 * this method will be called after each Test Suite run
	 * it will close all the Webdriver instances
	 */
	@AfterSuite
	public void end() {
		//close the WebDriver instance
		driver.close();
		//quit all WebDriver instance
		driver.quit();
	}

	/***
	 * this method will select the browser passed as parameter.
	 * it will detect on runtime whether system being use is Windows or Mac OS,
	 * and based on that it will call webdriver
	 * @param browser name of the browser. currently supported: "chrome/firefox"
	 */
	public void selectBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			//check the OS 
			if (System.getProperty("os.name").contains("Win")) {
				//if OS is windows then call below chromedriver
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
			} else {
				//if OS is Mac OS then call below chromedriver
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
			}
			ChromeOptions option = new ChromeOptions();
			option.addArguments("disable-infobars");
			wDriver = new ChromeDriver(option);
			//wDriver.manage().window().maximize();
		} else if (browser.equalsIgnoreCase("firefox")) {
			// check the OS
			if (System.getProperty("os.name").contains("Win")) {
				//if OS is windows then call below firefoxdriver
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "\\driver\\geckodriver.exe");
			} else {
				//if OS is Mac OS then call below firefoxdriver
				System.setProperty("webdriver.firefox.marionette", System.getProperty("user.dir") + "/driver/geckodriver");
				wDriver = new FirefoxDriver();
			}
		} else {
			log.error("Browser [" + browser + "] - Wrong Browser Selection!");
			//log.error("Wrong browser selection!");
		}
		log.info("Launching [" + browser + "] browser!!!!");
		driver = new EventFiringWebDriver(wDriver);
		//making WebEventListeners instance and passing it in WebDriver
		eventListener = new WebEventListener();
		driver.register(eventListener);
	}

	/***
	 * navigate to URL 
	 * @param url URL to be navigated
	 */
	public void getUrl(String url) {
		log.info("Navigating to: " + url);
		// go to URL passed as parameter
		driver.get(url);
		//maximizing window of the browser
		 driver.manage().window().maximize();
		 //implicit wait for 30 seconds after URL hits
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

}
