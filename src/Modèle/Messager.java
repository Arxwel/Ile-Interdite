package Modèle;

import java.awt.Color;

public class Messager extends Joueur {

    public Messager(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDArgent;
        this.setCouleur(Color.WHITE);
    }

	public void donnerCarte() {
		// TODO - implement Messager.donnerCarte
		throw new UnsupportedOperationException();
	}

}