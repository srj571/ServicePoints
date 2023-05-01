package com.servicepoints.testCases;

import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC25_VerifyRefundAndConversation extends BaseClass{

	ReadConfig rd=new ReadConfig();
	public String agmail=rd.setAgentTrackMail();
	public String agpass=rd.setAgentTrackPass();
	public String productRefundConv=rd.setProductRefundConv();
	public String process=rd.setProcessStatus();
	public String trackingNum=rd.setTrackingNum();
	public String clmail=rd.setClientTrackMail();
	public String clpass=rd.setClientTrackPass();
	public String fulfillStatus=rd.setFullfill();
	public String agentAnswer=rd.setAnswer();
	public String queries=rd.setQueries();
	
	@Test
	public void verifyRefundAndConversation() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		WebDriverWait wait=new WebDriverWait(driver,10);
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		aop.searchPnameTrack(productRefundConv);
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
		aop.clickOnfDiv();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.AddTrackingBtn));
		logger.info("Tracking number entered.");
		aop.scrollTillAddTracking(driver);
		Thread.sleep(1000);
		
		aop.clickOnAddTracking();
		Thread.sleep(3000);
		logger.info("clicked on add tracking button.");
		
		aop.clickOnAllCheckBoxes();
		Thread.sleep(1000);
		aop.clickOnFirstCheckBox();
		Thread.sleep(1000);
		
		
		aop.setTrackingNum(trackingNum);
		//aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));
		
		aop.clickOnSbmtTracking();
		logger.info("Clicked on submit tracking button.");
		Thread.sleep(6000);
		
		if(driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(3000);
		}else {
			logger.info("Verification of adding tracking number is failed.");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		
		cop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		cop.sendPnameinSearch(productRefundConv);
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
		
//		if(driver.getPageSource().contains("No orders found ")) {
//			cop.clickOnStatusDrop();
//			Thread.sleep(1000);
//			cop.dropdownSearch(fulfillStatus);
//			Thread.sleep(1000);
//			cop.clickOnFulfillTab();
//			Thread.sleep(1000);
//			cop.clickOnFDiv();
//			Thread.sleep(1000);
//		}
		
		logger.info("clicked on first div");
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOf(cop.openDspbtn));
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
			Assert.assertTrue(true);
			logger.info("Verification of Dispute for refund raised Successfully.");
			Thread.sleep(3000);
		}else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute for refund raising failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productRefundConv);
		Thread.sleep(3000);
		
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		
		
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		
//		asop.selectDspStatusToDeclined();
//		logger.info("Dispute Declined status selected.");
//		Thread.sleep(3000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);
		
		
		if(driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of conversation in dispute is successfull.");
		}else {
			captureScreen(driver, "acceptDispute");
			Assert.assertTrue(false);
			logger.info("Verification of conversation in dispute is failed.");
		}
		
		asop.clickOnShowDsp();
		Thread.sleep(2000);
		asop.selectDspStatus();
		Thread.sleep(2000);
		asop.sendAnswer(agentAnswer);
		Thread.sleep(2000);
		logger.info("Verification of close refund dispute.");
		asop.clickOnSendAnswer();
		Thread.sleep(4000);
		logger.info("Agent loggout successfully");
		
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		
		cop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		cop.sendPnameinSearch(productRefundConv);
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
		
		cop.scrollTillEle(driver);
		Thread.sleep(1000);
		
		cop.clickOnDispHistory();
		Thread.sleep(3000);
		
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
