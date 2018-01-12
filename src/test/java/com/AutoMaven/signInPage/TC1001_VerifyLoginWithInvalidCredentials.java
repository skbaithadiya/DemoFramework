package com.AutoMaven.signInPage;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.AutoMaven.Base.TestBase;
import com.AutoMaven.ExcelReader.ExcelReader;
import com.AutoMaven.HomePageElements.HomePage;
import com.AutoMaven.HomePageElements.SignInPage;
import com.AutoMaven.ProductPageElements.SearchProductPage;

import example.example;

public class TC1001_VerifyLoginWithInvalidCredentials extends TestBase {

	public static final Logger log = Logger.getLogger(TC1001_VerifyLoginWithInvalidCredentials.class.getName());
	SignInPage signIn;
	HomePage home;
	SearchProductPage tshirtP;

	// @BeforeSuite
	public void setup() {
		// init();
	}

	@Parameters({ "excelFilePath" })
	@BeforeTest
	public void setSignInPage(String excelPath) {
		signIn = new SignInPage(driver);
		home = new HomePage(driver);
		excel = new ExcelReader(excelPath);

	}

	// @Test(testName = "CheckScreenshot")
	public void doInvalidLogin() throws InterruptedException, IOException {
		home.clickSignInButton();
		// System.out.println(excel.excelReader("Sheet1", "Username"));
		signIn.doLogin(excel.excelDataReader("Login", "InvalidLogin", "Username"),
				excel.excelDataReader("Login", "InvalidLogin", "Password"));
		excel.excelDataWriter("Login", "InvalidLogin", "Result", "Failed");
		Assert.assertEquals(signIn.getLoginErrorText(), "Authentication failed.");
	}

	@Test(priority = 0)
	public void doValidLogin() throws InterruptedException {
		home.clickSignInButton();
		// System.out.println(excel.excelReader("Sheet1", "Username"));
		signIn.doLogin(excel.excelDataReader("Login", "Login", "Username"),
				excel.excelDataReader("Login", "Login", "Password"));
		Assert.assertTrue(signIn.isSignOutButtonPresent());

	}

	// @Test(priority=1)
	public void doValidLogin2() throws InterruptedException {
		home.clickSignInButton();
		// System.out.println(excel.excelReader("Sheet1", "Username"));
		signIn.doLogin(excel.excelDataReader("Login", "Login", "Username"),
				excel.excelDataReader("Login", "Login", "Password"));
		Assert.assertTrue(signIn.isSignOutButtonPresent());

	}

	// @Test
	public void signOutAccount() {
		log.info("========= Singing out ==========");
		home = new HomePage(driver);
		home.clickSignOutButton();
	}

	// @AfterTest
	public void endTest() {
		end();
	}
}
