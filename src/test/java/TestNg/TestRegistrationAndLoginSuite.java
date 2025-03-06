package TestNg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.Pages.Registration;
import com.Utils.configs.Core;
import com.Utils.reports.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestRegistrationAndLoginSuite {
	private WebDriver driver;
	private Log log;
	private String browser;
	
	@BeforeSuite
	public void setup() {
		try {
			WebDriverManager.chromedriver().setup();
			browser = Core.readProperty("browserName");
			log = new Log();
			Log.initReport("UserRegistrationAndLogin", Core.readProperty("tester"), Core.readProperty("environment")); 
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
			Registration reg = new Registration(driver);
			reg.gotToPage(Core.readProperty("HomePageUrl"));
			log.assertThat(reg.getPageTitle(),"Automation Exercise");
			log.assertThat(reg.clickOnButton(),"New User Signup!");
			log.endTest("Register User");
		}catch(Exception e) {
			log.fail(e.getMessage());
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
