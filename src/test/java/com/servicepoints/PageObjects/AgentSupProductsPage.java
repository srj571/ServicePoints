package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentSupProductsPage {
	
	WebDriver rdriver;
	public AgentSupProductsPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Products']")
	WebElement ProductsTab;
	//div[normalize-space()='Products']
	@FindBy(xpath="//label[normalize-space()='Quotations clients']")
	WebElement QuotationsClients;
	
	@FindBy(xpath="//input[@placeholder='Products, Stores or Clients']")
	WebElement serachField;
	
	@FindBy(xpath="//a[@class='table_expand_abso cp_link']")
	WebElement divfield;
	
	@FindBy(xpath="(//input[@placeholder='1 Pcs price'])[1]")
	WebElement txt1Pcs;
	
	@FindBy(xpath="(//input[@placeholder='2 Pcs price'])[1]")
	WebElement txt2Pcs;
	
	@FindBy(xpath="(//input[@placeholder='3 Pcs price'])[1]")
	WebElement txt3Pcs;
	
	@FindBy(xpath="(//input[@placeholder='4 Pcs price'])[1]")
	WebElement txt4Pcs;
	
	@FindBy(xpath="//button[normalize-space()='Submit quotation']")
	WebElement submitquote;
	
	@FindBy(xpath="//span[@class='badge-mod badge-info']")
	WebElement QuoteStatus;
	
	public void getProductsPage() {
		ProductsTab.click();
	}
	
	public void clickQuotationsClientsTab() {
		QuotationsClients.click();
	}
	
	public void searchProductName(String product) {
		serachField.sendKeys(product);
	}
	
	public void clickOnfdiv() {
		divfield.click();
	}
	
	
	
	public void firstPcsPrice(String fpcs) {
		txt1Pcs.sendKeys(fpcs);
	}
	
	public void secPcsPrice(String spcs) {
		txt2Pcs.sendKeys(spcs);
	}
	
	public void thirdPcsPrice(String tpcs) {
		txt3Pcs.sendKeys(tpcs);
	}
	
	public void forthPcsPrice(String ffpcs) {
		txt4Pcs.sendKeys(ffpcs);
	}
	
	public void clickOnSubmitQuote() {
		submitquote.click();
	}
	
	public boolean checkSubmitQuotebtn() {
		boolean tr=submitquote.isDisplayed();
		return tr;
	}
	
	public boolean isSubmitQuotebtnEnabled() {
		boolean submitQuote=submitquote.isEnabled();
		return submitQuote;
	}
	
	public String getStatus() {
		String text=QuoteStatus.getText();
		return text;
	}
	
	
	//AskForPriceChangeFunctionality
	@FindBy(xpath="//button[normalize-space()='Ask for price change']")
	WebElement askForPriceCh;
	
	public void clckOnAskForPrceChng() {
		askForPriceCh.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Submit the new price']")
	WebElement sbmtNewPrice;
	
	public void clickOnSbmtNewPrice() {
		sbmtNewPrice.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='I have notified the SP Account manager']")
	WebElement notifyPopUp;

	public void closeNotifyPopUp() {
		notifyPopUp.click();
	}
	
	public boolean clsNotifyPopUpisDisplays() {
		boolean val=notifyPopUp.isDisplayed();
		return val;
	}
	
	@FindBy(xpath="//label[@id='is_quotation_or_product_label3']")
	WebElement productsTab;
	
	public void clickOnProductsTab() {
		productsTab.click();
	}
	
	@FindBy(xpath="//div[@class='leftside-menu-fixed-bottom --transition-ease']//a[2]")
	WebElement logout;
	
	public void logpOutAgent() {
		logout.click();
	}
	
	@FindBy(xpath="//body/div[@id='new_price_popup']/div/div[1]")
	WebElement divIhvNotify;
	
	public boolean isDivVisible() {
		boolean div=divIhvNotify.isDisplayed();
		return div;
	}
	
	@FindBy(xpath="//button[normalize-space()='Update quotation']")
	WebElement updateQuotation;
	
	public void updateQuotation() {
		updateQuotation.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Delete quotation']")
	WebElement deletequote;
	
	public void deleteQuote() {
		deletequote.click();
	}
	
	@FindBy(xpath="//button[@class='btn btn-danger deleteQuotationConfirmedBtn btn-sm']")
	WebElement yesImSure;
	
	public void clickOnYesImSure() {
		yesImSure.click();
	}
	
	@FindBy(xpath="(//button[@class='br10 btn btn-border btn-sm'])[1]")
	WebElement dropDownCancelbtn;
	
	public boolean checkCancelBtnDisplayed() {
		boolean value=dropDownCancelbtn.isDisplayed();
		return value;
	}
	
	
	
	

}
