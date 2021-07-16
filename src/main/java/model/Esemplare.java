package model;

public class Esemplare {
	//IMPORTANTE: HO CAMBIATO SESSO IN STRING, PERCHE CHAR NON MI FUNZIONAVA//
    private String codiceEsemplare;
    private String luogoNascita;
    private String sesso;
    private float peso;
    private float quantitaMangimeHg;
    private String nomeScientifico;
    private int numeroVasca;
    private String codiceSalone;

    public Esemplare(String codiceEsemplare, String luogoNascita, String sesso, float peso,
                     float quantitaMangimeHg, String nomeScientifico, int numeroVasca, String codiceSalone) {
        this.codiceEsemplare = codiceEsemplare;
        this.luogoNascita = luogoNascita;
        this.sesso = sesso;
        this.peso = peso;
        this.quantitaMangimeHg = quantitaMangimeHg;
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

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getQuantitaMangimeHg() {
        return quantitaMangimeHg;
    }

    public void setQuantitaMangimeHg(float quantitaMangimeHg) {
        this.quantitaMangimeHg = quantitaMangimeHg;
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

    /**
     * Metodo per ottenere la stringa che mi dica tutti gli attributi da inserire nella query 
     * @return null
     */
	public String getAttributesForQuery() {
		return new String("values(" + ")");
	}
}
