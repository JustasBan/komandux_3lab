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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.komandux.Tables;
import com.komandux.model.Transaction;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@RestController
public class TransactionController {

    @ApiModel
	private static class TransactionDTO {
        @ApiModelProperty(dataType = "int")
        public int organization_id;
        @ApiModelProperty(dataType = "JSONObject")
        public Transaction transaction;
        public TransactionDTO(int organization_id, Transaction transaction) {
            super();
            this.organization_id = organization_id;
            this.transaction = transaction;
        }
    }
    
    @ApiModel
	private static class TransactionsResponseDTO extends TransactionDTO {

    	@ApiModelProperty(dataType = "int")
		public int id;
    	
		public TransactionsResponseDTO(int organization_id, int id, Transaction transaction) {
			super(organization_id, transaction);
			this.id = id;
		}
	}

    // Get Transactions
	@ApiOperation(value = "Get Transactions", response = Transaction.class, tags = "Transactions")
	@GetMapping(value = "/organizations/{organization_id}/transactions")
	public ResponseEntity<?> getTransactions(@PathVariable(value = "organization_id") int organization_id) throws ParseException {

		String sql = "SELECT * FROM transactions AS T JOIN employee_organizations AS E ON T.emp_org_id = E.id  WHERE E.org_id =" + organization_id + ";";
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			
			List<TransactionsResponseDTO> list = new ArrayList<TransactionsResponseDTO>();
			
			while(rs.next())
			{
				Transaction tran = new Transaction(rs.getInt("emp_org_id"), rs.getInt("cust_id"), rs.getInt("order_id"), rs.getInt("amount"), null, rs.getTimestamp("created_timestamp").toString());
				int id = rs.getInt("id");
				list.add(new TransactionsResponseDTO(organization_id, id, tran));
			}	

			connection.close();
			rs.close();
			statement.close();

			return new ResponseEntity<List<TransactionsResponseDTO>>(HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

    // Add Transaction
    @ApiOperation(value = "add Transaction", response = Transaction.class, tags = "Transactions")
	@PostMapping(value = "/organizations/{organization_id}/transactions")
	public ResponseEntity<?> createTransaction(@PathVariable(value = "organization_id") int organization_id,
		@RequestBody Transaction transaction) {

    	Timestamp ts1 = Timestamp.valueOf(transaction.getCreated_timestamp());
    	
		String sql = "INSERT INTO transactions (emp_org_id, cust_id, order_id, amount, type, created_timestamp) VALUES ("
		+ transaction.getEmp_org_id() + ",'" + transaction.getCust_id() + "','" + transaction.getOrder_id() + "','" + transaction.getAmount() + "','"  + transaction.getType() + "','" + ts1 + "'" + ");";

		System.out.println(sql);

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			
			String sql1 = "SELECT id FROM transactions WHERE cust_id = " + transaction.getCust_id() + " AND emp_org_id = '" + transaction.getEmp_org_id() + "' AND order_id = '" + transaction.getOrder_id() + "';";
			Statement statement1;
			statement1 = connection.createStatement();
			ResultSet result = statement1.executeQuery(sql1);
			int id = result.getInt(1);

			connection.close();
			statement.close();
			return new ResponseEntity<TransactionsResponseDTO>(
					new TransactionsResponseDTO(organization_id, id, transaction), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
