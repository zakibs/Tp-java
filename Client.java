import java.util.Vector;

public class Client {

    private int codeClient;
    private String nomClient;
    private String adrClient;
    private String telClient;
    private Vector<Commande> listCommandes;

    public Client(int codeClient, String nomClient, String adrClient, String telClient) {
        this.codeClient = codeClient;
        this.nomClient = nomClient;
        this.adrClient = adrClient;
        this.telClient = telClient;
        this.listCommandes = new Vector<>();
    }

    public boolean EnregistrerCommande(Commande cmd) {
        return this.listCommandes.add(cmd);
    }

    public boolean SupprimerCommande(int numCommande) {
        for (Commande c : listCommandes) {
            if (c.getNumCommande() == numCommande) {
                listCommandes.remove(c);
                return true;
            }
        }
        return false;
    }

    public Vector<Commande> getListeCommandes() {
        return this.listCommandes;
    }

    @Override
    public String toString() {
        String info = "Client{ Code=" + codeClient +
                ", Nom='" + nomClient + "', Adresse='" + adrClient +
                "', Tel='" + telClient + "' }\n";

        if (!listCommandes.isEmpty()) {
            info += "Commandes:\n";
            for (Commande c : listCommandes) info += " - " + c + "\n";
        } else {
            info += "Aucune commande.\n";
        }

        return info;
    }
}
