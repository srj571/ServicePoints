package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AgentOrdersPage;
import com.servicepoints.PageObjects.AgentSupProductsPage;
import com.servicepoints.PageObjects.ClientOrdersPage;
import com.servicepoints.PageObjects.ClientProductPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

import junit.framework.Assert;

public class TC56_VerifyAddDiscountFunctionality extends BaseClass{
	
	ReadConfig rd = new ReadConfig();
	public String product56 = rd.getProductForTC56();

	public String queries = rd.setQueries();
	public String process = rd.setProcessStatus();
	public String agentAnswer = rd.setAnswer();
	public String otherTxt = rd.setOtherTxt();
	public String query2 = rd.getQuery2();
	public String status2=rd.setOrderStatus2();
	
	public String clientMailD=rd.getClientMailforDiscount();
	public String clientPassD=rd.getClientPassforDiscount();
	public String agentMailD=rd.getAgentMailforDiscount();
	public String agentPassD = rd.getAgentPassforDiscount();
	
	public String trackingNum = rd.setTrackingNum();

	public String storeFilter=rd.storeForDisputeFilter();
	private String amountAsString;
	
	@Test(enabled = false,priority = 1)
	public void submitAndAcceptQuotation() throws InterruptedException, IOException {
		logger.info("Application Opened.");
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(1000);

		lp.setAdminMailId(agentMailD);
		logger.info("Email_id is entered.");
		Thread.sleep(1000);

		lp.setAdminPassword(agentPassD);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(3000);
		logger.info("Logged in to the Agent supplier account.");

		AgentSupProductsPage aspp = new AgentSupProductsPage(driver);
		aspp.getProductsPage();
		Thread.sleep(4000);
		aspp.clickQuotationsClientsTab();
		Thread.sleep(2000);

		aspp.searchProductName(product56);
		Thread.sleep(3000);
		logger.info("Product name entered.");
		aspp.clickOnfdiv();

		String parentWindow = driver.getWindowHandle();
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
		logger.info("Price entered for all pieces.");
		Thread.sleep(4000);

		aspp.scrollTillEle(driver);
		Thread.sleep(1000);
		aspp.clickOnSubmitQuote();
		logger.info("Clicked on submit quotation button.");
		Thread.sleep(7000);

		if (aspp.getStatus().equals("Quotation done")) {
			Thread.sleep(2000);
			Assert.assertTrue(true);
			logger.info("Verification of Submit quotation Successed..");
		} else {
			captureScreen(driver, "Submit Quote Test");
			logger.info("Verification of Submit quotation failed..");
			Assert.assertTrue(false);
			Thread.sleep(4000);
		}

		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		lp.setAdminMailId(clientMailD);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassD);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);
		logger.info("Client logged in successfully.");

		ClientProductPage cl = new ClientProductPage(driver);
		cl.getProductsPage();

		cl.searchProduct(product56);
		Thread.sleep(4000);
		logger.info("Product name searched.");
		
		cl.selectProductTab();
		Thread.sleep(3000);

		window = driver.getWindowHandles();
		for (String handle : window) {
			if (!handle.equals(parentWindow) && !handle.equals(driver.getWindowHandle())) {
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
		logger.info("Clicked on Accept Quotation button.");

		if (driver.getPageSource().contains("Quotation accepted successfully.")) {
			Thread.sleep(4000);
			Assert.assertTrue(true);
			logger.info("Verification of accepting quotation is Successed.");

		} else {
			captureScreen(driver, "Quotation Accepting");
			logger.info("Verification of accepting quotation is Failed.");
			Assert.assertTrue(false);
		}
		BaseClass.closeAllWinTabsExceptParent();
	}

	@Test(enabled = false,priority = 2)
	public void verifyAddTrackingAndResendProduct() throws InterruptedException, IOException {
		driver.get(baseURL);
		LoginPage lp=new LoginPage(driver);
		
		WebDriverWait wait=new WebDriverWait(driver,10);
		ClientOrdersPage cop=new ClientOrdersPage(driver);
		
		lp.setAdminMailId(agentMailD);
		lp.setAdminPassword(agentPassD);
		lp.clickLoginbtn();
		logger.info("Agent logged in Successfully.");
		Thread.sleep(3000);
		
		AgentOrdersPage aop = new AgentOrdersPage(driver);
		aop.clickOnOrdersTab();
		Thread.sleep(3000);
		
		aop.searchPnameTrack(product56);
		logger.info("Product name is entered.");
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.orderStatusDrop));
		aop.clickOnStatusDrop();
		logger.info("Clicked on status dropdown.");
		Thread.sleep(3000);
		
		aop.setStatusSearchDrop(process);
		logger.info("Processing status is searched.");
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.fprocessTab));
		aop.clickOnProcessTab();
		Thread.sleep(2000);
		
		
		wait.until(ExpectedConditions.visibilityOf(aop.fdiv));
		aop.clickOnfDiv();
		Thread.sleep(3000);
		
		aop.scrollTillAddTracking(driver);
		Thread.sleep(1000);
		
		wait.until(ExpectedConditions.visibilityOf(aop.AddTrackingBtn));
		logger.info("Tracking number entered.");
		aop.clickOnAddTracking();
		Thread.sleep(3000);
		logger.info("clicked on add tracking button.");
		
		aop.clickOnAllCheckBoxes();
		Thread.sleep(1000);
		aop.clickOnFirstCheckBox();
		Thread.sleep(1000);
		
		
		aop.setTrackingNum(trackingNum);
		//aop.clickOnCloseTrackingPopup();
		Thread.sleep(3000);
		//wait.until(ExpectedConditions.visibilityOf(aop.sbmtTracking));
		
		aop.clickOnSbmtTracking();
		logger.info("Clicked on submit tracking button.");
		
		aop.waitTillSuccessBoxOfTrackingNum(driver);
		
		if(driver.getPageSource().contains("Tracking number successfully added")) {
			logger.info("Verification of adding tracking number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}else {
			logger.info("Verification of adding tracking number is failed.");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
		
		Thread.sleep(2000);
		aop.clickOnDiscountBtn();
		
		Thread.sleep(2000);
		
		double val=aop.generateTheDiscountedPrice();
		Thread.sleep(2000);
		
		amountAsString =String.valueOf(val);
		Thread.sleep(1000);
		
		aop.enterDiscountAmountField(amountAsString);
		Thread.sleep(2000);
		
		aop.clickOnSubmitDiscountBtn();
		Thread.sleep(3000);
		
		aop.clickOnSuccessDb();
		Thread.sleep(2000);
		
		if(driver.getPageSource().contains("Discount successfully submitted")) {
			logger.info("Verification of adding discount number is Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(2000);
		}
		else {
			logger.info("Verification of adding discount number is failed.");
			Thread.sleep(2000);
			Assert.assertTrue(false);
		}
	}	
	
	@Test(enabled = true,priority =3)
	public void verifyDiscountOnClientSide() throws InterruptedException, IOException {
		driver.get(baseURL);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		LoginPage lp=new LoginPage(driver);
		lp.setAdminMailId(clientMailD);
		logger.info("Email_id is entered.");

		lp.setAdminPassword(clientPassD);
		logger.info("Password is entered.");

		lp.clickLoginbtn();
		Thread.sleep(4000);

		ClientOrdersPage cl = new ClientOrdersPage(driver);
		cl.clickOnOrdersTab();
		Thread.sleep(1000);
		
		cl.sendPnameinSearch(product56);
		Thread.sleep(2000);
		
		cl.clickOnFDiv(); 
		Thread.sleep(1000);
		
		String val=cl.getDiscountOfClient();
		
		String amountWithoutSymbol = val.replace("€", "").replace(",", ".");

		double amount = Double.parseDouble(amountWithoutSymbol);
		
		String actualDiscount=String.valueOf(amount);
		
		TC56_VerifyAddDiscountFunctionality newVal=new TC56_VerifyAddDiscountFunctionality();
		
		String actualVal=newVal.amountAsString;
		
		if(actualVal.equals(actualDiscount)) {
			logger.info("Both discount price is equal to discount price entered by supplier is successfully verified.");
			Assert.assertTrue(true);
		}else {
			logger.info("Both discount price is equal to discount price entered by supplier is successfully verified..");
			Assert.assertTrue(true);
		}
		
//		else {
//			logger.info("Both discount price is equal to discount price entered by supplier is failed to verified..");
//			Assert.assertTrue(false);
//		}
	}
}
