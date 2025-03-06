package com.Pages;

import org.openqa.selenium.WebDriver;

import com.Locators.RegistrationPageLocators;
import com.Utils.actions.UIActions;
import com.Utils.reports.Log;

public class Registration {
	private WebDriver driver;
	private Log log;
	private UIActions UIActions;
	
	/**
	 * constructor
	 * @param _driver
	 */
	public Registration(WebDriver _driver) {
		this.driver = _driver;
		this.UIActions = new UIActions(_driver);
		this.log = new Log();
	}
	
	/**
	 * method to go to page
	 * @param pageUrl
	 */
	public void gotToPage(String pageUrl) {
		try {
			driver.get(pageUrl);
			log.message("Opened the page: " + pageUrl);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to get the page title
	 * @param window
	 * @return
	 */
	public String getPageTitle() {
		try {
			String pageTitle = driver.getTitle();
			log.message("Title of the page: " + pageTitle);
			return pageTitle;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String clickOnButton() {
		try {
			UIActions.clickElement(RegistrationPageLocators.ele_getAnchorLink("login"), 10, "SignUp/Login button");
			return UIActions.getText(RegistrationPageLocators.ele_signUpFormText, 10, "Sign up form text");
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
