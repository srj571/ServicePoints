package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC23_VerifyAddTrackingAndRefundDspFunction extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agmail = rd.setAgentTrackMail();
	public String agpass = rd.setAgentTrackPass();
	public String productTrack = rd.setPnameTracking();
	public String process = rd.setProcessStatus();
	public String trackingNum = rd.setTrackingNum();
	public String clmail = rd.setClientTrackMail();
	public String clpass = rd.setClientTrackPass();
	public String fulfillStatus = rd.setFullfill();
	public String agentAnswer = rd.setAnswer();

	@Test
	public void verifyAddTrackingAndRefundDispute() throws InterruptedException, IOException {

		LoginPage lp = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(productTrack);
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
		Thread.sleep(3000);

		aop.clickOnSbmtTracking();
		logger.info("Clicked on submit tracking button.");
		
		aop.waitTillSuccessBoxOfTrackingNum(driver);
		
		if (driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of adding tracking number is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		ClientOrdersPage cop = new ClientOrdersPage(driver);

		cop.clickOnOrdersTab();
		Thread.sleep(3000);

		cop.sendPnameinSearch(productTrack);
		Thread.sleep(3000);
		logger.info("Product name is entered.");

		cop.clickOnStatusDrop();
		
		cop.dropdownSearch(process);
	
		cop.clickOnProcessingTab();
		Thread.sleep(3000);

		cop.clickOnFDiv();
		Thread.sleep(3000);
		logger.info("clicked on first div");

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(1000);

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

		cop.SaveDispute();
		Thread.sleep(5000);

		logger.info("Dispute saved.");

		Thread.sleep(3000);
		if (driver.getPageSource().contains("Dispute raised successfully")) {
			// Assert.assertTrue(true);
			logger.info("Verification of Dispute for refund raised Successfully.");
			Thread.sleep(3000);
		} else {
			// captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute for refund raised failed.");
			// Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productTrack);
		Thread.sleep(3000);

		asop.clickOnFrstDsp();
		Thread.sleep(3000);

		asop.scrollTillShowDispute(driver);
		Thread.sleep(2000);

		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);

		asop.selectDspStatus();
		logger.info("Dispute Accepted.");
		Thread.sleep(3000);

		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);

		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successfull.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}

		AgentOrdersPage aaop = new AgentOrdersPage(driver);

		Thread.sleep(4000);
		aaop.clickOnOrdersTab();
		Thread.sleep(2000);
		aaop.searchPnameTrack(productTrack);
		Thread.sleep(2000);
		aaop.clickOnfDiv();
		Thread.sleep(5000);

		logger.info("Verification of Refund Dispute acceptance is successfully.");

		driver.get(baseURL);

		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		cop.clickOnOrdersTab();

		cop.clickOnStatusDrop();
		// Thread.sleep(3000);
		cop.dropdownSearch(process);
		// logger.info("fulfilled status is entered.");
		// cop.clickOnFulfillTab();
		// cop.clickOnFProcessingTab();
		// aop.clickOnProcessTab();
		cop.clickOnProcessingTab();
		Thread.sleep(3000);

		cop.sendPnameinSearch(productTrack);
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

		if (cop.getDspHistoryStatusA().equals("Accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successed.");
		} else {
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}
}
