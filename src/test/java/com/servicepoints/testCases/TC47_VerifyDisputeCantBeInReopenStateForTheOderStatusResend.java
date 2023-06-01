package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC47_VerifyDisputeCantBeInReopenStateForTheOderStatusResend extends BaseClass{
	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String product47 = rd.productForTC47();

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

	@Test(priority = 1)
	public void submitAndAcceptQuotation() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(agentMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentPassMBO);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product47);
		Thread.sleep(3000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		aspp.scrollTillEle(driver);
		Thread.sleep(1000);
		aspp.clickOnSubmitQuote();
		Thread.sleep(7000);

		if (aspp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}

		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product47);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		window = driver.getWindowHandles();
		for (String handle : window) {
			if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}

		cl.selectQuoteTab();
		Thread.sleep(1000);
		cl.scrollTillAcceptQbtn(driver);
		Thread.sleep(1000);
		cl.selectAcceptQuoteBtn();
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");

		} else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 2)
	public void verifyOpenDispute() throws InterruptedException, IOException {
		driver.get(baseURL);
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

		aop.searchPnameTrack(product47);
		logger.info("Product name is entered.");
		Thread.sleep(3000);

		wait.until(ExpectedConditions.visibilityOf(aop.orderStatusDrop));
		aop.clickOnStatusDrop();
		logger.info("Clicked on status dropdown.");
		Thread.sleep(3000);

		aop.setStatusSearchDrop(process);
		logger.info("Processing status is searched.");
		Thread.sleep(2000);

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
		Thread.sleep(7000);

		if (driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			Assert.assertTrue(false);
			logger.info("Verification of adding tracking number is failed.");
		}
	}
	
	@Test(priority = 3)
	public void verifyRaisingAndAcceptingDisputeAgain() throws InterruptedException, IOException {
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		logger.info("client logged in Successfully.");
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(product47);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

//		cop.clickOnStatusDrop();
//		Thread.sleep(3000);
//		cop.dropdownSearch(process);
//		logger.info("fulfilled status is entered.");
//		cop.clickOnFulfillTab();
//		cop.clickOnFProcessingTab();
//		aop.clickOnProcessTab();
//		cop.clickOnProcessingTab();
//		Thread.sleep(3000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(1000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.resendSolutionDsp();
		logger.info("Resend dispute option is selected.");

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
			captureScreen(driver, "dispute Raised again");
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

		asop.searchProductForDsp(product47);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		asop.selectDspStatusToDeclined();
		logger.info("Dispute Declined Status selected from dropdown.");
		Thread.sleep(1000);

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
	}
	
	@Test(priority = 4)
	public void verifyRaiseDisputeAndAccept() throws InterruptedException, IOException {
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
		cop.sendPnameinSearch(product47);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

//		cop.clickOnStatusDrop();
//		Thread.sleep(3000);
//		cop.dropdownSearch(process);
//		logger.info("fulfilled status is entered.");
//		cop.clickOnFulfillTab();
//		cop.clickOnFProcessingTab();
//		aop.clickOnProcessTab();
//		cop.clickOnProcessingTab();
//		Thread.sleep(3000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(1000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.resendSolutionDsp();
		logger.info("Resend dispute option is selected.");

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
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(product47);
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
			captureScreen(driver, "Dispute for resend");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}
	
	
	@Test(priority = 5)
	public void verifyTryToReopenResendDispute() throws InterruptedException, IOException {
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
		cop.sendPnameinSearch(product47);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		if(cop.verifyOpenDisputeButtonIsVisible()==true) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Dispute for Resend is not able to reopen once accepted is successed.");
		}
		else {
			captureScreen(driver, "Dispute for resend reopen.");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
		
		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.refundSolutionDsp();
		logger.info("Resend dispute option is selected.");

		cop.clickOnCheckBox();
		Thread.sleep(3000);

		cop.sendQueries(queries);
		logger.info("Queries entered in text fields.");

		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");

		if (driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Refund Dispute raised Successfully.");
		} else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Refund Dispute raised failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(product47);
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
			logger.info("Verification of Refund Dispute acceptance is successed.");
		}else {
			captureScreen(driver, "Dispute for resend");
			logger.info("Verification of Refund Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 6)
	public void verifyRaiseDisputeForrRefund() throws InterruptedException, IOException {
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
		cop.sendPnameinSearch(product47);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		if(cop.verifyOpenDisputeButtonIsVisible()==false) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of not reopening of Dispute for refund is successed.");
		}
		else {
			captureScreen(driver, "Dispute for refund reopen.");
			logger.info("Verification of not reopening of Dispute for refund is failed.");
			Assert.assertTrue(false);
		}
	}
}
