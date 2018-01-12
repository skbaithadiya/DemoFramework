package com.AutoMaven.HomePageElements;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutoMaven.Base.BasePage;

public class MyAccountPage extends BasePage{

	@FindBy(className="icon-heart")
	WebElement myWishlistButton;
	
	WebDriver driver;
	
	public MyAccountPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToMyWishlist(){
		waitForElementVisible(driver, myWishlistButton);
		scrollToElement(driver, myWishlistButton);
		clickButton(driver, myWishlistButton);

	}
	
	
}
