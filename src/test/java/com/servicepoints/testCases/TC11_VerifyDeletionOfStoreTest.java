package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientStoresPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC11_VerifyDeletionOfStoreTest extends BaseClass{
	
	ReadConfig rc=new ReadConfig();
	public String CMailStore=rc.setClientMForStore();
	public String CPassStore=rc.setCPassForStore();
	
	@Test
	public void verifyDeletingStore() throws IOException, InterruptedException {
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
		Thread.sleep(2000);
		sp.clickOnDeleteStoreBtn();
		sp.clickOnDelePop();
		Thread.sleep(5000);
		if(driver.getPageSource().contains("Store deleted successfully.")) {
			AssertJUnit.assertTrue(true);
			logger.info("Varification for Store deletion is done.");
			Thread.sleep(2000);
		}else {
			captureScreen(driver, "StoreDeleteTest");
			AssertJUnit.assertTrue(false);
			logger.info("Varification for Store deletion is failed.");
			Thread.sleep(2000);
		}
	}
}
