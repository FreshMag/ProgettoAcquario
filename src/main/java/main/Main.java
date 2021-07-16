package main;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Main extends Application {


    public static void main(String[] args) {
        //launch(args);
    	DBSource source = new DBSource();
    	Connection connection = source.getMySQLConnection();
    	
    	/*String query2 ="insert into SETTORE values('codicesalone3')";
    	PreparedStatement insert = null;
    	try {
    		insert = connection.prepareStatement(query2);
    		insert.executeUpdate();
    		insert.close();
    	} catch(SQLException e) {
    		new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());			
            e.printStackTrace();
    	}*/
    	
    	
    	String query = "Select * from ABBONATO";
    	PreparedStatement statement = null;
    	try {
			statement = connection.prepareStatement(query);
		} catch (SQLException e) {
			new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());			
            e.printStackTrace();
		}
        try {
			ResultSet result = statement.executeQuery();
			while(result.next()) {
				System.out.println(result.getString(1));
			}
		} catch (SQLException e) {
			new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());			
            e.printStackTrace();
		}
        finally {
        	try {
				statement.close();
	        	connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
    	Parent root = new Group();
        primaryStage.setScene(new Scene(root));
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
