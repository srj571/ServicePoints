package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC22_VerifyDeclinedDisputeFunction extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String CMail=con.setCEmailFrDispt();
	public String CPass=con.setCpassForDispute();
	public String proToDeclinedDsp=con.setProductToDeclinedDsp();
	public String queries=con.setQueries();
	public String otherTxt=con.setOtherTxt();
	
	public String agentMailDsp=con.setAMailDsp();
	public String agentPassDsp=con.setApassDsp();
	public String agentAnswer=con.setAnswer();
	public String fileName="A319.pdf";

	@Test
	public void verifyDeclinedDisputeTest() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(proToDeclinedDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(1000);
		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);
		
		cop.selectOtherDspOption();
		logger.info("Customer got wrong product option selected.");
		
		cop.setOtherInfo(otherTxt);
		Thread.sleep(2000);
		
		cop.handleDspSolution();
		logger.info("I want the order to be shipped immediately option is selected.");
		Thread.sleep(3000);
		
		cop.clickOnCheckBox();	
		Thread.sleep(3000);
		
//		cop.sendFiles(fileName);
//		Thread.sleep(2000);
		
		cop.sendQueries(queries);
		Thread.sleep(2000);
		
		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");
		
		if(driver.getPageSource().contains("Dispute raised successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		}else {
			captureScreen(driver, "other dispute");
			logger.info("Verification of Dispute raised failed.");
			AssertJUnit.assertTrue(false);
		}
		
		logger.info("Client logged out Successfully.");
		driver.get(baseURL);
		
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(proToDeclinedDsp);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		asop.scrollTillShowDispute(driver);
		Thread.sleep(2000);
		
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		asop.selectDspStatusToDeclined();
		logger.info("Dispute Declined Status selected from dropdown.");
		Thread.sleep(3000);
		
		asop.scrollTillSendAns(driver);
		Thread.sleep(1000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);
		
		if(driver.getPageSource().contains("Dispute declined successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		}else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		
		cop.sendPnameinSearch(proToDeclinedDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(2000);
		cop.clickOnDispHistory();
		Thread.sleep(5000);
		logger.info("Verification of open Dispute History successfull.");
		
		if(cop.getDspHistoryStatusD().equals("Declined")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute declined is successed.");
		}else {
			logger.info("Verification of Dispute declined is failed.");
			AssertJUnit.assertTrue(false);
		}                                        
	}
}
