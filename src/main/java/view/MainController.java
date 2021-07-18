package view;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.DBManager;
import model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainController {
    DBManager db = null;
    @FXML
    Label errorLbl;
    public void checkConnection() {
        if (db == null) {
            try {
                db = new DBManager();
            } catch (Exception e){
                printError(e.getMessage());
            }
        }
    }
    public String getValueStr(String s) {
        return s == null || s.equals("")  || s.equals("null") ? "null" : s.substring(s.indexOf(":")+2);
    }
    public void printError(String error) {
        errorLbl.setText(error);
        System.out.println(error);
    }
    public List<Node> openInsertStation(List<String> attributes, List<Boolean> hasCombo, List<List<String>> comboValues) {
        Stage popUp = new Stage();
        popUp.setTitle("Inserisci/Rimuovi nel Database");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        GridPane pane = new GridPane();
        List<Node> nodes = new ArrayList<>();
        Button b = new Button();
        nodes.add(b);
        int i = 0;
        int j = 0;
        for (String attr : attributes) {
            Label fieldName = new Label(attr);
            if (hasCombo.get(i)) {
                ComboBox<String> combo = new ComboBox<>();
                combo.getItems().addAll(comboValues.get(j));
                nodes.add(combo);
                pane.add(combo,1,i);
                j++;
            } else {
                TextField fieldValue = new TextField();
                nodes.add(fieldValue);
                pane.add(fieldValue,1,i);
            }
            pane.add(fieldName,0,i);
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
        List<String> vasche = null;
        List<String> specie = null;
        try {
            vasche = db.getKeysFrom("vasca", List.of("NumeroVasca", "CodiceSalone"));
            specie = db.getKeysFrom("specie_animale", List.of("NomeScientifico"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        List<Node> l = openInsertStation(List.of("Codice Esemplare","Luogo Nascita","Sesso","Peso","Quantita Mangime Hg","Specie Animale","Vasca"),
                List.of(false,false,false,false,false,true,true), List.of(specie, vasche));
        ((TextField)l.get(3)).setPromptText("M or F");
        ((Button)l.get(0)).setOnAction(e -> {
            String res = ((String)((ComboBox)l.get(7)).getValue());
            int numVasca = Integer.parseInt(res.substring(res.indexOf(":")+ 2, res.indexOf(",")));
            String codSalone = res.substring(res.lastIndexOf(":")+2);
            String specieAni = ((String)((ComboBox)l.get(6)).getValue());
            Esemplare es = new Esemplare(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),((TextField)l.get(3)).getText(),Float.parseFloat(((TextField)l.get(4)).getText()),
                    Float.parseFloat(((TextField)l.get(5)).getText()), getValueStr(specieAni) , numVasca, codSalone);
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newPianta() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<String> vasche = null;
        List<String> specie = null;
        try {
            vasche = db.getKeysFrom("vasca", List.of("NumeroVasca", "CodiceSalone"));
            specie = db.getKeysFrom("specie_vegetale", List.of("NomeScientifico"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        List<Node> l = openInsertStation(List.of("Codice Vegetale","Peso Kg","Specie Vegetale","Vasca"),
                List.of(false,false,true,true),List.of(specie,vasche));
        ((Button)l.get(0)).setOnAction(e -> {
            String res = ((String)((ComboBox)l.get(4)).getValue());
            int numVasca = Integer.parseInt(res.substring(res.indexOf(":")+ 2, res.indexOf(",")));
            String codSalone = res.substring(res.lastIndexOf(":")+2);
            String specieVeg = ((String)((ComboBox)l.get(3)).getValue());
            Pianta es = new Pianta(((TextField)l.get(1)).getText(),Float.parseFloat(((TextField)l.get(2)).getText()),
                    getValueStr(specieVeg),numVasca, codSalone);
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newOrdine() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<String> fornitori = null;
        try {
            fornitori = db.getKeysFrom("fornitore", List.of("IDFornitore"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        List<Node> l = openInsertStation(List.of("Codice Ordine", "Data Ordine", "ID Fornitore"), List.of(false,false,true),List.of(fornitori));
        ((Button)l.get(0)).setOnAction(e -> {
            String fornitore = ((String)((ComboBox)l.get(3)).getValue());
            Ordine es = new Ordine(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    getValueStr(fornitore));
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newVasca() {
        checkConnection();
        List<String> saloni = null;
        List<String> habitat = null;
        try {
            saloni = db.getKeysFrom("salone", List.of("CodiceSalone"));
            habitat = db.getKeysFrom("habitat", List.of("Nome"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("numeroVasca", "codiceSalone", "dimensione","posizione", "aperta", "nome"), List.of(false, true,false,false,false,true),
                List.of(saloni, habitat));
        ((Button)l.get(0)).setOnAction(e -> {
            String salone = ((String)((ComboBox)l.get(2)).getValue());
            String hab = ((String)((ComboBox)l.get(6)).getValue());
            Vasca es = new Vasca(Integer.parseInt(((TextField)l.get(1)).getText()),getValueStr(salone),
            Float.parseFloat(((TextField)l.get(3)).getText()), ((TextField)l.get(4)).getText(), Boolean.parseBoolean(((TextField)l.get(5)).getText()),
                    getValueStr(hab));
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newIngresso() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<String> tipo_biglietti = null;
        List<String> eventi = null;
        List<String> abbonati = null;
        try {
            tipo_biglietti = db.getKeysFrom("tipo_biglietto", List.of("Nome"));
            eventi = db.getKeysFrom("evento", List.of("DataEvento", "OrarioInizio"));
            abbonati = db.getKeysFrom("abbonato", List.of("CodiceFiscale"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        List<Node> l = openInsertStation(List.of("Codice Biglietto","Data Ingresso","Prezzo","Numero Partecipanti", "Data Acquisto", "Tipo Biglietto", "Evento", "Nome Abbonato"),
                List.of(false,false,false,false,false,true,true,true),List.of(tipo_biglietti,eventi,abbonati));
        ((Button)l.get(0)).setOnAction(e -> {
            String res = ((String)((ComboBox)l.get(7)).getValue());
            String dataEve = res == null || res.equals("") ? "null" : res.substring(res.indexOf(":")+ 2, res.indexOf(","));
            String oraInizio = res == null || res.equals("") ? "null" : res.substring(res.indexOf(",")+ 17);
            String tipo = ((String)((ComboBox)l.get(6)).getValue());
            String nomeAbbo = ((String)((ComboBox)l.get(8)).getValue());
            System.out.println(dataEve);
            System.out.println(oraInizio);
            Ingresso es = new Ingresso(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    Float.parseFloat(((TextField)l.get(3)).getText()), Integer.parseInt(((TextField)l.get(4)).getText()), ((TextField)l.get(5)).getText(),
                    getValueStr(tipo), dataEve, oraInizio, getValueStr(nomeAbbo));
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newAbbonato() {
        checkConnection();
        List<String> tipi_abbo = null;
        try {
            tipi_abbo = db.getKeysFrom("tipo_abbonamento", List.of("Nome"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("Codice Fiscale", "Nome", "Cognome", "Data Nascita", "Contatto", "Indirizzo","Tipo Abbonamento", "Data Inizio"),
                List.of(false, false, false, false,false,false,true, false),List.of(tipi_abbo));
        ((Button)l.get(0)).setOnAction(e -> {
            String nomeAbbo = ((String)((ComboBox)l.get(7)).getValue());
            Abbonato es = new Abbonato(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    ((TextField)l.get(3)).getText(), ((TextField)l.get(4)).getText(), ((TextField)l.get(5)).getText(),
                    ((TextField)l.get(6)).getText(), getValueStr(nomeAbbo), ((TextField)l.get(8)).getText());
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void deleteEsemplare() {
        checkConnection();
        List<String> esemplari = null;
        try {
            esemplari = db.getKeysFrom("esemplare", List.of("CodiceEsemplare"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("Codice Esemplare"),  List.of(true), List.of(esemplari));
        Button b = ((Button)l.get(0));
        b.setText("Elimina");
        b.setOnAction(e -> {
            try {
                String codEse = ((String)((ComboBox)l.get(1)).getValue());
                db.delete(new Esemplare(getValueStr(codEse)));
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void deletePianta() {
        checkConnection();
        List<String> piante = null;
        try {
            piante = db.getKeysFrom("pianta", List.of("CodiceVegetale"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("Codice Vegetale"),  List.of(true), List.of(piante));
        Button b = ((Button)l.get(0));
        b.setText("Elimina");
        b.setOnAction(e -> {
            try {
                String codVeg = ((String)((ComboBox)l.get(1)).getValue());
                db.delete(new Pianta(getValueStr(codVeg)));
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void newImpiegato() {
        checkConnection();
        //System.out.println(Stream.of(Esemplare.class.getFields()).map(i -> i.getName()).collect(Collectors.toList()));
        List<Node> l = openInsertStation(List.of("codiceFiscale", "nome", "cognome", "dataNascita", "contatto", "indirizzo","IDImpiegato", "Ruolo"),
                null,null);
        ((Button)l.get(0)).setOnAction(e -> {
            Staff es = new Staff(((TextField)l.get(1)).getText(),((TextField)l.get(2)).getText(),
                    ((TextField)l.get(3)).getText(), ((TextField)l.get(4)).getText(), ((TextField)l.get(5)).getText(),
                    ((TextField)l.get(6)).getText(), ((TextField)l.get(7)).getText(), ((TextField)l.get(8)).getText());
            try {
                db.insertNew(es);
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
    }
    @FXML
    public void printMangime() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza il mangime richiesto dagli esemplari di una vasca");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        GridPane pane = new GridPane();
        Label name1 = new Label("Vasca");
        VBox boxVasca = new VBox();
        boxVasca.getChildren().add(name1);
        boxVasca.setAlignment(Pos.CENTER);
        List<String> vasche = null;
        try {
            vasche = db.getKeysFrom("vasca", List.of("NumeroVasca", "CodiceSalone"));
        } catch (SQLException throwables) {
            printError(throwables.getMessage());
        }
        ComboBox<String> combobox = new ComboBox<>();
        combobox.getItems().addAll(vasche);
        Button launchQ = new Button("Visualizza Risultati");
        VBox box = new VBox();
        Label resultLbl = new Label();
        box.getChildren().add(resultLbl);
        box.setAlignment(Pos.CENTER);
        pane.add(boxVasca, 0,0);
        pane.add(combobox,1,0);
        pane.add(launchQ,0,1);
        pane.add(box,1,1);
        launchQ.setOnAction(e -> {
            try {
                String res = combobox.getValue();
                int numVasca = Integer.parseInt(res.substring(res.indexOf(":")+ 2, res.indexOf(",")));
                String codSalone = res.substring(res.lastIndexOf(":")+2);
                resultLbl.setText(String.valueOf(db.viewMangimeRichiestoVasca(numVasca, codSalone)));
            } catch (Exception throwables) {
                printError(throwables.getMessage());
            }
        });
        popUp.setScene(new Scene(pane));
        popUp.show();
    }
    @FXML
    public void printRecentEntries() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza Ingressi dell'ultima settimana");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Ingresso> table = new TableView<Ingresso>();
        TableColumn<Ingresso, String> codBiglCol = new TableColumn<Ingresso, String>("Codice Biglietto");
        codBiglCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("codiceBiglietto"));
        TableColumn<Ingresso, String> dataIngrCol = new TableColumn<Ingresso, String>("Data Ingresso");
        dataIngrCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("dataIngresso"));
        TableColumn<Ingresso, Float> prezzoCol = new TableColumn<Ingresso, Float>("Prezzo");
        prezzoCol.setCellValueFactory(new PropertyValueFactory<Ingresso, Float>("prezzo"));
        TableColumn<Ingresso, Integer> numParCol = new TableColumn<Ingresso, Integer>("Numero Partecipanti");
        numParCol.setCellValueFactory(new PropertyValueFactory<Ingresso, Integer>("numeroPartecipanti"));
        TableColumn<Ingresso, String> dataAcquCol = new TableColumn<Ingresso, String>("Data Acquisto");
        dataAcquCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("dataAcquisto"));
        TableColumn<Ingresso, String> nomeCol = new TableColumn<Ingresso, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("nome"));
        TableColumn<Ingresso, String> dataEvenCol = new TableColumn<Ingresso, String>("Data Evento");
        dataEvenCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("dataEvento"));
        TableColumn<Ingresso, String> oraInizCol = new TableColumn<Ingresso, String>("Orario Inizio");
        oraInizCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("orarioInizio"));
        TableColumn<Ingresso, String> codFiscCol = new TableColumn<Ingresso, String>("Codice Fiscale");
        codFiscCol.setCellValueFactory(new PropertyValueFactory<Ingresso, String>("codiceFiscale"));

        table.getColumns().add(codBiglCol);
        table.getColumns().add(dataIngrCol);
        table.getColumns().add(prezzoCol);
        table.getColumns().add(numParCol);
        table.getColumns().add(dataAcquCol);
        table.getColumns().add(nomeCol);
        table.getColumns().add(dataEvenCol);
        table.getColumns().add(oraInizCol);
        table.getColumns().add(codFiscCol);
        try {
            table.getItems().addAll(db.viewIngressiRecenti());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
    @FXML
    public void printToday() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza eventi programmati per oggi");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Evento> table = new TableView<Evento>();
        TableColumn<Evento, String> dataEveCol = new TableColumn<Evento, String>("Data Evento");
        dataEveCol.setCellValueFactory(new PropertyValueFactory<Evento, String>("dataEvento"));
        TableColumn<Evento, String> nomeCol = new TableColumn<Evento, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Evento, String>("nome"));
        TableColumn<Evento, String> oraInizCol = new TableColumn<Evento, String>("Orario Inizio");
        oraInizCol.setCellValueFactory(new PropertyValueFactory<Evento, String>("orarioInizio"));
        TableColumn<Evento, String> oraFineCol = new TableColumn<Evento, String>("Orario Fine");
        oraFineCol.setCellValueFactory(new PropertyValueFactory<Evento, String>("orarioFine"));
        TableColumn<Evento, String> codFiscCol = new TableColumn<Evento, String>("Codice Fiscale");
        codFiscCol.setCellValueFactory(new PropertyValueFactory<Evento, String>("codiceFiscale"));

        table.getColumns().add(dataEveCol);
        table.getColumns().add(nomeCol);
        table.getColumns().add(oraInizCol);
        table.getColumns().add(oraFineCol);
        table.getColumns().add(codFiscCol);
        try {
            table.getItems().addAll(db.viewEventiOggi());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
    @FXML
    public void printInventory() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza inventario");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
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
        try {
            table.getItems().addAll(db.viewInventarioRisorse());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
    @FXML
    public void printImpiegati() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza impiegati");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Staff> table = new TableView<Staff>();
        TableColumn<Staff, String> codFiscCol = new TableColumn<Staff, String>("Codice Fiscale");
        codFiscCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("codiceFiscale"));
        TableColumn<Staff, String> nomeCol = new TableColumn<Staff, String>("Nome");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("nome"));
        TableColumn<Staff, String> cognCol = new TableColumn<Staff, String>("Cognome");
        cognCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("cognome"));
        TableColumn<Staff, String> dataNascCol = new TableColumn<Staff, String>("Data di nascita");
        dataNascCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("dataNascita"));
        TableColumn<Staff, String> contCol = new TableColumn<Staff, String>("Contatto");
        contCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("contatto"));
        TableColumn<Staff, String> indirCol = new TableColumn<Staff, String>("Indirizzo");
        indirCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("indirizzo"));
        TableColumn<Staff, String> idCol = new TableColumn<Staff, String>("ID Impiegato");
        idCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("IDImpiegato"));
        TableColumn<Staff, String> ruoloCol = new TableColumn<Staff, String>("Ruolo");
        ruoloCol.setCellValueFactory(new PropertyValueFactory<Staff, String>("ruolo"));

        table.getColumns().add(codFiscCol);
        table.getColumns().add(nomeCol);
        table.getColumns().add(cognCol);
        table.getColumns().add(dataNascCol);
        table.getColumns().add(contCol);
        table.getColumns().add(indirCol);
        table.getColumns().add(idCol);
        table.getColumns().add(ruoloCol);

        try {
            table.getItems().addAll(db.viewImpiegati());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
    @FXML
    public void printVasche() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza le vasche");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Vasca> table = new TableView<Vasca>();
        TableColumn<Vasca, Integer> numVasCol = new TableColumn<Vasca, Integer>("Numero Vasca");
        numVasCol.setCellValueFactory(new PropertyValueFactory<Vasca, Integer>("numeroVasca"));
        TableColumn<Vasca, String> codSalCol = new TableColumn<Vasca, String>("Codice Salone");
        codSalCol.setCellValueFactory(new PropertyValueFactory<Vasca, String>("codiceSalone"));
        TableColumn<Vasca, Float> dimCol = new TableColumn<Vasca, Float>("Dimensione");
        dimCol.setCellValueFactory(new PropertyValueFactory<Vasca, Float>("dimensione"));
        TableColumn<Vasca, String> posCol = new TableColumn<Vasca, String>("Posizione");
        posCol.setCellValueFactory(new PropertyValueFactory<Vasca, String>("posizione"));
        TableColumn<Vasca, Boolean> apertaCol = new TableColumn<Vasca, Boolean>("Aperta");
        apertaCol.setCellValueFactory(new PropertyValueFactory<Vasca, Boolean>("aperta"));
        TableColumn<Vasca, String> nomeCol = new TableColumn<Vasca, String>("Nome habitat");
        nomeCol.setCellValueFactory(new PropertyValueFactory<Vasca, String>("nome"));


        table.getColumns().add(numVasCol);
        table.getColumns().add(codSalCol);
        table.getColumns().add(dimCol);
        table.getColumns().add(posCol);
        table.getColumns().add(apertaCol);
        table.getColumns().add(nomeCol);
        try {
            table.getItems().addAll(db.viewVasche());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }

    @FXML
    public void printEsemplari() {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza esemplari dell'acquario");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Esemplare> table = new TableView<Esemplare>();
        TableColumn<Esemplare, String> codEsemCol = new TableColumn<Esemplare, String>("Codice Esemplare");
        codEsemCol.setCellValueFactory(new PropertyValueFactory<Esemplare, String>("codiceEsemplare"));
        TableColumn<Esemplare, String> luoNascCol = new TableColumn<Esemplare, String>("Luogo Nascita");
        luoNascCol.setCellValueFactory(new PropertyValueFactory<Esemplare, String>("luogoNascita"));
        TableColumn<Esemplare, String> sessoCol = new TableColumn<Esemplare, String>("Sesso");
        sessoCol.setCellValueFactory(new PropertyValueFactory<Esemplare, String>("sesso"));
        TableColumn<Esemplare, Float> pesoCol = new TableColumn<Esemplare, Float>("Peso");
        pesoCol.setCellValueFactory(new PropertyValueFactory<Esemplare, Float>("peso"));
        TableColumn<Esemplare, Float> quantitaCol = new TableColumn<Esemplare, Float>("Quantita Mangime");
        quantitaCol.setCellValueFactory(new PropertyValueFactory<Esemplare, Float>("quantitaMangimeHg"));
        TableColumn<Esemplare, String> nomeScienCol = new TableColumn<Esemplare, String>("Nome Scientifico");
        nomeScienCol.setCellValueFactory(new PropertyValueFactory<Esemplare, String>("nomeScientifico"));
        TableColumn<Esemplare, Integer> numVasCol = new TableColumn<Esemplare, Integer>("Numero Vasca");
        numVasCol.setCellValueFactory(new PropertyValueFactory<Esemplare, Integer>("numeroVasca"));
        TableColumn<Esemplare, String> codSalCol = new TableColumn<Esemplare, String>("Codice Salone");
        codSalCol.setCellValueFactory(new PropertyValueFactory<Esemplare, String>("codiceSalone"));

        table.getColumns().add(codEsemCol);
        table.getColumns().add(luoNascCol);
        table.getColumns().add(sessoCol);
        table.getColumns().add(pesoCol);
        table.getColumns().add(quantitaCol);
        table.getColumns().add(nomeScienCol);
        table.getColumns().add(numVasCol);
        table.getColumns().add(codSalCol);
        try {
            table.getItems().addAll(db.viewEsemplari());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }

    @FXML
    public void printPiante () {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza piante dell'acquario");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Pianta> table = new TableView<Pianta>();
        TableColumn<Pianta, String> codVegCol = new TableColumn<Pianta, String>("Codice Vegetale");
        codVegCol.setCellValueFactory(new PropertyValueFactory<Pianta, String>("codiceVegetale"));
        TableColumn<Pianta, Float> pesoCol = new TableColumn<Pianta, Float>("Peso KG");
        pesoCol.setCellValueFactory(new PropertyValueFactory<Pianta, Float>("pesoKg"));
        TableColumn<Pianta, String> nomeScieCol = new TableColumn<Pianta, String>("Nome Scientifico");
        nomeScieCol.setCellValueFactory(new PropertyValueFactory<Pianta, String>("nomeScientifico"));
        TableColumn<Pianta, Integer> numVasCol = new TableColumn<Pianta, Integer>("Numero Vasca");
        numVasCol.setCellValueFactory(new PropertyValueFactory<Pianta, Integer>("numeroVasca"));
        TableColumn<Pianta, String> codSalCol = new TableColumn<Pianta, String>("Codice Salone");
        codSalCol.setCellValueFactory(new PropertyValueFactory<Pianta, String>("codiceSalone"));

        table.getColumns().add(codVegCol);
        table.getColumns().add(pesoCol);
        table.getColumns().add(nomeScieCol);
        table.getColumns().add(numVasCol);
        table.getColumns().add(codSalCol);
        try {
            table.getItems().addAll(db.viewPiante());
        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
    }
    @FXML
    public void printOrdini () {
        this.checkConnection();
        Stage popUp = new Stage();
        popUp.setTitle("Visualizza ordini effettuati");
        popUp.getIcons().add(new Image(getClass().getResourceAsStream("/icons/dbicon2.png")));
        popUp.setResizable(false);
        BorderPane anchor = new BorderPane();
        TableView<Ordine> table = new TableView<Ordine>();
        TableColumn<Ordine, String> codOrdineCol = new TableColumn<Ordine, String>("Codice Ordine");
        codOrdineCol.setCellValueFactory(new PropertyValueFactory<Ordine, String>("codiceOrdine"));
        TableColumn<Ordine, String> dataOrdineCol = new TableColumn<Ordine, String>("Data Ordine");

        dataOrdineCol.setCellValueFactory(new PropertyValueFactory<Ordine, String>("dataOrdine"));

        TableColumn<Ordine, String> IDFornitoreCol = new TableColumn<Ordine, String>("ID Fornitore");
        IDFornitoreCol.setCellValueFactory(new PropertyValueFactory<Ordine, String>("IDFornitore"));

        table.getColumns().add(codOrdineCol);

        table.getColumns().add(dataOrdineCol);

        table.getColumns().add(IDFornitoreCol);

        try {

            table.getItems().addAll(db.viewOrdini());

        } catch (Exception throwables) {
            printError(throwables.getMessage());
        }
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        anchor.setBottom(table);
        popUp.setScene(new Scene(anchor));
        popUp.show();
 }

}
