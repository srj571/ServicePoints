package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientOrdersPage {
	
	WebDriver rdriver;
	
	public ClientOrdersPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//a)[15]")
	WebElement ordersTab;
	
	public void clickOnOrdersTab() {
		ordersTab.click();
	}
	
	@FindBy(xpath="//input[@id='order_product_search']")
	WebElement txtSearch;
	
	public void sendPnameinSearch(String nvar) {
		txtSearch.sendKeys(nvar);
	}
	
	@FindBy(xpath="(//div)[65]")
	WebElement statusDrop;
	
	public void clickOnStatusDrop() {
		statusDrop.click();
	}
	
	@FindBy(xpath="//li[contains(text(),'Not quoted')]")
	WebElement notQuotedSel;

	public void clickOnNotQuotedSel() {
		notQuotedSel.click();
	}
	
	@FindBy(xpath="//li[contains(text(),'Processing')]")
	WebElement processingTab;
	
	public void clickOnProcessingSel() {
		processingTab.click();
	}
	
	@FindBy(xpath="//div[@class='js_table_expand_two table_expand_abso']")
	WebElement firstDiv;
	
	public void clickOnFDiv() {
		firstDiv.click();
	}
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement dropdownSearch;
	
	public void dropdownSearch(String status) {
		dropdownSearch.sendKeys(status);
	}
	
	@FindBy(xpath="(//div)[65]")
	WebElement orderStatus;
	
	public void clickOnDropdown() {
		orderStatus.click();
	}
	

	
	
	
	
	
	
	
	
}
