🍽️ KendiFood - Système de Gestion de Paniers-Repas
Un système console Java pour la gestion de paniers-repas, développé dans le cadre d'un TP de programmation orientée objet.

📋 Description
KendiFood est une application console permettant de gérer un catalogue d'articles alimentaires, constituer des paniers clients, appliquer des codes de réduction et générer des reçus détaillés.

🏗️ Architecture du Projet
Le projet suit une architecture orientée objet avec les classes suivantes :

Classes Principales
KendiFoodSystem.java - Classe principale orchestrant l'application

Article.java - Représente un produit du catalogue

LignePanier.java - Représente un article dans le panier avec sa quantité

CodeReduction.java - Gère les codes promo et leurs pourcentages

Catalogue.java - Gère la collection d'articles (CRUD + persistence)

Panier.java - Gère le panier d'achat du client

GestionCodesReduction.java - Gère les codes de réduction

Util.java - Méthodes utilitaires pour la saisie

KendiFoodException.java - Exception métier personnalisée

🚀 Fonctionnalités Implémentées
Phase 1 - Gestion du Catalogue
✅ Création d'articles avec validation (ID, libellé, prix, stock)

✅ Tableau dynamique avec redimensionnement automatique

✅ Affichage formaté du catalogue

✅ Recherche d'articles par ID

✅ Tableau 2D pour les statistiques (ventes/retours)

Phase 2 - Gestion du Panier
✅ Ajout d'articles avec vérification du stock

✅ Suppression d'articles avec restauration du stock

✅ Redimensionnement automatique du panier

✅ Affichage aligné et calcul du total brut

✅ Gestion des quantités et mise à jour en temps réel

Phase 3 - Codes de Réduction & Reçus
✅ Application de codes promo (1-50%)

✅ Vérification de la validité des codes

✅ Calcul du total net après réduction

✅ Génération efficace de reçus avec StringBuilder

✅ Formatage professionnel des reçus

Phase 5 - Robustesse et Gestion d'Erreurs
✅ Saisies numériques sécurisées avec reprise

✅ Gestion des fichiers (chargement/sauvegarde) avec relance

✅ Contrôles métier stricts (stock, prix, ID)

✅ Exceptions dédiées avec messages clairs

✅ Gestion des chemins de fichiers invalides

🛠️ Installation et Exécution
Prérequis
Java JDK 8 ou supérieur

Terminal/Console pour l'exécution

Compilation
bash
# Compiler toutes les classes
javac *.java

# Ou compiler individuellement
javac KendiFoodException.java
javac Article.java
javac LignePanier.java
javac CodeReduction.java
javac Catalogue.java
javac Panier.java
javac GestionCodesReduction.java
javac Util.java
javac KendiFoodSystem.java
Exécution
bash
java KendiFoodSystem
📝 Utilisation
Menu Principal
Le système propose un menu interactif avec 8 options :

Afficher catalogue - Liste tous les articles disponibles

Afficher panier - Montre le contenu actuel du panier

Ajouter au panier - Ajoute un article avec sa quantité

Supprimer du panier - Retire un article du panier

Générer reçu - Crée un reçu avec option de code promo

Charger catalogue fichier - Importe depuis un fichier texte

Sauvegarder catalogue - Exporte le catalogue vers un fichier

Quitter - Ferme l'application

Format des Fichiers
Pour charger un catalogue depuis un fichier, utilisez le format :

text
ID|Libellé|Prix en centimes|Stock
KIT_BOL1|Bol végétarien|5990|12
TOMATE3|Tomates x3|990|40
🎯 Exemple d'Utilisation
bash
=== KENDIFOOD SYSTEM ===
1. Afficher catalogue
2. Afficher panier
3. Ajouter au panier
4. Supprimer du panier
5. Générer reçu
6. Charger catalogue fichier
7. Sauvegarder catalogue
8. Quitter
Choix: 1

[CATALOGUE]
- KIT_BOL1   | Bol végétarien       |   5990 cts | stock=12
- TOMATE3    | Tomates x3           |    990 cts | stock=40

Choix: 3
ID article: TOMATE3
Quantité: 2
Article ajouté au panier

Choix: 5
Code réduction (enter pour ignorer): KENDI10

===== REÇU KendiFood =====
- TOMATE3 x2 -> 1980 cts
Total brut : 1980 cts
Code appliqué : KENDI10 (-10%)
Total à payer : 1782 cts
⚙️ Contrôles et Validations
Articles : ID et libellé non vides, prix et stock ≥ 0

Panier : Quantité ≤ stock disponible

Codes promo : Existence vérifiée, pourcentage entre 1-50%

Fichiers : Chemins valides et accessibles

Saisies : Gestion des entrées non numériques

🏆 Conception OOP
Le projet respecte les principes de la programmation orientée objet :

Encapsulation : Données privées avec accesseurs contrôlés

Responsabilité unique : Chaque classe a un rôle spécifique

Composition : Relations entre Catalogue, Panier, Articles

Gestion d'erreurs : Exceptions métier personnalisées

Réutilisabilité : Classes indépendantes et modulaires

📁 Structure des Fichiers
text
KendiFood/
├── Article.java
├── Catalogue.java
├── CodeReduction.java
├── GestionCodesReduction.java
├── KendiFoodException.java
├── KendiFoodSystem.java
├── LignePanier.java
├── Panier.java
├── Util.java
└── README.md
👨‍💻 Auteur
Développé dans le cadre d'un TP de programmation orientée objet.