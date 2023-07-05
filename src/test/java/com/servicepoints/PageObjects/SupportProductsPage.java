package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportProductsPage {

	WebDriver rdriver;
	public SupportProductsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//div[contains(text(),'Products')])[1]")
	WebElement productsTab;
	
	public void clickOnProductsTab() {
		productsTab.click();
	}
	
	@FindBy(xpath="(//input[@id='client_product_search'])[1]")
	WebElement searchProducts;
			
	public void searchProducts(String product) {
		searchProducts.sendKeys(product);
	}
	
	@FindBy(xpath="(//a[@class='table_expand_abso cp_link'])[1]")
	WebElement fdiv;
	
	public void clickOnFDiv() {
		fdiv.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='Special request'])[1]")
	WebElement specialRequest;
	
	public void clickOnSpecialRequestDrop() {
		specialRequest.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Start fullfiling']")
	WebElement startFulfillmentBtn;
	
	public void clickOnStartFulfillmentBtn() {
		startFulfillmentBtn.click();
	}
	
	@FindBy(xpath="//div[@id='special_request_modal']//button[@class='btn btn-border btn-sm mb-3 start_fullfilling_order_num']")
	WebElement previousOrderQuote;
	
	public void clickOnPreviousOrderQuote() {
		previousOrderQuote.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Start fulfilling'])[1]")
	WebElement startFulfillingBtn;
	
	public void clickOnStartFulfilling() {
		startFulfillingBtn.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtn;
	
	public void clickOnCloseBtn() {
		closeBtn.click();
	}
	
	@FindBy(xpath="(//span[@class='badge-mod badge-success'])[1]")
	WebElement orderStatus;
	
	public String getOrderStatus() {
		String text=orderStatus.getText();
		return text;
	}
	
	@FindBy(xpath="(//a[normalize-space()='I want real factory pictures'])[1]")
	WebElement iWantRealFactoryPic;
	
	public void clickOnIWantRealFactoryPicSp() {
		iWantRealFactoryPic.click();
	}
	
	@FindBy(xpath="(//p[@class='special_request_messgae'][normalize-space()='I want real factory pictures'])[1]")
	WebElement iWantRealFactoryPicspText;
	
	public String getTextOFiWantRealFactoryPicSp() {
		String val=iWantRealFactoryPicspText.getText();
		return val;
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Submit'])[1]")
	WebElement submitBtn;
	
	public void clickOnSubmitBtn() {
		submitBtn.click();
	}
	
	@FindBy(xpath="(//span[@class='badge-mod badge-warning'])[1]")
	WebElement openSpRequestStatus;
	
	public String getSPStatus() {
		String val=openSpRequestStatus.getText();
		return val;
	}
	
	@FindBy(xpath="(//span[normalize-space()='Closed special request'])[1]")
	WebElement specialRequestStatus;
	
	public String getSpecialRequstStatus() {
		String val=specialRequestStatus.getText();
		return val;
	}
	
	@FindBy(xpath="(//div)[76]")
	WebElement newQuoteTab;
	
	public void clickOnNewQuoteTab() {
		newQuoteTab.click();
	}
	
	@FindBy(xpath="(//button[normalize-space()='Accept'])[1]")
	WebElement acceptBtnAskForPriceChange;
	
	public void clickOnAcceptBtnAskForPriceChange() {
		acceptBtnAskForPriceChange.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Yes, I am sure'])[1]")
	WebElement yesImSureBtn;
	
	public void clickOnYesImSureBtn() {
		yesImSureBtn.click();
	}
	
	@FindBy(xpath="(//button[normalize-space()='Submit'])[1]")
	WebElement submitBtnOnCantAbleToQuoteBtn;
	
	public void clickSubmitBtnOnCantAbleToQuoteBtn() {
		submitBtnOnCantAbleToQuoteBtn.click();
	}
	
	@FindBy(xpath="(//button[contains(@type,'button')][normalize-space()='Close'])[1]")
	WebElement closeBtnOnAcceptingNewPricePopup;
	
	public void clickCloseBtnOnAcceptingNewPricePopup() {
		closeBtnOnAcceptingNewPricePopup.click();
	}
	
}
