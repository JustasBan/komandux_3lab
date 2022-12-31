package com.komandux.model;

import java.time.LocalTime;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Employee extends User{
	@ApiModelProperty(dataType="int")
	private int org_id;
	@ApiModelProperty(dataType="JSONObject")
	private JSONObject access;
	
	public Employee(int user_id, int org_id, JSONObject access, String password, String email, String full_name, Date created_date, String phonenumber) {
		super(user_id, password, email, full_name, created_date, phonenumber);
		this.org_id = org_id;
		this.access = access;
	}

	public Employee(int user_id, int organization_id, String access2) throws ParseException {
		
		super(user_id);
		this.org_id = organization_id;
		
		JSONParser parser = new JSONParser();
		this.access = (JSONObject) parser.parse(access2);
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
