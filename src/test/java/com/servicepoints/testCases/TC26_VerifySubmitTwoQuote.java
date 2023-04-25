package com.servicepoints.testCases;

import java.awt.Desktop.Action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC26_VerifySubmitTwoQuote extends BaseClass{
	
	ReadConfig read=new ReadConfig();
	public String agentSUP1name=read.setAgent1NameTwoQuote();
	public String product2Quote=read.setProductForTwoQuote();
	public String agentSUP2name=read.setAgent2NameTwoQuote();
	
	@Test
	public void verifySubmitTwoQuote() throws InterruptedException, IOException {
		
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Admin Loged in.");
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(agentSUP1name);
		logger.info("Entered Admin Supplier name in search field.");
		Thread.sleep(3000);
		
		adminAccount.goToTheAgentSUPTab();
		Thread.sleep(3000);
		
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the clients Account.");
		Thread.sleep(3000);
		
		String parentWindow=driver.getWindowHandle();
//		List<String> windows=new ArrayList<String>(driver.getWindowHandles());
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String parent=it.next();
		String second=it.next();
		driver.switchTo().window(second);
		
//		for(int i=0; i< windows.size();i++) {
//			driver.switchTo().window(windows.get(0));
//			Thread.sleep(2000);
//		}
		
		Actions action=new Actions(driver);
//		action.sendKeys(Keys.chord(Keys.CONTROL,Keys.TAB)).build().perform();
//		Thread.sleep(4000);
		
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		
		aspp.getProductsPage();
		Thread.sleep(4000);
		
		aspp.searchProductName(product2Quote);
		Thread.sleep(2000);
		
		if(driver.getPageSource().contains("No more product quotations")) {
			aspp.clickQuotationsClientsTab();
			Thread.sleep(2000);
			aspp.clickOnfdiv();
		}else {
			aspp.clickOnfdiv();
		}
		
		Thread.sleep(2000);
		window = driver.getWindowHandles();
		for(String handle: window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;			}
		}
		
//		for(int i=0; i< windows.size();i++) {
//			driver.switchTo().window(windows.get(1));
//			Thread.sleep(2000);
//		}
		
//		action.sendKeys(Keys.chord(Keys.CONTROL,Keys.TAB)).build().perform();
		Thread.sleep(2000);
		
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
		
	//	driver.close();
		driver.switchTo().window(parent);
		Thread.sleep(3000);
		adminAccount.clearSearchField();
		adminAccount.enterUserName(agentSUP2name);
		

		adminAccount.goToTheAgentSUPTab();
		Thread.sleep(3000);
		
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the clients Account.");
		Thread.sleep(3000);
		
//		String thirdWin=it.next();
//		driver.switchTo().window(second);
		
//		for(int i=0; i< windows.size();i++) {
//			driver.switchTo().window(windows.get(2));
//			Thread.sleep(2000);
//		}
		
		action.sendKeys(Keys.chord(Keys.CONTROL,Keys.TAB)).build().perform();
		Thread.sleep(2000);
		
		aspp.getProductsPage();
		Thread.sleep(4000);
		
		aspp.searchProductName(product2Quote);
		Thread.sleep(2000);
		
		if(driver.getPageSource().contains("No more product quotations")) {
			aspp.clickQuotationsClientsTab();
			Thread.sleep(2000);
			aspp.clickOnfdiv();
		}else {
			Thread.sleep(2000);
			aspp.clickOnfdiv();
		}
	}
}
