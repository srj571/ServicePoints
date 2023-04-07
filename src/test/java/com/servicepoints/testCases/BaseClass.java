package com.servicepoints.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.servicepoints.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Logger logger = LogManager.getLogger(BaseClass.class.getName());

	ReadConfig rc = new ReadConfig();

	public String baseURL = rc.getApplicationUrl();
	public String AdminMailID = rc.getAdminUsername();
	public String AdminPassword = rc.getAdminPassword();
	public String clientName = rc.getClientName();

	public String fname = rc.getFirstName();
	public String lname = rc.getLastName();
	public String code = rc.getCountryCode();
	public String orders = rc.getOrders();
	public String password = rc.getPassword();
	public String cpassword = rc.getConfirmPass();
	public String userName = rc.getUserName();
	public String userEmail = rc.getUserEmail();
	public String agentSupplierName = rc.getAgentSupplierName();
	public String agentSupportName = rc.getAgentSupportName();
	public String adminSupplierName = rc.getAdminSUPName();

	public static WebDriver driver;

	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
		if (br.equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver",rc.getChromePath());
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (br.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", rc.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public static void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File trg = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(src, trg);
		System.out.println("Screenshot taken");
	}

	public static String getRandomString() {
		String email = RandomStringUtils.randomAlphabetic(5);
		return email;
	}

	public static String getRandomNum() {
		String num = RandomStringUtils.randomNumeric(10);
		return num;
	}

}
