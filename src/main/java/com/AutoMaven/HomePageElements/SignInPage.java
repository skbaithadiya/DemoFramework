package com.AutoMaven.HomePageElements;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.AutoMaven.Base.BasePage;

/***
 * this class 
 * @author sunilkb.ym
 *
 */
public class SignInPage extends BasePage {

	@FindBy(className = "page-heading")
	WebElement pageHeading;

	@FindBy(id = "email")
	WebElement emailAddressTextBox;

	@FindBy(id = "passwd")
	WebElement passwordTextBox;

	@FindBy(xpath = "//*[@id='SubmitLogin']/span")
	WebElement submitButton;

	@FindBy(xpath = "//*[@id='center_column']/div[1]/ol/li")
	WebElement errorTextLogin;

	@FindBy(id = "email_create")
	WebElement emailCreate;

	@FindBy(id = "SubmitCreate")
	WebElement submitCreateBtn;

	@FindBy(className = "logout")
	WebElement signOutButton;

	WebDriver driver;
	public static final Logger log = Logger.getLogger(SignInPage.class.getName());

	public SignInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/***
	 * hhis method ius for login
	 * @param email
	 * @param password
	 * @throws InterruptedException
	 */
	public void doLogin(String email, String password) throws InterruptedException {
		inputIntoTextbox(driver, emailAddressTextBox, email);
	//	log.info("Entered email: " + email + " and Object is: " + emailAddressTextBox.toString());
//		inputIntoTextbox(driver, passwordTextBox, password);
		passwordTextBox.clear();
		passwordTextBox.sendKeys(password);
		log.info("Entered password(in HashForm): [" + password.hashCode() + "] into Password Textbox: " + passwordTextBox.toString());
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", submitButton);
		// submitButton.click();
		log.info("clicked on the [Sign In] button and Object is: " + submitButton.toString());
	}

	/***
	 * this method will return Login Error Text 
	 * @return String Login Error
	 */
	public String getLoginErrorText() {
		log.info("Error message is: " + errorTextLogin.getText());
		action.highLight(errorTextLogin, driver);
		return errorTextLogin.getText();
		
	}

	/***
	 * this will navigate to Create Account Page. 
	 * Create Account Page cannot will navigated without verifying valid email address
	 * @param emailToBeCreated email id which will be use later to create new account
	 */
	public void goToCreateAccountPage(String emailToBeCreated) {
		emailCreate.clear();
		emailCreate.sendKeys(emailToBeCreated);
		submitCreateBtn.click();
	}

	/***
	 * this will be used in Assertion after Login Successfully
	 * it will check if Sign Out Button is present or not
	 * @return true/false
	 */
	public boolean isSignOutButtonPresent() {
		if (signOutButton.isDisplayed() || signOutButton.isEnabled()) {
			action.highLight(signOutButton, driver);
			return true;
		} else {
			return false;
		}
	}
}
