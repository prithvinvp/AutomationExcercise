package com.Locators;

import org.openqa.selenium.By;

public class RegistrationPageLocators {
	
	public static By ele_getAnchorLink(String elementName) {
		return By.xpath(String.format("//a[@href='/%s']",elementName));
	}
	
	public static By ele_signUpFormText = By.xpath("//div[@class='signup-form']/h2");
}
