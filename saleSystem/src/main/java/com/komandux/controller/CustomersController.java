package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.Customer;
import io.swagger.annotations.ApiOperation;

@RestController
public class CustomersController {
	
	@ApiOperation(value = "add Customer", tags = "Customers")
	@PostMapping(value = "/customers/")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {

		try {
			
			String sql = "INSERT INTO users(password_hash, email, full_name, created_date, phone_number) VALUES ('"
					+ customer.getPassword() + "','" + customer.getEmail() + "','" + customer.getFull_name() + "','" +  customer.getCreated_date() +
					"','" + customer.getPhonenumber() + "');";
			
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());		
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			
			String sqlrez = "SELECT id FROM users WHERE password_hash = '" + customer.getPassword() + "' AND email = '" + customer.getEmail() 
			+ "' AND full_name = '" + customer.getFull_name() + "' AND phone_number = '" + customer.getPhonenumber() + "';";
			Statement statementrez;
			statementrez = connection.createStatement();
			ResultSet result = statementrez.executeQuery(sqlrez);
			
			String sql1 = "INSERT INTO customers(user_id, birth_date) VALUES (" + result.getInt(1) + ", '" + customer.getBirth_date() + "');";
			Statement statement1;
			statement1 = connection.createStatement();
			statement1.executeUpdate(sql1);
			result.close();
			statement1.close();
			connection.close();
			
			
			return new ResponseEntity<Customer>(
					new Customer(customer.getBirth_date() , customer.getPassword(), customer.getEmail(), customer.getFull_name(), customer.getCreated_date(), customer.getPhonenumber()), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	
	@ApiOperation(value = "get Customer", tags = "Customers")
	@GetMapping(value = "/customers/{email, password_hash}")
	public ResponseEntity<?> getCustomer(String email, String password_hash) {

		try {
			
			String sql = "SELECT * FROM users AS U JOIN customers AS C ON U.id = C.user_id WHERE U.email = '" + email + "' AND password_hash = '" + password_hash + "';";
			
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());		
			Statement statement;
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			Customer customerRez = new Customer(result.getString("birth_date"), result.getString("password_hash"), result.getString("email"), result.getString("full_name"), result.getString("created_date"), result.getString("phone_number"));
			connection.close();
			statement.close();
			result.close();
			
			if(customerRez.getEmail() == null && customerRez.getPassword() == null)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<Customer>(
					customerRez, HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@ApiOperation(value = "get Customer ID", tags = "Customers")
	@GetMapping(value = "/customers/{email, password_hash}/id")
	public ResponseEntity<?> getCustomerId(String email, String password_hash) {

		try {
			
			String sql = "SELECT C.id FROM users AS U JOIN customers AS C ON U.id = C.user_id WHERE U.email = '" + email + "' AND password_hash = '" + password_hash + "';";
			
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());		
			Statement statement;
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			int id = result.getInt(1);
			connection.close();
			statement.close();
			result.close();
			
			if(id == 0)
			{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<String>(
					Integer.toString(id), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@ApiOperation(value = "update Customer", tags = "Customers")
	@PutMapping(value = "/customers/{email, password_hash}")
	public ResponseEntity<?> updateCustomer(String email, String password_hash, @RequestBody Customer customer) {

		try {
			
			String sql = "UPDATE users SET password_hash = '" + customer.getPassword() + "', email = '" + customer.getEmail()
			+ "', full_name = '" + customer.getFull_name() + "', phone_number = '" + customer.getPhonenumber() + "', birth_date = '" + customer.getBirth_date() 
					+ "' WHERE email = '" + email + "' AND password_hash = '" + password_hash + "';";
					
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());		
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
			
			return new ResponseEntity<Customer>(
					new Customer(customer.getBirth_date() , customer.getPassword(), customer.getEmail(), customer.getFull_name(), customer.getCreated_date(), customer.getPhonenumber()), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@ApiOperation(value = "delete Customer", tags = "Customers")
	@DeleteMapping(value = "/customers/{email, password_hash}")
	public ResponseEntity<?> deleteCustomer(String email, String password_hash) {

		try {
			
//			String sql = "UPDATE users SET password_hash = '" + "null" + "', email = '" + "null"
//			+ "', full_name = '" + "null" + "', phone_number = '" + "null" 
//					+ "' WHERE email = '" + email + "' AND password_hash = '" + password_hash + "';";
			
			String sql = "DELETE FROM users WHERE email = '" + email + "' AND password_hash = '" + password_hash + "';";
			
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());		
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
