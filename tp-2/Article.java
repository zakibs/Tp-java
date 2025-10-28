public class Article {
    private String id;
    private String libelle;
    private int prixCentimes;
    private int stock;
    
    public Article(String id, String libelle, int prixCentimes, int stock) throws KendiFoodException {
        setId(id);
        setLibelle(libelle);
        setPrixCentimes(prixCentimes);
        setStock(stock);
    }
    

    public String getId() { return id; }
    public String getLibelle() { return libelle; }
    public int getPrixCentimes() { return prixCentimes; }
    public int getStock() { return stock; }
    

    public void setId(String id) throws KendiFoodException {
        if (id == null || id.trim().isEmpty()) {
            throw new KendiFoodException("L'id ne peut pas être vide");
        }
        this.id = id.trim();
    }
    
    public void setLibelle(String libelle) throws KendiFoodException {
        if (libelle == null || libelle.trim().isEmpty()) {
            throw new KendiFoodException("Le libellé ne peut pas être vide");
        }
        this.libelle = libelle.trim();
    }
    
    public void setPrixCentimes(int prixCentimes) throws KendiFoodException {
        if (prixCentimes < 0) {
            throw new KendiFoodException("Le prix ne peut pas être négatif");
        }
        this.prixCentimes = prixCentimes;
    }
    
    public void setStock(int stock) throws KendiFoodException {
        if (stock < 0) {
            throw new KendiFoodException("Le stock ne peut pas être négatif");
        }
        this.stock = stock;
    }
    

    public void decrementerStock(int quantite) throws KendiFoodException {
        if (quantite > stock) {
            throw new KendiFoodException("Stock insuffisant pour " + id + " (disponible: " + stock + ")");
        }
        this.stock -= quantite;
    }
    
    public void incrementerStock(int quantite) {
        this.stock += quantite;
    }
    
    @Override
    public String toString() {
        return String.format("- %-10s | %-20s | %6d cts | stock=%2d", 
                           id, libelle, prixCentimes, stock);
    }
}