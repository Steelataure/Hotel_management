package models;

import java.io.Serializable;

public class Repas implements Serializable {
    private int prix;
    private int quantite;
    private String type;
    private int numero;
    private boolean estDisponible;

    public Repas(int prix, int quantite, String type, int numero) {
        this.prix = prix;
        this.quantite = quantite;
        this.type = type;
        this.numero = numero;
        this.estDisponible = true; // Par défaut, un repas est disponible
    }

    public int getPrix() {
        return this.prix;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public String getType() {
        return this.type;
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean estDisponible() {
        return this.estDisponible;
    }

    public void setEstDisponible(boolean estDisponible) {
        this.estDisponible = estDisponible;
    }

    @Override
    public String toString() {
        return numero + " " + type + " " + prix + " euros";
    }
    
}
