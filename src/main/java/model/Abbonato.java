package model;

import java.util.Date;

public class Abbonato extends Persona{
    private String abbonamento_Nome;
    private String dataInizio;

    public Abbonato(String codiceFiscale, String nome, String cognome, String dataNascita, String contatto, String indirizzo, String abbonamento_Nome, String dataInizio) {
        super(codiceFiscale, nome, cognome, dataNascita, contatto, indirizzo);
        this.abbonamento_Nome = abbonamento_Nome;
        this.dataInizio = dataInizio;
    }

    public String getAbbonamento_Nome() {
        return abbonamento_Nome;
    }

    public void setAbbonamento_Nome(String abbonamento_Nome) {
        this.abbonamento_Nome = abbonamento_Nome;
    }

    public String getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(String dataInizio) {
        this.dataInizio = dataInizio;
    }

    @Override
    public String getAttributesForQuery() {
        return "abbonato values(\'" + super.getCodiceFiscale() + "\',\'"+ dataInizio.toString() + "\', \'"+ super.getNome() + "\', \'" + super.getCognome() + "\', \'"+ super.getDataNascita() + "\', \'"
                + super.getContatto() + "\', \'" + super.getIndirizzo() + "\',\'"+ abbonamento_Nome + "\')";
    }
}
