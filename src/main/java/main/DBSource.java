package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBSource {
    
	private static String dbName = "acquarium";
    public static String userName;// = "root";
    public static String password; // = "LoriSQL26";
    public static String dbUri;

    public static Connection getMySQLConnection() throws SQLException {
    	
       String driver = "com.mysql.jdbc.Driver";
       //String dbUri = "jdbc:mysql://localhost:3306/" + dbName + "?serverTimezone=UTC" + "&useSSL=false";

        
       Connection connection = null;
        //try {
            System.out.println("DataSource.getConnection() driver = " + driver);
            //Class.forName(driver);
            System.out.println("DataSource.getConnection() dbUri = " + dbUri);
            connection = DriverManager.getConnection(dbUri, userName, password);
        //}
        /*catch (ClassNotFoundException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }*/
        /*catch(SQLException e) {
            new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }*/
        return connection;
    }
}
