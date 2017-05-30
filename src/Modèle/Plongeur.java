package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Plongeur extends Joueur {

    public Plongeur(String nom, Color couleur) {
        super(nom, couleur);
        this.spawnPoint = Zone.LaPorteDeFer;
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
		// TODO - implement Plongeur.listerCasesDispo
		throw new UnsupportedOperationException();
	}

}