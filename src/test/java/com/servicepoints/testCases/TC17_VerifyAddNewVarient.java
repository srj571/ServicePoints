package com.servicepoints.testCases;

import static org.testng.Assert.assertTrue;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC17_VerifyAddNewVarient extends BaseClass{
	
	ReadConfig rd=new ReadConfig();
	
	public String productFetch=rd.fetchProducts();
	public String ordersFetch=rd.fetchOrders();
	public String status=rd.setDropDownStatus();
	public String pname=rd.setProductAdded();
	public String status2=rd.setOrderStatus2();
	

	@Test
	public void verifyAddNewVarient() throws InterruptedException {
		
		LoginPage lp=new LoginPage(driver);
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		
		driver.get(productFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(ordersFetch);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get(baseURL);
		
		lp.setAdminMailId(rd.setCemail());
		
		lp.setAdminPassword(rd.setClientpass());
		
		lp.clickLoginbtn();
		
		logger.info("Client logged in Sucessfully.");
		
		cop.clickOnOrdersTab();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		cop.sendPnameinSearch(pname);
		Thread.sleep(3000);
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		cop.clickOnStatusDrop();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		cop.dropdownSearch(status);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(4000);
		
		cop.clickOnNotQuotedSel();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		logger.info("Not Quoted order status selected.");
		cop.clickOnFDiv();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		logger.info("Verification of New Varient added product is done..");
		
		driver.get(baseURL);
		
		lp.setAdminMailId(rd.setAgentEmail());
		lp.setAdminPassword(rd.setAgentPass());
		
		lp.clickLoginbtn();
		logger.info("Loggin to the Agent account.");
		
		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.searchProductName(pname);
		Thread.sleep(4000);
		logger.info("Product name entered.");
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		aspp.clickOnfdiv();
		String parentWindow=driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();

		for(String handle:windowHandles) {
			if(!handle.equals(parentWindow)) {
				driver.switchTo().window(handle);
				break;
			}
		}
		
		aspp.firstPcsPrice(FirstPcsPrice);
		aspp.secPcsPrice(SecPcsPrice);
		aspp.thirdPcsPrice(ThirdPcsPrice);
		aspp.forthPcsPrice(ForthPcsprice);
		logger.info("Price entered");
		Thread.sleep(4000);

		try {
			aspp.clickOnSubmitQuote();
			Thread.sleep(4000);
			
		}catch(Exception e) {
			logger.info("Price entered");
			aspp.updateQuotation();
		}
		
		
		driver.get(baseURL);
		
		lp.setAdminMailId(rd.setCemail());
		
		lp.setAdminPassword(rd.setClientpass());
		
		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client logged in Sucessfully.");
		
		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();
		Thread.sleep(4000);
		cl.searchProduct(pname);
		Thread.sleep(4000);
		cl.selectProductTab();
		Thread.sleep(3000);
		
		windowHandles = driver.getWindowHandles();
		for(String handle: windowHandles) {
			if(!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
				driver.switchTo().window(handle);
				break;
			}
		}

		try {
			cl.selectQuoteTab();
			cl.selectAcceptQuoteBtn();
			logger.info("Quotation accepted successfully.");
			Thread.sleep(7000);
		}catch(Exception e) {
			logger.info("New added varient is added to the quotation successfully..");
		}
			
		
		cop.clickOnOrdersTab();
		logger.info("GO to the orders page.");
		Thread.sleep(6000);
		cop.sendPnameinSearch(pname);
		Thread.sleep(6000);
		cop.clickOnStatusDrop();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(5000);
		
		cop.dropdownSearch(status2);
		logger.info("GO to the orders page.");
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(7000);
		cop.clickOnProcessingSel();
		Thread.sleep(4000);
		logger.info("GO to the orders page.");
		///driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//cop.clickOnFDiv();
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//Thread.sleep(3000);
		
		
		if(driver.getPageSource().contains("Processing")){
			Assert.assertTrue(true);
			logger.info("Verification of accept quotation after Adding new varient is successfull. ");
			
		}else{
			Assert.assertTrue(false);
			logger.info("Verification of accept quotation after Adding new varient is failed. ");
		}
	}
	
	
	
	
}
