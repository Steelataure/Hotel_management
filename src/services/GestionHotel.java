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
    private List<Repas> repasDisponibles = new ArrayList<>();


    public GestionHotel(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void annulerReservation(Reservation reservation) {
        if (reservations.contains(reservation)) {
            reservations.remove(reservation);
            reservation.getChambre().setEstDisponible(true);
            System.out.println("La réservation a été annulée avec succès.");
        } else {
            System.out.println("La réservation n'a pas été trouvée.");
        }
    }
    
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
            System.out.println(chambre.toString());
        }
    }

    public void ajouterRepas(Repas repas) {
        repasDisponibles.add(repas);
    }

    public void genererFacture(Client client) {
        double totalChambres = 0.0;
        double totalRepas = 0.0;
    
        for (Chambre chambre : client.getChambresReservees()) {
            // Calculer le coût total des chambres réservées
            totalChambres += chambre.getPrix(); // Supposons que chaque chambre ait une méthode getPrix()
        }
    
        for (Repas repas : client.getRepasCommandes()) {
            // Calculer le coût total des repas commandés
            totalRepas += repas.getPrix(); // Supposons que chaque repas ait une méthode getPrix()
        }
    
        // Afficher la facture combinée
        System.out.println("Facture pour " + client.getNom() + " " + client.getPrenom());
        System.out.println("Coût total des chambres : " + totalChambres);
        System.out.println("Coût total des repas : " + totalRepas);
        System.out.println("Coût total à payer : " + (totalChambres + totalRepas));
    }
    

    public Reservation rechercherReservationParNumero(int numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumero() == numeroReservation) {
                return reservation;
            }
        }
        return null;  // Return null if reservation not found
    }
    
    public void ajouterRepasAReservation(int numeroReservation, Repas repas) {
        Reservation reservation = rechercherReservationParNumero(numeroReservation);
        if (reservation != null) {
            reservation.ajouterRepas(repas);
        } else {
            System.out.println("La réservation avec le numéro " + numeroReservation + " n'a pas été trouvée.");
        }
    }

    public Repas rechercherRepasParNumero(int numero) {
        for (Repas repas : repasDisponibles) { // Supposons que repasDisponibles est la liste où vous stockez les repas disponibles.
            if (repas.getNumero() == numero) { // Supposons que chaque repas ait une méthode getNumero() qui retourne son numéro.
                return repas; // Retournez le repas si le numéro correspond.
            }
        }
        return null; // Retournez null si aucun repas avec ce numéro n'est trouvé.
    }
    
    public void effectuerReservation(Client client, Chambre chambre) throws ReservationException {

        System.out.println("Début de la réservation pour le client : " + client.getNom() + client.getPrenom());
        
        if (chambre == null) {
            System.out.println("La chambre est null.");
            throw new IllegalArgumentException("La chambre ne peut pas être null.");
        }
        
        if (!chambre.isEstDisponible()) {
            System.out.println("La chambre n'est pas disponible.");
            throw new ReservationException("La chambre est déjà réservée.");
        }
        
        System.out.println("Création de la réservation...");
        
        Reservation reservation = new Reservation(client, chambre, null, null);
        reservations.add(reservation);
        chambre.setEstDisponible(false);
        
        System.out.println("Réservation effectuée avec succès pour le client : " + client.getNom() + client.getPrenom());
    }


    public void modifierReservation(Reservation ancienneReservation, Reservation nouvelleReservation) {
        if (reservations.contains(ancienneReservation)) {
            // Annuler l'ancienne réservation
            annulerReservation(ancienneReservation);
            try {
                effectuerReservation(nouvelleReservation.getClient(), nouvelleReservation.getChambre());
                System.out.println("La réservation a été modifiée avec succès.");
            } catch (ReservationException e) {
                System.out.println("Erreur lors de la modification de la réservation : " + e.getMessage());
            }
        } else {
            System.out.println("La réservation à modifier n'a pas été trouvée.");
        }
    }
    

    // Commander un repas pour un client
    public void commanderRepas(Client client, Repas repas) {
        System.out.println("Commande de repas pour " + client.getNom() + " : " + repas.getDescription());
        // Ici, vous pouvez ajouter d'autres fonctionnalités, comme la création d'une facture pour le repas.
    }

    public void afficherRepasDisponibles() {
        if (repasDisponibles.isEmpty()) {
            System.out.println("Aucun repas disponible pour le moment.");
        } else {
            System.out.println("Repas disponibles :");
            for (Repas repas : repasDisponibles) {
                System.out.println(repas.toString()); 
            }
        }
    }

    // Enregistrer la facture pour un client
    public void enregistrerFacture(Client client) {
        System.out.println("Facture enregistrée pour " + client.getNom());
        // Implémentation de l'enregistrement de facture
    }

    // Chercher une chambre disponible par type
    public Chambre chercherChambreDisponible(String typeChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.isEstDisponible() && chambre.getType().equalsIgnoreCase(typeChambre)) {
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Client chercherClient(String nom, String prenom) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nom) && client.getPrenom().equalsIgnoreCase(prenom)) {
                return client;
            }
        }
        return null;  // Retourne null si le client n'est pas trouvé
    }
    
    // Ajouter un repas à un client
    public void ajouterRepasAuClient(Client client, Repas repas) {
        if (client != null && repas != null) {
            client.ajouterRepasCommande(repas);
            System.out.println("Repas ajouté au client avec succès !");
        } else {
            System.out.println("Impossible d'ajouter le repas au client.");
        }
    }
    
    // Obtenir la liste des clients
    public List<Client> getClients() {
        return clients;
    }
    
    // Calculer le montant total à facturer à un client (cette méthode peut être simplifiée)
    public double calculerMontantTotalPourClient(Client client) {
        double montantTotal = 0.0;
        
        // Calculer le montant total des chambres réservées (ceci est un exemple simplifié)
        for (Chambre chambre : client.getChambresReservees()) {
            montantTotal += chambre.getPrix();  // Supposons que chaque chambre ait une méthode getPrix()
        }
        
        // Ajouter le montant total des repas commandés (ceci est un exemple simplifié)
        for (Repas repas : client.getRepasCommandes()) {
            montantTotal += repas.getPrix();  // Supposons que chaque repas ait une méthode getPrix()
        }
        
        return montantTotal;
    }
}
