package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC08_VerifyClientLoginTest extends BaseClass{
	
	@Test
	public void verifyClientLogin() throws IOException, InterruptedException {
		
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
		Thread.sleep(3000);
		
		adminAccount.enterUserName(clientName);
		logger.info("Entered Client name in search field.");
		Thread.sleep(2000);
		
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
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}else {
			captureScreen(driver, "Client Login Verification");
			logger.info("Verification of client login Failed.");
			Assert.assertTrue(false);	
		}
		
	}
}
