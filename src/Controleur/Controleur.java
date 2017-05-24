package Controleur;


import Modèle.*;
import java.awt.Color;
import java.util.*;
import java.util.Scanner;

public class Controleur {

    
    private Stack<CarteInondation> cartesInondation;
    //private VueAventurier vueAventurier;
    private static Grille grille;
    private static ArrayList<Joueur> joueurs;
    private Stack<CarteInondation> défausseInondation;
    private Stack<CarteInondation> cimetièreInondation;
    private Scanner sc = new Scanner(System.in);
    private static Stack<CarteTresor> piocheCarteTresor;
    
    
    public static void main(String[] args) {
        joueurs = new ArrayList<>();
        inscriptionJoueurs();
        grille = new Grille();
        //Créer les Vues
        initPiocheTresor();
        
	

	
	
    }
    
    private void verifMain(Joueur joueur) {
	// TODO - implement Controleur.verifMain
	throw new UnsupportedOperationException();
    }


    public void débutTour() {
	// TODO - implement Controleur.débutTour
	throw new UnsupportedOperationException();
    }

    public void setJoueurActif() {
        // TODO - implement Controleur.setJoueurActif
	throw new UnsupportedOperationException();
    }

    private static void inscriptionJoueurs() {
        Joueur j1 = new Explorateur("John",Color.RED);
        Joueur j2 = new Navigateur("John",Color.GREEN);
        Joueur j3 = new Messager("John",Color.ORANGE);
        Joueur j4 = new Explorateur("John",Color.PINK);
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
}