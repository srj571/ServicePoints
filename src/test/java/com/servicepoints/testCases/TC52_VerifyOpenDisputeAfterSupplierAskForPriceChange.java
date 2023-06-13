package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC52_VerifyOpenDisputeAfterSupplierAskForPriceChange extends BaseClass{

	ReadConfig rd = new ReadConfig();
	public String agentMailMBO = rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO = rd.setAgentPassMergeBreakOrder();
	public String clientMailMBO = rd.setClientMailMergeBreakOrder();
	public String clientPassMBO = rd.setClientPassMergeBreakOrder();

	public String product52 = rd.getProductForTC52();

	public String queries = rd.setQueries();
	public String process = rd.setProcessStatus();
	public String agentAnswer = rd.setAnswer();
	public String otherTxt = rd.setOtherTxt();
	public String query2 = rd.getQuery2();
	public String status2=rd.setOrderStatus2();
	
	public String agentSpMail=rd.getAgentSpMailDsp();
	public String agentSpPass=rd.getAgentSpPassDsp();
	public String teamleaderName=rd.getTeamleaderName();

	public String storeFilter=rd.storeForDisputeFilter();
	
	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();
	
	public String val1=rd.getVal1();
	public String val2=rd.getVal2();
	public String val3=rd.getVal3();
	public String val4=rd.getVal4();
	public String val5=rd.getVal5();
	public String val6=rd.getVal6();
	public String val7=rd.getVal7();
	public String val8=rd.getVal8();
	public String val9=rd.getVal9();
	
	
	@Test(enabled = false)
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

		aspp.searchProductName(product52);
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

		cl.searchProduct(product52);
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

	@Test(enabled = false)
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
		cop.sendPnameinSearch(product52);
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

	
	@Test(enabled = false)
	public void verifyAskForPriceChange() throws InterruptedException, IOException {
		driver.get(baseURL);
		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		
		LoginPage lp = new LoginPage(driver);
		
		ClientProductPage cl = new ClientProductPage(driver);
		
		//WebDriverWait wait=new WebDriverWait(driver, 5);
		
		lp.setAdminMailId(agentMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(agentPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(3000);

		asop.getProductsPage();
		Thread.sleep(4000);

		asop.clickOnProductsTab();
		Thread.sleep(1000);
		asop.searchProductName(product52);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		
		asop.clickOnfdiv();
		Thread.sleep(2000);

		String parentWindow=driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		
		for(String handle:window) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		asop.scrollTillAskForPrChange(driver);
		Thread.sleep(1000);
		
		asop.clckOnAskForPrceChng();
		logger.info("Click on Ask for Price changed.");
		Thread.sleep(2000);

		asop.sendValFor1Pcs1(val1);
		asop.sendValFor2Pcs2(val2);
		asop.sendValFor3Pcs3(val3);
		asop.sendValFor1Pcs4(val4);
		asop.sendValFor2Pcs5(val5);
		asop.sendValFor3Pcs6(val6);
		asop.sendValFor1Pcs7(val7);
		asop.sendValFor2Pcs8(val8);
		asop.sendValFor3Pcs9(val9);
		Thread.sleep(2000);
		
//		asop.firstPcsPrice(c1price);
//		Thread.sleep(1000);
//		asop.secPcsPrice(c2price);
//		Thread.sleep(1000);
//		asop.thirdPcsPrice(c3price);
//		Thread.sleep(1000);
//		asop.forthPcsPrice(c4price);
//		Thread.sleep(1000);
		
		
		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(1000);
		asop.clickOnSbmtNewPrice();
		logger.info("Entered changed price and Clicked on submit.");
		
		asop.closeNotifyPopUp();
		logger.info("Pop up get closed.");
		Thread.sleep(3000);
		
		if (driver.getPageSource().contains("New price")) {
			logger.info("Status changed to New Price.");
		}
		Thread.sleep(3000);
		
		driver.get(baseURL);
		logger.info("Logged out from Agent account.");
		Thread.sleep(4000);
		
		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client loged in successfully.");

		cl.getProductsPage();
		Thread.sleep(2000);

		cl.searchProduct(product52);
		Thread.sleep(2000);
		
		cl.selectProductTab();
		logger.info("Product selected.");
		Thread.sleep(2000);
		
		window = driver.getWindowHandles();
		for(String handle: window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
	
		cl.waitTillCloseBtnVisible(driver);
		
		cl.closePopUpFrmClntSideAskPr();
		Thread.sleep(3000);
		logger.info("Pop up closed.");
	
		logger.info("Now accepting the quotation.");
		
		cl.acceptAskforPriceChange();
		Thread.sleep(2000);
		
		logger.info("Changed price is get accepted by the Client.");
		cl.clickOnYesImSure();
		logger.info("Clicked on yes im sure");
		Thread.sleep(2000);
		cl.closeThankUPopUp();
		logger.info("Now verification is to be done.");
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Quotation accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification is done from Client side for Ask for Price change test.");
		}else {
			captureScreen(driver, "askForPriceChange");
			logger.info("Verification is for Ask for Price change test is failed.");
			Assert.assertTrue(false);
		}
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(4000);
		cp.sendPnameinSearch(product52);
		Thread.sleep(4000);
		cp.clickOnEachDivForDisputeVerification(driver);
		Thread.sleep(5000);
		logger.info("Status changed to Processing.");
	}
	
	@Test(priority = 4)
	public void verifyByClient() throws InterruptedException {
		driver.get(baseURL);
		logger.info("Logged out from Agent account.");
		Thread.sleep(4000);
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client loged in successfully.");
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(4000);
		
//		cp.clickOnGoToDisputesTab();
//		Thread.sleep(2000);
		
		cp.sendPnameinSearch(product52);
		Thread.sleep(2000);
		cp.clickOnEachDivForDisputeVerification(driver);
		Thread.sleep(5000);
		logger.info("Status changed to Processing.");
	}
}
