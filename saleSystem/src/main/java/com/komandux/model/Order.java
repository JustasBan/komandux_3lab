package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Order {
	@ApiModelProperty(dataType="int", name="order id")
	private int Id;
	
	@ApiModelProperty(dataType="int", name="customer id")
	private int Cust_id;
	
	@ApiModelProperty(dataType="int", name="tracking code")
	private int tracking_code;

	@ApiModelProperty(dataType="int", name="status code")
	private int status;

	@ApiModelProperty(dataType="String", name="note text")
	private String note;

	@ApiModelProperty(dataType="LocalTime", name="requested timestamp")
	private String requested_timestamp;

	@ApiModelProperty(dataType="LocalTime", name="estimated timestamp")
	private String estimated_timestamp;

	public Order(int id, int cust_id, int tracking_code, int status, String note, String requested_timestamp,
			String estimated_timestamp) {
		super();
		Id = id;
		Cust_id = cust_id;
		this.tracking_code = tracking_code;
		this.status = status;
		this.note = note;
		this.requested_timestamp = requested_timestamp;
		this.estimated_timestamp = estimated_timestamp;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCust_id() {
		return Cust_id;
	}

	public void setCust_id(int cust_id) {
		Cust_id = cust_id;
	}

	public int getTracking_code() {
		return tracking_code;
	}

	public void setTracking_code(int tracking_code) {
		this.tracking_code = tracking_code;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRequested_timestamp() {
		return requested_timestamp;
	}

	public void setRequested_timestamp(String requested_timestamp) {
		this.requested_timestamp = requested_timestamp;
	}

	public String getEstimated_timestamp() {
		return estimated_timestamp;
	}

	public void setEstimated_timestamp(String estimated_timestamp) {
		this.estimated_timestamp = estimated_timestamp;
	}
}
