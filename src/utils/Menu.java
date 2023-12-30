package utils;

import java.util.Scanner;

public class Menu {
    public static void afficherMenuPrincipal() {
        
        System.out.println("Bienvenue dans l'application de gestion hôtelière.");
        System.out.println("1. Afficher les détails des chambres.");
        System.out.println("2. Réserver une chambre.");
        System.out.println("0. Quitter.");
        System.out.println("----------------------------------------------------");

    }

    public static int lireChoixUtilisateur() {
        try (Scanner scanner = new Scanner(System.in)) {
            return scanner.nextInt();
        }
    }
}
