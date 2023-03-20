package com.example.app.model;

public class AppSetting {

	private Long appSettingId;
	private String description;
	
	public AppSetting() {
		
	}

	public AppSetting(String description) {
		this.description = description;
	}

	public Long getAppSettingId() {
		return appSettingId;
	}

	public void setAppSettingId(Long appSettingId) {
		this.appSettingId = appSettingId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AppSetting [appSettingId=" + appSettingId + ", description=" + description + "]";
	}
	
}
