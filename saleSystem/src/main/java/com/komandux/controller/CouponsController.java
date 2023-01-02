package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.Coupon;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
public class CouponsController {

	@ApiModel
	private static class CouponsDTO {

		@ApiModelProperty(dataType = "int")
		public int organization_id;
		@ApiModelProperty(dataType = "JSONObject")
		public Coupon coupon;
		public CouponsDTO(int organization_id, Coupon coupon) {
			super();
			this.organization_id = organization_id;
			this.coupon = coupon;
		}
    }

	@ApiModel
	private static class CouponsResponseDTO extends CouponsDTO {
		@ApiModelProperty(dataType = "int")
		public int coupon_id;

		public CouponsResponseDTO(int organization_id, int coupon_id, Coupon coupon) {
			super(organization_id, coupon);
			this.coupon_id = coupon_id;
		}
	}

    // Post Coupon
	@ApiOperation(value = "add Coupon", response = Coupon.class, tags = "Coupons")
	@PostMapping(value = "/organizations/{organization_id}/coupons")
	public ResponseEntity<?> createCoupon(@PathVariable(value = "organization_id") int organization_id,
		@RequestBody Coupon coupon) {
		
		Timestamp ts1 = Timestamp.valueOf(coupon.getCreated_timestamp());
		Timestamp ts2 = Timestamp.valueOf(coupon.getValid_unit());

		String sql = "INSERT INTO coupons (cust_id, code, created_timestamp, valid_until) VALUES ("
		+ coupon.getCust_id() + ",'" + coupon.getCode() + "','" + ts1 + "','" + ts2 + "');";

		System.out.println(sql);

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			String sql1 = "SELECT id FROM coupons WHERE cust_id = " + coupon.getCust_id() + " AND code = '" + coupon.getCode() + "';";
			Statement statement1;
			statement1 = connection.createStatement();
			ResultSet result = statement1.executeQuery(sql1);
			int coupon_id = result.getInt(1);
			
			connection.close();
			statement.close();
			statement1.close();
			result.close();
			
			return new ResponseEntity<CouponsResponseDTO>(
					new CouponsResponseDTO(organization_id, coupon_id, coupon), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// Put Coupon, kartu su assign
	@ApiOperation(value = "update Coupon", response = Coupon.class, tags = "Coupons")
	@PutMapping(value = "/organizations/{organization_id}/coupons/{coupon_id}")
	public ResponseEntity<?> putCoupon(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "coupon_id") int coupon_id, @RequestBody Coupon coupon)
			throws ParseException {
		
		Timestamp ts1 = Timestamp.valueOf(coupon.getCreated_timestamp());
		Timestamp ts2 = Timestamp.valueOf(coupon.getValid_unit());

		String sql = "UPDATE coupons SET " + "cust_id = \'" + coupon.getCust_id() + "'," + "code ='"
				+ coupon.getCode() + "'," + "created_timestamp ='" + ts1 + "'," + "valid_until ='"
				+ ts2 + "' " + "WHERE id= " + coupon_id + ";";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;

			statement = connection.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			connection.close();

			return new ResponseEntity<CouponsResponseDTO>(
					new CouponsResponseDTO(organization_id, coupon_id, coupon), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@ApiOperation(value = "get Coupon", response = Coupon.class, tags = "Coupons")
	@GetMapping(value = "/organizations/{organization_id}/coupons/{coupon_id}")
	public ResponseEntity<?> getCoupon(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "coupon_id") int coupon_id)
	{

		String sql = "SELECT * FROM coupons WHERE id= " + coupon_id + ";";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;

			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Coupon coupon = new Coupon(result.getInt("cust_id"), result.getInt("code"), result.getTimestamp("created_timestamp").toString(), result.getTimestamp("valid_until").toString());

			statement.close();
			connection.close();
			result.close();

			return new ResponseEntity<CouponsResponseDTO>(
					new CouponsResponseDTO(organization_id, coupon_id, coupon), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@ApiOperation(value = "get all Coupons", response = Coupon.class, tags = "Coupons")
	@GetMapping(value = "/organizations/{organization_id}/coupons")
	public ResponseEntity<?> getCoupons(@PathVariable(value = "organization_id") int organization_id)
	{

		String sql = "SELECT * FROM coupons;";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;

			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			List<CouponsResponseDTO> list = new ArrayList();
			
			while(result.next())
			{
				Coupon coupon = new Coupon(result.getInt("cust_id"), result.getInt("code"), result.getTimestamp("created_timestamp").toString(), result.getTimestamp("valid_until").toString());
				int coupon_id = result.getInt("id");
				list.add(new CouponsResponseDTO(organization_id, coupon_id, coupon));
			}			

			statement.close();
			connection.close();
			result.close();

			return new ResponseEntity<List<CouponsResponseDTO>>(
					list, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
