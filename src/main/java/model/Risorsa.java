package model;

public class Risorsa implements Insertable{
    private String codiceOrdine;
    private int quantita;
    private String nome;
    private float costoSingolo;
    private String tipologia;
    private String marca;
    private int numeroVasca;
    private String codiceSalone;

    public Risorsa(String codiceOrdine, int quantita, String nome, float costoSingolo, String tipologia, String marca, int numeroVasca, String codiceSalone) {
        this.codiceOrdine = codiceOrdine;
        this.quantita = quantita;
        this.nome = nome;
        this.costoSingolo = costoSingolo;
        this.tipologia = tipologia;
        this.marca = marca;
        this.numeroVasca = numeroVasca;
        this.codiceSalone = codiceSalone;
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getCostoSingolo() {
        return costoSingolo;
    }

    public void setCostoSingolo(float costoSingolo) {
        this.costoSingolo = costoSingolo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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

    @Override
    public String getAttributesForQuery() {
        return "risorsa values(\'" + codiceOrdine + "\', "
        + quantita + ",\' "
        + nome + "\', "
        + costoSingolo + ",\' "
        + tipologia+ "\',\' "
        + marca + "\', "
        + (numeroVasca == 0 ? "null" : numeroVasca) + "," + (codiceSalone.equals("null") ? "null);" : "\'" + codiceSalone + "\');");
    }

    @Override
    public String getKey() {
        return codiceOrdine;
    }
}
