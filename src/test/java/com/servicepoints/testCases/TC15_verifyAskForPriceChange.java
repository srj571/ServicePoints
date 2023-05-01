package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Set;

import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC15_verifyAskForPriceChange extends BaseClass {

	ReadConfig rc = new ReadConfig();
	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();
	public String prdctForPChange=rc.setProductForPriceChange();
	
	
	@Test
	public void verifyAskForPriceChange() throws InterruptedException, IOException {
		

		
		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		
		LoginPage lp = new LoginPage(driver);
		
		ClientProductPage cl = new ClientProductPage(driver);
		
		//WebDriverWait wait=new WebDriverWait(driver, 5);
		
		lp.setAdminMailId(agentsupmail);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);
		
		lp.setAdminPassword(agentsuppass);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		
		lp.clickLoginbtn();
		Thread.sleep(3000);

		asop.getProductsPage();
		Thread.sleep(4000);

		asop.clickOnProductsTab();
		Thread.sleep(1000);
		asop.searchProductName(prdctForPChange);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		
		asop.clickOnfdiv();
		Thread.sleep(2000);

		String parentWindow=driver.getWindowHandle();
		Set<String> window = driver.getWindowHandles();
		
		for(String handle:window) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		asop.scrollTillAskForPrChange(driver);
		Thread.sleep(1000);
		
		asop.clckOnAskForPrceChng();
		logger.info("Click on Ask for Price changed.");
		Thread.sleep(2000);

		asop.firstPcsPrice(c1price);
		Thread.sleep(1000);
		asop.secPcsPrice(c2price);
		Thread.sleep(1000);
		asop.thirdPcsPrice(c3price);
		Thread.sleep(1000);
		asop.forthPcsPrice(c4price);
		Thread.sleep(1000);
		asop.scrollTillSubmitNewPrice(driver);
		Thread.sleep(1000);
		asop.clickOnSbmtNewPrice();
		logger.info("Entered changed price and Clicked on submit.");
		
		try {
			asop.closeNotifyPopUp();
			logger.info("Pop up get closed.");
			Thread.sleep(3000);
		}catch(Exception e) {
			logger.info("Element is not found.");
			Thread.sleep(4000);
		}
		
		
		if (driver.getPageSource().contains("New price")) {
			logger.info("Status changed to New Price.");
		}
		Thread.sleep(3000);
		
		driver.get(baseURL);
		logger.info("Logged out from Agent account.");
		Thread.sleep(4000);
		
		lp.setAdminMailId(clientemail);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(cPass);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Client loged in successfully.");

		cl.getProductsPage();
		Thread.sleep(2000);

		cl.searchProduct(prdctForPChange);
		Thread.sleep(2000);
		
		cl.selectProductTab();
		logger.info("Product selected.");
		Thread.sleep(1000);
		
		window = driver.getWindowHandles();
		for(String handle: window) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		try {
			Thread.sleep(3000);
			cl.clsePopUpFrmClntSideAskPr();
			Thread.sleep(3000);
			logger.info("Pop up closed.");
		}catch(Exception e) {
			logger.info("Now accepting the quotation.");
		}
		
		
		cl.acceptAskforPriceChange();
		Thread.sleep(2000);
		logger.info("Changed price is get accepted by the Client.");
		cl.clickOnYesImSure();
		logger.info("Clicked on yes im sure");
		Thread.sleep(2000);
		cl.closeThankUPopUp();
		logger.info("Now verification is to be done.");
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Quotation accepted")) {
			Assert.assertTrue(true);
			logger.info("Verification is done from Client side for Ask for Price change test.");
		}else {
			captureScreen(driver, "askForPriceChange");
			logger.info("Verification is for Ask for Price change test is failed.");
			Assert.assertTrue(false);
		}
	}
}
