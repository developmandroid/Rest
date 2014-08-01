package com.youtube.rest.dao;

import java.sql.Connection;

import javax.naming.*;
import javax.sql.*;

public class Postgre {

	private static DataSource postgre = null; //hold the database object
	private static Context context = null; //used to lookup the database connection in weblogic

	/**
	 * This is a public method that will return the 308tube database connection.
	 * 
	 * On Episode 5, I discussed that this method should not be private instead of public.
	 * This will make sure all sql/database relate code be place in the dao package.
	 * I am not doing this because I do not want to break the previous code... since this
	 * is just a tutorial project.
	 * 
	 * Pre-episode 6, updated this to a private scope, as it should be. That means, V1_inventory
	 * and V1_status methods needs to be updated.
	 * 
	 * @return Database object
	 * @throws Exception
	 */
	public static DataSource postgreConn() throws Exception {

		/**
		 * check to see if the database object is already defined...
		 * if it is, then return the connection, no need to look it up again.
		 */
		if (postgre != null) {
			return postgre;
		}

		try {

			/**
			 * This only needs to run one time to get the database object.
			 * context is used to lookup the database object in weblogic
			 * Oracle308tube will hold the database object
			 */
			if (context == null) {
				context = new InitialContext();
			}

			postgre = (DataSource) context.lookup("java:comp/env/jdbc/rest");

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return postgre;

	}

	/**
	 * This method will return the connection to the Oracle 308tube schema
	 * Note that the scope is protected which means only java class in the
	 * dao package can use this method.
	 * 
	 * @return Connection to 308tube Oracle database.
	 */
	protected static Connection oraclePcPartsConnection() {
		Connection conn = null;
		try {
			conn = postgreConn().getConnection();
			return conn;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
