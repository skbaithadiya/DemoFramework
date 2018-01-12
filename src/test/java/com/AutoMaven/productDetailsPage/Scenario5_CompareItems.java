package com.AutoMaven.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.AutoMaven.Base.TestBase;
import com.AutoMaven.HomePageElements.HomeFiltersPage;
import com.AutoMaven.HomePageElements.HomePage;

public class Scenario5_CompareItems extends TestBase{
	HomePage home;
	HomeFiltersPage homeF;
	
	private static final Logger log = Logger.getLogger(Scenario5_CompareItems.class.getName());

	@BeforeTest
	public void setup() {
		home = new HomePage(driver);
		homeF=new HomeFiltersPage(driver);

	}
	
	
	@Test
	public void compareItems() throws InterruptedException{
		home.goToWomenTopTshirt();
		log.info("Navigating to the Women Top Tshirt Category" );
		home.goToWomenSubMenu("Dresses");
	//	homeF.setPriceRange("18", "45");
	//	homeF.howtomoveSlider();

	}
	

}
