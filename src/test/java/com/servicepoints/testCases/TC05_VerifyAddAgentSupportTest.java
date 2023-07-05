package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.servicepoints.PageObjects.AdminAccountsPage;
import com.servicepoints.PageObjects.AdminSupplierPage;
import com.servicepoints.PageObjects.LoginPage;

public class TC05_VerifyAddAgentSupportTest extends BaseClass {

	@Test
	public void verifyAddAgentSupportTest() throws InterruptedException, Exception {
		LoginPage lp = new LoginPage(driver);
		logger.info("Application Opened.");

		lp.setAdminMailId(AdminMailID);
		logger.info("Admin Email_id is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.setAdminPassword(AdminPassword);
		logger.info("Admin password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.clickLoginbtn();

		AdminAccountsPage adminAccount = new AdminAccountsPage(driver);
		adminAccount.getAdminAccountsPage();
		logger.info("Accounts page opened.");
		Thread.sleep(2000);
		adminAccount.enterUserName(TeamLName);
		logger.info("Entered Teamleader name in search field.");
		Thread.sleep(4000);
		adminAccount.clickOnTeamleaderTab();
		Thread.sleep(2000);
		adminAccount.clickOnLoginBtn();
		logger.info("Logged in to the Teamleader Account.");
		Thread.sleep(5000);

		Set<String> window = driver.getWindowHandles();
		Iterator<String> it = window.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		adminAccount.clickONTeamTab();

		adminAccount.clickOnAddAgentTab();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		AdminSupplierPage asp = new AdminSupplierPage(driver);
		asp.sendFName(TAfname);
		Thread.sleep(1000);

		asp.sendLname(TAlname);
		Thread.sleep(1000);

		asp.sendCCode(TACode);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		String num = BaseClass.getRandomNum();
		asp.sendmobNum(num);
		Thread.sleep(1000);

		asp.sendEmail(TAEmail);
		Thread.sleep(1000);

		asp.sendPass(TAPass);
		Thread.sleep(1000);

		asp.sendcpass(TAcPass);
		Thread.sleep(1000);

		adminAccount.clickOnAddAgetSP();
		logger.info("Click on Add Agent Button.");
		Thread.sleep(6000);

		if (driver.getPageSource().contains("Agents are successfully registered.")) {
			logger.info("Verification of AgentSupport adding Successfull.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			captureScreen(driver, "Add agent");
			logger.info("Verification of AgentSupport adding failed.");
			Assert.assertTrue(false);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}

		adminAccount.closeAgentPopUp();
		Thread.sleep(2000);
		adminAccount.logoutTeaml();
		Thread.sleep(3000);

		lp.setAdminMailId(TAEmail);
		logger.info("Agent Support email is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.setAdminPassword(TAPass);
		logger.info("Agent Support password is entered.");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		lp.clickLoginbtn();
		Thread.sleep(6000);

		if (driver.getPageSource().contains(TAfname)) {
			logger.info("Verification of AgentSupport login Successfull.");
			Assert.assertTrue(true);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		} else {
			captureScreen(driver, "Agent loginned.");
			logger.info("Verification of AgentSupport login failed.");
			Assert.assertTrue(false);
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		}

	}
}
