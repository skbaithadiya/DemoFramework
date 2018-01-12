package com.AutoMaven.Base;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import com.AutoMaven.ElementControls.WebElementControls;

public class BasePage extends WebElementControls {
	private static final Logger log = Logger.getLogger(BasePage.class.getName());

	/***
	 * Scroll to the Element passed as parameter (Element which needs to be scrolled to must be available on the Page)
	 * @param driver Webdriver instance
	 * @param element Webelement at which need to scroll
	 */
	protected void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		log.info("Scrolling to the Element: [" + element.toString() + "]");

	}

	/***
	 * to put thread on Sleep
	 * @param a integer value in seconds
	 */
	protected void sleepforSeconds(int a) throws InterruptedException {
		Thread.sleep(a * 1000);
	}

	/***
	 * Hover on Element (Element must be visible when use this method)
	 * @param driver Webdriver instance 
	 * @param element Webelement which need to be hover on
	 */
	protected void hoverElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
		log.info("Hover on Element: [" + element.toString() + "]");
	}

}
