import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.Client;
import models.Repas;
import models.Chambre;
import models.Reservation;
import services.GestionHotel;
import services.ReservationException;
import services.FichierUtils;
import utils.Menu;

public class MainApp {

    public static void main(String[] args) {
        try {
            // Charger les réservations depuis le fichier
            List<Reservation> reservations = FichierUtils.chargerReservations("C:\\Users\\alexa\\OneDrive\\Bureau\\Hotel_management\\src\\data\\reservations.txt");
            if (reservations == null) {
                reservations = new ArrayList<>();  // Initialiser une nouvelle liste si elle est null
            }

            GestionHotel gestionHotel = new GestionHotel(reservations);

            // Ajout des chambres
            gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50, 1));
            gestionHotel.ajouterChambre(new Chambre(2, "Simple", 50, 1));
            gestionHotel.ajouterChambre(new Chambre(3, "Double", 80, 2));
            gestionHotel.ajouterChambre(new Chambre(4, "Double", 80, 2));

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
                        System.out.println("Liste des chambres :");
                        gestionHotel.afficherChambresDisponibles();
                        break;

                    case 2:
                        System.out.println("Effectuer une réservation :");
                        System.out.print("Entrez le nom du client : ");
                        nomClient = scanner.next();
                        System.out.print("Entrez le prénom du client : ");
                        prenomClient = scanner.next();
                        client = new Client((int)(Math.random() * 10000), nomClient, prenomClient);
                        System.out.print("Entrez le type de chambre (Simple/Double) : ");
                        String typeChambre = scanner.next().toLowerCase();
                        Chambre chambreReservee = gestionHotel.chercherChambreDisponible(typeChambre);

                        if (chambreReservee != null) {
                            gestionHotel.effectuerReservation(client, chambreReservee);
                            System.out.println("Réservation effectuée avec succès pour " + nomClient + " " + prenomClient);
                        } else {
                            System.out.println("Aucune chambre disponible de ce type.");
                        }
                        FichierUtils.sauvegarderReservations(gestionHotel.getReservations(), "C:\\Users\\alexa\\OneDrive\\Bureau\\Hotel_management\\src\\data\\reservations.txt");
                        break;

                    case 3:
                        gestionHotel.afficherListeReservations();
                        break;

                    case 4:
                        System.out.println("Liste des repas disponibles :");
                        gestionHotel.afficherRepasDisponibles();
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
