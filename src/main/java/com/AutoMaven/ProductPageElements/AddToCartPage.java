package com.AutoMaven.ProductPageElements;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AutoMaven.Base.BasePage;

public class AddToCartPage extends BasePage {

	@FindBy(xpath = "//*[@id='layer_cart']/div[1]/div[1]/h2")
	WebElement successMessageCart;

	@FindBy(xpath = "//span[contains(@title,'Close window')]")
	WebElement closeAddedToCartBox;

	@FindBy(xpath = "//span[contains(@class,'block_shipping')]")
	private WebElement shippingCostCart;

	@FindBy(xpath = "//div/div/span[contains(@class,'price cart_block_total ajax_block_cart_total')]")
	private WebElement totalCostCart;

	@FindBy(xpath = "//span[contains(text(),'Check out')]")
	private WebElement checkoutButtonCart;

	@FindBy(xpath = "//p/a/span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckoutButtonSummary;

	@FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckoutButtonAddress;

	@FindBy(name = "message")
	private WebElement inputCommentForOrder;

	@FindBy(id = "cgv")
	private WebElement iAgreeCheckbox;

	@FindBy(xpath = "//button[@name='processCarrier']/span")
	private WebElement proceedToCheckoutButtonShippingPage;

	@FindBy(className = "bankwire")
	private WebElement payByBankWireButton;

	@FindBy(className = "cheque")
	private WebElement payByChequebutton;

	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	private WebElement iConfirmMyOrderButton;

	@FindBy(xpath = "//p/strong[@class='dark']")
	private WebElement confirmationMessage;

	WebDriver driver;
	private static final Logger log = Logger.getLogger(AddToCartPage.class.getName());

	public AddToCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * @author sunilbaithadiya
	 * @version 1.0
	 * @since Oct 17, 2017
	 */

	/***
	 * click on 'Proceed to Checkout' Button on Shopping Summary Page
	 */
	public void clickProceedToCheckoutButtonSummaryPage() {
		clickButton(driver, proceedToCheckoutButtonSummary);
	}

	/***
	 * get Shipping cost from the cart (Need to hover on Cart First)
	 * 
	 * @return String value of the Shipping Cost
	 */
	public String getShippingCostFromCart() {
		String shippingCost = shippingCostCart.getText().trim();
		log.info("Returning value of Shipping Cost["+shippingCost+"] from Cart");
		return shippingCost;
	}

	/***
	 * get Total Cost from the cart (Need to hover on Cart First)
	 * 
	 * @return String value of the Total Cost
	 */
	public String getTotalCostFromCart() {
		String totalCost = totalCostCart.getText().trim();
		log.info("Returning value of Total Cost["+totalCost+"] from Cart");
		return totalCost;
	}

	/***
	 * get the Success Message after adding the product
	 * 
	 * @return returns the Success Message as String
	 */
	public String getSuccessMessage() {
		waitForElementVisible(driver, successMessageCart);
		String sValue = successMessageCart.getText().trim();
		// System.out.println("value is:"+sValue);
		log.info("Getting success message, message is: ["+sValue+"]. Returning the same message");
		return sValue;
	}

	/***
	 * clicking on Close Button of the Add To Cart Detail Box
	 */
	public void closeAddToCartDetailBox() {
		waitForElementVisible(driver, closeAddedToCartBox);
		scrollToElement(driver, closeAddedToCartBox);
		clickButton(driver, closeAddedToCartBox);
	}

	/***
	 * get the Shipping Cost in Cart (Need to Hover on Cart Option first)
	 * 
	 * @return returns Shipping Cost in String
	 */
	public String getShippingCostInCart() {
		String sValue = shippingCostCart.getText().trim();
		log.info("Shipping Cost from Cart: ["+sValue+"]. Returning the same value in String");
		return sValue;
	}

	/***
	 * get the Total Cost in Cart (Need to Hover on Cart Option first)
	 * 
	 * @return returns Shipping Cost in String
	 */
	public String getTotalCostInCart() {
		String sValue = totalCostCart.getText().trim();
		log.info("Total Cost from Cart: ["+sValue+"]. Returning the same value is String");
		return sValue;
	}

	/***
	 * clicking on Checkout button on Cart
	 */
	public void clickCheckoutButtonCart() {
		hoverElement(driver, checkoutButtonCart);
		clickButton(driver, checkoutButtonCart);
	}

	/***
	 * get the Row Number of the product passed in as parameter
	 * 
	 * @param productName
	 *            Name of the product
	 * @return String value of Row Number
	 */
	private int getRowNumberOfProduct(String productName) {
		int rowNumOfProduct = 0;
		List<WebElement> table = driver.findElements(By.xpath("//table[@id='cart_summary']/tbody/tr"));
		int totalRows = table.size();
		// System.out.println("total no of rows are : " + totalRows);
		log.info("Total rows found in: ["+table.toString()+"] are: ["+totalRows+"]");
		for (int i = 1; i <= totalRows; i++) {
			WebElement product_name_Element = driver
					.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + i + "]/td[2]/p/a"));
			String product = product_name_Element.getText().trim().toLowerCase();
			if (product.equalsIgnoreCase(productName)) {
				rowNumOfProduct = i;
				break;
			}
		}
		return rowNumOfProduct;
	}

	/***
	 * check if Product is available in Cart (Need to hover on cart first)
	 * product name should be taken from html tag as it may sometimes differ
	 * because of different font/size. Product Name must be unique or else it
	 * will check the first visible Product if there has multiple products with
	 * the same name.
	 * 
	 * @param productName
	 * @return returns True/False
	 */
	public boolean checkProductNameFromCart(String productName) {
		boolean result = false;
		List<WebElement> tableCart = driver.findElements(By.xpath("//dl[@class='products']/dt"));
		int totalRows = tableCart.size();
		// System.out.println("Total rows inside cart is: " + totalRows);
		for (int i = 1; i <= totalRows; i++) {
			WebElement product = driver.findElement(By.xpath("//dl[@class='products']/dt[" + i + "]/a/img"));
			String productN = product.getAttribute("alt").trim();
			// System.out.println(productN);
			log.info(i+". Product: ["+productN+"] is found");
			if (productN.equalsIgnoreCase(productName)) {
				result = true;
				break;
			}
		}
		return result;
	}

	/***
	 * get Row Number of the Product(need to hover on the cart first). it checks
	 * the product name (product name must be unique for this function to work)
	 * and then returns the row number in that product inside Cart
	 * 
	 * @param productName
	 * @return
	 */
	private int getRowNumberOfProductCart(String productName) {
		int rowNum = 0;
		List<WebElement> tableCart = driver.findElements(By.xpath("//dl[@class='products']/dt"));
		int totalRows = tableCart.size();
		// System.out.println("Total rows inside cart is: " + totalRows);

		for (int i = 1; i <= totalRows; i++) {
			WebElement product = driver.findElement(By.xpath("//dl[@class='products']/dt[" + i + "]/a"));
			String productN = product.getAttribute("title").trim();
			if (productN.equalsIgnoreCase(productName)) {
				rowNum = i;
				// System.out.println("row number of product inside cart (inside
				// loop): "+ rowNum);
				break;
			}
		}
		// System.out.println("row number of product inside cart (outside loop):
		// "+ rowNum);
		return rowNum;
	}

	/***
	 * get Product quantity from the cart (Need to Hover on Cart Option first)
	 * 
	 * @param productName
	 * @return String value of product Quantity
	 */
	public String getProductQuantityFromCart(String productName) {
		int rowNum = getRowNumberOfProductCart(productName);
		WebElement quantity = driver
				.findElement(By.xpath("//dl[@class='products']/dt[" + rowNum + "]/div/div/span/span"));
		return quantity.getText().trim();
	}

	/***
	 * click on Remove Link inside cart of related product (Need to Hover on
	 * Cart Option first)
	 * 
	 * @param productName
	 */
	public void clickRemoveProductFromCart(String productName) {
		int rowNum = getRowNumberOfProductCart(productName);
		WebElement removeLink = driver.findElement(By.xpath("//dl[@class='products']/dt[" + rowNum + "]/span/a"));
		clickButton(driver, removeLink);
	}

	/***
	 * get Product Price from the cart (Need to Hover on Cart Option first)
	 * 
	 * @param productName
	 * @return String value of the Price (with "$" sign), for Ex: $18.5
	 */
	public String getProductPriceFromCart(String productName) {
		int rowNum = getRowNumberOfProductCart(productName);
		WebElement price = driver.findElement(By.xpath("//dl[@class='products']/dt[" + rowNum + "]/div/span"));
		return price.getText().trim();
	}

	/***
	 * get Color Or Size of the product from the cart (Need to Hover on Cart
	 * Option first)
	 * 
	 * @param productName
	 * @param colorOrSize
	 *            : choose "color" or "size" as parameter
	 * @return returns the string value of "color" or "size" depends on the
	 *         parameter given
	 */
	public String getProductColorAndSizeFromCart(String productName, String colorOrSize) {
		int rowNum = getRowNumberOfProductCart(productName);
		WebElement colorAndSize = driver
				.findElement(By.xpath("//dl[@class='products']/dt[" + rowNum + "]/div/div[2]/a"));
		String[] splitColorNSize = colorAndSize.getText().trim().split("[,]");
		String color = splitColorNSize[0];
		String size = splitColorNSize[1];
		String returnValue = null;
		if (colorOrSize.equalsIgnoreCase("color")) {
			returnValue = color;
		} else if (colorOrSize.equalsIgnoreCase("size")) {
			returnValue = size;
		}
		return returnValue;
	}

	// get product Color and Size At Shopping Summary
	private String getProductColorAndSize(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement product = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[2]/small/a"));
		return product.getText();
	}

	/***
	 * get the Product Color and Product Size from the Product
	 * 
	 * @param productName
	 * @param colorOrSize
	 *            : choose "color" or "size" of product
	 * @return returns product color and size as String Value
	 */
	public String getProductColorOrSize(String productName, String colorOrSize) {
		String productColorAndSize = getProductColorAndSize(productName);
		String[] splitValue = productColorAndSize.split("[,]");
		String splitValue1 = splitValue[0];
		//System.out.println("First split Value 1: " + splitValue1);
		String splitValue2 = splitValue[1];
		//System.out.println("First split Value 2: " + splitValue2);

		// Splitting first part for Color
		String[] split = splitValue1.split("[:]");
		String color = split[1];
		//System.out.println("color is: " + color);

		// Splitting second part for Size
		String[] split2 = splitValue2.split("[:]");
		String size = split2[1];
		//System.out.println("Size is: " + size);
		String returnValue = null;
		if (colorOrSize.equalsIgnoreCase("color")) {
			returnValue = color;
		} else if (colorOrSize.equalsIgnoreCase("size")) {
			returnValue = size;
		}
		return returnValue;

	}

	/***
	 * get the Unit Price of the Product passed in as parameter
	 * 
	 * @param productName
	 *            : Name of the Product
	 * @return String value of Unit Price for ex: $16.51
	 */
	public String getUnitPriceOfProduct(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement unitPrice = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[4]/span/span"));
		return unitPrice.getText().trim();
	}

	/***
	 * get Product Discount % of the Unit Price (this will only work when
	 * discount is visible and/Or Product has discount
	 * 
	 * @param productName
	 * @return : discount % rate returns in String
	 */
	public String getDiscount(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement unitPrice = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[4]/span/span[2]"));
		return unitPrice.getText().trim();

	}

	/***
	 * get before discount Unit Price of the Product
	 * 
	 * @param productName
	 * @return : before discount Unit Price returns in String
	 */
	public String beforeDiscountProductPrice(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement unitPrice = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[4]/span/span[3]"));
		return unitPrice.getText().trim();
	}

	/***
	 * get Availability status of the Product passed in as parameter
	 * 
	 * @param productName
	 * @return : String Value of available for ex: "In Stock"
	 */
	public String getAvailabilityOfProduct(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement availability = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[3]/span"));
		return availability.getText().trim();
	}

	/***
	 * change the Quantity of Product passed in as parameter
	 * 
	 * @param productName
	 * @param quantity
	 *            : Quantity of the product to be
	 */
	public void changeQuantityOfProduct(String productName, String quantity) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement availability = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[5]/input[2]"));
		availability.clear();
		availability.sendKeys(quantity);
	}

	/***
	 * click on Decrease Quantity Button
	 * 
	 * @param productName
	 */
	public void clickDecreaseQuantityOfProduct(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement quantityMinusButton = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[5]/div/a[1]"));
		quantityMinusButton.click();
	}

	/***
	 * click on Increase Quantity Button
	 * 
	 * @param productName
	 */
	public void clickIncreaseQuantityOfProduct(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement quantityPlusButton = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[5]/div/a[2]"));
		quantityPlusButton.click();
	}

	/***
	 * get the Sub-Total Amount of the product
	 * 
	 * @param productName
	 * @return Sub-Total price of the product in String
	 */
	public String getTotalPriceOfProduct(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement subTotal = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[6]/span"));
		return subTotal.getText().trim();
	}

	/***
	 * click on the Delete Product Button of the Product
	 * 
	 * @param productName
	 */
	public void clickDeleteProductButton(String productName) {
		int rowNum = getRowNumberOfProduct(productName);
		WebElement deleteButton = driver
				.findElement(By.xpath("//table[@id='cart_summary']/tbody/tr[" + rowNum + "]/td[7]/div/a"));
		deleteButton.click();
	}

	/***
	 * click on Proceed to Checkout Button at Address Page
	 */
	public void clickProceedToCheckoutAtAddressPage() {
		clickButton(driver, proceedToCheckoutButtonAddress);
	}

	/***
	 * input comment for Order at the Address Page
	 * 
	 * @param comment
	 */
	public void inputCommentForOrder(String comment) {
		inputIntoTextbox(driver, inputCommentForOrder, comment);
	}

	/***
	 * select 'i agree to the terms of service..' checkbox
	 */
	public void selectIAgreeCheckbox() {
		selectCheckBox(iAgreeCheckbox, "yes");
	}

	/***
	 * click on proceed to checkout button on Shipping Page
	 */
	public void clickProceedToCheckoutButtonShippingPage() {
		clickButton(driver, proceedToCheckoutButtonShippingPage);
	}

	/***
	 * click on payment mode at Payment Page
	 * 
	 * @param option:
	 *            choose option between 'bankwire' Or 'cheque'
	 */
	public void selectPaymentMode(String option) {
		if (option.equalsIgnoreCase("bankwire")) {
			clickButton(driver, payByBankWireButton);
		} else if (option.equalsIgnoreCase("cheque")) {
			clickButton(driver, payByChequebutton);
		}
	}

	/***
	 * click on I confirm my order Button
	 */
	public void clickIConfirmMyOrderButton() {
		clickButton(driver, iConfirmMyOrderButton);
	}

	/***
	 * get the confirmation message after completing the order
	 * 
	 * @return : returns the string message on completion
	 */
	public String getConfirmationMessageOnCompletion() {
		String confirmMessage = confirmationMessage.getText().trim();
		log.info("Confirmation message on the completion is: ["+confirmMessage+"]");
		return confirmMessage;
	}
}
