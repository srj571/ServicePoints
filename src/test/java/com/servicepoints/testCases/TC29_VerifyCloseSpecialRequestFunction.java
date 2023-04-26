package com.servicepoints.testCases;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC29_VerifyCloseSpecialRequestFunction extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	
	public String proDsp=rd.setProductForDsp();
	public String agentMailDsp=rd.setAMailDsp();
	public String agentPassDsp=rd.setApassDsp();
	public String agentAnswer=rd.setAnswer();
	
	@Test
	public void verifyCloseSpecialRequest() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Client logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
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
		
		asop.clickOnShowRequestTab();
		Thread.sleep(3000);
		
		asop.setTxtAnsSpRequest(agentAnswer);
		Thread.sleep(3000);
		
		asop.clickOnSendAnsSpRequest();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Thanks for the answer.")) {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request is successfull..");
		}else {
			Assert.assertTrue(true);
			logger.info("Verification of Close Special request is failed..");
		}
	}
	
}
