import java.io.*;

public class Catalogue {
    private Article[] articles;
    private int taille;
    private int capacite;
    private int[][] statistiques; 
    
    public Catalogue() {
        capacite = 10;
        articles = new Article[capacite];
        taille = 0;
        statistiques = new int[capacite][2];
    }
    
    public void ajouterArticle(Article article) {
        if (taille >= capacite) {
            agrandir();
        }
        articles[taille] = article;
        taille++;
    }
    
    private void agrandir() {
        capacite += 10;
        Article[] nouveauxArticles = new Article[capacite];
        System.arraycopy(articles, 0, nouveauxArticles, 0, taille);
        articles = nouveauxArticles;
        
        int[][] nouvellesStats = new int[capacite][2];
        System.arraycopy(statistiques, 0, nouvellesStats, 0, taille);
        statistiques = nouvellesStats;
    }
    
    public void afficher() {
        System.out.println("\n[CATALOGUE]");
        for (int i = 0; i < taille; i++) {
            System.out.println(articles[i]);
        }
    }
    
    public int trouverIndexParId(String id) {
        for (int i = 0; i < taille; i++) {
            if (articles[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    public Article trouverArticleParId(String id) {
        int index = trouverIndexParId(id);
        return index != -1 ? articles[index] : null;
    }
    
    public void chargerDepuisFichier(String chemin) {
        try (BufferedReader reader = new BufferedReader(new FileReader(chemin))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                try {
                    String[] parties = ligne.split("\\|");
                    if (parties.length == 4) {
                        String id = parties[0].trim();
                        String libelle = parties[1].trim();
                        int prix = Integer.parseInt(parties[2].trim());
                        int stock = Integer.parseInt(parties[3].trim());
                        
                        ajouterArticle(new Article(id, libelle, prix, stock));
                    }
                } catch (Exception e) {
                    System.out.println("Ligne mal formée ignorée: " + ligne);
                }
            }
            System.out.println("Catalogue chargé depuis: " + chemin);
        } catch (IOException e) {
            System.out.println("Erreur lecture fichier: " + e.getMessage());
        }
    }
    
    public void sauvegarderVersFichier(String chemin) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(chemin))) {
            for (int i = 0; i < taille; i++) {
                Article a = articles[i];
                writer.println(a.getId() + "|" + a.getLibelle() + "|" + 
                              a.getPrixCentimes() + "|" + a.getStock());
            }
            System.out.println("Catalogue sauvegardé dans: " + chemin);
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde fichier: " + e.getMessage());
        }
    }
    

    public int getTaille() {
        return taille;
    }
    

    public Article getArticle(int index) {
        if (index < 0 || index >= taille) {
            return null;
        }
        return articles[index];
    }
}