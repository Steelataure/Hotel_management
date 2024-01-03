package models;

import java.io.Serializable;
import java.util.List;

public class Facture implements Serializable {
    private Client client;
    private List<Repas> repasConsommes;
    private double fraisLogement;
    private double fraisRepas;

    public Facture(Client client, List<Repas> repasConsommes, double fraisLogement, double fraisRepas) {
        this.client = client;
        this.repasConsommes = repasConsommes;
        this.fraisLogement = fraisLogement;
        this.fraisRepas = fraisRepas;
    }

    public double getTotal() {
        return fraisLogement + fraisRepas;
    }
    public Client getClient() {
        return client;
    }
    public List<Repas> getRepasConsommes() {
        return repasConsommes;
    }
    public double getFraisLogement() {
        return fraisLogement;
    }
    public double getFraisRepas() {
        return fraisRepas;
    }

    @Override
    public String toString() {
        return "Facture{" +
                "client=" + client +
                ", repasConsommes=" + repasConsommes +
                ", fraisLogement=" + fraisLogement +
                ", fraisRepas=" + fraisRepas +
                '}';
    }
    
}
