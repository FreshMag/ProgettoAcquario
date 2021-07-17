package model;

import java.time.Clock;
import java.util.Date;

public class Evento implements Insertable{
    private String dataEvento;
    private String nome;
    private String orarioInizio;
    private String orarioFine;
    private String codiceFiscale;

    public Evento(String dataEvento, String nome, String orarioInizio, String orarioFine, String codiceFiscale) {
        this.dataEvento = dataEvento;
        this.nome = nome;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.codiceFiscale = codiceFiscale;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(String orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public String getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(String orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    @Override
    public String getAttributesForQuery() {
        return "evento values(\'" + dataEvento.toString() + "\', \'"
        + nome + "\', \'"
        + orarioInizio + "\', \'"
        + orarioFine + "\', \'"
        + codiceFiscale + "\');";
    }

    @Override
    public String getKey() {
        return dataEvento.toString() + ","+ orarioInizio;
    }
}
