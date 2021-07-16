package model;

public class Ordine implements Insertable{
    private String codiceOrdine;
    private String dataOrdine;
    private String IDFornitore;

    public Ordine(String codiceOrdine, String date, String IDFornitore) {
        this.codiceOrdine = codiceOrdine;
        this.dataOrdine = date;
        this.IDFornitore = IDFornitore;
    }


    @Override
    public String getAttributesForQuery() {
        return "ordine values(" + codiceOrdine + ", "
                + dataOrdine + ", "
                + IDFornitore + ")";
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public String getDate() {
        return dataOrdine;
    }

    public void setDate(String date) {
        this.dataOrdine = date;
    }

    public String getIDFornitore() {
        return IDFornitore;
    }

    public void setIDFornitore(String IDFornitore) {
        this.IDFornitore = IDFornitore;
    }
}
