package src;
class Repas{
    private int prix;
    private int quantite;
    private String type;

    Repas(int prix, int quantite, String type) {
        this.prix = prix;
        this.quantite = quantite;
        this.type = type;
    }

    int getPrix() {
        return this.prix;
    }

    int getQuantite() {
        return this.quantite;
    }

    String getType() {
        return this.type;
    }  
}