package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.Employee;
import com.komandux.model.Order;
import com.komandux.model.OrderedService;
import com.komandux.model.Organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;

@RestController
public class OrganizationsController {

	// Add organization (location, bank, time merged into one)
	@ApiOperation(value = "add organization", response = Organization.class, tags = "Organization")
	@PostMapping(value = "/organizations/{organization_id}/addOrganization")
	public ResponseEntity<?> updateReservation(@PathVariable(value = "organization_id") int organization_id,
			@RequestBody Organization organizationDTO) throws ParseException {

		String sql = "INSERT INTO organizations (name, credentials, settings, created_timestamp, opening, closing, location) VALUES (?,?,?,?,?,?,?)";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);

			statement.setString(1, organizationDTO.getName());
			statement.setString(2, organizationDTO.getCredentials().toJSONString());
			statement.setString(3, organizationDTO.getSettings().toJSONString());
			Timestamp ts = Timestamp.valueOf(organizationDTO.getCreated_timestamp());
			statement.setTimestamp(4, ts);
			ts = Timestamp.valueOf(organizationDTO.getOpening());
			statement.setTimestamp(5, ts);
			ts = Timestamp.valueOf(organizationDTO.getClosing());
			statement.setTimestamp(6, ts);
			statement.setString(7, organizationDTO.getLocation());

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<Organization>(organizationDTO, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// Delete organization
	@ApiOperation(value = "add organization", tags = "Organization")
	@DeleteMapping(value = "/organizations/{organization_id}/deleteOrganization")
	public ResponseEntity<?> deleteReservation(@PathVariable(value = "organization_id") int organization_id)
			throws ParseException {

		String sql = "DELETE FROM organizations WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);

			statement.setInt(1, organization_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// get order history endpoint
	@ApiOperation(value = "Get order history", response = ArrayList.class, tags = "Organization")
	@GetMapping(value = "/organizations/{organization_id}/order_history")
	public ResponseEntity<?> getOrderHistory(@PathVariable(value = "organization_id") int organization_id)
			throws ParseException {

		String sql = "SELECT orders.id, orders.cust_id, tracking_code, status, note, requested_timestamp, estimated_timestamp FROM orders, ordered_services, services, organizations WHERE organizations.id=services.org_id AND services.id=ordered_services.service_id AND ordered_services.order_id=orders.id AND organizations.id=?";
		try {

			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, organization_id);
			ResultSet rs = statement.executeQuery();

			ArrayList<Order> rows = new ArrayList<Order>();
			while (rs.next()) {
				rows.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getTimestamp(6).toString(), rs.getTimestamp(7).toString()));
			}

			return new ResponseEntity<ArrayList<Order>>(rows, HttpStatus.OK);

		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// get Employee Order history endpoint
	@ApiOperation(value = "Get Employee Order history", response = ArrayList.class, tags = "Organization")
	@GetMapping(value = "/organizations/{organization_id}/order_history/{employee_id}")
	public ResponseEntity<?> getEmployeeOrderHistory(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "employee_id") int employee_id)
			throws ParseException {

		String sql = "SELECT orders.id, orders.cust_id, tracking_code, status, note, requested_timestamp, estimated_timestamp FROM employee_organizations, orders, ordered_services, services, organizations WHERE employee_organizations.org_id=organizations.id AND organizations.id=services.org_id AND services.id=ordered_services.service_id AND ordered_services.order_id=orders.id AND organizations.id=? AND employee_organizations.id=?";
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, organization_id);
			statement.setInt(2, employee_id);
			ResultSet rs = statement.executeQuery();

			ArrayList<Order> rows = new ArrayList<Order>();
			while (rs.next()) {
				rows.add(new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getTimestamp(6).toString(), rs.getTimestamp(7).toString()));
			}

			return new ResponseEntity<ArrayList<Order>>(rows, HttpStatus.OK);

		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
