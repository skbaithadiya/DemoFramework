package com.AutoMaven.HomePageElements;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.AutoMaven.Base.BasePage;

public class ContactUsPage extends BasePage{

	@FindBy(xpath = "//*[@id='center_column']/h1")
	WebElement pageTitle;

	@FindBy(id = "id_contact")
	WebElement subjectHeadingDropDownBox;

	@FindBy(id = "email")
	WebElement emailAddressTextbox;

	@FindBy(id = "id_order")
	WebElement orderReferenceTextbox;

	@FindBy(id = "fileUpload")
	WebElement chooseFileButton;

	@FindBy(id = "message")
	WebElement messageTextbox;

	@FindBy(id = "submitMessage")
	WebElement sendButton;

	@FindBy(xpath = "//*[@id='center_column']/p")
	WebElement successMesssage;

	WebDriver driver;
	public static final Logger log = Logger.getLogger(ContactUsPage.class.getName());

	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/***
	 * get page title of the Contact Us Page
	 * @return
	 */
	public String getPageTitle() {
		String sValue = pageTitle.getText().toLowerCase().trim();
		return sValue;
	}

	/***
	 * get the success message after sending any message to be Customer Service
	 * @return
	 */
	public String getSuccessMessage() {
		String sValue=successMesssage.getText().toLowerCase();
		log.info("returning success message as: "+sValue);
		return sValue;
	}

	public void sendMessageToCustomerServiceWithUpload(String subjectHeading, String emailAddress,
			String orderReference, String message, String attachmentFullPath) throws InterruptedException {
		selectByVisibleTextFromDropDownBox(driver, subjectHeadingDropDownBox, subjectHeading);
		log.info("Subject Heading is selected as: "+subjectHeading);
		inputIntoTextbox(driver, emailAddressTextbox, emailAddress);
		log.info("Email Address is typed as: "+emailAddress);
		inputIntoTextbox(driver, orderReferenceTextbox, orderReference);
		log.info("Order Reference is input as: "+orderReference);
		inputIntoTextbox(driver, messageTextbox, message);
		log.info("Message is input as: ["+message+"]");
		//choose a file to upload
		chooseFileButton.sendKeys(attachmentFullPath);
		log.info("Attachment added and its path is: ["+attachmentFullPath+"]");
		//Thread.sleep(3000);
		scrollToElement(driver, sendButton);
		sendButton.click();
		log.info("Send Button is clicked and object is: ["+sendButton+"]");

	}
}
