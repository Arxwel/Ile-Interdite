package Modèle;

import java.awt.Color;

public class Ingénieur extends Joueur {

    public Ingénieur(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDeBronze;
        this.setCouleur(Color.RED);
        //this.spawnPoint = Zone.
    }
    
    @Override
    public void assécher() {
	// TODO - implement Ingénieur.assécher
	throw new UnsupportedOperationException();
    }

}