package model;

import java.time.Clock;
import java.util.Date;

public class Ingresso implements Insertable{
    private String codiceBiglietto;
    private String dataIngresso;
    private float prezzo;
    private int numeroPartecipanti;
    private String dataAcquisto;
    private String nome;
    private String dataEvento;
    private String orarioInizio;
    private String codiceFiscale;

    public Ingresso(String codiceBiglietto, String dataIngresso, float prezzo, int numeroPartecipanti, String dataAcquisto,
                    String nome, String dataEvento, String orarioInizio, String codiceFiscale) {
        this.codiceBiglietto = codiceBiglietto;
        this.dataIngresso = dataIngresso;
        this.prezzo = prezzo;
        this.numeroPartecipanti = numeroPartecipanti;
        this.dataAcquisto = dataAcquisto;
        this.nome = nome;
        this.dataEvento = dataEvento;
        this.orarioInizio = orarioInizio;
        this.codiceFiscale = codiceFiscale;
    }

    public String getCodiceBiglietto() {
        return codiceBiglietto;
    }

    public void setCodiceBiglietto(String codiceBiglietto) {
        this.codiceBiglietto = codiceBiglietto;
    }

    public String getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(String dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public int getNumeroPartecipanti() {
        return numeroPartecipanti;
    }

    public void setNumeroPartecipanti(int numeroPartecipanti) {
        this.numeroPartecipanti = numeroPartecipanti;
    }

    public String getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(String dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    @Override
    public String getAttributesForQuery() {
        return "ingresso values(\'" +  codiceBiglietto + "\', \'"
                + dataIngresso + "\', "
                + prezzo + ", "
                + numeroPartecipanti + ", \'"
                + dataAcquisto + "\',"
                + (nome.equals("null") ? "null, " : "\'" + nome + "\',")
                + (dataEvento.equals("null") ? "null, " : "\'" + dataEvento + "\',")
                + (orarioInizio.equals("null") ? "null," : "\'" + orarioInizio + "\',")
                + (codiceFiscale.equals("null") ? "null);" : "\'" + codiceFiscale + "\');");
    }

    @Override
    public String getKey() {
        return codiceBiglietto;
    }
}
