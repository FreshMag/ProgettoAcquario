package model;

import java.util.Date;

public class Abbonato extends Persona{
    private String abbonamento_Nome;
    private Date dataInizio;

    public Abbonato(String codiceFiscale, String nome, String cognome, Date dataNascita, String contatto, String indirizzo, String abbonamento_Nome, Date dataInizio) {
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

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }
}
