package com.servicepoints.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AgentClientsPage {
	WebDriver rdriver;
	public AgentClientsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Clients']")
	WebElement clientsPage;
	
	public void clickOnClientsPage() {
		clientsPage.click();
	}
	
	@FindBy(xpath="//input[@id='accountSearch']")
	WebElement searchAccount;
	
	public void searchAccount(String name) {
		searchAccount.sendKeys(name);
	}
	
	@FindBy(xpath="//a[@onclick='mLogin(this.id)']")
	WebElement loginBtn;
	
	public void clickOnLoginBtn() {
		loginBtn.click();
	}
	
	public void clickOnFClientDiv(WebDriver driver,String cNameForPayment) {
		String path="//a[contains(text(),'"+cNameForPayment+"')]";
		WebElement client=driver.findElement(By.xpath(path));
		client.click();
	}
	
	@FindBy(xpath="(//div[@class='custom-control toggle-btn custom-switch right-switch switch-mod float-left'])[3]")
	WebElement paymentPendingOrder;
	
	public boolean toggleStatusAbtPayment() {
		boolean val=paymentPendingOrder.isSelected();
		return val;
	}
	
	@FindBy(xpath="(//input[@value='1'])[3]")
	WebElement pPendingOrderON;
	
	public boolean toggleStatusPayment() {
		boolean val=pPendingOrderON.isSelected();
		return val;
	}
	
	public void clickOnPaymentToggle() {
		paymentPendingOrder.click();
	}
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-sm update-payment-pending-order pending-order']")
	WebElement yesImSureBtn;
	
	public void clickOnYesImSure() {
		yesImSureBtn.click();
	}
	
	@FindBy(xpath="(//div[@class='modal-content special_request_modal_content'])[1]")
	WebElement yesImSureBox;
	
	public void waitTillImSureDiv(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(yesImSureBox));
	}
	
	@FindBy(xpath="(//span[contains(text(),'Processing')])[1]")
	WebElement orderStatusPayment;
	
	public String getOrderStatusProcessing() {
		String statusp=orderStatusPayment.getText();
		return statusp;
	}
	
	
	
	
	
	
}
