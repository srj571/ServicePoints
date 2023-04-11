package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientProductPage {
	
	WebDriver rdriver;
	public ClientProductPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Products')]")
	WebElement productTab;
	
	@FindBy(xpath="//input[@id='client_product_search']")
	WebElement searchField;
	
	@FindBy(xpath="//a[@class='table_expand_abso cp_link']")
	WebElement divField;
	
	@FindBy(xpath="(//div)[83]")
	WebElement quoteTab;
	
	@FindBy(xpath="//button[normalize-space()='Accept selected quotation']")
	WebElement acceptbtn;
	
	//Quotation accepted successfully.
	
	@FindBy(xpath="//span[normalize-space()='Quotation accepted']")
	WebElement statusEle;
	
	public void getProductsPage() {
		productTab.click();
	}
	
	public void searchProduct(String pname) {
		searchField.sendKeys(pname);
	}
	
	public void selectProductTab() {
		divField.click();
	}
	
	public void selectQuoteTab() {
		quoteTab.click();
	}
	
	public void selectAcceptQuoteBtn() {
		acceptbtn.click();
	}
	
	public String getStatus() {
		String status=statusEle.getText();
		return status;
	}
	
}
