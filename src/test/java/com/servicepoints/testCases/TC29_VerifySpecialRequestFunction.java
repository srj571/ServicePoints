package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC29_VerifySpecialRequestFunction extends BaseClass {

	ReadConfig con = new ReadConfig();

	public String CMail = con.setCEmailFrDispt();
	public String CPass = con.setCpassForDispute();
	public String proDsp = con.setProductDsp();
	public String queries = con.setQueries();

	@Test
	public void verifySpecialRequestTest() throws InterruptedException, IOException {
		LoginPage lp = new LoginPage(driver);

		lp.setAdminMailId(CMail);
		lp.setAdminPassword(CPass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		ClientOrdersPage cop = new ClientOrdersPage(driver);
		cop.clickOnOrdersTab();

		cop.sendPnameinSearch(proDsp);
		logger.info("Product name is entered.");
		Thread.sleep(2000);

		cop.clickOnFDiv();
		logger.info("Clicked on first div.");
		Thread.sleep(3000);

		cop.scrollTillEle(driver);
		Thread.sleep(1000);

		cop.clickOnSpecialRequest();
		Thread.sleep(3000);
		cop.handleRequestDropdown();
		Thread.sleep(3000);

		cop.clickOnSpecialRequestchecks();
		Thread.sleep(3000);
		logger.info("Checkboxes selected.");

		cop.clickOnSendRequestBtn();
		logger.info("Clicked on Send Request.");

		Thread.sleep(6000);
		if (driver.getPageSource().contains("Proof of shipment will be sent to you within 24 hours")) {
			Assert.assertTrue(true);
			logger.info("Special Request is opend by Client Successfully..");
		} else {
			captureScreen(driver, "Special Request");
			Assert.assertTrue(false);
			logger.info("Special Request is failed to opend by Client..");
		}
	}
}
