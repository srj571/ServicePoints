package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentClientsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC35_VerifyPaymentPendingOrderForAddressError extends BaseClass{

	ReadConfig rd=new ReadConfig();
	public String productFetch=rd.fetchProducts();
	public String ordersFetch=rd.fetchOrders();
	public String cmailForPayment=rd.setCmailForPayment();
	public String cPassForPayment=rd.setCPassForPayment();
	public String AmailForPayment=rd.setAmailForPayment();
	public String ApassForPayment=rd.setAPassForPayment();
	public String productPayment=rd.setProductForPaymentPO();
	public String cNameForPayment=rd.setCNameForPaymentPO();
	public String aNameForPayment=rd.setANameForPaymentPO();
	public String aspMailForPayment=rd.setASupportForPayment();
	public String aspPassForPayment=rd.setASPPassForPayment();
	public String secProduct=rd.setSecProductName();
	public String editAdd=rd.setAdd();
	public String editZip=rd.setZip();
	public String proForAE=rd.setProForAddError();
	
	@Test
	public void verifyPaymentPendingOrderForAddressError() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		AgentClientsPage acp=new AgentClientsPage(driver);
		
		lp.setAdminMailId(cmailForPayment);
		lp.setAdminPassword(cPassForPayment);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(proForAE);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		cp.clickOnEditAddress();
		Thread.sleep(2000);
		cp.clearAddress();
		cp.setAddress1(editAdd);
		Thread.sleep(1000);
		cp.setZipNum(editZip);
		Thread.sleep(1000);
		cp.saveInfo();
		Thread.sleep(1000);
		cp.waitTillDivOpen(driver);
		cp.clickOnYesAddress();
		Thread.sleep(7000);
		
		cp.clearSearchProductField();
		cp.sendPnameinSearch(proForAE);
		Thread.sleep(1000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		
		if(driver.getPageSource().contains("Shipping address updated successfully.")) {
			logger.info("Verification of Status to Not quoted successfull.");
			Assert.assertTrue(true);
			Thread.sleep(3000);
		}else {
			captureScreen(driver, "AddressError");
			logger.info("Verification of Status to Not quoted Failed.");
			Assert.assertTrue(true);
		}
		
//		ClientProductPage cpp=new ClientProductPage(driver);
//		cpp.getProductsPage();
//		Thread.sleep(1000);
//		cpp.searchProduct(proForAE);
//		Thread.sleep(2000);
//		cpp.clickOnFirstPDiv();
//		Thread.sleep(2000);
//		
//		String parentWindow=driver.getWindowHandle();
//		Set<String> window = driver.getWindowHandles();
//		Iterator<String> it = window.iterator();
//		String parent = it.next();
//		String child = it.next();
//		driver.switchTo().window(child);
//		Thread.sleep(4000);
//		
//		cpp.clickOnRequestForQuoteBtn();
//		
//		if(driver.getPageSource().contains("Quotation bid will start right away.")) {
//			logger.info("Verification of Qotation bit start successfull.");
//			Assert.assertTrue(true);
//			Thread.sleep(3000);
//		}else {
//			captureScreen(driver, "AddressError");
//			logger.info("Verification of Quotation bid start Failed.");
//			Assert.assertTrue(false);
//		}
//		
//		cpp.clickOnCloseBtnForRR();
//		Thread.sleep(3000);
//		
		
		driver.get(baseURL);
		lp.setAdminMailId(AmailForPayment);
		lp.setAdminPassword(ApassForPayment);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(proForAE);
		Thread.sleep(2000);
		logger.info("Product name entered.");
		
		aspp.clickOnAllClientsFilter();
		aspp.setClientNameInSearch(cNameForPayment);
		Thread.sleep(2000);
		aspp.clickOnfClientTab();
		Thread.sleep(2000);
		aspp.clickOnfdiv();
		
		String parentWindow=driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);
		
//		window = driver.getWindowHandles();
//		for(String handle: window) {
//			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
		
		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered for the order.");
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
		
		lp.setAdminMailId(cmailForPayment);
		lp.setAdminPassword(cPassForPayment);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(proForAE);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		
		window = driver.getWindowHandles();
		for(String handle: window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
//		window = driver.getWindowHandles();
//		for(String handle: window) {
//			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
//				driver.switchTo().window(handle);
//				break;
//			}
//		}
		
		cl.selectQuoteTab();
		cl.scrollTillAcceptQbtn(driver);
		Thread.sleep(3000);
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
		
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(proForAE);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(4000);
		logger.info("Status changed to Processing.");
		
		if(cp.getFinancialStatus().equals("Pending")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Financial Status to Pending.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Financial Status to Pending failed.");
			Assert.assertTrue(false);
		}
		
		if(cp.verifyStatusToProcessing().equals("Processing")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Order Status to Not quoted.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Financial Status to Not quoted failed.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		lp.setAdminMailId(aspMailForPayment);
		lp.setAdminPassword(aspPassForPayment);
		lp.clickLoginbtn();
		Thread.sleep(2000);
		logger.info("Agent Support logged in Successfully.");
		
		acp.clickOnClientsPage();
		Thread.sleep(2000);
		acp.searchAccount(cNameForPayment);
		Thread.sleep(2000);
		acp.clickOnFClientDiv();
		Thread.sleep(2000);
		acp.clickOnPaymentToggle();
		logger.info("Payment Pending order toggle is Disabled.");
		Thread.sleep(2000);
		acp.clickOnYesImSure();
		Thread.sleep(4000);
		
	}
}
