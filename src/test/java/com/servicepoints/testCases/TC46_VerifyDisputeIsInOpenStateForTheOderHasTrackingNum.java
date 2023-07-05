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

public class TC46_VerifyDisputeIsInOpenStateForTheOderHasTrackingNum extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String productForTC46 = rd.productForTC46();

	public String queries = rd.setQueries();
	public String process = rd.setProcessStatus();
	public String agentAnswer = rd.setAnswer();
	public String otherTxt = rd.setOtherTxt();

	public String agentAns2 = rd.getAgentAns2();
	public String query2 = rd.getQuery2();
	public String query3 = rd.getQuery3();
	public String query4 = rd.getQuery4();
	public String query5 = rd.getQuery5();
	public String query6 = rd.getQuery6();

	public String trackingNum = rd.setTrackingNum();

	@Test(enabled = true, priority = 1)
	public void verifyAddTrackingNumberAndReopenDeclinedDispute() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(productForTC46);
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
		// aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));

		aop.clickOnSbmtTracking();
		logger.info("Clicked on submit tracking button.");
		// wait.until(ExpectedConditions.visibi);
		aop.waitTillSuccessBoxOfTrackingNum(driver);

		if (driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of adding tracking number is failed.");
		}
	}

	@Test(enabled = true, priority = 2)
	public void verifySubmitAndDeclinedDisputeAndReopenIt() throws InterruptedException, IOException {
		driver.get(baseURL);

		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		logger.info("client logged in Successfully.");
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(productForTC46);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		// cop.clickOnStatusDrop();
		// Thread.sleep(3000);
		// cop.dropdownSearch(process);
		// logger.info("fulfilled status is entered.");
		// cop.clickOnFulfillTab();
		// cop.clickOnFProcessingTab();
		// aop.clickOnProcessTab();
		// cop.clickOnProcessingTab();
		// Thread.sleep(3000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(1000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.refundSolutionDsp();
		logger.info("I want the order to be shipped immediately option is selected.");

		cop.clickOnCheckBox();
		Thread.sleep(3000);

		cop.sendQueries(queries);
		logger.info("Queries entered in text fields.");

		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");

		if (driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		} else {
			captureScreen(driver, "Dispute Reopen");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);

		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);

		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query2);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message send on closed dispute is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		asop.sendAnswer(agentAns2);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);

		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query3);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message send on closed dispute is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		asop.sendAnswer(agentAns2);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);

		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query4);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message send on closed dispute is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		asop.sendAnswer(agentAns2);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);
		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query5);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message send on closed dispute is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		asop.sendAnswer(agentAns2);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);
		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query6);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Message send successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message send on closed dispute is failed.");
		}

		driver.get(baseURL);

		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(5000);

		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(productForTC46);
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

		asop.sendAnswer(agentAns2);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute declined successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		cop.clickOnOrdersTab();
		Thread.sleep(2000);
		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);
		cop.clickOnDeclinedDisputesTab();
		Thread.sleep(1000);
		cop.sendPnameinSearch(productForTC46);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cop.clickOnShowDispute();
		Thread.sleep(1000);
		cop.sendQueries(query6);
		Thread.sleep(1000);
		cop.SaveDispute();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("You have reached the limits of reopen dispute.")) {
			Assert.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message that you have reached the limits of reopen dispute is successfull.");
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of message that you have reached the limits of reopen dispute is failed.");
		}

	}
}
