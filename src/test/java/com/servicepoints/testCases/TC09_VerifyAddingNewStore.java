package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientStoresPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC09_VerifyAddingNewStore extends BaseClass {

	@Test
	public void verifyAddStore() throws InterruptedException, IOException {

		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client login successed.");

		ClientStoresPage sp = new ClientStoresPage(driver);
		sp.goToStoresPage();
		sp.clickOnAddNewStorebtn();
		
		sp.setDomainName(DomainName);
		logger.info("Domain name is entered.");
		Thread.sleep(1000);
		
		sp.setAlias(Alias);
		Thread.sleep(1000);
		
		sp.setPassword(StorePass);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		sp.setOrderFetch(DateOrder);
		Thread.sleep(1000);
		
		sp.clickOnSaveBtn();
		Thread.sleep(6000);
		logger.info("Saved info.");
		
		sp.goToStoresPage();
		Thread.sleep(3000);
		
		if(sp.checkElementText().equals(Alias)) {
			logger.info("Verification of Store Adding successfull.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		}else {
			captureScreen(driver, "AddStore");
			logger.info("Verification of Store Adding Failed.");
			Assert.assertTrue(false);
			//Thread.sleep(4000);
		}
	}
}
