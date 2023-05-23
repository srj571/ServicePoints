package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.ClientAffiliatePage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SignUpPage;
import com.servicepoints.utilities.ReadConfig;

public class TC36_VerifyAddAffiliateTest extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String clntMailForAffiliate=con.setClntMailForAffiliate();
	public String clntPassForAffiliate=con.setClntPassForAffiliate();
	public String fnameAf=con.setFnameAf();
	public String lnameAf=con.setLnameAf();
	public String codeAf=con.setCodeAf();
	public String ordersAf=con.setOrdersAf();
	public String passwordAf=con.setPasswordAf();
	public String cPasswordAf=con.setCpasswordAf();
	public String userEmailAf=con.setUserMailAf();
	public String unameAf=con.setUserNameAf();
	public String domainNameAf=con.setDomainNameAf();
	public String aliasAf=con.setAliasAf();
	public String storePassAf=con.setStorePassAf();
	public String dateOrderAf=con.setStorePassAf();
	
	
	@Test
	public void verifyAddAffiliate() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(clntMailForAffiliate);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clntPassForAffiliate);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client logged in successfully.");

		ClientAffiliatePage cap=new ClientAffiliatePage(driver);
		SignUpPage sp=new SignUpPage(driver);
		cap.clickOnAffiliateTab();
		
		driver.get(cap.getAffiliateLink());
		Thread.sleep(2000);
		
		sp.setUserFirstName(fnameAf);
		logger.info("First Name is entered.");
		Thread.sleep(1000);
		
		sp.setUserLastName(lnameAf);
		logger.info("Last Name is entered.");
		Thread.sleep(1000);
		
		sp.setCountryCode(codeAf);
		logger.info("Country code is entered.");
		Thread.sleep(1000);
		
		String num=BaseClass.getRandomNum();
		sp.setMobileNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(1000);
		
//		String val=BaseClass.getRandomString();
//		String email=val+"@yopmail.com";	
		sp.setEmail(userEmailAf);
		logger.info("Email is entered.");
		Thread.sleep(1000);
		
		sp.setDailyOrder(ordersAf);
		logger.info("Order is set.");
		Thread.sleep(1000);
		
		sp.setPassword(passwordAf);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		sp.setCofirmPass(cPasswordAf);
		logger.info("Confirm password is entered.");
		Thread.sleep(1000);
		
		sp.clickTermCheckBox();
		sp.clickOrderCheckBox();
		Thread.sleep(2000);
		sp.clickBtnSignUp();
		Thread.sleep(5000);
		
		if(driver.getPageSource().contains("Congratulations! You have successfully signed up for Service Points.")) {
			logger.info("User is Successfully Signed up.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		}else {
			captureScreen(driver, "UserSignUp");
			logger.info("User is Failed to Signed up.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		logger.info("Application Opened.");

		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(4000);
		
		adminAccount.enterUserName(unameAf);
		logger.info("Entered User name in search field.");
		Thread.sleep(2000);
		
		adminAccount.getClientsTab();
		Thread.sleep(4000);
		
		adminAccount.goToUserProfile();
		
		adminAccount.editUserProfile();
		Thread.sleep(3000);
		
		adminAccount.selectAccountType();
		adminAccount.selectUserAsAClient();
		logger.info("Client type is selected.");
		Thread.sleep(4000);
		
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
		Thread.sleep(2000);
		
		adminAccount.selectSwitchOn();
		Thread.sleep(2000);
		
		adminAccount.saveInfo();
		logger.info("User info is saved.");
		Thread.sleep(2000);
		
		
		if(driver.getPageSource().contains("Account updated successfully")) {
			Assert.assertTrue(true);
			logger.info("Account updated successfully");
			Thread.sleep(6000);
		}else {
			captureScreen(driver, "Client approval");
			logger.info("Account updation failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		lp.setAdminMailId(userEmailAf);
		lp.setAdminPassword(passwordAf);
		Thread.sleep(2000);
		lp.clickLoginbtn();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains(fnameAf)) {
			Assert.assertTrue(true);
			logger.info("Verification of Affiliate login successfully");
			Thread.sleep(4000);
		}else {
			captureScreen(driver, "Client approval");
			logger.info("Verification of Affiliate login failed.");
			Assert.assertTrue(false);
		}
	}
}
