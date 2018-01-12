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

	private WebElement wishlist(String nameWishList) {
		WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + nameWishList + "')]"));
		return element;

	}

	private WebElement product(String nameProduct) {
		WebElement element = driver.findElement(By.xpath("//p[contains(text(),'" + nameProduct + "')]"));
		return element;
	}

	public void viewWishlist(String wishlistName) {
		wishlist(wishlistName).click();
	}

	public void checkProduct(String wishlistName, String productName) {
		scrollToElement(driver, wishlist(wishlistName));
		wishlist(wishlistName).click();
		scrollToElement(driver, product(productName));
	}

	public void deleteWishlist() {
		scrollToElement(driver, iconRemoveButton);
		iconRemoveButton.click();
		//String alertMessage = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
	}
	
	public boolean verifyWishlist(String wishlistName){
		boolean boo = ExpectedConditions.invisibilityOf(wishlist(wishlistName)) != null;
		return boo;
		
	}
}
