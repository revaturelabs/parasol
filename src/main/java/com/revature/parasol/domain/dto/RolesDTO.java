package com.revature.parasol.domain.dto;

public class RolesDTO {
	
	//The role
	private String value;
	private boolean checked;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
