package models;

import java.io.Serializable;

public class Chambre implements Serializable {
    private int numero;
    private String type;
    private double prix;
    private boolean estDisponible;

    public Chambre(int numero, String type, double prix) {
        this.numero = numero;
        this.type = type;
        this.prix = prix;
        this.estDisponible = true;  // Par défaut, une chambre est disponible
    }

    public int getNumero() {
        return numero;
    }

    public String getType() {
        return type;
    }

    public double getPrix() {
        return prix;
    }

    public boolean estDisponible() {
        return estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return "Chambre{" +
                "numero=" + numero +
                ", type='" + type + '\'' +
                ", prix=" + prix +
                ", estDisponible=" + estDisponible +
                '}';
    }
}
