package com.servicepoints.testCases;

import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;

import junit.framework.Assert;

public class TC10_VerifyAcceptQuotation extends BaseClass {

	@Test
	public void verifyAcceptQuote() throws InterruptedException {
		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(proToAcceptQuo);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		Thread.sleep(4000);

		cl.selectQuoteTab();
		cl.selectAcceptQuoteBtn();
		Thread.sleep(4000);

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			logger.info("Verification of accepting Quotation is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			logger.info("Verification of accepting Quotation is Failed.");
			Assert.assertTrue(false);
		}
		
	}

}
