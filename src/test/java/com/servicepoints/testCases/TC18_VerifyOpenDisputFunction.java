package com.servicepoints.testCases;

import java.io.IOException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC18_VerifyOpenDisputFunction extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	
	public String CMail=con.setCEmailFrDispt();
	public String CPass=con.setCpassForDispute();
	public String proDsp=con.setProductForDsp();
	public String queries=con.setQueries();
	
	@Test
	public void verifyOpenDispute() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		//WebDriverWait wait=new WebDriverWait(driver,10);
		Thread.sleep(3000);
		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();
		cop.sendPnameinSearch(proDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);
		cop.clickOnFDiv();		
		logger.info("Clicked on first div.");
		Thread.sleep(3000);
		
		cop.clickOnOpenDspbtn();
		Thread.sleep(2000);
		
		cop.handleDspIssues();
		logger.info("Customer got wrong product option selected.");
		
		cop.handleDspSolution();
		logger.info("I want the order to be shipped immediately option is selected.");
		
		cop.clickOnCheckBox();	
		Thread.sleep(3000);
		cop.sendQueries(queries);
		
		cop.SaveDispute();
		Thread.sleep(5000);
		logger.info("Dispute saved.");
		
		if(driver.getPageSource().contains("Dispute raised successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute raised Successfully.");
		}else {
			captureScreen(driver, "disputeRaised");
			logger.info("Verification of Dispute raised failed.");
			Assert.assertTrue(false);
		}
	}
}
