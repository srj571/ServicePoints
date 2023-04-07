package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminSupplierPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC07_VerifyAddAgentSupBySup extends BaseClass{
	
	@Test
	public void verifyAddAgent() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(adminemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(adminagpass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Admin(SUP) Loged in.");
		
		AdminSupplierPage asp=new AdminSupplierPage(driver);
		asp.clickOnTeamTab();
		logger.info("Go to the Team page.");
		Thread.sleep(3000);
		
		asp.clickOnAddNewAgent();
		Thread.sleep(1000);
		
		asp.sendFName(afname);
		logger.info("First name is entered.");
		
		asp.sendLname(alname);
		logger.info("Last name is entered.");
		
		asp.sendCCode(accode);
		logger.info("Country code is entered.");
		
		String num = BaseClass.getRandomNum();
		asp.sendmobNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(3000);
		
		
		asp.sendEmail(amail);
		
		asp.sendPass(pass);
		
		asp.sendcpass(cpass);
		
		asp.clickOnAddAgentbtn();
		logger.info("Agent info is saved.");
		Thread.sleep(4000);
		
		asp.closePopup();
		
		asp.logOut();
		Thread.sleep(3000);
		lp.setAdminMailId(amail);
		
		lp.setAdminPassword(pass);
		Thread.sleep(3000);
		
		if(driver.getTitle().contains("Agent Supplier Dashboard | Service Points 167.172.153.57")) {
			logger.info("Verification of Agent Supplier Login is Successfull.");
			Assert.assertTrue(true);
		}else {
			logger.info("Verification of Agent Supplier Login is Failed.");
			Assert.assertTrue(false);
		}
		
	}
	
	
	
	
}
