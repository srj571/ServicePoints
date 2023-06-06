package com.servicepoints.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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
	
	@FindBy(xpath="//a[normalize-space()='Show dispute']")
	WebElement showDsp;

	public void clickOnShowDsp() {
		showDsp.click();
	}
	
	public void scrollTillShowDispute(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", showDsp);
	}
	
	@FindBy(xpath="//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel full_expand']")
	List<WebElement> allDisps;
	
	@FindBy(xpath="//a[@class='btn btn-border btn-block mt-3 supportDispute linkactive']")
	List<WebElement> allShowDspBtn;
	
	@FindBy(xpath = "//h5[@id='orderDisputeId']//button[@aria-label='Close']")
	WebElement closeDisputeAgent;
	
	public void handleEachDispute(WebDriver driver) throws InterruptedException {
		for(int i=0; i<allDisps.size();i++) {
			for(int j=0; j<allShowDspBtn.size();j++) {
				allDisps.get(i).click();
				Thread.sleep(2000);
				
				scrollTillShowDispute(driver);
				Thread.sleep(3000);
				
				allShowDspBtn.get(i).click();
				Thread.sleep(4000);
				
				closeDisputeAgent.click();
				Thread.sleep(2000);
				break;
			}
		}
	}
	
	@FindBy(xpath="//a[normalize-space()='Delete dispute']")
	WebElement deleteDspBtn;
	
	public void clickOnDeleteDisputeBtn() {
		deleteDspBtn.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	WebElement submitBtnOnDsp;
	
	public void clickOnSubmitBtnOnDispute() {
		submitBtnOnDsp.click();
	}
	
	@FindBy(xpath="//select[@id='disputeIssueAccept']")
	WebElement disputeDropdown;
	
	public void acceptTheDispute() {
		Select sel=new Select(disputeDropdown);
		sel.selectByIndex(1);
	}
	public void declinedTheDispute() {
		Select sel=new Select(disputeDropdown);
		sel.selectByIndex(2);
	}
	
	@FindBy(xpath = "//textarea[@id='textarea']")
	WebElement txtanswer;

	public void sendAnswer(String answer) {
		txtanswer.sendKeys(answer);
	}

	@FindBy(xpath = "//input[@value='   Send answer   ']")
	WebElement btnSendAnswer;

	public void clickOnSendAnswer() {
		btnSendAnswer.click();
	}
	
}
