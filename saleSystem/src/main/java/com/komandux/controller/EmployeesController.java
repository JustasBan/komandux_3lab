//package com.komandux.controller;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.komandux.Tables;
//import com.komandux.model.Employee;
//import com.komandux.model.Shift;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//public class EmployeesController {
//
//	@ApiModel
//	private static class EmployeeDTO {
//
//		@ApiModelProperty(dataType = "int")
//		public int user_id;
//		@ApiModelProperty(dataType = "JSONObject", example = "{\"access1\": true}")
//		public String access;
//
//		public EmployeeDTO(int user_id, String access) {
//			super();
//			this.user_id = user_id;
//			this.access = access;
//		}
//	}
//
//	@ApiModel
//	private static class EmployeeResponseDTO extends EmployeeDTO {
//
//		public EmployeeResponseDTO(int user_id, String access, int org_id) {
//			super(user_id, access);
//			this.org_id = org_id;
//		}
//
//		@ApiModelProperty(dataType = "int")
//		public int org_id;
//	}
//
//	// Add Employee endpoint
//	@ApiOperation(value = "Add Employee", response = EmployeeResponseDTO.class, tags = "Employee")
//	@PostMapping(value = "/organizations/{organization_id}/employees/")
//	public ResponseEntity<?> createEmployee(@PathVariable(value = "organization_id") int organization_id,
//			@RequestBody EmployeeDTO employeeDTO) {
//
//		try {
//			JSONParser parser = new JSONParser();
//			parser.parse(employeeDTO.access);
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//
//		String sql = "INSERT INTO employee_organizations(user_id, org_id, access) VALUES ("
//				+ Integer.toString(employeeDTO.user_id) + "," + Integer.toString(organization_id) + "," + "\'"
//				+ employeeDTO.access + "\');";
//
//		System.out.println(sql);
//
//		try {
//			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
//			Statement statement;
//			statement = connection.createStatement();
//			statement.executeUpdate(sql);
//
//			connection.close();
//			statement.close();
//			return new ResponseEntity<EmployeeResponseDTO>(
//					new EmployeeResponseDTO(employeeDTO.user_id, employeeDTO.access, organization_id), HttpStatus.OK);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	// getEmployee endpoint
//	@ApiOperation(value = "Get Employee", response = Employee.class, tags = "Employee")
//	@GetMapping(value = "/organizations/{organization_id}/employees/{employee_id}")
//	public ResponseEntity<?> getEmployee(@PathVariable(value = "organization_id") int organization_id,
//			@PathVariable(value = "employee_id") int employee_id) throws ParseException {
//
//		String sql = "SELECT user_id, org_id, access, password_hash, email, full_name, created_timestamp, phone_number FROM users INNER JOIN employee_organizations ON users.id = employee_organizations.user_id WHERE users.id ="
//				+ employee_id + " AND employee_organizations.org_id =" + organization_id + ";";
//		try {
//			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
//			Statement statement;
//			statement = connection.createStatement();
//			ResultSet rs = statement.executeQuery(sql);
//
//			while (rs.next()) {
//				JSONParser parser = new JSONParser();
//				java.util.Date newDate = rs.getTimestamp("created_timestamp");
//				Employee emp = new Employee(rs.getInt("user_id"), (JSONObject) parser.parse(rs.getString("access")), rs.getString("password_hash"),
//						rs.getString("email"), rs.getString("full_name"), rs.getString("created_date"), rs.getString("phone_number"));
//
//				connection.close();
//				rs.close();
//				statement.close();
//
//				return new ResponseEntity<Employee>(emp, HttpStatus.OK);
//			}
//
//			connection.close();
//			rs.close();
//			statement.close();
//
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	// putEmployee endpoint
//	@ApiOperation(value = "Update Employee", response = Employee.class, tags = "Employee")
//	@PutMapping(value = "/organizations/{organization_id}/employees/{employee_id}")
//	public ResponseEntity<?> putEmployee(@PathVariable(value = "organization_id") int organization_id,
//			@PathVariable(value = "employee_id") int employee_id, @RequestBody Employee employeeDTO)
//			throws ParseException {
//
////		String sql = "UPDATE users SET " + "password_hash = '" + employeeDTO.getPassword() + "'," + "email ='"
//				+ employeeDTO.getEmail() + "'," + "full_name ='" + employeeDTO.getFull_name() + "'," + "phone_number ='"
//				+ employeeDTO.getPhonenumber() + "' " + "WHERE id= " + employee_id + ";";
//
//		String sql2 = "UPDATE employee_organizations SET " + "user_id =" + employeeDTO.getUserId() + "," + "org_id ="
//				+ employeeDTO.getOrg_id() + "," + "access ='" + employeeDTO.getAccess() + "' " + "WHERE user_id= "
//				+ employee_id + " AND org_id=" + organization_id + ";";
//		;
//		try {
//			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
//			Statement statement;
//
//			statement = connection.createStatement();
//			statement.executeUpdate(sql + sql2);
//
//			statement.close();
//
//			return new ResponseEntity<Employee>(employeeDTO, HttpStatus.OK);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	// deleteEmployee endpoint
//	@ApiOperation(value = "delete Employee", tags = "Employee")
//	@DeleteMapping(value = "/organizations/{organization_id}/employees/{employee_id}")
//	public ResponseEntity<?> deletetEmployee(@PathVariable(value = "organization_id") int organization_id,
//			@PathVariable(value = "employee_id") int employee_id) throws ParseException {
//
//		String sql = "DELETE FROM employee_organizations " + "WHERE user_id= " + employee_id + " AND org_id="
//				+ organization_id + ";";
//		try {
//			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
//			Statement statement;
//
//			statement = connection.createStatement();
//			statement.executeUpdate(sql);
//
//			statement.close();
//
//			return new ResponseEntity<>(HttpStatus.OK);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	// Add shift endpoint
//	@ApiOperation(value = "Add shift", response = Shift.class, tags = "Employee")
//	@PostMapping(value = "/organizations/{organization_id}/employees/{employee_id}/shifts")
//	public ResponseEntity<?> createShift(@PathVariable(value = "organization_id") int organization_id, @PathVariable(value = "employee_id") int employee_id, @RequestBody Shift shiftDTO) {
//
//		String sql = "INSERT INTO shifts (emp_org_id, start_time, end_time, created_timestamp) VALUES (?,?,?,?)";
//
//		System.out.println(sql);
//
//		try {
//			
//			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
//			PreparedStatement statement;
//			statement = connection.prepareStatement(sql);
//			statement.setInt(1, employee_id);
//			Timestamp ts = Timestamp.valueOf(shiftDTO.getStart_time());
//			statement.setTimestamp(2, ts);
//			ts = Timestamp.valueOf(shiftDTO.getEnd_time());
//			statement.setTimestamp(3, ts);
//			statement.setInt(4, shiftDTO.getCreated_timestamp());
//			
//			statement.executeUpdate();
//
//			connection.close();
//			statement.close();
//			return new ResponseEntity<Shift>(shiftDTO, HttpStatus.OK);
//		} catch (SQLException e) {
//			e.printStackTrace();
//
//			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
//		}
//	}
//
//	// didnt add update shift endpoint, since its unclear what it should update from API documentation
//}
