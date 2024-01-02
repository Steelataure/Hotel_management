package models;

import java.io.Serializable;

public abstract class Hebergement implements Serializable {
    private int numero;
    private String type;
    private int prix;
    protected boolean estDisponible;

    public Hebergement(int numero, String type, int prix) {
        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.estDisponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return "Hebergement{" +
                "numero=" + numero +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                ", estDisponible=" + estDisponible +
                '}';
    }
}
