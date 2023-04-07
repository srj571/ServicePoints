package com.servicepoints.testCases;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.LoginPage;

public class TC01_AdminLoginTest extends BaseClass {

	@Test
	public void LoginTest() throws IOException, InterruptedException {
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
			logger.info("Login to the Admin Dashboard Successfully.");
		} else {
			captureScreen(driver, "AdminLoginPage");
			logger.info("Login to Admin Dashboard failed.");
			Assert.assertTrue(false);
		}
	}

}
