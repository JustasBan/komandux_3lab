package com.komandux.model;

import java.time.LocalTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Shift {
	@ApiModelProperty(dataType="int")
	private int id;
	@ApiModelProperty(dataType="int")
	private int emp_org_id;
	@ApiModelProperty(dataType="LocalTime")
	private LocalTime start_time;
	@ApiModelProperty(dataType="LocalTime")
	private LocalTime end_time;
	@ApiModelProperty(dataType="LocalTime")
	private LocalTime created_timestamp;
	
	public Shift(int id, int emp_org_id, LocalTime start_time, LocalTime end_time, LocalTime created_timestamp) {
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

	public LocalTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}

	public LocalTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}

	public LocalTime getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(LocalTime created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
	
	
}
