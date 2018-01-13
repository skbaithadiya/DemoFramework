package com.AutoMaven.HomePageElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutoMaven.Base.BasePage;

public class CreateAnAccount extends BasePage {

	@FindBy(xpath = "//*[@id='account-creation_form']/div[1]/h3")
	WebElement headerPersonalDetail;

	@FindBy(className = "page-subheading")
	WebElement formTitle;

	@FindBy(id = "id_gender1")
	WebElement titleMr;

	@FindBy(id = "id_gender2")
	WebElement titleMrs;

	@FindBy(id = "customer_firstname")
	WebElement firstNameTextBox;

	@FindBy(id = "customer_lastname")
	WebElement lastNameTextBox;

	@FindBy(id = "email")
	WebElement emailTextBox;

	@FindBy(id = "passwd")
	WebElement passwordTextBox;

	@FindBy(id = "days")
	WebElement dayDropDown;

	@FindBy(id = "months")
	WebElement monthDropDown;

	@FindBy(id = "years")
	WebElement yearDropDown;

	@FindBy(name = "newsletter")
	WebElement newsLetterCheckBox;

	@FindBy(name = "optin")
	WebElement offerCheckBox;

	// --- Address field objects ---//
	@FindBy(xpath = "//*[@id='account-creation_form']/div[2]/h3")
	WebElement headerAddressDetail;

	@FindBy(id = "firstname")
	WebElement aFirstNameTextBox;

	@FindBy(id = "lastname")
	WebElement aLastNameTextBox;

	@FindBy(id = "company")
	WebElement companyNameTextBox;

	@FindBy(id = "address1")
	WebElement addressLine1TextBox;

	@FindBy(id = "address2")
	WebElement addressLine2TextBox;

	@FindBy(id = "city")
	WebElement cityTextBox;

	@FindBy(name = "id_state")
	WebElement stateDropDown;

	@FindBy(name = "postcode")
	WebElement postalCodeTextBox;

	@FindBy(name = "id_country")
	WebElement countryDropDown;

	@FindBy(name = "other")
	WebElement additionalDetailsTextBox;

	@FindBy(name = "phone")
	WebElement homePhoneTextBox;

	@FindBy(name = "phone_mobile")
	WebElement mobilePhoneTextBox;

	@FindBy(name = "alias")
	WebElement aliasAddressTextBox;

	// --- Submit button elements to create new account ---//
	@FindBy(id = "submitAccount")
	WebElement submitButton;

	@FindBy(id = "submitAddress")
	private WebElement saveButton;

	@FindBy(id = "id_address_delivery")
	private WebElement chooseADeliveryAddressDropdown;

	@FindBy(id = "addressesAreEquals")
	private WebElement chooseDeliveryAddressCheckbox;

	@FindBy(xpath = "//p[@class='address_add submit']/a/span[contains(text(),'Add a new address')]")
	private WebElement addANewAddressButton;

	WebDriver driver;

	public CreateAnAccount(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void selectTitleOfUser(String title) {
		if (title == "Mr") {
			if (!titleMr.isSelected()) {
				titleMr.click();
			} else if (title == "Mrs") {
				if (!titleMrs.isSelected()) {
					titleMrs.click();
				}
			}
		}
	}

	public WebElement getElementById(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}

	/***
	 * This method will be use to create New Account
	 * @param title
	 * @param firstName
	 * @param lastName
	 * @param emailId
	 * @param password
	 * @param day
	 * @param month
	 * @param year
	 * @param needNewsLetter
	 * @param needSpecialOffers
	 * @param afirstName
	 * @param alastName
	 * @param companyName
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param country
	 * @param additionalInfo
	 * @param homePhone
	 * @param mobile
	 * @param aliasAddress
	 * @throws InterruptedException
	 */
	public void createNewAccount(String title, String firstName, String lastName, String emailId, String password,
			String day, String month, String year, String needNewsLetter, String needSpecialOffers, String afirstName,
			String alastName, String companyName, String address1, String address2, String city, String state,
			String postalCode, String country, String additionalInfo, String homePhone, String mobile,
			String aliasAddress) throws InterruptedException {
		selectTitleOfUser(title);
		inputIntoTextbox(driver, firstNameTextBox, firstName, 1);
		inputIntoTextbox(driver, lastNameTextBox, lastName, 1);
		inputIntoTextbox(driver, emailTextBox, emailId, 1);
		inputIntoTextbox(driver, passwordTextBox, password, 1);
		selectByValueFromDropDownBox(driver, dayDropDown, day);
		selectByValueFromDropDownBox(driver, monthDropDown, month);
		selectByValueFromDropDownBox(driver, yearDropDown, year);
		selectCheckBox(newsLetterCheckBox, needNewsLetter);
		selectCheckBox(offerCheckBox, needSpecialOffers);

		inputIntoTextbox(driver, aFirstNameTextBox, afirstName, 1);
		inputIntoTextbox(driver, aLastNameTextBox, alastName, 1);
		inputIntoTextbox(driver, companyNameTextBox, companyName, 1);
		inputIntoTextbox(driver, addressLine1TextBox, address1, 1);
		inputIntoTextbox(driver, addressLine2TextBox, address2, 1);
		inputIntoTextbox(driver, cityTextBox, city, 1);
		selectByVisibleTextFromDropDownBox(driver, stateDropDown, state);
		inputIntoTextbox(driver, postalCodeTextBox, postalCode, 1);
		selectByVisibleTextFromDropDownBox(driver, countryDropDown, country);
		inputIntoTextbox(driver, additionalDetailsTextBox, additionalInfo, 1);
		inputIntoTextbox(driver, homePhoneTextBox, homePhone, 1);
		inputIntoTextbox(driver, mobilePhoneTextBox, mobile, 1);
		inputIntoTextbox(driver, aliasAddressTextBox, aliasAddress, 1);
		clickButton(driver, submitButton);

	}

	/***
	 * this method will be use during Making order if User wants to update Delivery address
	 * @param afirstName
	 * @param alastName
	 * @param companyName
	 * @param address1
	 * @param address2
	 * @param city
	 * @param state
	 * @param postalCode
	 * @param country
	 * @param additionalInfo
	 * @param homePhone
	 * @param mobile
	 * @param aliasAddress
	 * @throws InterruptedException
	 */
	public void updateDeliveryAddress(String afirstName, String alastName, String companyName, String address1,
			String address2, String city, String state, String postalCode, String country, String additionalInfo,
			String homePhone, String mobile, String aliasAddress) throws InterruptedException {

		inputIntoTextbox(driver, aFirstNameTextBox, afirstName, 1);
		inputIntoTextbox(driver, aLastNameTextBox, alastName, 1);
		inputIntoTextbox(driver, companyNameTextBox, companyName, 1);
		inputIntoTextbox(driver, addressLine1TextBox, address1, 1);
		inputIntoTextbox(driver, addressLine2TextBox, address2, 1);
		inputIntoTextbox(driver, cityTextBox, city, 1);
		selectByVisibleTextFromDropDownBox(driver, stateDropDown, state);
		inputIntoTextbox(driver, postalCodeTextBox, postalCode, 1);
		selectByVisibleTextFromDropDownBox(driver, countryDropDown, country);
		inputIntoTextbox(driver, additionalDetailsTextBox, additionalInfo, 1);
		inputIntoTextbox(driver, homePhoneTextBox, homePhone, 1);
		inputIntoTextbox(driver, mobilePhoneTextBox, mobile, 1);
		inputIntoTextbox(driver, aliasAddressTextBox, aliasAddress, 1);
		clickButton(driver, saveButton);

	}

	public void chooseADeliveryAddress(String aliasAddress) {
		selectByVisibleTextFromDropDownBox(driver, chooseADeliveryAddressDropdown, aliasAddress);
	}

	public void chooseDeliveryAddressCheckbox() {
		selectCheckBox(chooseDeliveryAddressCheckbox, "yes");
	}

	public void clickAddANewAddressbutton() {
		clickButton(driver, addANewAddressButton);
	}
}
