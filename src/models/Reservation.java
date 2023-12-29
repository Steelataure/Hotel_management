package models;
import java.io.Serializable;
import java.util.Date;

public class Reservation implements Serializable {
    private Client client;
    private Chambre chambre;
    private Date dateDebut;
    private Date dateFin;

    public Reservation(Client client, Chambre chambre, Date dateDebut, Date dateFin) {
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return this.client;
    }
    public Chambre getChambre() {
        return this.chambre;
    }
    public Date getDateDebut() {
        return this.dateDebut;
    }
    public Date getDateFin() {
        return this.dateFin;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "Client=" + client.getNom() + " " + client.getPrenom() +
                ", Chambre=" + chambre.getType() +
                ", Date début=" + dateDebut +
                ", Date fin=" + dateFin +
                // Vous pouvez ajouter d'autres attributs comme le repas, le prix, etc.
                '}';
    }
        
}