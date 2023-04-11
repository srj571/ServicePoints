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
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Store added successfully")) {
			logger.info("Verification of Store Adding successfull.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			captureScreen(driver, "AddStore");
			logger.info("Verification of Store Adding Failed.");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}
	}
	
	@Test(enabled=false)
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
		Thread.sleep(2000);
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
	
	@Test(enabled=false)
	public void verifyDeletingStore() throws IOException, InterruptedException {
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
		sp.clickOnDeleteStoreBtn();
		sp.clickOnDelePop();
		Thread.sleep(5000);
		if(driver.getPageSource().contains("Store deleted successfully.")) {
			Assert.assertTrue(true);
			logger.info("Varification for Store deletion is done.");
		}else {
			captureScreen(driver, "StoreDeleteTest");
			Assert.assertTrue(false);
			logger.info("Varification for Store deletion is failed.");
		}
		
	}
	
	
}
