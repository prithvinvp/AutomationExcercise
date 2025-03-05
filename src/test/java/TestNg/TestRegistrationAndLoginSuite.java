package TestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Utils.Core;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRegistrationAndLoginSuite {
	private WebDriver driver;
	private Core core;
	private String browser;
	
	@BeforeClass
	public void setup() {
		try {
			WebDriverManager.chromedriver().setup();
			core = new Core();
			browser = core.readProperty("browserName");
			
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
			}
			
			driver.manage().window().maximize();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
	public void registerUser() {
		try {
			driver.get(core.readProperty("HomePageUrl"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void tearDown() {
		try {
			driver.quit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
