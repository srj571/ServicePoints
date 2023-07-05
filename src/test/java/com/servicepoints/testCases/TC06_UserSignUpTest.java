package com.servicepoints.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;

import com.servicepoints.PageObjects.SignUpPage;

public class TC06_UserSignUpTest extends BaseClass {

	@Test
	public void testUserSignUp() throws IOException, InterruptedException {
		SignUpPage sp = new SignUpPage(driver);
		sp.signUpUser();
		Thread.sleep(3000);

		sp.setUserFirstName(fname);
		logger.info("First Name is entered.");
		Thread.sleep(1000);

		sp.setUserLastName(lname);
		logger.info("Last Name is entered.");
		Thread.sleep(1000);

		sp.setCountryCode(code);
		logger.info("Country code is entered.");
		Thread.sleep(1000);

		String num = BaseClass.getRandomNum();
		sp.setMobileNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(1000);

//		String val=BaseClass.getRandomString();
//		String email=val+"@yopmail.com";	
		sp.setEmail(userEmail);
		logger.info("Email is entered.");
		Thread.sleep(1000);

		sp.setDailyOrder();
		logger.info("Order is set.");
		Thread.sleep(1000);

		sp.selectReferenceByDropDown();
		Thread.sleep(1000);

		sp.setPassword(password);
		logger.info("Password is entered.");
		Thread.sleep(1000);

		sp.setCofirmPass(cpassword);
		logger.info("Confirm password is entered.");
		Thread.sleep(1000);

		sp.clickTermCheckBox();
		sp.clickOrderCheckBox();
		Thread.sleep(2000);
		sp.clickBtnSignUp();
		Thread.sleep(5000);

		if (driver.getPageSource().contains("Congratulations! You have successfully signed up for Service Points.")) {
			logger.info("User is Successfully Signed up.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		} else {
			captureScreen(driver, "UserSignUp");
			logger.info("User is Failed to Signed up.");
			Assert.assertTrue(false);
		}

	}
}
