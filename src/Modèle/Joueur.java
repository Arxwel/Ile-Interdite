package Modèle;

import java.awt.Color;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;

public abstract class Joueur {

	private Tuile position;
	private ArrayList<CarteTresor> mainJoueur;
	private String nom;
	private Color couleur;
        protected Zone spawnPoint;
        
        
        private Scanner sc = new Scanner(System.in);
        

	public Joueur(String nom) {
            mainJoueur = new ArrayList<>();
            this.setNom(nom);
            this.setCouleur(null);
            this.spawnPoint = null;
            this.setPosition(null);
	}

	public ArrayList<Tuile> listerCasesDispo() {
           ArrayList<Tuile> tuileslibres = new ArrayList<>();

           if (((position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()).getEtat() == Etat.Sec)) {
               //tuile à gauche du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()).getEtat() == Etat.Sec)) {
               //tuile à droite du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getY()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1).getEtat() == Etat.Sec)) {
               //tuile en dessous du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getY()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1).getEtat() == Etat.Sec)){
               //tuile au-dessus du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1));
           }
           return tuileslibres;
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
            ArrayList<Tuile> casesDispo = new ArrayList<>(listerCasesDispo());
            
            //A déplacer dans Controlleur
            Integer i = 0;
            for (Tuile t: casesDispo) {
                System.out.println(i);
                System.out.println(t.getIntitule().toString());
                i++;
            }
            System.out.println("Choisissez:");
            String choix = sc.nextLine();
            //Jusque ici
            
            
            
            Tuile caseDepl = casesDispo.get(Integer.getInteger(choix));
            Tuile tuileQuittee = this.getPosition();
            tuileQuittee.delLocataire(this);
            this.setPosition(caseDepl);
            caseDepl.addLocataire(this);
            
	}

	public ArrayList<Tuile> listerTuilesAssechables() {
		 ArrayList<Tuile> tuilesinond = new ArrayList<>();
                 Coordonnees coor = new Coordonnees(position.getCoordonees().getX(),position.getCoordonees().getY());
                 
           if (((position.getPlateau().getTuile(coor.getX()-1,coor.getY()) != null)) && (position.getPlateau().getTuile(coor.getX()-1,coor.getY()).getEtat() == Etat.Inondé)) {
               //tuile à gauche du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX()-1,coor.getY()));
           } else if(((position.getPlateau().getTuile(coor.getX()+1,coor.getY()) != null)) && (position.getPlateau().getTuile(coor.getX()+1,coor.getY()).getEtat() == Etat.Inondé)) {
               //tuile à droite du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX()+1,coor.getY()));
           } else if(((position.getPlateau().getTuile(coor.getX(),coor.getY()-1) != null)) && (position.getPlateau().getTuile(coor.getX(),coor.getY()-1).getEtat() == Etat.Inondé)) {
               //tuile en dessous du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX(),coor.getY()-1));
           } else if(((position.getPlateau().getTuile(coor.getX(),coor.getY()+1) != null)) && (position.getPlateau().getTuile(coor.getX(),coor.getY()+1).getEtat() == Etat.Inondé)){
               //tuile au-dessus du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX(),coor.getY()+1));
           }
           return tuilesinond;
	}

    /**
     * @return the position
     */
    public Tuile getPosition() {
        return position;
    }

    /**
     * @return the carteTrésors
     */
    public ArrayList<CarteTresor> getMainJoueur() {
        return mainJoueur;
    }

    /**
     * @param carteTresor the carteTresor to set
     */
    private void setCarteTresor(Collection<CarteTresor> carteTresor) {
        this.mainJoueur = mainJoueur;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    private void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the couleur
     */
    private Color getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    protected void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
    public boolean isMvmntPossible() {
        return !this.listerCasesDispo().isEmpty();
    }

    public boolean isAssPossible() {
        return !this.listerTuilesAssechables().isEmpty();
    }

	public boolean isReliquePossible() {
            //couleur relique == couleur cartes && position joueur == tuile relique && nb cartes relique >= 4
            int n = 0;
            if (this.getPosition().getReliqueDispo() != null) {
                Color c = this.getPosition().getReliqueDispo();
                for(CarteTresor carte: this.getMainJoueur()) {
                    if (carte.equals(new CarteRelique(c))) {
                        n++;
                    }
                }
            }
            return (n>=4);
	}
        
	public boolean isDonPossible() {
            return (!this.getMainJoueur().isEmpty()) && (this.getPosition().getLocataires().size()>1);
	}
        
        public Zone getSpawnPoint() {
            return this.spawnPoint;
        }
        
        public void defausserCarte() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        public void useCarteSpe(CarteSpeciale cs) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}
