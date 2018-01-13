package com.AutoMaven.productDetailsTC;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.AutoMaven.Base.TestBase;
import com.AutoMaven.HomePageElements.HomePage;
import com.AutoMaven.HomePageElements.MyAccountPage;
import com.AutoMaven.HomePageElements.SignInPage;
import com.AutoMaven.ProductPageElements.ProductDetailsPage;
import com.AutoMaven.ProductPageElements.SearchProductPage;
import com.AutoMaven.ProductPageElements.WishlistPage;

public class TCPD1001_CheckProduct extends TestBase {
	HomePage home;
	SignInPage signin;
	SearchProductPage sTshirt;
	ProductDetailsPage pDetails;
	WishlistPage wishlist;
	MyAccountPage myaccount;
	public static final Logger log = Logger.getLogger(TCPD1001_CheckProduct.class.getName());

	@BeforeTest
	public void setup() {
		// init();
		home = new HomePage(driver);
		sTshirt = new SearchProductPage(driver);
		pDetails = new ProductDetailsPage(driver);
		signin = new SignInPage(driver);
		wishlist = new WishlistPage(driver);
		myaccount = new MyAccountPage(driver);

	}

	@Test
	public void addProductToWishlist() throws InterruptedException {
		log.info("========= Starting testcase: Add Product to Cart =========");
		home.goToTshirts();
		log.info("Navigating to Tshirts Category");
		sTshirt.goToProduct(excel.excelDataReader("ProductPage", "TCP1001", "ProductName"));
		pDetails.addProductTo("wishlist", "2", "L", "Orange");
		log.info("Adding product to the cart");

		if (pDetails.fancyError().contentEquals("You must be logged in to manage your wishlist.")) {
			// System.out.println("test loop");
			log.error("User is not loging, Need to Login First!");
			pDetails.closeFancyMessage();
			home.clickSignInButton();
			log.info(
					"Sign in to the User Account, Where Username is: " + excel.excelReaderLoginPage("Login", "Username")
							+ " And Password is: " + excel.excelReaderLoginPage("Login", "Password"));
			signin.doLogin(excel.excelReaderLoginPage("Login", "Username"),
					excel.excelReaderLoginPage("Login", "Password"));
			// home.goToTshirts();
			home.goToHomePage();
			home.goToWomenTopTshirt();
			sTshirt.goToProduct(excel.excelDataReader("ProductPage", "TCP1001", "ProductName"));
			excel.excelDataWriter("ProductPage", "TCP1001", "Result", "Passed");
			pDetails.addProductTo("wishlist", "2", "L", "Orange");

			if (pDetails.fancyError().contentEquals("Added to your wishlist.")) {
				pDetails.closeFancyMessage();
			}
		} else {
			System.out.println("condition failed!");
		}
		// going to my account page
		home.goToMyAccountPage("iamnotuserokay");
		myaccount.goToMyWishlist();
		wishlist.checkProduct("wishlist", excel.excelDataReader("ProductPage", "TCP1001", "ProductName"));
		wishlist.deleteWishlist();
		Assert.assertTrue(wishlist.verifyWishlist("wishlist"));
		log.info("========= end testcase: Add Product to Cart =========");
	}

	// @Test
	public void createOrder() {
		sTshirt.goToProduct("Faded Short Sleeve T-shirts");
		// sTshirt.addToCart();

	}

	// @Test
	public void scenario4() {
		home.goToWomenTopTshirt();
	}

}
