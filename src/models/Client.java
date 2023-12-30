package models;

import java.io.Serializable;

public class Client implements Serializable {
    private int clientID;
    private String nom;
    private String prenom;

    public Client(int clientID, String nom, String prenom) {
        this.clientID = clientID;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getClientID() {
        return this.clientID;
    }

    public String getContact() {
        return this.nom + " " + this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

}
