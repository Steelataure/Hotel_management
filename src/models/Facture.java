package models;

import java.io.Serializable;
import java.util.List;

public class Facture implements Serializable {
    private Reservation reservation;
    private double montantTotal;
    private List<Repas> repas;

    public Facture(Reservation reservation, double montantTotal, List<Repas> repas) {
        this.reservation = reservation;
        this.montantTotal = montantTotal;
        this.repas = repas;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public double getMontantTotal() {
        return this.montantTotal;
    }
    public List<Repas> getRepas() {
        return this.repas;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    public void setMontantTotal(double montantTotal) {
        this.montantTotal = montantTotal;
    }

}
