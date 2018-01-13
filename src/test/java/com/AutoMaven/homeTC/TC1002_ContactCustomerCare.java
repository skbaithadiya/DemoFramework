package com.AutoMaven.homeTC;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.AutoMaven.Base.TestBase;
import com.AutoMaven.ExcelReader.ExcelReader;
import com.AutoMaven.HomePageElements.ContactUsPage;
import com.AutoMaven.HomePageElements.HomeFiltersPage;
import com.AutoMaven.HomePageElements.HomePage;

public class TC1002_ContactCustomerCare extends TestBase {

	public ContactUsPage contact;
	HomePage home;
	HomeFiltersPage homeF;
	private static final Logger log = Logger.getLogger(TC1002_ContactCustomerCare.class.getName());

	@Parameters({ "excelFilePath" })
	@BeforeTest
	public void setup(String excelPath) {
		contact = new ContactUsPage(driver);
		home = new HomePage(driver);
		homeF = new HomeFiltersPage(driver);
		excel = new ExcelReader(excelPath);
	}

	@Test
	public void verifySendingMessageToCustomerCareWithoutAttachment() throws InterruptedException {
		home.clickContactUsButton();
		log.info("Nagivating to the Contact Us Page");
		Assert.assertEquals(contact.getPageTitle(), "customer service - contact us");
		contact.sendMessageToCustomerServiceWithUpload(
				excel.excelDataReader("CustomerCare", "TC2001", "SubjectHeading"),
				excel.excelDataReader("CustomerCare", "TC2001", "Email"),
				excel.excelDataReader("CustomerCare", "TC2001", "OrderReference"),
				excel.excelDataReader("CustomerCare", "TC2001", "Message"),
				System.getProperty("user.dir")+"\\attachment\\no.jpg");
		Assert.assertEquals(contact.getSuccessMessage(), "your message has been successfully sent to our team.");
	}

	// @Test
	public void checkSlider() throws InterruptedException {
		home.goToWomen();
		homeF.setPriceRange("18", "45");
		homeF.selectColorFromFilters("orange");
		homeF.selectSizeFromFilters("L");
	}

	// @AfterTest
	public void closeBrowser() {
		end();
	}

}
