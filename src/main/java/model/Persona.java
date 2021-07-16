package model;

import java.util.Date;

public class Persona implements Insertable
{
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String contatto;
    private String indirizzo;

    public Persona(String codiceFiscale, String nome, String cognome, String dataNascita, String contatto, String indirizzo) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.contatto = contatto;
        this.indirizzo = indirizzo;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getContatto() {
        return contatto;
    }

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    @Override
    public String getAttributesForQuery() {
        return "persona values(" + codiceFiscale + ", "+ nome + ", " + cognome + ", "+ dataNascita + ", " + contatto + ", " + indirizzo + ")";
    }
}
