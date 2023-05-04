package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminPaymentsPage {

	WebDriver rdriver;
	public AdminPaymentsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Payments']")
	WebElement paymentsTab;
	
	public void clickOnPaymentsPage() {
		paymentsTab.click();
	}
	
	@FindBy(xpath="//input[@id='searchAmount']")
	WebElement enterAmounttxt;
	
	public void sendAmount(String amount) {
		enterAmounttxt.sendKeys(amount);
	}
	
	@FindBy(xpath="//button[@id='searchPaymentListing']")
	WebElement searchBtn;
	
	public void clickOnSearchBtn() {
		searchBtn.click();
	}
	
	@FindBy(xpath="//span[@role='combobox']")
	WebElement allAcountDropdown;
	
	public void clickOnAllAccountDrop() {
		allAcountDropdown.click();
	}
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement searchAccountName;
	
	public void searchAccountToFilter(String name) {
		searchAccountName.sendKeys(name);
	}
	
	@FindBy(xpath="(//li[@role='option'])[1]")
	WebElement ftabOfAccount;
	
	public void clickOnfTabOfAccount() {
		ftabOfAccount.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Status(All)']")
	WebElement statusDropdown;
	
	public void clickOnApprovedDrop() {
		Select sel=new Select(statusDropdown);
		sel.selectByIndex(1);
	}
	
}
