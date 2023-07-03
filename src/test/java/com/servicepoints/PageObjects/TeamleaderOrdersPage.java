package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TeamleaderOrdersPage {

	WebDriver rdriver;

	public TeamleaderOrdersPage(WebDriver ldriver) {
		rdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "(//div[normalize-space()='Orders'])[1]")
	WebElement ordersTab;

	public void clickOnOrdersTab() {
		ordersTab.click();
	}

	@FindBy(xpath = "(//input[@id='order_product_search'])[1]")
	WebElement searchProducts;

	public void searchProducts(String product) {
		searchProducts.sendKeys(product);
	}
	
	@FindBy(xpath="(//div)[84]")
	WebElement clickOnFDiv;
	
	public void clickOnFDiv() {
		clickOnFDiv.click();
	}
	
	@FindBy(xpath="//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel']//div[@class='col table-border-right table_item_block_md'][3]//span[@class='badge-mod badge-warning']")
	WebElement orderStatusOnSupportOrdersPage;
	
	public String getOrderStatusFromSupportSide() {
		String text=orderStatusOnSupportOrdersPage.getText();
		return text;
	}
}
