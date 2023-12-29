package models;

import java.io.Serializable;

public class Chambre implements Serializable {
    private int numero;
    private String type;
    private boolean estDisponible;
    private double prix;

    public Chambre(int numero, String type, double prix) {
        this.numero = numero;
        this.type = type;
        this.estDisponible = true;
        this.prix = prix;
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
    public boolean isEstDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    public double getPrix() {
        return prix;
    }
    public void setPrix(double prix) {
        this.prix = prix;
    }
    @Override
    public String toString() {
        return "Chambre{" +
                "numero=" + numero +
                ", type='" + type + '\'' +
                ", estDisponible=" + estDisponible +
                ", prix=" + prix +
                '}';
    
                }

    public boolean estDisponible() {
        return false;
    }
}
