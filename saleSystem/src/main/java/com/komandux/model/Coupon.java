package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Coupon extends Discount {
	@ApiModelProperty(dataType="int")
	private int coupon_id;
	@ApiModelProperty(dataType="int")
	private int cust_id;
	@ApiModelProperty(dataType="int")
	private int discount_id;
	@ApiModelProperty(dataType="int")
	private int code;
	@ApiModelProperty(dataType="LocalTime")
	private LocalTime valid_unit;
	
	public Coupon(int coupon_id, int cust_id, int discount_id, int code, LocalTime valid_unit, int id, int service_id, int percentage_off, int exact_price, LocalTime created_timestamp) {
		super(id, service_id, percentage_off, exact_price, created_timestamp);
		this.coupon_id = coupon_id;
		this.cust_id = cust_id;
		this.discount_id = discount_id;
		this.code = code;
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

	public int getDiscount_id() {
		return discount_id;
	}

	public void setDiscount_id(int discount_id) {
		this.discount_id = discount_id;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LocalTime getValid_unit() {
		return valid_unit;
	}

	public void setValid_unit(LocalTime valid_unit) {
		this.valid_unit = valid_unit;
	}
	
}
