package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC12_VerifyAcceptQuotationTest extends BaseClass {

	
	
	@Test
	public void verifyAcceptQuote() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(proToAcceptQuo);
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

		cl.selectQuoteTab();
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
		
		Thread.sleep(4000);
		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		
		cl.stopFullfilling();
		Thread.sleep(2000);
		
		cl.clickOnYesImSure();
		Thread.sleep(2000);
		
		cl.clickOnClosebtn();
		Thread.sleep(2000);
		logger.info("button is closed.");
		
		if(driver.getPageSource().contains("Stop fullfilment")) {
			logger.info("Verification of Stop fullfilment of Quotation is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			captureScreen(driver, "Stop fullfilling");
			logger.info("Verification of Stop fullfillment of Quotation is Failed.");
			Assert.assertTrue(false);
		}
		
		//...............Client Logout and login to Agent acount................
		driver.get(baseURL);
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminMailId(agentsupmail);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.setAdminPassword(agentsuppass);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.clickLoginbtn();
		Thread.sleep(5000);
		
		aspp.getProductsPage();
		Thread.sleep(4000);
		
		aspp.searchProductName(product);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		if(driver.getPageSource().contains("No more product quotations")) {
			logger.info("Verification of visiblity of product after Stop fullifilling in Quotations Client tab in Agent side is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is failed.");
			Assert.assertTrue(false);
			Thread.sleep(2000);
		}
		
		
		aspp.clickOnProductsTab();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("No more product quotations")) {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is failed.");
			Assert.assertTrue(false);
			Thread.sleep(2000);
		}
		
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
		
		cl.searchProduct(proToAcceptQuo);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		//driver.switchTo().window(newChild);
		windowHandles = driver.getWindowHandles();
		for(String handle : windowHandles) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		cl.startFulfillingDropbtn();
		Thread.sleep(3000);
		cl.clickOnPreviousFulfill();
		Thread.sleep(2000);
		cl.clickOnStartfulfillbtn();
		Thread.sleep(3000);
		cl.clickOnClosebtn();
		Thread.sleep(3000);
		
		if(cl.checkEleIsDisabled()==false) {
			Thread.sleep(2000);
			logger.info("Verification of Start fullfilment of Quotation is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			captureScreen(driver, "Start fullfilling");
			logger.info("Verification of Start fullfilment of Quotation is failed.");
			Assert.assertTrue(false);
		}
		
		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		cl.pleaseRequote();
		Thread.sleep(2000);
		cl.clickOnYesImSure();
		Thread.sleep(3000);
		cl.clickOnClosebtn();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Requote - Bidding")) {
			logger.info("Verification of Client side Requote is Successed.");
			//Assert.assertTrue(true);
		}
		else {
			logger.info("Verification of client side Requote is failed.");
			//Assert.assertTrue(false);
		}
		
		cl.logoutTheClient();
		
		lp.setAdminMailId(agentsupmail);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.setAdminPassword(agentsuppass);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.clickLoginbtn();
		Thread.sleep(5000);
		
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product);
		Thread.sleep(4000);
		logger.info("Product name entered.");
//		aspp.clickOnfdiv();
		//Thread.sleep(4000);
		
	//	String childg=it.next();
	//	driver.switchTo().window(childg);
//		String pwin=driver.getWindowHandle();
//		Set<String>windowHs = driver.getWindowHandles();
//		for(String handle : windowHs) {
//			if(!handle.equals(pwin)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
		
		if (aspp.getStatusRequote().equals("Requote - Bidding")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Requotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Requotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
//		logger.info("Switched to new window.");
//		Thread.sleep(5000);
//		
//		aspp.firstPcsPrice(FirstPcsPrice);
//		aspp.secPcsPrice(SecPcsPrice);
//		aspp.thirdPcsPrice(ThirdPcsPrice);
//		aspp.forthPcsPrice(ForthPcsprice);
//		logger.info("Price entered");
//		Thread.sleep(4000);
//
//		aspp.clickOnSubmitQuote();
//		Thread.sleep(6000);

		
		
//		if(driver.getPageSource().contains("Quotation done")) {
//			logger.info("Verification of Requote from Agent side is Successed.");
//			Assert.assertTrue(true);
//		}else {
//			logger.info("Verification of Requote from Agent side is failed.");
//			Assert.assertTrue(false);
//		}
//		
//		if(aspp.isSubmitQuotebtnEnabled()==true) {
//			logger.info("Verification of Requote from Agent side is Successed.");
//			Assert.assertTrue(true);
//		}else {
//			logger.info("Verification of Requote from Agent side is failed.");
//			Assert.assertTrue(false);
//		}
		
//		if (aspp.getStatus().equals("Quotation done")) {
//			Thread.sleep(2000);
//			Assert.assertTrue(true);
//			logger.info("Verification of Submit Requotation Successed..");
//		} else {
//			captureScreen(driver, "Submit Quote Test");
//			logger.info("Verification of Submit Requotation failed..");
//			Assert.assertTrue(true);
//			Thread.sleep(4000);
//		}
	
	}
}
