package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.awt.AWTException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.TeamleaderDisputePage;
import com.servicepoints.utilities.ReadConfig;

public class TC53_VerifyRaiseDisputeAfterClientStopsFulfillment extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String product53 = rd.getProductForTC53();

	public String queries = rd.setQueries();
	public String process = rd.setProcessStatus();
	public String agentAnswer = rd.setAnswer();
	public String otherTxt = rd.setOtherTxt();
	public String query2 = rd.getQuery2();
	public String status2 = rd.setOrderStatus2();

	public String agentSpMail = rd.getAgentSpMailDsp();
	public String agentSpPass = rd.getAgentSpPassDsp();
	public String teamleaderName = rd.getTeamleaderName();
	public String storeFilter = rd.storeForDisputeFilter();

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
		logger.info("Logged in to the Agent supplier account.");

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product53);
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
		logger.info("Price entered for all pieces.");
		Thread.sleep(4000);

		aspp.scrollTillEle(driver);
		Thread.sleep(1000);
		aspp.clickOnSubmitQuote();
		logger.info("Clicked on submit quotation button.");
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
		logger.info("Client logged in successfully.");

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product53);
		Thread.sleep(4000);
		logger.info("Product name searched.");

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
		logger.info("Clicked on Accept Quotation button.");

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
		cop.sendPnameinSearch(product53);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		cop.clickOnStatusDrop();
		cop.dropdownSearch(process);
		cop.clickOnProcessingTab();
		Thread.sleep(3000);
		logger.info("Processing filter selected.");

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillEle(driver);
		Thread.sleep(1000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);
		logger.info("Clicked on Open dispute button.");

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
		logger.info("Clicked on Saved dispute.");

		if (driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		} else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 3)
	public void verifyStopFulfillment() throws InterruptedException, IOException, AWTException {
		driver.get(baseURL);

		ClientProductPage cl = new ClientProductPage(driver);
		LoginPage lp = new LoginPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);

		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client login successed.");

		cl.getProductsPage();

		cl.searchProduct(product53);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}

		cl.scrollTillSpRequestBtn(driver);
		Thread.sleep(3000);

		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);

		cl.stopFullfilling();
		Thread.sleep(2000);
		logger.info("Clicked on Stop fullfillment");

		cl.clickOnYesImSure();
		Thread.sleep(2000);

		cl.clickOnClosebtn();
		Thread.sleep(2000);

		if (driver.getPageSource().contains("Stop fullfilment")) {
			logger.info("Verification of Stop fullfilment of Quotation is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			captureScreen(driver, "Stop fullfilling");
			logger.info("Verification of Stop fullfillment of Quotation is Failed.");
			Assert.assertTrue(false);
		}

		ClientOrdersPage cp = new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(product53);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(1000);

		cp.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);
		cp.clickOnShowDispute();
		Thread.sleep(4000);

		logger.info("Status changed to Processing.");
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(enabled = true, priority = 4)
	public void verifyDisputeOnSupplierSide() throws InterruptedException, IOException {

		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(agentMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Agent login successed.");

		AgentDisputesPage adp = new AgentDisputesPage(driver);

		adp.clickOnDisputesTab();
		Thread.sleep(1000);
		adp.searchProductForDsp(product53);
		Thread.sleep(2000);

		if (driver.getPageSource().contains("No disputes found")) {
			logger.info("Verification of dispute from client side is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			captureScreen(driver, "Stop fullfilling and verify dsp");
			logger.info("Verification of dispute from client side is Failed.");
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 5)
	public void verifyDisputeOnSupportSide() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(agentSpMail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentSpPass);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Agent login successed.");

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(product53);
		Thread.sleep(2000);

		asop.clickOnAllStoreBtn();
		asop.sendStoreNameInStoreFilter(storeFilter);
		Thread.sleep(1000);
		logger.info("Store name entered.");

		asop.clickOnFStoreEle();
		Thread.sleep(3000);

		asop.clickOnFrstDsp();
		Thread.sleep(1000);

		if (asop.visibilityOfShowBtn() == true) {
			logger.info("Verification of dispute from support side is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			logger.info("Verification of dispute from support side is Failed.");
			Assert.assertTrue(false);
		}

		asop.scrollTillShowDispute(driver);
		Thread.sleep(2000);

		asop.clickOnShowDsp();
		Thread.sleep(5000);
		logger.info("Product name searched.");
	}

	@Test(enabled = true, priority = 6)
	public void verifyShowDisputeFromTeamleaderSide() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(AdminMailID);
		lp.setAdminPassword(AdminPassword);
		lp.clickLoginbtn();
		Thread.sleep(2000);
		logger.info("Admin logged in successfully.");

		AdminAccountsPage adminAccount = new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);

		adminAccount.enterUserName(teamleaderName);
		logger.info("Entered Client name in search field.");

		adminAccount.getTeamleaderTab();
		Thread.sleep(2000);

		adminAccount.clickOnLoginBtn();
		Thread.sleep(3000);
		logger.info("Teamleader logged in successfully.");

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(3000);

		TeamleaderDisputePage tdp = new TeamleaderDisputePage(driver);
		tdp.clickOnDisputeTab();
		Thread.sleep(1000);

		tdp.searchProduct(product53);
		logger.info("Product name searched.");
		Thread.sleep(1000);

		tdp.clickOnFDiv();
		Thread.sleep(2000);

		tdp.scrollTillShowDispute(driver);
		Thread.sleep(2000);

		tdp.clickOnShowDsp();
		Thread.sleep(5000);
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(enabled = true, priority = 7)
	public void verifyStartFulfillment() throws InterruptedException, IOException {
		driver.get(baseURL);
		Thread.sleep(3000);
		LoginPage lp = new LoginPage(driver);
		ClientProductPage cl = new ClientProductPage(driver);
		ClientOrdersPage cp = new ClientOrdersPage(driver);

		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();

		cl.searchProduct(product53);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}

		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		cl.startFulfillingDropbtn();
		logger.info("Clicked on Start Fullfillment.");
		Thread.sleep(3000);
		cl.clickOnPreviousFulfill();
		Thread.sleep(2000);
		cl.clickOnStartfulfillbtn();
		Thread.sleep(3000);
		cl.clickOnClosebtn();
		Thread.sleep(3000);

		if (cl.checkEleIsDisabled() == false) {
			Thread.sleep(2000);
			logger.info("Verification of Start fullfilment of Quotation is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			captureScreen(driver, "Start fullfilling");
			logger.info("Verification of Start fullfilment of Quotation is failed.");
			Assert.assertTrue(false);
		}

		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(product53);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");

		cp.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);

		cp.clickOnShowDispute();
		Thread.sleep(4000);
	}

}
