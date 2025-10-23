import java.util.Date;

public class Commande implements Comparable<Commande> {

    private int numCommande;
    private Date dateCommande;
    private String nomFournisseur;

    public Commande(int numCommande, Date dateCommande, String nomFournisseur) {
        this.numCommande = numCommande;
        this.dateCommande = dateCommande;
        this.nomFournisseur = nomFournisseur;
    }

    public int getNumCommande() { return numCommande; }
    public Date getDateCommande() { return dateCommande; }
    public String getNomFournisseur() { return nomFournisseur; }

    public void setNumCommande(int numCommande) { this.numCommande = numCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }
    public void setNomFournisseur(String nomFournisseur) { this.nomFournisseur = nomFournisseur; }

    @Override
    public String toString() {
        return "Commande{ Num=" + numCommande +
                ", Date=" + dateCommande +
                ", Fournisseur='" + nomFournisseur + "' }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Commande)) return false;
        Commande c = (Commande) obj;
        return this.numCommande == c.numCommande;
    }

    @Override
    public int compareTo(Commande c) {
        return this.dateCommande.compareTo(c.dateCommande);
    }
}
