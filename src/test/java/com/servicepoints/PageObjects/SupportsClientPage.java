package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SupportsClientPage {
	
	WebDriver rdriver;
	
	public SupportsClientPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="(//div[normalize-space()='Clients'])[1]")
	WebElement clientsTab;
	
	public void goToClientsPage() {
		clientsTab.click();
	}
	
	@FindBy(xpath="(//input[@id='accountSearch'])[1]")
	public WebElement searchField;
	
	public void enterClientNameField(String cname) {
		searchField.sendKeys(cname);
	}
	
	@FindBy(xpath="//*[@id=\"clients-content\"]/div/div[2]/div/div[2]/span/a")
	public WebElement clientsDetailsTab;
	
	public void goToClientDetailsPage() {
		clientsDetailsTab.click();
	}
	
	@FindBy(xpath="/html/body/div[4]/div/div/div[1]/h2")
	WebElement clientNameForVerification;
	
	public String verifyClientsNameOnClientsDetailsPage()
	{
		String val=clientNameForVerification.getText();
		return val;
	}
	
	@FindBy(xpath="(//i[@class='fas fa-edit tooltip_js'])[3]")
	public WebElement noteForSupplierToolTip;
	
	public void clickOnNoteForSupplier() {
		noteForSupplierToolTip.click();
	}
	
	@FindBy(xpath="(//i[@class='fas fa-expand tooltip_js expand_cl'])[2]")
	WebElement expandBtn;
	
	public void clickOnExpandBtn() {
		expandBtn.click();
	}
	
	@FindBy(xpath="(//i[@class='fas fa-edit tooltip_js'])[2]")
	WebElement noteForAgentNLToolTip;
	
	public void clickOnNoteForAgentNL() {
		noteForAgentNLToolTip.click();
	}

	@FindBy(xpath="(//i[@class='fas fa-expand tooltip_js expand_cl'])[1]")
	WebElement expandBtnForAgentNL;
	
	public void clickOnExpandBtnForAgentNL() {
		expandBtnForAgentNL.click();
	}
	
	@FindBy(xpath="(//textarea[@id='clientNotes'])[1]")
	public WebElement noteTextField;
	
	public void sendNoteForSupplier(String name) {
		noteTextField.sendKeys(name);
	}
	
	@FindBy(xpath="(//div[normalize-space()='Notes for supplier'])[1]")
	WebElement supplierNoteExpand;
	
	public String getSupplierExpandedFieldName() {
		String val=supplierNoteExpand.getText();
		return val;
	}
	
	@FindBy(xpath="(//iframe[@title='Rich Text Editor, agentNlNotes'])[1]")
	WebElement richTextEditor;
	
	public void sendTextIntoTextEditor(String text) {
		richTextEditor.sendKeys(text);
	}
	
	@FindBy(xpath="(//input[@id='updateClientNotes'])[1]")
	WebElement saveBtn;
	
	public void clickOnSaveBtn() {
		saveBtn.click();
	}
	
	@FindBy(xpath="(//div[@id='save_client_info']/div/div)[1]")
	WebElement noteUpdatedPopUp;
	
	public void waitTillPopUp(WebDriver driver) {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(noteUpdatedPopUp));
	}
	
	@FindBy(xpath=" (//i[@class='fas tooltip_js expand_cl fa-compress'])[2]")
	WebElement compressBtn;

	public void clickOnCompressBtn() {
		compressBtn.click();
	}
	
	//Client notes for supplier updated successfully.
	
	@FindBy(xpath="(//div[normalize-space()='Agent NL Notes'])[1]")
	WebElement agentNLNotesPage;
	
	public String getagentNLNotesPage() {
		String val=agentNLNotesPage.getText();
		return val;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
