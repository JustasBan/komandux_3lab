package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

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
import com.komandux.model.Discount;
import com.komandux.model.Order;
import com.komandux.model.Service;

import io.swagger.annotations.ApiOperation;

@RestController
public class ServicesController {

	// Add discount endpoint
	@ApiOperation(value = "Add discount", response = Discount.class, tags = "Services")
	@PostMapping(value = "/organizations/{organization_id}/services/{service_id}/discounts/{percentage_off}")
	public ResponseEntity<?> createEmployee(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "service_id") int service_id, int percentage) {

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			String sql_price = "SELECT price FROM services WHERE id = ? AND org_id = ?;";
			int price = 0;
			PreparedStatement p;
			p = connection.prepareStatement(sql_price);
			p.setInt(1, service_id);
			p.setInt(2, organization_id);
			ResultSet rs = p.executeQuery();

			while (rs.next()) {
				price = rs.getInt("price");
			}

			p.close();
			rs.close();
			connection.close();

			System.out.println(price);
			String sql = "INSERT INTO discounts (service_id, percentage_off, exact_price, created_timestamp) "
					+ "VALUES (?,?,?,?);";
			connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, service_id);
			statement.setInt(2, percentage);
			statement.setInt(3, price);
			statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			statement.executeUpdate();

			connection.close();
			statement.close();
			return new ResponseEntity<Discount>(
					new Discount(service_id, percentage, price, LocalDateTime.now().toString()), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// Add service endpoint
	@ApiOperation(value = "Add service", response = Service.class, tags = "Services")
	@PostMapping(value = "/organizations/{organization_id}/services/")
	public ResponseEntity<?> addService(@PathVariable(value = "organization_id") int organization_id,
			@RequestBody Service service) {

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			String sql = "INSERT INTO services (org_id, price, description, created_timestamp, available, loyalty_point_reward) VALUES (?,?,?,?,?,?)";

			PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setInt(1, organization_id);
			p.setInt(2, service.getPrice());
			p.setString(3, service.getDescription());
			p.setTimestamp(4, Timestamp.valueOf(service.getCreated_timestamp()));
			p.setBoolean(5, service.getAvailable());
			p.setInt(6, service.getLoyalty_point_reward());

			p.executeUpdate();

			return new ResponseEntity<Service>(service, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// delete service endpoint
	@ApiOperation(value = "Delete service", tags = "Services")
	@DeleteMapping(value = "/organizations/{organization_id}/services/{service_id}/")
	public ResponseEntity<?> deleteService(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "service_id") int service_id) {

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			String sql = "DELETE FROM services WHERE id = ? AND org_id = ?;";

			PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setInt(1, service_id);
			p.setInt(2, organization_id);

			p.executeUpdate();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// get service endpoint
	@ApiOperation(value = "Search service", response = ArrayList.class, tags = "Services")
	@GetMapping(value = "/organizations/{organization_id}/services/search/")
	public ResponseEntity<?> getService(@PathVariable(value = "organization_id") int organization_id) {

		try {

			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			String sql = "SELECT id, org_id, price, description, created_timestamp, available, loyalty_point_reward FROM services WHERE id = ?;";

			PreparedStatement p;
			p = connection.prepareStatement(sql);
			p.setInt(1, organization_id);
			ResultSet rs = p.executeQuery();
			ArrayList<Service> rows = new ArrayList<Service>();
			while (rs.next()) {
				rows.add(new Service(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
						rs.getTimestamp(5).toString(), rs.getBoolean(6), rs.getInt(7)));
			}
			return new ResponseEntity<ArrayList<Service>>(rows, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// Set Loyalty Point Reward endpoint
	@ApiOperation(value = "Set Loyalty Point Reward", response = Service.class, tags = "Services")
	@PutMapping(value = "/organizations/{organization_id}/services/{service_id}/setLoyaltyPointReward")
	public ResponseEntity<?> setLoyalty(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "service_id") int service_id, int reward) {

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			String sql = "UPDATE services SET loyalty_point_reward = ? WHERE id =? AND org_id =?;";

			PreparedStatement p;
			p = connection.prepareStatement(sql);

			p.setInt(1, reward);
			p.setInt(2, service_id);
			p.setInt(3, organization_id);

			p.executeUpdate();

			sql = "SELECT id, org_id, price, description, created_timestamp, available, loyalty_point_reward FROM services WHERE org_id = ? AND id = ?;";
			p = connection.prepareStatement(sql);
			p.setInt(1, organization_id);
			p.setInt(2, service_id);
			ResultSet rs = p.executeQuery();

			return new ResponseEntity<Service>(new Service(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4),
					rs.getTimestamp(5).toString(), rs.getBoolean(6), rs.getInt(7)), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
