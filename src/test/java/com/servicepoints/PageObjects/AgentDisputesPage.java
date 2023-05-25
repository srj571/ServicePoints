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
	
	public void scrollTillSendAns(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", btnSendAnswer);
	}
	
	@FindBy(xpath="//input[@name='upload']")
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
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", showDsp);
	}
	
	public void waitTillElementToBeClickable(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,10);
	    wait.until(ExpectedConditions.elementToBeClickable(showDsp)).click();
	    // Perform any actions you need to on the clicked element
	    // ...
	    scrollTillShowDispute(driver);
	}
	
	
	
	@FindBy(xpath="//div[@id='client_orders_body']/div")
	List<WebElement> eachDspDivFromAgentSide;
	
	@FindBy(xpath="//a[@class='btn btn-border btn-block mt-2 showDisputes linkactive']")
	List<WebElement> allShowBtn;
	
	@FindBy(xpath="//h5[@id='orderDisputeId']//button[@aria-label='Close']")
	WebElement closeDisputeAgent;
	
	public void clickOnEachDisputeAgentSide(WebDriver driver) throws InterruptedException {
		for(int j=0;j<eachDspDivFromAgentSide.size();j++) {
			for(int i=0;i<allShowBtn.size();i++) {
				eachDspDivFromAgentSide.get(j).click();
				Thread.sleep(2000);
				scrollTillShowDispute(driver);
				Thread.sleep(2000);
				allShowBtn.get(i).click();
				Thread.sleep(3000);
				closeDisputeAgent.click();
			}
		}
	}
	
//	public void clickOnEachDisputeAgentSide(WebDriver driver) throws InterruptedException {
//		for(WebElement eachDiv : eachDspDivFromAgentSide) {
//			eachDiv.click();
//			Thread.sleep(2000);
//			scrollTillShowDispute(driver);
//			Thread.sleep(2000);
//		
//			for(int i=0;i<allShowBtn.size();i++) {
//				allShowBtn.get(i).click();
//				break;
//			}
//			
//			Thread.sleep(3000);
//			closeDisputeAgent.click();
//			Thread.sleep(3000);
//		}
//	}

}
