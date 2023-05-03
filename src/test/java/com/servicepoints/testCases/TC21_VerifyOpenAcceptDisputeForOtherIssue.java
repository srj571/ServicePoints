package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC21_VerifyOpenAcceptDisputeForOtherIssue extends BaseClass{

	ReadConfig con=new ReadConfig();
	
	public String CMail=con.setCEmailFrDispt();
	public String CPass=con.setCpassForDispute();
	public String proDsp=con.setProductForDsp();
	public String queries=con.setQueries();
	public String otherTxt=con.setOtherTxt();
	
	public String agentMailDsp=con.setAMailDsp();
	public String agentPassDsp=con.setApassDsp();
	public String productToAcceptDsp=con.setProductDsp();
	public String agentAnswer=con.setAnswer();
	
	@Test
	public void verifyOpenAcceptOtherDsp() throws InterruptedException, IOException {
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
		
		cop.scrollTillEle(driver);
		Thread.sleep(1000);
		
		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);
		
		cop.selectOtherDspOption();
		logger.info("Customer got wrong product option selected.");
		
		cop.setOtherInfo(otherTxt);
		Thread.sleep(2000);
		
		cop.handleDspSolution();
		logger.info("I want the order to be shipped immediately option is selected.");
		
		cop.clickOnCheckBox();	
		Thread.sleep(3000);
		cop.sendQueries(queries);
		
		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");
		
		if(driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		}else {
			captureScreen(driver, "other dispute");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}
		
		logger.info("Client logged out Successfully.");
		driver.get(baseURL);
		
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(proDsp);
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
		Thread.sleep(3000);
		
		
		if(driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
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
		
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(proDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
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
