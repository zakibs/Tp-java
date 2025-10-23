public class ClientFidel extends Client {

    private double tauxRemise;

    public ClientFidel(int code, String nom, String adr, String tel, double tauxRemise) {
        super(code, nom, adr, tel);
        this.tauxRemise = tauxRemise;
    }

    @Override
    public boolean EnregistrerCommande(Commande cmd) {
        return super.EnregistrerCommande(cmd);
    }

    public double getMontantRemise() {
        return tauxRemise;
    }

    @Override
    public String toString() {
        return super.toString() + "Taux Remise = " + tauxRemise + "\n";
    }
}
