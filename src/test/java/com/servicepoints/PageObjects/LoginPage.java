package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver rdriver;
	public LoginPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(name="useremail")
	WebElement txtAdminMailID;
	
	@FindBy(xpath="//input[@name='userpassword']")
	WebElement txtAdminPass;
	
	@FindBy(id="login_signin")
	WebElement btnLogin;
	
	@FindBy(xpath="//li[@class='parsley-type']")
	WebElement mailErrorMsg;
	
	public String getErrorMsgOfInvalidMailId() {
		String val=mailErrorMsg.getText();
		return val;
	}
	
	public void setAdminMailId(String uname) {
		txtAdminMailID.sendKeys(uname);
	}
	
	public void setAdminPassword(String pass) {
		txtAdminPass.sendKeys(pass);
	}
	
	public void clickLoginbtn() {
		btnLogin.click();
	}
	
}
