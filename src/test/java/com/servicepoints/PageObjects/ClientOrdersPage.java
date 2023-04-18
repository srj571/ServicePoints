package com.servicepoints.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ClientOrdersPage {
	
	WebDriver rdriver;
	
	public ClientOrdersPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//a)[15]")
	WebElement ordersTab;
	
	public void clickOnOrdersTab() {
		ordersTab.click();
	}
	
	@FindBy(xpath="//input[@id='order_product_search']")
	WebElement txtSearch;
	
	public void sendPnameinSearch(String nvar) {
		txtSearch.sendKeys(nvar);
	}
	
	@FindBy(xpath="(//div)[65]")
	WebElement statusDrop;
	
	public void clickOnStatusDrop() {
		statusDrop.click();
	}
	
	@FindBy(xpath="//li[contains(text(),'Not quoted')]")
	WebElement notQuotedSel;

	public void clickOnNotQuotedSel() {
		notQuotedSel.click();
	}
	
	@FindBy(xpath="//li[contains(text(),'Processing')]")
	WebElement processingTab;
	
	public void clickOnProcessingSel() {
		processingTab.click();
	}
	
	@FindBy(xpath="//div[@class='js_table_expand_two table_expand_abso']")
	WebElement firstDiv;
	
	public void clickOnFDiv() {
		firstDiv.click();
	}
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement dropdownSearch;
	
	public void dropdownSearch(String status) {
		dropdownSearch.sendKeys(status);
	}
	
	@FindBy(xpath="(//div)[65]")
	WebElement orderStatus;
	
	public void clickOnDropdown() {
		orderStatus.click();
	}
	
	@FindBy(xpath="//a[@id='raiseDispute']")
	WebElement openDspbtn;
	
	public void clickOnOpenDspbtn() {
		openDspbtn.click();
	}
	
	@FindBy(xpath="//select[@id='dispute_issue_list']")
	WebElement dspIssue;
	
	public void handleDspIssues() {
		Select sel=new Select(dspIssue);
		sel.selectByIndex(1);
	}
	
	@FindBy(xpath="//select[@id='dispute_type_list']")
	WebElement dspSolutions;
	
	
	public void handleDspSolution() {
		Select sl=new Select(dspSolutions);
		sl.selectByIndex(3);
	}
	
	@FindBy(xpath="(//input[@name='order_mapping_ids[]'])[6]")
	WebElement checkF;
	
	@FindBy(xpath="(//input[@name='order_mapping_ids[]'])[7]")
	WebElement checkS;
	
	@FindBy(xpath="(//div[@class='mb-2 p-2 sacol withedit multi_list bg-transparent'])[2]//input[@name='order_mapping_ids[]' and @type='checkbox']")
	List<WebElement> checkBoxTable;
	
	public void clickOnCheckBox() {
	//	checkF.click();
		//checkS.click();
		for(WebElement checkbox : checkBoxTable) {
			checkbox.click();
		}
	}
	
	@FindBy(xpath="//input[@id='saveDispute']")
	WebElement SaveDsp;
	
	public void SaveDispute() {
		SaveDsp.click();
	}
	
	@FindBy(xpath="//textarea[@id='textarea']")
	WebElement enterQueries;
	
	public void sendQueries(String queries) {
		enterQueries.sendKeys(queries);
	}
	
	
	
}
