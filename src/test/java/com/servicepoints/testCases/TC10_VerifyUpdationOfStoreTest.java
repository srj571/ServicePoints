package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientStoresPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC10_VerifyUpdationOfStoreTest extends BaseClass{
	
	ReadConfig rc=new ReadConfig();
	public String CMailStore=rc.setClientMForStore();
	public String CPassStore=rc.setCPassForStore();
	
	@Test
	public void verifyUpdationOfStore() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(CMailStore);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(CPassStore);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client login successed.");
		
		ClientStoresPage sp = new ClientStoresPage(driver);
		sp.goToStoresPage();
		Thread.sleep(3500);
		
		sp.clickSeeMore();
		
		sp.clickOnEditStore();
		Thread.sleep(3000);
		
		sp.clearAlias();
		
		sp.editStoreName(editAlias);
		Thread.sleep(1000);
		
		sp.clickOnSave();
		Thread.sleep(6000);
		
		if(driver.getPageSource().contains("Store details updated successfully.")) {
			AssertJUnit.assertTrue(true);
			logger.info("Varification for Store updation is done.");
			Thread.sleep(3000);
		}
		else {
			captureScreen(driver, "VerifyUpdatingStore");
			logger.info("Varification for Store updation is failed.");
			AssertJUnit.assertTrue(false);
			Thread.sleep(3000);
		}	
	}
}
