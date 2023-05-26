package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentHomePage {
	
	WebDriver rdriver;
	public AgentHomePage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[@class='leftside-menu-fixed-bottom --transition-ease']//a[1]//*[name()='svg']")
	WebElement notificationSvg;
	
	public void clickOnNotification() {
		notificationSvg.click();
	}
	
	@FindBy(xpath="(//a[contains(text(),'View')])[1]")
	WebElement viewBtn;
	
	public void clickOnViewBtnNotification() {
		viewBtn.click();
	}
	
	
}
