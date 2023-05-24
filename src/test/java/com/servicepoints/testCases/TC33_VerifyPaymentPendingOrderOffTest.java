package com.servicepoints.testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentClientsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC33_VerifyPaymentPendingOrderOffTest extends BaseClass{
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
	
	@Test
	public void verifyPaymentPendingOrder() throws InterruptedException, IOException {
		
		driver.get(productFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(ordersFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(cmailForPayment);
		lp.setAdminPassword(cPassForPayment);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productPayment);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		
		if(cp.getFinancialStatus().equals("Pending")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Financial Status to Pending.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Financial Status to Pending failed.");
			Assert.assertTrue(false);
		}
		
		if(cp.getOrderStatusPayment().equals("Not quoted")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of Order Status to Not quoted.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Financial Status to Not quoted failed.");
			Assert.assertTrue(false);
		}
		
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

		aspp.searchProductName(productPayment);
		Thread.sleep(2000);
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
		logger.info("Price entered for the order.");
		Thread.sleep(4000);
		
		aspp.scrollTillEle(driver);
		Thread.sleep(1000);
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
		
		lp.setAdminMailId(cmailForPayment);
		lp.setAdminPassword(cPassForPayment);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(productPayment);
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
		logger.info("Verification of 1");
		cl.selectQuoteTab();
		logger.info("Verification of 2");
		cl.scrollTillViewPro(driver);
		logger.info("Verification of 3");
		Thread.sleep(2000);
		//cl.scrollTillAcceptQbtn(driver);
		cl.clickOnAcceptSelectedQuote();
		logger.info("Verification of 4");
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
		cp.sendPnameinSearch(productPayment);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(4000);
		logger.info("Status remains 'Not quoted'.");
		
		if(cp.getFinancialStatus().equals("Pending")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Financial Status to Pending.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Financial Status to Pending failed.");
			Assert.assertTrue(false);
		}
		
		if(cp.getOrderStatusPayment().equals("Not quoted")) {
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
		
		AgentClientsPage acp=new AgentClientsPage(driver);
		
		acp.clickOnClientsPage();
		Thread.sleep(2000);
		acp.searchAccount(cNameForPayment);
		Thread.sleep(2000);
		acp.clickOnFClientDiv();
		Thread.sleep(2000);
		
		if(acp.toggleStatusAbtPayment()==false) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Toggler is Off.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Toggle is failed.");
			Assert.assertTrue(false);
		}
		
		acp.clickOnPaymentToggle();
		Thread.sleep(2000);
		acp.clickOnYesImSure();
		Thread.sleep(4000);
		
		if(driver.getPageSource().contains("All pending orders successfully moved to process.")) {
			Thread.sleep(3000);
			Assert.assertTrue(true);
			logger.info("Verification of toggle ON is successfull.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of Toggle failed to ON.");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		
		lp.setAdminMailId(cmailForPayment);
		lp.setAdminPassword(cPassForPayment);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);
		
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productPayment);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");
		
		if(acp.getOrderStatusProcessing().equals("Processing")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Order Status to Processing.");
		}else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of order Status to Processing failed.");
			Assert.assertTrue(false);
		}
//		
//		driver.get(baseURL);
//		lp.setAdminMailId(aspMailForPayment);
//		lp.setAdminPassword(aspPassForPayment);
//		lp.clickLoginbtn();
//		Thread.sleep(2000);
//		logger.info("Agent Support logged in Successfully.");
//		
//		acp.clickOnClientsPage();
//		Thread.sleep(2000);
//		acp.searchAccount(cNameForPayment);
//		Thread.sleep(2000);
//		acp.clickOnFClientDiv();
//		Thread.sleep(2000);
//		acp.clickOnPaymentToggle();
//		logger.info("Payment Pending order toggle is Disabled.");
//		Thread.sleep(2000);
//		acp.clickOnYesImSure();
//		Thread.sleep(4000);
	}
}
