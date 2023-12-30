import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.Client;
import models.Chambre;
import models.Reservation;
import services.GestionHotel;
import services.ReservationException;
import services.FichierUtils;
import utils.Menu;

public class MainApp {
    // private static final String FICHIER_RESERVATIONS = "./data/reservations.txt";

    public static void main(String[] args) {
        // List<Reservation> reservations = FichierUtils.chargerReservations(FICHIER_RESERVATIONS);
        // GestionHotel gestionHotel = new GestionHotel(reservations);

        GestionHotel gestionHotel = new GestionHotel(new ArrayList<>());

        gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50.0));
        gestionHotel.ajouterChambre(new Chambre(2, "Simple", 50.0));
        gestionHotel.ajouterChambre(new Chambre(3, "Double", 80.0));
        gestionHotel.ajouterChambre(new Chambre(4, "Double", 80.0));

        int choix;
        Scanner scanner = new Scanner(System.in);

        do {
            Menu.afficherMenuPrincipal();
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Liste des chambres disponibles :");
                    gestionHotel.afficherChambresDisponibles();
                    break;
                case 2:
                    System.out.println("Effectuer une réservation :");
                    System.out.print("Entrez le nom du client : ");
                    String nomClient = scanner.next();
                    System.out.print("Entrez le type de chambre (Simple/Double) : ");
                    String typeChambre = scanner.next();
                    
                    Client client = new Client(1, nomClient, nomClient);
                    Chambre chambreReservee = gestionHotel.chercherChambreDisponible(typeChambre);
                    System.out.println(client);
                    System.out.println(chambreReservee);

                    if (chambreReservee != null) {
                        try {
                            gestionHotel.effectuerReservation(client, chambreReservee);
                            System.out.println("Réservation effectuée avec succès pour " + nomClient);
                            // FichierUtils.sauvegarderReservations(gestionHotel.getReservations(), FICHIER_RESERVATIONS);
                        } catch (ReservationException e) {
                            System.out.println("Erreur lors de la réservation : " + e.getMessage());
                        }
                    } else {
                        System.out.println("Aucune chambre disponible de ce type.");
                    }
                    break;
                case 3:
                    System.out.println("Liste des réservations :");
                    gestionHotel.afficherListeReservations();
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
    }
}
