import java.util.Collections;
import java.util.Date;

public class Test {
    public static void main(String[] args) {

        Commande c1 = new Commande(101, new Date(125, 0, 5), "Fourn 1");
        Commande c2 = new Commande(102, new Date(124, 7, 20), "Fourn 2");
        Commande c3 = new Commande(103, new Date(126, 2, 10), "Fourn 3");

        Client cl = new Client(1, "Zakariaa", "Casablanca", "0631368896");
        cl.EnregistrerCommande(c1);
        cl.EnregistrerCommande(c2);

        ClientFidel cf = new ClientFidel(2, "Adam", "Rabat", "0652654834", 0.15);
        cf.EnregistrerCommande(c3);
        double remiseRetour = cf.getMontantRemise();

        System.out.println("----- AVANT TRI -----");
        System.out.println(cl);
        System.out.println(cf);
        System.out.println("Remise retournée = " + remiseRetour);

        Collections.sort(cl.getListeCommandes());
        Collections.sort(cf.getListeCommandes());

        System.out.println("----- APRES TRI -----");
        System.out.println(cl);
        System.out.println(cf);
    }
}
