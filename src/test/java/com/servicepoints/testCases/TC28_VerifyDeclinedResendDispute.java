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

public class TC28_VerifyDeclinedResendDispute extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agmail = rd.setAgentTrackMail();
	public String agpass = rd.setAgentTrackPass();
	public String productDResend = rd.setProductDeclineResend();
	public String process = rd.setProcessStatus();
	public String trackingNum = rd.setTrackingNum();
	public String clmail = rd.setClientTrackMail();
	public String clpass = rd.setClientTrackPass();
	public String fulfillStatus = rd.setFullfill();
	public String agentAnswer = rd.setAnswer();
	public String queries = rd.setQueries();

	@Test
	public void verifyDeclinedResendDispute() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		lp.setAdminMailId(agmail);
		lp.setAdminPassword(agpass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(productDResend);
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

		aop.scrollTillAddTracking(driver);
		Thread.sleep(1000);

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
		// aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));

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
			Thread.sleep(2000);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		cop.clickOnOrdersTab();
		Thread.sleep(3000);

		cop.sendPnameinSearch(productDResend);
		Thread.sleep(3000);
		logger.info("Product name is entered.");

		cop.clickOnStatusDrop();
		cop.dropdownSearch(process);
		
		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		cop.clickOnFDiv();

		logger.info("clicked on first div");
		Thread.sleep(2000);

		cop.scrollTillTheLast(driver);
		Thread.sleep(3000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(3000);
		logger.info("click on open dispute button.");

		cop.handleDspIssues();
		Thread.sleep(3000);

		cop.resendSolutionDsp();
		Thread.sleep(3000);
		logger.info("dispute solution entered.");

		cop.clickOnFirstCheckBoxForDsp();
		Thread.sleep(3000);

		cop.sendQueries(queries);

		cop.SaveDispute();
		Thread.sleep(5000);

		logger.info("Dispute saved.");

		Thread.sleep(3000);
		if (driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute for resend raised Successfully.");
			Thread.sleep(3000);
		} else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute for resend raising failed.");
			Assert.assertTrue(false);
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

		asop.searchProductForDsp(productDResend);
		Thread.sleep(3000);

		asop.clickOnFrstDsp();
		Thread.sleep(3000);

		asop.scrollTillShowDispute(driver);
		Thread.sleep(2000);

		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);

		asop.selectDspStatusToDeclined();
		logger.info("Dispute declined.");
		Thread.sleep(3000);

		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);

		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Dispute declining is successfull.");
		} else {
			captureScreen(driver, "acceptDispute");
			Assert.assertTrue(false);
			logger.info("Verification of Dispute declining is failed.");
		}
		
		
		if(asop.getDisputeStatus().equals("Close dispute - Declined")) {
			Assert.assertTrue(true);
			logger.info("Verification of status for Dispute of refund raised Successfully.");
			Thread.sleep(3000);
		}else {
			logger.info("Verification of status for Dispute of refund raising failed.");
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

		cop.sendPnameinSearch(productDResend);
		Thread.sleep(3000);
		logger.info("Product name is entered.");

		cop.clickOnStatusDrop();
		cop.dropdownSearch(process);
		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		cop.clickOnFDiv();

		cop.scrollTillDspHistory(driver);
		Thread.sleep(3000);
		cop.clickOnDispHistory();
		Thread.sleep(2000);
		cop.clickOnFirstDisputeTab();
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute history")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute history opening is successfull.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute history opening is failed.");
			Assert.assertTrue(false);
		}

		if (cop.getDspHistoryStatusD().equals("Declined")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute declined is successed.");
		} else {
			logger.info("Verification of Dispute declined is failed.");
			Assert.assertTrue(false);
		}
	}
}
