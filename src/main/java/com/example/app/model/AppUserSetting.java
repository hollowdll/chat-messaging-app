package com.example.app.model;

public class AppUserSetting {

	private AppUser appUser;
	private AppSetting appSetting;
	private String value;
	
	public AppUserSetting() {
		
	}

	public AppUserSetting(AppUser appUser, AppSetting appSetting, String value) {
		this.appUser = appUser;
		this.appSetting = appSetting;
		this.value = value;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public AppSetting getAppSetting() {
		return appSetting;
	}

	public void setAppSetting(AppSetting appSetting) {
		this.appSetting = appSetting;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "AppUserSetting [appUser=" + appUser + ", appSetting=" + appSetting + ", value=" + value + "]";
	}
	
}
