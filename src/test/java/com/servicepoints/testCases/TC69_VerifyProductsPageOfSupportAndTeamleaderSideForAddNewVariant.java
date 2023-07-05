package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportOrdersPage;
import com.servicepoints.utilities.ReadConfig;

public class TC69_VerifyProductsPageOfSupportAndTeamleaderSideForAddNewVariant extends BaseClass{

	ReadConfig rd = new ReadConfig();
	public String product68 = rd.getProductForTC68();
	public String supplierMailPP = rd.getSuppllierMailForProductsPage();
	public String supplierPassPP = rd.getSupplierPassForProductsPage();
	public String supportMailPP = rd.getSupportMailForProductsPage();
	public String supportPassPP = rd.getSupportPassForProductsPage();
	public String clientMailPP = rd.getClientMailForProductsPage();
	public String clientPassPP = rd.getClientPassForProductsPage();
	public String teamleadName = rd.getTeamleadNameForProductsPage();

	public String clientName = rd.getclientNameForPP();
	public String orderStatus = rd.getOrderStatus();
	public String message = rd.getMessageBySupplier();
	
	public String productFetch=rd.fetchProducts();
	public String ordersFetch=rd.fetchOrders();

	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();

	@Test(enabled = true, priority = 1)
	public void submitAndAcceptQuotation() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(supplierMailPP);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(supplierPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product68);
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

		BaseClass.closeAllWinTabsExceptParent();

		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		lp.setAdminMailId(clientMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product68);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		String parentWindow1 = driver.getWindowHandle();
		Set<String> window1 = driver.getWindowHandles();
		Iterator<String> it1 = window1.iterator();
		String parent1 = it1.next();
		String child1 = it1.next();
		driver.switchTo().window(child1);
		Thread.sleep(4000);

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
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	
	
	@Test(priority = 2,enabled = true)
	public void verifyDisableQuotationBySupplier() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		
		driver.get(productFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(ordersFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(baseURL);
		
		lp.setAdminMailId(supplierMailPP);
		
		lp.setAdminPassword(supplierPassPP);
		
		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Supplier logged in Sucessfully.");
		
		asop.getProductsPage();
		Thread.sleep(4000);

		asop.searchProductName(product68);
		Thread.sleep(4000);
		logger.info("Product name entered.");

		asop.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		asop.clickOnfdiv();
		Thread.sleep(2000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		asop.clickOncheckBoxesToDisableQuote();
		Thread.sleep(2000);
		
		asop.firstPcsPrice(FirstPcsPrice);
		asop.secPcsPrice(SecPcsPrice);
		asop.thirdPcsPrice(ThirdPcsPrice);
		asop.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);
		
		asop.scrollTillEle(driver);
		Thread.sleep(1000);
		asop.clickOnSubmitQuote();
		Thread.sleep(7000);

		if (asop.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		asop.scrollTillAskForPrChange(driver);
		Thread.sleep(2000);
		
		asop.clckOnAskForPrceChng();
		Thread.sleep(2000);
		
		
	}
	
}
