package com.komandux;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {
	static String jdbcUrl = "jdbc:sqlite:sale.db";

	public static void createAllTables() throws SQLException {
		Connection connection = DriverManager.getConnection(getJdbcUrl());
		createTableUsers(connection);
		createTableInvoices(connection);
		createTableTransactions(connection);
		createTableCustomers(connection);
		createTableOrganizations(connection);
		createTableServices(connection);
		createTableDiscounts(connection);
		createTableLoyaltyDeals(connection);
		createTableRatings(connection);
		createTableLoyaltyPoints(connection);
		createTableOrders(connection);
		createTableOrderedServices(connection);
		createTableEmployeeOrganizations(connection);
		createTableShifts(connection);
		createTableActivityLogs(connection);
		createTableCoupons(connection);
		createTableCouponsServices(connection);
	}

	public static void createTableUsers(Connection connection) {
		String sql = "create table if not exists users (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  password_hash     CHAR(20)    NOT NULL,\r\n"
				+ "  email             CHAR(50)    NOT NULL,\r\n"
				+ "  full_name         CHAR(20)    NOT NULL,\r\n"
				+ "  created_date      CHAR(12)    NOT NULL,\r\n"
				+ "  phone_number      CHAR(12)    NOT NULL,\r\n"
				+ "  created_timestamp TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableInvoices(Connection connection) {
		String sql = "create table if not exists invoices (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  transaction_id    INTEGER     NOT NULL,\r\n"
				+ "  invoice_number    INTEGER     NOT NULL,\r\n"
				+ "  created_timestamp TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "  CONSTRAINT  FKtransaction_id  FOREIGN KEY(transaction_id)   REFERENCES transactions(id) ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableTransactions(Connection connection) {
		String sql = "create table if not exists transactions (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  emp_org_id        INTEGER     NOT NULL,\r\n"
				+ "  cust_id           INTEGER     NOT NULL,\r\n"
				+ "  order_id          INTEGER     NOT NULL,\r\n"
				+ "  amount            INTEGER     NOT NULL,\r\n"
				+ "  type              TEXT,\r\n"
				+ "  created_timestamp TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "  CONSTRAINT  FKemp_org_id  FOREIGN KEY(emp_org_id)   REFERENCES employee_organizations(id) ON DELETE NO ACTION,\r\n"
				+ "  CONSTRAINT  FKcust_id     FOREIGN KEY(cust_id)      REFERENCES customers(id)    ON DELETE NO ACTION,\r\n"
				+ "  CONSTRAINT  FKorder_id    FOREIGN KEY(order_id)     REFERENCES orders(id)       ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableCustomers(Connection connection) {
		String sql = "create table if not exists customers (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT, \r\n"
				+ "  user_id           INTEGER     NOT NULL,\r\n"
				+ "  birth_date        CHAR(20)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableLoyaltyPoints(Connection connection) {
		String sql = "create table if not exists loyalty_points (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT, \r\n"
				+ "  cust_id           INTEGER     NOT NULL,\r\n"
				+ "  amount            INTEGER     NOT NULL,\r\n"
				+ "  created_timestamp TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "  CONSTRAINT  FKcust_id  FOREIGN KEY(cust_id) REFERENCES customers(id) ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableOrders(Connection connection) {
		String sql = "create table if not exists orders (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT, \r\n"
				+ "  cust_id           INTEGER     NOT NULL,\r\n"
				+ "  tracking_code     INTEGER     NOT NULL,\r\n"
				+ "  status            INTEGER     NOT NULL,\r\n"
				+ "  note              TEXT,\r\n"
				+ "  requested_timestamp TIMESTAMP,\r\n"
				+ "  estimated_timestamp TIMESTAMP,\r\n"
				+ "  CONSTRAINT  FKcust_id  FOREIGN KEY(cust_id) REFERENCES customers(id) ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableEmployeeOrganizations(Connection connection) {
		String sql = "create table if not exists employee_organizations (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  user_id           INTEGER     NOT NULL,\r\n"
				+ "  org_id            INTEGER     NOT NULL,\r\n"
				+ "  access            TEXT,\r\n"
				+ "  CONSTRAINT  FKuser_id  FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE NO ACTION,\r\n"
				+ "  CONSTRAINT  FKorg_id   FOREIGN KEY(org_id) REFERENCES organizations(id) ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableActivityLogs(Connection connection) {
		String sql = "create table if not exists activity_logs (\r\n"
				+ "  id                INTEGER     PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "  activity_info     TEXT        NOT NULL,\r\n"
				+ "  emp_org_id        INTEGER     NOT NULL,\r\n"
				+ "  CONSTRAINT  FKemp_org_id FOREIGN KEY(emp_org_id) REFERENCES employee_organizations(id) ON DELETE NO ACTION\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableOrganizations(Connection connection) {
		String sql = "create table if not exists organizations (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "name 				TEXT,\r\n"
				+ "credentials 			JSON,\r\n"
				+ "settings 			JSON,\r\n"
				+ "created_timestamp 	INTEGER,\r\n"
				+ "opening 				TIMESTAMP,\r\n"
				+ "closing 				TIMESTAMP\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableServices(Connection connection) {
		String sql = "create table if not exists services (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "org_id 				INTEGER,\r\n"
				+ "price 				INTEGER,\r\n"
				+ "description 			TEXT,\r\n"
				+ "created_timestamp 	INTEGER,\r\n"
				+ "available 			BOOLEAN,\r\n"
				+ "closing 				TIMESTAMP,\r\n"
				+ "FOREIGN KEY (org_id) REFERENCES organizations(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableDiscounts(Connection connection) {
		String sql = "create table if not exists discounts (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "service_id 			INTEGER,\r\n"
				+ "percentage_off 		INTEGER,\r\n"
				+ "exact_price 			INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (service_id) REFERENCES services(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableLoyaltyDeals(Connection connection) {
		String sql = "create table if not exists loyalty_deals (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "service_id 			INTEGER,\r\n"
				+ "price 				INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (service_id) REFERENCES services(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableCouponsServices(Connection connection) {
		String sql = "create table if not exists coupons_services (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "service_id 			INTEGER,\r\n"
				+ "coupon_id 			INTEGER,\r\n"
				+ "percentage_off 		INTEGER,\r\n"
				+ "exact_price	 		INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (service_id) REFERENCES services(id),\r\n"
				+ "FOREIGN KEY (coupon_id) REFERENCES coupons(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableRatings(Connection connection) {
		String sql = "create table if not exists ratings (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "service_id 			INTEGER,\r\n"
				+ "customer_id 			INTEGER,\r\n"
				+ "rating		 		INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (service_id) REFERENCES services(id),\r\n"
				+ "FOREIGN KEY (customer_id) REFERENCES customers(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableCoupons(Connection connection) {
		String sql = "create table if not exists coupons (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "cust_id 				INTEGER,\r\n"
				+ "code		 			INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "valid_until		 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (cust_id) REFERENCES customers(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableOrderedServices(Connection connection) {
		String sql = "create table if not exists ordered_services (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "order_id				INTEGER,\r\n"
				+ "service_id 			INTEGER,\r\n"
				+ "created_timestamp 	TIMESTAMP,\r\n"
				+ "FOREIGN KEY (order_id) REFERENCES orders(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void createTableShifts(Connection connection) {
		String sql = "create table if not exists shifts (\r\n"
				+ "id 					INTEGER PRIMARY KEY AUTOINCREMENT,\r\n"
				+ "emp_org_id			INTEGER,\r\n"
				+ "created_timestamp 	INTEGER,\r\n"
				+ "start_time 			TIMESTAMP,\r\n"
				+ "end_time 			TIMESTAMP,\r\n"
				+ "FOREIGN KEY (emp_org_id) REFERENCES employee_organizations(id)\r\n"
				+ ");";

		Statement statement;
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getJdbcUrl() {
		return jdbcUrl;
	}
}
