package models;

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
    
    public List<Reservation> getReservations() {
        return reservations;
    }
    
    public List<Client> getClients() {
        return clients;
    }

    public void ajouterChambre(Chambre chambre) {
        chambres.add(chambre);
    }

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public void ajouterRepas(Repas repas) {
        repasDisponibles.add(repas);
    }

    public void afficherChambresDisponibles() {
        for (Chambre chambre : chambres) {
            System.out.println(chambre.toString());
        }
    }

    public Reservation rechercherReservationParNumero(int numeroReservation) {
        for (Reservation reservation : reservations) {
            if (reservation.getNumero() == numeroReservation) {
                return reservation;
            }
        }
        return null;
    }
    
    public Repas rechercherRepasParNumero(int numero) {
        for (Repas repas : repasDisponibles) {
            if (repas.getNumero() == numero) {
                return repas; 
            }
        }
        return null;
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

    public Chambre chercherChambreDisponible(String typeChambre) {
        for (Chambre chambre : chambres) {
            if (chambre.isEstDisponible() && chambre.getType().equalsIgnoreCase(typeChambre)) {
                return chambre;
            }
        }
        return null;
    }

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

    public Client chercherClient(String nom, String prenom) {
        for (Client client : clients) {
            if (client.getNom().equalsIgnoreCase(nom) && client.getPrenom().equalsIgnoreCase(prenom)) {
                return client;
            }
        }
        return null;
    }
    
    public void ajouterRepasAuClient(Client client, Repas repas) {
        if (client != null && repas != null) {
            client.ajouterRepasCommande(repas);
            System.out.println("Repas ajouté au client avec succès !");
        } else {
            System.out.println("Impossible d'ajouter le repas au client.");
        }
    }

}
