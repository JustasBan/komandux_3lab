package com.komandux.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.Employee;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeesController {

	@ApiModel
	private static class EmployeeDTO {

		@ApiModelProperty(dataType="int")
		public int user_id;
		@ApiModelProperty(dataType="JSONObject", example ="{\"access1\": true}")
		public String access;
		
		public EmployeeDTO(int user_id, String access) {
			super();
			this.user_id = user_id;
			this.access = access;
		}
	}
	
	@ApiModel
	private static class EmployeeResponseDTO extends EmployeeDTO{

		public EmployeeResponseDTO(int user_id, String access, int org_id) {
			super(user_id, access);
			this.org_id = org_id;
		}

		@ApiModelProperty(dataType="int")
		public int org_id;
	}
	
	@ApiOperation(value = "Add Employee", response = Employee.class, tags = "Employee")
    @PostMapping(value = "/organizations/{organization_id}/employees/")
    public ResponseEntity<?> createEmployee(@PathVariable(value = "organization_id") int organization_id, @RequestBody EmployeeDTO employeeDTO) {
		Employee employee;
		
		try {
			employee = new Employee(employeeDTO.user_id, organization_id, employeeDTO.access);
		} catch (ParseException e1) {
			e1.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		String sql = "INSERT INTO employee_organizations(user_id, org_id, access) VALUES ("
				+ Integer.toString(employee.getUser_id()) + "," 
				+ Integer.toString(employee.getOrg_id()) + "," 
				+ "\'" + employee.getAccess().toJSONString() + "\');";
		
		System.out.println(sql);
		
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			return new ResponseEntity<EmployeeResponseDTO>(new EmployeeResponseDTO(employeeDTO.user_id, employeeDTO.access, organization_id), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}        
    }
}
