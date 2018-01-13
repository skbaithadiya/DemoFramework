package com.AutoMaven.homeTC;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.AutoMaven.Base.TestBase;
import com.AutoMaven.HomePageElements.CreateAnAccount;
import com.AutoMaven.HomePageElements.HomePage;
import com.AutoMaven.HomePageElements.SignInPage;

public class TCSP1001_VerifyCreateNewAccount extends TestBase {

	public static final Logger log = Logger.getLogger(TCSP1001_VerifyCreateNewAccount.class.getName());
	CreateAnAccount createaccount;
	SignInPage signIn;
	HomePage home;
	String emailIdToBeCreated = "email" + System.currentTimeMillis() + "@yoyoyo.com";
	String firstname = "firstname" + System.currentTimeMillis();



	@Test
	public void createNewAccount() throws InterruptedException {
		log.info("========= Starting testcase: createNewAccount =========");
		home = new HomePage(driver);
		home.clickSignInButton();
		signIn = new SignInPage(driver);
		signIn.goToCreateAccountPage(emailIdToBeCreated);
		createaccount = new CreateAnAccount(driver);
		createaccount.createNewAccount("Mr", "iamnotuser", "lastuserName", "email1506004855261@yoyoyo.com", "1234567890", "1", "10",
				"1994", "no", "no", "4th floor", "helios", "gcs", "gcs1", "gcs2", "city", "Alaska", "10010",
				"United States", "no additonal info", "10101010", "098989898", "no alias");
		log.info("========= End testcase: createNewAccount =========");
	}


	//@AfterTest
	public void afterTest() {
		end();
	}
}
