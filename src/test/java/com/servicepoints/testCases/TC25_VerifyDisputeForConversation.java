package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;


public class TC25_VerifyDisputeForConversation extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String CMail=con.setCEmailFrDispt();
	public String process=con.setProcessStatus();
	public String CPass=con.setCpassForDispute();
	public String proForConversation=con.setProductForConversation();
	public String queries=con.setQueries();
	public String otherTxt=con.setOtherTxt();

	public String agentMailDsp=con.setAMailDsp();
	public String agentPassDsp=con.setApassDsp();
	public String agentAnswer=con.setAnswer();
	public String fileName="A319.pdf";

	@Test
	public void verifyDisputeForConversation() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(2000);
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		
		cop.sendPnameinSearch(proForConversation);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnStatusDrop();
		//Thread.sleep(3000);
		cop.dropdownSearch(process);
		//logger.info("fulfilled status is entered.");
		//cop.clickOnFulfillTab();
		//cop.clickOnFProcessingTab();
		//aop.clickOnProcessTab();
		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.scrollTillEle(driver);
		Thread.sleep(2000);
		
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
		Thread.sleep(3000);
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(proForConversation);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(6000);
		
		if(driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute conversation is successed.");
		}else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute conversation is failed.");
			Assert.assertTrue(false);
		}
		
		asop.clickOnShowDsp();
		Thread.sleep(2000);
		asop.selectDspStatus();
		Thread.sleep(2000);
		asop.sendAnswer(agentAnswer);
		Thread.sleep(2000);
		asop.clickOnSendAnswer();
		Thread.sleep(4000);
		
		driver.get(baseURL);
		
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		
		cop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		cop.sendPnameinSearch(proForConversation);
		Thread.sleep(3000);
		logger.info("Product name is entered.");
		
		cop.clickOnStatusDrop();
		//Thread.sleep(3000);
		cop.dropdownSearch(process);
		//logger.info("fulfilled status is entered.");
		//cop.clickOnFulfillTab();
		//cop.clickOnFProcessingTab();
		//aop.clickOnProcessTab();
		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		cop.clickOnFDiv();
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(3000);
		cop.clickOnDispHistory();
		Thread.sleep(2000);
		cop.clickOnFirstDisputeTab();
		Thread.sleep(5000);
		
		
		if(driver.getPageSource().contains("Dispute history")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute history opening is successfull.");
		}else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute history opening is failed.");
			Assert.assertTrue(false);
		}
		
	}
}
