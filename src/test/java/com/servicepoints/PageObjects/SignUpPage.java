package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {
	
	WebDriver rdriver;
	
	public SignUpPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//a[@class='nav-link']")
	WebElement signUpLink;
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@name='country_code']")
	WebElement countryCode;
	
	@FindBy(xpath="//input[@name='phoneNumber']")
	WebElement txtMobileNum;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txtEmail;	
	
	@FindBy(xpath="//input[@name='daily_orders']")
	WebElement txtDailyOrders;
	
	@FindBy(xpath="//input[@id='new_password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='confirm_password']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@id='terms']")
	WebElement checkboxTerms;
	
	@FindBy(xpath="//input[@id='daily_orders_count_terms']")
	WebElement checkboxCountTerms;
	
	@FindBy(xpath="//input[@id='register_signup']")
	@CacheLookup
	WebElement btnSignUp;
	
	public void signUpUser() {
		signUpLink.click();
	}
	
	public void setUserFirstName(String uname) {
		txtFirstName.sendKeys(uname);
	}
	
	public void setUserLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setCountryCode(String code) {
		countryCode.sendKeys(code);
	}
	
	public void setMobileNum(String mnum) {
		txtMobileNum.sendKeys(mnum);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setDailyOrder(String order) {
		txtDailyOrders.sendKeys(order);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setCofirmPass(String cpass) {
		txtConfirmPassword.sendKeys(cpass);
	}
	
	public void clickTermCheckBox() {
		checkboxTerms.click();
	}
	
	public void clickOrderCheckBox() {
		checkboxCountTerms.click();
	}
	
	public void clickBtnSignUp() {
		btnSignUp.click();
	}
}








