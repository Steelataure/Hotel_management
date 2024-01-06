package models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reservation implements Serializable {
    private static int nextReservationNumber = 1;
    private Client client;
    private Chambre chambre;
    private Date dateDebut;
    private Date dateFin;
    private List<Repas> repas = new ArrayList<>();
    private int numero;
    private int dureeSejour;


    public Reservation(Client client, Chambre chambre, Date dateDebut, Date dateFin, int dureeSejour){
        this.numero = nextReservationNumber++;
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dureeSejour = dureeSejour;
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
    public int getNumero() {
        return this.numero;
    }
    public void ajouterRepas(Repas repasChoisi) {
        this.repas.add(repasChoisi);
    }

    public int getDuration() {
        return this.dureeSejour;
    }
    

    @Override
    public String toString() {
        return "Reservation | " +
                "Numero : " + chambre.getNumero() +
                " Client : " + client.getNom() + " " + client.getPrenom() +
                ", Chambre : " + chambre.getType() + ", Durée : " + getDuration() +
                '}';
    }
}