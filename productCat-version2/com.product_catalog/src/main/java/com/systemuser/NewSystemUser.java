package com.systemuser;

public class NewSystemUser extends CurrentSystemUser{
	private String userRealName;

	
	//constructor with parameters
	public NewSystemUser(String userRealName, String userName, String userPassword) {
		super(userName, userPassword);
		this.userRealName = userRealName;
	}
	
	
	//getters
	public String getUserRealName() {
		return userRealName;
	}
	
	
	//setters
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	
	@Override
	public boolean isComplete() {
			if(this.userRealName != null && !this.userRealName.isEmpty() && this.userPassword != null && !this.userPassword.isEmpty())
										//if the user has a real name and a password
				return true;//then they are complete
		return true;
	}
}
