package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminManualPaymentsPage {

	WebDriver rdriver;
	public AdminManualPaymentsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//div[contains(text(),'Manual Payment')])[1]")
	WebElement manualPaymentTab;
	
	public void clickOnManualPaymentTab() {
		manualPaymentTab.click();
	}
	
	@FindBy(xpath="//input[@id='searchAmountDate']")
	WebElement enterClientName;
	
	public void sendClientNameToApprovePay(String cname) {
		enterClientName.sendKeys(cname);
	}
	
	@FindBy(xpath="(//a[@class='ml-0 btn btn-border btn-sm getPaymentId'])[1]")
	WebElement markPaymentAsReceived;
	
	public void clickOnMarkPaymentAsReceived() {
		markPaymentAsReceived.click();
	}
	
	@FindBy(xpath="(//button[@name='payment_confirm'][normalize-space()='Confirm'])[1]")
	WebElement confirmBtn;
	
	public void clickOnConfirm() {
		confirmBtn.click();
	}
	
	@FindBy(xpath="(//div[@class='modal-content'])[2]")
	WebElement confirmDiv;
	
	public void waitTillConfirmBtn(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(confirmDiv));
	}
}
