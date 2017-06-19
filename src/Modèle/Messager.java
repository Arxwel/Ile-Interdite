package Modèle;

import Controleur.Controleur;
import java.awt.Color;

public class Messager extends Joueur {
    
    public Messager(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDArgent;
        this.setCouleur(Color.WHITE);
    }
        @Override //peut donner des cartes a distance
	public void donnerCarte() {
		// TODO - implement Messager.donnerCarte
		throw new UnsupportedOperationException();
	}

}