package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.OrderedService;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
public class ReservationsController {

	// update reservation endpoint
	@ApiOperation(value = "Update reservation", response = OrderedService.class, tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}")
	public ResponseEntity<?> updateReservation(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id, @RequestBody OrderedService orderedServiceDTO)
			throws ParseException {

		String sql = "UPDATE ordered_services SET estimated_finish_time = ?, paid = ? WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);

			Timestamp ts = Timestamp.valueOf(orderedServiceDTO.getEstimated_finish_time());
			statement.setTimestamp(1, ts);

			statement.setBoolean(2, orderedServiceDTO.isPaid());
			statement.setInt(3, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<OrderedService>(orderedServiceDTO, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// handle reservation endpoint
	@ApiOperation(value = "handle reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/handle")
	public ResponseEntity<?> handleReservation(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id) throws ParseException {

		String sql = "UPDATE ordered_services SET paid = TRUE WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// add note endpoint
	@ApiOperation(value = "add note reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/addNote")
	public ResponseEntity<?> addNote(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id, @RequestBody String note)
			throws ParseException {

		String sql = "UPDATE orders SET note = ? WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);

			statement.setString(1, note);
			statement.setInt(2, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@ApiModel
	private static class EstimateDTO {
		@ApiModelProperty(dataType = "LocalTime", example = "2022-12-31 20:32:50")
		public String estimate;
	}

	// set estimate endpoint
	@ApiOperation(value = "set estimate reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/setEstimate")
	public ResponseEntity<?> setEstimate(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id, @RequestBody EstimateDTO estimateDTO)
			throws ParseException {

		String sql = "UPDATE ordered_services SET estimated_finish_time = ? WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			Timestamp ts = Timestamp.valueOf(estimateDTO.estimate);
			statement.setTimestamp(1, ts);
			statement.setInt(2, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// set status endpoint
	@ApiOperation(value = "set status reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/setStatus")
	public ResponseEntity<?> setStatus(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id, @RequestBody int statusDTO)
			throws ParseException {

		String sql = "UPDATE orders SET status = ? WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, statusDTO);
			statement.setInt(2, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	// pay cash endpoint
	@ApiOperation(value = "pay cash reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/payCash")
	public ResponseEntity<?> payCash(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id) throws ParseException {

		String sql = "UPDATE ordered_services SET paid = TRUE WHERE id = ?";

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, reservation_id);

			statement.executeUpdate();

			statement.close();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	// pay loyalty
	@ApiOperation(value = "pay loyalty reservation", tags = "Reservation")
	@PutMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/payLoyalty")
	public ResponseEntity<?> payLoyalty(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id) throws ParseException {

		String sql = "UPDATE ordered_services SET paid = TRUE WHERE id = ?";
		String sql2=" UPDATE loyalty_points SET amount = ? WHERE cust_id = ?;";
		
		int custId = 0, price=0, loyalty=0;
		String sql_price = "SELECT price, cust_id FROM services, ordered_services, orders WHERE ordered_services.service_id = services.id AND ordered_services.id = ? AND orders.id=ordered_services.order_id";
		String sql_loylty = "SELECT amount FROM loyalty_points WHERE cust_id = ?";
		
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql_price);
			statement.setInt(1, reservation_id);

			ResultSet rs =statement.executeQuery();
			while (rs.next()) {
				price = rs.getInt("price");
				custId = rs.getInt("cust_id");
			}
			
			statement = connection.prepareStatement(sql_loylty);
			statement.setInt(1, custId);

			rs =statement.executeQuery();
			while (rs.next()) {
				loyalty = rs.getInt("amount");
			}
			
			System.out.println(price + " " + loyalty + " " + custId);
			
			if(loyalty - price < 0) {
				return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
			}
			else {
				
				statement = connection.prepareStatement(sql);
				statement.setInt(1, reservation_id);
				statement.executeUpdate();
				
				statement = connection.prepareStatement(sql2);
				int amount = price - loyalty;
				statement.setInt(1, amount);
				statement.setInt(2, custId);
				statement.executeUpdate();

				return new ResponseEntity<>(HttpStatus.OK);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@ApiModel
	private static class ReceiptDTO {
		@ApiModelProperty(dataType = "int")
		public int price;
		@ApiModelProperty(dataType = "String")
		public String description;
		@ApiModelProperty(dataType = "LocalDate")
		public LocalDateTime created_timestamp;
		@ApiModelProperty(dataType = "String")
		public String note;
		@ApiModelProperty(dataType = "LocalDate")
		public LocalDateTime requested_timestamp;
		
		public ReceiptDTO(int price, String description, LocalDateTime created_timestamp, String note,
				LocalDateTime requested_timestamp) {
			super();
			this.price = price;
			this.description = description;
			this.created_timestamp = created_timestamp;
			this.note = note;
			this.requested_timestamp = requested_timestamp;
		}

		@Override
		public String toString() {
			return "ReceiptDTO [price=" + price + ", description=" + description + ", created_timestamp="
					+ created_timestamp + ", note=" + note + ", requested_timestamp=" + requested_timestamp + "]";
		}
		
		
	}
	
	// get receipt
	@ApiOperation(value = "get receipt", tags = "Reservation")
	@GetMapping(value = "/organizations/{organization_id}/reservations/{reservation_id}/receipt")
	public ResponseEntity<?> getReceipt(@PathVariable(value = "organization_id") int organization_id,
			@PathVariable(value = "reservation_id") int reservation_id) throws ParseException {

		String sql = "SELECT price, description, ordered_services.created_timestamp, note, requested_timestamp FROM services, ordered_services, orders WHERE ordered_services.service_id = services.id AND ordered_services.id = ? AND orders.id=ordered_services.order_id";
		
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			PreparedStatement statement;
			statement = connection.prepareStatement(sql);
			statement.setInt(1, reservation_id);

			ResultSet rs =statement.executeQuery();
			ReceiptDTO receipt = null;
			while (rs.next()) {
				
				receipt = new ReceiptDTO(rs.getInt("price"), rs.getString("description"), rs.getTimestamp("created_timestamp").toLocalDateTime(), rs.getString("note"),  rs.getTimestamp("requested_timestamp").toLocalDateTime());
			}
			return new ResponseEntity<ReceiptDTO>(receipt, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
