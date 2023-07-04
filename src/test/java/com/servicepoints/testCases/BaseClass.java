package com.servicepoints.testCases;

import org.testng.annotations.AfterMethod;
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
import org.openqa.selenium.chrome.ChromeOptions;
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
	public String cpass=rc.getcPass();
	public String pass=rc.getPass();
	public String amail=rc.getEmail();
	public String accode=rc.getCode();
	public String alname=rc.getAgentlname();
	public String afname=rc.getAgentfname();
	public String adminagpass=rc.getAdminPass();
	public String adminemail=rc.getAdminSUPmail();
	public String agentsupmail=rc.setAgentSupEmail();
	public String agentsuppass=rc.setAgentSupPass();
	public String product=rc.setProduct();
	public String FirstPcsPrice=rc.set1PcsPrice();
	public String SecPcsPrice=rc.set2PcsPrice();
	public String ThirdPcsPrice=rc.set3PcsPrice();
	public String ForthPcsprice=rc.set4PcsPrice();
	public String clientemail=rc.setClientEmail();
	public String cPass = rc.setClientPassword();
	public String proToAcceptQuo=rc.setProductName();
	public String DomainName=rc.setDomainName();
	public String Alias=rc.setAlias();
	public String StorePass=rc.setStorePass();
	public String DateOrder=rc.setFetchDate();
	public String editAlias=rc.editAlias();
	public String TeamLName=rc.setTeamlName();
	public String TAfname=rc.setFName();
	public String TAlname=rc.setlName();
	public String TACode=rc.settCode();
	public String TAEmail=rc.settEmail();
	public String TAPass=rc.setTPass();
	public String TAcPass=rc.setCPass();
					
	public static WebDriver driver;

	@BeforeClass
	@Parameters("browser")
	public void setup(String br) {
		if (br.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--ignore-certificate-errors");
			option.addArguments("--allow-running-insecure-content");
			driver = new ChromeDriver(option);
			
		} else if (br.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
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
	
	public static void closeAllWinTabsExceptParent() {
	    String originalHandle = driver.getWindowHandle();

	    for(String handle : driver.getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	            driver.switchTo().window(handle);
	            driver.close();
	        }
	    }

	    driver.switchTo().window(originalHandle);
	}

}
