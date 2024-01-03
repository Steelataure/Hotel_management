package utils;

import java.util.Scanner;

public class Menu {
    public static void afficherMenuPrincipal() {
        System.out.println("----------------------------------------------------");
        System.out.println("Bienvenue dans l'application de gestion hôtelière.");
        System.out.println("1. Enregistrer un nouveau client :");
        System.out.println("2. Afficher les détails d0es chambres.");
        System.out.println("3. Réserver une chambre.");
        System.out.println("4. Liste des réservations.");
        System.out.println("5. Commander un repas");
        System.out.println("6. Afficher la facture actuelle");
        System.out.println("0. Quitter");
        System.out.println("----------------------------------------------------");

    }

    public static int lireChoixUtilisateur() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextInt();
        }
    }
}
