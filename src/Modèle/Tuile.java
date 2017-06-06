package Modèle;

import java.awt.Color;
import java.util.*;

public class Tuile {

	private Coordonnees coordonees;
	private ArrayList<Joueur> locataires;
	private Zone intitule;
	private Etat etat;
        private Grille plateau;
        private Color reliqueDispo;

        public Tuile(Zone nom, Etat etat, Grille grille, Coordonnees coo) {
            setIntitule(nom);
            setEtat(etat);
            setCoordonees(coo);
            locataires = new ArrayList<>();
            plateau = grille;
          
        }
        
        public Tuile(Zone nom, Etat etat, Grille grille, Coordonnees coo, Color reliqueDispo) {
            setIntitule(nom);
            setEtat(etat);
            setCoordonees(coordonees);
            locataires = new ArrayList<>();
            plateau = grille;
            this.reliqueDispo = reliqueDispo; //quand relique recuperée supprimer reliqueDispo ou changer la couleur dans un truc qui n'existe pas
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
	public void updateEtat(Etat newEtat) {
            this.etat = newEtat;
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
    protected void setEtat(Etat etat) {
        this.etat = etat;
    }

    /**
     * @return the coordonees
     */
    public Coordonnees getCoordonees() {
        return coordonees;
    }

    /**
     * @param coordonees the coordonees to set
     */
    private void setCoordonees(Coordonnees coordonees) {
        this.coordonees = coordonees;
    }

    /**
     * @return the locataires
     */
    public ArrayList<Joueur> getLocataires() {
        return locataires;
    }

    /**
     * @param locataires the locataires to set
     */
    private void setLocataires(ArrayList<Joueur> locataires) {
        this.locataires = locataires;
    }

    /**
     * @return the plateau
     */
    public Grille getPlateau() {
        return plateau;
    }

    /**
     * @return the reliqueDispo
     */
    public Color getReliqueDispo() {
        return reliqueDispo;
    }

    /**
     * @param reliqueDispo the reliqueDispo to set
     */
    private void setReliqueDispo(Color reliqueDispo) {
        this.reliqueDispo = reliqueDispo;
    }

}
