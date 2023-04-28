package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentDisputesPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC19_VerifyAcceptDispute extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	
	public String agentMailDsp=rd.setAMailDsp();
	public String agentPassDsp=rd.setApassDsp();
	public String productToAcceptDsp=rd.setProductDsp();
	public String agentAnswer=rd.setAnswer();
	
	@Test
	public void verifyAcceptDispute() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(agentMailDsp);
		lp.setAdminPassword(agentPassDsp);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		
		AgentDisputesPage asop=new AgentDisputesPage(driver);
		asop.clickOnDisputesTab();
		logger.info("Open disputes page.");
		
		asop.searchProductForDsp(productToAcceptDsp);
		Thread.sleep(3000);
		asop.clickOnFrstDsp();
		Thread.sleep(3000);
		asop.clickOnShowDsp();
		logger.info("Clicked on show disputes.");
		Thread.sleep(3000);
		asop.selectDspStatus();
		logger.info("Dispute Accepted.");
		Thread.sleep(3000);
		
		asop.sendAnswer(agentAnswer);
		Thread.sleep(3000);
		asop.clickOnSendAnswer();
		logger.info("Dispute send.");
		Thread.sleep(3000);
		
		
		if(driver.getPageSource().contains("Dispute accepted successfully")) {
			Assert.assertTrue(true);
			logger.info("Verification of Dispute acceptance is successed.");
		}else {
			captureScreen(driver, "acceptDispute");
			Assert.assertTrue(false);
			logger.info("Verification of Dispute acceptance is failed.");
		}
		
	}
}
