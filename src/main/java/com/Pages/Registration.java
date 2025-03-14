package com.Pages;

import org.openqa.selenium.WebDriver;

import com.Locators.RegistrationPageLocators;
import com.Utils.Actions.UIActions;
import com.Utils.Data.TestDataReader;
import com.Utils.Reports.Log;

public class Registration {
	private WebDriver driver;
	private UIActions UIActions;
	private String email;
	private String name;
	private String password;
	
	/**
	 * constructor
	 * @param _driver
	 */
	public Registration(WebDriver _driver) {
		this.driver = _driver;
		this.UIActions = new UIActions(_driver);
	}
	
	/**
	 * method to go to page
	 * @param pageUrl
	 */
	public void gotToPage(String pageUrl) {
		try {
			driver.get(pageUrl);
			Log.message("Opened the page: " + pageUrl);
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
			Log.message("Title of the page: " + pageTitle);
			return pageTitle;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to click on button
	 * @return
	 */
	public String clickOnSignUpLink() {
		try {
			UIActions.clickElement(RegistrationPageLocators.ele_getAnchorLink("login"), "SignUp/Login button",10);
			return UIActions.getText(RegistrationPageLocators.ele_signUpFormText, "Sign up form text",10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to enter the email and name
	 */
	public String nameAndEmailAddress() {
		try {
			name = TestDataReader.generateRandomText(10);
			email = name + "@gmail.com";
			
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("signup-name"), name, "Name", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("signup-email"), email, "Email", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_button("signup-button"), "Signup Button", 10);
			return UIActions.getText(RegistrationPageLocators.ele_InformationHeader, "Information Form text", 10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String registerWithExisitngEmail(String email) {
		try {
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("signup-name"), "asdfasgaad", "Name", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("signup-email"), email, "Email", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_button("signup-button"), "Signup Button", 10);
			return UIActions.getText(RegistrationPageLocators.ele_errorMessagecheck, "Error Message", 10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to fill the details
	 */
	public void fillDetailedInformation() {
		try {
			password = TestDataReader.generateRandomText(10);
			String firstName = name.substring(0,name.length()/2);
			String lastName = name.substring(name.length()/2);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("password"), password, "Password", 10);
			UIActions.selectByValue(RegistrationPageLocators.ele_Select("days"), "21", "Day", 10);
			UIActions.selectByValue(RegistrationPageLocators.ele_Select("months"), "January", "Month", 10);
			UIActions.selectByValue(RegistrationPageLocators.ele_Select("years"), "1998", "Year", 10);
			UIActions.hardClick(RegistrationPageLocators.ele_checkbox("newsletter"), "Sign up for news letter", 10);
			UIActions.hardClick(RegistrationPageLocators.ele_checkbox("optin"), "Recieve Special Offers", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("first_name"), firstName, "First Name", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("last_name"), lastName, "Last Name", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("company"), "Anonymous Company", "Company", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("address"), TestDataReader.generateRandomText(10), "Address", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("address2"), TestDataReader.generateRandomText(10), "Address 2", 10);
			UIActions.selectByValue(RegistrationPageLocators.ele_Select("country"), "India", "Country", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("state"), TestDataReader.generateRandomText(10), "State", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("city"), TestDataReader.generateRandomText(10), "City", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("zipcode"), "550011", "Zip Code", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("mobile_number"), "9570171080", "Phone Number", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_createAccount, "Create Account", 10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	/**
	 * method to validate account creation
	 * @return
	 */
	public String validateAccountCreation() {
		try {
			String text = UIActions.getText(RegistrationPageLocators.ele_getTextFromHtmlEement("account-created"), "Account Created", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_continue, "Continue", 10);
			return text;
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to check logged in or not
	 * @return
	 */
	public String checkLoggedIn() {
		try {
			return  UIActions.getText(RegistrationPageLocators.ele_loggedInUser, "Logged In User", 10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to check the user is logged out or not
	 * @return
	 */
	public String logoutUser(String input) {
		try {
			UIActions.clickElement(RegistrationPageLocators.ele_getAnchorLink(input), "Logout", 10);
			return driver.getTitle();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * method to click on login
	 * @param email
	 * @param password
	 */
	public void clickOnLogin(String email, String password) {
		try {
			
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("login-email"), email, "Email", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("login-password"), password, "Email", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_button("login-button"), password, 10);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clickOnLogin() {
		try {
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("login-email"), email, "Email", 10);
			UIActions.sendText(RegistrationPageLocators.ele_inputElement("login-password"), password, "Email", 10);
			UIActions.clickElement(RegistrationPageLocators.ele_button("login-button"), password, 10);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * method to check incorrect login
	 * @return
	 */
	public String checkIncorrectLogin() {
		try {
			return UIActions.getText(RegistrationPageLocators.ele_errorMessagecheck, "Error Message",10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public String checkDelete() {
		try {
			UIActions.clickElement(RegistrationPageLocators.ele_getAnchorLink("delete_account"), "SignUp/Login button",10);
			return UIActions.getText(RegistrationPageLocators.ele_getTextFromHtmlEement("account-deleted"), "Account Created", 10);
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
}
