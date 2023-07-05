package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.servicepoints.PageObjects.LoginPage;
import com.servicepoints.PageObjects.SignUpPage;
import com.servicepoints.utilities.ReadConfig;

public class TC02_VerifySupplierSignUp extends BaseClass {

	ReadConfig rc = new ReadConfig();

	public String baseURL = rc.getApplicationUrl();
	public String supurl = rc.getSuppllierURL();
	public String supfname = rc.getSupFirstName();
	public String suplname = rc.getSupLastName();
	public String supccode = rc.getsupCc();
	public String supemail = rc.getSupEmail();
	public String suppass = rc.getSupPass();
	public String supcpass = rc.getSupCpass();

	@Test
	public void verifySupplierSignUp() throws InterruptedException, IOException {
		driver.get(supurl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		SignUpPage sup = new SignUpPage(driver);
		sup.setUserFirstName(supfname);
		logger.info("Supplier name is entered.");
		Thread.sleep(1000);

		sup.setUserLastName(suplname);
		logger.info("Supplier last name is entered.");
		Thread.sleep(1000);

		sup.setCountryCode(supccode);
		logger.info("Country code is entered.");
		Thread.sleep(1000);

		String num = BaseClass.getRandomNum();
		sup.setMobileNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(1000);

		sup.setEmail(supemail);
		logger.info("Supplier mail is entered.");
		Thread.sleep(1000);

		sup.setPassword(suppass);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		sup.setCofirmPass(supcpass);
		Thread.sleep(1000);
		sup.clickTermCheckBox();
		sup.clickBtnSignUp();

		Thread.sleep(6000);

		if (driver.getPageSource().contains("Congratulations! You have successfully signed up for Service Points.")) {
			logger.info("Supplier is Successfully Signed up.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			captureScreen(driver, "SupplierSignUp");
			logger.info("Supplier is Failed to Signed up.");
			Assert.assertTrue(false);
		}

		driver.get(baseURL);
		Thread.sleep(4000);

		LoginPage lp = new LoginPage(driver);
		lp.setAdminMailId(supemail);
		logger.info("Mailid is entered.");
		Thread.sleep(1000);
		lp.setAdminPassword(suppass);
		logger.info("Password is entered.");
		Thread.sleep(1000);
		lp.clickLoginbtn();
		Thread.sleep(4000);

		if (driver.getTitle().contains("Admin Supplier Dashboard | Service Points")) {
			logger.info("Verification of Supplier Sign in is Successed.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			captureScreen(driver, "VerifySupSignIn");
			logger.info("Verification of Supplier Sign in is failed.");
			Assert.assertTrue(false);
		}
	}
}
