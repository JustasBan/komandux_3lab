package com.komandux.model;

import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Customer extends User {
	
	@ApiModelProperty(dataType="int")
	private String birth_date;
	
	public Customer(String birth_date, String password, String email, String full_name, String created_date, String phonenumber) {
		super(password, email, full_name, created_date, phonenumber);
		this.birth_date = birth_date;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}
}
