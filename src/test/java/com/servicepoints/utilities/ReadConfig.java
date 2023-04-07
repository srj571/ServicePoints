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
	
	
	
	
	
}

