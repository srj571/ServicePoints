package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC39_VerifyAcceptingRequoteQuotationTest extends BaseClass{
	ReadConfig con=new ReadConfig();
	public String cmailSsf=con.setClientMailForFulfilment();
	public String cpassSsf=con.setClientPassForFulfilment();
	public String agentMailSsf=con.setAgentMailSsf();
	public String agentPassSsf=con.setAgentPassSsf();
	public String productSsf=con.setProductSSF();
	
	@Test
	public void verifyAcceptingRequoteQuote() throws InterruptedException, IOException {
		ClientProductPage cl = new ClientProductPage(driver);
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		LoginPage lp=new LoginPage(driver);
		driver.get(baseURL);
		Thread.sleep(3000);
		lp.setAdminMailId(cmailSsf);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
				
		lp.setAdminPassword(cpassSsf);
		logger.info("Password is entered.");
		Thread.sleep(1000);
				
		lp.clickLoginbtn();
		Thread.sleep(4000);
		cl.getProductsPage();
				
		cl.searchProduct(productSsf);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		
		String parentWindow=driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		driver.navigate().refresh();
		Thread.sleep(2000);
		cl.selectQuoteTab();
		Thread.sleep(1000);
		cl.scrollTillAcceptQbtn(driver);
		Thread.sleep(1000);
		cl.selectAcceptQuoteBtn();
		logger.info("Clicked on accept quotation button.");
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");
		
		} else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}
		
		ClientOrdersPage cp=new ClientOrdersPage(driver);
		cp.clickOnOrdersTab();
		logger.info("Go to Orders page.");
		Thread.sleep(2000);
		cp.sendPnameinSearch(productSsf);
		Thread.sleep(2000);
		cp.clickOnFDiv();
		Thread.sleep(2000);
		logger.info("Status changed to Processing.");
	}
}
