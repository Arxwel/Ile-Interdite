package Controleur;


import Modèle.*;
import java.awt.Color;
import java.util.*;
import java.util.Scanner;

public class Controleur {

    
    
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
    

    private static boolean isPartieFinie() {
        return false;
    }

    
    
    public static void main(String[] args) {
        
        piocheCarteTresor = new Stack<>();
        piocheInondation = new Stack<>();
        défausseInondation = new Stack<>();
        cimetièreInondation = new Stack<>();
                
        joueurs = new ArrayList<>();
        inscriptionJoueurs(); // A faire
        grille = new Grille();
        initPositionAventurier();
        //Créer les Vues
        
        initPiocheTresor();
        initPiocheInondation();
        
        for (Joueur j: joueurs) {
            for (int i=0; i<4; i++) {
                donnerCarte(j);
          }
        }
        
        
        // A vérifier avec D de Sequence
        
        while (!isPartieFinie()) {
            débutTour();
            
            
            }
            
            
    
            numTour++;
        } 

    private static void initPositionAventurier() {
        for (Joueur j: joueurs) {
            Zone spawn = j.getSpawnPoint();
            for (Tuile[] tArr: grille.getTuiles()) {
                for (Tuile t: tArr) {
                    if (t.getIntitule() == spawn) {
                        t.addLocataire(j);
                        j.setPosition((t));
                    }
                }
            }
        }
    }
	
    
    private void verifMain(Joueur joueur) {
	// TODO - implement Controleur.verifMain
	throw new UnsupportedOperationException();
    }


    public static void débutTour() {
        setJoueurActif();
        nbact =3;
        while (nbact>0) {
            actionsPossibles[0]=joueurActif.isMvmntPossible();
            actionsPossibles[1]=joueurActif.isAssPossible();
            actionsPossibles[2]=joueurActif.isDonPossible();
            actionsPossibles[3]=joueurActif.isReliquePossible();
            
            boolean choixValide = false; // /!\ ATTENTION C'EST DANGEREUX !!!!!
            while (!choixValide) {
                if (actionsPossibles[0]) {
                    System.out.println("1. Déplacer");
                }
                if (actionsPossibles[1]) {
                    System.out.println("2. Assécher");
                }
                if (actionsPossibles[2]) {
                    System.out.println("3. Donner");
                }
                if (actionsPossibles[3]) {
                    System.out.println("4. Ramasser");
                }
                System.out.println("Saisissez le numéro de l'action:    STP rentre pas nawak");
                int choix;
                choix = sc.nextInt();
                if (sc.hasNextInt(choix) && choix<5) {
                    if (actionsPossibles[choix-1]) {
                        choixValide = true;
                        switch (choix-1) {
                            case 0:
                                joueurActif.déplacer();
                                break;
                            case 1:
                                joueurActif.assécher();
                                break;
                            case 2:
                                joueurActif.donnerCarte();
                                break;
                            case 3:
                                joueurActif.prendreTrésor();
                                break;
                                
                        }
                    }
                }
            }
        nbact--;
	
        }
    }

    public static void setJoueurActif() {
        joueurActif = joueurs.get(numTour%4);
    }

    private static void inscriptionJoueurs() {
        Joueur j1 = new Explorateur("Jason",Color.RED);
        Joueur j2 = new Navigateur("Tommy",Color.GREEN);
        Joueur j3 = new Messager("John",Color.ORANGE);
        Joueur j4 = new Pilote("Kim",Color.PINK);
        joueurs.add(j1);
        joueurs.add(j2);
        joueurs.add(j3);
        joueurs.add(j4);
        
        
        
    }
    
    private static void initPiocheTresor() {
        for (int i=0; i<5; i++) {
            piocheCarteTresor.add(new CarteRelique(Color.BLACK));
            piocheCarteTresor.add(new CarteRelique(Color.GREEN));
            piocheCarteTresor.add(new CarteRelique(Color.RED));
            piocheCarteTresor.add(new CarteRelique(Color.YELLOW));
        }
        for (int i=0; i<3; i++) {
            piocheCarteTresor.add(new CarteMonteeDesEaux());
        }
        for (int i=0; i<3; i++) {
            piocheCarteTresor.add(new CarteSpeciale(TypeSpé.Hélicoptère));
        }
        for (int i=0; i<2; i++) {
           piocheCarteTresor.add(new CarteSpeciale(TypeSpé.SacDeSable));
        }
        Collections.shuffle(piocheCarteTresor);
    }
    
    private static void initPiocheInondation() {
        for (Tuile[] tArray: grille.getTuiles()) {
            for (Tuile t: tArray) {
                piocheInondation.add(new CarteInondation(t));
            }
        }
        Collections.shuffle(piocheInondation);
    }
    
    private static void donnerCarte(Joueur j) {
        j.getMainJoueur().add(piocheCarteTresor.firstElement());
        piocheCarteTresor.remove(0);
    }
}
