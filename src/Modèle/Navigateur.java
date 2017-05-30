package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Navigateur extends Joueur {

    public Navigateur(String nom, Color couleur) {
        super(nom, couleur);
        this.spawnPoint = Zone.LaPorteDOr;
    }

	public ArrayList<Tuile> listerCasesDispo() {
		// TODO - implement Navigateur.listerCasesDispo
		throw new UnsupportedOperationException();
	}

}