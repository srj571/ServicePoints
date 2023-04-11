package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientStoresPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC08_VerifyAddingNewStore extends BaseClass {

	@Test
	public void verifyAddStore() throws InterruptedException, IOException {

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
		sp.clickOnAddNewStorebtn();
		
		sp.setDomainName(DomainName);
		logger.info("Domain name is entered.");
		sp.setAlias(Alias);
		Thread.sleep(4000);
		sp.setPassword(StorePass);
		logger.info("Password is entered.");
		sp.setOrderFetch(DateOrder);
		sp.clickOnSaveBtn();
		Thread.sleep(6000);
		logger.info("Saved info.");
		sp.goToStoresPage();
		Thread.sleep(3000);
		if(sp.checkElementText().equals(Alias)) {
			logger.info("Verification of Store Adding successfull.");
			Assert.assertTrue(true);
			Thread.sleep(6000);
		}else {
			captureScreen(driver, "AddStore");
			logger.info("Verification of Store Adding Failed.");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
		
		
//		if (driver.getPageSource().contains("Store added successfully")) {
//			logger.info("Verification of Store Adding successfull.");
//			Assert.assertTrue(true);
//			Thread.sleep(4000);
//		}else if(driver.getPageSource().contains("[API] Invalid API key or access token (unrecognized login or wrong password)")){
//			captureScreen(driver, "VerifyUpdatingStore");
//			logger.info("unrecognized login or wrong password.");
//			Assert.assertTrue(false);
//		} else {
//			captureScreen(driver, "AddStore");
//			logger.info("Verification of Store Adding Failed.");
//			Assert.assertTrue(false);
//			Thread.sleep(4000);
//		}
	}
}
