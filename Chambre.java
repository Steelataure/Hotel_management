class Chambre{
    private int numero_chambre;
    private String type;
    private boolean isdisponible;
    private int prix;

    Chambre(int numero_chambre, String type, boolean isdisponible, int prix){
        this.numero_chambre = numero_chambre;
        this.type = type;
        this.isdisponible = isdisponible;
        this.prix = prix;
    }
    int getNumero_chambre() {
        return this.numero_chambre;
    }
    String getType() {
        return this.type;
    }
    int getPrix() {
        return this.prix;
    }
    boolean getIsdisponible() {
        return this.isdisponible;
    }
}