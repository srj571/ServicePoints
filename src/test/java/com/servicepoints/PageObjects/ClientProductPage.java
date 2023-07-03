package com.servicepoints.PageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

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
	
	@FindBy(xpath="(//div)[75]")
	WebElement quoteTabSupportSide;
	
	@FindBy(xpath="//button[normalize-space()='Accept selected quotation']")
	WebElement acceptbtn;
	
	@FindBy(xpath="//span[normalize-space()='Quotation accepted']")
	WebElement statusEle;
	
	@FindBy(xpath="(//span[@class='badge-mod badge-danger'])[1]")
	WebElement getQuotedStatus;
	
	@FindBy(xpath="(//span[@class='badge-mod badge-warning'])[1]")
	WebElement getBiddingStatus;
	
	public String getBiddingStatus() {
		String val=getBiddingStatus.getText();
		return val;
	}
	
	public String getQuotedStatus() {
		String val=getQuotedStatus.getText();
		return val;
	}
	
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
	
	@FindBy(xpath="//button[normalize-space()='Request for quotation']")
	WebElement requestForQuoteBtn;
	
	public void clickOnRequestForQuoteBtn() {
		requestForQuoteBtn.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtnRequestForRequote;
	
	public void clickOnCloseBtnForRR() {
		closeBtnRequestForRequote.click();
	}
	
	@FindBy(xpath="(//a[normalize-space()='View product'])[1]")
	WebElement viewProBtn;
	
	public void scrollTillViewPro(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", viewProBtn);
	}
	
	@FindBy(xpath="(//button[normalize-space()='Accept selected quotation'])[1]")
	WebElement acceptSelectedQuote;
	
	public void clickOnAcceptSelectedQuote() {
		acceptSelectedQuote.click();
	}
	
	@FindBy(xpath="(//div[@class='modal-content special_request_modal_content'])[1]//div[2]/p")
	WebElement erroeAfterRequotation;
	
	public String getErrorTextAfterReqouteTwoTimes() {
		String error=erroeAfterRequotation.getText();
		return error;
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtnOnErrorMsg;
	
	public void clickOnCloseBtnOnErrorMsg() {
		closeBtnOnErrorMsg.click();
	}
	
	@FindBy(xpath="//div[@class='dropdown-menu show']/a[@data-value='4']")
	WebElement addCountryBtn;
	
	@FindBy(xpath="(//div[@class='modal-content special_request_modal_content'])[1]//select")
	WebElement countryDropDown;
	
	@FindBy(xpath="(//div[@class='modal-content special_request_modal_content'])[1]/div[@class='modal-footer']/button[2]")
	WebElement saveCountry;
	
	@FindBy(xpath="(//div[@class='modal-content special_request_modal_content'])[1]/div[@class='modal-footer']/button[1]")
	WebElement cancelBtnOnCountry;
	
	@FindBy(xpath="(//div[contains(text(),'Please select country')])[1]")
	WebElement actError;
	
	@FindBy(xpath="(//div[normalize-space()='Country already added in the quotation'])[1]")
	WebElement actCountryAddedErr;
	
	@FindBy(xpath="(//div[normalize-space()='Country added to the quotation successfully'])[1]")
	WebElement successMsg;
	
	public void clickOnAddCountry() throws InterruptedException {
		addCountryBtn.click();
		Thread.sleep(2000);
		
		Select sel=new Select(countryDropDown);
		sel.selectByValue("13");
		Thread.sleep(2000);
		
		saveCountry.click();
		Thread.sleep(1000);
		
		String success=successMsg.getText();
		String expMsg="Country added to the quotation successfully";
		
		Assert.assertEquals(expMsg, success);
		Thread.sleep(2000);
		
		saveCountry.click();
		String actErr=actError.getText();
		String expErr="Please select country";
		
		Assert.assertEquals(expErr, actErr);
		Thread.sleep(2000);
		
		sel.selectByValue("13");
		Thread.sleep(2000);
		
		saveCountry.click();
		
		String actErr2=actCountryAddedErr.getText();
		String expErr2="Country already added in the quotation";
		
		Assert.assertEquals(expErr2, actErr2);
		Thread.sleep(2000);
		
		cancelBtnOnCountry.click();
		Thread.sleep(2000);
	}
	
	@FindBy(xpath="(//button[normalize-space()='Cancel quotation request'])[1]")
	WebElement cancelQuotationRequest;
	
	public void selectCancelQuotationRequest() {
		cancelQuotationRequest.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Yes, I am sure'])[1]")
	WebElement yesImSUreBtnCancelQuote;
	
	public void clickOnImSureBtn() {
		yesImSUreBtnCancelQuote.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtnSuccess;
	
	public void clickOnCloseBtnSuccess() {
		closeBtnSuccess.click();
	}
	
	@FindBy(xpath="(//button[normalize-space()='Request for quotation'])[1]")
	WebElement requestForQuotationBtn;
	
	public void clickOnRequestForQuotationBtn() {
		requestForQuotationBtn.click();
	}
	
	@FindBy(xpath="(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement closeBtnAfterRequestForQuote;
	
	public void clickOnCloseBtnAfterRQ() {
		closeBtnAfterRequestForQuote.click();
	}
	
	public void clickOnQuoteTabSupportSide() {
		quoteTabSupportSide.click();
	}
	
}
