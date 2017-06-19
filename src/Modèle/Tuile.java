package Modèle;

import java.awt.Color;
import java.util.*;
import javax.swing.ImageIcon;

public class Tuile {

	private Coordonnees coordonees;
	private ArrayList<Joueur> locataires;
	private Zone intitule;
	private Etat etat;
        private Grille plateau;
        private Color reliqueDispo;
        private ImageIcon image;

        public Tuile(Zone nom, Etat etat, Grille grille, Coordonnees coo) {
            setIntitule(nom);
            setEtat(etat);
            setCoordonees(coo);
            locataires = new ArrayList<>();
            plateau = grille;
            if(null == etat) {
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
            } else switch (etat) {
                case Sec:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+nom.name()+".png"));
                    break;
                case Inondé:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+nom.name()+"2.png"));
                    break;
                case Sombré:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/EauSombree.png"));
                    break;    
                default:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
                    break;
            }

          
        }
        
        public Tuile(Zone nom, Etat etat, Grille grille, Coordonnees coo, Color reliqueDispo) {
            setIntitule(nom);
            setEtat(etat);
            setCoordonees(coo);
            locataires = new ArrayList<>();
            plateau = grille;
            this.reliqueDispo = reliqueDispo; //quand relique recuperée supprimer reliqueDispo ou changer la couleur dans un truc qui n'existe pas
            if(null == etat) {
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
            } else switch (etat) {
                case Sec:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+nom.name()+".png"));
                    break;
                case Inondé:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+nom.name()+"2.png"));
                    break;
                case Sombré:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/EauSombree.png"));
                    break;
                default:
                    image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
                    break;
            }
        }
        
	/**
	 * 
	 * @param joueur
	 */
        //ajoute un joueur sur la tuile
	public void addLocataire(Joueur joueur) {
            this.locataires.add(joueur);
	}

	/**
	 * 
	 * @param joueur
	 */
        //retire un joueur de la Tuile
	public void delLocataire(Joueur joueur) {
            this.locataires.remove(joueur);
	}

	/**
	 * 
	 * @param newEtat
	 */
        //change la tuiles d'Etat
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

    /**
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }

}
