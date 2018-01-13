package com.AutoMaven.ProductPageElements;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AutoMaven.Base.BasePage;

public class SearchProductPage extends BasePage {

	@FindBy(xpath = "//h5/a[contains(text(),'Faded Short Sleeve T-shirts')]")
	WebElement tshirt1;

	@FindBy(name = "Submit")
	WebElement addToCartButton;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	WebElement proceedToCheckOutButton;

	@FindBy(className = "cat-name")
	WebElement productTypeTitle;

	WebDriver driver;
	public static final Logger log = Logger.getLogger(SearchProductPage.class.getName());

	public SearchProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public WebElement elementCheckIsClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		element = wait.until(ExpectedConditions.elementToBeClickable(element));
		return element;
	}

	private WebElement findProduct(String title) {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath("//h5/a[contains(text(),'" + title + "')]"));
		} catch (Exception e) {
			log.error(title + " is Not Found!");
		}
		return element;
	}

	/***
	 * This will navigate to the Product Page
	 * @param productTitle
	 */
	public void goToProduct(String productTitle) {
		try {
			WebElement element = findProduct(productTitle);
			scrollToElement(driver,element);
			clickButton(driver, element);
		} catch (Exception e) {
			log.error("element is not clikable yet!");
			e.printStackTrace();
		}
	}
	
	public void scrollToProduct(String productTitle){
		WebElement element = findProduct(productTitle);
		scrollToElement(driver,element);
	}

	public String titleOfProductType() {
		String sValue = productTypeTitle.getText().trim().toLowerCase();
		return sValue;
	}

	public void addToCart() {
		if (addToCartButton.isDisplayed() || addToCartButton.isEnabled()) {
			addToCartButton.click();
		}
	}

	public void proceedToCheckOut() {
		elementCheckIsClickable(proceedToCheckOutButton).click();

	}

}
