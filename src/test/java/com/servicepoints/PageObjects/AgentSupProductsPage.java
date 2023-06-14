package com.servicepoints.PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AgentSupProductsPage {
	
	WebDriver rdriver;
	public AgentSupProductsPage(WebDriver ldriver){
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	@FindBy(xpath="//div[normalize-space()='Products']")
	WebElement ProductsTab;
	//div[normalize-space()='Products']
	@FindBy(xpath="//label[normalize-space()='Quotations clients']")
	WebElement QuotationsClients;
	
	@FindBy(xpath="//input[@placeholder='Products, Stores or Clients']")
	WebElement serachField;
	
	@FindBy(xpath="(//a[@class='table_expand_abso cp_link'])[1]")
	WebElement divfield;
	
	@FindBy(xpath="(//input[@placeholder='1 Pcs price'])[1]")
	WebElement txt1Pcs;
	
	@FindBy(xpath="(//input[@placeholder='2 Pcs price'])[1]")
	WebElement txt2Pcs;
	
	@FindBy(xpath="(//input[@placeholder='3 Pcs price'])[1]")
	WebElement txt3Pcs;
	
	@FindBy(xpath="(//input[@placeholder='4 Pcs price'])[1]")
	WebElement txt4Pcs;
	
	@FindBy(xpath="//button[normalize-space()='Submit quotation']")
	WebElement submitquote;
	
	@FindBy(xpath="//span[@class='badge-mod badge-success']")
	WebElement QuoteStatus;
	
	@FindBy(xpath="(//div[@class='d-flex justify-content-center align-items-center mh100p'])[2]")
	WebElement seeMoreBtn;
	
	@FindBy(xpath="//span[@class='badge-mod badge-warning']")
	WebElement getStatusRequote;
	
	@FindBy(xpath="//div[@id='error_modal' and @class='modal fade bd-example-modal-sm']//h6")
	WebElement askForPriceCMsgf;
	
	public String verifyMessageAfterAFPC() {
		String val=askForPriceCMsgf.getTagName();
		return val;
	}
	
	public String getStatusRequote() {
		String val=getStatusRequote.getText();
		return val;
	}
	
	public String getStatusAwating() {
		String val=QuoteStatus.getText();
		return val;
	}
	
	public void getProductsPage() {
		ProductsTab.click();
	}
	
	public void clickQuotationsClientsTab() {
		QuotationsClients.click();
	}
	
	public void searchProductName(String product) {
		serachField.sendKeys(product);
	}
	
	public void clickOnfdiv() {
		divfield.click();
	}
	
	public void clickOnSeeMore() {
		seeMoreBtn.click();
	}
	
	public void firstPcsPrice(String fpcs) {
		txt1Pcs.sendKeys(fpcs);
	}
	
	public void secPcsPrice(String spcs) {
		txt2Pcs.sendKeys(spcs);
	}
	
	public void thirdPcsPrice(String tpcs) {
		txt3Pcs.sendKeys(tpcs);
	}
	
	public void forthPcsPrice(String ffpcs) {
		txt4Pcs.sendKeys(ffpcs);
	}
	
	public void clickOnSubmitQuote() {
		submitquote.click();
	}
	
	public boolean checkSubmitQuotebtn() {
		boolean tr=submitquote.isDisplayed();
		return tr;
	}
	
	public boolean isSubmitQuotebtnEnabled() {
		boolean submitQuote=submitquote.isEnabled();
		return submitQuote;
	}
	
	public String getStatus() {
		String text=QuoteStatus.getText();
		return text;
	}
	
	//AskForPriceChangeFunctionality
	@FindBy(xpath="//button[normalize-space()='Ask for price change']")
	WebElement askForPriceCh;
	
	public void clckOnAskForPrceChng() {
		askForPriceCh.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='Submit the new price']")
	WebElement sbmtNewPrice;
	
	public void clickOnSbmtNewPrice() {
		sbmtNewPrice.click();
	}
	
	@FindBy(xpath="//button[normalize-space()='I have notified the SP Account manager']")
	WebElement notifyPopUp;

	public void closeNotifyPopUp() {
		notifyPopUp.click();
	}
	
	public boolean clsNotifyPopUpisDisplays() {
		boolean val=notifyPopUp.isDisplayed();
		return val;
	}
	
	@FindBy(xpath="//label[@id='is_quotation_or_product_label3']")
	WebElement productsTab;
	
	public void clickOnProductsTab() {
		productsTab.click();
	}
	
	@FindBy(xpath="//div[@class='leftside-menu-fixed-bottom --transition-ease']//a[2]")
	WebElement logout;
	
	public void logpOutAgent() {
		logout.click();
	}
	
	@FindBy(xpath="//body/div[@id='new_price_popup']/div/div[1]")
	WebElement divIhvNotify;
	
	public boolean isDivVisible() {
		boolean div=divIhvNotify.isDisplayed();
		return div;
	}
	
	@FindBy(xpath="//button[normalize-space()='Update quotation']")
	WebElement updateQuotation;
	
	public void updateQuotation() {
		updateQuotation.click();
	}
	
	@FindBy(xpath="//a[normalize-space()='Delete quotation']")
	WebElement deletequote;
	
	public void deleteQuote() {
		deletequote.click();
	}
	
	@FindBy(xpath="//a[@class='btn btn-danger btn-sm btn-block deleteQuoteBTN mb-2 mb-sm-0']")
	WebElement deleteBtn;
	
	public void scrollTillDeletebtn2(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", deleteBtn);
	}
	
	@FindBy(xpath="//button[@class='btn btn-danger deleteQuotationConfirmedBtn btn-sm']")
	WebElement yesImSure;
	
	public void clickOnYesImSure() {
		yesImSure.click();
	}
	
	@FindBy(xpath="(//button[@class='br10 btn btn-border btn-sm'])[1]")
	WebElement dropDownCancelbtn;
	
	public boolean checkCancelBtnDisplayed() {
		boolean value=dropDownCancelbtn.isDisplayed();
		return value;
	}
	
	@FindBy(xpath="(//div[@class='js_table_expand_two table_expand_abso'])[2]")
	WebElement secondDiv;
	
	public void clickOnSecondDiv() {
		secondDiv.click();
	}
	
	public void scrollTillEle(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", submitquote);
	}
	
	@FindBy(xpath="//div[@class='d-xl-flex align-items-stretch justify-content-center table_expand_rel position-relative']")
	List<WebElement> divTwo;
	
	public int clickOnSecDiv() {
		int a=divTwo.size();
		return a;
	}
	
	public void scrollTillAskForPrChange(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", askForPriceCh);
	}
	
	public void scrollTillSubmitNewPrice(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", sbmtNewPrice);
	}
	
	public void scrollTillUpdateBtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", updateQuotation);
	}
	
	public void scrollTillDeleteBtn(WebDriver driver) {
		JavascriptExecutor exe=(JavascriptExecutor) driver;
		exe.executeScript("arguments[0].scrollIntoView();", deletequote);
	}
	
	public void switchOnOtherTab() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}
	
	@FindBy(xpath="//span[@title='All clients']")
	WebElement allClientsFilter;
	
	public void clickOnAllClientsFilter() {
		allClientsFilter.click();
	}
	
	@FindBy(xpath="//input[@role='searchbox']")
	WebElement allClientsSearchF;
	
	public void setClientNameInSearch(String cname) {
		allClientsSearchF.sendKeys(cname);
	}
	
	@FindBy(xpath="(//li[@role='option'])[1]")
	WebElement fClientTab;
	
	public void clickOnfClientTab() {
		fClientTab.click();
	}
	
	@FindBy(xpath="(//input[@placeholder='1 Pcs price'])[2]")
	WebElement onePcsPrice1;

	@FindBy(xpath="(//input[@placeholder='2 Pcs price'])[2]")
	WebElement twoPcsPrice2;
	
	@FindBy(xpath="(//input[@placeholder='3 Pcs price'])[2]")
	WebElement threePcsPrice3;
	
	@FindBy(xpath="(//input[@placeholder='1 Pcs price'])[3]")
	WebElement onePcsPrice4;
	
	@FindBy(xpath="(//input[@placeholder='2 Pcs price'])[3]")
	WebElement twoPcsPrice5;
	
	@FindBy(xpath="(//input[@placeholder='3 Pcs price'])[3]")
	WebElement threePcsPrice6;
	
	@FindBy(xpath="(//input[@placeholder='1 Pcs price'])[4]")
	WebElement onePcsPrice7;
	
	@FindBy(xpath="(//input[@placeholder='2 Pcs price'])[4]")
	WebElement twoPcsPrice8;
	
	@FindBy(xpath="(//input[@placeholder='3 Pcs price'])[4]")
	WebElement threePcsPrice9;
	
	@FindBy(xpath="(//h6[@class='content'])[1]")
	WebElement alertUpdateQuote;
	
	public boolean verifyTextOnAlert() {
		boolean val=alertUpdateQuote.getText().equals("Quotation accepted successfully.");
		return true;
	}
	
	public void sendValFor1Pcs1(String val1) {
		onePcsPrice1.sendKeys(val1);
		
	}
	public void sendValFor2Pcs2(String val2) {
		twoPcsPrice2.sendKeys(val2);
	}
	
	public void sendValFor3Pcs3(String val3) {
		threePcsPrice3.sendKeys(val3);
	}
	public void sendValFor1Pcs4(String val4) {
		onePcsPrice4.sendKeys(val4);
	}
	
	public void sendValFor2Pcs5(String val5) {
		twoPcsPrice5.sendKeys(val5);
	}
	public void sendValFor3Pcs6(String val6) {
		threePcsPrice6.sendKeys(val6);
	}
	public void sendValFor1Pcs7(String val7) {
		onePcsPrice7.sendKeys(val7);
	}
	public void sendValFor2Pcs8(String val8) {
		twoPcsPrice8.sendKeys(val8);
	}
	
	public void sendValFor3Pcs9(String val9) {
		threePcsPrice9.sendKeys(val9);
	}
	
}
