package model;

import java.time.Clock;
import java.util.Date;

public class Ingresso {
    private String codiceBiglietto;
    private Date dataIngresso;
    private float prezzo;
    private int numeroPartecipanti;
    private Date dataAcquisto;
    private String nome;
    private Date dataEvento;
    private Clock orarioInizio;
    private String codiceFiscale;

    public Ingresso(String codiceBiglietto, Date dataIngresso, float prezzo, int numeroPartecipanti, Date dataAcquisto,
                    String nome, Date dataEvento, Clock orarioInizio, String codiceFiscale) {
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

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
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

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public Clock getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(Clock orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
