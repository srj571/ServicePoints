package com.servicepoints.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.servicepoints.PageObjects.SignUpPage;

public class TC03_UserSignUpTest extends BaseClass{
	
	@Test
	public void testUserSignUp() throws IOException, InterruptedException {
		SignUpPage sp=new SignUpPage(driver);
		sp.signUpUser();
		Thread.sleep(3000);
		
		sp.setUserFirstName(fname);
		logger.info("First Name is entered.");
		Thread.sleep(4000);
		
		sp.setUserLastName(lname);
		logger.info("Last Name is entered.");
		Thread.sleep(4000);
		
		sp.setCountryCode(code);
		logger.info("Country code is entered.");
		Thread.sleep(4000);
		
		String num=BaseClass.getRandomNum();
		sp.setMobileNum(num);
		logger.info("Mobile number is entered.");
		Thread.sleep(3000);
		
//		String val=BaseClass.getRandomString();
//		String email=val+"@yopmail.com";	
		sp.setEmail(userEmail);
		logger.info("Email is entered.");
		Thread.sleep(4000);
		
		sp.setDailyOrder(orders);
		logger.info("Order is set.");
		
		sp.setPassword(password);
		logger.info("Password is entered.");
		
		sp.setCofirmPass(cpassword);
		logger.info("Confirm password is entered.");
		
		sp.clickTermCheckBox();
		sp.clickOrderCheckBox();
		Thread.sleep(3000);
		sp.clickBtnSignUp();
		Thread.sleep(3000);
		
		if(driver.getPageSource().contains("Congratulations! You have successfully signed up for Service Points.")) {
			logger.info("User is Successfully Signed up.");
			Assert.assertTrue(true);
			Thread.sleep(4000);
		}else {
			captureScreen(driver, "UserSignUp");
			logger.info("User is Failed to Signed up.");
			Assert.assertTrue(false);
		}
			
	}
}
