package com.servicepoints.testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC00_verifySubmitAndAcceptQuote extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String cmailSsf=con.setClientMailForFulfilment();
	public String cpassSsf=con.setClientPassForFulfilment();
	public String agentMailSsf=con.setAgentMailSsf();
	public String agentPassSsf=con.setAgentPassSsf();
	public String productSsf=con.setProductSSF();
	
	
	@Test
	public void verifySubmitAndAcceptQuote() throws InterruptedException, IOException {
		
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);
		
		lp.setAdminMailId(agentMailSsf);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(agentPassSsf);
		logger.info("Password is entered.");

		
		lp.clickLoginbtn();
		Thread.sleep(3000);

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(productSsf);
		Thread.sleep(3000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();
		
		String parentWindow=driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		
		aspp.scrollTillEle(driver);
		Thread.sleep(1000);
		aspp.clickOnSubmitQuote();
		Thread.sleep(7000);
		
		if (aspp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			AssertJUnit.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			AssertJUnit.assertTrue(false);
			Thread.sleep(4000);
		}
		
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		lp.setAdminMailId(cmailSsf);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(cpassSsf);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(productSsf);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		window = driver.getWindowHandles();
		for(String handle : window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		cl.selectQuoteTab();
		Thread.sleep(1000);
		cl.scrollTillAcceptQbtn(driver);
		Thread.sleep(1000);
		cl.selectAcceptQuoteBtn();
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			Thread.sleep(4000);
			AssertJUnit.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");
		
		} else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of accepting quotation is Failed.");
			AssertJUnit.assertTrue(false);
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
