package com.servicepoints.testCases;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.ClientsPaymentsPage;
import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.utilities.ReadConfig;

public class TC32_VerifyClientPaymentApproveByAdmin extends BaseClass{
	
	ReadConfig con=new ReadConfig();
	public String ClientForPayment=con.setClientNameForPayment();
	public String priceForPayment=con.setPriceForPayment();
	public String remark=con.setRemarkForPayment();
	
	
	@Test
	public void verifyClientPaymentApprovedByAdmin() throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		logger.info("Application Opened.");
		
		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		lp.clickLoginbtn();
		
		AdminAccountsPage adminAccount=new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(3000);
		
		adminAccount.enterUserName(ClientForPayment);
		logger.info("Entered Client name in search field.");
		
		adminAccount.getClientsTab();
		Thread.sleep(3000);
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the clients Account.");
		Thread.sleep(3000);
		
		Set<String> window=driver.getWindowHandles();
		Iterator<String> it=window.iterator();
		String parent=it.next();
		String child=it.next();
		driver.switchTo().window(child);
		Thread.sleep(3000);
			
		if(driver.getPageSource().contains(ClientForPayment)) {
			logger.info("Verification of client login Successfull.");
			Assert.assertTrue(true);	
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}else {
			captureScreen(driver, "Client Login Verification");
			logger.info("Verification of client login Failed.");
			Assert.assertTrue(false);	
		}
		
		ClientsPaymentsPage cpp=new ClientsPaymentsPage(driver);
		cpp.goToPaymentsPage();
		logger.info("Client go to the payment page.");	
		Thread.sleep(1000);
		cpp.sendAmount(priceForPayment);
		logger.info("Price entered.");
		Thread.sleep(1000);
		cpp.clickOnPayNowBtn();
		Thread.sleep(5000);
//		cpp.waitTillBankDiv(driver);
//		logger.info("Try to click on Browse element.");
//		cpp.clickOnBrowseEle();
//		Thread.sleep(3000);
//		cpp.sendFile();
		//cpp.sendFileInText();
		
		String filePath="/Home/Downloads/A324.pdf";
		
		cpp.sendFileAsPath(driver, filePath);
		
		Thread.sleep(3000);
		cpp.sendRemarkWhilePay(remark);
		Thread.sleep(3000);
		cpp.clickOnIHavePaid();
		
	}
}
