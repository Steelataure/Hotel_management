package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private int clientID;
    private String nom;
    private String prenom;
    private List<Chambre> chambresReservees;
    private List<Repas> repasCommandes;  

    public Client(int clientID, String nom, String prenom) {
        this.clientID = clientID;
        this.nom = nom;        
        this.prenom = prenom;     
        this.chambresReservees = new ArrayList<>(); 
        this.repasCommandes = new ArrayList<>();  
    }

    public int getClientID() {
        return clientID;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void ajouterChambreReservee(Chambre chambre) {
        chambresReservees.add(chambre);
    }

    public void ajouterRepasCommande(Repas repas) {
        repasCommandes.add(repas);
    }

    public List<Chambre> getChambresReservees() {
        return chambresReservees;
    }

    public List<Repas> getRepasCommandes() {
        return repasCommandes;
    }
    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
