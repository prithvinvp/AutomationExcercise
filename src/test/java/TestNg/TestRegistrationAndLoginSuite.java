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
	private String browser;
	private Registration reg ;
	
	@BeforeSuite
	public void setup() {
		try {
			WebDriverManager.chromedriver().setup();
			browser = Core.readProperty("browserName");
			Log.initReport("UserRegistrationAndLogin", Core.readProperty("tester"), Core.readProperty("environment")); 
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}else if(browser.equalsIgnoreCase("safari")) {
				driver = new SafariDriver();
			}
			driver.manage().window().maximize();
			reg = new Registration(driver);
			reg.gotToPage(Core.readProperty("HomePageUrl"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(priority=1)
 	public void registerUser() {
		try {
			Log.startTest("Register User");
			Log.assertThat(reg.getPageTitle(),"Automation Exercise");
			Log.assertThat(reg.clickOnSignUpLink(),"New User Signup!");
			Log.assertThat(reg.nameAndEmailAddress(),"Enter Account Information");
			
			reg.fillDetailedInformation();
			Log.assertThat(reg.validateAccountCreation(), "Account Created!");
			Log.assertThat(reg.checkLoggedIn(), "Logged in as");
			Log.endTest("Register User");
		}catch(Exception e) {
			Log.fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test(priority=2)
	public void logoutUser() {
		try {
			Log.startTest("Logout User");
			Log.assertThat(reg.logoutUser("logout"), "Automation Exercise");
			Log.endTest("Logout User");
		}catch(Exception e) {
			e.printStackTrace();
			Log.fail(e.getMessage());
		}
	}
	
	@Test(priority=3)
	public void registerUserwithExistingEmail() {
		try {
			Log.startTest("Register User with Existing Email");
			Log.assertThat(reg.clickOnSignUpLink(),"New User Signup!");
			Log.assertThat(reg.registerWithExisitngEmail("test@tes.com"), "Email Address already exist!");
			Log.endTest("Register user with Exisiting Email");
		}catch(Exception e) {
			e.printStackTrace();
			Log.fail(e.getMessage());
		}
	}
	
	@Test(priority=4)
	public void loginWithIncorrectUserAndPassword() {
		try {
			Log.startTest("Login with Incorrect User and Password");
			reg.clickOnLogin("1510585@gm194.com", "dsnfkaskfjhahf");
			Log.assertThat(reg.checkIncorrectLogin(), "Your email or password is incorrect!");
			Log.endTest("Login with Incorrect User and Password");
		}catch(Exception e) {
			e.printStackTrace();
			Log.fail(e.getMessage());
		}
	}
	
	@Test(priority=5)
	public void loginWithCorrectUserNameAndPassword() {
		try {
			Log.startTest("Login with Correct Username and Password");
			reg.clickOnLogin();
			Log.assertThat(reg.checkLoggedIn(), "Logged in as");
			Log.endTest("Login with Correct Username and Password");
		}catch(Exception e) {
			e.printStackTrace();
			Log.fail(e.getMessage());
		}
	}
	
	@Test(priority=6)
	public void deleteUser() {
		try {
			Log.startTest("Delete the User");
			Log.assertThat(reg.checkDelete(), "Account Deleted!");
			Log.endTest("Delete the User");
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
