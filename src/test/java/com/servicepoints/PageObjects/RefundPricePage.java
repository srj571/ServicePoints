package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RefundPricePage {
	
	WebDriver rdriver;
	
	public RefundPricePage(WebDriver ldriver) {
		rdriver=ldriver;
		
		PageFactory.initElements(ldriver, this);
	}
	
	
	@FindBy(xpath="RefundPricePage")
	WebElement costsPrice;
	
	public String getCostsPrice() {
		String cost=costsPrice.getText();
		return cost;
	}

	@FindBy(xpath="//div[@class='user_dropdown_text']/span[@class='balance_text']")
	WebElement balance;
	
	public String getClientBalance() {
		String balanceClient=balance.getText();
		return balanceClient;
	}
}
