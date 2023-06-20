package com.servicepoints.testCases;

import java.awt.AWTException;
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

public class TC63_VerifyGroupByFunctionality extends BaseClass{

	
	ReadConfig rd = new ReadConfig();
	public String product63 = rd.getProductForTC63();

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
	public String variantType=rd.getGroupByVariantType();

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

		aspp.searchProductName(product63);
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
		
		aspp.verifyGroupByFunction(variantType);
		Thread.sleep(2000);

		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered for all pieces.");
		Thread.sleep(4000);

		aspp.scrollTillEle(driver);
		Thread.sleep(3000);
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

		cl.searchProduct(product63);
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
		Thread.sleep(3000);

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
	public void verifyRequoteQuotation() throws InterruptedException, IOException, AWTException {
		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clientMailD);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(clientPassD);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(product63);
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
		
		lp.setAdminMailId(agentMailD);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(agentPassD);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		Thread.sleep(5000);
				
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		aspp.searchProductName(product63);
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
		
		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);
		
		
		aspp.scrollTillSubmitQuotationBtn(driver);
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
	
	@Test(enabled = true, priority = 3)
	public void verifyAcceptingRequoteQuote() throws InterruptedException, IOException {
		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(clientMailD);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(clientPassD);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(product63);
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
		Thread.sleep(3000);
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
		cp.sendPnameinSearch(product63);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");
	}
}
