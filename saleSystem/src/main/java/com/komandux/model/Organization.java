package com.komandux.model;

import org.json.simple.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class Organization {

	@ApiModelProperty(dataType="int", name="organization id")
	private int id;
	@ApiModelProperty(dataType="String", name="org name")
	private String name;

	@ApiModelProperty(dataType="JSONObject", name="credentials")
	private JSONObject credentials;
	
	@ApiModelProperty(dataType="JSONObject", name="settings")
	private JSONObject settings;
	
	@ApiModelProperty(dataType="LocalTime", name="created timestamp", example="2022-12-31 20:32:50")
	private String created_timestamp;
	
	@ApiModelProperty(dataType="String", example="2022-12-31 20:32:50")
	private String opening;
	
	public Organization(int id, String name, JSONObject credentials, JSONObject settings, String created_timestamp,
			String opening, String closing, String location) {
		super();
		this.id = id;
		this.name = name;
		this.credentials = credentials;
		this.settings = settings;
		this.created_timestamp = created_timestamp;
		this.opening = opening;
		this.closing = closing;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JSONObject getCredentials() {
		return credentials;
	}

	public void setCredentials(JSONObject credentials) {
		this.credentials = credentials;
	}

	public JSONObject getSettings() {
		return settings;
	}

	public void setSettings(JSONObject settings) {
		this.settings = settings;
	}

	public String getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(String created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	public String getClosing() {
		return closing;
	}

	public void setClosing(String closing) {
		this.closing = closing;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@ApiModelProperty(dataType="String", example="2022-12-31 20:32:50")
	private String closing;
	
	@ApiModelProperty(dataType="String", name="location")
	private String location;
}
