package org.common.login.LoginService.Model;

import java.util.Date;

public class LoginDAO {
    private String username;
    private String password;
    private String loginstatus;
    private String lastlogindate;
   
	public LoginDAO(){
    	
    }
	public LoginDAO(String username, String password, String loginstatus, String lastlogindate) {
		this.username = username;
		this.password = password;
		if (loginstatus.isEmpty()){
			this.loginstatus = "";
		}
		else
			this.loginstatus=loginstatus;
		this.lastlogindate=lastlogindate;
	}
	
	public LoginDAO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLoginstatus() {
		return loginstatus;
	}
	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}
	public String getLastlogindate() {
		return lastlogindate;
	}
	public void setLastlogindate(String lastlogindate) {
		this.lastlogindate = lastlogindate;
	}
	
	@Override
	public String toString() {
		return "LoginDoc [username=" + username + ", password=" + password + ", loginstatus=" + loginstatus
				+ ", lastlogintime=" + lastlogindate + "]";
	}
	
	
}
