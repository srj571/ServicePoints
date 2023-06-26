package com.servicepoints.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientStoresPage {
	
	WebDriver rdriver;
	public ClientStoresPage(WebDriver ldriver) {
		rdriver=ldriver;
		PageFactory.initElements(ldriver, this);
	}
	
	
	@FindBy(xpath="(//div[normalize-space()='Stores'])[1]")
	WebElement storesPage;
	
	@FindBy(xpath="//div[@class='float-right']//a[@class='btn btn-primary btn-sm']")
	WebElement AddNewStorebtn;
	
	@FindBy(xpath="//input[@name='domainName']")
	WebElement txtdomain;
	
	@FindBy(xpath="//input[@name='allias']")
	WebElement txtAlias;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='order_fetch_date']")
	WebElement txtOrderFetch;
	
	@FindBy(xpath="//button[@id='saveStore']")
	WebElement saveStore;
	
	//Store Updation
	
	@FindBy(xpath="(//div[@class='table_list_item'])[1]")
	WebElement seeMoreTxt;
	
	@FindBy(xpath="(//i)[8]")
	WebElement edit;
	
	@FindBy(xpath="(//input[@name='allias'])[1]")
	WebElement txtaliasEdit;
	
	@FindBy(xpath="(//i)[9]")
	WebElement savebtn;
	
	@FindBy(xpath="(//span[contains(text(),'Ecomaprils2re')])[1]")
	WebElement verifyEle;
	
	//Store Deletion
	@FindBy(xpath="(//div[@class='table-zindex-btn'])[1]")
	WebElement deleteStorebtn;
	
	@FindBy(xpath="//button[@id='del_store']")
	WebElement deletebtn;
	
	@FindBy(xpath="(//div[@class='col'])[6]//div//div[@class='col']/span")
	WebElement eleCheck;
	
	public String checkElementText() {
		String check=eleCheck.getText();
		return check;
	}
	
	public void goToStoresPage() {
		storesPage.click();
	}
	
	public void clickOnAddNewStorebtn() {
		AddNewStorebtn.click();
	}
	
	public void setDomainName(String dname) {
		txtdomain.sendKeys(dname);
	}
	
	public void setAlias(String alias) {
		txtAlias.sendKeys(alias);
	}
	
	public void setPassword(String pass) {
		txtPassword.sendKeys(pass);
	}
	
	public void setOrderFetch(String date) {
		txtOrderFetch.sendKeys(date);
	}
	
	public void clickOnSaveBtn() {
		saveStore.click();
	}
	
	//Store Updation
	
	public void clickSeeMore() {
		seeMoreTxt.click();
	}
	
	public void clickOnEditStore() {
		edit.click();
	}
	
	public void editStoreName(String alias) {
		txtaliasEdit.sendKeys(alias);
	}
	public void clickOnSave() {
		savebtn.click();
	}
	
	public String getEditedStore() {
		String val=verifyEle.getText();
		return val;
	}
	
	public void clearAlias() {
		txtaliasEdit.clear();
	}
	//Store Deletion
	
	public void clickOnDeleteStoreBtn() {
		deleteStorebtn.click();
	}
	
	public void clickOnDelePop() {
		deletebtn.click();
	}
	
	
	
	
	
}
