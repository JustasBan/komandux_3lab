package com.komandux.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Coupon {
	@ApiModelProperty(dataType="int")
	private int cust_id;
	@ApiModelProperty(dataType="int")
	private int code;
	@ApiModelProperty(dataType="String")
	private String valid_unit;
	@ApiModelProperty(dataType="String")
	private String created_timestamp;
	
	public Coupon(int cust_id, int code, String created_timestamp, String valid_unit) {
		this.cust_id = cust_id;
		this.code = code;
		this.created_timestamp = created_timestamp;
		this.valid_unit = valid_unit;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getValid_unit() {
		return valid_unit;
	}

	public void setValid_unit(String valid_unit) {
		this.valid_unit = valid_unit;
	}

	public String getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
	
}
