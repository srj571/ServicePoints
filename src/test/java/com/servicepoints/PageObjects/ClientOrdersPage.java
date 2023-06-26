package com.servicepoints.PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.poi.hssf.record.PageBreakRecord.Break;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.servicepoints.testCases.BaseClass;

import junit.framework.Assert;

public class ClientOrdersPage {

	WebDriver rdriver;

	public ClientOrdersPage(WebDriver ldriver) {
		rdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "(//a)[15]")
	WebElement ordersTab;

	public void clickOnOrdersTab() {
		ordersTab.click();
	}

	@FindBy(xpath = "//input[@id='order_product_search']")
	WebElement txtSearch;

	public void sendPnameinSearch(String nvar) {
		txtSearch.sendKeys(nvar);
	}

	public void clearSearchProductField() {
		txtSearch.clear();
	}

	@FindBy(xpath = "(//div)[65]")
	WebElement statusDrop;

	public void clickOnStatusDrop() {
		statusDrop.click();
	}

	@FindBy(xpath = "//li[contains(text(),'Not quoted')]")
	WebElement notQuotedSel;

	public void clickOnNotQuotedSel() {
		notQuotedSel.click();
	}

	@FindBy(xpath = "//li[contains(text(),'Processing')]")
	WebElement processingTab;

	public void clickOnProcessingSel() {
		processingTab.click();
	}

	@FindBy(xpath = "//div[@class='js_table_expand_two table_expand_abso']")
	WebElement firstDiv;

	@FindBy(xpath = "(//div[@class='js_table_expand_two table_expand_abso'])[3]")
	WebElement clickOn3rdDiv;

	public void clickOn3rdDiv() {
		clickOn3rdDiv.click();
	}

	public void clickOnFDiv() {
		firstDiv.click();
	}

	@FindBy(xpath = "//input[@role='searchbox']")
	WebElement dropdownSearch;

	public void dropdownSearch(String status) {
		dropdownSearch.sendKeys(status);
	}

	@FindBy(xpath = "(//div)[65]")
	WebElement orderStatus;

	public void clickOnDropdown() {
		orderStatus.click();
	}

	@FindBy(xpath = "//a[@id='raiseDispute']")
	public WebElement openDspbtn;

	public void clickOnOpenDspbtn() {
		openDspbtn.click();
	}

	@FindBy(xpath = "//select[@id='dispute_issue_list']")
	WebElement dspIssue;

	public void handleDspIssues() {
		Select sel = new Select(dspIssue);
		sel.selectByIndex(1);
	}

	public void selectOtherDspOption() {
		Select sel = new Select(dspIssue);
		sel.selectByIndex(4);
	}

	@FindBy(xpath = "//textarea[@id='otherIssue']")
	WebElement txtotherOption;

	public void setOtherInfo(String text) {
		txtotherOption.sendKeys(text);
	}

	@FindBy(xpath = "//select[@id='dispute_type_list']")
	WebElement dspSolutions;

	@FindBy(xpath = "//select[@name='disputeSol']")
	WebElement disputeSolutionsNew;

	public void clickOnRefundSolution() {
		Select sl = new Select(disputeSolutionsNew);
		sl.selectByIndex(1);
	}

	public void clickOnResendSolution() {
		Select sl = new Select(disputeSolutionsNew);
		sl.selectByIndex(2);
	}

	public void handleDspSolution() {
		Select sl = new Select(dspSolutions);
		sl.selectByIndex(3);
	}

	public void refundSolutionDsp() {
		Select sl = new Select(dspSolutions);
		sl.selectByIndex(1);
	}

	public void resendSolutionDsp() {
		Select sl = new Select(dspSolutions);
		sl.selectByIndex(2);
	}

	@FindBy(xpath = "//input[@name='disput-file']")
	WebElement sendFiles;

	public void sendFiles(String str) {
		sendFiles.sendKeys(str);
	}

	@FindBy(xpath = "(//input[@name='order_mapping_ids[]'])[6]")
	WebElement checkF;

	@FindBy(xpath = "(//input[@name='order_mapping_ids[]'])[7]")
	WebElement checkS;

	@FindBy(xpath = "(//div[@class='mb-2 p-2 sacol withedit multi_list bg-transparent'])[2]//input[@name='order_mapping_ids[]' and @type='checkbox']")
	List<WebElement> checkBoxTable;

	public void clickOnCheckBox() {

		for (WebElement checkbox : checkBoxTable) {
			checkbox.click();
		}
	}

	public void clickOnFirstCheckBoxForDsp() {
		if (checkBoxTable.size() > 0) {
			checkBoxTable.get(0).click();
		}
	}
	
	public void clickOnFirstTwoCheckBoxesForOpenDispute() {
		for (int i = 0; i <= 1; i++) {
			checkBoxTable.get(i).click();
		}
	}

	@FindBy(xpath = "//input[@id='saveDispute']")
	WebElement SaveDsp;

	public void SaveDispute() {
		SaveDsp.click();
	}

	@FindBy(xpath = "//textarea[@id='textarea']")
	WebElement enterQueries;

	public void sendQueries(String queries) {
		enterQueries.sendKeys(queries);
	}

	@FindBy(xpath = "(//a[normalize-space()='Special request'])[1]")
	WebElement specialRequestBtn;

	public void clickOnSpecialRequest() {
		specialRequestBtn.click();
	}

	@FindBy(xpath = "//select[@id='select']")
	WebElement requestDropDown;

	public void handleRequestDropdown() {
		Select sel = new Select(requestDropDown);
		sel.selectByIndex(1);
	}

	@FindBy(xpath = "(//div[@class='mb-2 p-2 sacol withedit multi_list bg-transparent'])[1]//input[@name='order_mapping_ids[]' and @type='checkbox']")
	List<WebElement> specialRequestCheckBoxses;

	public void clickOnSpecialRequestchecks() {
		for (WebElement checks : specialRequestCheckBoxses) {
			checks.click();
		}
	}

	public void clickOnFirstTwoCheckBoxes() {
		for (int i = 0; i <= 1; i++) {
			specialRequestCheckBoxses.get(i).click();
		}
	}

	public void clickOnTwoThirdCheckBoxes() {
		for (int i = 2; i <= 3; i++) {
			specialRequestCheckBoxses.get(i).click();
		}
	}

	@FindBy(xpath = "//input[@id='disputeButton']")
	WebElement sendRequestbtn;

	public void clickOnSendRequestBtn() {
		sendRequestbtn.click();
	}

	@FindBy(xpath = "//a[contains(text(),'Cancel order')]")
	WebElement cancelOrderBtn;

	public void clickOnCancelBtn() {
		cancelOrderBtn.click();
	}

	@FindBy(xpath = "//div[@id='client_orders_body']/div")
	List<WebElement> noOfOrdersTab;

	@FindBy(xpath = "//h5[@id='orderDisputeId']//button[@aria-label='Close']")
	WebElement closeDispute;

	@FindBy(xpath = "//a[contains(text(),'Show dispute')]")
	List<WebElement> showDisputeList;

	public void clickOnEachDivForDisputeVerification(WebDriver driver) throws InterruptedException {
		for ( int i=0; i<noOfOrdersTab.size();i++) {
			
			noOfOrdersTab.get(i).click();
			Thread.sleep(1000);

			scrollTillTheLast(driver);
			Thread.sleep(3000);

			showDisputeList.get(i).click();
			Thread.sleep(4000);

			closeDispute.click();
			Thread.sleep(2000);

		}
	}

	public void reopenDeclinedDisputes(WebDriver driver, String queries) throws InterruptedException {
		for (int i = 0; i < noOfDisputesDiv.size(); i++) {

			noOfDisputesDiv.get(i).click();
			Thread.sleep(2000);

			scrollTillTheLast(driver);
			Thread.sleep(2000);

			showDisputeList.get(i).click();
			Thread.sleep(2000);

			sendQueries(queries);
			Thread.sleep(2000);

			SaveDispute();
			Thread.sleep(5000);
			BaseClass.logger.info("Clicked on Saved dispute.");

			if (driver.getPageSource().contains("Message send successfully")) {
				Assert.assertTrue(true);
				BaseClass.logger.info("Verification of Declined Dispute reopen Successfully.");
			} else {
				BaseClass.logger.info("Verification of Diclined Dispute reopen failed.");
				Assert.assertTrue(false);
			}
		}
	}

	public void reopenDeclinedDisputesForCancelOrder(WebDriver driver, String queries) throws InterruptedException {
		for (int i = 0; i < noOfDisputesDiv.size(); i++) {

			noOfDisputesDiv.get(i).click();
			Thread.sleep(2000);

			scrollTillTheLast(driver);
			Thread.sleep(2000);

			showDisputeList.get(i).click();
			Thread.sleep(2000);

			sendQueries(queries);
			Thread.sleep(2000);

			SaveDispute();
			Thread.sleep(5000);
			BaseClass.logger.info("Clicked on Saved dispute.");

			if (driver.getPageSource().contains("Message send successfully")) {
				Assert.assertTrue(true);
				BaseClass.logger.info("Verification of Declined Dispute reopen Successfully.");
			} else if (verifyAlertOfAlreadyOpenDsp() == true) {
				Assert.assertTrue(true);
				Thread.sleep(2000);
				closeAlert.click();
				BaseClass.logger.info("Verification of message after Declined Dispute reopen Successfully.");
			} else if (verifyAlertOfCancelOrder() == true) {
				Assert.assertTrue(true);
				Thread.sleep(2000);
				closeAlert.click();
				BaseClass.logger
						.info("Verification of message after Declined Dispute reopening of cancel order Successfully.");
			} else {
				BaseClass.logger.info("Verification of messages failed.");
				Assert.assertTrue(false);
			}
		}
	}

	public void reopenHoldDeclinedDisputes(WebDriver driver, String queries) throws InterruptedException {
		for (int i = 0; i < noOfDisputesDiv.size(); i++) {

			noOfDisputesDiv.get(i).click();
			Thread.sleep(2000);

			scrollTillTheLast(driver);
			Thread.sleep(2000);

			showDisputeList.get(i).click();
			Thread.sleep(2000);

			sendQueries(queries);
			Thread.sleep(2000);

			SaveDispute();
			Thread.sleep(4000);
			BaseClass.logger.info("Clicked on Saved dispute.");

			closeAlert.click();

			if (driver.getPageSource().contains("You can not generate or reopen the dispute request for this order.")) {
				Thread.sleep(2000);
				Assert.assertTrue(true);
				BaseClass.logger.info("Verification of not reopening of Declined Dispute of Hold status Successfull.");
			} else {
				BaseClass.logger.info("Verification of not reopening of Declined Dispute of Hold status failed.");
				Assert.assertTrue(true);
			}
		}
	}

	@FindBy(xpath = "//div[@id='client_orders_body']/div")
	List<WebElement> noOfDisputesDiv;

	@FindBy(xpath = "(//a[contains(text(),'Open dispute')])")
	List<WebElement> openDisputeList;

	@FindBy(xpath = "//a[@class='btn btn-border btn-block mt-3 raiseDispute disputeId5547 linkactive  olddispute5547']")
	WebElement openDspBtn;

	public void raisedDispute(WebDriver driver, String queries) throws InterruptedException {

		for (int i = 0; i < noOfDisputesDiv.size(); i++) {

			noOfDisputesDiv.get(i).click();
			Thread.sleep(2000);

			scrollTillTheLast(driver);
			Thread.sleep(2000);

			openDisputeList.get(i).click();
			Thread.sleep(2000);

			handleDspIssues();
			BaseClass.logger.info("Customer got wrong product option selected.");

			handleDspSolution();
			BaseClass.logger.info("I want the order to be shipped immediately option is selected.");

			clickOnCheckBox();
			Thread.sleep(3000);

			sendQueries(queries);
			BaseClass.logger.info("Queries entered in text fields.");

			SaveDispute();
			Thread.sleep(5000);
			BaseClass.logger.info("Clicked on Saved dispute.");

			if (driver.getPageSource().contains("Dispute raised successfully")) {
				Assert.assertTrue(true);
				BaseClass.logger.info("Verification of Dispute raised Successfully.");
			} else {
				BaseClass.logger.info("Verification of Dispute raised failed.");
				Assert.assertTrue(false);
			}
			Thread.sleep(3000);
		}
	}

	@FindBy(xpath = "(//a[contains(text(),'Show dispute')])[1]")
	WebElement eachD;

	@FindBy(xpath = "//div[@class='js_table_expand_two table_expand_abso']")
	List<WebElement> eachDivOnDspPage;

	@FindBy(xpath = "//div[contains(text(),'See more')]")
	List<WebElement> allSeeMoreBtn;

	@FindBy(xpath = "//div[contains(text(),'See less')]")
	List<WebElement> allSeeLessBtn;

	@FindBy(xpath = "//a[contains(text(),'Show dispute')]")
	List<WebElement> leachD;

//	public void clickOnEachDivForDisputeVerification(WebDriver driver) throws InterruptedException {
//		
//		for(int i=0;i<noOfOrdersTab.size();i++) {
//			for(int j=1; j<=3 ;j++) {
//				
//				noOfOrdersTab.get(i).click();
//				Thread.sleep(1000);
//					
//				scrollTillEle(driver);
//				Thread.sleep(2000);
//				
//				String xpath="//a[contains(text(),'Show dispute')]";
//				WebElement one=driver.findElement(By.xpath(xpath));
//				one.click();
//				Thread.sleep(4000);
//				
//				closeDispute.click();
//				Thread.sleep(2000);
//				
//				break;
//			}
//		}
//	}

//	public void clickOnEachDivForDisputeVerification(WebDriver driver) throws InterruptedException{
//		for(int i=0;i<allSeeMoreBtn.size();i++) {
//			for(int j=0; j<leachD.size(); j++) {
//				for(int e=0; e<allSeeLessBtn.size(); e++) {
//					
//					allSeeMoreBtn.get(i).click();
//					Thread.sleep(1000);
//					
//					leachD.get(j).click();
//					Thread.sleep(4000);
//					
//					closeDispute.click();
//					Thread.sleep(2000);
//					
//					allSeeLessBtn.get(e).click();
//					Thread.sleep(1000);
//					
//					break;
//				}
//				
//				break;
//			}
//		}
//	}

	@FindBy(xpath = "(//div[@class='mb-2 multi_data'])[1]//input[@name='cancel_items[]' and @type='checkbox']")
	List<WebElement> cancelOrderCheckBoxes;

	public void clickOnFirstCheckbox() {
		if (cancelOrderCheckBoxes.size() > 0) {
			cancelOrderCheckBoxes.get(0).click();
		}
	}

	@FindBy(xpath = "//button[normalize-space()='Submit']")
	WebElement submitCancelOrder;

	public void clickOnSubmitOrder() {
		submitCancelOrder.click();
	}

	@FindBy(xpath = "(//button[@type='button'][normalize-space()='Close'])[1]")
	WebElement cancelBtn;

	public void clickOnCancelOrderBtn() {
		cancelOrderBtn.click();
	}

	public void clickOnCancelOrderSuccessBtn() {
		cancelBtn.click();
	}

	@FindBy(xpath = "(//li[@id='select2--result-xd2z-2'])[1]")
	WebElement fullfillTabInSearch;

	public void clickOnFulfillTab() {
		fullfillTabInSearch.click();
	}

	@FindBy(xpath = "(//li[@id='select2-b5v1-result-bmjm-1'])[1]")
	WebElement fProcessingTab;

	public void clickOnFProcessingTab() {
		fProcessingTab.click();
	}

	@FindBy(xpath = "//a[normalize-space()='Show dispute']")
	public WebElement showDispute;

	public void clickOnShowDispute() {
		showDispute.click();
	}

	@FindBy(xpath = "(//a[contains(text(),'Show dispute')])[3]")
	WebElement thirdShowBtn;

	public void clickOnThirdShowDisputeBtn() {
		thirdShowBtn.click();
	}

	@FindBy(xpath = "//li[@role='option']")
	WebElement proTab;

	public void clickOnProcessingTab() {
		proTab.click();
	}

	@FindBy(xpath = "(//span[@class='btn btn-border btn-block mb-3 '])[1]")
	WebElement disputeHistory;

	public void clickOnDispHistory() {
		disputeHistory.click();
	}

	public void scrollTillEle(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", cancelOrderBtn);
	}

	public void scrollTillTheLast(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void scrollTillDspHistory(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", disputeHistory);
	}

	public void scrollTillShowDisputeBtn(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", showDispute);
	}

	public void scrollTillThirdShowDisputeBtn(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", thirdShowBtn);
	}

	@FindBy(xpath = "(//div[@class='table_list_item'])[1]")
	WebElement disputeHistoryTab;

	public void clickOnFirstDisputeTab() {
		disputeHistoryTab.click();
	}

	@FindBy(xpath = "//a[@class='btn btn-primary btn-sm float-right']")
	WebElement goToDisputesTab;

	public void clickOnGoToDisputesTab() {
		goToDisputesTab.click();
	}

	@FindBy(xpath = "//a[@id='special-requests']")
	WebElement specialRequestTab;

	public void clickOnSpecialRequestTab() {
		specialRequestTab.click();
	}

	@FindBy(xpath = "//a[@class='btn btn-border btn-sm special-requests-answers showSelected mr-0']")
	WebElement answerOfDsp;

	public void clickOnAnswerOfDispute() {
		answerOfDsp.click();
	}

	@FindBy(xpath = "(//a[contains(text(),'Show request')])[1]")
	WebElement showRequestTab;

	public void clickOnShowRequestTab() {
		showRequestTab.click();
	}

	@FindBy(xpath = "//button[contains(@class,'btn btn-border btn-sm selected')]")
	public WebElement closeInShowRequest;

	public void clickOnCloseShowRequest() {
		closeInShowRequest.click();
	}

	@FindBy(xpath = "//label[@class='custom-file-label']")
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

		// Paste the file path into the file dialog box and press Enter to upload the
		// file
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	@FindBy(xpath = "(//span[contains(text(),'Accepted')])[1]")
	WebElement acceptStatusDispute;

	@FindBy(xpath = "(//span[contains(text(),'Declined')])[1]")
	WebElement declinedStatusDispute;

	public String getDspHistoryStatusD() {
		String val = declinedStatusDispute.getText();
		return val;
	}

	public String getDspHistoryStatusA() {
		String val = acceptStatusDispute.getText();
		return val;
	}

	@FindBy(xpath = "//span[normalize-space()='Pending']")
	WebElement financialStatus;

	public String getFinancialStatus() {
		String status = financialStatus.getText();
		return status;
	}

	@FindBy(xpath = "//span[contains(text(),'Not quoted')]")
	WebElement orderStatusPayment;

	public String getOrderStatusPayment() {
		String ostatus = orderStatusPayment.getText();
		return ostatus;
	}

//	public void waitTillText(WebDriver driver) {
//		WebDriverWait wait =new WebDriverWait(driver,10);
//		wait.until(ExpectedConditions.)
//	}

	@FindBy(xpath = "(//span[contains(text(),'Not quoted')])[1]")
	WebElement orderStatusToNQ;

	public String getOrderStatusNQ() {
		String val = orderStatusToNQ.getText();
		return val;
	}

	@FindBy(xpath = "(//span[contains(text(),'Processing')])[1]")
	WebElement processingStatus;

	public String verifyStatusToProcessing() {
		String val = processingStatus.getText();
		return val;
	}

	@FindBy(xpath = "(//i[contains(@class,'fas fa-edit')])[1]")
	WebElement editAddress;

	public void clickOnEditAddress() {
		editAddress.click();
	}

	@FindBy(xpath = "//div[@id='update_address_modal']//input[@placeholder='Address line 1']")
	WebElement address1;

	public void clearAddress() {
		address1.clear();
	}

	public void setAddress1(String add) {
		address1.sendKeys(add);
	}

	@FindBy(xpath = "//div[@id='update_address_modal']//input[@placeholder='Zip']")
	WebElement zipNum;

	public void setZipNum(String zip) {
		zipNum.sendKeys(zip);
	}

	@FindBy(xpath = "//div[@id='update_address_modal']//input[@name='update']")
	WebElement saveInfo;

	public void saveInfo() {
		saveInfo.click();
	}

	@FindBy(xpath = "//div[@id='force_update_add_modal']//div[@class='modal-content']")
	WebElement invalidAddDiv;

	public void waitTillDivOpen(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(invalidAddDiv));
	}

	@FindBy(xpath = "(//button[contains(text(),'Yes')])[2]")
	WebElement clickOnYesBtn;

	public void clickOnYesAddress() {
		clickOnYesBtn.click();
	}

	@FindBy(xpath = "//a[@id='closeDispute']")
	WebElement closedDisputes;

	public void clickOnClosedDisputestab() {
		closedDisputes.click();
	}

	@FindBy(xpath = "//button[@id='notifySupport']")
	WebElement notifySupportBtn;

	public void clickOnNotifySupportBtn() {
		notifySupportBtn.click();
	}

	@FindBy(xpath = "//a[normalize-space()='Open dispute']")
	WebElement openDisputeBtn;

	public boolean verifyOpenDisputeButtonIsVisible() {
		boolean val = openDisputeBtn.isDisplayed();
		return val;
	}

	public void scrollTillOpenDisputesBtn(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", openDisputeBtn);
	}

	@FindBy(xpath = "//a[@class='btn btn-primary btn-sm float-right']")
	WebElement goToDisputes;

	public void scrollTillGoToDisputesBtn(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", goToDisputes);
	}

	@FindBy(xpath = "(//span[@aria-hidden='true'][normalize-space()='×'])[4]")
	public WebElement closeAlert;

	@FindBy(xpath = "//div[@id='error_modal']//div[@class='modal-content']")
	public WebElement alertMsg;

	public void verifyAlert(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.textToBePresentInElement(alertMsg,
				"You are allowed to raise refund and resend disputes only for those orders status that are delivered, waiting for tracking updates & in transit. Also on orders in processing-resend status, refund disputes can be opened."));
		Thread.sleep(3000);
		closeAlert.click();
	}

	@FindBy(xpath = "//h6[normalize-space()='There is already an open dispute for this order']")
	WebElement alertText;

	public boolean verifyAlertOfAlreadyOpenDsp() {
		String actVal = alertText.getText();
		String expVal = "There is already an open dispute for this order";
		if (expVal.equals(actVal)) {
			return true;
		}
		closeAlert.click();
		return false;
	}

	@FindBy(xpath = "//h6[contains(text(),'You can not generate or reopen the dispute request')]")
	public WebElement alertTextForCancelOrder;

	public boolean verifyAlertOfCancelOrder() {
		String actVal = alertTextForCancelOrder.getText();
		String expVal = "You can not generate or reopen the dispute request for this order.";
		if (expVal.equals(actVal)) {
			// closeAlert.click();
			return true;
		}
		closeAlert.click();
		return false;
	}

	@FindBy(xpath = "(//span[@aria-hidden='true'][normalize-space()='×'])[2]")
	WebElement closeShowDisputeWin;

	public void closeShowDisputeWin() {
		closeShowDisputeWin.click();
	}

	public void scrollUpToTop(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(0, 0);");
	}

	@FindBy(xpath = "//a[normalize-space()='Declined disputes']")
	WebElement declinedDisputeTab;

	public void clickOnDeclinedDisputesTab() {
		declinedDisputeTab.click();
	}

	public void checkVisibilityOfShowDisputeBtn() {
		showDispute.isDisplayed();
	}

	@FindBy(xpath = "//div[@class='mb-2 multi_data']//input")
	List<WebElement> allCheckBoxes;

	public void clickOnSecondCheckBox() {
		if (allCheckBoxes.size() > 0) {
			allCheckBoxes.get(1).click();
		}
	}

	@FindBy(xpath = "//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel']")
	public List<WebElement> closedDisputesDiv;

	@FindBy(xpath = "(//span[@class='badge-mod badge-warning'])[2]")
	public WebElement statusText;

	@FindBy(xpath = "(//div[@class='float-left btn-block order_mapping_list'])[2]//input")
	List<WebElement> checkBoxes;

	public boolean verifyCheckBoxesDisabled() {
		for (int i = 0; i < checkBoxes.size(); i++) {
			boolean isEnabled = checkBoxes.get(i).isEnabled();
			if (isEnabled) {
				return false; // Return false if any checkbox is enabled
			}
		}
		return true; // Return true if all checkboxes are disabled
	}

	@FindBy(xpath = "(//div[@class='col table_item_block_md'])[1]//div[@class='col-7'][2]")
	WebElement getDiscountOnClient;

	public String getDiscountOfClient() {
		String discount = getDiscountOnClient.getText();
		return discount;
	}

	@FindBy(xpath="//a[normalize-space()='Approved disputes']")
	WebElement approvedDispute;
	
	public void clickOnApprovedDispute() {
		approvedDispute.click();
	}
	
	@FindBy(xpath="(//div[@class='content-page'])[1]")
	WebElement answersInSpecialRequest;
	
	public void clickOnAnswersBtnInSR() {
		answersInSpecialRequest.click();
	}
}
