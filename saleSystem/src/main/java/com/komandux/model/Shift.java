package com.komandux.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Shift {
	@ApiModelProperty(dataType="int")
	private int id;
	@ApiModelProperty(dataType="int")
	private int emp_org_id;
	@ApiModelProperty(dataType="String", example="2022-12-31 20:32:50")
	private String start_time;
	@ApiModelProperty(dataType="String", example="2022-12-31 20:32:50")
	private String end_time;
	@ApiModelProperty(dataType="int")
	private int created_timestamp;
	
	public Shift(int id, int emp_org_id, String start_time, String end_time, int created_timestamp) {
		super();
		this.id = id;
		this.emp_org_id = emp_org_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.created_timestamp = created_timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmp_org_id() {
		return emp_org_id;
	}

	public void setEmp_org_id(int emp_org_id) {
		this.emp_org_id = emp_org_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(int created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
	
	
}
