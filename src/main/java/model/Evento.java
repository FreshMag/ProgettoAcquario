package model;

import java.time.Clock;
import java.util.Date;

public class Evento {
    private Date dataEvento;
    private String nome;
    private Clock orarioInizio;
    private Clock orarioFine;
    private String codiceFiscale;

    public Evento(Date dataEvento, String nome, Clock orarioInizio, Clock orarioFine, String codiceFiscale) {
        this.dataEvento = dataEvento;
        this.nome = nome;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.codiceFiscale = codiceFiscale;
    }

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Clock getOrarioInizio() {
        return orarioInizio;
    }

    public void setOrarioInizio(Clock orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    public Clock getOrarioFine() {
        return orarioFine;
    }

    public void setOrarioFine(Clock orarioFine) {
        this.orarioFine = orarioFine;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }
}
