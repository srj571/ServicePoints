package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportProductsPage;
import com.servicepoints.PageObjects.TeamleaderOrdersPage;
import com.servicepoints.PageObjects.TeamleaderProductsPage;
import com.servicepoints.utilities.ReadConfig;


public class TC67_VerifySimpleSpecialRequestFunctionalityBySupportAndTeamleader extends BaseClass{

	ReadConfig rd = new ReadConfig();
	public String product67 = rd.getProductForTC67();
	public String supplierMailPP = rd.getSuppllierMailForProductsPage();
	public String supplierPassPP = rd.getSupplierPassForProductsPage();
	public String supportMailPP = rd.getSupportMailForProductsPage();
	public String supportPassPP = rd.getSupportPassForProductsPage();
	public String clientMailPP = rd.getClientMailForProductsPage();
	public String clientPassPP = rd.getClientPassForProductsPage();
	public String teamleadName = rd.getTeamleadNameForProductsPage();
	
	public String clientName=rd.getclientNameForPP();
	public String orderStatus=rd.getOrderStatus();
	public String message=rd.getMessageBySupplier();
	
	@Test(enabled = false, priority = 1)
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

		aspp.searchProductName(product67);
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

		tpp.searchProduct(product67);
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
		sop.searchProducts(product67);
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
	
	
	@Test(priority = 2, enabled = false)
	public void submitSpecialRequestByTeamleader() throws InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		
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
		logger.info("Teamleader login successfully.");
		
		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent= it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		TeamleaderProductsPage tpp = new TeamleaderProductsPage(driver);
		tpp.clickOnProductsTab();
		Thread.sleep(1000);

		tpp.searchProduct(product67);
		Thread.sleep(2000);
		logger.info("Product name searched.");
		
		tpp.handleOrderStatusDropdown(orderStatus);
		Thread.sleep(1000);
		logger.info("Order status filtered.");
		
		tpp.handleClientNameDropdown(clientName);
		Thread.sleep(1000);
		logger.info("Client name filtered.");
		
		tpp.clickOnFtab();
		Thread.sleep(2000);

		window = driver.getWindowHandles();
		for (String handle : window) {
			if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		tpp.clickOnSpecialrequestDrop();
		Thread.sleep(2000);
		tpp.clickOnNeedSizingChartSp();
		Thread.sleep(2000);
		String exp="I need a sizing chart";
		
		String act=tpp.getMesssageOfINeedSizeChart();
		
		Assert.assertEquals(exp, act);
		logger.info("I need a sizing chart message verified successfully.");
		
		tpp.clickOnSubmitBtn();
		Thread.sleep(2000);
		
		String expStatus="Open special request";
		String actStatus=tpp.getOpenSPStatus();
		
		Assert.assertEquals(expStatus, actStatus);
		logger.info("Open special request status verified successfully.");
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 3,enabled = false)
	public void verifySupplierSubmittedTheSpRequest() throws InterruptedException {
		
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

		asp.clickOnProductsTab();
		Thread.sleep(2000);

		asp.searchProductName(product67);
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
		
		asp.clickOnCheckBoxForSpecialRequest();
		Thread.sleep(1000);
		
		asp.sendMessageForSpecialRequest(message);
		Thread.sleep(2000);
		
		asp.clickOnSubmitBtn();
		logger.info("Message sent successfully for the special request.");
		
		BaseClass.closeAllWinTabsExceptParent();
		
	}
	
	@Test(priority = 4, enabled = false)
	public void verifyCloseSpecialRequestFromTeamleaderSide() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		
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
		logger.info("Teamleader login successfully.");
		
		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent= it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		TeamleaderProductsPage tpp = new TeamleaderProductsPage(driver);
		tpp.clickOnProductsTab();
		Thread.sleep(1000);

		tpp.searchProduct(product67);
		Thread.sleep(2000);
		logger.info("Product name searched.");
		
		tpp.handleOrderStatusDropdown(orderStatus);
		Thread.sleep(1000);
		logger.info("Order status filtered.");
		
		tpp.handleClientNameDropdown(clientName);
		Thread.sleep(1000);
		logger.info("Client name filtered.");
		
		tpp.clickOnFtab();
		Thread.sleep(2000);

		window = driver.getWindowHandles();
		for (String handle : window) {
			if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		String exp="Closed special request";
		String act=tpp.getClosedSpRequestTag();
		
		Assert.assertEquals(exp, act);
		logger.info("Close Special request status verified successfully.");
		
		BaseClass.closeAllWinTabsExceptParent();
	}
	
	@Test(priority = 5, enabled = true)
	public void verifySumbmittingSpRequestFromSupport() throws InterruptedException {
		driver.get(baseURL);
		LoginPage lp = new LoginPage(driver);
		
		lp.setAdminMailId(supportMailPP);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supportPassPP);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		SupportProductsPage cl = new SupportProductsPage(driver);
		cl.clickOnProductsTab();
		
		cl.searchProducts(product67);
		Thread.sleep(4000);
		
		cl.clickOnFDiv();
		Thread.sleep(1000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);
		
		cl.clickOnSpecialRequestDrop();
		Thread.sleep(2000);
		
		cl.clickOnIWantRealFactoryPicSp();
		Thread.sleep(2000);
		
		String exp="I want real factory pictures";
		String actText=cl.getTextOFiWantRealFactoryPicSp();
		
		Assert.assertEquals(exp, actText);
		logger.info("I want real factory pictures message is verified successfully.");
		cl.clickOnSubmitBtn();
		Thread.sleep(2000);
		
		String expStatus="Open special request";
		
		String actStatus=cl.getSPStatus();
		
		Assert.assertEquals(expStatus, actStatus);
		logger.info("Status is verified successfully.");
		
		BaseClass.closeAllWinTabsExceptParent();
		
	}
	
	@Test(priority = 6,enabled = true)
	public void verifySupplierSubmittedTheSpRequestFromSupport() throws InterruptedException {
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

		asp.clickOnProductsTab();
		Thread.sleep(2000);

		asp.searchProductName(product67);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		asp.clickOnfdiv();
		Thread.sleep(4000);

		String parentWindow = driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);
		
		asp.clickOnCheckBoxForSpecialRequest();
		Thread.sleep(1000);
		
		asp.sendMessageForSpecialRequest(message);
		Thread.sleep(2000);
		
		asp.clickOnSubmitBtn();
		logger.info("Message sent successfully for the special request.");
		
		BaseClass.closeAllWinTabsExceptParent();
		
	}
	
	
	
}
