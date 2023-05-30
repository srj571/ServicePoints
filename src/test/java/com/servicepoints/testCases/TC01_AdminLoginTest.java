package com.servicepoints.testCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.LoginPage;

public class TC01_AdminLoginTest extends BaseClass {

	@Test
	public void adminLoginTest() throws IOException, InterruptedException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);

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
}
