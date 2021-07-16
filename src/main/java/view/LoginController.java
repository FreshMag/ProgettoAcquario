package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.DBSource;

import java.sql.Connection;

public class LoginController {

    @FXML
    TextField uriDB;
    @FXML
    TextField nomeUtente;
    @FXML
    PasswordField password;
    @FXML
    Label errorLbl;

    @FXML
    public void accedi () {
        DBSource.userName = nomeUtente.getText();
        DBSource.password = password.getText();
        DBSource.dbUri = "jdbc:mysql://" + uriDB.getText() +  "?serverTimezone=UTC" + "&useSSL=false";
        try {
            Connection conn = DBSource.getMySQLConnection();
            Stage controlStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/controlPanel.fxml"));
            controlStage.setScene(new Scene(root));
            controlStage.show();
            ((Stage)uriDB.getScene().getWindow()).close();
            conn.close();
            errorLbl.setText("OK!");
        } catch (Exception e) {
            errorLbl.setText(e.toString());
        }
    }
}
