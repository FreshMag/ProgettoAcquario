package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {


    public static void main(String[] args) {
        Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainGui.fxml"));
        primaryStage.setTitle("Accedi al database dell'acquario");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
