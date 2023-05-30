package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC40_VerifyMergeBreakOrderForDisputeTest extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	public String agentMailMBO=rd.setAgentMailMergeBreakOrder();
	public String agentPassMBO=rd.setAgentPassMergeBreakOrder();
    public String clientMailMBO=rd.setClientMailMergeBreakOrder();
    public String clientPassMBO=rd.setClientPassMergeBreakOrder();
    public String productMBO=rd.setProductMergeBreakOrder();
    public String queries=rd.setQueries();
	public String process=rd.setProcessStatus();
	public String agentAnswer=rd.setAnswer();
	
	public String val1=rd.getVal1();
	public String val2=rd.getVal2();
	public String val3=rd.getVal3();
	public String val4=rd.getVal4();
	public String val5=rd.getVal5();
	public String val6=rd.getVal6();
	public String val7=rd.getVal7();
	public String val8=rd.getVal8();
	public String val9=rd.getVal9();
	
    
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

		aspp.searchProductName(productMBO);
		Thread.sleep(3000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();
		
		String parentWindow=driver.getWindowHandle();
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

		cl.searchProduct(productMBO);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		window = driver.getWindowHandles();
		for(String handle : window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
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
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productMBO);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");
		BaseClass.closeAllWinTabsExceptParent();
	}
	
 	@Test(priority = 2)
 	public void verifyOpenDispute() throws InterruptedException, IOException {
 		driver.get(baseURL);
 		
 		LoginPage lp=new LoginPage(driver);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		Thread.sleep(2000);
		
		logger.info("client logged in Successfully.");
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(productMBO);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		
		cop.clickOnStatusDrop();
		//Thread.sleep(3000);
		cop.dropdownSearch(process);
		//logger.info("fulfilled status is entered.");
		//cop.clickOnFulfillTab();
		//cop.clickOnFProcessingTab();
		//aop.clickOnProcessTab();
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
		
		if(driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		}else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}
		BaseClass.closeAllWinTabsExceptParent();
 	}
 	
 	
 	@Test(enabled = false)
 	public void verifyAcceptDispute() throws InterruptedException, IOException {
 		driver.get(baseURL);
 		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productMBO);
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
			captureScreen(driver, "acceptDispute");
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		
		Thread.sleep(3000);
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(productMBO);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnStatusDrop();
		Thread.sleep(1000);
		cop.dropdownSearch(process);
		cop.clickOnProcessingTab();
		Thread.sleep(2000);
		
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(4000);
		
		cop.scrollTillDspHistory(driver);
		Thread.sleep(3000);
		cop.clickOnDispHistory();
		Thread.sleep(5000);
		logger.info("Verification of open Dispute History successfull.");
		
		if(cop.getDspHistoryStatusA().equals("Accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successed.");
		}else {
			logger.info("Verification of Dispute acceptance is failed.");
			Assert.assertTrue(false);
		}
 	}
 	
 	
 	@Test(priority = 3)
 	public void verifyRequotation() throws InterruptedException, IOException {
 		driver.get(baseURL);
 		
 		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(productMBO);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
				
		String parentWindow=driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		cl.pleaseRequote();
		logger.info("Requoted the quotation");
		Thread.sleep(2000);
		cl.clickOnYesImSure();
		Thread.sleep(3000);
		cl.clickOnClosebtn();
		Thread.sleep(3000);
				
		if(driver.getPageSource().contains("Requote - Bidding")) {
			logger.info("Verification of Client side Requote is Successed.");
			Assert.assertTrue(true);
		}
		else {
			logger.info("Verification of client side Requote is failed.");
			Assert.assertTrue(false);
			Thread.sleep(2000);
		}

		cl.logoutTheClient();
		
		lp.setAdminMailId(agentMailMBO);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(agentPassMBO);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		Thread.sleep(5000);
				
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		aspp.searchProductName(productMBO);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();
		Thread.sleep(4000);

		windowHandles = driver.getWindowHandles();
		for(String handle : windowHandles) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		aspp.sendValFor1Pcs1(val1);
		aspp.sendValFor2Pcs2(val2);
		aspp.sendValFor3Pcs3(val3);
		aspp.sendValFor1Pcs4(val4);
		aspp.sendValFor2Pcs5(val5);
		aspp.sendValFor3Pcs6(val6);
		aspp.sendValFor1Pcs7(val7);
		aspp.sendValFor2Pcs8(val8);
		aspp.sendValFor3Pcs9(val9);
		Thread.sleep(2000);
		
		aspp.clickOnSubmitQuote();
		Thread.sleep(6000);

		if(driver.getPageSource().contains("Quotation done")) {
			logger.info("Verification of Requote from Agent side is Successed.");
			Assert.assertTrue(true);
		}else {
			logger.info("Verification of Requote from Agent side is failed.");
			Assert.assertTrue(false);
		}
		
		if (aspp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit Requotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit Requotation failed..");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		}
		 BaseClass.closeAllWinTabsExceptParent();
 	}
 	
 	@Test(priority = 4)
 	public void acceptRequoteQuotation() throws InterruptedException, IOException {
 		driver.get(baseURL);
 		
 		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(clientMailMBO);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(clientPassMBO);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(productMBO);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		
		String parentWindow=driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		driver.navigate().refresh();
		Thread.sleep(2000);
		cl.selectQuoteTab();
		Thread.sleep(1000);
		cl.scrollTillAcceptQbtn(driver);
		Thread.sleep(1000);
		cl.selectAcceptQuoteBtn();
		logger.info("Clicked on accept quotation button.");
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
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productMBO);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");
		BaseClass.closeAllWinTabsExceptParent();
 	}
 	
 	@Test(enabled = false)
 	public void verifyDisputeIsStillOpenFromAgentSide() throws InterruptedException {
 		driver.get(baseURL);
 		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(agentMailMBO);
		lp.setAdminPassword(agentPassMBO);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productMBO);
		Thread.sleep(3000);
//		asop.clickOnFrstDsp();
//		Thread.sleep(3000);
//		asop.clickOnShowDsp();
//		logger.info("Clicked on show disputes.");
//		Thread.sleep(3000);
		
		asop.clickOnEachDisputeAgentSide(driver);
		logger.info("Verification of show dispute from Agent side.");
		
 	}
 	
 	@Test(priority = 6)
 	public void verifyDisputeIsStillOpenFromClientSide() throws InterruptedException {
 		driver.get(baseURL);
 		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(clientMailMBO);
		lp.setAdminPassword(clientPassMBO);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");                                       
		Thread.sleep(3000);
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(productMBO);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		
		cop.clickOnStatusDrop();
		Thread.sleep(1000);
		cop.dropdownSearch(process);
		cop.clickOnProcessingTab();
		Thread.sleep(2000);
		
//		cop.clickOnFDiv();		
//		logger.info("Clicked on first div.");
//		Thread.sleep(4000);
//		
//		cop.scrollTillEle(driver);
//		Thread.sleep(3000);
//		
//		cop.clickOnShowDispute();
//		logger.info("Clicked on show disputes.");
//		Thread.sleep(3000);
		
		cop.clickOnEachDivForDisputeVerification(driver);
		Thread.sleep(2000);
		
		logger.info("Verification of show disputes successfull from client side.");
 	}
}
