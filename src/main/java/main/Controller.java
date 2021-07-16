package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import model.Esemplare;

public class Controller {
	
	DBSource source = new DBSource();
	Connection connection = source.getMySQLConnection();
	
	/**
	 * Da valutare: 
	 * 1. passare i dati in input ai metodi, che verranno chiamanti in un main, facedo lì la gestione dell'input.
	 * 2. creare interfaccia per il model con metodi comuni a tutti
	 * 3. creazione di uno statement generico, il quale viene modificato solo all'interno dei metodi e poi ripassato una volta completato, e messo infinne a null per resettarlo.
	 */
	
	public void insertNewEsemplare() {
		Scanner scan = new Scanner(System.in);
		Esemplare esemplare = new Esemplare(scan.next(), scan.next(), scan.next(), scan.nextFloat(), scan.nextFloat(), scan.next(), scan.nextInt(), scan.next());
        
		PreparedStatement statement = null; 
		
		System.out.println("Inserisici " + esemplare.getAttributesForQuery());
		String insertEsemplare = "insert into esemplare values(?, ?, ?, ?, ?, ?, ?, ?)";

		try {
            statement = connection.prepareStatement(insertEsemplare);
            
            statement.setString(1, esemplare.getCodiceEsemplare());
            statement.setString(2, esemplare.getLuogoNascita());
            statement.setString(3, esemplare.getSesso());
            statement.setFloat(4, esemplare.getPeso());
            statement.setFloat(5, esemplare.getQuantitaMangimeHg());
            statement.setString(6, esemplare.getNomeScientifico());
            statement.setInt(7, esemplare.getNumeroVasca());
            statement.setString(8, esemplare.getCodiceSalone());
            
            statement.executeUpdate();
        }
        catch (SQLException e) {
           	new Exception(e.getMessage());
            System.out.println("Errore"+ e.getMessage());
        }
        finally {
            try {
                if (statement != null) 
                    statement.close();
                if (connection!= null)
                    connection.close();
            }
            catch (SQLException e) {
            	new Exception(e.getMessage());
	            System.out.println("Errore"+ e.getMessage());
            }
        }
	}
	
	public void insertNewPianta() {
		String insertPianta = "insert into pianta values(?, ?, ?, ?, ?)";
	}
	
	public void insertNewOrdine() {
		String insertOrdine = "insert into ordine values(?, ?, ?)";
	}
	
	public void insertNewVasca() {
		String insetVasca = "insert into vasca values(?, ?, ?, ?, ?, ?)";
	}
	
	public void insertNewIngresso() {
		String insertIngresso = "insert into ingresso values(?, ?, ?, ?, ?, ?, ?, ?, ?)"; // da verificare, alcuni attributi derivano da abbonato
	}
	
	public void insertNewAbbonato() {
		String insertAbbonato = "insert into abbonato values(?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public void deleteEsemplare() {
		String delteEsemplare = "delete from esemplare where code = ?"; // da verificare anche questa delete, non sono sicuro rimuova quello giusto 
	}
	
	public void deletePianta() {
		String deletePianta = "delete from pianta where code = ?";
	}
	
	public void insertNewStaffIntoEvent() {
		String insertStaff = ""; //finire
	}
	
	public void insertNewImpiegato() {
		String insertImpiegato = "insert into staff values(?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public void insertNewManutenzioneVasca() {
		String insertManut = " "; // finire
	}
	
	public void viewMangimeRichiestoVasca() {
		String selectMangimeFromVasca = "select * from vasca"; //finire
	}
	
	public void viewIngressiRecenti() {
		String selectIngressi = "select * from ingressi"; //finire
	}
	
	public void viewEventiOggi() {
		String selectEventiOggi = "select * from evento"; //finire
	}
	
	public void viewInventarioRisorse() {
		String selectRisorse = "select * from risorse"; //finire
	}
}
