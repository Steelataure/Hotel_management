package models;

public class Repas extends Hebergement {

    private String description;

    public Repas(int numero, String type, int prix, String description) {
        super(numero, type, prix);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getNumero() + " " + getDescription() + " " + getPrix() + " euros";
    }
}
