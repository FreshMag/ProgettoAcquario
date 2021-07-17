package model;

public class Vasca implements Insertable{
    private int numeroVasca;
    private String codiceSalone;
    private float dimensione;
    private String posizione;
    private boolean aperta;
    private String nome;

    public Vasca(int numeroVasca, String codiceSalone, float dimensione, String posizione, boolean aperta, String nome) {
        this.numeroVasca = numeroVasca;
        this.codiceSalone = codiceSalone;
        this.dimensione = dimensione;
        this.posizione = posizione;
        this.aperta = aperta;
        this.nome = nome;
    }

    @Override
    public String getAttributesForQuery() {
        return "vasca values ("+ numeroVasca + ",\'"
        + codiceSalone + "\',"
        + dimensione + ",\'"
        + posizione + "\',"
        + aperta + ",\'"
        + nome + "\');";
    }

    @Override
    public String getKey() {
        return numeroVasca + "," + codiceSalone;
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

    public float getDimensione() {
        return dimensione;
    }

    public void setDimensione(float dimensione) {
        this.dimensione = dimensione;
    }

    public String getPosizione() {
        return posizione;
    }

    public void setPosizione(String posizione) {
        this.posizione = posizione;
    }

    public boolean isAperta() {
        return aperta;
    }

    public void setAperta(boolean aperta) {
        this.aperta = aperta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
