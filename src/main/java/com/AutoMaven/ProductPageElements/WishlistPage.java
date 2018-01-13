package com.AutoMaven.ProductPageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.AutoMaven.Base.BasePage;

public class WishlistPage extends BasePage {

	@FindBy(className = "icon-remove")
	WebElement iconRemoveButton;

	WebDriver driver;

	public WishlistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/***
	 * to get the Wishlist Webelement by passing Wishlist's name as parameter (On wishlist Page)
	 * @param nameWishList
	 * @return webelement wishlist
	 */
	private WebElement wishlist(String nameWishList) {
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + nameWishList + "')]"));
		return element;

	}

	/***
	 * to get the Product from wishlist by passing name of the product as parameter
	 * @param nameProduct
	 * @return WebElement Product
	 */
	private WebElement product(String nameProduct) {
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),'" + nameProduct + "')]"));
		return element;
	}

	/***
	 * By calling this method will Navigate to specific wishlist by passing wishlist's name
	 * @param wishlistName
	 */
	public void viewWishlist(String wishlistName) {
		wishlist(wishlistName).click();
	}

	/***
	 * To check the product if its available in the wishlist
	 * @param wishlistName
	 * @param productName
	 */
	public void checkProduct(String wishlistName, String productName) {
		scrollToElement(driver, wishlist(wishlistName));
		wishlist(wishlistName).click();
		scrollToElement(driver, product(productName));
	}

	/***
	 * By calling this method will delete existing wishlist by passing wishlist's name
	 */
	public void deleteWishlist() {
		scrollToElement(driver, iconRemoveButton);
		iconRemoveButton.click();
		//String alertMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
	}
	
	/***
	 * this method will verify if wishlist is present or not
	 * @param wishlistName
	 * @return true/false
	 */
	public boolean verifyWishlist(String wishlistName){
		boolean boo = ExpectedConditions.invisibilityOf(wishlist(wishlistName)) != null;
		return boo;
		
	}
}
