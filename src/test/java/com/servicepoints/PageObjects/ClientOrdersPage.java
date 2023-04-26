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
	public WebElement openDspbtn;
	
	public void clickOnOpenDspbtn() {
		openDspbtn.click();
	}
	
	@FindBy(xpath="//select[@id='dispute_issue_list']")
	WebElement dspIssue;
	
	public void handleDspIssues() {
		Select sel=new Select(dspIssue);
		sel.selectByIndex(1);
	}
	
	public void selectOtherDspOption() {
		Select sel=new Select(dspIssue);
		sel.selectByIndex(4);
	}
	
	@FindBy(xpath="//textarea[@id='otherIssue']")
	WebElement txtotherOption;
	
	public void setOtherInfo(String text) {
		txtotherOption.sendKeys(text);
	}
	
	
	@FindBy(xpath="//select[@id='dispute_type_list']")
	WebElement dspSolutions;
	
	
	public void handleDspSolution() {
		Select sl=new Select(dspSolutions);
		sl.selectByIndex(3);
	}
	
	public void refundSolutionDsp() {
		Select sl=new Select(dspSolutions);
		sl.selectByIndex(1);
	}
	
	public void resendSolutionDsp() {
		Select sl=new Select(dspSolutions);
		sl.selectByIndex(2);
	}
	
	@FindBy(xpath="//input[@name='disput-file']")
	WebElement sendFiles;
	
	public void sendFiles(String str) {
		sendFiles.sendKeys(str);
	}
	
	@FindBy(xpath="(//input[@name='order_mapping_ids[]'])[6]")
	WebElement checkF;
	
	@FindBy(xpath="(//input[@name='order_mapping_ids[]'])[7]")
	WebElement checkS;
	
	@FindBy(xpath="(//div[@class='mb-2 p-2 sacol withedit multi_list bg-transparent'])[2]//input[@name='order_mapping_ids[]' and @type='checkbox']")
	List<WebElement> checkBoxTable;
	
	public void clickOnCheckBox() {
	
		for(WebElement checkbox : checkBoxTable) {
			checkbox.click();
		}
	}
	
	public void clickOnFirstCheckBoxForDsp() {
		if(checkBoxTable.size() > 0) {
			checkBoxTable.get(0).click();
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
	
	@FindBy(xpath="(//a[normalize-space()='Special request'])[1]")
	WebElement specialRequestBtn;
	
	public void clickOnSpecialRequest() {
		specialRequestBtn.click();
	}
	
	@FindBy(xpath="//select[@id='select']")
	WebElement requestDropDown;
	
	public void handleRequestDropdown() {
		Select sel=new Select(requestDropDown);
		sel.selectByIndex(1);	
	}
	
	@FindBy(xpath="(//div[@class='mb-2 p-2 sacol withedit multi_list bg-transparent'])[1]//input[@name='order_mapping_ids[]' and @type='checkbox']")
	List<WebElement> specialRequestCheckBoxses;
	
	public void clickOnSpecialRequestchecks() {
		for(WebElement checks: specialRequestCheckBoxses) {
			checks.click();
		}
	}
	
	public void clickOnFirstTwoCheckBoxes() {
		for(int i=0;i<=1; i++) {
			specialRequestCheckBoxses.get(i).click();
		}
	}
	
	public void clickOnTwoThirdCheckBoxes() {
		for(int i=2;i<=3;i++) {
			specialRequestCheckBoxses.get(i).click();
		}
	}
	
	@FindBy(xpath="//input[@id='disputeButton']")
	WebElement sendRequestbtn;
	
	public void clickOnSendRequestBtn() {
		sendRequestbtn.click();
	}
	
	@FindBy(xpath="//a[contains(text(),'Cancel order')]")
	WebElement cancelOrderBtn;
	
	public void clickOnCancelBtn() {
		cancelOrderBtn.click();
	}
	
	@FindBy(xpath="(//div[@class='mb-2 multi_data'])[1]//input[@name='cancel_items[]' and @type='checkbox']")
	List<WebElement> cancelOrderCheckBoxes;
	
	public void clickOnFirstCheckbox() {
		if(cancelOrderCheckBoxes.size() > 0) {
			cancelOrderCheckBoxes.get(0).click();
		}
	}	
	
	@FindBy(xpath="//button[normalize-space()='Submit']")
	WebElement submitCancelOrder;
	
	public void clickOnSubmitOrder() {
		submitCancelOrder.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement cancelBtn;
	
	public void clickOnCancelOrderBtn() {
		cancelBtn.click();
	}

	@FindBy(xpath="(//li[@id='select2--result-xd2z-2'])[1]")
	WebElement fullfillTabInSearch;
	
	public void clickOnFulfillTab() {
		fullfillTabInSearch.click();
	}

	@FindBy(xpath="(//li[@id='select2-b5v1-result-bmjm-1'])[1]")
	WebElement fProcessingTab;
	
	public void clickOnFProcessingTab() {
		fProcessingTab.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Show dispute']")
	WebElement showDispute;
	
	public void clickOnShowDispute() {
		showDispute.click();
	}
	
	@FindBy(xpath="//li[@role='option']")
	WebElement proTab;
	
	public void clickOnProcessingTab() {
		proTab.click();
	}
	
	@FindBy(xpath="(//span[@class='btn btn-border btn-block mb-3 '])[1]")
	WebElement disputeHistory;
	
	public void clickOnDispHistory() {
		disputeHistory.click();
	}
}
