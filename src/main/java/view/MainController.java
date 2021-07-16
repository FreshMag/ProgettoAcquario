package view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.DBManager;
import model.Esemplare;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainController {
    DBManager db = null;

    public void checkConnection() {
        if (db == null) {
            try {
                db = new DBManager();
            } catch (Exception e){
                printError(e.getMessage());
            }
        }
    }
    public void printError(String error) {
        //TODO
    }
    public List<Node> openInsertStation(List<String> attributes) {
        Stage popUp = new Stage();
        GridPane pane = new GridPane();
        List<Node> nodes = new ArrayList<>();
        Button b = new Button();
        nodes.add(b);
        int i = 0;
        for (String attr : attributes) {
            TextField fieldName = new TextField(attr);
            fieldName.setEditable(false);
            TextField fieldValue = new TextField();
            nodes.add(fieldValue);
            pane.add(fieldName,0,i);
            pane.add(fieldValue,1,i);
            i++;
        }
        b.setText("Inserisci");
        pane.add(b, 0, i);
        popUp.setScene(new Scene(pane));
        popUp.show();
        return nodes;
    }
    @FXML
    public void newEsemplare() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceEsemplare","luogoNascita","sesso","peso","quantitaMangimeHg","nomeScientifico","numeroVasca","codiceSalone"));
        ((TextField)l.get(3)).setPromptText("M or F");
        ((Button)l.get(0)).setOnAction(e -> {
            Esemplare es = new Esemplare(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),((TextField)l.get(3)).getText(),Float.parseFloat(((TextField)l.get(4)).getText()),
                    Float.parseFloat(((TextField)l.get(5)).getText()), ((TextField)l.get(6)).getText(), Integer.parseInt(((TextField)l.get(7)).getText()), ((TextField)l.get(8)).getText());
            System.out.println(es.getLuogoNascita());
            db.insertNew(es);
        });
    }
    @FXML
    public void newPianta() {}
    @FXML
    public void newOrdine() {}
    @FXML
    public void newVasca() {}
    @FXML
    public void newIngresso() {}
    @FXML
    public void newAbbonato() {}
    @FXML
    public void deleteEsemplare() {}
    @FXML
    public void deletePianta() {}
    @FXML
    public void newStaffEvento() {}
    @FXML
    public void newImpiegato() {}
    @FXML
    public void newManutenzione() {}
    @FXML
    public void printMangime() {}
    @FXML
    public void printRecentEntries() {}
    @FXML
    public void printToday() {}
    @FXML
    public void printInventory() {}
    @FXML
    public void printImpiegati() {}
    @FXML
    public void printEsemplari() {}















}
