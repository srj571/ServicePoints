package com.servicepoints.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamleaderProductsPage {

	WebDriver rdriver;
	public TeamleaderProductsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//div[contains(text(),'Products')])[1]")
	WebElement productsTab;
	
	public void clickOnProductsTab() {
		productsTab.click();
	}
	
	@FindBy(xpath="(//input[@id='client_product_search'])[1]")
	WebElement productSearch;
	
	public void searchProduct(String product) {
		productSearch.sendKeys(product);
	}
	
	@FindBy(xpath="(//a[@class='table_expand_abso cp_link'])[1]")
	WebElement ftab;
	
	public void clickOnFtab() {
		ftab.click();
	}
	
	@FindBy(xpath="(//div)[76]")
	WebElement quoteTab;
	
	public void clickOnQouteTab() {
		quoteTab.click();
	}
	
	@FindBy(xpath="(//button[normalize-space()='Accept selected quotation'])[1]")
	WebElement acceptQuoteBtn;
	
	public void clickOnAcceptQuoteBtn() {
		acceptQuoteBtn.click();
	}
	
	public void scrollTillAcceptQuoteBtn(WebDriver driver) {
		JavascriptExecutor jse=(JavascriptExecutor) driver;
		jse.executeScript("arguments[0],scrollIntoView;", acceptQuoteBtn);
	}
	
	@FindBy(xpath="(//span[contains(@role,'presentation')])[1]")
	WebElement productStatusDrop;
	
	@FindBy(xpath="(//span[@title='All clients'])[1]")
	WebElement allClientDrop;
	
	@FindBy(xpath="(//input[@role='searchbox'])[1]")
	WebElement searchField;
	
	@FindBy(xpath="(//li[@role='option'])[1]")
	WebElement firstTab;
	
	public void handleOrderStatusDropdown(String status) throws InterruptedException {
		productStatusDrop.click();
		Thread.sleep(1000);
		
		searchField.sendKeys(status);
		Thread.sleep(1000);
		
		firstTab.click();
		Thread.sleep(2000);
	}
	
	public void handleClientNameDropdown(String name) throws InterruptedException {
		allClientDrop.click();
		Thread.sleep(1000);
		
		searchField.sendKeys(name);
		Thread.sleep(1000);
		
		firstTab.click();
		Thread.sleep(2000);
	}
	
	@FindBy(xpath="(//a[normalize-space()='Special request'])[1]")
	WebElement specialRequstDropdown;
	
	public void clickOnSpecialrequestDrop() {
		specialRequstDropdown.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='I need a sizing chart'])[1]")
	WebElement needSizingChartSp;
	
	public void clickOnNeedSizingChartSp() {
		needSizingChartSp.click();
	}
	
	@FindBy(xpath="(//p[@class='special_request_messgae'])[1]")
	WebElement iNeedSizeChartMessage;
	
	public String getMesssageOfINeedSizeChart() {
		String val=iNeedSizeChartMessage.getText();
		return val;
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Submit'])[1]")
	WebElement submitBtn;
	
	public void clickOnSubmitBtn() {
		submitBtn.click();
	}
	
	@FindBy(xpath="(//span[@class='badge-mod badge-warning'])[1]")
	WebElement openSpStatus;
	
	public String getOpenSPStatus() {
		String val=openSpStatus.getText();
		return val;
	}
	
	@FindBy(xpath="(//span[normalize-space()='Closed special request'])[1]")
	WebElement closedSpecialRequestTag;
	
	
	public String getClosedSpRequestTag() {
		String val=closedSpecialRequestTag.getText();
		return val;
	}
	
	@FindBy(xpath="(//button[normalize-space()='Accept'])[1]")
	WebElement acceptAskForPriceChange;
	
	public void clickOnAcceptAskForPriceChangeBtn() {
		acceptAskForPriceChange.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Yes, I am sure'])[1]")
	WebElement yesImSureBtn;
	
	public void clickOnYesImSureBtn() {
		yesImSureBtn.click();
	}
	
	@FindBy(xpath="(//button[normalize-space()='Submit'])[1]")
	WebElement submitBtnOnDisableVariantPopup;
	
	public void clickSubmitBtnOnDisableVariantPopup() {
		submitBtnOnDisableVariantPopup.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtnOnYouHaveAcceptedTheNewPrice;
	
	public void clickCloseBtnOnYouHaveAcceptedTheNewPrice() {
		closeBtnOnYouHaveAcceptedTheNewPrice.click();
	}
	
}
