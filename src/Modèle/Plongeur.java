package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Plongeur extends Joueur {

    public Plongeur(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDeFer;
        this.setCouleur(Color.BLACK);
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
		// TODO - implement Plongeur.listerCasesDispo
		throw new UnsupportedOperationException();
	}

}