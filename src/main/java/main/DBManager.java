package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Deletable;
import model.Esemplare;
import model.Insertable;
import model.Risorsa;

public class DBManager {

	Connection connection = null;//source.getMySQLConnection();
	public DBManager() throws SQLException {
		connection = DBSource.getMySQLConnection();
	}
	/**
	 * Da valutare: 
	 * 1. passare i dati in input ai metodi, che verranno chiamanti in un main, facendo li la gestione dell'input.
	 * 2. creare interfaccia per il model con metodi comuni a tutti
	 * 3. creazione di uno statement generico, il quale viene modificato solo all'interno dei metodi e poi ripassato una volta completato, e messo infinne a null per resettarlo.
	 */
	
	public void insertNew(Insertable ins) throws SQLException {
        
		PreparedStatement statement = null; 
		
		//System.out.println("Inserisici " + esemplare.getAttributesForQuery());
		//String insertEsemplare = "insert into esemplare values(?, ?, ?, ?, ?, ?, ?, ?)";
		String query = "insert into " + ins.getAttributesForQuery();
		//try {
            statement = connection.prepareStatement(query);
            /*
            statement.setString(1, esemplare.getCodiceEsemplare());
            statement.setString(2, esemplare.getLuogoNascita());
            statement.setString(3, esemplare.getSesso());
            statement.setFloat(4, esemplare.getPeso());
            statement.setFloat(5, esemplare.getQuantitaMangimeHg());
            statement.setString(6, esemplare.getNomeScientifico());
            statement.setInt(7, esemplare.getNumeroVasca());
            statement.setString(8, esemplare.getCodiceSalone());
            */
            statement.executeUpdate();
        //}
        //catch (SQLException e) {
       //    	e.getMessage();
       // }
        //finally {
        //    try {
               if (statement != null)
                    statement.close();
                if (connection!= null)
                    connection.close();
           // }
       //     catch (SQLException e) {
	    //        return  e.getMessage();
        //    }
        //    return "";
        //}
	}

	public void delete(Deletable dt) throws SQLException {
		PreparedStatement statement = null;
		String query = "delete from " + dt.getStringForQuery();
		//try {
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		//}
		//catch (SQLException e) {
		//	return e.getMessage();
		//}
		//finally {
		//	try {
				if (statement != null)
					statement.close();
				if (connection!= null)
					connection.close();
		//	}
		//	catch (SQLException e) {
		//		return  e.getMessage();
		//	}
		//	return "";
		//}
	}
	
	/*public void insertNewStaffIntoEvent() {
		String insertStaff = ""; //finire
	}*/

	//public void insertNewManutenzioneVasca() {
		//String insertManut = " "; // finire
	//}
	
	public List<Risorsa> viewMangimeRichiestoVasca(int numeroVasca, String codiceSalone) throws SQLException {
		PreparedStatement statement = null;
		String query = "select * from vasca where NumeroVasca = " + numeroVasca + " AND CodiceSalone = "+ codiceSalone  ; //finire
		statement = connection.prepareStatement(query);
		ResultSet result = statement.executeQuery();
		List<Risorsa> output = new ArrayList<>();
		while(result.next()) {
			Risorsa risorsa = new Risorsa(result.getString("CodiceOrdine"), result.getInt("Quantita"),
					result.getString("Nome"),result.getFloat("CostoSingolo"),result.getString("Tipologia"),
					result.getString("Marca"), result.getInt("NumeroVasca"), result.getString("CodiceSalone"));
			output.add(risorsa);
		}
		if (statement != null)
			statement.close();
		if (connection!= null)
			connection.close();
		return output;
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
