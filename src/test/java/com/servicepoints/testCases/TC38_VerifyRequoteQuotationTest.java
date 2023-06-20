package com.servicepoints.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC38_VerifyRequoteQuotationTest extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String cmailSsf=con.setClientMailForFulfilment();
	public String cpassSsf=con.setClientPassForFulfilment();
	public String agentMailSsf=con.setAgentMailSsf();
	public String agentPassSsf=con.setAgentPassSsf();
	public String productSsf=con.setProductSSF();

	@Test(priority = 1)
	public void verifyRequoteQuotation() throws InterruptedException, IOException, AWTException {
		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(cmailSsf);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(cpassSsf);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(productSsf);
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
		
		lp.setAdminMailId(agentMailSsf);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(agentPassSsf);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		Thread.sleep(5000);
				
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		aspp.searchProductName(productSsf);
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
	}
	
	
//	@Test()
//	public void acceptRequoteQuotation() throws InterruptedException, IOException {
//		ClientProductPage cl = new ClientProductPage(driver);
//		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
//		LoginPage lp=new LoginPage(driver);
//		driver.get(baseURL);
//		Thread.sleep(3000);
//		lp.setAdminMailId(cmailSsf);
//		logger.info("Email_id is entered.");
//		Thread.sleep(1000);
//				
//		lp.setAdminPassword(cpassSsf);
//		logger.info("Password is entered.");
//		Thread.sleep(1000);
//				
//		lp.clickLoginbtn();
//		Thread.sleep(4000);
//		cl.getProductsPage();
//				
//		cl.searchProduct(productSsf);
//		Thread.sleep(4000);
//		cl.selectProductTab();
//		Thread.sleep(3000);
//		
//		
//		String parentWindow=driver.getWindowHandle();
//		Set<String> windowHandles = driver.getWindowHandles();
//		for(String handle: windowHandles) {
//			if(!handle.equals(parentWindow)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
//		driver.navigate().refresh();
//		Thread.sleep(2000);
//		cl.selectQuoteTab();
//		Thread.sleep(1000);
//		cl.scrollTillAcceptQbtn(driver);
//		Thread.sleep(1000);
//		cl.selectAcceptQuoteBtn();
//		logger.info("Clicked on accept quotation button.");
//		Thread.sleep(4000);
//
//		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
//			Thread.sleep(4000);
//			Assert.assertTrue(true);
//			logger.info("Verification of accepting quotation is Successed.");
//		
//		} else {
//			captureScreen(driver, "Quotation Accepting");
//			logger.info("Verification of accepting quotation is Failed.");
//			Assert.assertTrue(false);
//		}
//		
//		ClientOrdersPage cp=new ClientOrdersPage(driver);
//		cp.clickOnOrdersTab();
//		logger.info("Go to Orders page.");
//		Thread.sleep(2000);
//		cp.sendPnameinSearch(productSsf);
//		Thread.sleep(2000);
//		cp.clickOnFDiv();
//		Thread.sleep(2000);
//		logger.info("Status changed to Processing.");
//	}
}
