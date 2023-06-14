package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	
	@FindBy(xpath="(//span[@role='combobox'])[1]")
	WebElement countryCodeDrop;
	
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
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement searchBoxCountryCode;
	
	@FindBy(xpath="(//li[@role='option'])[1]")
	WebElement firstDivCountry;
	
	@FindBy(xpath="//select[@name='daily_orders']")
	WebElement dailyOrdersSelect;
	
	@FindBy(xpath="(//select[@name='reference_by'])[1]")
	WebElement referenceBy;
	
	public void selectReferenceByDropDown() {
		Select sel=new Select(referenceBy);
		sel.selectByIndex(1);
	}
	
	
	public void signUpUser() {
		signUpLink.click();
	}
	
	public void setUserFirstName(String uname) {
		txtFirstName.sendKeys(uname);
	}
	
	public void setUserLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setCountryCode(String code) throws InterruptedException {
		countryCodeDrop.click();
		Thread.sleep(1000);
		searchBoxCountryCode.sendKeys(code);
		Thread.sleep(1000);
		firstDivCountry.click();
	}
	
	public void setMobileNum(String mnum) {
		txtMobileNum.sendKeys(mnum);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setDailyOrder() {
		Select sel=new Select(dailyOrdersSelect);
		sel.selectByIndex(2);
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








