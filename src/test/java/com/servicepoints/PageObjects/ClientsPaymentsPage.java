package com.servicepoints.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClientsPaymentsPage {
	
	WebDriver rdriver;
	public ClientsPaymentsPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Payments']")
	WebElement paymentsTab;
	
	public void  goToPaymentsPage() {
		paymentsTab.click();
	}
	
	@FindBy(xpath="//input[@id='fieldSelectorId']")
	WebElement txtAmount;
	
	public void sendAmount(String amount) {
		txtAmount.sendKeys(amount);
	}
	
	@FindBy(xpath="(//button[@type='submit'])[1]")
	WebElement payNowBtn;
	
	public void clickOnPayNowBtn() {
		payNowBtn.click();
	}
	
	@FindBy(xpath="//textarea[@id='textarea']")
	WebElement txtRemark;
	
	public void sendRemarkWhilePay(String name) {
		txtRemark.sendKeys(name);
	}
	
	@FindBy(xpath="//input[@id='submitManualPayment']")
	WebElement iHavePaidBtn;
	
	public void clickOnIHavePaid() {
		iHavePaidBtn.click();
	}
	
	@FindBy(xpath="(//div)[252]")
	WebElement manualBankTransferDiv;
	
	public void waitTillBankDiv(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOf(manualBankTransferDiv));
	}
	
	@FindBy(xpath="//input[@id='validatedCustomFile']")
	WebElement browseFile;
	
	@FindBy(css = "#validatedCustomFile")
	WebElement browseFileFunct;
	
	public void sendFileInText() {
		browseFileFunct.sendKeys("/Home/Downloads/A324.pdf");
	}
	
	public void clickOnBrowseEle() {
		browseFile.click();
	}
	
	public void sendFile() {
		browseFile.sendKeys("/Home/Downloads/A324.pdf");
	}
	
	public void sendFileAsPath(WebDriver driver, String filePath) {
		 // Set the value of the file input element using JavaScript
	     String script = "arguments[0].setAttribute('value', arguments[1]);";
	     ((JavascriptExecutor) driver).executeScript(script, browseFileFunct, filePath);
	}
	
    

     
}
