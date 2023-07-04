package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.TeamleaderDisputePage;
import com.servicepoints.utilities.ReadConfig;

public class TC49_VerifyCloseDisputeAfterCancelOrderAndDeclinedTheDispute extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String product49 = rd.productForTC49();

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

		aspp.searchProductName(product49);
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
		logger.info("Client logged in successfully.");

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product49);
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
		cop.sendPnameinSearch(product49);
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

		asop.searchProductForDsp(product49);
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
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Dispute rejection is successed.");
		} else {
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute rejection is failed.");
			AssertJUnit.assertTrue(false);
		}
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(enabled = true, priority = 3)
	public void verifyCancelOrder() throws InterruptedException, IOException {

		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(2000);

		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		logger.info("Clicked on orders tab.");
		Thread.sleep(2000);
		cop.sendPnameinSearch(product49);
		Thread.sleep(4000);

		cop.clickOnDropdown();
		Thread.sleep(2000);

		cop.dropdownSearch(status2);
		Thread.sleep(4000);
		cop.clickOnProcessingSel();
		Thread.sleep(2000);
		logger.info("Processing filter selected.");

		cop.clickOnFDiv();
		Thread.sleep(2000);

		cop.scrollTillEle(driver);
		Thread.sleep(1000);

		cop.clickOnCancelBtn();
		logger.info("Clicked on cancel order.");
		Thread.sleep(2000);

		cop.clickOnFirstCheckbox();
		Thread.sleep(2000);
		logger.info("Clicked on first checkbox.");

		cop.clickOnSubmitOrder();
		Thread.sleep(2000);
		logger.info("Clicked on Submit order.");

		cop.clickOnCancelOrderSuccessBtn();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Order cancelled successfully")) {
			AssertJUnit.assertTrue(true);
			logger.info("Verification of cancel one variant is Successed.");
		} else {
			captureScreen(driver, "Cancel order in dispute");
			Thread.sleep(4000);
			logger.info("Verification of cancel one variant is failed.");
			AssertJUnit.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 4)
	public void verifyCloseDisputeFromSupplierSide() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		Thread.sleep(2000);

		cop.clickOnGoToDisputesTab();
		Thread.sleep(2000);

		cop.clickOnDeclinedDisputesTab();
		logger.info("Clicked on Declined dispute tab.");
		Thread.sleep(1000);

		cop.sendPnameinSearch(product49);
		logger.info("Product name searched.");
		Thread.sleep(2000);

		cop.clickOnFDiv();
		Thread.sleep(2000);

		cop.scrollTillShowDisputeBtn(driver);
		Thread.sleep(2000);

		cop.clickOnShowDispute();
		Thread.sleep(5000);

		logger.info("Clicked on Show Dispute.");
		logger.info("Verify from client side");
	}

	@Test(enabled = true, priority = 5)
	public void verifyShowDisputeFromSupportSide() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(agentSpMail);
		lp.setAdminPassword(agentSpPass);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(product49);
		Thread.sleep(2000);
		logger.info("Product name searched.");

		asop.clickOnDeclinedDispute();
		Thread.sleep(1000);
		logger.info("Clicked on declined dispute.");

		asop.clickOnAllStoreBtn();
		asop.sendStoreNameInStoreFilter(storeFilter);
		Thread.sleep(1000);
		logger.info("Store name entered.");

		asop.clickOnFStoreEle();
		Thread.sleep(3000);

		asop.clickOnEachDisputeAgentSide(driver);
		Thread.sleep(5000);
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

		tdp.clickOnDeclinedDispute();
		Thread.sleep(1000);
		logger.info("Clicked on decline dispute tab.");

		tdp.searchProduct(product49);
		logger.info("Product name searched.");
		Thread.sleep(1000);

		tdp.clickOnAllStoreFilter();
		Thread.sleep(2000);

		tdp.searchStoreFilter(storeFilter);
		Thread.sleep(1000);

		tdp.clickOnFStoreTab();
		logger.info("Store filter handled.");
		Thread.sleep(1000);

		tdp.handleEachDispute(driver);
		Thread.sleep(5000);
	}
}
