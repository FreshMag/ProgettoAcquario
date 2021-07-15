package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSource {
    
	private String dbName = "acquarium";

    public Connection getMySQLConnection()  {
    	
       String driver = "com.mysql.jdbc.Driver";
       String dbUri = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC" + "&useSSL=false";
   	   String userName = "root";
   	   String password = "LoriSQL26";
        
       Connection connection = null;
        try {
            System.out.println("DataSource.getConnection() driver = " + driver);
            //Class.forName(driver);
            System.out.println("DataSource.getConnection() dbUri = " + dbUri);
            connection = DriverManager.getConnection(dbUri, userName, password);
        }
        /*catch (ClassNotFoundException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }*/
        catch(SQLException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }
        return connection;
    }
}
