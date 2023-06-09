package com.servicepoints.testCases;

import java.awt.AWTException;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC35_VerifyStopFulfillmentAndRequoteTest extends BaseClass {
	
	ReadConfig con=new ReadConfig();
	public String cmailForRequote=con.setCMailForRequote();
	public String cpassForRequote=con.setCPassForRequote();
	public String proForRequote=con.setProductForRequote();
	public String agentmForRequote=con.setAgentMailForRequote();
	public String agentpForRequote=con.setAgentPassForRequote();
	

	@Test
	public void verifyStopFulfillmentAndRequoteTest() throws InterruptedException, IOException, AWTException {
		
		ClientProductPage cl = new ClientProductPage(driver);
		LoginPage lp = new LoginPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		
		lp.setAdminMailId(cmailForRequote);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(cpassForRequote);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client login successed.");
		
		cl.getProductsPage();
		
		cl.searchProduct(proForRequote);
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
				
		lp.setAdminMailId(agentmForRequote);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	
		lp.setAdminPassword(agentpForRequote);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		Thread.sleep(5000);
				
		aspp.getProductsPage();
		Thread.sleep(4000);
				
		aspp.searchProductName(proForRequote);
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
		lp.setAdminMailId(cmailForRequote);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(cpassForRequote);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(proForRequote);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
				
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
		logger.info("Clicked on Start Fullfillment.");
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
			
		lp.setAdminMailId(agentmForRequote);
		logger.info("Agent supplier email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(agentpForRequote);
		logger.info("Agent supplier password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		Thread.sleep(5000);
				
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		aspp.searchProductName(proForRequote);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();
		Thread.sleep(4000);

//		String childg=it.next();
//		driver.switchTo().window(childg);
//		String pwin=driver.getWindowHandle();
//		Set<String>windowHs = driver.getWindowHandles();
//		for(String handle : windowHs) {
//			if(!handle.equals(pwin)) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
//		
//		windowHandles = driver.getWindowHandles();
//		for(String handle : windowHandles) {
//			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
		
		aspp.switchOnOtherTab();
		Thread.sleep(2000);
		
		if (aspp.getStatusAwating().equals("Requote - Bidding")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Requotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Requotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}

		logger.info("Switched to new window.");
		Thread.sleep(5000);
				
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
				
		if(aspp.isSubmitQuotebtnEnabled()==true) {
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
}
