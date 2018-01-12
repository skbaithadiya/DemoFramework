package com.AutoMaven.productDetailsPage;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.AutoMaven.Base.TestBase;
import com.AutoMaven.HomePageElements.CreateAnAccount;
import com.AutoMaven.HomePageElements.HomePage;
import com.AutoMaven.HomePageElements.MyAccountPage;
import com.AutoMaven.HomePageElements.SignInPage;
import com.AutoMaven.ProductPageElements.AddToCartPage;
import com.AutoMaven.ProductPageElements.ProductDetailsPage;
import com.AutoMaven.ProductPageElements.SearchProductPage;
import com.AutoMaven.ProductPageElements.WishlistPage;

public class BuyProductTc extends TestBase {
	HomePage home;
	SignInPage signin;
	SearchProductPage sTshirt;
	ProductDetailsPage pDetails;
	WishlistPage wishlist;
	MyAccountPage myaccount;
	AddToCartPage addcart;
	SearchProductPage search;
	CreateAnAccount create;
	public static final Logger log = Logger.getLogger(BuyProductTc.class.getName());

	@BeforeTest
	public void setup() {
		// init();
		home = new HomePage(driver);
		sTshirt = new SearchProductPage(driver);
		pDetails = new ProductDetailsPage(driver);
		signin = new SignInPage(driver);
		wishlist = new WishlistPage(driver);
		myaccount = new MyAccountPage(driver);
		addcart = new AddToCartPage(driver);
		search = new SearchProductPage(driver);
		create = new CreateAnAccount(driver);
	}

	@Test
	public void addProductToCart() throws InterruptedException {
		log.info("========= Starting testcase: Add Product to Cart =========");
		home.goToHomePage();
		log.info("Navigating to Tshirts Category");

		/* step 2 */
		//for (int i = 0; i < 3; i++) {
			sTshirt.scrollToProduct(excel.excelDataReader("ProductPage", "TCP1001", "ProductName"));
			home.clickAddToCartButton();
			log.info("Adding product to the cart");
			Assert.assertEquals(addcart.getSuccessMessage(),
					excel.excelDataReader("ProductPage", "TCP1001", "SuccessMessage"));
			addcart.closeAddToCartDetailBox();
			//System.out.println("added 1st product");

			sTshirt.scrollToProduct(excel.excelDataReader("ProductPage", "TCP1002", "ProductName"));
			home.clickAddToCartButton();
			log.info("Adding product to the cart");
			Assert.assertEquals(addcart.getSuccessMessage(),
					excel.excelDataReader("ProductPage", "TCP1002", "SuccessMessage"));
			addcart.closeAddToCartDetailBox();
			//System.out.println("added 2nd product");

			sTshirt.scrollToProduct(excel.excelDataReader("ProductPage", "TCP1003", "ProductName"));
			home.clickAddToCartButton();
			log.info("Adding product to the cart");
			Assert.assertEquals(addcart.getSuccessMessage(),
					excel.excelDataReader("ProductPage", "TCP1003", "SuccessMessage"));
			addcart.closeAddToCartDetailBox();
			//System.out.println("added 3rd product");
		//}
		// checking product Name
		home.hoverMyCart();
		Thread.sleep(1000);
		Assert.assertTrue(
				addcart.checkProductNameFromCart(excel.excelDataReader("ProductPage", "TCP1001", "ProductName")));
		Assert.assertTrue(
				addcart.checkProductNameFromCart(excel.excelDataReader("ProductPage", "TCP1002", "ProductName")));
		Assert.assertTrue(
				addcart.checkProductNameFromCart(excel.excelDataReader("ProductPage", "TCP1003", "ProductName")));
		//check product color
		
		/* step 2 end */

		home.hoverMyCart();
		Thread.sleep(1000);
		addcart.clickCheckoutButtonCart();

		// String quantity =addcart.getUnitPriceOfProduct("Blouse");
		System.out.println("unit price of above product is: " + addcart.getUnitPriceOfProduct("Blouse"));
		System.out.println("unit price of above product is: " + addcart.getAvailabilityOfProduct("Blouse"));
		// addcart.getProductColorOrSize("Blouse","size");

		home.hoverMyCart();
		System.out.println(
				"price of product from cart is: " + addcart.getProductPriceFromCart("Faded Short Sleeve T-shirts"));
		addcart.clickProceedToCheckoutButtonSummaryPage();

		// sign in
		signin.doLogin("email1506004855261@yoyoyo.com", "1234567890");
		
		//step 10: add new delivery address
		home.clickUpdateDeliveryAddressButton();

		create.updateDeliveryAddress("firstnameFriend","lastname Friend", "gcs", "gcs1", "gcs2", "city", "Alaska", "10010",
				"United States", "no additonal info", "10101010", "098989898", "no alias");
		addcart.clickProceedToCheckoutAtAddressPage();
		
		addcart.selectIAgreeCheckbox();
		addcart.clickProceedToCheckoutButtonShippingPage();
		addcart.selectPaymentMode("bankwire");
		addcart.clickIConfirmMyOrderButton();
		Assert.assertEquals(addcart.getConfirmationMessageOnCompletion(), "Your order on My Store is complete.");

		log.info("========= End testcase: Add Product to Cart =========");
	}

	// @Test
	public void scenario2_BuyProduct() {
		search.scrollToProduct("Faded Short Sleeve T-shirts");
		home.clickAddToCartButton();
	}

}
