package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC32_VerifySpecialRequestCloseByAll extends BaseClass{

	ReadConfig con=new ReadConfig();
	
	public String CMail=con.setSRClientMail();
	public String CPass=con.setSRClientPass();
	public String productSR=con.setProductSR();
	
	public String agentMailDsp=con.setAMailDsp();
	public String agentPassDsp=con.setApassDsp();
	public String teamleaderSr=con.setTeamleadSR();
	public String adminSp=con.setAdminSPSR();
	public String agentSp=con.setAgentSPSR();
	public String answerSR=con.setAnswerSR();
	@Test
	public void verifySpecialRequestClosingByAll() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(2000);
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		
		cop.sendPnameinSearch(productSR);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.clickOnSpecialRequest();
		Thread.sleep(3000);
		cop.handleRequestDropdown();
		Thread.sleep(3000);
		
		cop.clickOnFirstTwoCheckBoxes();
		Thread.sleep(3000);
		logger.info("Checkboxes selected.");
		
		cop.clickOnSendRequestBtn();
		logger.info("Clicked on Send Request.");
		
		Thread.sleep(6000);
		if(driver.getPageSource().contains("Proof of shipment will be sent to you within 24 hours")) {
			Assert.assertTrue(true);
			logger.info("Special Request is open by Client Successfully..");		
		}
		else {
			captureScreen(driver, "Special Request");
			Assert.assertTrue(false);
			logger.info("Special Request is failed to open by Client..");		
		}
		
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(AdminMailID);
		lp.setAdminPassword(AdminPassword);
		lp.clickLoginbtn();
		logger.info("Admin logged in Successfully.");
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(adminSp);
		logger.info("Entered Admin Supplier name in search field.");
		Thread.sleep(3000);
		
		adminAccount.goToAdminSUPTab();
		Thread.sleep(3000);
		
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the admin supplier Account.");
		Thread.sleep(3000);
		
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String parent=it.next();
		String second=it.next();
		driver.switchTo().window(second);
		Thread.sleep(4000);
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		Thread.sleep(3000);
		logger.info("Open disputes page.");
		
		asop.clickOnSpecialRequestTab();
		Thread.sleep(3000);
		logger.info("Click on special request.");
		
		asop.clickOnOpenTabInSreq();
		Thread.sleep(3000);
		
		asop.searchProductForDsp(productSR);
		Thread.sleep(3000);
		
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		asop.clickOnShowRequestTab();
		Thread.sleep(3000);
		
		asop.setTxtAnsSpRequest(answerSR);
		Thread.sleep(3000);
		
		asop.clickOnSendAnsSpRequest();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Thanks for the answer.")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request by Admin supplier is successfull..");
		}else {
			Assert.assertTrue(false);
			logger.info("Verification of Close Special request by Admin supplier is failed..");
		}
		
		driver.close();
		driver.switchTo().window(parent);
		driver.get(baseURL);
		
		lp.setAdminMailId(CMail);
		Thread.sleep(2000);
		lp.setAdminPassword(CPass);
		Thread.sleep(2000);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfull.");
		Thread.sleep(2000);
		
		cop.clickOnOrdersTab();
		
		cop.sendPnameinSearch(productSR);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.clickOnSpecialRequest();
		Thread.sleep(3000);
		cop.handleRequestDropdown();
		Thread.sleep(3000);
		
		cop.clickOnTwoThirdCheckBoxes();
		Thread.sleep(3000);
		logger.info("Checkboxes selected.");
		
		cop.clickOnSendRequestBtn();
		logger.info("Clicked on Send Request.");
		
		Thread.sleep(6000);
		if(driver.getPageSource().contains("Proof of shipment will be sent to you within 24 hours")) {
			Assert.assertTrue(true);
			logger.info("Special Request is open by Client Successfully..");		
		}
		else {
			captureScreen(driver, "Special Request");
			Assert.assertTrue(false);
			logger.info("Special Request is failed to open by Client..");		
		}
		
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(AdminMailID);
		lp.setAdminPassword(AdminPassword);
		lp.clickLoginbtn();
		logger.info("Admin logged in Successfully.");
		
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(teamleaderSr);
		logger.info("Entered teamleader name in search field.");
		Thread.sleep(3000);
		
		adminAccount.getTeamleaderTab();;
		Thread.sleep(3000);
		
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the teamleader Account.");
		Thread.sleep(3000);
		
		String third=it.next();
		driver.switchTo().window(third);
		Thread.sleep(4000);
		
		asop.clickOnDisputesTab();
		Thread.sleep(3000);
		logger.info("Open disputes page.");
		
		asop.clickOnSpecialRequestTab();
		Thread.sleep(3000);
		logger.info("Click on special request.");
		
		asop.clickOnOpenTabInSreq();
		Thread.sleep(3000);
		logger.info("Click on special request.");
		
		asop.searchProductForDsp(productSR);
		Thread.sleep(3000);
		logger.info("Searched product name.");
		
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		asop.clickOnShowRequestTab();
		Thread.sleep(3000);
		
		asop.setTxtAnsSpRequest(answerSR);
		Thread.sleep(3000);
		logger.info("Answer is given by teamlead.");
		
		asop.clickOnSendAnsSpRequest();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Thanks for the answer.")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request by Teamleader is successfull..");
		}else {
			Assert.assertTrue(false);
			logger.info("Verification of Close Special request by Teamleader is failed..");
		}
		
		
		
	}
}
