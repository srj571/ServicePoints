package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.servicepoints.PageObjects.LoginPage;

public class TC01_AdminLoginTest extends BaseClass {

	@Test(priority = 2)
	public void adminLoginTest() throws IOException, InterruptedException {
		driver.get(baseURL);
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(5000);

		if (driver.getTitle().equals("Admin Dashboard | Service Points")) {
			Assert.assertTrue(true);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			logger.info("Login to the Admin Dashboard Successfully.");
		} else {
			captureScreen(driver, "AdminLoginPage");
			logger.info("Login to Admin Dashboard failed.");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority = 1)
	public void verifyLoginWithInvalidCredentials() throws InterruptedException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(adminInvalidPass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(5000);
		
		if (driver.getPageSource().contains("You filled in the wrong credentials.")) {
			Assert.assertTrue(true);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			logger.info("Verification with invalid credentials while login is Successfully.");
		} else {
			logger.info("Verification with invalid credentials while login is failed.");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 3)
	public void verifyErrorMsgAfterPassingWrongMailId() throws InterruptedException {
		driver.get(baseURL);
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(InvalidAdminMailId);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(5000);
		
		if (lp.getErrorMsgOfInvalidMailId().contains("Please enter valid e-mail address")) {
			Assert.assertTrue(true);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			logger.info("Verification with invalid mail ID while login is Successfully.");
		} else {
			logger.info("Verification with invalid mail ID while login is failed.");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			Assert.assertTrue(false);
		}
	}
}
