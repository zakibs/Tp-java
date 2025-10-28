import java.util.Scanner;
import java.io.File;

public class Util {
    public static int saisirEntier(Scanner scanner, String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Quantité invalide (non numérique). Réessayez.");
            }
        }
    }
    
    public static String saisirCheminFichier(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String chemin = scanner.nextLine();
            File file = new File(chemin);
            if (file.exists() && file.canRead()) {
                return chemin;
            } else {
                System.out.println("Chemin introuvable : " + chemin + " --- saisissez un autre chemin.");
            }
        }
    }
}