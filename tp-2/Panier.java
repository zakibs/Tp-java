public class Panier {
    private LignePanier[] lignes;
    private int taille;
    private int capacite;
    
    public Panier() {
        capacite = 5;
        lignes = new LignePanier[capacite];
        taille = 0;
    }
    
    private void agrandir() {
        capacite += 5;
        LignePanier[] nouveauPanier = new LignePanier[capacite];
        System.arraycopy(lignes, 0, nouveauPanier, 0, taille);
        lignes = nouveauPanier;
    }
    
    public void ajouterLigne(Article article, int quantite) throws KendiFoodException {
        
        for (int i = 0; i < taille; i++) {
            if (lignes[i].getArticle().getId().equals(article.getId())) {
                
                int nouvelleQuantite = lignes[i].getQuantite() + quantite;
                article.decrementerStock(quantite); 
                lignes[i].setQuantite(nouvelleQuantite);
                return;
            }
        }
        
        
        if (taille >= capacite) {
            agrandir();
        }
        
        article.decrementerStock(quantite);
        lignes[taille] = new LignePanier(article, quantite);
        taille++;
    }
    
    public boolean supprimerLigne(String id) {
        for (int i = 0; i < taille; i++) {
            if (lignes[i].getArticle().getId().equals(id)) {
                
                lignes[i].getArticle().incrementerStock(lignes[i].getQuantite());
                
                
                for (int j = i; j < taille - 1; j++) {
                    lignes[j] = lignes[j + 1];
                }
                lignes[taille - 1] = null;
                taille--;
                return true;
            }
        }
        return false;
    }
    
    public int totalBrut() {
        int total = 0;
        for (int i = 0; i < taille; i++) {
            total += lignes[i].getSousTotal();
        }
        return total;
    }
    
    public void afficher() {
        System.out.println("\n[PANIER]");
        if (taille == 0) {
            System.out.println("Le panier est vide");
            return;
        }
        
        for (int i = 0; i < taille; i++) {
            System.out.println(lignes[i]);
        }
        System.out.println("Total brut : " + totalBrut() + " cts");
    }
    
    public int getTaille() {
        return taille;
    }
    
    public LignePanier getLigne(int index) {
        if (index < 0 || index >= taille) {
            return null;
        }
        return lignes[index];
    }
}