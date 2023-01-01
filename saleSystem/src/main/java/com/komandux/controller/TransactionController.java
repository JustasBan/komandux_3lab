package com.komandux.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

import org.apache.tomcat.util.json.JSONParser;
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
import com.komandux.model.Transaction;
import com.komandux.model.TransactionType;

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

		public TransactionsResponseDTO(int organization_id, Transaction transaction) {
			super(organization_id, transaction);
		}
	}

    // Get Transactions
	@ApiOperation(value = "Get Transactions", response = Transaction.class, tags = "Transactions")
	@GetMapping(value = "/organizations/{organization_id}/transactions")
	public ResponseEntity<?> getTransactions(@PathVariable(value = "organization_id") int organization_id) throws ParseException {

		String sql = "SELECT emp_org_id, cust_id, order_id, amount, type, created_timestamp FROM transactions WHERE emp_org_id =" + organization_id + ";";
		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				java.util.Date newDate = rs.getTimestamp("created_timestamp");
				Transaction tran = new Transaction(rs.getInt("id"),rs.getInt("emp_org_id"), rs.getInt("cust_id"),
                        rs.getInt("order_id"), rs.getInt("amount"), null, newDate);

				connection.close();
				rs.close();
				statement.close();

				return new ResponseEntity<Transaction>(tran, HttpStatus.OK);
			}

			connection.close();
			rs.close();
			statement.close();

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

    // Add Transaction
    @ApiOperation(value = "Add Transaction", response = Transaction.class, tags = "Transactions")
	@PostMapping(value = "/organizations/{organization_id}/transactions")
	public ResponseEntity<?> createTransaction(@PathVariable(value = "organization_id") int organization_id,
		@RequestBody Transaction transaction) {

		String sql = "INSERT INTO transactions (emp_org_id, cust_id, order_id, amount, type, created_timestamp) VALUES ("
		+ transaction.getEmp_org_id() + ",'" + transaction.getCust_id() + "','" + transaction.getOrder_id() + "','" + transaction.getAmount() + transaction.getType() + transaction.getCreated_timestamp() + "','" + "','" + "');";

		System.out.println(sql);

		try {
			Connection connection = DriverManager.getConnection(Tables.getJdbcUrl());
			Statement statement;
			statement = connection.createStatement();
			statement.executeUpdate(sql);

			connection.close();
			statement.close();
			return new ResponseEntity<TransactionsResponseDTO>(
					new TransactionsResponseDTO(organization_id, transaction), HttpStatus.OK);
		} catch (SQLException e) {
			e.printStackTrace();

			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
