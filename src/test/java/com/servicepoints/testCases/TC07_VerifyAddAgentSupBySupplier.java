package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminSupplierPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC07_VerifyAddAgentSupBySupplier extends BaseClass {

	@Test
	public void verifyAddAgent() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(adminemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(adminagpass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Admin(SUP) Loged in.");

		AdminSupplierPage asp = new AdminSupplierPage(driver);
		asp.clickOnTeamTab();
		logger.info("Go to the Team page.");
		Thread.sleep(4000);

		asp.clickOnAddNewAgent();
		Thread.sleep(2000);

		asp.sendFName(afname);
		logger.info("First name is entered.");

		asp.sendLname(alname);
		logger.info("Last name is entered.");

		asp.sendCCode(accode);
		logger.info("Country code is entered.");

		String num = BaseClass.getRandomNum();
		asp.sendmobNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(4000);

		asp.sendEmail(amail);

		asp.sendPass(pass);

		asp.sendcpass(cpass);

		asp.clickOnAddAgentbtn();
		logger.info("Agent info is saved.");
		Thread.sleep(4000);
		
		if(driver.getPageSource().contains("Agent with the same email address already exists. Please use different email address.")) {
			logger.info("Agent with the same email address already exists.");
			Assert.assertTrue(false);			
		}
			
		asp.closePopup();

		asp.logOut();
		Thread.sleep(4000);

		lp.setAdminMailId(amail);
		logger.info("Agent email is entered.");

		lp.setAdminPassword(pass);
		logger.info("Agent password is entered.");
		Thread.sleep(4000);

		lp.clickLoginbtn();
		Thread.sleep(4000);

		if (driver.getTitle().contains("Agent Supplier Dashboard | Service Points")) {
			logger.info("Verification of Agent Supplier Login is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(1000);
		} else {
			captureScreen(driver, "AddAgent");
			logger.info("Verification of Agent Supplier Login is Failed.");
			Assert.assertTrue(false);
		}

	}

}
