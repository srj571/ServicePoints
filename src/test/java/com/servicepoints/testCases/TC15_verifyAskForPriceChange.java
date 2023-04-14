package com.servicepoints.testCases;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC15_verifyAskForPriceChange extends BaseClass {

	ReadConfig rc = new ReadConfig();
	public String c1price = rc.setChangePrice1Pcs();
	public String c2price = rc.setChangePrice2Pcs();
	public String c3price = rc.setChangePrice3Pcs();
	public String c4price = rc.setChangePrice4Pcs();


	@Test
	public void verifyAskForPriceChange() throws InterruptedException {
		
		AgentSupProductsPage asop = new AgentSupProductsPage(driver);
		
		LoginPage lp = new LoginPage(driver);
		
		ClientProductPage cl = new ClientProductPage(driver);
		
		WebDriverWait wait=new WebDriverWait(driver, 5);
		
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
		asop.searchProductName(product);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		asop.clickOnfdiv();

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		
		
		driver.switchTo().window(child);
		Thread.sleep(4000);

		asop.clckOnAskForPrceChng();
		logger.info("Click on Ask for Price changed.");
		Thread.sleep(2000);

		asop.firstPcsPrice(c1price);
		asop.secPcsPrice(c2price);
		asop.thirdPcsPrice(c3price);
		asop.forthPcsPrice(c4price);
		asop.clickOnSbmtNewPrice();
		logger.info("Entered changed price and Clicked on submit.");
		System.out.println(asop.checkCancelBtnDisplayed());
		
		if(asop.checkCancelBtnDisplayed() == true) {
			asop.closeNotifyPopUp();
			logger.info("Pop up get closed.");
			Thread.sleep(5000);
		}
		Thread.sleep(5000);
		if (driver.getPageSource().contains("New price")) {
			logger.info("Status changed to New Price.");
		}
		Thread.sleep(4000);
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

		cl.searchProduct(product);
		Thread.sleep(3000);
		
		cl.selectProductTab();
		logger.info("Product selected.");
		Thread.sleep(3000);
		
		String childer = it.next();
		driver.switchTo().window(childer);
		Thread.sleep(6000);
		
		cl.clsePopUpFrmClntSideAskPr();
		logger.info("Pop up closed.");
		cl.acceptAskforPriceChange();
		logger.info("Changed price is get accepted by the Client.");
		cl.clickOnYesImSure();
		cl.closeThankUPopUp();
		logger.info("Now verification is to be done.");
		Thread.sleep(6000);

	}
}
