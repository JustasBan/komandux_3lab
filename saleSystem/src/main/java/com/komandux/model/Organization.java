package com.komandux.model;

import java.time.LocalTime;

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
	
	@ApiModelProperty(dataType="LocalTime", name="created timestamp")
	private LocalTime created_timestamp;
	
	@ApiModelProperty(dataType="int", name="opening")
	private int opening;
	
	public Organization(int id, String name, JSONObject credentials, JSONObject settings, LocalTime created_timestamp,
			int opening, int closing, String location) {
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

	public LocalTime getCreated_timestamp() {
		return created_timestamp;
	}

	public void setCreated_timestamp(LocalTime created_timestamp) {
		this.created_timestamp = created_timestamp;
	}

	public int getOpening() {
		return opening;
	}

	public void setOpening(int opening) {
		this.opening = opening;
	}

	public int getClosing() {
		return closing;
	}

	public void setClosing(int closing) {
		this.closing = closing;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@ApiModelProperty(dataType="int", name="closing")
	private int closing;
	
	@ApiModelProperty(dataType="String", name="location")
	private String location;
}
