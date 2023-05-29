package com.servicepoints.PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	
	public void clearSearchProductField() {
		txtSearch.clear();
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
	
	@FindBy(xpath="//select[@name='disputeSol']")
	WebElement disputeSolutionsNew;
	
	public void clickOnRefundSolution() {
		Select sl=new Select(disputeSolutionsNew);
		sl.selectByIndex(1);
	}
	
	public void clickOnResendSolution() {
		Select sl=new Select(disputeSolutionsNew);
		sl.selectByIndex(2);
	}
	
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
	
	@FindBy(xpath="//div[@id='client_orders_body']/div")
	List<WebElement> noOfOrdersTab;
	//No of orders tab
	
	@FindBy(xpath="//h5[@id='orderDisputeId']//button[@aria-label='Close']")
	WebElement closeDispute;
	
	public void clickOnEachDivForDisputeVerification(WebDriver driver) throws InterruptedException {
		for(WebElement eachDiv:noOfOrdersTab) {
			eachDiv.click();
			Thread.sleep(1000);
			scrollTillEle(driver);
			Thread.sleep(2000);
			showDispute.click();
			Thread.sleep(4000);
			closeDispute.click();
			Thread.sleep(2000);
		}
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
	public WebElement showDispute;
	
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
	
	public void scrollTillEle(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", cancelOrderBtn);
	}
	
	public void scrollTillDspHistory(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", disputeHistory);
	}
	
	public void scrollTillShowDisputeBtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", showDispute);
	}
	
	@FindBy(xpath="(//div[@class='table_list_item'])[1]")
	WebElement disputeHistoryTab;
	
	public void clickOnFirstDisputeTab() {
		disputeHistoryTab.click();
	}
	
	@FindBy(xpath="//a[@class='btn btn-primary btn-sm float-right']")
	WebElement goToDisputesTab;
	
	public void clickOnGoToDisputesTab() {
		goToDisputesTab.click();
	}
	
	@FindBy(xpath="//a[@id='special-requests']")
	WebElement specialRequestTab;
	
	public void clickOnSpecialRequestTab() {
		specialRequestTab.click();
	}
	
	@FindBy(xpath="//a[@class='btn btn-border btn-sm special-requests-answers showSelected mr-0']")
	WebElement answerOfDsp;
	
	public void clickOnAnswerOfDispute() {
		answerOfDsp.click();
	}
	
	@FindBy(xpath="(//a[contains(text(),'Show request')])[1]")
	WebElement showRequestTab;
	
	public void clickOnShowRequestTab() {
		showRequestTab.click();
	}
	
	@FindBy(xpath="//button[contains(@class,'btn btn-border btn-sm selected')]")
	public WebElement closeInShowRequest;
	
	public void clickOnCloseShowRequest() {
		closeInShowRequest.click();
	}
	
	@FindBy(xpath="//label[@class='custom-file-label']")
	WebElement attachments;
	
	public void clickOnAttachments() {
		attachments.click();
	}
	public void sendFile() throws AWTException { 	
		Robot robot = new Robot();
	    robot.delay(1000);

	    // Set the file path to be uploaded
	    StringSelection stringSelection = new StringSelection("Home/Downloads/A324.pdf");
	    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

	    // Paste the file path into the file dialog box and press Enter to upload the file
	    robot.keyPress(KeyEvent.VK_CONTROL);
	    robot.keyPress(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_V);
	    robot.keyRelease(KeyEvent.VK_CONTROL);
	    robot.delay(1000);
	    robot.keyPress(KeyEvent.VK_ENTER);
	    robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	@FindBy(xpath="(//span[contains(text(),'Accepted')])[1]")
	WebElement acceptStatusDispute;
	
	@FindBy(xpath="(//span[contains(text(),'Declined')])[1]")
	WebElement declinedStatusDispute;
	
	public String getDspHistoryStatusD() {
		String val=declinedStatusDispute.getText();
		return val;
	}
	public String getDspHistoryStatusA() {
		String val=acceptStatusDispute.getText();
		return val;
	}
	
	@FindBy(xpath="//span[normalize-space()='Pending']")
	WebElement financialStatus;
	
	public String getFinancialStatus() {
		String status=financialStatus.getText();
		return status;
	}
	
	@FindBy(xpath="//span[contains(text(),'Not quoted')]")
	WebElement orderStatusPayment;
	
	public String getOrderStatusPayment() {
		String ostatus=orderStatusPayment.getText();
		return ostatus;
	}
	
//	public void waitTillText(WebDriver driver) {
//		WebDriverWait wait =new WebDriverWait(driver,10);
//		wait.until(ExpectedConditions.)
//	}
	
	@FindBy(xpath="(//span[contains(text(),'Not quoted')])[1]")
	WebElement orderStatusToNQ;
	
	public String getOrderStatusNQ() {
		String val=orderStatusToNQ.getText();
		return val;
	}
	
	@FindBy(xpath="(//span[contains(text(),'Processing')])[1]")
	WebElement processingStatus;
	
	public String verifyStatusToProcessing() {
		String val=processingStatus.getText();
		return val;
	}
	
	@FindBy(xpath="(//i[contains(@class,'fas fa-edit')])[1]")
	WebElement editAddress;
	
	public void clickOnEditAddress() {
		editAddress.click();
	}
	
	@FindBy(xpath="//div[@id='update_address_modal']//input[@placeholder='Address line 1']")
	WebElement address1;
	
	public void clearAddress() {
		address1.clear();
	}
	
	public void setAddress1(String add) {
		address1.sendKeys(add);
	}
	
	@FindBy(xpath="//div[@id='update_address_modal']//input[@placeholder='Zip']")
	WebElement zipNum;
	
	public void setZipNum(String zip) {
		zipNum.sendKeys(zip);
	}
	
	@FindBy(xpath="//div[@id='update_address_modal']//input[@name='update']")
	WebElement saveInfo;
	
	public void saveInfo() {
		saveInfo.click();
	}
	
	@FindBy(xpath="//div[@id='force_update_add_modal']//div[@class='modal-content']")
	WebElement invalidAddDiv;
	
	public void waitTillDivOpen(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(invalidAddDiv));
	}
	
	@FindBy(xpath="(//button[contains(text(),'Yes')])[2]")
	WebElement clickOnYesBtn;
	
	public void clickOnYesAddress() {
		clickOnYesBtn.click();
	}
	
	@FindBy(xpath="//a[@id='closeDispute']")
	WebElement closedDisputes;
	
	public void clickOnClosedDisputestab() {
		closedDisputes.click();
	}
	
	@FindBy(xpath="//button[@id='notifySupport']")
	WebElement notifySupportBtn;
	
	public void clickOnNotifySupportBtn() {
		notifySupportBtn.click();
	}
	
	
	@FindBy(xpath="//a[@class='btn btn-primary btn-sm float-right']")
	WebElement goToDisputes;
	
	public void scrollTillGoToDisputesBtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", goToDisputes);
	}
	
	@FindBy(xpath="(//span[@aria-hidden='true'][normalize-space()='×'])[4]")
	WebElement closeAlert;
	
	@FindBy(xpath="//div[@id='error_modal']//div[@class='modal-content']")
	WebElement alertMsg;
	
	public void verifyAlert(WebDriver driver) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.textToBePresentInElement(alertMsg, "You are allowed to raise refund and resend disputes only for those orders status that are delivered, waiting for tracking updates & in transit. Also on orders in processing-resend status, refund disputes can be opened."));
		Thread.sleep(3000);
		closeAlert.click();
	}
	
	public void scrollUpToTop(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(0, 0);");
	}
	
	@FindBy(xpath="//a[normalize-space()='Declined disputes']")
	WebElement declinedDisputeTab;
	
	public void clickOnDeclinedDisputesTab() {
		declinedDisputeTab.click();
	}
	
}
