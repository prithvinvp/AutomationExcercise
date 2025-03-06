package TestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Utils.Core;
import com.Utils.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRegistrationAndLoginSuite {
	private WebDriver driver;
	private Core core;
	private Log log;
	private String browser;
	
	@BeforeSuite
	public void setup() {
		try {
			WebDriverManager.chromedriver().setup();
			core = new Core();
			browser = core.readProperty("browserName");
			log = new Log();
			Log.initReport("UserRegistrationAndLogin", core.readProperty("tester"), core.readProperty("environment")); 
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
			log.startTest("Register User");
			driver.get(core.readProperty("HomePageUrl"));
			log.testCaseInfo("Opened the URL");
			log.endTest("Register User");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@AfterSuite
	public void tearDown() {
		try {
			driver.quit();
			Log.generateReport();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
