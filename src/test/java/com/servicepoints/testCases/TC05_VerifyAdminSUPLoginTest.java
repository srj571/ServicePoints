package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC05_VerifyAdminSUPLoginTest  extends BaseClass {
	
	@Test
	public void verifyAdminSUPLogin() throws InterruptedException, IOException{
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");

		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Admin Loged in.");
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(4000);
		
		adminAccount.enterUserName(adminSupplierName);
		logger.info("Entered Admin Supplier name in search field.");
		Thread.sleep(4000);
		
		adminAccount.goToAdminSUPTab();
		Thread.sleep(4000);
		
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the clients Account.");
		Thread.sleep(4000);
		
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String second=it.next();
		driver.switchTo().window(second);
		Thread.sleep(4000);
		
		if(driver.getPageSource().contains(adminSupplierName)) {
			logger.info("Verification of Admin Supplier login Successfull.");
			Assert.assertTrue(true);	
			Thread.sleep(5000);
		}else {
			captureScreen(driver, "AdminSUP Login Verification");
			logger.info("Verification of client login Failed.");
			Assert.assertTrue(false);	
		}
		
		
	}
}
