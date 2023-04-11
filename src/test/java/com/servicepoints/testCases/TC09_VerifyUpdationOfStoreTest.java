package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientStoresPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC09_VerifyUpdationOfStoreTest extends BaseClass{
	
	
	@Test
	public void verifyUpdatingStore() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");

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
		Thread.sleep(2000);
		sp.clickOnSave();
		Thread.sleep(6000);
		
		if(driver.getPageSource().contains("Store details updated successfully.")) {
			Assert.assertTrue(true);
			logger.info("Varification for Store updation is done.");
		}
		else {
			captureScreen(driver, "VerifyUpdatingStore");
			Assert.assertTrue(false);
			logger.info("Varification for Store updation is failed.");
		}	
	}
}
