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

public class TC22_VerifyEndToEndFunctionality extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	
	public String agentMailDsp=rd.setAMailDsp();
	public String agentPassDsp=rd.setApassDsp();
	public String CMail=rd.setCEmailFrDispt();
	public String CPass=rd.setCpassForDispute();
	public String productFetch=rd.fetchProducts();
	public String ordersFetch=rd.fetchOrders();
	public String status2=rd.setOrderStatus2();

	
	@Test
	public void verifyEndToEndFunctionality() throws InterruptedException, IOException {
		
//		driver.get(productFetch);
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		
//		driver.get(ordersFetch);
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		
//		driver.get(baseURL);
		
		LoginPage lp=new LoginPage(driver);
		
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(proToAcceptQuo);
		Thread.sleep(2000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();

//		Set<String> window = driver.getWindowHandles();
//		Iterator<String> it = window.iterator();
//		String parent = it.next();
//		String child = it.next();
//		driver.switchTo().window(child);
//		Thread.sleep(4000);

		String parentWindow=driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow)) {
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
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(proToAcceptQuo);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
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
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		logger.info("Clicked on orders tab.");
		Thread.sleep(2000);
		cop.sendPnameinSearch(proToAcceptQuo);
		Thread.sleep(4000);
	
		cop.clickOnDropdown();
		Thread.sleep(2000);
		
		cop.dropdownSearch(status2);
		Thread.sleep(4000);
		cop.clickOnProcessingSel();
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		 	
		cop.clickOnCancelBtn();
		logger.info("Clicked on cancel order.");
		Thread.sleep(2000);
		
		cop.clickOnFirstCheckbox();
		Thread.sleep(2000);
		logger.info("Clicked on first checkbox.");
		
		cop.clickOnSubmitOrder();
		Thread.sleep(2000);
		
		cop.clickOnCancelOrderBtn();
		Thread.sleep(2000);
		logger.info("Clicked on Submit order.");
		
		cl.getProductsPage();
		Thread.sleep(2000);
		cl.searchProduct(proToAcceptQuo);
		Thread.sleep(2000);
		logger.info("Go to products page.");
		cl.clickOnFirstPDiv();
		Thread.sleep(2000);
		
		// Switch to the fourth child window
		windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
		    if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
		        driver.switchTo().window(handle);
		        // We are now in the fourth child window
		        break;
		    }
		}
		
		Thread.sleep(2000);
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
		
		
	}
}
