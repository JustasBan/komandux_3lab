package com.komandux.model;

import java.time.LocalTime;

import org.json.simple.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Employee extends User{
	@ApiModelProperty(dataType="int")
	private int user_id;
	@ApiModelProperty(dataType="int")
	private int org_id;
	@ApiModelProperty(dataType="JSONObject")
	private JSONObject access;
	
	public Employee(int user_id, int org_id, JSONObject access, int id, String password, String email, String full_name, LocalTime created_date, String phonenumber) {
		super(id, password, email, full_name, created_date, phonenumber);
		this.user_id = user_id;
		this.org_id = org_id;
		this.access = access;
	}

	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
