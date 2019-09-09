package com.systemuser;

public class CurrentSystemUser {
	String userName;
	String userPassword;
	
	
	public CurrentSystemUser(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	
	public String getUserName() {
		return userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	//method checks that user has a password set. only password because if no user name is provided, then the userrealname will be assumed
	public boolean isComplete() {
		if(this.userName == null || this.userPassword == null || this.userName.isEmpty() || this.userPassword.isEmpty())
			return false;
		return true;
	}
}
