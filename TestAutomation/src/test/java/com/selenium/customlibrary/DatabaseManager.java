package com.selenium.customlibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {

	private String databaseServerName = "localhost";
	private String databasePort = "1521"; //data communicates thru port
	private String databaseName = "xe";
	private String userName = "hr";
	private String userPassword = "oracle";
	
	private String connectionURL = "jdbc:oracle:thin:hr@//" + databaseServerName + ":" + databasePort + "/" + databaseName;
	
	
	private ResultSet resultSet = null;
	private Statement statement = null;
	private Connection connection = null;
	
	
	//create a method
	private void connectToOracleDB() throws SQLException, ClassNotFoundException
	{
		
	    //Load the driver
		Class.forName("oracle.jdbc.OracleDriver");
		//create connection
		connection = DriverManager.getConnection(connectionURL, userName, userPassword);
		statement = connection.createStatement();
	}
	
	
	//This is only to run SQLQuery, you can include - delete, add, update queries.
	public ResultSet runSQLQuery(String sqlQuery)throws ClassNotFoundException, SQLException
	{
		connectToOracleDB();
		resultSet = statement.executeQuery(sqlQuery);
		return resultSet;
	}
	
	
	public static void main(String[] args)throws Exception, SQLException  {
		
	
	DatabaseManager db = new DatabaseManager();
	ResultSet data = db.runSQLQuery("select * from COUNTRIES");
	ResultSet data1 = db.runSQLQuery("select * from EMPLOYEES");
	//System.out.println("Data:"+ data);
	
	System.out.println("COUNTRY_ID" +  "\tCOUNTRY_NAME" + "\t\t" +  "REGION_ID");
	System.out.println("----------" +   "--------------------------" + "-------------------");
	
	
	while(data.next())
	{
		String countryID = data.getString("COUNTRY_ID");
		String countryName = data.getString("COUNTRY_NAME");
		int regionID = data.getInt("REGION_ID");
		System.out.println(countryID + "\t" + countryName + "\t\t\t\t" + regionID);  //t means tabbing gives space
	}
  
	System.out.println("----------" +   "--------------------------" + "-------------------");
	System.out.println("EMPLOYEE_ID" +  "\tFIRST_NAME" + "\t\t" + "LAST_NAME");
	System.out.println("----------" +   "--------------------------" + "-------------------");
	while(data1.next())
	{
		int employeeID = data1.getInt("EMPLOYEE_ID");
		String firstName = data1.getString("FIRST_NAME");
		String lastName = data1.getString("LAST_NAME");
		System.out.println(employeeID + "\t" + firstName + "\t\t\t\t" + lastName);  //t means tabbing gives space
	}
	
	}
	
	
}


