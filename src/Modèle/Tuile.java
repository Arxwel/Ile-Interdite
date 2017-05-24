package Modèle;

import java.util.*;

public class Tuile {

	Coordonees coordonees;
	ArrayList<Joueur> locataires;
	private Zone intitule;
	private Etat etat;

        public Tuile(Zone nom, Etat etat, Coordonees position) {
            setIntitule(nom);
            setEtat(etat);
            locataires = new ArrayList<>();
        }
        
	/**
	 * 
	 * @param joueur
	 */
	public void addLocataire(Joueur joueur) {
		// TODO - implement Tuile.addLocataire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param joueur
	 */
	public void delLocataire(Joueur joueur) {
		// TODO - implement Tuile.delLocataire
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newEtat
	 */
	public void updateEtat(String newEtat) {
		// TODO - implement Tuile.updateEtat
		throw new UnsupportedOperationException();
	}

    /**
     * @return the intitule
     */
    public Zone getIntitule() {
        return intitule;
    }

    /**
     * @param intitule the intitule to set
     */
    private void setIntitule(Zone intitule) {
        this.intitule = intitule;
    }

    /**
     * @return the etat
     */
    public Etat getEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    private void setEtat(Etat etat) {
        this.etat = etat;
    }

}