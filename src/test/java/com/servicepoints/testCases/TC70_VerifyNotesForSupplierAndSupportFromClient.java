package com.servicepoints.testCases;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SupportsClientPage;
import com.servicepoints.utilities.ReadConfig;

public class TC70_VerifyNotesForSupplierAndSupportFromClient extends BaseClass{
	
	
	ReadConfig rc=new ReadConfig();
	public String product70=rc.getProductForTC70();
	public String supportMailID=rc.getSupportMailForCNote();
	public String supportPass=rc.getSupportPassForCNote();
	public String supplierMailID=rc.getSupplierMailForCNote();
	public String supplierPass=rc.getSupplierPassForCNote();
	public String clientNameForNote=rc.clientNameForNote();
	public String supplierNote=rc.getSupplierNoteContent();
	public String text=rc.getTextForAgentNLNotes();
	
	
	@Test(enabled = true, priority = 1)
	public void verifyClientNoteFromSupport() throws InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		
		lp.setAdminMailId(supportMailID);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(supportPass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Support logged in successfully.");

		SupportsClientPage scp=new SupportsClientPage(driver);
		
		scp.goToClientsPage();
		wait.until(ExpectedConditions.visibilityOf(scp.searchField));
		logger.info("Go to clients page.");
		
		scp.enterClientNameField(clientNameForNote);
		wait.until(ExpectedConditions.visibilityOf(scp.clientsDetailsTab));
		logger.info("Client name is entered.");
		
		scp.goToClientDetailsPage();
		wait.until(ExpectedConditions.visibilityOf(scp.noteForSupplierToolTip));
		logger.info("Go to clients detail page.");
		
		if(scp.verifyClientsNameOnClientsDetailsPage().equals("Client: "+clientNameForNote)) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Client name is Successfull..");
		} else {
			logger.info("Verification of CLient name is failed..");
			Assert.assertTrue(false);
		}
		
		scp.clickOnNoteForSupplier();
		wait.until(ExpectedConditions.visibilityOf(scp.noteTextField));
		
		
		scp.sendNoteForSupplier(supplierNote);
		logger.info("Note is written to supplier.");
		
		scp.clickOnSaveBtn();
		logger.info("Clicked on note save button.");
		
		scp.waitTillPopUp(driver);
		
		if(driver.getPageSource().contains("Client notes for supplier updated successfully.")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Client note for supplier is updated Successfully..");
		} else {
			logger.info("Verification of Client note for supplier updation is failed..");
			Assert.assertTrue(false);
		}
		
		scp.clickOnExpandBtn();
		logger.info("Clicked on expand button.");
		
		if(scp.getSupplierExpandedFieldName().equals("Notes for supplier")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Client note for supplier is expanded Successfully..");
		} else {
			logger.info("Verification of Client note for supplier exapansion is failed..");
			Assert.assertTrue(false);
		}
		
		scp.clickOnCompressBtn();
		logger.info("Clicked on compress button.");
		
		scp.clickOnNoteForAgentNL();
		
		scp.sendTextIntoTextEditor(text);
		
		scp.clickOnSaveBtn();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Client notes updated successfully.")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Client note for Agent NL updated Successfully..");
		} else {
			logger.info("Verification of Client note for Agent NL updatedation failed..");
			Assert.assertTrue(false);
		}
		
		scp.clickOnExpandBtnForAgentNL();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Client notes updated successfully.")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Client note for Agent NL updated Successfully..");
		} else {
			logger.info("Verification of Client note for Agent NL updatedation failed..");
			Assert.assertTrue(false);
		}
		
		
		
		
	}
}
