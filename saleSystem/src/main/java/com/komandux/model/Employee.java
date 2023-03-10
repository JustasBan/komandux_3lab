package com.komandux.model;

import org.json.simple.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Employee extends User{
	@ApiModelProperty(dataType="int")
	private int org_id;
	@ApiModelProperty(dataType="JSONObject")
	private JSONObject access;
	
	public Employee(int org_id, JSONObject access, String password, String email, String full_name, String created_date, String phonenumber) {
		super(password, email, full_name, created_date, phonenumber);
		this.org_id = org_id;
		this.access = access;
	}


	public int getOrg_id() {
		return org_id;
	}


	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}


	public JSONObject getAccess() {
		return access;
	}


	public void setAccess(JSONObject access) {
		this.access = access;
	}
	
	
}
