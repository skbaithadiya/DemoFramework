package com.AutoMaven.HomePageElements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.AutoMaven.Base.BasePage;

public class HomePage extends BasePage {
	public static final Logger log = Logger.getLogger(SignInPage.class.getName());

	@FindBy(className = "login")
	WebElement signInButton;

	@FindBy(className = "logout")
	WebElement signOutButton;

	@FindBy(id = "search_query_top")
	WebElement searchTextBox;

	@FindBy(name = "submit_search")
	WebElement searchButton;

	@FindBy(xpath = "//a[contains(@title,'View my shopping cart')]")
	WebElement goToCartButton;

	@FindBy(xpath = "//a[contains(@title,'Add to cart')]")
	WebElement addToCartButton;

	@FindBy(xpath = "//a[contains(@title,'Contact Us')]")
	WebElement contactUsButton;

	@FindBy(xpath = "//img[contains(@class,'logo')]")
	WebElement homePageLogo;

	// Home Page Header Elements //

	@FindBy(xpath = "//a[contains(text(),'Women')]")
	WebElement womenHeader;

	@FindBy(xpath = "//ul[contains(@class,'submenu')]//li/a[contains(@title,'T-shirts')]")
	WebElement womenTopTshirt;

	@FindBy(xpath = "//a[contains(text(),'Dresses')]")
	WebElement dressesHeader;

	@FindBy(xpath = "//*[@id='block_top_menu']/ul/li[3]/a")
	WebElement tshirtHeader;
	WebDriver driver;

	@FindBy(xpath = "//ul[@id='address_delivery']/li/a/span")
	private WebElement updateDeliveryAddressButton;

	@FindBy(xpath = "//ul[@id='address_invoice']/li/a/span")
	private WebElement updateBillingAddressButton;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickSignInButton() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(signInButton));
		scrollToElement(driver, signInButton);
		clickButton(driver, signInButton);
		log.info("clicked on the Sign In button and Object is: " + signInButton.toString());

	}

	public void clickSignOutButton() {
		clickButton(driver, signOutButton);
		log.info("Clicked on Sign Out button and Object is:" + signOutButton.toString());
	}

	public void goToTshirts() throws InterruptedException {
		if (tshirtHeader.isDisplayed() || tshirtHeader.isEnabled()) {
			clickButton(driver, tshirtHeader);
			Thread.sleep(3000);
		}
	}

	public void goToWomen() {
		clickButton(driver, womenHeader);
	}

	public void clickContactUsButton() {
		checkElementIsClickable(driver, contactUsButton);
		clickButton(driver, contactUsButton);
	}

	public WebElement userAccount(String userName) {
		WebElement element = driver.findElement(By.xpath("//span[contains(text(),'" + userName + "')]"));
		return element;
	}

	public void goToMyAccountPage(String userName) throws InterruptedException {
		Thread.sleep(2000);
		WebElement element = userAccount(userName);
		scrollToElement(driver, element);
		clickButton(driver, userAccount(userName));
	}

	public void clickAddToCartButton() {
		scrollToElement(driver, addCartButton());
		clickButton(driver, addCartButton());
	}

	public void hoverMyCart() {
		scrollToElement(driver, goToCartButton);
		Actions action = new Actions(driver);
		action.moveToElement(goToCartButton).build().perform();
	}

	public void goToHomePage() {
		clickButton(driver, homePageLogo);
	}

	public void goToWomenTopTshirt() {
		scrollToElement(driver, womenHeader);
		System.out.println("scrolling done");
		Actions action = new Actions(driver);
		action.moveToElement(womenHeader).build().perform();
		action.moveToElement(womenTopTshirt).click().build().perform();
		System.out.println("hover and cliked done");

	}

	/***
	 * to go to Women sub categories (it will hover on Women Section and find
	 * the subcategory and click on it)
	 * 
	 * @param categoryName
	 *            name of the category in Women section for ex: 'Tops'
	 * @param subCategoryName
	 *            Subcategory name in Women Section for ex: 'Summer Dresses'
	 */
	public void goToWomenSubMenu(String categoryName, String subCategoryName) {
		scrollToElement(driver, womenHeader);
		System.out.println("scrolling done");
		Actions action = new Actions(driver);
		action.moveToElement(womenHeader).build().perform();
		action.moveToElement(womenSubCategory(categoryName, subCategoryName)).click().build().perform();
		log.info("Hover and clicked on [" + categoryName + "> " + subCategoryName+"]");
	}


	/***
	 * to go to Women sub categories (it will hover on Women Section and find
	 * the category inside Women Section and click on it)
	 * 
	 * @param categoryName
	 *            name of the category in Women section for ex: 'Tops'
	 */
	public void goToWomenSubMenu(String categoryName) {
		scrollToElement(driver, womenHeader);
		System.out.println("scrolling done");
		Actions action = new Actions(driver);
		action.moveToElement(womenHeader).build().perform();
		action.moveToElement(womenSubCategory(categoryName)).click().build().perform();
		log.info("Hover and clicked on [" + categoryName+"]");
	}
	
	/***
	 * to go to Women's category
	 * 
	 * @param subCategory
	 *            Name of sub category for ex: 'Tops' etc.
	 * @return returns the element of the choosen category based on the
	 *         parameters
	 */
	private WebElement womenSubCategory(String category) {
		WebElement element = driver.findElement(By.xpath("//a[@title='Women']/following::ul/li/a[@title='"+category+"']"));
		waitForElementVisible(driver, element);
		return element;
	}
	
	/***
	 * to go to Women's subcategory
	 * 
	 * @param subCategory
	 *            Name of sub category for ex: 'Tops' etc.
	 * @param categoryName
	 *            Name of child Subcategory name for ex: 'Evening Dresses'
	 * @return returns the element of the choosen subcategory based on the
	 *         parameters
	 */
	private WebElement womenSubCategory(String category, String subcategory) {
		WebElement element = driver.findElement(By.xpath("//a[@title='Women']/following::ul/li/a[@title='" + category
				+ "']/following::ul/li/a[@title='" + subcategory + "']"));
		waitForElementVisible(driver, element);
		return element;
	}
	

	private WebElement addCartButton() {
		WebElement addCartButton = null;
		List<WebElement> e = driver.findElements(By.xpath("//a[contains(@title,'Add to cart')]"));
		int count = e.size();
		for (int i = 0; i < count; i++) {
			if (e.get(i).isDisplayed()) {
				addCartButton = e.get(i);
				System.out.println(e.get(i).toString());
				break;
			}
		}
		return addCartButton;
	}

	private WebElement deleteProductButtonCart() {
		WebElement deleteButton = null;
		List<WebElement> e = driver.findElements(By.xpath("//span[contains(@class,'remove_link')]"));
		for (WebElement ele : e) {
			if (ele.isDisplayed()) {
				deleteButton = ele;
				break;
			}
		}
		return deleteButton;
	}

	public void deleteFirstProductinCart() {
		clickButton(driver, deleteProductButtonCart());
	}

	/***
	 * click on Update Button To Update Delivery Address
	 */
	public void clickUpdateDeliveryAddressButton() {
		clickButton(driver, updateDeliveryAddressButton);
	}

	/***
	 * click on Update Button to Update Billing Address
	 */
	public void clickUpdateBillingAddress() {
		clickButton(driver, updateBillingAddressButton);
	}
	


}
