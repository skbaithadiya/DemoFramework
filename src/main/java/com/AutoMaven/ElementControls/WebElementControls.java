package com.AutoMaven.ElementControls;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementControls {
	private static final Logger log = Logger.getLogger(WebElementControls.class.getName());

	protected ActionControls action = new ActionControls();

	/***
	 * select the checkbox (whether it is checked or not)
	 * 
	 * @param element
	 *            WebElement of the Checkbox
	 * @param value
	 *            yes/no (for example you need to make sure checkbox is
	 *            unchecked then pass "no" as parameter
	 */
	protected void selectCheckBox(WebElement element, String value) {
		if (value == "yes") {
			if (element.isDisplayed() || element.isEnabled()) {
				if (!element.isSelected()) {
					element.click();
					log.info("Element [" + element.toString() + "] is Selected");
				}
			}
		} else if (value == "no") {
			if (element.isDisplayed() || element.isEnabled()) {
				if (element.isSelected()) {
					element.click();
					log.info("Element [" + element.toString() + "] is Selected");
				}
			}
		}
	}

	/***
	 * Send keys to the textbox (with time to stop execution)
	 * 
	 * @param element
	 *            WebElement
	 * @param value
	 *            value String value need to input
	 * @param timer
	 *            no of seconds to stop execution (for ex. 5, will stop
	 *            execution for 5 seconds)
	 * @throws InterruptedException
	 */
	protected void inputIntoTextbox(WebDriver driver, WebElement element, String value, int timer)
			throws InterruptedException {
		try {
			if (element.isDisplayed() || element.isEnabled()) {
				action.highLight(element, driver);
				element.clear();
				element.sendKeys(value);
				log.info("String [" + value + "] has been typed into [" + element.toString() + "]");
				log.info("Thread is going to sleep for [" + timer + "]");
				sleepforSeconds(1);
			} else {
				// System.out.println("element '" + element + "' not found!");
				log.error("Element: [" + element.toString() + "] is not found!");

			}
		} catch (InterruptedException e) {
			log.error("Exception occurred during putting thread on Sleep!");

		}
	}

	/***
	 * Send keys to the textbox
	 * 
	 * @param element
	 *            WebElement
	 * @param value
	 *            String value need to input
	 */
	protected void inputIntoTextbox(WebDriver driver, WebElement element, String value) {
		if (element.isDisplayed() || element.isEnabled()) {
			element.clear();
			action.highLight(element, driver);
			element.sendKeys(value);
			action.highLight(element, driver);
			log.info("String [" + value + "] has been typed into [" + element.toString() + "]");
		} else {
			System.out.println("element '" + element + "' not found!");
			log.error("Element: [" + element.toString() + "] is not found!");
		}
	}

	/***
	 * to stop execution for no of seconds.
	 * 
	 * @param time
	 *            no of seconds to stop execution (for ex. 5, will stop
	 *            execution for 5 seconds)
	 * @throws InterruptedException
	 */
	protected void sleepforSeconds(int time) throws InterruptedException {
		log.info("Thread is going to sleep for [" + time + "] second(s)");
		Thread.sleep(time * 1000);

	}

	protected void selectByValueFromDropDownBox(WebDriver driver, WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
		action.highLight(element, driver);
		log.info("Selected the [" + element.toString() + "] by value: [" + value + "]");
	}

	protected void selectByVisibleTextFromDropDownBox(WebDriver driver, WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(value);
		action.highLight(element, driver);
		log.info("Selected the [" + element.toString() + "] by visible text: [" + value + "]");
	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Waiting for [" + element.toString() + "] to be visible");
		action.highLight(element, driver);
		// return element;
	}

	protected void checkElementIsClickable(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Waiting for [" + element.toString() + "] to be Clickable");
		action.highLight(element, driver);
	}

	/***
	 * click on Button
	 * 
	 * @param element
	 *            WebElement
	 */
	protected void clickButton(WebDriver driver, WebElement element) {
		if (element.isDisplayed()) {
			action.highLight(element, driver);
			element.click();
			log.info("Clicked on [" + element.toString() + "]");
		} else {
			System.out.println("element is not displayed!");
			log.error("[" + element.toString() + "] is not found!");
		}
	}

}
