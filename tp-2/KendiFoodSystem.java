import java.util.Scanner;

public class KendiFoodSystem {
    private Catalogue catalogue;
    private Panier panier;
    private GestionCodesReduction gestionCodes;
    
    public KendiFoodSystem() {
        catalogue = new Catalogue();
        panier = new Panier();
        gestionCodes = new GestionCodesReduction();
        initialiserDonneesTest();
    }
    
    private void initialiserDonneesTest() {
        try {
            
            catalogue.ajouterArticle(new Article("KIT_BOL1", "Bol végétarien", 5990, 12));
            catalogue.ajouterArticle(new Article("TOMATE3", "Tomates x3", 990, 40));
            
            
            gestionCodes.ajouterCode(new CodeReduction("KENDI10", 10));
            gestionCodes.ajouterCode(new CodeReduction("KENDI20", 20));
        } catch (KendiFoodException e) {
            System.out.println("Erreur initialisation: " + e.getMessage());
        }
    }
    
    public void genererRecu(String codeOptionnel) {
        StringBuilder recu = new StringBuilder();
        
        recu.append("===== REÇU KendiFood =====\n");
        
        for (int i = 0; i < panier.getTaille(); i++) {
            LignePanier ligne = panier.getLigne(i);
            recu.append(String.format("- %s x%d -> %d cts\n", 
                ligne.getArticle().getId(), 
                ligne.getQuantite(), 
                ligne.getSousTotal()));
        }
        
        int totalBrut = panier.totalBrut();
        recu.append("Total brut : ").append(totalBrut).append(" cts\n");
        
        if (codeOptionnel != null && !codeOptionnel.isEmpty()) {
            try {
                int totalNet = gestionCodes.appliquerCode(codeOptionnel, totalBrut);
                CodeReduction codeReduction = gestionCodes.trouverCode(codeOptionnel);
                recu.append("Code appliqué : ").append(codeOptionnel)
                    .append(" (-").append(codeReduction.getPourcentage()).append("%)\n")
                    .append("Total à payer : ").append(totalNet).append(" cts\n");
            } catch (KendiFoodException e) {
                recu.append("Code invalide, total à payer : ").append(totalBrut).append(" cts\n");
            }
        } else {
            recu.append("Total à payer : ").append(totalBrut).append(" cts\n");
        }
        
        System.out.println(recu.toString());
    }
    
    public static void main(String[] args) {
        KendiFoodSystem system = new KendiFoodSystem();
        Scanner scanner = new Scanner(System.in);
        
        boolean continuer = true;
        while (continuer) {
            System.out.println("\n=== KENDIFOOD SYSTEM ===");
            System.out.println("1. Afficher catalogue");
            System.out.println("2. Afficher panier");
            System.out.println("3. Ajouter au panier");
            System.out.println("4. Supprimer du panier");
            System.out.println("5. Générer reçu");
            System.out.println("6. Charger catalogue fichier");
            System.out.println("7. Sauvegarder catalogue");
            System.out.println("8. Quitter");
            System.out.print("Choix: ");
            
            String choix = scanner.nextLine();
            
            try {
                switch (choix) {
                    case "1":
                        system.catalogue.afficher();
                        break;
                        
                    case "2":
                        system.panier.afficher();
                        break;
                        
                    case "3":
                        System.out.print("ID article: ");
                        String idAjout = scanner.nextLine();
                        Article article = system.catalogue.trouverArticleParId(idAjout);
                        if (article == null) {
                            System.out.println("Article non trouvé");
                        } else {
                            int qte = Util.saisirEntier(scanner, "Quantité: ");
                            system.panier.ajouterLigne(article, qte);
                            System.out.println("Article ajouté au panier");
                        }
                        break;
                        
                    case "4":
                        System.out.print("ID article à supprimer: ");
                        String idSupp = scanner.nextLine();
                        if (system.panier.supprimerLigne(idSupp)) {
                            System.out.println("Article supprimé du panier");
                        } else {
                            System.out.println("Article non trouvé dans le panier");
                        }
                        break;
                        
                    case "5":
                        System.out.print("Code réduction (enter pour ignorer): ");
                        String code = scanner.nextLine();
                        if (code.isEmpty()) code = null;
                        system.genererRecu(code);
                        break;
                        
                    case "6":
                        String cheminChargement = Util.saisirCheminFichier(scanner, 
                            "Chemin fichier catalogue: ");
                        system.catalogue.chargerDepuisFichier(cheminChargement);
                        break;
                        
                    case "7":
                        System.out.print("Chemin sauvegarde: ");
                        String cheminSauvegarde = scanner.nextLine();
                        system.catalogue.sauvegarderVersFichier(cheminSauvegarde);
                        break;
                        
                    case "8":
                        continuer = false;
                        break;
                        
                    default:
                        System.out.println("Choix invalide");
                }
            } catch (KendiFoodException e) {
                System.out.println("Erreur: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erreur inattendue: " + e.getMessage());
            }
        }
        
        scanner.close();
        System.out.println("Merci d'avoir utilisé KendiFood!");
    }
}
