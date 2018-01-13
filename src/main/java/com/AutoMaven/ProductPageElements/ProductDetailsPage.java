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

public class ProductDetailsPage extends BasePage {

	@FindBy(id = "quantity_wanted")
	WebElement quantityTextBox;

	@FindBy(className = "icon-minus")
	WebElement quantityMinusButton;

	@FindBy(className = "icon-plus")
	WebElement quantityPlusButton;

	@FindBy(id = "group_1")
	WebElement sizeDropDownBox;

	@FindBy(id = "color_to_pick_list")
	WebElement selectColor;

	@FindBy(name = "Submit")
	WebElement addToCartButton;

	@FindBy(id = "wishlist_button")
	WebElement wishlistButton;

	@FindBy(className = "fancybox-error")
	WebElement errorForWishList;

	@FindBy(xpath = "//a[contains(@title,'Close')]")
	WebElement closeError;

	@FindBy(className = "lnk_wishlist")
	WebElement wishlistButtonL;

	WebDriver driver;
	public static final Logger log = Logger.getLogger(ProductDetailsPage.class.getName());

	public ProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private void selectSize(String size) {
		selectByVisibleTextFromDropDownBox(driver, sizeDropDownBox, size);
	}

	/***
	 * this method will dynamically find the Colors present in Filters on Product Detail Page
	 * @param colorName
	 * @return
	 */
	public WebElement color(String colorName) {
		WebElement element = driver.findElement(By.xpath("//*[contains(@title,'" + colorName + "')]"));
		return element;
	}

	/***
	 * it will select the Color passed as parameter on Product Detail Page
	 * it will also check if color is already selected
	 * If color is not present in the page it will Log the Error message but wont stop execution
	 * @param colorName name of the color to select
	 */
	public void selectColor(String colorName) {
		if (color(colorName).isDisplayed() || color(colorName).isEnabled()) {
			if (!color(colorName).isSelected()) {
				color(colorName).click();
				log.info(colorName + " is selected");
			} else {
				log.info(colorName + " is already selected.");
				// System.out.println("Element is selected already");
			}
		} else {
			log.error("["+colorName +"] color is not present in the Page");
		}
	}

	/***
	 * this method will add product to Wishlist/Cart 
	 * @param option wishlist/cart
	 * @param quantity 
	 * @param size
	 * @param color
	 */
	public void addProductTo(String option, String quantity, String size, String color) {
		inputIntoTextbox(driver, quantityTextBox, quantity);
		selectSize(size);
		selectColor(color);
		if (option.equalsIgnoreCase("wishlist")) {
			scrollToElement(driver, wishlistButton);
			checkElementIsClickable(driver, wishlistButton);
			clickButton(driver, wishlistButton);
			log.info("clicked on Wishlist button");
		} else if (option.equalsIgnoreCase("cart")) {
			scrollToElement(driver, addToCartButton);
			checkElementIsClickable(driver,addToCartButton);
			clickButton(driver, addToCartButton);
			log.info("Clicked on Add to Cart Button.");
		}

	}
	
	/***
	 * this will click on Add Product to cart button
	 */
	public void addProductToCart(){
		scrollToElement(driver, addToCartButton);
		checkElementIsClickable(driver,addToCartButton);
		clickButton(driver, addToCartButton);
		log.info("Clicked on Add to Cart Button.");
	}

	/***
	 * get the Error Message on the Product Details Page
	 * @return
	 */
	public String fancyError() {
		String text = "";
		checkElementIsVisible(errorForWishList);
		if (errorForWishList.isDisplayed() || errorForWishList.isEnabled()) {
			text = errorForWishList.getText();
			log.info("Alert message shown as: " + text);
		}
		return text;
	}

	public void checkElementIsVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		element = wait.until(ExpectedConditions.visibilityOf(element));
	}

	/***
	 * it will close the Message shown on Product Details Page
	 */
	public void closeFancyMessage() {
		checkElementIsVisible(errorForWishList);
		closeError.click();
		log.info("Alert message is closed now");
	}

}
