
package com.servicepoints.PageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class AgentOrdersPage {

	WebDriver rdriver;

	public AgentOrdersPage(WebDriver ldriver) {
		rdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "(//div[normalize-space()='Orders'])[1]")
	WebElement ordersTab;

	public void clickOnOrdersTab() {
		ordersTab.click();
	}

	@FindBy(xpath = "//input[@id='order_search']")
	WebElement orderSearch;

	public void searchPnameTrack(String pname) {
		orderSearch.sendKeys(pname);
	}

	@FindBy(xpath = "(//span[normalize-space()='Order status (all)']//span[@id='select2--container'])[1]")
	public WebElement orderStatusDrop;

	public void clickOnStatusDrop() {
		orderStatusDrop.click();
	}

	@FindBy(xpath = "//input[@role='searchbox']")
	WebElement searchStatus;

	public void setStatusSearchDrop(String status) {
		searchStatus.sendKeys(status);
	}

	@FindBy(xpath = "(//li[@id='select2--result-jwhy-1'])[1]")
	public WebElement firstProcessTab;

	@FindBy(xpath = "(//ul[@id='select2--results'])[1]")
	public WebElement fprocessTab;

	public void clickOnProcessTab() {
		fprocessTab.click();
	}

	@FindBy(xpath = "//div[@class='js_table_expand_two table_expand_abso']")
	public WebElement fdiv;

	public void clickOnfDiv() {
		fdiv.click();
	}

	@FindBy(xpath = "//a[normalize-space()='Add tracking']")
	public WebElement AddTrackingBtn;

	public void clickOnAddTracking() {
		AddTrackingBtn.click();
	}

	@FindBy(xpath = "//input[@placeholder='Please add the tracking number here']")
	WebElement trackingNumField;

	public void setTrackingNum(String trackNum) {
		trackingNumField.sendKeys(trackNum);
	}

	@FindBy(xpath = "//input[@id='submitTrackingNumber']")
	public WebElement sbmtTracking;

	public void clickOnSbmtTracking() {
		sbmtTracking.click();
	}

	@FindBy(xpath = "//div[@class='float-right bnt-group-right']//button[@type='button'][normalize-space()='Close']")
	WebElement closeTrackingPopup;

	public void clickOnCloseTrackingPopup() {
		closeTrackingPopup.click();
	}

	@FindBy(xpath = "(//div[@class='col d-flex flex-wrap mb-2 align-items-center multi_data'])[1]//input[@name='order_detail_mapping_ids[]' and @type='checkbox']")
	List<WebElement> addTrackingCheck;

	public void clickOnAllCheckBoxes() {

		for (WebElement ele : addTrackingCheck) {
			boolean val = ele.isSelected();
			if (val == true) {
				ele.click();
			}
		}
	}

	public void clickOnFirstCheckBox() {
		if (addTrackingCheck.size() > 0) {
			addTrackingCheck.get(0).click();
		}
	}
	
	public void clickOnTwoCheckBoxes() {
		if (addTrackingCheck.size() > 0) {
			addTrackingCheck.get(0).click();
		}
	}

	public void clickOnThirdOrder() {
		for (int i = 0; i < addTrackingCheck.size(); i++) {
			addTrackingCheck.get(2).click();
			break;
		}
	}

	public void scrollTillAddTracking(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", AddTrackingBtn);
	}

	@FindBy(xpath = "(//a[normalize-space()='Discount'])[1]")
	WebElement discountBtn;

	public void clickOnDiscountBtn() {
		discountBtn.click();
	}
	
	public void scrollTillDiscountbtn(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", discountBtn);
	}
	
	
	@FindBy(xpath="//span[@id='quotePrice']")
	public WebElement quotePrice;
	
	@FindBy(xpath="//div[@class='col']//input[@id='discount_amount']")
	WebElement amountField;
	
	public void enterDiscountAmountField(String result) {
		amountField.sendKeys(result);
	}

	@FindBy(xpath="//input[@id='submitDiscount']")
	WebElement submitDiscount;
	
	public void clickOnSubmitDiscountBtn() {
		submitDiscount.click();
	}
	
	@FindBy(xpath="//div[@id='successModal']//div[@class='small_modal_mod update_address_modal_body']")
	WebElement successDialogBox;
	
	public void clickOnSuccessDb() {
		successDialogBox.click();
	}
	
	@FindBy(xpath="//div[@class='modal fade bd-example-modal-sm' and @id='save_client_info']")
	WebElement addTrackingSuccessBox;
	
	public void waitTillSuccessBoxOfTrackingNum(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.visibilityOf(addTrackingSuccessBox));
	}
	
	public double generateTheDiscountedPrice() {
		
		String amountWithSymbol=quotePrice.getText();
		String amountWithoutSymbol = amountWithSymbol.replace("€", "").replace(",", ".");

		double amount = Double.parseDouble(amountWithoutSymbol);
		double percentage = 0.8;
		double result = amount * percentage;

		return result;
	}
	
	@FindBy(xpath="//span[@id='discountOrderPriceModalContentError']")
	WebElement errorMsg2;
	
	
	@FindBy(xpath="//li[@class='parsley-required']")
	WebElement errorMsg;
	
	public void verifyErrorMessages(WebDriver driver) throws InterruptedException {
		submitDiscount.click();
		Thread.sleep(2000);
		
		String expError=errorMsg.getText();
		String actError="Amount is required";
		Assert.assertEquals(actError, expError);
		
		String amountWithSymbol=quotePrice.getText();
		String amountWithoutSymbol = amountWithSymbol.replace("€", "").replace(",", ".");

		double amount = Double.parseDouble(amountWithoutSymbol);
		
		String amountAsString =String.valueOf(amount);
		Thread.sleep(1000);
		
		amountField.sendKeys(amountAsString);
		Thread.sleep(1000);
		
		submitDiscount.click();
		Thread.sleep(1000);
		
		String actError2=errorMsg2.getText();
		Thread.sleep(1000);
		
		AgentOrdersPage apo=new AgentOrdersPage(driver);
		
		double discountedPrice = apo.generateTheDiscountedPrice();
		
		String formattedPrice = String.format("%.2f", discountedPrice);
		
		String expError2="Enter amount should not be greater than €"+formattedPrice;
		
		System.out.println(expError2);
		System.out.println(actError2);
		
		//Assert.assertEquals(expError2, actError2);
		Assert.assertEquals(expError2, actError2);
		Thread.sleep(2000);
		
		amountField.clear();
		Thread.sleep(1000);
	}

}
