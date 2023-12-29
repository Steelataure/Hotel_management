package services;

import models.Client;
import models.Chambre;
import models.Reservation;
import models.Repas;

import java.util.ArrayList;
import java.util.List;

public class GestionHotel {
    private List<Client> clients = new ArrayList<>();
    private List<Chambre> chambres = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();

    // Ajouter une chambre
    public void ajouterChambre(Chambre chambre) {
        chambres.add(chambre);
    }

    // Ajouter un client
    public void ajouterClient(Client client) {
        clients.add(client);
    }

    // Afficher les chambres disponibles
    public void afficherChambresDisponibles() {
        for (Chambre chambre : chambres) {
            if (chambre.estDisponible()) {
                System.out.println(chambre.toString());
            }
        }
    }

    // Effectuer une réservation
    public void effectuerReservation(Client client, Chambre chambre) throws ReservationException {
        if (!chambre.estDisponible()) {
            throw new ReservationException("La chambre est déjà réservée.");
        }
        Reservation reservation = new Reservation(client, chambre, null, null);
        reservations.add(reservation);
        chambre.setEstDisponible(false);
    }

    // Modifier une réservation
    public void modifierReservation(Reservation ancienneReservation, Reservation nouvelleReservation) {
        // Implémentation de la modification de réservation
    }

    // Annuler une réservation
    public void annulerReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            reservation.getChambre().setEstDisponible(true);
        }
    }

    // Commander un repas pour un client
    public void commanderRepas(Client client, Repas repas) {
        System.out.println("Repas commandé pour " + client.getNom());
        // Implémentation de la commande de repas
    }

    // Enregistrer la facture pour un client
    public void enregistrerFacture(Client client) {
        System.out.println("Facture enregistrée pour " + client.getNom());
        // Implémentation de l'enregistrement de facture
    }

    // Chercher une chambre disponible par type
    public Chambre chercherChambreDisponible(String typeChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.estDisponible() && chambre.getType().equalsIgnoreCase(typeChambre)) {
                return chambre;
            }
        }
        return null;
    }

    // Chercher un client par nom
    public Client chercherClient(String nomClient) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nomClient)) {
                return client;
            }
        }
        return null;
    }

    
    // Afficher la liste des réservations
    public void afficherListeReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation enregistrée.");
        } else {
            System.out.println("Liste des réservations :");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }
}
