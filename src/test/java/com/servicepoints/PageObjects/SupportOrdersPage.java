package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportOrdersPage {

	WebDriver rdriver;
	public SupportOrdersPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//div[normalize-space()='Orders'])[1]")
	WebElement ordersTab;
	
	public void clickOnOrdersTab() {
		ordersTab.click();
	}
	
	@FindBy(xpath="(//input[@id='order_product_search'])[1]")
	WebElement searchProduct;
	
	public void sendProductName(String product) {
		searchProduct.sendKeys(product);
	}
	
	@FindBy(xpath="(//div[@class='js_table_expand_two table_expand_abso'])[1]")
	WebElement fdiv;
	
	public void clickOnFDiv() {
		fdiv.click();
	}
	
	@FindBy(xpath="//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel']//div[@class='col table-border-right table_item_block_md'][3]//span[@class='badge-mod badge-warning']")
	WebElement orderStatusOnSupportOrdersPage;
	
	public String getOrderStatusFromSupportSide() {
		String text=orderStatusOnSupportOrdersPage.getText();
		return text;
	}
}
