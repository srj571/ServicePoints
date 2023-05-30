package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamleaderDisputePage {
WebDriver rdriver;
	
	public TeamleaderDisputePage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Disputes']")
	WebElement disputeTab;
	
	public void clickOnDisputeTab() {
		disputeTab.click();
	}
	
	@FindBy(xpath="//input[@id='order_product_search']")
	WebElement orderProSearch;
	
	public void searchProduct(String proName) {
		orderProSearch.sendKeys(proName);
	}
	
	@FindBy(xpath="//a[@id='closeDispute']")
	WebElement closeDisputeTab;
	
	public void clickOnCloseDisputeTab() {
		closeDisputeTab.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Declined disputes']")
	WebElement declinedDisputeTab;
	
	public void clickOnDeclinedDispute() {
		declinedDisputeTab.click();
	}
	
	@FindBy(xpath="(//span[@title='All store'])[1]")
	WebElement allStoreFilter;
	
	public void clickOnAllStoreFilter() {
		allStoreFilter.click();
	}
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement searchStoreFilter;
	
	public void searchStoreFilter(String storeName) {
		searchStoreFilter.sendKeys(storeName);
	}
	
	@FindBy(xpath="//li[@role='option']")
	WebElement fStoreTab;
	
	public void clickOnFStoreTab() {
		fStoreTab.click();
	}
	
	@FindBy(xpath="(//div[@class='js_table_expand_two table_expand_abso full_expand'])[1]")
	WebElement fDiv;
	
	public void clickOnFDiv() {
		fDiv.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='Show dispute'])[1]")
	WebElement showDsp;

	public void clickOnShowDsp() {
		showDsp.click();
	}
}
