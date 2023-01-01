package com.komandux.model;

import java.security.Timestamp;
import java.util.Date;
import java.sql.Time;
import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Coupon {
	@ApiModelProperty(dataType="int")
	private int coupon_id;
	@ApiModelProperty(dataType="int")
	private int cust_id;
	@ApiModelProperty(dataType="int")
	private int code;
	@ApiModelProperty(dataType="Date")
	private Date valid_unit;
	@ApiModelProperty(dataType="Date")
	private Date created_timestamp;
	
	public Coupon(int coupon_id, int cust_id, int code, Date created_timestamp, Date valid_unit) {
		this.coupon_id = coupon_id;
		this.cust_id = cust_id;
		this.code = code;
		this.created_timestamp = created_timestamp;
		this.valid_unit = valid_unit;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
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

	public Date getValid_unit() {
		return valid_unit;
	}

	public void setValid_unit(Date valid_unit) {
		this.valid_unit = valid_unit;
	}

	public Date getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(Date created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
	
}
