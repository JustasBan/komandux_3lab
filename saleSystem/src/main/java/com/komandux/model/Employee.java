package com.komandux.model;

import java.time.LocalTime;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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

	public Employee(int user_id2, int organization_id, String access2) throws ParseException {
		this.user_id = user_id2;
		this.org_id = organization_id;
		
		JSONParser parser = new JSONParser();
		System.out.println(access2);
		this.access = (JSONObject) parser.parse(access2);
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
