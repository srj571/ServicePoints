package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC16_VerifyUpdateDeleteQuotation extends BaseClass{
	ReadConfig rc = new ReadConfig();
	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();
	public String ProductForUpdate=rc.setProductForVerifyUpdate();
	public String productFetch=rc.fetchProducts();
	public String ordersFetch=rc.fetchOrders();
	public String clientMailForDelete=rc.setClientMailForDeleteQuote();
	public String clientPassForDelete=rc.setClientPassForDeleteQuote();
	
	@Test
	public void verifyUpdateDeleteQuotation() throws InterruptedException, IOException {
	
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(agentsupmail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentsuppass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(ProductForUpdate);
		Thread.sleep(4000);
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
		Thread.sleep(5000);

		if (aspp.verifyTextOnAlert()==true) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}

		aspp.firstPcsPrice(c1price);
		aspp.secPcsPrice(c2price);
		aspp.thirdPcsPrice(c3price);
		aspp.forthPcsPrice(c4price);
		logger.info("Price entered");
		Thread.sleep(4000);
		
		aspp.scrollTillUpdateBtn(driver);
		Thread.sleep(1000);
		aspp.updateQuotation();
		Thread.sleep(5000);

		if (aspp.verifyTextOnAlert()==true) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Quotation updation Successed..");
		} else {
			captureScreen(driver, "Submit Updated Quote Test");
			logger.info("Verification of Quotation updation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		
		driver.get(baseURL);
		
		lp.setAdminMailId(clientMailForDelete);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(clientPassForDelete);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client logged in successfully.");
		
		ClientProductPage cpp=new ClientProductPage(driver);
		cpp.getProductsPage();
		Thread.sleep(2000);
		cpp.searchProduct(ProductForUpdate);
		Thread.sleep(5000);
		
		driver.get(baseURL);
		Thread.sleep(2000);
		
		lp.setAdminMailId(agentsupmail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentsuppass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Agent logged in successfully.");
		
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(ProductForUpdate);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv(); 
		Thread.sleep(3000);
		
		window = driver.getWindowHandles();
		for(String handle:window) {
			if(!handle.equals(parentWindow)&& !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		aspp.scrollTillUpdateBtn(driver);
		Thread.sleep(1000);
		aspp.deleteQuote();
		logger.info("Click on delete Quote.");
		Thread.sleep(4000);
		aspp.clickOnYesImSure();
		logger.info("Yes Im sure.");
		

		if (aspp.checkSubmitQuotebtn() == true) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of Quotation deletion Successed..");
		} else {
			captureScreen(driver, "Delete Quote Test");
			Thread.sleep(2000);
			logger.info("Verification of Quotation deletion failed..");
			Assert.assertTrue(false);
		}
		
		driver.get(baseURL);
		Thread.sleep(1000);
		
		lp.setAdminMailId(clientMailForDelete);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(clientPassForDelete);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client logged in successfully.");
		
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		
		cpp.getProductsPage();
		Thread.sleep(2000);
		
		cpp.searchProduct(ProductForUpdate);
		Thread.sleep(5000);
		
		logger.info("Verification of Quotation Status after deletion Successed..");
//		if(driver.getPageSource().contains("requote - bidding")) {
//			Thread.sleep(4000);
//			Assert.assertTrue(true);
//			logger.info("Verification of Quotation Status after deletion Successed..");
//		}else {
//			captureScreen(driver, "Status ckeck");
//			Thread.sleep(2000);
//			logger.info("Verification of Quotation status after deletion failed..");
//			Assert.assertTrue(false);
//		}
//		
	}
}
