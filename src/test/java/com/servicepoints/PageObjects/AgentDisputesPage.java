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

public class AgentDisputesPage {

	WebDriver rdriver;

	public AgentDisputesPage(WebDriver ldriver) {
		rdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	@FindBy(xpath = "//div[normalize-space()='Disputes']")
	WebElement diputesTab;

	public void clickOnDisputesTab() {
		diputesTab.click();
	}

	@FindBy(xpath = "//input[@id='order_product_search']")
	WebElement searchDspPro;

	public void searchProductForDsp(String pdsp) {
		searchDspPro.sendKeys(pdsp);
	}

	public void clearSearchFieldForDisputePro() {
		searchDspPro.clear();
	}

	@FindBy(xpath = "(//div[@class='js_table_expand_two table_expand_abso full_expand'])[1]")
	WebElement fdivShowDsp;

	public void clickOnFrstDsp() {
		fdivShowDsp.click();
	}

	@FindBy(xpath = "(//a[normalize-space()='Show dispute'])[1]")
	WebElement showDsp;

	public void clickOnShowDsp() {
		showDsp.click();
	}

	@FindBy(xpath = "//select[@id='disputeIssueAccept']")
	WebElement dspDropdown;

	public void selectDspStatus() {
		Select sel = new Select(dspDropdown);
		sel.selectByIndex(1);
	}

	public void selectDspStatusToDeclined() {
		Select sel = new Select(dspDropdown);
		sel.selectByIndex(2);
	}

	@FindBy(xpath = "//textarea[@id='textarea']")
	WebElement txtanswer;

	public void sendAnswer(String answer) {
		txtanswer.sendKeys(answer);
	}

	@FindBy(xpath = "//input[@value='   Send answer   ']")
	WebElement btnSendAnswer;

	public void clickOnSendAnswer() {
		btnSendAnswer.click();
	}

	@FindBy(xpath = "//a[contains(text(),'Special requests')]")
	WebElement Srteamleader;

	public void goToSRTabInTL() {
		Srteamleader.click();
	}

	@FindBy(xpath = "//a[@id='specialDispute']")
	WebElement specialRequestTab;

	public void clickOnSpecialRequestTab() {
		specialRequestTab.click();
	}

	@FindBy(xpath = "//a[normalize-space()='Open']")
	WebElement openInSpecialReTab;

	public void clickOnOpenTabInSreq() {
		openInSpecialReTab.click();
	}

	@FindBy(xpath = "(//a[normalize-space()='Show request'])[1]")
	WebElement showRequestTab;

	public void clickOnShowRequestTab() {
		showRequestTab.click();
	}

	@FindBy(xpath = "//input[@id='disputeButton']")
	WebElement sendAnsForSpRequest;

	public void clickOnSendAnsSpRequest() {
		sendAnsForSpRequest.click();
	}

	@FindBy(xpath = "//textarea[@id='answerSupplier']")
	WebElement txtAnsFieldSpReq;

	public void setTxtAnsSpRequest(String ans) {
		txtAnsFieldSpReq.sendKeys(ans);
	}

	@FindBy(xpath = "//input[@id='uploadFile']")
	WebElement sendFile;

	public void sendFileToInASpReq(String file) {
		sendFile.sendKeys(file);
	}

	public void scrollTillSendAns(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", btnSendAnswer);
	}

	@FindBy(xpath = "//input[@name='upload']")
	WebElement attachments;

	public void clickOnAttachments() {
		attachments.click();
	}

	public void sendFile() throws AWTException {
		Robot robot = new Robot();
		robot.delay(1000);

		StringSelection stringSelection = new StringSelection("Home/Downloads/A324.pdf");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void scrollTillShowDispute(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", showDsp);
	}

	public void scrollTillEndOfThePage(WebDriver driver) {
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		exe.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public void waitTillElementToBeClickable(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(showDsp)).click();
		scrollTillShowDispute(driver);
	}

	@FindBy(xpath = "//div[@id='client_orders_body']/div/div[1]")
	List<WebElement> eachDspDivFromAgentSide;

	@FindBy(xpath = "//div[@class='d-xl-flex ']//a[@class='btn btn-border btn-block mt-2 showDisputes linkactive']")
	List<WebElement> allShowBtn;

	@FindBy(xpath = "//h5[@id='orderDisputeId']//button[@aria-label='Close']")
	WebElement closeDisputeAgent;
	
	public void clickOnEachDisputeAgentSide(WebDriver driver) throws InterruptedException {

		for (int j = 0; j < eachDspDivFromAgentSide.size(); j++) {

			for (int i = 0; i < allShowBtn.size(); i++) {

				eachDspDivFromAgentSide.get(j).click();
				Thread.sleep(2000);

				scrollTillShowDispute(driver);
				//scrollTillEndOfThePage(driver);
				Thread.sleep(3000);

				allShowBtn.get(i).click();
				Thread.sleep(3000);

				closeDisputeAgent.click();
				Thread.sleep(2000);

				break;
				// eachDspDivFromAgentSide.get(j).click();
			}
		}
	}

//	public void clickOnEachDisputeAgentSide(WebDriver driver) throws InterruptedException {
//		for(WebElement eachDiv : eachDspDivFromAgentSide) {
//			for(WebElement showBtn : allShowBtn) {
//				eachDiv.click();
//				Thread.sleep(2000);
//				
//				scrollTillShowDispute(driver);
//				Thread.sleep(2000);
//			
//				showBtn.click();
//				Thread.sleep(3000);
//				
//				closeDisputeAgent.click();
//				Thread.sleep(3000);
//			}
//		}
//	}

	@FindBy(xpath="//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel full_expand']")
	List<WebElement> allDisps;
	
	@FindBy(xpath="//a[@class='btn btn-border btn-block mt-3 supportDispute linkactive']")
	List<WebElement> allShowDspBtn;
	
	public void handleEachDispute(WebDriver driver) throws InterruptedException {
		for(int i=0; i<allDisps.size();i++) {
			for(int j=0; j<allShowDspBtn.size();j++) {
				allDisps.get(i).click();
				Thread.sleep(2000);
				
				scrollTillShowDispute(driver);
				Thread.sleep(3000);
				
				allShowDspBtn.get(i).click();
				Thread.sleep(4000);
				
				closeDisputeAgent.click();
				Thread.sleep(2000);
				break;
			}
		}
	}
	
	
	
	
	
	@FindBy(xpath = "//a[normalize-space()='Closed disputes']")
	WebElement closedDisputesTab;

	public void clickOnClosedDisputesTab() {
		closedDisputesTab.click();
	}

	public boolean visibilityOfShowBtn() {
		boolean flag = showDsp.isDisplayed();
		return flag;
	}

	@FindBy(xpath = "//a[normalize-space()='Declined disputes']")
	WebElement declinedDispute;

	public void clickOnDeclinedDispute() {
		declinedDispute.click();
	}

	@FindBy(xpath = "//input[@value='   Cancel   ']")
	WebElement cancelTabOnShowDsp;

	public void clickOnCancelDspTab() {
		cancelTabOnShowDsp.click();
	}

	@FindBy(xpath = "//span[@title='All store']")
	WebElement allStoreBtn;

	public void clickOnAllStoreBtn() {
		allStoreBtn.click();
	}

	@FindBy(xpath = "//input[@role='searchbox']")
	WebElement searchInStoreFilter;

	public void sendStoreNameInStoreFilter(String storeName) {
		searchInStoreFilter.sendKeys(storeName);
	}

	@FindBy(xpath = "//li[@role='option']")
	WebElement clickOnFStoreEle;

	public void clickOnFStoreEle() {
		clickOnFStoreEle.click();
	}
	
	

}
