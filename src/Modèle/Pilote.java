package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Pilote extends Joueur {

    public Pilote(String nom, Color couleur) {
        super(nom, couleur);
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
		// TODO - implement Pilote.listerCasesDispo
		throw new UnsupportedOperationException();
	}

}