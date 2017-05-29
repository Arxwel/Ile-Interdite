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

    private static boolean isPartieFinie() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private Scanner sc = new Scanner(System.in);
    private static Stack<CarteTresor> piocheCarteTresor;
    
    
    public static void main(String[] args) {
        piocheCarteTresor = new Stack<>();
        piocheInondation = new Stack<>();
        défausseInondation = new Stack<>();
        cimetièreInondation = new Stack<>();
                
        joueurs = new ArrayList<>();
        inscriptionJoueurs();
        grille = new Grille();
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
    }
    
    private void verifMain(Joueur joueur) {
	// TODO - implement Controleur.verifMain
	throw new UnsupportedOperationException();
    }


    public static void débutTour() {
	// TODO - implement Controleur.débutTour
	throw new UnsupportedOperationException();
    }

    public void setJoueurActif() {
        // TODO - implement Controleur.setJoueurActif
	throw new UnsupportedOperationException();
    }

    private static void inscriptionJoueurs() {
        Joueur j1 = new Explorateur("Jason",Color.RED);
        Joueur j2 = new Navigateur("Tommy",Color.GREEN);
        Joueur j3 = new Messager("John",Color.ORANGE);
        Joueur j4 = new Explorateur("Kim",Color.PINK);
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