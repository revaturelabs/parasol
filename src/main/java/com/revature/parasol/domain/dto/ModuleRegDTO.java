package com.revature.parasol.domain.dto;

import java.util.List;

public class ModuleRegDTO {
	
	//Module URL
	private String url;
	//Module Name
	private String moduleName;
	//Checked Data - Roles
	private List<RolesDTO> checkedData;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public List<RolesDTO>  getCheckedData() {
		return checkedData;
	}
	public void setCheckedData(List<RolesDTO>  checkedData) {
		this.checkedData = checkedData;
	}
}
