package model;

public class Esemplare {
    private String codiceEsemplare;
    private String luogoNascita;
    private char sesso;
    private float peso;
    private float quantitàMangimeHg;
    private String nomeScientifico;
    private int numeroVasca;
    private String codiceSalone;

    public Esemplare(String codiceEsemplare, String luogoNascita, char sesso, float peso,
                     float quantitàMangimeHg, String nomeScientifico, int numeroVasca, String codiceSalone) {
        this.codiceEsemplare = codiceEsemplare;
        this.luogoNascita = luogoNascita;
        this.sesso = sesso;
        this.peso = peso;
        this.quantitàMangimeHg = quantitàMangimeHg;
        this.nomeScientifico = nomeScientifico;
        this.numeroVasca = numeroVasca;
        this.codiceSalone = codiceSalone;
    }

    public String getCodiceEsemplare() {
        return codiceEsemplare;
    }

    public void setCodiceEsemplare(String codiceEsemplare) {
        this.codiceEsemplare = codiceEsemplare;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public char getSesso() {
        return sesso;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getQuantitàMangimeHg() {
        return quantitàMangimeHg;
    }

    public void setQuantitàMangimeHg(float quantitàMangimeHg) {
        this.quantitàMangimeHg = quantitàMangimeHg;
    }

    public String getNomeScientifico() {
        return nomeScientifico;
    }

    public void setNomeScientifico(String nomeScientifico) {
        this.nomeScientifico = nomeScientifico;
    }

    public int getNumeroVasca() {
        return numeroVasca;
    }

    public void setNumeroVasca(int numeroVasca) {
        this.numeroVasca = numeroVasca;
    }

    public String getCodiceSalone() {
        return codiceSalone;
    }

    public void setCodiceSalone(String codiceSalone) {
        this.codiceSalone = codiceSalone;
    }
}
