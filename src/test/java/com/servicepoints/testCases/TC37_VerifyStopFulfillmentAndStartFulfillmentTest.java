package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
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

public class TC37_VerifyStopFulfillmentAndStartFulfillmentTest extends BaseClass{

	
	ReadConfig con=new ReadConfig();
	public String cmailSsf=con.setClientMailForFulfilment();
	public String cpassSsf=con.setClientPassForFulfilment();
	public String agentMailSsf=con.setAgentMailSsf();
	public String agentPassSsf=con.setAgentPassSsf();
	public String productSsf=con.setProductSSF();
	

	@Test
	public void verifyStopFulfillmentAndRequoteTest() throws InterruptedException, IOException, AWTException {
		
		ClientProductPage cl = new ClientProductPage(driver);
		LoginPage lp = new LoginPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		
		lp.setAdminMailId(cmailSsf);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(cpassSsf);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client login successed.");
		
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
		}else {
			captureScreen(driver, "Stop fullfilling");
			logger.info("Verification of Stop fullfillment of Quotation is Failed.");
			AssertJUnit.assertTrue(false);
		}
				
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productSsf);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Hold.");
				
				
				//...............Client Logout and login to Agent acount................
		driver.get(baseURL);
				
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				
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
				
		aspp.searchProductName(productSsf);
		Thread.sleep(4000);
		logger.info("Product name entered.");
				
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
				
		if(driver.getPageSource().contains("No more product quotations")) {
			logger.info("Verification of visiblity of product after Stop fullifilling in Quotations Client tab in Agent side is Successfull.");
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is failed.");
			AssertJUnit.assertTrue(false);
			Thread.sleep(2000);
		}
				
		aspp.clickOnProductsTab();
		Thread.sleep(3000);
				
		if(driver.getPageSource().contains("No more product quotations")) {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is Successfull.");
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of visiblity of product after Stop fullifilling in Products tab in Agent side is failed.");
			AssertJUnit.assertTrue(false);
			Thread.sleep(2000);
		}
				
		driver.get(baseURL);
		Thread.sleep(3000);
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
			AssertJUnit.assertTrue(true);
			Thread.sleep(2000);
		}else {
			captureScreen(driver, "Start fullfilling");
			logger.info("Verification of Start fullfilment of Quotation is failed.");
			AssertJUnit.assertTrue(false);
		}
		
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productSsf);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Hold.");
	}
	
	
	
}