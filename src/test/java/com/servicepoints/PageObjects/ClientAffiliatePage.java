package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientAffiliatePage {
	
	WebDriver rdriver;
	public ClientAffiliatePage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Affiliate']")
	WebElement affiliateTab;
	
	public void clickOnAffiliateTab() {
		affiliateTab.click();
	}
	
	@FindBy(xpath="//input[@id='url']")
	WebElement copyAffPath;
	
	public String getAffiliateLink() {
		String value=copyAffPath.getAttribute("value");
		return value;
	}
	
	
}
