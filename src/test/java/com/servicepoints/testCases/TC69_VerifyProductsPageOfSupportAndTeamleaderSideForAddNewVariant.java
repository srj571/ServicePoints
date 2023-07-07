package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportOrdersPage;
import com.servicepoints.PageObjects.SupportProductsPage;
import com.servicepoints.PageObjects.TeamleaderOrdersPage;
import com.servicepoints.PageObjects.TeamleaderProductsPage;
import com.servicepoints.utilities.ReadConfig;

public class TC69_VerifyProductsPageOfSupportAndTeamleaderSideForAddNewVariant extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String product69 = rd.getProductForTC69();
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

	public String productFetch = rd.fetchProducts();
	public String ordersFetch = rd.fetchOrders();


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

		aspp.searchProductName(product69);
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

		cl.searchProduct(product69);
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

	@Test(priority = 2, enabled = true)
	public void verifyDisableQuotationBySupplier() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
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

		asop.searchProductName(product69);
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
		Thread.sleep(10000);

		if (asop.getStatus().equals("Quotation accepted")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successfull..");
		} else {
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}

		asop.scrollTillAskForPrChange(driver);
		Thread.sleep(2000);

		asop.clckOnAskForPrceChng();
		Thread.sleep(2000);

		asop.deselectCheckBoxes();
		Thread.sleep(2000);

		asop.firstPcsPrice(FirstPcsPrice);
		asop.secPcsPrice(SecPcsPrice);
		asop.thirdPcsPrice(ThirdPcsPrice);
		asop.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(1000);
		asop.clickOnSbmtNewPrice();
		Thread.sleep(9000);

		if (asop.getOrderStatusToNewPrice().equals("New price")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successfull..");
		} else {
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		BaseClass.closeAllWinTabsExceptParent();
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

		SupportProductsPage spp = new SupportProductsPage(driver);
		spp.clickOnProductsTab();
		Thread.sleep(2000);

		spp.searchProducts(product69);
		Thread.sleep(3000);

		spp.clickOnFDiv();
		Thread.sleep(2000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		spp.clickOnNewQuoteTab();
		Thread.sleep(2000);
		spp.clickOnAcceptBtnAskForPriceChange();
		Thread.sleep(3000);
		
		spp.clickOnYesImSureBtn();
		Thread.sleep(3000);
		
		spp.clickSubmitBtnOnCantAbleToQuoteBtn();
		Thread.sleep(4000);
		
		spp.clickCloseBtnOnAcceptingNewPricePopup();
		Thread.sleep(9000);

		if (spp.getOrderStatus().equals("Quotation accepted")) {
			logger.info("Verification of Quotation accepted is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			logger.info("Verification of Quotation accepted is Failed.");
			Assert.assertTrue(false);
		}

		SupportOrdersPage sop = new SupportOrdersPage(driver);
		sop.clickOnOrdersTab();
		Thread.sleep(1000);

		sop.sendProductName(product69);
		Thread.sleep(2000);

		sop.clickOnFDiv();
		Thread.sleep(1000);

		if (sop.getOrderStatusFromSupportSide().equals("Processing")) {
			logger.info("Verification of Order status to Processing is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		} else {
			logger.info("Verification of Order status to Processing is Failed.");
			Assert.assertTrue(false);
		}

		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 4, enabled = true)
	public void verifyUnableQuotationBySupplier() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		AgentSupProductsPage asop = new AgentSupProductsPage(driver);

		driver.get(baseURL);

		lp.setAdminMailId(supplierMailPP);

		lp.setAdminPassword(supplierPassPP);

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Supplier logged in Sucessfully.");

		asop.getProductsPage();
		Thread.sleep(4000);

		asop.searchProductName(product69);
		Thread.sleep(4000);
		logger.info("Product name entered.");

		asop.clickOnProductsTab();
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

		asop.scrollTillAskForPrChange(driver);
		Thread.sleep(2000);

		asop.clckOnAskForPrceChng();
		Thread.sleep(3000);

		asop.deselectCheckBoxesAnother();
		Thread.sleep(2000);

		asop.firstPcsPrice(FirstPcsPrice);
		asop.secPcsPrice(SecPcsPrice);
		asop.thirdPcsPrice(ThirdPcsPrice);
		asop.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(1000);
		asop.clickOnSbmtNewPrice();
		Thread.sleep(9000);

		if (asop.getOrderStatusToNewPrice().equals("New price")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 5, enabled = true)
	public void verifyAcceptQuotationByTeamleader() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
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

		tpp.searchProduct(product69);
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

		tpp.clickOnAcceptAskForPriceChangeBtn();
		Thread.sleep(4000);
		
		tpp.clickOnYesImSureBtn();
		Thread.sleep(4000);
		
		tpp.clickCloseBtnOnYouHaveAcceptedTheNewPrice();
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Quotation accepted")) {
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
		sop.searchProducts(product69);
		Thread.sleep(2000);
		sop.clickOnFDiv();
		Thread.sleep(4000);
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
