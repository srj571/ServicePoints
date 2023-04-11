package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC02_VerifyClientLoginTest extends BaseClass{
	
	@Test
	public void verifyClientLogin() throws IOException, InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		logger.info("Application Opened.");
		
		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");
		Thread.sleep(3000);
		
		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");
		Thread.sleep(3000);
		
		lp.clickLoginbtn();
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(clientName);
		logger.info("Entered Client name in search field.");
		
		adminAccount.getClientsTab();
		Thread.sleep(3000);
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the clients Account.");
		Thread.sleep(3000);
		
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String parent=it.next();
		String child=it.next();
		driver.switchTo().window(child);
		Thread.sleep(3000);
			
		if(driver.getPageSource().contains(clientName)) {
			logger.info("Verification of client login Successfull.");
			Assert.assertTrue(true);	
			Thread.sleep(3000);
		}else {
			captureScreen(driver, "Client Login Verification");
			logger.info("Verification of client login Failed.");
			Assert.assertTrue(false);	
		}
		
	}
}
