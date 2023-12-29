

import java.util.Scanner;  // Importez la classe Scanner
import models.Client;      // Importez la classe Client
import services.GestionHotel;  // Importez la classe GestionHotel
import services.ReservationException;  // Importez la classe ReservationException
import utils.Menu;         // Importez la classe Menu
import models.Chambre;  


public class MainApp {
    public static void main(String[] args) {
        GestionHotel gestionHotel = new GestionHotel();

        gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50.0));
        gestionHotel.ajouterChambre(new Chambre(2, "Double", 80.0));

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
                    
                    Client client = new Client(choix, nomClient, nomClient);  // Utilisez le bon constructeur
                    Chambre chambreReservee = gestionHotel.chercherChambreDisponible(typeChambre);
                    
                    if (chambreReservee != null) {
                        try {
                            gestionHotel.effectuerReservation(client, chambreReservee);
                            System.out.println("Réservation effectuée avec succès pour " + nomClient);
                        } catch (ReservationException e) {
                            System.out.println("Erreur lors de la réservation : " + e.getMessage());
                        }
                    } else {
                        System.out.println("Aucune chambre disponible de ce type.");
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
    }
}