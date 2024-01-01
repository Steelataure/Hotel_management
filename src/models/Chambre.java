package models;

import java.io.Serializable;

public class Chambre implements Serializable {
    private int numero;
    private String type;
    private int prix;
    private boolean estDisponible;

    public Chambre(int numero, String type, int prix) {
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

    public int getPrix() {
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
        return numero + " " + type + " " + prix + " euros";
    }
}
