package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Service {
	@ApiModelProperty(dataType="int")
	private int id;
	
	@ApiModelProperty(dataType="int")
	private int org_id;
	
	@ApiModelProperty(dataType="int")
	private int price;

	@ApiModelProperty(dataType="String")
	private String description;
	
	@ApiModelProperty(dataType="LocalTime", example="2022-12-31 20:32:50")
	private String created_timestamp;
	
	@ApiModelProperty(dataType="Boolean")
	private Boolean available;
	
	@ApiModelProperty(dataType="int")
	private int loyalty_point_reward;

	public Service(int id, int org_id, int price, String description, String created_timestamp, Boolean available,
			int loyalty_point_reward) {
		super();
		this.id = id;
		this.org_id = org_id;
		this.price = price;
		this.description = description;
		this.created_timestamp = created_timestamp;
		this.available = available;
		this.loyalty_point_reward = loyalty_point_reward;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrg_id() {
		return org_id;
	}

	public void setOrg_id(int org_id) {
		this.org_id = org_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public int getLoyalty_point_reward() {
		return loyalty_point_reward;
	}

	public void setLoyalty_point_reward(int loyalty_point_reward) {
		this.loyalty_point_reward = loyalty_point_reward;
	}
	
	
}
