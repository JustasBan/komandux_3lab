package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderedService {
	@ApiModelProperty(dataType="int")
	private int id;
	@ApiModelProperty(dataType="int")
	private int order_id;
	@ApiModelProperty(dataType="int")
	private int service_id;
	@ApiModelProperty(dataType="int")
	private int created_timestamp;
	@ApiModelProperty(dataType="LocalTime")
	private LocalTime estimated_finish_time;
	@ApiModelProperty(dataType="boolean")
	private boolean paid;
	
	public OrderedService(int id, int order_id, int service_id, int created_timestamp, LocalTime estimated_finish_time,
			boolean paid) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.service_id = service_id;
		this.created_timestamp = created_timestamp;
		this.estimated_finish_time = estimated_finish_time;
		this.paid = paid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(int created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public LocalTime getEstimated_finish_time() {
		return estimated_finish_time;
	}

	public void setEstimated_finish_time(LocalTime estimated_finish_time) {
		this.estimated_finish_time = estimated_finish_time;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	
}
