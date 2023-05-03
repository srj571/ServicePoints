package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC20_VerifyAcceptDispute extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	public String CMail=rd.setCEmailFrDispt();
	public String CPass=rd.setCpassForDispute();
	public String agentMailDsp=rd.setAMailDsp();
	public String agentPassDsp=rd.setApassDsp();
	public String productToAcceptDsp=rd.setProductDsp();
	public String agentAnswer=rd.setAnswer();
	public String process=rd.setProcessStatus();
	
	@Test
	public void verifyAcceptDisputeTest() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productToAcceptDsp);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		
		asop.selectDspStatus();
		logger.info("Dispute Accepted.");
		Thread.sleep(3000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		asop.scrollTillSendAns(driver);
		Thread.sleep(1000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);
		
		
		if(driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Dispute acceptance is successed.");
		}else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(productToAcceptDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnStatusDrop();
		Thread.sleep(1000);
		cop.dropdownSearch(process);
		cop.clickOnProcessingTab();
		Thread.sleep(2000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(4000);
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(3000);
		cop.clickOnDispHistory();
		Thread.sleep(5000);
		logger.info("Verification of open Dispute History successfull.");
		
		if(cop.getDspHistoryStatusA().equals("Accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successed.");
		}else {
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
		
		
	}
}
