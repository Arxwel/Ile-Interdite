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
        private Zone spawnPoint;
        
        
        private Scanner sc = new Scanner(System.in);
        

	public Joueur(String nom, Color couleur) {
            mainJoueur = new ArrayList<>();
            this.setNom(nom);
            this.setCouleur(couleur);
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

           if (((position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()).getEtat() == Etat.Inondé)) {
               //tuile à gauche du joueur
               tuilesinond.add(position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()).getEtat() == Etat.Inondé)) {
               //tuile à droite du joueur
               tuilesinond.add(position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getY()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1).getEtat() == Etat.Inondé)) {
               //tuile en dessous du joueur
               tuilesinond.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1));
           } else if(((position.getPlateau().getTuile(position.getCoordonees().getY()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1).getEtat() == Etat.Inondé)){
               //tuile au-dessus du joueur
               tuilesinond.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1));
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
    public Collection<CarteTresor> getCartesTrésor() {
        return mainJoueur;
    }

    /**
     * @param carteTrésors the carteTrésors to set
     */
    private void setCarteTrésors(Collection<CarteTresor> carteTrésors) {
        this.mainJoueur = mainJoueur;
    }

    /**
     * @return the nom
     */
    private String getNom() {
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
    private void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    
	public boolean isMvmntPossible() {
            return !this.listerCasesDispo().isEmpty();
	}

	public boolean isAssPossible() {
            return !this.listerTuilesAssechables().isEmpty();
	}

	public boolean isReliquePossible(Color c) {
            return 
	}
        
	public boolean isDonPossible(Joueur destinataire) {
            return (!this.getCartesTrésor().isEmpty()) && (this.getPosition() == destinataire.getPosition());
	}
        
        public Zone getSpawnPoint() {
            return this.spawnPoint;
        }

}
