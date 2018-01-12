package com.AutoMaven.CustomListener;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import com.AutoMaven.Base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener {
	public static final Logger log = Logger.getLogger(WebEventListener.class.getName());

	public void beforeAlertAccept(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterAlertAccept(WebDriver driver) {
		log.info("Alert will be accepted now");
		
	}

	public void afterAlertDismiss(WebDriver driver) {
		log.info("Alert is dismissed");
		
	}

	public void beforeAlertDismiss(WebDriver driver) {
		log.info("Alert will be dismissed now");
		
	}

	public void beforeNavigateTo(String url, WebDriver driver) {
		log.info("Navigating to URL ["+url+"]");
		
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		log.info("Navigatied already to URL ["+url+"]");
		
	}

	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterNavigateRefresh(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		log.info("Clicking on Element ["+element+"]");
		
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		log.info("Clicked on Element ["+element+"]");
		
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		// TODO Auto-generated method stub
		
	}

	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}

	public void onException(Throwable throwable, WebDriver driver) {
		log.info("Exception Occurrred ["+throwable.getStackTrace()+"]");
		
	}

}
