package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC30_VerifyCloseSpecialRequestFunction extends BaseClass {

	ReadConfig rd = new ReadConfig();
	public String clmail = rd.setClientTrackMail();
	public String clpass = rd.setClientTrackPass();
	public String proDsp = rd.setProductDsp();
	public String agentMailDsp = rd.setAMailDsp();
	public String agentPassDsp = rd.setApassDsp();
	public String agentAnswer = rd.setAnswer();

	@Test
	public void verifyCloseSpecialRequest() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		ClientOrdersPage cop = new ClientOrdersPage(driver);
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client logged in Successfully.");

		AgentDisputesPage asop = new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		Thread.sleep(3000);
		logger.info("Open disputes page.");

		asop.clickOnSpecialRequestTab();
		Thread.sleep(3000);
		logger.info("Click on special request.");

		asop.clickOnOpenTabInSreq();
		Thread.sleep(3000);
		logger.info("Click on special request.");

		asop.searchProductForDsp(proDsp);
		Thread.sleep(3000);

		asop.clickOnFrstDsp();
		Thread.sleep(3000);

		asop.scrollTillEndOfThePage(driver);
		Thread.sleep(2000);
		asop.clickOnShowRequestTab();
		Thread.sleep(3000);

		asop.setTxtAnsSpRequest(agentAnswer);
		Thread.sleep(3000);

		asop.clickOnSendAnsSpRequest();
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Thanks for the answer.")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request is successfull..");
		} else {
			logger.info("Verification of Close Special request is failed..");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(clmail);
		lp.setAdminPassword(clpass);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		Thread.sleep(3000);

		cop.clickOnOrdersTab();
		Thread.sleep(3000);

		cop.clickOnGoToDisputesTab();
		Thread.sleep(3000);
		cop.clickOnSpecialRequestTab();
		Thread.sleep(3000);
		cop.clickOnAnswersBtnInSR();
		Thread.sleep(2000);

		cop.sendPnameinSearch(proDsp);
		Thread.sleep(2000);
		cop.clickOnFDiv();
		Thread.sleep(2000);
		cop.scrollTillTheLast(driver);
		Thread.sleep(2000);
		cop.clickOnShowRequestTab();
		Thread.sleep(2000);

		if (driver.getPageSource().contains("Special request")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request is successfull..");
		} else {
			logger.info("Verification of Close Special request is failed..");
			Assert.assertTrue(false);
		}
	}
}
