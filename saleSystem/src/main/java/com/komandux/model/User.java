package com.komandux.model;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class User {
	@ApiModelProperty(dataType="int")
	private int user_id;
	@ApiModelProperty(dataType="String")
	private String password;
	@ApiModelProperty(dataType="String")
	private String email;
	@ApiModelProperty(dataType="String")
	private String full_name;
	@ApiModelProperty(dataType="LocalTime")
	private Date created_date;
	@ApiModelProperty(dataType="String")
	private String phonenumber;
	
	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getCreated_date() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		return sdf.format(created_date);
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public User(int user_id, String password, String email, String full_name, Date created_date, String phonenumber) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.email = email;
		this.full_name = full_name;
		this.created_date = created_date;
		this.phonenumber = phonenumber;
	}

	public User(int user_id2) {
		this.user_id = user_id2;
	}
	
	
}
