package com.komandux.model;

import org.json.simple.JSONObject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EmployeeOrganization {
	@ApiModelProperty(dataType="int")
	private int id;
	@ApiModelProperty(dataType="int")
	private int user_id;
	@ApiModelProperty(dataType="int")
	private int org_id;
	@ApiModelProperty(dataType="JSONObject")
	private JSONObject access;
	
	public EmployeeOrganization(int id, int user_id, int org_id, JSONObject access) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.org_id = org_id;
		this.access = access;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
