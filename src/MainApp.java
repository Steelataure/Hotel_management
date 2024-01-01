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
    // private static final String FICHIER_RESERVATIONS = "./data/reservations.txt";

    public static void main(String[] args) {
        // List<Reservation> reservations = FichierUtils.chargerReservations(FICHIER_RESERVATIONS);
        // GestionHotel gestionHotel = new GestionHotel(reservations);

        GestionHotel gestionHotel = new GestionHotel(new ArrayList<>());

        gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50));
        gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50));
        gestionHotel.ajouterChambre(new Chambre(1, "Double", 80));
        gestionHotel.ajouterChambre(new Chambre(1, "Double", 80 ));

        gestionHotel.ajouterRepas(new Repas(9, 1,"Petit-déjeuner Continental : Croissants, pain frais, confiture, beurre, café, thé, et jus d'orange.",1));
        gestionHotel.ajouterRepas(new Repas(12,1,"Petit Déjeuner Américain : oeufs brouillés, bacon, saucisses, pancakes au sirop d'érable, café, et jus de fruits.",2));
        gestionHotel.ajouterRepas(new Repas(18, 1,"Buffet : Un assortiment de salades, charcuteries, plats chauds et desserts.",3));
        gestionHotel.ajouterRepas(new Repas(20,1,"Repas traditionnel: Tajines kefta.", 4));
        gestionHotel.ajouterRepas(new Repas(25, 1,"Gastronomique :  foie gras, sole meunière et crème brûlée.", 5));
        gestionHotel.ajouterRepas(new Repas(22,1,"Dîner Asiatique : Chicken Katsu accompagné de riz et de curry ainsi que son saké.", 6));

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

                System.out.print("Entrez le prénom du client : ");
                String prenomClient = scanner.next();  // Ajout de la saisie du prénom

                int clientID = (int)(Math.random() * 10000);  // Génération d'un ID client aléatoire
                Client client = new Client(clientID, nomClient, prenomClient);  // Création d'un nouveau client

                System.out.print("Entrez le type de chambre (Simple/Double) : ");
                String typeChambre = scanner.next().toLowerCase();

                Chambre chambreReservee = gestionHotel.chercherChambreDisponible(typeChambre);
                // Affichez les informations du client et de la chambre réservée
                System.out.println(client);
                System.out.println(chambreReservee);

                
                if (chambreReservee != null) {
                    try {
                        gestionHotel.effectuerReservation(client, chambreReservee);
                        System.out.println("Réservation effectuée avec succès pour " + nomClient + prenomClient);
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
                case 4: 
                    System.out.println("Choisissez un repas :");
                    gestionHotel.afficherRepasDisponibles();
                    int choixRepas  = scanner.nextInt();

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
