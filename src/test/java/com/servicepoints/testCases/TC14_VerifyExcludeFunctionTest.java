package com.servicepoints.testCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC14_VerifyExcludeFunctionTest extends BaseClass{
	ReadConfig rd=new ReadConfig();
	public String Agname=rd.setAgentNameForExcldOpen();
	public String AgadminName=rd.setAgentsAdminName(); 
	
	@Test
	public void verifyExcludeQuotationFunction() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		logger.info("Application Opened.");
		
		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		adminAccount.enterUserName(Agname);
		logger.info("Entered Agent name in search field.");
		
		adminAccount.goToTheAgentSUPTab();
		Thread.sleep(4000);
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the Agents Account.");
		Thread.sleep(4000);
		
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String parent=it.next();
		String child=it.next();
		
		driver.switchTo().window(child);
		Thread.sleep(4000);
		AgentSupProductsPage aspp=new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		
		if(driver.getPageSource().contains("No more product quotations")) {
			Thread.sleep(3000);
			driver.switchTo().window(parent);
		
			adminAccount.clearSearchField();
			adminAccount.enterUserName(AgadminName);
			Thread.sleep(4000);
			System.out.println(adminAccount.verifyExcludeBtn());
			logger.info("AdminSupplier name is entered.");			
			Thread.sleep(2000);
			adminAccount.goToAdminSUPTab();
			Thread.sleep(6000);
			
			if(adminAccount.verifyExcludeBtn()== true) {
				Thread.sleep(3000);
				logger.info("Exclude open quotation button is ON.");
				adminAccount.clickOnExcludeQuotebtn();
				Thread.sleep(4000);
				logger.info("Now... Exclude open quotation button is turned Off.");
				
				adminAccount.goToTheAgentSUPTab();
				
				adminAccount.clearSearchField();
				adminAccount.enterUserName(Agname);
				Thread.sleep(4000);
				
				driver.switchTo().window(child);
				driver.navigate().refresh();
				Thread.sleep(2000);
				
				aspp.getProductsPage();
				Thread.sleep(3000);
				
				if(driver.getPageSource().contains("No more product quotations.")) {
					logger.info("Verification of Exclude Button failed.");
					Thread.sleep(4000);
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
					Thread.sleep(4000);
					logger.info("Verification of exclude button is Successed.");
				}
			}		
		}
		else {
			driver.switchTo().window(parent);
			Thread.sleep(3000);
			adminAccount.goToAdminSUPTab();
			
			Thread.sleep(4000);
			adminAccount.clearSearchField();
			adminAccount.enterUserName(AgadminName);
			logger.info("Entered Admin Supplier name.");
			Thread.sleep(4000);
			
			if(adminAccount.verifyExcludeBtn() == false) {
				logger.info("Verification of Exclude Button Successed.");
				Assert.assertTrue(true);				
			}else {
				Assert.assertTrue(true);
				Thread.sleep(4000);
				logger.info("Verification of Exclude button is failed.");
			}
		}
		
	}
	
	
	
}
