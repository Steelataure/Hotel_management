import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.Client;
import models.Facture;
import models.Repas;
import models.Chambre;
import models.Reservation;
import services.GestionHotel;
import services.ReservationException;
import services.FichierUtils;
import utils.Menu;


public class MainApp {

    public static void main(String[] args) {
        String file = "src\\data\\reservations.txt";
        try {
            // Charger les réservations depuis le fichier
            List<Reservation> reservations = FichierUtils.chargerReservations("src\\data\\reservations.txt");
            if (reservations == null) {
                reservations = new ArrayList<>();
            }

            GestionHotel gestionHotel = new GestionHotel(reservations);

            // Ajout des chambres
            gestionHotel.ajouterChambre(new Chambre(1, "Luxe_Simple", 250, 1));
            gestionHotel.ajouterChambre(new Chambre(2, "Luxe_Double", 400, 2));
            gestionHotel.ajouterChambre(new Chambre(3, "Normal_Simple", 50, 1));
            gestionHotel.ajouterChambre(new Chambre(4, "Normal_Double", 80, 2));

            // Ajout des repas
            gestionHotel.ajouterRepas(new Repas(1, null, 9, "Petit-déjeuner Continental"));
            gestionHotel.ajouterRepas(new Repas(2, null, 12, "Petit Déjeuner Américain"));
            gestionHotel.ajouterRepas(new Repas(3, null, 18, "Buffet"));
            gestionHotel.ajouterRepas(new Repas(4, null, 20, "Repas traditionnel"));
            gestionHotel.ajouterRepas(new Repas(5, null, 25, "Gastronomique"));
            gestionHotel.ajouterRepas(new Repas(6, null, 22, "Dîner Asiatique"));

            String nomClient;
            String prenomClient;
            Client client;

            int choix;
            Scanner scanner = new Scanner(System.in);


            do {
                Menu.afficherMenuPrincipal();
                choix = scanner.nextInt();

                switch (choix) {
                    case 1:
                        System.out.print("Entrez le nom du client : ");
                        nomClient = scanner.next();
                        System.out.print("Entrez le prénom du client : ");
                        prenomClient = scanner.next();
                        client = new Client((int)(Math.random() * 10000), nomClient, prenomClient);
                        gestionHotel.ajouterClient(client);
                        System.out.println("Client enregistré avec succès !");
                        break;
                    case 2:
                        System.out.println("Liste des chambres :");
                        gestionHotel.afficherChambresDisponibles();
                        break;

                    case 3:
                        System.out.println("Liste des réservations :");
                        gestionHotel.afficherListeReservations();
                        break;

                    case 4:
                        System.out.println("Effectuer une réservation :");
                        System.out.print("Entrez le nom du client : ");
                        nomClient = scanner.next();
                        System.out.print("Entrez le prénom du client : ");
                        prenomClient = scanner.next();
                        client = gestionHotel.chercherClient(nomClient, prenomClient);
                    
                        if (client == null) {
                            client = new Client((int) (Math.random() * 10000), nomClient, prenomClient);
                            gestionHotel.ajouterClient(client);
                        }
                    
                        System.out.print("Entrez le type de chambre (Luxe_Simple / Luxe_Double / Normal_Simple / Normal_Double) : ");
                        String typeChambre = scanner.next().toLowerCase();
                        Chambre chambreReservee = gestionHotel.chercherChambreDisponible(typeChambre);
                    
                        if (chambreReservee != null) {
                            gestionHotel.effectuerReservation(client, chambreReservee);
                            System.out.println("Réservation effectuée avec succès pour " + nomClient + " " + prenomClient);
                            double montantTotal = gestionHotel.calculerMontantTotalPourClient(client) - chambreReservee.getPrix();
                            gestionHotel.ajouterReservationAuClient(client, chambreReservee);

                            System.out.println("Facture totale après déduction : " + montantTotal + " euros");
                        } else {
                            System.out.println("Aucune chambre disponible de ce type.");
                        }
                        
                        FichierUtils.sauvegarderReservations(gestionHotel.getReservations(), file);
                        break;

                        case 5:
                        System.out.println("Entrez le numéro de réservation à modifier :");
                        int numeroReservationModif = scanner.nextInt();
                        Reservation reservationModif = gestionHotel.rechercherReservationParNumero(numeroReservationModif);
                        if (reservationModif != null) {
                            System.out.println("Que souhaitez-vous modifier ?");
                            System.out.println("1. Changer la chambre");
                            System.out.println("2. Changer la date");
                            int choixModification = scanner.nextInt();
                                                
                            switch (choixModification) {
                                case 1:
                                    System.out.print("Entrez le type de chambre (Luxe_Simple / Luxe_Double / Normal_Simple / Normal_Double) : ");
                                    String typeChambreModif = scanner.next().toLowerCase();
                                    Chambre chambreReserveeModif = gestionHotel.chercherChambreDisponible(typeChambreModif); 
                                                    
                                    if (chambreReserveeModif != null) {
                                    } else {
                                        System.out.println("Aucune chambre disponible de ce type.");
                                    }
                                    break;
                            }
                        } else {
                            System.out.println("Réservation non trouvée.");
                        }
                        break;
                    
                    case 6:
                        System.out.println("Entrez le numéro de réservation à annuler :");
                        int numeroReservation = scanner.nextInt();
                        Reservation reservation = gestionHotel.rechercherReservationParNumero(numeroReservation);
                        if (reservation != null) {
                            gestionHotel.annulerReservation(reservation);
                        } else {
                            System.out.println("Réservation non trouvée.");
                        }
                        break;
            
                        
                    case 7:
                        System.out.println("Choisissez un repas :");
                        gestionHotel.afficherRepasDisponibles();
                        int choixRepas = scanner.nextInt();
                    
                        System.out.println("Entrez le nom du client pour ajouter ce repas :");
                        nomClient = scanner.next();
                        System.out.println("Entrez le prénom du client :");
                        prenomClient = scanner.next();
                        client = gestionHotel.chercherClient(nomClient, prenomClient);
                    
                        if (client != null) {
                            Repas repasChoisi = gestionHotel.rechercherRepasParNumero(choixRepas);
                            if (repasChoisi != null) {
                                gestionHotel.ajouterRepasAuClient(client, repasChoisi);
                                System.out.println("Repas ajouté au client avec succès !");
                            } else {
                                System.out.println("Repas non trouvé. Veuillez réessayer.");
                            }
                        } else {
                            System.out.println("Client non trouvé. Veuillez réessayer.");
                        }
                        break;
                    
                    case 8:
                        // Afficher la facture actuelle et le montant total pour chaque client
                        System.out.println("Factures actuelles :");
                        for (Client c : gestionHotel.getClients()) {
                            System.out.println("Nom du client : " + c.getNom() + " " + c.getPrenom());
                            double montantTotal = gestionHotel.calculerMontantTotalPourClient(c);
                            System.out.println("Montant total à payer : " + montantTotal);
                            System.out.println("------");
                        }
                        break;
                    case 0:
                        System.out.println("Merci d'avoir utilisé notre application. Au revoir !");
                        break;

                    default:
                        System.out.println("Choix non valide. Veuillez réessayer.");
                        break;
                }
            } while (choix != 0);

            scanner.close();

        } catch (Exception e) {
            System.out.println("Une erreur s'est produite : " + e.getMessage());
            e.printStackTrace();
        }
    }
}