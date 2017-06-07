package Modèle;

import java.awt.Color;

public class Messager extends Joueur {

    public Messager(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDArgent;
        this.setCouleur(Color.WHITE);
    }
        @Override //peut donner des cartes a distance
	public void donnerCarte() {
		// TODO - implement Messager.donnerCarte
		throw new UnsupportedOperationException();
	}

}