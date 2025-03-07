package com.Utils.actions;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import com.Utils.reports.Log;

public class UIActions {
	
	private WebDriver driver;
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
	public void clickElement(By locator, String message, int seconds) {
        try {
            WebElement element = waitForElement(locator, seconds);
            Log.message("Clicked on element: " + message);
            element.click();
        } catch (Exception e) {
        	e.printStackTrace();
            throw e;
        }
    }
	
	public void hardClick(By locator, String message, int seconds) {
		try {
			WebElement element = waitForElement(locator, seconds);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();",element);
			Log.message("Clicked on element: " + message);
		}catch(Exception e) {
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
	public String getText(By locator, String message, int seconds) {
		try {
			WebElement element = waitForElement(locator, seconds);
			Log.message("Retrieved text for the element: " + message);
			return element.getText();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to send the text
	 * @param locator
	 * @param inputText
	 * @param message
	 * @param seconds
	 */
	public void sendText(By locator, String inputText, String message, int seconds) {
		try {
			WebElement element = waitForElement(locator, seconds);
			element.clear();
			element.sendKeys(inputText);
			Log.message("Entered the text: "+ message);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to select the dropdown by its value
	 * @param locator
	 * @param input
	 * @param message
	 * @param seconds
	 */
	public void selectByValue(By locator, String input, String message, int seconds) {
		try {
			WebElement element = waitForElement(locator, seconds);
			Select dropdown = new Select(element);
			dropdown.selectByVisibleText(input);
			Log.message("Selected the value: " + message);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
