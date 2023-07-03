package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportOrdersPage;
import com.servicepoints.PageObjects.SupportProductsPage;
import com.servicepoints.PageObjects.TeamleaderOrdersPage;
import com.servicepoints.PageObjects.TeamleaderProductsPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC66_VerifyProductPageOffSupportAndTeamleaderSideAnotherScenario extends BaseClass{

	ReadConfig rd = new ReadConfig();
	public String product66 = rd.getProductForTC66();
	public String supplierMailPP = rd.getSuppllierMailForProductsPage();
	public String supplierPassPP = rd.getSupplierPassForProductsPage();
	public String supportMailPP = rd.getSupportMailForProductsPage();
	public String supportPassPP = rd.getSupportPassForProductsPage();
	public String clientMailPP = rd.getClientMailForProductsPage();
	public String clientPassPP = rd.getClientPassForProductsPage();
	public String teamleadName = rd.getTeamleadNameForProductsPage();

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

		aspp.searchProductName(product66);
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

		driver.get(baseURL);
		
		lp.setAdminMailId(AdminMailID);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		AdminAccountsPage aap = new AdminAccountsPage(driver);

		aap.getAdminAccountsPage();
		Thread.sleep(2000);
		aap.clickOnTeamleaderTab();
		Thread.sleep(2000);

		aap.enterUserName(teamleadName);
		Thread.sleep(1000);

		aap.clickOnLoginBtn();
		Thread.sleep(2000);

		String parentWindow1 = driver.getWindowHandle();
		Set<String> window1 = driver.getWindowHandles();
		Iterator<String> it1 = window1.iterator();
		String parent1 = it1.next();
		String child1 = it1.next();
		driver.switchTo().window(child1);
		Thread.sleep(4000);

		TeamleaderProductsPage tpp = new TeamleaderProductsPage(driver);
		tpp.clickOnProductsTab();
		Thread.sleep(1000);

		tpp.searchProduct(product66);
		Thread.sleep(2000);
		tpp.clickOnFtab();
		Thread.sleep(2000);

		window1 = driver.getWindowHandles();
		for (String handle : window1) {
			if (!handle.equals(parentWindow1) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}

		tpp.clickOnQouteTab();
		Thread.sleep(2000);

		tpp.clickOnAcceptQuoteBtn();
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");

		} else {
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}

		TeamleaderOrdersPage sop = new TeamleaderOrdersPage(driver);
		sop.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		sop.searchProducts(product66);
		Thread.sleep(2000);
		sop.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");

		if (sop.getOrderStatusFromSupportSide().equals("Processing")) {
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");
		} else {
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}
		
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 2, enabled = true)
	public void stopFulfillmentByClient() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(clientMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product66);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(3000);

		cl.clickOnSpecialRequestDrop();
		Thread.sleep(1000);
		
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
			logger.info("Verification of Stop fullfillment of Quotation is Failed.");
			Assert.assertTrue(false);
		}
				
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(product66);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Hold.");
		
		if(cp.checkOrderStatusHold().equals("Hold")) {
			logger.info("Verification of Order status to Hold is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of Order status to Hold is Failed.");
			Assert.assertTrue(false);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void startFulfillmentBySupport() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(supportMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supportPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		
		SupportProductsPage spp=new SupportProductsPage(driver);
		spp.clickOnProductsTab();
		Thread.sleep(2000);
		
		spp.searchProducts(product66);
		Thread.sleep(1000);
		
		spp.clickOnFDiv();
		Thread.sleep(2000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);
		
		spp.clickOnSpecialRequestDrop();
		Thread.sleep(1000);
		spp.clickOnStartFulfillmentBtn();
		Thread.sleep(3000);
		spp.clickOnPreviousOrderQuote();
		Thread.sleep(1000);
		spp.clickOnStartFulfilling();
		Thread.sleep(3000);
		spp.clickOnCloseBtn();
		Thread.sleep(2000);
		
		if(spp.getOrderStatus().equals("Quotation accepted")) {
			logger.info("Verification of Quotation accepted is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of Quotation accepted is Failed.");
			Assert.assertTrue(false);
		}
		
		
		SupportOrdersPage sop=new SupportOrdersPage(driver);
		sop.clickOnOrdersTab();
		Thread.sleep(1000);
		
		sop.sendProductName(product66);
		Thread.sleep(2000);
		
		sop.clickOnFDiv();
		Thread.sleep(1000);
		
		if(sop.getOrderStatusFromSupportSide().equals("Processing")) {
			logger.info("Verification of Order status to Processing is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of Order status to Processing is Failed.");
			Assert.assertTrue(false);
		}
		
	}
	
	@Test(priority = 4, enabled = true)
	public void requoteQuoteByClient() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(clientMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product66);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(3000);

		cl.clickOnSpecialRequestDrop();
		Thread.sleep(1000);
		cl.pleaseRequote();
		Thread.sleep(1000);
		cl.clickOnYesImSureBtn();
		Thread.sleep(3000);
		cl.clickOnClosebtn();
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Requote - Bidding")) {
			logger.info("Verification of Client side Requote is Successed.");
			Assert.assertTrue(true);
		} else {
			logger.info("Verification of client side Requote is failed.");
			Assert.assertTrue(false);
			Thread.sleep(2000);
		}

		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		cl.clickOnAddCountry();
		Thread.sleep(2000);

		BaseClass.closeAllWinTabsExceptParent();

	}
	
	@Test(priority = 5, enabled = true)
	public void supplierSubmitQuotationAgain() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(supplierMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supplierPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		AgentSupProductsPage asp = new AgentSupProductsPage(driver);
		asp.getProductsPage();
		Thread.sleep(1000);

		asp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		asp.searchProductName(product66);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		asp.clickOnfdiv();
		Thread.sleep(4000);

		String parentWindow1 = driver.getWindowHandle();
		Set<String> window1 = driver.getWindowHandles();
		Iterator<String> it1 = window1.iterator();
		String parent1 = it1.next();
		String child1 = it1.next();
		driver.switchTo().window(child1);
		Thread.sleep(4000);

		asp.verifyPassingDiffValuesInFirstCountry(driver, FirstPcsPrice, SecPcsPrice, ThirdPcsPrice, ForthPcsprice);
		Thread.sleep(2000);
		asp.verifyPassingValueInCountryQuote(driver, FirstPcsPrice, SecPcsPrice, ThirdPcsPrice, ForthPcsprice);
		Thread.sleep(4000);

		asp.scrollTillSubmitQuotationBtn(driver);
		Thread.sleep(2000);

		asp.clickOnSubmitQuote();
		Thread.sleep(6000);

		if (asp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");
		} else {
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}

		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 6, enabled = true)
	public void acceptQuotationFromSupport() throws InterruptedException {
		driver.get(baseURL);
		
		LoginPage lp = new LoginPage(driver);
		
		lp.setAdminMailId(supportMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supportPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product66);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		cl.clickOnQuoteTabSupportSide();
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
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}

		SupportOrdersPage sop = new SupportOrdersPage(driver);
		sop.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		sop.sendProductName(product66);
		Thread.sleep(2000);
		sop.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");

		if (sop.getOrderStatusFromSupportSide().equals("Processing")) {
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");
		} else {
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}

		BaseClass.closeAllWinTabsExceptParent();
	}
}
