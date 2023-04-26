package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AgentDisputesPage {
	
	WebDriver rdriver;
	public AgentDisputesPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	
	@FindBy(xpath="//div[normalize-space()='Disputes']")
	WebElement diputesTab;
	
	public void clickOnDisputesTab() {
		diputesTab.click();
	}
	
	@FindBy(xpath="//input[@id='order_product_search']")
	WebElement searchDspPro;
	
	public void searchProductForDsp(String pdsp) {
		searchDspPro.sendKeys(pdsp);
	}
	
	@FindBy(xpath="(//div[@class='js_table_expand_two table_expand_abso full_expand'])[1]")
	WebElement fdivShowDsp;
	
	public void clickOnFrstDsp() {
		fdivShowDsp.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='Show dispute'])[1]")
	WebElement showDsp;

	public void clickOnShowDsp() {
		showDsp.click();
	}
	
	@FindBy(xpath="//select[@id='disputeIssueAccept']")
	WebElement dspDropdown;
	
	public void selectDspStatus() {
		Select sel=new Select(dspDropdown);
		sel.selectByIndex(1);
	}
	
	public void selectDspStatusToDeclined() {
		Select sel=new Select(dspDropdown);
		sel.selectByIndex(2);
	}
	
	@FindBy(xpath="//textarea[@id='textarea']")
	WebElement txtanswer;
	
	public void sendAnswer(String answer) {
		txtanswer.sendKeys(answer);
	}
	
	@FindBy(xpath="//input[@value='   Send answer   ']")
	WebElement btnSendAnswer;
	
	public void clickOnSendAnswer() {
		btnSendAnswer.click();
	}
	
	@FindBy(xpath="//a[contains(text(),'Special requests')]")
	WebElement Srteamleader;
	
	public void goToSRTabInTL() {
		Srteamleader.click();
	}
	
	@FindBy(xpath="//a[@id='specialDispute']")
	WebElement specialRequestTab;
	
	public void clickOnSpecialRequestTab() {
		specialRequestTab.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Open']")
	WebElement openInSpecialReTab;
	
	public void clickOnOpenTabInSreq() {
		openInSpecialReTab.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='Show request'])[1]")
	WebElement showRequestTab;
	
	public void clickOnShowRequestTab() {
		showRequestTab.click();
	}
	
	@FindBy(xpath="//input[@id='disputeButton']")
	WebElement sendAnsForSpRequest;
	
	public void clickOnSendAnsSpRequest() {
		sendAnsForSpRequest.click();
	}
	
	@FindBy(xpath="//textarea[@id='answerSupplier']")
	WebElement txtAnsFieldSpReq;
	
	public void setTxtAnsSpRequest(String ans) {
		txtAnsFieldSpReq.sendKeys(ans);
	}
	
	@FindBy(xpath="//input[@id='uploadFile']")
	WebElement sendFile;
	
	public void sendFileToInASpReq(String file) {
		sendFile.sendKeys(file);
	}
	
	
}
