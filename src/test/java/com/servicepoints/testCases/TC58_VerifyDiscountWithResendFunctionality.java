package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC58_VerifyDiscountWithResendFunctionality extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String product58 = rd.getProductForTC58();

	public String queries = rd.setQueries();
	public String process = rd.setProcessStatus();
	public String agentAnswer = rd.setAnswer();
	public String otherTxt = rd.setOtherTxt();
	public String query2 = rd.getQuery2();
	public String status2 = rd.setOrderStatus2();

	public String clientMailD = rd.getClientMailforDiscount();
	public String clientPassD = rd.getClientPassforDiscount();
	public String agentMailD = rd.getAgentMailforDiscount();
	public String agentPassD = rd.getAgentPassforDiscount();

	public String trackingNum = rd.setTrackingNum();

	public String storeFilter = rd.storeForDisputeFilter();
	String formattedPrice;

	@Test(enabled = true, priority = 1)
	public void submitAndAcceptQuotation() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(agentMailD);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentPassD);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Logged in to the Agent supplier account.");

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);

		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product58);
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

		lp.setAdminMailId(clientMailD);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassD);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client logged in successfully.");

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product58);
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
	public void verifyAddTrackingOnProduct() throws InterruptedException, IOException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ClientOrdersPage cop = new ClientOrdersPage(driver);

		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(product58);
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
		Thread.sleep(4000);
		logger.info("clicked on add tracking button.");

		aop.setTrackingNum(trackingNum);
		// aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));

		aop.clickOnSbmtTracking();
		Thread.sleep(3000);
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
	}

	@Test(enabled = true, priority = 3)
	public void raiseResendDispute() throws InterruptedException, IOException {
		driver.get(baseURL);

		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailD);
		lp.setAdminPassword(clientPassD);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		logger.info("client logged in Successfully.");
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(product58);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		if (cop.verifyOpenDisputeButtonIsVisible() == true) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Dispute for Refund is not able to reopen once accepted is successed.");
		} else {
			captureScreen(driver, "Dispute for resend reopen.");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.resendSolutionDsp();
		logger.info("Refund dispute option is selected.");

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
		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(product58);
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

		asop.scrollTillSendAns(driver);
		Thread.sleep(1000);

		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Refund Dispute acceptance is successed.");
		} else {
			captureScreen(driver, "Dispute for resend");
			logger.info("Verification of Refund Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 4)
	public void verifyAddTrackingOnProductAgain() throws InterruptedException, IOException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ClientOrdersPage cop = new ClientOrdersPage(driver);

		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(product58);
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
		Thread.sleep(4000);
		logger.info("clicked on add tracking button.");

		aop.setTrackingNum(trackingNum);
		// aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		// wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));

		aop.clickOnSbmtTracking();
		Thread.sleep(3000);
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
	}

	@Test(enabled = true, priority = 5)
	public void verifyAddingDiscount() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		WebDriverWait wait = new WebDriverWait(driver, 10);
		ClientOrdersPage cop = new ClientOrdersPage(driver);

		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);

		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);

		aop.searchPnameTrack(product58);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(aop.fdiv));
		aop.clickOnfDiv();
		Thread.sleep(3000);

		aop.scrollTillDiscountbtn(driver);
		Thread.sleep(2000);

		aop.clickOnDiscountBtn();
		Thread.sleep(2000);

		aop.verifyErrorMessages(driver);

		double val = aop.generateTheDiscountedPrice();
		Thread.sleep(2000);

		String amountAsString = String.valueOf(val);
		Thread.sleep(1000);
		
		formattedPrice = String.format("%.2f", val);


		aop.enterDiscountAmountField(formattedPrice);
		Thread.sleep(2000);

		aop.clickOnSubmitDiscountBtn();
		Thread.sleep(3000);

		aop.clickOnSuccessDb();
		Thread.sleep(2000);

		if (driver.getPageSource().contains("Discount successfully submitted")) {
			logger.info("Verification of adding discount number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			logger.info("Verification of adding discount number is failed.");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 6, invocationCount = 3)
	public void raiseRefundDispute() throws InterruptedException, IOException {
		driver.get(baseURL);

		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailD);
		lp.setAdminPassword(clientPassD);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		logger.info("client logged in Successfully.");
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(product58);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		if (cop.verifyOpenDisputeButtonIsVisible() == true) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Dispute for Refund is not able to reopen once accepted is successed.");
		} else {
			captureScreen(driver, "Dispute for resend reopen.");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}

		cop.scrollTillOpenDisputesBtn(driver);
		Thread.sleep(2000);

		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);

		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");

		cop.refundSolutionDsp();
		logger.info("Refund dispute option is selected.");

		cop.clickOnFirstCheckBoxForDsp();
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
		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");

		asop.searchProductForDsp(product58);
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

		asop.scrollTillSendAns(driver);
		Thread.sleep(1000);

		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
			Thread.sleep(3000);
			logger.info("Verification of Refund Dispute acceptance is successed.");
		} else {
			captureScreen(driver, "Dispute for refund");
			logger.info("Verification of Refund Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
	}

	@Test(enabled = true, priority = 7)
	public void verifyDiscountFromClientSide() throws InterruptedException {
		driver.get(baseURL);

		LoginPage lp = new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailD);
		lp.setAdminPassword(clientPassD);
		lp.clickLoginbtn();
		Thread.sleep(2000);

		logger.info("client logged in Successfully.");
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(product58);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(4000);
		
	
	}

}
