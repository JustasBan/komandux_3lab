package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Discount {
	@ApiModelProperty(dataType="int")
	private int id;
	@ApiModelProperty(dataType="int")
	private int service_id;
	@ApiModelProperty(dataType="int")
	private int percentage_off;
	@ApiModelProperty(dataType="int")
	private int exact_price;
	@ApiModelProperty(dataType="LocalTime", example="2022-12-31 20:32:50")
	private String created_timestamp;
	
	public Discount(int id, int service_id, int percentage_off, int exact_price, String created_timestamp) {
		super();
		this.id = id;
		this.service_id = service_id;
		this.percentage_off = percentage_off;
		this.exact_price = exact_price;
		this.created_timestamp = created_timestamp;
	}
	
	public Discount(int id, int service_id, int percentage_off, int exact_price, LocalTime created_timestamp) {
		super();
		this.id = id;
		this.service_id = service_id;
		this.percentage_off = percentage_off;
		this.exact_price = exact_price;
		this.created_timestamp = created_timestamp.toString();
	}
	
	public Discount(int service_id, int percentage_off, int exact_price, String created_timestamp) {
		super();
		this.service_id = service_id;
		this.percentage_off = percentage_off;
		this.exact_price = exact_price;
		this.created_timestamp = created_timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getPercentage_off() {
		return percentage_off;
	}

	public void setPercentage_off(int percentage_off) {
		this.percentage_off = percentage_off;
	}

	public int getExact_price() {
		return exact_price;
	}

	public void setExact_price(int exact_price) {
		this.exact_price = exact_price;
	}

	public String getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
}
