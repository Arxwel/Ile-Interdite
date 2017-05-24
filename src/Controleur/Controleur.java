package Controleur;

import Modèle.CarteInondation;
import Modèle.Grille;
import Modèle.Joueur;
import java.util.*;
import java.util.Scanner;

public class Controleur {

    
    private Stack<CarteInondation> cartesInondation;
    //private VueAventurier vueAventurier;
    private static Grille grille;
    private ArrayList<Joueur> joueurs;
    private Stack<CarteInondation> défausseInondation;
    private Stack<CarteInondation> cimetièreInondation;
    private Scanner sc = new Scanner(System.in);
    
    
    public static void main(String[] args) {
        inscriptionJoueurs();
        grille = new Grille();
        //Créer les Vues
        
	

	
	
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

    private static  void inscriptionJoueurs() {
        
    }
    
}