package com.komandux.model;

import java.time.LocalTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Transaction {
	@ApiModelProperty(dataType="int", name="transaction Id")
	private int id;
	
	@ApiModelProperty(dataType="int", name="organization Id")
	private int emp_org_id;

	@ApiModelProperty(dataType="int", name="customer id")
	private int cust_id;
	
	@ApiModelProperty(dataType="int", name="order id")
	private int order_id;

	@ApiModelProperty(dataType="int", name="amount")
	private int amount;
	
	@ApiModelProperty(dataType="TransactionType", name="type")
	private TransactionType type;
	
	@ApiModelProperty(dataType="Date", name="date of creation")
	private Date  created_timestamp;
	
	public Transaction(int id, int emp_org_id, int cust_id, int order_id, int amount, TransactionType type,
			Date created_timestamp) {
		super();
		this.id = id;
		this.emp_org_id = emp_org_id;
		this.cust_id = cust_id;
		this.order_id = order_id;
		this.amount = amount;
		this.type = type;
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

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Date getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(Date created_timestamp) {
		this.created_timestamp = created_timestamp;
	}
}
