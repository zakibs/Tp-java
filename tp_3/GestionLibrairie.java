package tp_3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

public class GestionLibrairie {
    private static final String WORKSPACE_PATH = "workspace";
    private static final String DATA_PATH = WORKSPACE_PATH + "/data";
    private static final String PHOTOS_PATH = DATA_PATH + "/photos";
    private static final String LOGS_PATH = WORKSPACE_PATH + "/logs";
    private static final String TMP_PATH = WORKSPACE_PATH + "/tmp";
    
    // Q1.1 - Création de l'arborescence
    public static void creerArborescence() {
        try {
            
            new File(WORKSPACE_PATH).mkdirs();
            new File(DATA_PATH).mkdirs();
            new File(PHOTOS_PATH).mkdirs();
            new File(LOGS_PATH).mkdirs();
            new File(TMP_PATH).mkdirs();

            File stockFile = new File(DATA_PATH + "/stock_initial.csv");
            if (!stockFile.exists()) {
                try (PrintWriter writer = new PrintWriter(stockFile, "UTF-8")) {
                    writer.println("REF:DESIGNATION;PRIX;QTE");
                    writer.println("BK001;Algorithmes en Java;220.0;15");
                    writer.println("BK002;Programmation Réseau;180.5;10");
                    writer.println("BK003;Structures de Données;199.9;7");
                    writer.println("BK004;Systèmes et Réseaux;250.0;5");
                    writer.println("BK005;Programmation Orientée Objet;210.0;12");
                }
            }
            
            new File(DATA_PATH + "/ventes.txt").createNewFile();
            new File(DATA_PATH + "/produits.ser").createNewFile();
            new File(DATA_PATH + "/index.dat").createNewFile();
            new File(LOGS_PATH + "/app.log").createNewFile();
            
            System.out.println("=== ARBORESCENCE CRÉÉE ===");
            listerContenuRecursif(new File(WORKSPACE_PATH));
            
        } catch (IOException e) {
            loggerErreur("Création arborescence", e);
        }
    }
    
    public static void listerContenuRecursif(File repertoire) {
        if (!repertoire.exists()) return;
        
        File[] contenu = repertoire.listFiles();
        if (contenu != null) {
            for (File element : contenu) {
                if (element.isDirectory()) {
                    System.out.println(element.getAbsolutePath() + " [Dossier]");
                    listerContenuRecursif(element);
                } else {
                    System.out.println(element.getAbsolutePath() + 
                                     " [Fichier - " + element.length() + " octets]");
                }
            }
        }
    }
    
    // Q2.1 et Q2.2 - Import du stock CSV
    public static List<Produit> importerStock() {
        List<Produit> produits = new ArrayList<>();
        double valorisationTotale = 0.0;
        
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(DATA_PATH + "/stock_initial.csv"),
                    StandardCharsets.UTF_8))) {
            
            String line;
            boolean header = true;
            
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                
                String[] tokens = line.split(";");
                if (tokens.length == 4) {
                    String ref = tokens[0];
                    String designation = tokens[1];
                    double prix = Double.parseDouble(tokens[2]);
                    int qte = Integer.parseInt(tokens[3]);
                    
                    Produit produit = new Produit(ref, designation, prix, qte);
                    produits.add(produit);
                    valorisationTotale += prix * qte;
                }
            }
            
        } catch (IOException e) {
            loggerErreur("Import stock", e);
        }
        
        // Q2.3 - Affichage des résultats
        System.out.println("\n=== IMPORT STOCK ===");
        System.out.println("Nombre de produits importés: " + produits.size());
        System.out.println("Valorisation totale du stock: " + String.format("%.2f", valorisationTotale) + " €");
        
        return produits;
    }
    
    // Q3.1 - Écriture vente avec BufferedWriter
    public static void ecrireVenteBuffered() {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(DATA_PATH + "/ventes.txt", true),
                    StandardCharsets.UTF_8))) {
            
            String vente = LocalDateTime.now() + ";BK002;2;361.00";
            bw.write(vente);
            bw.newLine();
            bw.flush();
            System.out.println("✓ Vente écrite (BufferedWriter): " + vente);
            
        } catch (IOException e) {
            loggerErreur("Écriture vente BufferedWriter", e);
        }
    }
    
    // Q3.2 - Écriture vente avec PrintWriter
    public static void ecrireVentePrintWriter() {
        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(DATA_PATH + "/ventes.txt", true),
                    StandardCharsets.UTF_8), true)) {
            
            String timestamp = LocalDateTime.now().toString();
            pw.printf("%s;BK003;1;199.90%n", timestamp);
            System.out.println("✓ Vente écrite (PrintWriter): " + timestamp + ";BK003;1;199.90");
            
        } catch (IOException e) {
            loggerErreur("Écriture vente PrintWriter", e);
        }
    }
    
    // Q4.1 - Écriture UTF-8 garantie
    public static void appendVenteUtf8(String ref, String designation, int quantite, double montant) {
        try (PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(DATA_PATH + "/ventes.txt", true),
                    StandardCharsets.UTF_8), true)) {
            
            String vente = String.format("%s;%s;%s;%d;%.2f", 
                LocalDateTime.now(), ref, designation, quantite, montant);
            out.println(vente);
            System.out.println("✓ Vente UTF-8 écrite: " + vente);
            
        } catch (IOException e) {
            loggerErreur("Écriture vente UTF-8", e);
        }
    }
    
    // Q5.1 - Copie binaire avec buffer
    public static void copierFichierBinaire(String source, String destination) {
        long debut = System.currentTimeMillis();
        
        try (FileInputStream fis = new FileInputStream(source);
             FileOutputStream fos = new FileOutputStream(destination)) {
            
            byte[] buffer = new byte[8192];
            int bytesLus;
            long totalBytes = 0;
            
            File fichierSource = new File(source);
            System.out.println("Taille source: " + fichierSource.length() + " octets");
            
            while ((bytesLus = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesLus);
                totalBytes += bytesLus;
            }
            
            long fin = System.currentTimeMillis();
            File fichierDest = new File(destination);
            
            System.out.println("✓ Copie terminée:");
            System.out.println("  - Taille destination: " + fichierDest.length() + " octets");
            System.out.println("  - Temps écoulé: " + (fin - debut) + " ms");
            System.out.println("  - Octets copiés: " + totalBytes);
            
        } catch (IOException e) {
            loggerErreur("Copie binaire", e);
        }
    }
    
    // Méthode pour créer un fichier dummy
    public static void creerFichierDummy(String chemin, int tailleKo) {
        try (FileOutputStream fos = new FileOutputStream(chemin)) {
            byte[] data = new byte[tailleKo * 1024];
            new Random().nextBytes(data);
            fos.write(data);
            System.out.println("✓ Fichier dummy créé: " + chemin + " (" + tailleKo + " Ko)");
        } catch (IOException e) {
            loggerErreur("Création fichier dummy", e);
        }
    }
    
    // Q6.1 - Création index avec RandomAccessFile
    public static void creerIndexProduits(List<Produit> produits) {
        try (RandomAccessFile raf = new RandomAccessFile(DATA_PATH + "/index.dat", "rw")) {
            
            for (Produit produit : produits) {
                raf.writeUTF(produit.getRef());
                long positionStock = Math.abs(produit.getRef().hashCode() * 1000L);
                raf.writeLong(positionStock);
                System.out.println("Indexé: " + produit.getRef() + " → position: " + positionStock);
            }
            
        } catch (IOException e) {
            loggerErreur("Création index", e);
        }
    }
    
    // Q6.2 - Recherche par référence
    public static long seekByRef(String refRecherchee) {
        try (RandomAccessFile raf = new RandomAccessFile(DATA_PATH + "/index.dat", "r")) {
            
            while (raf.getFilePointer() < raf.length()) {
                String ref = raf.readUTF();
                long position = raf.readLong();
                
                if (ref.equals(refRecherchee)) {
                    System.out.println("Référence trouvée: " + ref + " → position: " + position);
                    return position;
                }
            }
            
        } catch (IOException e) {
            loggerErreur("Recherche référence", e);
        }
        
        System.out.println("Référence non trouvée: " + refRecherchee);
        return -1;
    }
    
    // Q7.1 - Sérialisation
    public static void serialiserProduits(List<Produit> produits) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_PATH + "/produits.ser"))) {
            
            oos.writeObject(produits);
            System.out.println("Produits sérialisés: " + produits.size() + " produits");
            
        } catch (IOException e) {
            loggerErreur("Sérialisation", e);
        }
    }
    
    // Q7.2 - Désérialisation
    @SuppressWarnings("unchecked")
    public static List<Produit> deserialiserProduits() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_PATH + "/produits.ser"))) {
            
            List<Produit> produits = (List<Produit>) ois.readObject();
            
            System.out.println("✓ Produits désérialisés: " + produits.size() + " produits");
            if (!produits.isEmpty()) {
                System.out.println("Premier produit: " + produits.get(0));
                System.out.println("Dernier produit: " + produits.get(produits.size() - 1));
            }
            
            return produits;
            
        } catch (IOException | ClassNotFoundException e) {
            loggerErreur("Désérialisation", e);
            return new ArrayList<>();
        }
    }
    
    // Q8.1 - Journalisation des erreurs
    public static void loggerErreur(String operation, Exception e) {
        try (PrintWriter logWriter = new PrintWriter(
                new OutputStreamWriter(
                    new FileOutputStream(LOGS_PATH + "/app.log", true),
                    StandardCharsets.UTF_8), true)) {
            
            String messageErreur = String.format("[%s] Opération: %s | Erreur: %s",
                LocalDateTime.now(), operation, e.getMessage());
            
            logWriter.println(messageErreur);
            System.out.println(" Erreur journalisée: " + messageErreur);
            
        } catch (IOException logException) {
            System.err.println("Échec de journalisation: " + logException.getMessage());
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== DÉMARRAGE APPLICATION GESTION LIBRAIRIE ===\n");
        
        creerArborescence();
        
        List<Produit> produits = importerStock();
        
        System.out.println("\n=== JOURNAL DES VENTES ===");
        ecrireVenteBuffered();
        ecrireVentePrintWriter();
        appendVenteUtf8("BK004", "Livre avec accents éàè", 1, 250.0);
        
        appendVenteUtf8("BK006", "Programmation C++", 3, 540.0);
        
        System.out.println("\n=== GESTION BINAIRE ===");
        creerFichierDummy("dummy.bin", 10);
        copierFichierBinaire("dummy.bin", PHOTOS_PATH + "/BK001.jpg");
        
        System.out.println("\n=== ACCÈS DIRECT ===");
        creerIndexProduits(produits);
        seekByRef("BK003");
        
        System.out.println("\n=== SÉRIALISATION ===");
        serialiserProduits(produits);
        List<Produit> produitsDeserialises = deserialiserProduits();
        
        System.out.println("\n=== VÉRIFICATION ===");
        System.out.println("Collections égales: " + 
            (produits.size() == produitsDeserialises.size()));
        
        System.out.println("\n=== RÉFLEXION THÉORIQUE ===");
        System.out.println("R1) BufferedWriter vs PrintWriter:");
        System.out.println("   - BufferedWriter: Optimise l'écriture avec buffer, méthodes bas niveau");
        System.out.println("   - PrintWriter: Formatage automatique, gestion simplifiée des exceptions");
        System.out.println("R2) BufferedReader + InputStreamReader UTF-8:");
        System.out.println("   - Garantit le bon décodage des caractères UTF-8");
        System.out.println("   - Buffer améliore les performances pour lecture ligne par ligne");
        System.out.println("R3) Cas d'usage RandomAccessFile:");
        System.out.println("   - Bases de données simples avec index");
        System.out.println("   - Fichiers logs avec recherche par position");
        System.out.println("R4) Bonnes pratiques fermeture flux:");
        System.out.println("   - try-with-resources pour fermeture automatique");
        System.out.println("   - flush() avant fermeture pour vider le buffer");
        System.out.println("   - taille buffer adaptée (4-8 Ko généralement optimal)");
        
        System.out.println("\n=== APPLICATION TERMINÉE ===");
    }
}

class Produit implements Serializable {
    private static final long serialVersionUID = 1L;
    private String ref;
    private String designation;
    private double prix;
    private int qte;

    public Produit(String ref, String designation, double prix, int qte) {
        this.ref = ref;
        this.designation = designation;
        this.prix = prix;
        this.qte = qte;
    }

    public String getRef() { return ref; }
    public String getDesignation() { return designation; }
    public double getPrix() { return prix; }
    public int getQte() { return qte; }
    
    @Override
    public String toString() {
        return ref + ";" + designation + ";" + prix + ";" + qte;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Produit produit = (Produit) obj;
        return Objects.equals(ref, produit.ref);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(ref);
    }
}