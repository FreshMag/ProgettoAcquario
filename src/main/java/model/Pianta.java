package model;

public class Pianta implements Insertable, Deletable{
    private String codiceVegetale;
    private float pesoKg;
    private String nomeScientifico;
    private int numeroVasca;
    private String codiceSalone;

    public Pianta(String codiceVegetale, float pesoKg, String nomeScientifico, int numeroVasca, String codiceSalone) {
        this.codiceVegetale = codiceVegetale;
        this.pesoKg = pesoKg;
        this.nomeScientifico = nomeScientifico;
        this.numeroVasca = numeroVasca;
        this.codiceSalone = codiceSalone;
    }

    public Pianta(String codiceVegetale) {
        this.codiceVegetale = codiceVegetale;
    }

    public String getCodiceVegetale() {
        return codiceVegetale;
    }

    public void setCodiceVegetale(String codiceVegetale) {
        this.codiceVegetale = codiceVegetale;
    }

    public float getPesoKg() {
        return pesoKg;
    }

    public void setPesoKg(float pesoKg) {
        this.pesoKg = pesoKg;
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

    @Override
    public String getAttributesForQuery() {
        return "pianta values(" + codiceVegetale + ", "
        + pesoKg + ", "
        + nomeScientifico + ", "
        + numeroVasca + ", "
        + codiceSalone + ")";
    }

    @Override
    public String getStringForQuery() {
        return "pianta where CodiceVegetale = " + codiceVegetale;
    }
}
