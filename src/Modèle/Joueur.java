package Modèle;

import java.awt.Color;
import java.util.*;
import java.util.Scanner;

public abstract class Joueur {

	private Tuile position;
	private Collection<CarteTresor> carteTrésors;
	private String nom;
	private Color couleur;
        
        
        private Scanner sc = new Scanner(System.in);
        

	public Joueur(String nom, Color couleur) {
            this.setNom(nom);
            this.setCouleur(couleur);
	}

	public ArrayList<Tuile> listerCasesDispo() {
           ArrayList<Tuile> tuileslibres = new ArrayList<>();

           if (position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()).getEtat() == Etat.Sec) {
               //tuile à gauche du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()));
           } else if(position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()).getEtat() == Etat.Sec) {
               //tuile à droite du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()));
           } else if(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1).getEtat() == Etat.Sec) {
               //tuile en dessous du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1));
           } else if(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1).getEtat() == Etat.Sec){
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

	public Collection<Tuile> listerTuilesAss() {
		// TODO - implement Joueur.listerTuilesAss
		throw new UnsupportedOperationException();
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
    public Collection<CarteTresor> getCarteTrésors() {
        return carteTrésors;
    }

    /**
     * @param carteTrésors the carteTrésors to set
     */
    private void setCarteTrésors(Collection<CarteTresor> carteTrésors) {
        this.carteTrésors = carteTrésors;
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
    
    
	public void isMvmntPossible() {
		// TODO - implement Controleur.isMvmntPossible
		throw new UnsupportedOperationException();
	}

	public void isAssPossible() {
		// TODO - implement Controleur.isAssPossible
		throw new UnsupportedOperationException();
	}

	public void isReliquePossible() {
		// TODO - implement Controleur.isReliquePossible
		throw new UnsupportedOperationException();
	}
        
        

	public void isDonPossible() {
		// TODO - implement Controleur.isDonPossible
		throw new UnsupportedOperationException();
	}

}
