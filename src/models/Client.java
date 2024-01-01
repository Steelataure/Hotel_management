package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Client implements Serializable {
    private int clientID;
    private String nom;
    private String prenom;
    private List<Chambre> chambresReservees; // Liste pour stocker les chambres réservées
    private List<Repas> repasCommandes;      // Liste pour stocker les repas commandés

    public Client(int clientID, String nom, String prenom) {
        this.clientID = clientID; // Identifiant unique du client
        this.nom = nom;           // Nom du client
        this.prenom = prenom;     // Prénom du client
        this.chambresReservees = new ArrayList<>(); // Initialisation de la liste des chambres
        this.repasCommandes = new ArrayList<>();    // Initialisation de la liste des repas
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

    // Méthodes pour ajouter une chambre réservée et un repas commandé
    public void ajouterChambreReservee(Chambre chambre) {
        chambresReservees.add(chambre);
    }

    public void ajouterRepasCommande(Repas repas) {
        repasCommandes.add(repas);
    }

    // Méthodes pour obtenir les listes des chambres et repas
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
