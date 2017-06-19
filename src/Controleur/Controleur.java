package Controleur;


import Modèle.*;
import Vue.VueInscription;
import Vue.VuePlateau;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Controleur implements Observateur {
  
    //private VueAventurier vueAventurier;
    private static Grille grille;
    private static ArrayList<Joueur> joueurs;
    private static Stack<CarteInondation> piocheInondation;
    private static Stack<CarteInondation> défausseInondation;
    private static Stack<CarteInondation> cimetièreInondation;
    private static Joueur joueurActif;
    private static int numTour = 0;
    private static int nbact;
    private static boolean[] actionsPossibles = new boolean[4];
    private static Scanner sc = new Scanner(System.in);
    private static Stack<CarteTresor> piocheCarteTresor;
    private static boolean suite = false;
    private static VueInscription vueInscription;

    /**
     * @return the suite
     */
    public static boolean isSuite() {
        return suite;
    }

    /**
     * @return the joueurs
     */
    public static ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }
    private VuePlateau vuePlateau;
    
    public Controleur() {
        grille = new Grille();
        joueurs = new ArrayList<>();
        piocheInondation = new Stack<>();
        défausseInondation = new Stack<>();
        cimetièreInondation = new Stack<>();
        joueurActif = null;
        nbact = 3;
        piocheCarteTresor = new Stack<>();
        vueInscription = Main.getVueInscription();
    }
    

    
    // méthode main temporaire pour tester
    /*public static void main(String[] args) {
        initPositionAventurier();
        
        initPiocheTresor();
        initPiocheInondation();
        
        for (Joueur j: getJoueurs()) {
            for (int i=0; i<4; i++) {
                donnerCarte(j);
          }
        }
        
        
        while (!isPartieFinie()) {
            débutTour();
            numTour++;
        }    
    } */
    public void play() {
        
        //Initialisation des pioches et position des joueurs
        initPositionAventurier();
        
        initPiocheTresor();
        initPiocheInondation();
        
        //distribution des cartes
        
        for (Joueur j: getJoueurs()) {
            for (int i=0; i<4; i++) {
                donnerCarte(j);
          }
        }
        
        //Boucle de jeu
        while (!isPartieFinie()) {
            débutTour();
            numTour++;
        }    
    } 
    
    private static boolean isPartieFinie() {
        return false;
    }

    //Place les aventuriers sur le plateau dans leur position de départ
    private static void initPositionAventurier() {
        for (Joueur j: getJoueurs()) {
            Zone spawn = j.getSpawnPoint();
            for (Tuile[] tArr: grille.getTuiles()) {
                for (Tuile t: tArr) {
                    if (t!=null && t.getIntitule().equals(spawn)) {
                        System.out.println(t.getCoordonees().getX()); //Trace Test Coordonnées
                        t.addLocataire(j);
                        j.setPosition((t));
                    }
                }
            }
        }
    }
	
    //Assure que le joueur a moins de 6 cartes en main et propose l'utilisation ou la défausse de cartes
    private void verifMain(Joueur joueur) {   
        while (joueur.getMainJoueur().size() >= 6) {
            System.out.println(joueur.getNom() + " a trop de cartes en main. Il doit en défausser ou en utiliser jusqu'à en avoir 5 au plus.");
            CarteTresor cs1 = new CarteTresor(TypeCarte.SpécialHélicoptère);
            CarteTresor cs2 = new CarteTresor(TypeCarte.SpécialSacDeSable);
            CarteTresor cr1 = new CarteTresor(TypeCarte.TresorVert);
            CarteTresor cr2 = new CarteTresor(TypeCarte.TresorNoir);
            CarteTresor cr3 = new CarteTresor(TypeCarte.TresorRouge);
            CarteTresor cr4 = new CarteTresor(TypeCarte.TresorJaune);
            for (int i = 1; i<joueur.getMainJoueur().size(); i++) {
                /*if (joueur.getMainJoueur().get(i).equals(cs1)) {
                    System.out.println("Carte Hélicoptère. Choisissez votre action: Utiliser, Defausser ou Rien.");                    
                    String choix = sc.nextLine();
                    if (choix=="1") {
                        joueur.useCarteSpe(cs1);
                    } else if (choix=="2") {
                        joueur.defausserCarte();
                    } else if (choix=="3") {
                        
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cs2)) {
                    System.out.println("Carte Sac de sable. Choisissez votre action: Utiliser (1), Defausser (2) ou Rien (3).");                    
                    String choix = sc.nextLine();
                    if (choix=="1") {
                        joueur.useCarteSpe(cs1);
                    } else if (choix=="2") {
                        joueur.defausserCarte();
                    } else if (choix=="3") {
                        
                    }
                }*/
                int choix;
                
                if (joueur.getMainJoueur().get(i).equals(cs1)) {
                    System.out.println("Carte Hélicoptère. Choisissez votre action: Utiliser (1), Defausser (2) ou Rien (3).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                //joueur.useCarteSpe(cs1);
                                break;
                            case 1:
                                joueur.defausserCarte();
                                break;
                            case 2:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cs2)) {
                    System.out.println("Carte Sac de sable. Choisissez votre action: Utiliser (1), Defausser (2) ou Rien (3).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                //joueur.useCarteSpe(cs2);
                                break;
                            case 1:
                                joueur.defausserCarte();
                                break;
                            case 2:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr1)) {
                    System.out.println("Carte relique Turquoise. Choisissez votre action: Defausser (1) ou Rien (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte();
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr2)) {
                    System.out.println("Carte relique Grise. Choisissez votre action: Defausser (1) ou Rien (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte();
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr3)) {
                    System.out.println("Carte relique Magenta. Choisissez votre action: Defausser (1) ou Rien (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte();
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                } else if (joueur.getMainJoueur().get(i).equals(cr4)) {
                    System.out.println("Carte relique Orange. Choisissez votre action: Defausser (1) ou Rien (2).");
                    choix = sc.nextInt();
                    if (sc.hasNextInt(choix) && choix<4) {
                        switch (choix-1) {
                            case 0:
                                joueur.defausserCarte();
                                break;
                            case 1:
                                break;
                                
                        }
                    }
                }
            }
        }
    }
    
    

    //décompte le nombre d'actions disponibles pour le joueur et propose les actions en fonction de leur disponibilité
    public static void débutTour() {
        setJoueurActif();
        nbact =3;
        while (nbact>0) {
            actionsPossibles[0]=joueurActif.isMvmntPossible();
            actionsPossibles[1]=joueurActif.isAssPossible();
            actionsPossibles[2]=joueurActif.isDonPossible();
            actionsPossibles[3]=joueurActif.isReliquePossible();
            
            joueurActif.getVueAventurier();
            
            nbact--;
        }
    }

    //Détermine le joueur dont c'est le tour de jouer en début de tour
    public static void setJoueurActif() {
        joueurActif = getJoueurs().get(numTour%getJoueurs().size());
    }

    
    //met les bonnes cartes dans la pioche trésor et les mélange
    private static void initPiocheTresor() {
        for (int i=0; i<5; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorNoir));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorVert));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorRouge));
            piocheCarteTresor.add(new CarteTresor(TypeCarte.TresorJaune));
        }
        for (int i=0; i<3; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.MontéeEaux));
        }
        for (int i=0; i<3; i++) {
            piocheCarteTresor.add(new CarteTresor(TypeCarte.SpécialHélicoptère));
        }
        for (int i=0; i<2; i++) {
           piocheCarteTresor.add(new CarteTresor(TypeCarte.SpécialSacDeSable));
        }
        Collections.shuffle(piocheCarteTresor);
    }
    
    //met les bonnes cartes dans la pioche inondation et les mélange
    private static void initPiocheInondation() {
        for (Tuile[] tArray: grille.getTuiles()) {
            for (Tuile t: tArray) {
                piocheInondation.add(new CarteInondation(t));
            }
        }
        Collections.shuffle(piocheInondation);
    }
    
    // gère l'action "donner carte"
    private static void donnerCarte(Joueur j) {
        j.getMainJoueur().add(piocheCarteTresor.firstElement());
        piocheCarteTresor.remove(0);
    }

    

    //traite les messages en provenance des vues
    @Override
    public void traiterMessage(Message msg) {
        switch (msg.getType().toString()) {
            case ("Valider"):
               Joueur j1, j2, j3, j4;
               String nom;
               nom = vueInscription.getNomFieldJ1();
                while(nom == null) {
                    nom = fenetreNom("Joueur 1");
                }  
                switch (vueInscription.getRoleComboJ1()) {
                    case ("Explorateur"):
                        j1 = new Explorateur(nom);
                        getJoueurs().add(j1);
                    break;
                    case ("Ingénieur"):
                        j1 = new Ingénieur(nom);
                        getJoueurs().add(j1);
                    break;
                    case ("Messager"):
                        j1 = new Messager(nom);
                        getJoueurs().add(j1);
                    break;
                    case ("Navigateur"):
                        j1 = new Navigateur(nom);
                        getJoueurs().add(j1);
                    break;
                    case ("Pilote"):
                        j1 = new Pilote(nom);
                        getJoueurs().add(j1);
                    break;
                    case ("Plongeur"):
                        j1 = new Plongeur(nom);
                        getJoueurs().add(j1);
                    break;
                } 
                nom = vueInscription.getNomFieldJ2();
                while(nom == null) {
                    nom = fenetreNom("Joueur 2");
                }  
                switch (vueInscription.getRoleComboJ2()) {
                    case ("Explorateur"):
                        j2 = new Explorateur(nom);
                        getJoueurs().add(j2);
                    break;
                    case ("Ingénieur"):
                        j2 = new Ingénieur(nom);
                        getJoueurs().add(j2);
                    break;
                    case ("Messager"):
                        j2 = new Messager(nom);
                        getJoueurs().add(j2);
                    break;
                    case ("Navigateur"):
                        j2 = new Navigateur(nom);
                        getJoueurs().add(j2);
                    break;
                    case ("Pilote"):
                        j2 = new Pilote(nom);
                        getJoueurs().add(j2);
                    break;
                    case ("Plongeur"):
                        j2 = new Plongeur(nom);
                        getJoueurs().add(j2);
                    break;
                }
                
                nom = vueInscription.getNomFieldJ3();
                while(nom == null) {
                    nom = fenetreNom("Joueur 3");
                }  
                switch (vueInscription.getRoleComboJ3()) {
                    case ("Explorateur"):
                        j3 = new Explorateur(nom);
                        getJoueurs().add(j3);
                    break;
                    case ("Ingénieur"):
                        j3 = new Ingénieur(nom);
                        getJoueurs().add(j3);
                    break;
                    case ("Messager"):
                        j3 = new Messager(nom);
                        getJoueurs().add(j3);
                    break;
                    case ("Navigateur"):
                        j3 = new Navigateur(nom);
                        getJoueurs().add(j3);
                    break;
                    case ("Pilote"):
                        j3 = new Pilote(nom);
                        getJoueurs().add(j3);
                    break;
                    case ("Plongeur"):
                        j3 = new Plongeur(nom);
                        getJoueurs().add(j3);
                    break;
                }
                
                nom = vueInscription.getNomFieldJ4();
                while(nom == null) {
                    nom = fenetreNom("Joueur 4");
                }  
                switch (vueInscription.getRoleComboJ4()) {
                    case ("Explorateur"):
                        j4 = new Explorateur(nom);
                        getJoueurs().add(j4);
                    break;
                    case ("Ingénieur"):
                        j4 = new Ingénieur(nom);
                        getJoueurs().add(j4);
                    break;
                    case ("Messager"):
                        j4 = new Messager(nom);
                        getJoueurs().add(j4);
                    break;
                    case ("Navigateur"):
                        j4 = new Navigateur(nom);
                        getJoueurs().add(j4);
                    break;
                    case ("Pilote"):
                        j4 = new Pilote(nom);
                        getJoueurs().add(j4);
                    break;
                    case ("Plongeur"):
                        j4 = new Plongeur(nom);
                        getJoueurs().add(j4);
                    break;
                }
                vueInscription.getWindow().dispose();
                suite = true;
            break;
            case ("Annuler"):
                System.exit(0);
            break;
        }
    }
    
    
    public Grille getGrille() {
        return grille;
    }
    
    //message d'erreur en cas de pseudo null
    public String fenetreNom(String Joueur) {
        String nom;
        JFrame frame = new JFrame("Saisie du Nom");
        nom = JOptionPane.showInputDialog(frame, Joueur + " : Saisissez votre nom");
        return nom;
    }
}
