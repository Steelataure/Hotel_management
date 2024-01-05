package models;

import java.io.*;
import java.util.List;

public class FichierUtils {

    private static final String DEFAULT_PATH = "src/data/reservations.txt";

    public static void sauvegarderReservations(List<Reservation> reservations, String nomFichier) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(reservations);
            System.out.println("Réservations sauvegardées avec succès !");
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Reservation> chargerReservations(String nomFichier) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            marquerChambresReservees(reservations);
            return reservations;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
            return null;
        }
    }

    public static List<Reservation> chargerReservationsParDefaut() {
        return chargerReservations(DEFAULT_PATH);
    }

    public static boolean fichierExiste(String nomFichier) {
        return new File(nomFichier).exists();
    }

    public static void supprimerFichier(String nomFichier) {
        if (fichierExiste(nomFichier)) {
            if (new File(nomFichier).delete()) {
                System.out.println("Fichier supprimé avec succès !");
            } else {
                System.out.println("Erreur lors de la suppression du fichier.");
            }
        } else {
            System.out.println("Le fichier n'existe pas.");
        }
    }

    private static void marquerChambresReservees(List<Reservation> reservations) {
        reservations.forEach(reservation -> {
            Chambre chambre = reservation.getChambre();
            if (chambre != null) {
                chambre.setEstDisponible(false);
            }
        });
    }
}
