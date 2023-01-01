package com.komandux.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

		public CouponsResponseDTO(int organization_id, Coupon coupon) {
			super(organization_id, coupon);
		}
	}

    // Add coupon
	@ApiOperation(value = "Add Coupon", response = Coupon.class, tags = "Coupons")
	@PostMapping(value = "/organizations/{organization_id}/coupons")
	public ResponseEntity<?> createCoupon(@PathVariable(value = "organization_id") int organization_id,
		@RequestBody Coupon coupon) {

		String sql = "INSERT INTO coupons (cust_id, code, created_timestamp, valid_until) VALUES ("
		+ coupon.getCust_id() + ",'" + coupon.getCode() + "','" + coupon.getCreated_timestamp() + "','" + coupon.getValid_unit() + "');";

		System.out.println(sql);

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);

			connection.close();
			statement.close();
			return new ResponseEntity<CouponsResponseDTO>(
					new CouponsResponseDTO(organization_id, coupon), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// putEmployee endpoint, kartu su assign
	@ApiOperation(value = "Update Coupon", response = Coupon.class, tags = "Coupons")
	@PutMapping(value = "/organizations/{organization_id}/coupons/{coupon_id}")
	public ResponseEntity<?> putEmployee(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "coupon_id") int coupon_id, @RequestBody Coupon coupon)
			throws ParseException {

		String sql = "UPDATE coupons SET " + "cust_id = \'" + coupon.getCust_id() + "'," + "code ='"
				+ coupon.getCode() + "'," + "created_timestamp ='" + coupon.getCreated_timestamp() + "'," + "valid_until ='"
				+ coupon.getValid_unit() + "' " + "WHERE id= " + coupon_id + ";";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;

			statement = connection.createStatement();
			statement.executeUpdate(sql);

			statement.close();

			return new ResponseEntity<CouponsResponseDTO>(
					new CouponsResponseDTO(organization_id, coupon), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}


}
