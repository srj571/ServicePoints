package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC32_VerifySpecialRequestCloseByAll extends BaseClass{

	ReadConfig con=new ReadConfig();
	
	public String CMail=con.setCEmailFrDispt();
	public String CPass=con.setCpassForDispute();
	public String proDsp=con.setProductForDsp();
	public String queries=con.setQueries();
	
	public String agentMailDsp=con.setAMailDsp();
	public String agentPassDsp=con.setApassDsp();
	public String agentAnswer=con.setAnswer();
	
	@Test
	public void verifySpecialRequestClosingByAll() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		
		cop.sendPnameinSearch(proDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.clickOnSpecialRequest();
		Thread.sleep(3000);
		cop.handleRequestDropdown();
		Thread.sleep(3000);
		
		cop.clickOnSpecialRequestchecks();
		Thread.sleep(3000);
		logger.info("Checkboxes selected.");
		
		cop.clickOnSendRequestBtn();
		logger.info("Clicked on Send Request.");
		
		Thread.sleep(6000);
		if(driver.getPageSource().contains("Proof of shipment will be sent to you within 24 hours")) {
			Assert.assertTrue(true);
			logger.info("Special Request is opend by Client Successfully..");		
		}
		else {
			captureScreen(driver, "Special Request");
			Assert.assertTrue(false);
			logger.info("Special Request is failed to opend by Client..");		
		}
		
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		Thread.sleep(3000);
		logger.info("Open disputes page.");
		
		asop.clickOnSpecialRequestTab();
		Thread.sleep(3000);
		logger.info("Click on special request.");
		
		asop.clickOnOpenTabInSreq();
		Thread.sleep(3000);
		logger.info("Click on special request.");
		
		asop.searchProductForDsp(proDsp);
		Thread.sleep(3000);
		
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		asop.clickOnShowRequestTab();
		Thread.sleep(3000);
		
		asop.setTxtAnsSpRequest(agentAnswer);
		Thread.sleep(3000);
		
		asop.clickOnSendAnsSpRequest();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Thanks for the answer.")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request is successfull..");
		}else {
			Assert.assertTrue(false);
			logger.info("Verification of Close Special request is failed..");
		}
	}
}
