package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Explorateur extends Joueur {

    public Explorateur(String nom, Color couleur) {
        super(nom, couleur);
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
		// TODO - implement Explorateur.listerCasesDispo
		throw new UnsupportedOperationException();
	}

    @Override
	public void assécher() {
		// TODO - implement Explorateur.assécher
		throw new UnsupportedOperationException();
	}

}