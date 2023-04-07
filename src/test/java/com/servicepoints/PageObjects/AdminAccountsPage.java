package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdminAccountsPage {
	WebDriver rdriver;

	public AdminAccountsPage(WebDriver ldriver) {
		rdriver = ldriver;
		PageFactory.initElements(ldriver, this);
	}

	// Webelements required to VerifyClientloginTest
	@FindBy(xpath = "//div[contains(text(),'Accounts')]")
	WebElement AdminAccountLink;

	@FindBy(xpath = "//input[@id='accountSearch']")
	WebElement searchNameField;

	@FindBy(xpath = "//a[@data-type='clients']")
	WebElement clientbtnTab;

	@FindBy(xpath = "//a[contains(text(),'Log in')]")
	WebElement loginBtn;

	@FindBy(xpath = "//a[normalize-space()='Admin (SUP)']")
	WebElement adminSUPTab;

	// Webelements required to Verify Client Approval by Admin
	@FindBy(xpath = "//a[@class='accDetail text-ellipsis 1']")
	WebElement userbtn;

	@FindBy(xpath = "//i[@class='fas fa-edit']")
	WebElement editIcon;

	@FindBy(xpath = "//button[@title='None selected']")
	WebElement agentSUPDrop;

	@FindBy(xpath = "(//span[@class='select2-selection select2-selection--single'])[2]")
	WebElement agentSPDrop;

	@FindBy(xpath = "//input[@id='customSwitche']")
	WebElement customSwitch;

	@FindBy(xpath = "//select[@id='invoiceSetting']")
	WebElement invoiceDropdown;

	@FindBy(xpath = "//select[@name='select_user_type_id']")
	WebElement accountType;

	@FindBy(xpath = "//input[@id='updateClientInfo']")
	WebElement saveInfoBtn;

	@FindBy(xpath = "(//*[name()='svg'])[8]")
	WebElement logOutbtn;

	@FindBy(xpath = "//label[contains(text(),' Agent Test')]")
	WebElement agentTest;

	@FindBy(xpath = "//input[@id='agent_fee']")
	WebElement agentfee;

	@FindBy(xpath = "//input[@role='searchbox']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//li[@role='option']")
	WebElement firstOption;

	// VerifyClientloginTest

	public void getAdminAccountsPage() {
		AdminAccountLink.click();
	}

	public void enterUserName(String uname) {
		searchNameField.sendKeys(uname);
	}

	public void getClientsTab() {
		clientbtnTab.click();
	}

	public void clickOnLoginBtn() {
		loginBtn.click();
	}

	// Verify Client Approval by Admin
	public void selectAgentSPOption() {
		firstOption.click();
	}

	public void sendAgentSupportName(String asname) {
		dropdownSearch.sendKeys(asname);
	}

	public void clickOnAgentFee() {
		agentfee.click();
	}

	public void selectUserAsAClient() {
		Select sel = new Select(accountType);
		sel.selectByIndex(0);
	}

	public void selectAgentSupplier() {
		Select sel = new Select(agentSUPDrop);
		sel.selectByIndex(5);
	}

	public void selectAgentSUP() {
		agentTest.click();
	}

	public void selectInvoice() {
		Select sel = new Select(invoiceDropdown);
		sel.selectByIndex(2);
	}

	public void goToUserProfile() {
		userbtn.click();
	}

	public void editUserProfile() {
		editIcon.click();
	}

	public void selectAccountType() {
		accountType.click();
	}

	public void selectAgentSupplierDrop() {
		agentSUPDrop.click();
	}

	public void selectAgentSupportDrop() {
		agentSPDrop.click();
	}

	public void selectInvoiceTypeDrop() {
		invoiceDropdown.click();
	}

	public void selectSwitchOn() {
		customSwitch.click();
	}

	public void saveInfo() {
		saveInfoBtn.click();
	}

	public void logoutAdmPanel() {
		logOutbtn.click();
	}

	// Verify Admin SUP LoginTest
	public void goToAdminSUPTab() {
		adminSUPTab.click();
	}

}
