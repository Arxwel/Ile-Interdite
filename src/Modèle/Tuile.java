package Modèle;

import java.util.*;

public class Tuile {

	private Coordonees coordonees;
	private ArrayList<Joueur> locataires;
	private Zone intitule;
	private Etat etat;
        private Grille plateau;

        public Tuile(Zone nom, Etat etat, Grille grille) {
            setIntitule(nom);
            setEtat(etat);
            setCoordonees(coordonees);
            locataires = new ArrayList<>();
            plateau = grille;
          
        }
        
	/**
	 * 
	 * @param joueur
	 */
	public void addLocataire(Joueur joueur) {
            this.locataires.add(joueur);
	}

	/**
	 * 
	 * @param joueur
	 */
	public void delLocataire(Joueur joueur) {
            this.locataires.remove(joueur);
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

    /**
     * @return the coordonees
     */
    public Coordonees getCoordonees() {
        return coordonees;
    }

    /**
     * @param coordonees the coordonees to set
     */
    private void setCoordonees(Coordonees coordonees) {
        this.coordonees = coordonees;
    }

    /**
     * @return the locataires
     */
    private ArrayList<Joueur> getLocataires() {
        return locataires;
    }

    /**
     * @param locataires the locataires to set
     */
    private void setLocataires(ArrayList<Joueur> locataires) {
        this.locataires = locataires;
    }

}
