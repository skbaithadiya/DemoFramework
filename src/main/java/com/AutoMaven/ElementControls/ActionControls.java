package com.AutoMaven.ElementControls;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionControls {
	
	/***
	 * to scroll to any element present in current page (Webelement must be present in the Page to scroll)
	 * @param driver WebDriver instance
	 * @param element WebElement
	 */
	protected void scrollToElement(WebDriver driver, WebElement element) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	/***
	 * this method is to highlight the Elements during testcase execution
	 * @param element WebElement to highlight
	 * @param driver WebDriver instance
	 */
	public void highLight(WebElement element, WebDriver driver){
        for (int i = 0; i <2; i++) 
        {
            try {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border: 4px solid red;");
                Thread.sleep(100);
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        }

    }
}
