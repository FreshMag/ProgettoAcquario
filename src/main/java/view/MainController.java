package view;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.DBManager;
import model.*;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        popUp.setResizable(false);
        GridPane pane = new GridPane();
        List<Node> nodes = new ArrayList<>();
        Button b = new Button();
        nodes.add(b);
        int i = 0;
        for (String attr : attributes) {
            Label fieldName = new Label(attr);
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
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newPianta() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceVegetale","pesoKg","nomeScientifico","numeroVasca","codiceSalone"));
        ((Button)l.get(0)).setOnAction(e -> {
            Pianta es = new Pianta(((TextField)l.get(1)).getText(),Float.parseFloat(((TextField)l.get(2)).getText()),
                    ((TextField)l.get(3)).getText(), Integer.parseInt(((TextField)l.get(4)).getText()), ((TextField)l.get(5)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newOrdine() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceOrdine", "dataOrdine", "IDFornitore"));
        ((Button)l.get(0)).setOnAction(e -> {
            Ordine es = new Ordine(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    ((TextField)l.get(3)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newVasca() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("numeroVasca", "codiceSalone", "dimensione","posizione", "aperta", "nome"));
        ((Button)l.get(0)).setOnAction(e -> {
            Vasca es = new Vasca(Integer.parseInt(((TextField)l.get(1)).getText()),((TextField)l.get(2)).getText(),
            Float.parseFloat(((TextField)l.get(3)).getText()), ((TextField)l.get(4)).getText(), Boolean.parseBoolean(((TextField)l.get(5)).getText()),
                    ((TextField)l.get(6)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newIngresso() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceBiglietto","dataIngresso","prezzo","numeroPartecipanti", "dataAcquisto", "nome", "dataEvento", "orarioInizio","codiceFiscale"));
        ((Button)l.get(0)).setOnAction(e -> {
            Ingresso es = new Ingresso(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    Float.parseFloat(((TextField)l.get(3)).getText()), Integer.parseInt(((TextField)l.get(4)).getText()), ((TextField)l.get(5)).getText(),
                    ((TextField)l.get(6)).getText(), ((TextField)l.get(7)).getText(), ((TextField)l.get(8)).getText(), ((TextField)l.get(9)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newAbbonato() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceFiscale", "nome", "cognome", "dataNascita", "contatto", "indirizzo","abbonamento_Nome", "dataInizio"));
        ((Button)l.get(0)).setOnAction(e -> {
            Abbonato es = new Abbonato(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    ((TextField)l.get(3)).getText(), ((TextField)l.get(4)).getText(), ((TextField)l.get(5)).getText(),
                    ((TextField)l.get(6)).getText(), ((TextField)l.get(7)).getText(), ((TextField)l.get(8)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void deleteEsemplare() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("Codice Esemplare"));
        Button b = ((Button)l.get(0));
        b.setText("Elimina");
        b.setOnAction(e -> {
            try {
                db.delete(new Esemplare(((TextField)l.get(1)).getText()));
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void deletePianta() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("Codice Vegetale"));
        Button b = ((Button)l.get(0));
        b.setText("Elimina");
        b.setOnAction(e -> {
            try {
                db.delete(new Pianta(((TextField)l.get(1)).getText()));
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newStaffEvento() {}
    @FXML
    public void newImpiegato() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceFiscale", "nome", "cognome", "dataNascita", "contatto", "indirizzo","IDImpiegato", "Ruolo"));
        ((Button)l.get(0)).setOnAction(e -> {
            Staff es = new Staff(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    ((TextField)l.get(3)).getText(), ((TextField)l.get(4)).getText(), ((TextField)l.get(5)).getText(),
                    ((TextField)l.get(6)).getText(), ((TextField)l.get(7)).getText(), ((TextField)l.get(8)).getText());
            try {
                db.insertNew(es);
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newManutenzione() {}
    @FXML
    public void printMangime() {
        Stage popUp = new Stage();
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        GridPane pane = new GridPane();
        Label name1 = new Label("Numero vasca");
        Label name2 = new Label("CodiceSalone");
        TextField field1 = new TextField();
        TextField field2 = new TextField();
        Button launchQ = new Button("Visualizza Risultati");
        pane.add(name1, 0,0);
        pane.add(name2, 0,1);
        pane.add(field1,1,0);
        pane.add(field2,1,1);
        pane.add(launchQ,0,2);
        anchor.setTop(pane);

        TableView<Risorsa> table = new TableView<Risorsa>();
        TableColumn<Risorsa, String> codOrdCol = new TableColumn<Risorsa, String>("Codice Ordine");
        codOrdCol.setCellValueFactory(new PropertyValueFactory<Risorsa, String>("codiceOrdine"));
        TableColumn<Risorsa, Integer> quantCol = new TableColumn<Risorsa, Integer>("Quantita'");
        quantCol.setCellValueFactory(new PropertyValueFactory<Risorsa, Integer>("quantita"));
        TableColumn<Risorsa, String> nomeCol = new TableColumn<Risorsa, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Risorsa, String>("nome"));
        TableColumn<Risorsa, Float> cosSingCol = new TableColumn<Risorsa, Float>("Costo Singolo");
        cosSingCol.setCellValueFactory(new PropertyValueFactory<Risorsa, Float>("costoSingolo"));
        TableColumn<Risorsa, String> tipCol = new TableColumn<Risorsa, String>("Tipologia");
        tipCol.setCellValueFactory(new PropertyValueFactory<Risorsa, String>("tipologia"));
        TableColumn<Risorsa, String> marcaCol = new TableColumn<Risorsa, String>("Marca");
        marcaCol.setCellValueFactory(new PropertyValueFactory<Risorsa, String>("marca"));
        TableColumn<Risorsa, Integer> numVasCol = new TableColumn<Risorsa, Integer>("Numero Vasca");
        numVasCol.setCellValueFactory(new PropertyValueFactory<Risorsa, Integer>("numeroVasca"));
        TableColumn<Risorsa, String> codSalCol = new TableColumn<Risorsa, String>("Codice Salone");
        codSalCol.setCellValueFactory(new PropertyValueFactory<Risorsa, String>("codiceSalone"));

        table.getColumns().add(codOrdCol);
        table.getColumns().add(quantCol);
        table.getColumns().add(nomeCol);
        table.getColumns().add(cosSingCol);
        table.getColumns().add(tipCol);
        table.getColumns().add(marcaCol);
        table.getColumns().add(numVasCol);
        table.getColumns().add(codSalCol);

        launchQ.setOnAction(e -> {
            try {
                for (Risorsa r : db.viewMangimeRichiestoVasca(Integer.parseInt(field1.getText()), field2.getText())) {
                    table.getItems().add(r);
                }
            } catch (SQLException throwables) {
                printError(throwables.getMessage());
            }
        });
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
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
