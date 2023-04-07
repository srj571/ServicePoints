package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminSupplierPage {
	
	WebDriver rdriver;
	public AdminSupplierPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Team']")
	WebElement teamTab;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement AddAgentbtn;
	
	@FindBy(xpath="//input[@placeholder='First name']")
	WebElement fname;
	
	@FindBy(xpath="//input[@placeholder='Last name']")
	WebElement lname;
	
	@FindBy(xpath="//input[@placeholder='+31']")
	WebElement ccode;
	
	@FindBy(xpath="//input[@placeholder='Phone number']")
	WebElement mnum;
	
	@FindBy(xpath="//input[@placeholder='E-mail address']")
	WebElement txtemail;
	
	@FindBy(xpath="//input[@id='new_password']")
	WebElement txtpass;
	
	@FindBy(xpath="//input[@id='confirm_password']")
	WebElement txtcpass;
	
	@FindBy(xpath="//input[@id='signupagent']")
	WebElement addAgentbtn;
	
	@FindBy(css = "h5[class='mb-3'] span[aria-hidden='true']")
	WebElement closebtn;
	
	@FindBy(xpath="(//*[name()='svg'])[12]")
	WebElement logOutBtn;
	
	public void clickOnTeamTab() {
		teamTab.click();
	}
	
	public void clickOnAddNewAgent() {
		AddAgentbtn.click();
	}
	
	public void sendFName(String finame) {
		fname.sendKeys(finame);
	}
	
	public void sendLname(String ltname) {
		lname.sendKeys(ltname);
	}
	
	public void sendCCode(String code) {
		ccode.sendKeys(code);
	}
	
	public void sendmobNum(String num) {
		mnum.sendKeys(num);
	}
	
	public void sendEmail(String email) {
		txtemail.sendKeys(email);
	}
	
	public void sendPass(String pass) {
		txtpass.sendKeys(pass);
	}
	
	public void sendcpass(String cpass) {
		txtcpass.sendKeys(cpass);
	}
	
	public void clickOnAddAgentbtn() {
		addAgentbtn.click();
	}
	
	public void closePopup() {
		closebtn.click();
	}
	
	public void logOut() {
		logOutBtn.click();
	}
	
}
