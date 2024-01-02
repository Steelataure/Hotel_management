import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import models.Client;
import models.Repas;
import models.Chambre;
import models.Facture;
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

        // Ajout des chambres
        gestionHotel.ajouterChambre(new Chambre(1, "Simple", 50, 1));
        gestionHotel.ajouterChambre(new Chambre(2, "Simple", 50, 1));
        gestionHotel.ajouterChambre(new Chambre(3, "Double", 80, 2));
        gestionHotel.ajouterChambre(new Chambre(4, "Double", 80, 2));

        // Ajout des repas
        gestionHotel.ajouterRepas(new Repas(1, null, 9, "Petit-déjeuner Continental : Croissants, pain frais, confiture, beurre, café, thé, et jus d'orange."));
        gestionHotel.ajouterRepas(new Repas(2, null, 12, "Petit Déjeuner Américain : oeufs brouillés, bacon, saucisses, pancakes au sirop d'érable, café, et jus de fruits."));
        gestionHotel.ajouterRepas(new Repas(3, null, 18, "Buffet : Un assortiment de salades, charcuteries, plats chauds et desserts."));
        gestionHotel.ajouterRepas(new Repas(4, null, 20, "Repas traditionnel: Tajines kefta."));
        gestionHotel.ajouterRepas(new Repas(5, null, 25, "Gastronomique :  foie gras, sole meunière et crème brûlée."));
        gestionHotel.ajouterRepas(new Repas(6, null, 22, "Dîner Asiatique : Chicken Katsu accompagné de riz et de curry ainsi que son saké."));

        String nomClient = null;
        String prenomClient = null;
        Client client = null;


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
                    // Affichez les informations du client et de la chambre réservée
                    System.out.println(client);
                    System.out.println(chambreReservee);

                    
                    if (chambreReservee != null) {
                        try {
                            gestionHotel.effectuerReservation(client, chambreReservee);
                            System.out.println("Réservation effectuée avec succès pour " + nomClient + " " + prenomClient);
                            // FichierUtils.sauvegarderReservations(gestionHotel.getReservations(), FICHIER_RESERtranquiVATIONS);
                        } catch (ReservationException e) {
                            System.out.println("Erreur lors de la réservation : " + e.getMessage());
                        }
                    } else {
                        System.out.println("Aucune chambre disponible de ce type.");
                    }
                    FichierUtils.sauvegarderReservations(gestionHotel.getReservations(), "C:\\Users\\alexa\\OneDrive\\Bureau\\Hotel_management\\src\\data\\reservations.txt");
                    break;

                case 3:
                    System.out.println("Liste des réservations :");
                    gestionHotel.afficherListeReservations();
                    break;


                case 4:
                    System.out.println("Liste des repas disponibles :");
                    gestionHotel.afficherRepasDisponibles();
                    System.out.print("Choisissez un repas (entrez le numéro) : ");
                    int choixRepas = scanner.nextInt();
                    
                    System.out.print("Entrez le nom du client : ");
                    nomClient = scanner.next();
                    System.out.print("Entrez le prénom du client : ");
                    prenomClient = scanner.next();
                    client = new Client((int)(Math.random() * 10000), nomClient, prenomClient);
                
                    // Récupérer le repas choisi
                    Repas repasChoisi = gestionHotel.getRepasByNumero(choixRepas);  // Suppose que vous avez une méthode pour récupérer un repas par numéro.
                    
                    if (repasChoisi != null) {
                        gestionHotel.commanderRepas(client, repasChoisi);  // Utiliser l'objet Client initialisé
                        System.out.println("Repas commandé avec succès !");
                    } else {
                        System.out.println("Repas non trouvé.");
                    }
                    break;
        
                case 5:
                    

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
