package com.Utils.actions;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import com.Utils.reports.Log;

public class UIActions {
	
	private WebDriver driver;
	private static final Logger logger = LogManager.getLogger(Log.class);
	public UIActions(WebDriver _driver) {
		this.driver = _driver;
	}
	
	/**
	 * adding fluent wait to the element
	 * @param seconds
	 * @return
	 */
	private FluentWait<WebDriver> getFluentWait(int seconds) {
		try {
			return new FluentWait<>(driver)
						.withTimeout(Duration.ofSeconds(seconds))
						.pollingEvery(Duration.ofMillis(500)) // Check every 500ms
			            .ignoring(NoSuchElementException.class)
			            .ignoring(ElementClickInterceptedException.class)
			            .ignoring(StaleElementReferenceException.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * waiting for the web element
	 * @param locator
	 * @param seconds
	 * @return
	 */
	private WebElement waitForElement(By locator, int seconds) {
        return getFluentWait(seconds).until(driver -> driver.findElement(locator));
    }
	
	/**
	 * method to click on element
	 * @param locator
	 * @param Seconds
	 * @param message
	 */
	public void clickElement(By locator, int seconds, String message) {
        try {
            WebElement element = waitForElement(locator, seconds);
            logger.info("Clicked on element: " + message);
            element.click();
        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        }
    }
	
	/**
	 * method to get the text from the tag
	 * @param locator
	 * @param seconds
	 * @param message
	 */
	public String getText(By locator, int seconds, String message) {
		try {
			WebElement element = waitForElement(locator, seconds);
			logger.info("Retrieved text for the element: " + message);
			return element.getText();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
