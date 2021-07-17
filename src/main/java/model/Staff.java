package model;

import java.util.Date;

public class Staff extends Persona{
    private String IDImpiegato;
    private String ruolo;

    public Staff(String codiceFiscale, String nome, String cognome, String dataNascita, String contatto, String indirizzo, String IDImpiegato, String Ruolo) {
        super(codiceFiscale, nome, cognome, dataNascita, contatto, indirizzo);
        this.IDImpiegato = IDImpiegato;
        this.ruolo = Ruolo;
    }

    public String getIDImpiegato() {
        return IDImpiegato;
    }

    public void setIDImpiegato(String IDImpiegato) {
        this.IDImpiegato = IDImpiegato;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String getAttributesForQuery() {
        return "staff values(\'" + super.getCodiceFiscale() + "\',\'" + super.getNome() + "\',\' " + super.getCognome() + "\', \'"+ super.getDataNascita() + "\',\' "
                + super.getContatto() + "\',\'" + super.getIndirizzo() + "\',\'"+ IDImpiegato + "\',\' "+ ruolo+ "\')";
    }
}
