package com.Locators;

import org.openqa.selenium.By;

public class RegistrationPageLocators {
	
	public static By ele_getAnchorLink(String elementName) {
		return By.xpath(String.format("//a[@href='/%s']",elementName));
	}
	
	public static By ele_inputElement(String elementName) {
		return By.xpath(String.format("//input[@data-qa='%s']", elementName));
	}
	
	public static By ele_Select(String elementName) {
		return By.xpath(String.format("//select[@data-qa='%s']", elementName));
	}
	
	public static By ele_checkbox(String elementName) {
		return By.id(elementName);
	}
	
	public static By ele_button(String elementName) {
		return By.xpath(String.format("//button[@data-qa='%s']",elementName));
	}
	
	public static By ele_getTextFromHtmlEement(String elementName) {
		return By.xpath(String.format("//h2[@data-qa='%s']/b", elementName));
	}
	
	public static By ele_signUpFormText = By.xpath("//div[@class='signup-form']/h2");
	public static By ele_InformationHeader = By.xpath("//div[@class='login-form']/h2/b");
	public static By ele_createAccount = By.xpath("//button[@data-qa='create-account']");
	public static By ele_continue = By.xpath("//a[@data-qa='continue-button']");
	public static By ele_loggedInUser = By.xpath("//i[contains(@class,'fa-user')]/parent::a");
	public static By ele_errorMessagecheck = By.xpath("//p[@style]");
}
