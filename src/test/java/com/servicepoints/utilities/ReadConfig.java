package com.servicepoints.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	Properties po;
	
	public ReadConfig(){
		
		File src=new File("./Configurations/config.properties");
		
		try {
			po=new Properties();
			FileInputStream fis=new FileInputStream(src);
			po.load(fis);		
		}
		catch(Exception e){
			System.out.println("Exception is "+e.getMessage());
		}	
	}
	
	public String getApplicationUrl() {
		String url=po.getProperty("appURL");
		return url;
	}
	
	public String getAdminUsername() {
		String uname=po.getProperty("Admin_emailid");
		return uname;
	}
	
	public String getAdminPassword() {
		String pwd=po.getProperty("Admin_password");
		return pwd;
	}
	
	public String getChromePath() {
		String chromepath=po.getProperty("chromepath");
		return chromepath;
	}
	public String getFirefoxPath() {
		String chromepath=po.getProperty("Firefoxpath");
		return chromepath;
	}
	
	public String getClientName() {
		String clientName=po.getProperty("ClientName");
		return clientName;
	}
	
	public String getFirstName() {
		String fname=po.getProperty("FirstName");
		return fname;
	}
	
	public String getLastName() {
		String lname=po.getProperty("LastName");
		return lname;
	}
	
	public String getCountryCode() {
		String code=po.getProperty("CountryCode");
		return code;
	}
	
	public String getOrders() {
		String orders=po.getProperty("Orders");
		return orders;
	}
	public String getUserEmail() {
		String email=po.getProperty("UserEmail");
		return email;
	}
	public String getPassword() {
		String pass=po.getProperty("Password");
		return pass;
	}
	
	public String getConfirmPass() {
		String cpass=po.getProperty("CPassword");
		return cpass;
	}
	
	public String getUserName() {
		String uname=po.getProperty("UserName");
		return uname;
	}
	
	public String getAgentSupplierName() {
		String agentsup=po.getProperty("AgentSupplierName");
		return agentsup;
	}
	
	public String getAgentSupportName() {
		String agentsp=po.getProperty("AgentSupportName");
		return agentsp;
	}
	
	public String getAdminSUPName() {
		String adminsup=po.getProperty("AdminSupplier");
		return adminsup;				
	}
	
	public String getSuppllierURL() {
		String supurl=po.getProperty("SupplierURL");
		return supurl;
	}
	
	public String getSupFirstName() {
		String supfname=po.getProperty("SUPFirstName");
		return supfname;
	}
	
	public String getSupLastName() {
		String suplname=po.getProperty("SUPLastName");
		return suplname;
	}
	
	public String getsupCc() {
		String supcode=po.getProperty("CCode");
		return supcode;
	}
	
	public String getSupEmail() {
		String supemail=po.getProperty("Umail");
		return supemail;
	}
	
	public String getSupPass() {
		String suppass=po.getProperty("Pword");
		return suppass;
	}
	
	public String getSupCpass() {
		String supcpass=po.getProperty("Cword");
		return supcpass;
	}
	
	public String getAdminSUPmail() {
		String admemail=po.getProperty("AdminSupEmail");
		return admemail;
	}
	
	public String getAdminPass() {
		String suppass=po.getProperty("AdminsupPass");
		return suppass;
	}
	
	public String getAgentfname() {
		String fname=po.getProperty("Fname");
		return fname;
	}
	
	public String getAgentlname() {
		String lname=po.getProperty("Lname");
		return lname;
	}
	
	public String getCode() {
		String code=po.getProperty("Code");
		return code;
	}
	
	public String getEmail() {
		String mail=po.getProperty("Email");
		return mail;
	}
	
	public String getPass() {
		String pass=po.getProperty("Pass");
		return pass;
	}
	
	public String getcPass() {
		String cpass=po.getProperty("cpass");
		return cpass;
	}
	
	public String setAgentSupEmail() {
		String mail=po.getProperty("AgentSupplierEmail");
		return mail;
	}
	
	public String setAgentSupPass() {
		String suppass=po.getProperty("AgentSupplierPass");
		return suppass;
	}
	
	public String setProduct() {
		String product=po.getProperty("Product");
		return product;			
	}
	
	public String setProductForVerifyUpdate() {
		String setProduct=po.getProperty("verifyProduct");
		return setProduct;
	}
	
	public String set1PcsPrice() {
		String fpcs=po.getProperty("1PcsPrice");
		return fpcs;
	}
	
	public String set2PcsPrice() {
		String spcs=po.getProperty("2PcsPrice");
		return spcs;
	}
	
	public String set3PcsPrice() {
		String tpcs=po.getProperty("3PcsPrice");
		return tpcs;
	}
	
	public String set4PcsPrice() {
		String ffpcs=po.getProperty("4PcsPrice");
		return ffpcs;
	}
	
	public String setClientEmail() {
		String cemail=po.getProperty("ClientEmail");
		return cemail;
	}
	
	public String setClientPassword() {
		String clpass=po.getProperty("ClientPass");
		return clpass;
	}
	
	public String setProductName() {
		String product=po.getProperty("SelProduct");
		return product;
	}
	
	public String setDomainName() {
		String  domain=po.getProperty("DomainName");
		return domain;
	}
	
	public String setAlias() {
		String alias=po.getProperty("Alias");
		return alias;
	}
	
	public String setStorePass() {
		String storePass=po.getProperty("StorePass");
		return storePass;
	}
	
	public String setFetchDate() {
		String fetchDate=po.getProperty("OrderFetchDate");
		return fetchDate;
	}
	
	public String editAlias() {
		String editAlias=po.getProperty("EditStoreAlias");
		return editAlias;
	}
	
	public String setTeamlName() {
		String tname=po.getProperty("TeamleaderName");
		return tname;
	}
	
	public String setFName() {
		String tfname=po.getProperty("tfname");
		return tfname;
	}
	
	public String setlName() {
		String tlname=po.getProperty("tlname");
		return tlname;
	}
	
	public String settCode() {
		String tcode=po.getProperty("tcode");
		return tcode;
	}
	
	public String settEmail() {
		String temail=po.getProperty("temail");
		return temail;
	}
	
	public String setTPass() {
		String tpass=po.getProperty("tpass");
		return tpass;
	}
	
	public String setCPass() {
		String cpass=po.getProperty("tcpass");
		return cpass;
	}
	
	public String setAgentNameForExcldOpen() {
		String clname=po.getProperty("AgentSupplier");
		return clname;
	}
	
	public String setAgentsAdminName() {
		String cladminName=po.getProperty("AgentOfAdmin");
		return cladminName;
	}
	
	
	//Methods for change price in Ask for price change
	public String setChangePrice1Pcs() {
		String c1price=po.getProperty("1CPcsPrice");
		return c1price;
	}
	
	public String setChangePrice2Pcs() {
		String c2price=po.getProperty("2CPcsPrice");
		return c2price;
	}
	
	public String setChangePrice3Pcs() {
		String c3price=po.getProperty("3CPcsPrice");
		return c3price;
	}
	
	public String setChangePrice4Pcs() {
		String c4price=po.getProperty("4CPcsPrice");
		return c4price;
	}
	//Methods for Veify Add New Varient
	public String fetchProducts() {
		String purl=po.getProperty("ProductFetchURL");
		return purl;
	}
	
	public String fetchOrders() {
		String ourl=po.getProperty("OrderFetchURL");
		return ourl;
	}
	
	public String setCemail() {
		String cmail=po.getProperty("Cemail");
		return cmail;
	}
	
	public String setClientpass() {
		String cpass=po.getProperty("CPass");
		return cpass;
	}
	
	public String setProductAdded() {
		String cpro=po.getProperty("ProductAdded");
		return cpro;
	}
	
	public String setDropDownStatus() {
		String status=po.getProperty("Status");
		return status;
	}
	
	public String setAgentEmail() {
		String email=po.getProperty("Aemail");
		return email;
	}
	public String setAgentPass() {
		String pass=po.getProperty("Apass");
		return pass;
	}
	
	public String setOrderStatus2() {
		String status2=po.getProperty("Status2");
		return status2;
	}
	
	public String setCEmailFrDispt() {
		String cdispute=po.getProperty("CMailDispute");
		return cdispute;
	}
	
	public String setCpassForDispute() {
		String cpassdis=po.getProperty("CPassDispute");
		return cpassdis;
	}
	
	public String setProductForDsp() {
		String pdsp=po.getProperty("ProductDsp");
		return pdsp;
	}
	
	public String setQueries() {
		String queries=po.getProperty("Queries");
		return queries;
	}
	
	public String setAMailDsp() {
		String amail=po.getProperty("AgentMailDsp");
		return amail;
	}
	
	public String setApassDsp() {
		String apass=po.getProperty("AgentPassDsp");
		return apass;
	}
	
	public String setProductDsp() {
		String padsp=po.getProperty("ProductAcceptDsp");
		return padsp;
	}
	
	public String setAnswer() {
		String ans=po.getProperty("Answer");
		return ans;
	}
	
	public String setOtherTxt() {
		String txt=po.getProperty("OtherTextBox");
		return txt;
	}
	
	public String setAgentTrackMail() {
		String atrackmail=po.getProperty("AgentTrackMail");
		return atrackmail;
	}
	
	public String setAgentTrackPass() {
		String atrackpass=po.getProperty("AgentTrackPass");
		return atrackpass;
	}
	
	public String setPnameTracking() {
		String ptname=po.getProperty("ProductTrackingName");
		return ptname;
	}
	
	public String setProcessStatus() {
		String process=po.getProperty("ProcessStatus");
		return process;
	}
	
	public String setTrackingNum() {
		String trackingNum=po.getProperty("TrackingNumber");
		return trackingNum;
	}
	
	public String setClientTrackMail() {
		String clientMail=po.getProperty("ClientTrackMail");
		return clientMail;
	}
	
	public String setClientTrackPass() {
		String clientPass=po.getProperty("ClientTrackPass");
		return clientPass;
	}
	
	public String setFullfill() {
		String fullfill=po.getProperty("OrderStatus");
		return fullfill;
	}
	
	public String setAgent1NameTwoQuote() {
		String name=po.getProperty("AgentSUPname1");
		return name;
	}
	
	public String setProductForTwoQuote() {
		String product=po.getProperty("ProductTQuote");
		return product;
	}
	
	public String setAgent2NameTwoQuote() {
		String pname=po.getProperty("AgentSUPname2");
		return pname;
	}
	
	public String setProductToDeclinedDsp() {
		String pdsp=po.getProperty("ProductToDeclinedDsp");
		return pdsp;
	}
	
	public String setProductForConversation() {
		String pc=po.getProperty("productToConverse");
		return pc;
	}
	
	public String setSRClientMail() {
		String srcmail=po.getProperty("ClientMailSR");
		return srcmail;
	}
	
	public String setSRClientPass() {
		String srcpass=po.getProperty("ClientPassSR");
		return srcpass;
	}
	
	public String setProductSR() {
		String srproduct=po.getProperty("PoductSR");
		return srproduct;
	}
	
	public String setTeamleadSR() {
		String teamlead=po.getProperty("TeamleadSR");
		return teamlead;
	}
	
	public String setAdminSPSR() {
		String adminSr=po.getProperty("AdminSupportSR");
		return adminSr;
	}
	
	public String setAgentSPSR() {
		String agentsp=po.getProperty("AgentSupportSR");
		return agentsp;
	}
	
	public String setAnswerSR() {
		String answerSr=po.getProperty("AnswerSR");
		return answerSr;
	}
	
	public String setProductRefundDD() {
		String productdd=po.getProperty("productRefundDD");
		return productdd;
	}
	
	public String setProductRefundConv() {
		String prorefundConv=po.getProperty("productRefundConv");
		return prorefundConv;
	}
	
	public String setProductResend() {
		String productResend=po.getProperty("productResend");
		return productResend;
	}
	
	public String setProductDeclineResend() {
		String proDResend=po.getProperty("productDResend");
		return proDResend;
	}
	
	public String setProductCancelOrder() {
		String proCancelOrder=po.getProperty("proCancelOrder");
		return proCancelOrder;
	}
	
	public String setProductForPriceChange() {
		String proPrice=po.getProperty("ProductAskForPrice");
		return proPrice;
	}
	
	public String setClientMailForDeleteQuote() {
		String cemail=po.getProperty("CDeleteQuoteMail");
		return cemail;
	}
	
	public String setClientPassForDeleteQuote() {
		String cpass=po.getProperty("CDeleteQuotePass");
		return cpass;
	}
	
	public String setClientNameForPayment() {
		String cname=po.getProperty("ClientForPayment");
		return cname;
	}
	
	public String setPriceForPayment() {
		String price=po.getProperty("AmountForPayment");
		return price;
	}
	
	public String setRemarkForPayment() {
		String remark=po.getProperty("Remark");
		return remark;
	}
	
	public String setClientMForStore() {
		String cmail=po.getProperty("CMailForStore");
		return cmail;
	}
	
	public String setCPassForStore() {
		String cpass=po.getProperty("CPassForStore");
		return cpass;
	}
	
	public String setCmailForPayment() {
		String cMailPayment=po.getProperty("CMailForPayment");
		return cMailPayment;
	}
	
	public String setCPassForPayment() {
		String cMailPayment=po.getProperty("CPassForPayment");
		return cMailPayment;
	}
	
	public String setAmailForPayment() {
		String cMailPayment=po.getProperty("AMailForPayment");
		return cMailPayment;
	}
	
	public String setAPassForPayment() {
		String cMailPayment=po.getProperty("APassForPayment");
		return cMailPayment;
	}
	
	public String setProductForPaymentPO() {
		String cMailPayment=po.getProperty("ProductForPayment");
		return cMailPayment;
	}
	
	public String setCNameForPaymentPO() {
		String cname=po.getProperty("CNameForPayment");
		return cname;
	}
	
	public String setANameForPaymentPO() {
		String cname=po.getProperty("ANameForPayment");
		return cname;
	}
	
	public String setASupportForPayment() {
		String spmail=po.getProperty("ASupportForPayment");
		return spmail;
	}
	
	public String setASPPassForPayment(){
		String sppass=po.getProperty("ASPPassForPayment");
		return sppass;
	}
	
	public String setSecProductName() {
		String secPro=po.getProperty("SecondProductName");
		return secPro;
	}
	
	public String setCMailForRequote() {
		String cmail=po.getProperty("cMailForRequote");
		return cmail;
	}
	
	public String setCPassForRequote() {
		String cpass=po.getProperty("cPassForRequote");
		return cpass;
	}
	
	public String setProductForRequote() {
		String proRequote=po.getProperty("productForRequote");
		return proRequote;
	}
	
	public String setAgentMailForRequote() {
		String agentmForRequote=po.getProperty("AgentMailRequote");
		return agentmForRequote;
	}
	
	public String setAgentPassForRequote() {
		String agentpForRequote=po.getProperty("AgentPassRequote");
		return agentpForRequote;
	}
	
	public String setAdd() {
		String addError=po.getProperty("AddError");
		return addError;
	}
	
	public String setZip() {
		String zip=po.getProperty("zip");
		return zip;
	}
	
	public String setProForAddError() {
		String proAdd=po.getProperty("thirdProForAE");
		return proAdd;
	}
	
	public String setClntMailForAffiliate() {
		String clntMail=po.getProperty("clntMailForAffiliate");
		return clntMail;
	}
	
	public String setClntPassForAffiliate() {
		String clntPass=po.getProperty("clntPassForAffiliate");
		return clntPass;
	}
	
	public String setFnameAf() {
		String fname=po.getProperty("fnameAf");
		return fname;
	}
	
	public String setLnameAf() {
		String lname=po.getProperty("lnameAf");
		return lname;
	}
	
	public String setCodeAf() {
		String codeAf=po.getProperty("codeAf");
		return codeAf;
	}
	
	public String setOrdersAf() {
		String  ordersAf=po.getProperty("ordersAf");
		return ordersAf;
	}
	
	public String setPasswordAf() {
		String passwordAf=po.getProperty("passwordAf");
		return passwordAf;
	}
	
	public String setCpasswordAf() {
		String cpassAf=po.getProperty("cpasswordAf");
		return cpassAf;
	}
	
	public String setUserMailAf() {
		String afmail=po.getProperty("UserEmailAf");
		return afmail;
	}
	
	public String setUserNameAf() {
		String unameAf=po.getProperty("userNameAF");
		return unameAf;
	}
	
	public String setDomainNameAf() {
		String domainName=po.getProperty("domainNameAf");
		return domainName;
	}
	
	public String setAliasAf() {
		String aliasAf=po.getProperty("aliasAf");
		return aliasAf;
	}
	
	public String setStorePassAf() {
		String storePass=po.getProperty("storePassAf");
		return storePass;
	}
	
	public String setDateOrderAf() {
		String dateOrderAf=po.getProperty("dateOrderAf");
		return dateOrderAf;
	}
	
	public String setClientMailForFulfilment() {
		String cmailssf=po.getProperty("cmailSSF");
		return cmailssf;
	}
	
	public String setClientPassForFulfilment() {
		String cpassssf=po.getProperty("cPassSSF");
		return cpassssf;
	}
	
	public String setAgentMailSsf() {
		String agentssf=po.getProperty("agentMailSSF");
		return agentssf;
	}
	
	public String setAgentPassSsf() {
		String agentPassSsf=po.getProperty("agentPassSSF");
		return agentPassSsf;
	}
	
	public String setProductSSF() {
		String proSsf=po.getProperty("productSSF");
		return proSsf;
	}
	
	public String setClientMailMergeBreakOrder() {
		String cmailmbo=po.getProperty("clientMailMBO");
		return cmailmbo;
	}
	
	public String setClientPassMergeBreakOrder() {
		String cpassmbo=po.getProperty("clientPassMBO");
		return cpassmbo;
	}
	
	public String setAgentMailMergeBreakOrder() {
		String agentmbo=po.getProperty("agentMailMBO");
		return agentmbo;
	}
	
	public String setAgentPassMergeBreakOrder() {
		String agentPassmbo=po.getProperty("agentPassMBO");
		return agentPassmbo;
	}
	
	public String setProductMergeBreakOrder() {
		String proMbo=po.getProperty("productMBO");
		return proMbo;
	}
	
	
	
}



