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
	
	@FindBy(xpath="//div[contains(text(),'Products')]")
	WebElement ProductsTab;
	
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
	
	public String getStatus() {
		String text=QuoteStatus.getText();
		return text;
	}
	
	
	
	
	
}
