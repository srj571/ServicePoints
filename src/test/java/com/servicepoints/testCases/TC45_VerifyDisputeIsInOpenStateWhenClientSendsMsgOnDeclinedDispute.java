package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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

public class TC45_VerifyDisputeIsInOpenStateWhenClientSendsMsgOnDeclinedDispute extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String productForTC45 = rd.productForTC45();

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

	@Test(enabled = true, priority = 1)
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

		aspp.searchProductName(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			AssertJUnit.assertTrue(false);
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

		cl.searchProduct(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");

		} else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of accepting quotation is Failed.");
			AssertJUnit.assertTrue(false);
		}
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(enabled = true, priority = 2)
	public void verifyOpenDispute() throws InterruptedException, IOException {
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
		cop.sendPnameinSearch(productForTC45);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

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
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillEle(driver);
		Thread.sleep(1000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.handleDspSolution();
		logger.info("I want the order to be shipped immediately option is selected.");

		cop.clickOnCheckBox();
		Thread.sleep(3000);

		cop.sendQueries(queries);
		logger.info("Queries entered in text fields.");

		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");

		if (driver.getPageSource().contains("Dispute raised successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		} else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised failed.");
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message send on closed dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
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

		asop.searchProductForDsp(productForTC45);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
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
		cop.sendPnameinSearch(productForTC45);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
			logger.info("Verification of message that you have reached the limits of reopen dispute is successfull.");
		} else {
			AssertJUnit.assertTrue(false);
			logger.info("Verification of message that you have reached the limits of reopen dispute is failed.");
		}

	}
}
