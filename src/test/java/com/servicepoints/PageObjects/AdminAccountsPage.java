package com.servicepoints.PageObjects;

import org.openqa.selenium.By;
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

	

	@FindBy(xpath = "//input[@id='agent_fee']")
	WebElement agentfee;

	@FindBy(xpath = "//input[@role='searchbox']")
	WebElement dropdownSearch;

	@FindBy(xpath = "//li[@role='option']")
	WebElement firstOption;

	//WebElements for Add Agent Support 
	
	@FindBy(xpath="//a[normalize-space()='Teamleader (SP)']")
	WebElement teamleaderTab;
	
	@FindBy(xpath="//div[normalize-space()='Team']")
	WebElement teamTabl;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement addNewAgent;
	
	@FindBy(xpath="//input[@id='signupagent']")
	WebElement addagentbtn;
	
	
	
	@FindBy(xpath="(//span[@aria-hidden='true'][normalize-space()='×'])[1]")
	WebElement closePopupAgent;
	
	@FindBy(xpath="(//*[name()='svg'])[10]")
	WebElement logoutTeamL;
	
	@FindBy(xpath="(//span[@class='name_overflow'][contains(text(),'John')])[1]")
	WebElement verifyName;
	
	@FindBy(xpath="//div[@class='custom-control custom-switch right-switch switch-mod float-left']")
	WebElement excludebtn;
	
	@FindBy(xpath="//div[@id='accounts-content']//div//div//div//div//div")
	WebElement excludebtnForClick;
	
	@FindBy(xpath="//input[contains(@class, 'custom-control-input')]")
	WebElement switchToggle;
	
	@FindBy(xpath="//a[normalize-space()='Agent (SUP)']")
	WebElement agentSUPtab;
	
	public void goToTheAgentSUPTab() {
		agentSUPtab.click();
	}
	
	public boolean verifyExcludeBtn() {
		boolean tr=switchToggle.isSelected();
		return tr;
	}
	
	public void clearSearchField() {
		searchNameField.clear();
	}
	
	public void clickOnExcludeQuotebtn() {
		switchToggle.click();
	}
	
	public String verifyName() {
		String name=verifyName.getText();
		return name;
	}
	public void clickOnAddAgetSP() {
		addagentbtn.click();
	}
	
	public void clickOnTeamleaderTab() {
		teamleaderTab.click();
	}
	
	public void clickONTeamTab() {
		teamTabl.click();
	}
	
	public void clickOnAddAgentTab() {
		addNewAgent.click();
	}
	
	public void closeAgentPopUp() {
		closePopupAgent.click();
	}
	
	public void logoutTeaml() {
		logoutTeamL.click();
	}
	
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

	@FindBy(xpath = "//label[contains(text(),'Maxwell')]")
	WebElement agentTest;
	
	public void selectAgentSUPForClientApp(WebDriver driver,String AgentSupplierName) {
		String path="//label[contains(text(),'"+AgentSupplierName+"')]";
		WebElement agentSup=driver.findElement(By.xpath(path));
		agentSup.click();
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
	
	@FindBy(xpath="//a[contains(text(),'Teamleader (SP)')]")
	WebElement teamleader;
	
	public void getTeamleaderTab() {
		teamleader.click();
	}

}
