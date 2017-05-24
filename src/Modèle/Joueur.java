package Modèle;

import java.awt.Color;
import java.util.*;

public abstract class Joueur {

	Tuile position;
	Collection<CarteTrésor> carteTrésors;
	private String nom;
	private Color couleur;

	public Joueur() {
		// TODO - implement Joueur.Joueur
		throw new UnsupportedOperationException();
	}

	public void listerCasesDispo() {
		// TODO - implement Joueur.listerCasesDispo
		throw new UnsupportedOperationException();
	}

	public void assécher() {
		// TODO - implement Joueur.assécher
		throw new UnsupportedOperationException();
	}

	public void donnerCarte() {
		// TODO - implement Joueur.donnerCarte
		throw new UnsupportedOperationException();
	}

	public void prendreTrésor() {
		// TODO - implement Joueur.prendreTrésor
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param tuile
	 */
	public void setPosition(Tuile tuile) {
		this.position = tuile;
	}

	public void déplacer() {
		// TODO - implement Joueur.déplacer
		throw new UnsupportedOperationException();
	}

	public Collection<Tuile> listerTuilesAss() {
		// TODO - implement Joueur.listerTuilesAss
		throw new UnsupportedOperationException();
	}

}