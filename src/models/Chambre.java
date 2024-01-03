package models;

public class Chambre extends Hebergement {

    private int capacite;

    public Chambre(int numero, String type, int prix, int capacite) {
        super(numero, type, prix);
        this.capacite = capacite;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String afficherDisponible(boolean estDisponible) {
        if (estDisponible == true) {
            return "Disponible";
        } else {
            return "Indisponible";
        }
    }


    @Override
    public String toString() {
        return getNumero() + " " + getType() + " " + " " + getCapacite() + "place(s) " + getPrix() + " euros [" + afficherDisponible(estDisponible) + "]";
    }

    public Object getId() {
        return null;
    }
}
