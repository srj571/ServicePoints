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
}
