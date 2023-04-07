package com.servicepoints.testCases;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC04_VerifyClientApprovalTest extends BaseClass{
	
	
	public String supemail = rc.getSupEmail();
	public String suppass = rc.getSupPass();
	public String supfname = rc.getSupFirstName();
	public String suplname = rc.getSupLastName();
	public String supsupfull=fname+" "+lname;
	
	
	@Test
	public void verifyClientApproval() throws InterruptedException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(userName);
		logger.info("Entered User name in search field.");
		
		adminAccount.getClientsTab();
		Thread.sleep(3000);
		
		adminAccount.goToUserProfile();
		
		adminAccount.editUserProfile();
		Thread.sleep(3000);
		
		adminAccount.selectAccountType();
		adminAccount.selectUserAsAClient();
		logger.info("Client type is selected.");
		Thread.sleep(1000);
		
		adminAccount.selectAgentSupplierDrop();
		adminAccount.selectAgentSUP();
		Thread.sleep(3000);
		logger.info("Agent Supplier name is selected.");
		adminAccount.clickOnAgentFee();
		
		adminAccount.selectAgentSupportDrop();
		adminAccount.sendAgentSupportName(agentSupportName);
		adminAccount.selectAgentSPOption();
		logger.info("Agent support name is selected.");
		Thread.sleep(3000);
		
		adminAccount.selectInvoiceTypeDrop();
		adminAccount.selectInvoice();
		logger.info("Invoice type is selected.");
		Thread.sleep(3000);
		
		adminAccount.selectSwitchOn();
		Thread.sleep(3000);
		
		adminAccount.saveInfo();
		logger.info("User info is saved.");
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Account updated successfully")) {
			Assert.assertTrue(true);
			logger.info("Account updated successfully");
			Thread.sleep(3000);
		}else {
			logger.info("Account updation failed.");
			Assert.assertTrue(false);
		}
		
		adminAccount.logoutAdmPanel();
		logger.info("Admin Logout...");
		Thread.sleep(3000);
				
		lp.setAdminMailId(userEmail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(password);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains(supsupfull)) {
			Assert.assertTrue(true);
			logger.info("Account updated successfully");
			Thread.sleep(3000);
		}else {
			logger.info("Account updation failed.");
			Assert.assertTrue(false);
		}
	}
	
	
}
