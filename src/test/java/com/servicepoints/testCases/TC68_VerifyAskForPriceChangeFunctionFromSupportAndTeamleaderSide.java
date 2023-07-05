package com.servicepoints.testCases;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportOrdersPage;
import com.servicepoints.PageObjects.TeamleaderOrdersPage;
import com.servicepoints.PageObjects.TeamleaderProductsPage;
import com.servicepoints.utilities.ReadConfig;

public class TC68_VerifyAskForPriceChangeFunctionFromSupportAndTeamleaderSide extends BaseClass {

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

		lp.setAdminMailId(supportMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supportPassPP);
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
		sop.sendProductName(product68);
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
	public void verifySubmitingAskForPriceChange() throws InterruptedException {
		driver.get(baseURL);

		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(supplierMailPP);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(supplierPassPP);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(3000);

		asop.getProductsPage();
		Thread.sleep(4000);

		asop.clickOnProductsTab();
		Thread.sleep(1000);
		asop.searchProductName(product68);
		Thread.sleep(4000);
		logger.info("Product name entered.");

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
		Thread.sleep(1000);

		asop.clckOnAskForPrceChng();
		logger.info("Click on Ask for Price changed.");
		Thread.sleep(2000);

		asop.firstPcsPrice(c1price);
		Thread.sleep(1000);
		asop.secPcsPrice(c2price);
		Thread.sleep(1000);
		asop.thirdPcsPrice(c3price);
		Thread.sleep(1000);
		asop.forthPcsPrice(c4price);
		Thread.sleep(1000);

		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(2000);

		asop.clickOnSbmtNewPrice();
		Thread.sleep(2000);
		logger.info("Entered changed price and Clicked on submit.");

		asop.closeNotifyPopUp();
		logger.info("Pop up get closed.");
		Thread.sleep(2000);

		if (driver.getPageSource().contains("New price")) {
			logger.info("Status changed to New Price.");
		}

		Thread.sleep(4000);

		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(priority = 3, enabled = true)
	public void verifyClientAcceptedTheNewPrice() throws InterruptedException {
		driver.get(baseURL);
		logger.info("Logged out from Agent account.");

		LoginPage lp = new LoginPage(driver);

		ClientProductPage cl = new ClientProductPage(driver);

		lp.setAdminMailId(clientMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client loged in successfully.");

		cl.getProductsPage();
		Thread.sleep(2000);

		cl.searchProduct(product68);
		Thread.sleep(2000);

		cl.selectProductTab();
		logger.info("Product selected.");
		Thread.sleep(1000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		cl.waitTillCloseBtnVisible(driver);

		cl.closePopUpFrmClntSideAskPr();
		Thread.sleep(3000);
		logger.info("Pop up closed.");

		logger.info("Now accepting the quotation.");

		cl.acceptAskforPriceChange();
		Thread.sleep(2000);

		logger.info("Changed price is get accepted by the Client.");
		cl.clickOnYesImSure();
		logger.info("Clicked on yes im sure");
		Thread.sleep(2000);
		cl.closeThankUPopUp();
		logger.info("Now verification is to be done.");
		Thread.sleep(3000);

		if (driver.getPageSource().contains("Quotation accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification is done from Client side for Ask for Price change test.");
		} else {
			logger.info("Verification is for Ask for Price change test is failed.");
			Assert.assertTrue(false);
		}

		ClientOrdersPage cp = new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(4000);
		cp.sendPnameinSearch(product68);
		Thread.sleep(4000);
		cp.clickOnFDiv();
		Thread.sleep(5000);
		logger.info("Status changed to Processing.");
		
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(priority = 4, enabled = true)
	public void verifyDisableQuotationFromSupplier() throws InterruptedException {
		driver.get(baseURL);

		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(supplierMailPP);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(supplierPassPP);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		lp.clickLoginbtn();
		Thread.sleep(3000);

		asop.getProductsPage();
		Thread.sleep(4000);

		asop.clickOnProductsTab();
		Thread.sleep(1000);
		asop.searchProductName(product68);
		Thread.sleep(4000);
		logger.info("Product name entered.");

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
		Thread.sleep(1000);

		asop.clckOnAskForPrceChng();
		logger.info("Click on Ask for Price changed.");
		Thread.sleep(2000);

		asop.disableQuotationCheckBoxes();
		Thread.sleep(2000);

		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(1000);

		asop.clickOnSbmtNewPrice();
		logger.info("Entered changed price and Clicked on submit.");
		Thread.sleep(2000);

		if (driver.getPageSource().contains("New price")) {
			Assert.assertTrue(true);
			logger.info("Status changed to New Price.");
		}else {
			logger.info("Status failed to change.");
			Assert.assertTrue(false);
		}

		Thread.sleep(4000);
		
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	
	@Test(priority = 5, enabled = true)
	public void verifyAcceptingNewQuotation() throws InterruptedException {
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

		tpp.searchProduct(product68);
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
		Thread.sleep(2000);
		
		tpp.clickSubmitBtnOnDisableVariantPopup();
		Thread.sleep(3000);
		
		tpp.clickCloseBtnOnYouHaveAcceptedTheNewPrice();
		Thread.sleep(4000);
		
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
		sop.searchProducts(product68);
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
