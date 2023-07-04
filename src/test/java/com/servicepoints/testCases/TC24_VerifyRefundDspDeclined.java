package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC24_VerifyRefundDspDeclined extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	public String agmail=rd.setAgentTrackMail();
	public String agpass=rd.setAgentTrackPass();
	public String productRefundDD=rd.setProductRefundDD();
	public String process=rd.setProcessStatus();
	public String trackingNum=rd.setTrackingNum();
	public String clmail=rd.setClientTrackMail();
	public String clpass=rd.setClientTrackPass();
	public String fulfillStatus=rd.setFullfill();
	public String agentAnswer=rd.setAnswer();
	public String queries=rd.setQueries();
	
	@Test
	public void verifyRefundDspDeclined() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		WebDriverWait wait=new WebDriverWait(driver,10);
		
		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		aop.searchPnameTrack(productRefundDD);
		logger.info("Product name is entered.");
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.orderStatusDrop));
		aop.clickOnStatusDrop();
		logger.info("Clicked on status dropdown.");
		Thread.sleep(3000);
		
		aop.setStatusSearchDrop(process);
		logger.info("Processing status is searched.");
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.fprocessTab));
		aop.clickOnProcessTab();
		logger.info("Processing status is searched.");
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.fdiv));
		logger.info("Processing status is searched.");
		aop.clickOnfDiv();
		Thread.sleep(3000);
		
		aop.scrollTillAddTracking(driver);
		Thread.sleep(2000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.AddTrackingBtn));
		logger.info("Tracking number entered.");
		aop.clickOnAddTracking();
		Thread.sleep(3000);
		logger.info("clicked on add tracking button.");
		
		aop.clickOnAllCheckBoxes();
		Thread.sleep(1000);
		aop.clickOnFirstCheckBox();
		Thread.sleep(1000);
		
		
		aop.setTrackingNum(trackingNum);
		Thread.sleep(3000);
		
		aop.clickOnSbmtTracking();
		logger.info("Clicked on submit tracking button.");
		Thread.sleep(7000);
		
		if(driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of adding tracking number is failed.");
			Thread.sleep(2000);
			AssertJUnit.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		
		cop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		cop.sendPnameinSearch(productRefundDD);
		Thread.sleep(3000);
		logger.info("Product name is entered.");
		
		cop.clickOnFDiv();
		Thread.sleep(3000);
		logger.info("clicked on first div");
		
		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(2000);
		
		cop.clickOnOpenDspbtn();
		Thread.sleep(3000);
		logger.info("click on open dispute button.");
		
		cop.handleDspIssues();
		Thread.sleep(3000);
		
		cop.refundSolutionDsp();
		Thread.sleep(3000);
		logger.info("dispute solution entered.");
		
		cop.clickOnFirstCheckBoxForDsp();
		Thread.sleep(3000);
		
		cop.sendQueries(queries);
		
		cop.SaveDispute();
		Thread.sleep(5000);
		
		logger.info("Dispute saved.");
		
		Thread.sleep(3000);
		if(driver.getPageSource().contains("Dispute raised successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute for refund raised Successfully.");
			Thread.sleep(3000);
		}else {
			//captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute for refund raising failed.");
			AssertJUnit.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productRefundDD);
		Thread.sleep(3000);
		
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		asop.scrollTillShowDispute(driver);
		Thread.sleep(2000);
		
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		
		asop.selectDspStatusToDeclined();
		logger.info("Dispute Declined status selected.");
		Thread.sleep(3000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(3000);
		
		
		if(driver.getPageSource().contains("Dispute declined successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute declined is successfull.");
		}else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute declined is failed.");
			AssertJUnit.assertTrue(false);
		}
		
		
		AgentOrdersPage aaop=new AgentOrdersPage(driver);
		
		Thread.sleep(4000);
		aaop.clickOnOrdersTab();
		Thread.sleep(2000);
		aaop.searchPnameTrack(productRefundDD);
		Thread.sleep(2000);
		aaop.clickOnfDiv();
		Thread.sleep(4000);
		
		logger.info("Verification of Refund Dispute acceptance is successfully.");
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		cop.clickOnOrdersTab();
		Thread.sleep(1000);
		
		cop.sendPnameinSearch(productRefundDD);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(2000);
		cop.clickOnDispHistory();
		Thread.sleep(6000);
		logger.info("Verification of open Dispute History successfull.");
		
		cop.clickOnFirstDisputeTab();
		Thread.sleep(5000);
		
		if(cop.getDspHistoryStatusD().equals("Declined")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute declined is successed.");
		}else {
			logger.info("Verification of Dispute declined is failed.");
			AssertJUnit.assertTrue(false);
		}
		
	}
}
