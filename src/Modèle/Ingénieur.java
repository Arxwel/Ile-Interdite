package Modèle;

import Controleur.Controleur;
import java.awt.Color;

public class Ingénieur extends Joueur {
    
    public Ingénieur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeBronze;
        this.setCouleur(Color.RED);
        //this.spawnPoint = Zone.
    }
    
    @Override //peut assecher 2 tuiles a la fois
    public void assécher() {
	// TODO - implement Ingénieur.assécher
	throw new UnsupportedOperationException();
    }

}