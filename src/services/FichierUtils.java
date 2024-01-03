package services;

import java.io.*;
import java.util.List;

import models.Chambre;
import models.Reservation;

public class FichierUtils {

    // Chemin par défaut pour sauvegarder le fichier
    private static final String CHEMIN_PAR_DEFAUT = "src\\data\\reservations.txt";

    // Sauvegarder les réservations dans un fichier
    public static void sauvegarderReservations(List<Reservation> reservations, String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(reservations);
            System.out.println("Les réservations ont été sauvegardées avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des réservations : " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    @SuppressWarnings("unchecked")
    public static List<Reservation> chargerReservations(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            
            for (Reservation reservation : reservations) {
                Chambre chambreReservee = reservation.getChambre();
                if (chambreReservee != null) {
                    chambreReservee.setEstDisponible(false);
                }
            }
            
            return reservations;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement des réservations : " + e.getMessage());
            return null;
        }
    }


    // Méthode pour charger les réservations avec un chemin par défaut
    public static List<Reservation> chargerReservationsParDefaut() {
        return chargerReservations(CHEMIN_PAR_DEFAUT);
    }

    // Méthode pour vérifier si un fichier existe
    public static boolean fichierExiste(String nomFichier) {
        File file = new File(nomFichier);
        return file.exists();
    }

    // Méthode pour supprimer un fichier de sauvegarde
    public static void supprimerFichier(String nomFichier) {
        File file = new File(nomFichier);
        if (file.exists()) {
            file.delete();
            System.out.println("Fichier " + nomFichier + " supprimé avec succès !");
        } else {
            System.out.println("Le fichier " + nomFichier + " n'existe pas.");
        }
    }
}
