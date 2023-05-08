package com.servicepoints.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	@FindBy(xpath="//span[normalize-space()='Quotation accepted']")
	WebElement statusEle;
	
	//Verify stop fullfilling status
	@FindBy(xpath="//a[normalize-space()='Stop fullfilling']")
	WebElement stopFullfill;
	
	@FindBy(xpath="//a[normalize-space()='Price too high, please requote']")
	WebElement requote;
		
	@FindBy(xpath="//a[contains(text(),'Special request')]")
	public WebElement specialRequestDrop;
	
	public void scrollTillSpRequestBtn(WebDriver driver)
	{
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", specialRequestDrop);
	}
	
	public void visibilityOfSPDropdown(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOf(specialRequestDrop));
	}
	
	@FindBy(xpath="//span[normalize-space()='Quotation accepted']")
	WebElement statusQuote;
	
	@FindBy(xpath="//div[@id='special_request_modal']//button[@type='button'][normalize-space()='Yes, I am sure']")
	WebElement YesImSurebtn;
	
	@FindBy(xpath="//button[normalize-space()='Select any quotation']")
	WebElement selectAnyQuote;
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeTab;
	
	@FindBy(xpath="//a[normalize-space()='Start fullfiling']")
	WebElement startFullfillDrop;
	
	@FindBy(xpath="//div[@id='special_request_modal']//button[@class='btn btn-border btn-sm mb-3 start_fullfilling_order_num']")
	WebElement previousOrder;
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Start fulfilling'])[1]")
	WebElement startFullfilBtn;
	
	@FindBy(xpath="(//span[@aria-hidden='true'][normalize-space()='×'])[3]")
	WebElement clseTabAfterQuoteAcpt;
	
	@FindBy(xpath="(//*[name()='svg'])[12]")
	WebElement logoutClient;
	
	//Required for- AskForPriceChange
	@FindBy(xpath="//div[@id='high_price_error_modal']//button[@type='button'][normalize-space()='Close']")
	WebElement clsePopUpFrmClntSide;
	
	public void waitTillCloseBtnVisible(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(clsePopUpFrmClntSide));
	}
	
	public void closePopUpFrmClntSideAskPr() {
		clsePopUpFrmClntSide.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Accept']")
	WebElement acceptBtn;
	
	public void acceptAskforPriceChange() {
		acceptBtn.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Reject']")
	WebElement rejectBtn;
	
	public void rejectAskForPriceChange() {
		rejectBtn.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Yes, I am sure'])[1]")
	WebElement YesImSureBtn;
	
	public void clickOnYesImSureBtn() {
		YesImSureBtn.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement clsethankUPopUp;
	
	public void closeThankUPopUp() {
		clsethankUPopUp.click();
	}
	
	@FindBy(xpath="//a[@class='table_expand_abso cp_link']")
	WebElement fPdiv;
	
	public void clickOnFirstPDiv() {
		fPdiv.click();
	}
	
	public void scrollTillAcceptQbtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", acceptbtn);
	}
	
	public void scrollTillSpDropdown(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", specialRequestDrop);
	}
	
	public void scrollTillAcceptBtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", acceptBtn);
	}
	
	//....................................
	public void logoutTheClient() {
		logoutClient.click();
	}
	
	public boolean checkEleIsDisabled() {
		boolean check=selectAnyQuote.isEnabled();
		return check;
	}
	
	public void closeTabAfterAcceptQuote() {
		clseTabAfterQuoteAcpt.click();
	}
	
	public void clickOnStartfulfillbtn() {
		startFullfilBtn.click();
	}
	
	public void clickOnPreviousFulfill() {
		previousOrder.click();
	}
	
	public void startFulfillingDropbtn() {
		startFullfillDrop.click();
	}
	
	public void clickOnClosebtn() {
		closeTab.click();
	}
	
	public void clickOnYesImSure() {
		YesImSurebtn.click();
	}
	
	public String getStatusOfQuote() {
		String status=statusQuote.getText();
		return status;
	}
	
	public void pleaseRequote() {
		requote.click();
	}
	
	public void clickOnSpecialRequestDrop() {
		specialRequestDrop.click();
	}
	
	public void stopFullfilling() {
		stopFullfill.click();
	}
	
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
