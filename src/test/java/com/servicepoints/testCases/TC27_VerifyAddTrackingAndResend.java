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

public class TC27_VerifyAddTrackingAndResend extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agmail = rd.setAgentTrackMail();
	public String agpass = rd.setAgentTrackPass();
	public String productResend = rd.setProductResend();
	public String process = rd.setProcessStatus();
	public String trackingNum = rd.setTrackingNum();
	public String clmail = rd.setClientTrackMail();
	public String clpass = rd.setClientTrackPass();
	public String fulfillStatus = rd.setFullfill();
	public String agentAnswer = rd.setAnswer();
	public String queries = rd.setQueries();

	@Test
	public void verifyAddTrackingAndResendProduct() throws InterruptedException, IOException {
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

		aop.searchPnameTrack(productResend);
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

		cop.sendPnameinSearch(productResend);
		Thread.sleep(3000);
		logger.info("Product name is entered.");

		cop.clickOnStatusDrop();
		cop.dropdownSearch(process);

		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		cop.clickOnFDiv();

		if (driver.getPageSource().contains("No orders found ")) {
			cop.clickOnStatusDrop();
			Thread.sleep(1000);
			cop.dropdownSearch(fulfillStatus);
			Thread.sleep(1000);
			cop.clickOnFulfillTab();
			Thread.sleep(1000);
			cop.clickOnFDiv();
			Thread.sleep(1000);
		}

		logger.info("clicked on first div");

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(2000);

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
			logger.info("Verification of Dispute raised for resend Successfully.");
			Thread.sleep(3000);
		} else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised for resend  failed.");
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

		asop.searchProductForDsp(productResend);
		Thread.sleep(3000);

		asop.clickOnFrstDsp();
		Thread.sleep(3000);

		String exp="Open dispute - Message received";
		
		String act=asop.getDisputeStatus();
		Assert.assertEquals(act, exp);
		
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

		driver.get(baseURL);

		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		cop.clickOnOrdersTab();
		Thread.sleep(3000);

		cop.sendPnameinSearch(productResend);
		Thread.sleep(3000);
		logger.info("Product name is entered.");

		cop.clickOnStatusDrop();
		// Thread.sleep(3000);
		cop.dropdownSearch(process);
		// logger.info("fulfilled status is entered.");
		// cop.clickOnFulfillTab();
		// cop.clickOnFProcessingTab();
		// aop.clickOnProcessTab();
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

		if (cop.getDspHistoryStatusA().equals("Accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successed.");
		} else {
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}
}
