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
        return "ordine values(\'" + codiceOrdine + "\', \'"
                + dataOrdine + "\', \'"
                + IDFornitore + "\');";
    }

    @Override
    public String getKey() {
        return codiceOrdine;
    }

    public String getCodiceOrdine() {
        return codiceOrdine;
    }

    public void setCodiceOrdine(String codiceOrdine) {
        this.codiceOrdine = codiceOrdine;
    }

    public String getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(String date) {
        this.dataOrdine = date;
    }

    public String getIDFornitore() {
        return IDFornitore;
    }

    public void setIDFornitore(String IDFornitore) {
        this.IDFornitore = IDFornitore;
    }
}
