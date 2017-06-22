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
        this.setImage();
        switch(this.getIntitule().toString().substring(0, 3).toUpperCase()){
            case("LAC"):
                this.reliqueDispo=Color.MAGENTA;
                break;
            case("LET"):
                this.reliqueDispo=Color.GRAY;
                break;
            case("LEP"):
                this.reliqueDispo=Color.CYAN;
                break;
            case("LEJ"):
                this.reliqueDispo=Color.ORANGE;
                break;
        }
    }


    public Tuile(Zone nom, Etat etat, Grille grille, Coordonnees coo, Color reliqueDispo) {
        setIntitule(nom);
        setEtat(etat);
        setCoordonees(coo);
        locataires = new ArrayList<>();
        plateau = grille;
        this.reliqueDispo = reliqueDispo;
    }

    public void setImage() {
        if(null == etat) {
            image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
        } else switch (etat) {
            case Sec:
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+this.getIntitule().name()+".png"));
                break;
            case Inondé:
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/"+this.getIntitule().name()+"2.png"));
                break;
            case Sombré:
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/EauSombree.png"));
                break;    
            default:
                image = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
                break;
        }

    }

    public ArrayList<Tuile> getDiagonales() {
        Grille g = this.getPlateau();
        Coordonnees coo = this.getCoordonees();
        ArrayList<Tuile> tuilesDia = new ArrayList();
        Tuile t;
        int[][] deltaCoo = {{1,1},{1,-1},{-1,1},{-1,-1}};
        for (int[] tabI:deltaCoo) {
            int[] cooATest = {coo.getX()+tabI[0],coo.getY()+tabI[1]};
            if (cooATest[0]>0&&cooATest[0]<6&&cooATest[1]>0&&cooATest[1]<6) {
                t = g.getTuile(cooATest[0],cooATest[1]);
                if (t!=null) {
                    tuilesDia.add(t);
                }
            }
        }
        return tuilesDia;
    }

    public ArrayList<Tuile> getAdjacent() {
        Grille g = this.getPlateau();
        Coordonnees coo = this.getCoordonees();
        ArrayList<Tuile> tuilesAdj = new ArrayList();
        Tuile t;
        int[][] deltaCoo = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] tabI:deltaCoo) {
            int[] cooATest = {coo.getX()+tabI[0],coo.getY()+tabI[1]};
            if (cooATest[0]>=0&&cooATest[0]<6&&cooATest[1]>=0&&cooATest[1]<6) {
                t = g.getTuile(cooATest[0],cooATest[1]);
                if (t!=null) {
                    tuilesAdj.add(t);
                }
            }
        }
        return tuilesAdj;
    }

    public boolean isSombre() {
        return this.getEtat() == Etat.Sombré;
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
    public void setEtat(Etat etat) {
        this.etat = etat;
        setImage();
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
    //méthode pour lister des tuiles de déplacement spéciales du plongeur
    public Collection<Tuile> tuilesPlongeurs() {
        ArrayList<Tuile> adjacentsSombres = new ArrayList<>();
        
        adjacentsSombres.addAll(this.getAdjacent());
        
        for(int i = 0; i < adjacentsSombres.size(); i++)
        {
            if (!adjacentsSombres.get(i).getEtat().equals(Etat.Sec)){
                for(Tuile t : adjacentsSombres.get(i).getAdjacent()){
                    if (!adjacentsSombres.contains(t)){
                        adjacentsSombres.add(t);
                    }
                }
            } else {
                if (!adjacentsSombres.contains(adjacentsSombres.get(i))){ 
                    adjacentsSombres.add(adjacentsSombres.get(i));
                }
            }
        }
    
        return adjacentsSombres;
    }

}
