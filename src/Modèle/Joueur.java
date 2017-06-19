package Modèle;

import Vue.VueAventurier;
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
        public VueAventurier vueAventurier;
        
        
        private Scanner sc = new Scanner(System.in);
        

	public Joueur(String nom) {
            mainJoueur = new ArrayList<>();
            this.setNom(nom);
            this.setCouleur(null);
            this.spawnPoint = null;
            this.setPosition(null);
	}

            //lister les cases sur lesquelles le joueur peut se déplacer en usant une seule action
            //Cette méthodes est redéfinie pour les type de joueurs ayant des mouvements spécifiques
	public ArrayList<Tuile> listerCasesDispo() {
           ArrayList<Tuile> tuileslibres = new ArrayList<>();

           if (((position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()).getEtat() == Etat.Sec)) {
               //tuile à gauche du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()));
           } 
           if(((position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()).getEtat() == Etat.Sec)) {
               //tuile à droite du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX()+1,position.getCoordonees().getY()));
           } 
           if(((position.getPlateau().getTuile(position.getCoordonees().getY()-1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1).getEtat() == Etat.Sec)) {
               //tuile en dessous du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()-1));
           } 
           if(((position.getPlateau().getTuile(position.getCoordonees().getY()+1,position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1).getEtat() == Etat.Sec)){
               //tuile au-dessus du joueur
               tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()+1));
           }
           return tuileslibres;
	}

        //Gère l'assèchement des cases par les joueurs
	public void assécher() {
		// TODO - implement Joueur.assécher
            ArrayList<Tuile> tuilesInon = new ArrayList<>(listerTuilesAssechables());
            
            Integer i = 0;
            for (Tuile t: tuilesInon) {
                System.out.println(i);
                System.out.println(t.getIntitule().toString());
                i++;
            }
            System.out.println("Choisissez:");
            String choix = sc.nextLine();
            //Jusque ici
            
            
            
            Tuile tuileAAssecher = tuilesInon.get(Integer.getInteger(choix));            
            tuileAAssecher.setEtat(Etat.Sec);

	}

        //Gère le don de cartes par les joueurs
	public void donnerCarte() {
		// TODO - implement Joueur.donnerCarte
		throw new UnsupportedOperationException();
	}

        //Gère la prise de trésors par les joueurs
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

        //Gère le déplacement des joueurs
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

            //lister les cases que le joueur peut assécher en usant une seule action
            //Cette méthodes est redéfinie pour les type de joueurs ayant des mouvements spécifiques
	public ArrayList<Tuile> listerTuilesAssechables() {
		 ArrayList<Tuile> tuilesinond = new ArrayList<>();
                 Coordonnees coor = new Coordonnees(position.getCoordonees().getX(),position.getCoordonees().getY());
                 
           if (((position.getPlateau().getTuile(coor.getX()-1,coor.getY()) != null)) && (position.getPlateau().getTuile(coor.getX()-1,coor.getY()).getEtat() == Etat.Inondé)) {
               //tuile à gauche du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX()-1,coor.getY()));
           } 
           if(((position.getPlateau().getTuile(coor.getX()+1,coor.getY()) != null)) && (position.getPlateau().getTuile(coor.getX()+1,coor.getY()).getEtat() == Etat.Inondé)) {
               //tuile à droite du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX()+1,coor.getY()));
           } 
           if(((position.getPlateau().getTuile(coor.getX(),coor.getY()-1) != null)) && (position.getPlateau().getTuile(coor.getX(),coor.getY()-1).getEtat() == Etat.Inondé)) {
               //tuile en dessous du joueur
               tuilesinond.add(position.getPlateau().getTuile(coor.getX(),coor.getY()-1));
           } 
           if(((position.getPlateau().getTuile(coor.getX(),coor.getY()+1) != null)) && (position.getPlateau().getTuile(coor.getX(),coor.getY()+1).getEtat() == Etat.Inondé)){
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
    public Color getCouleur() {
        return couleur;
    }

    /**
     * @param couleur the couleur to set
     */
    protected void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    //true si le joueur peut se déplacer quelquepart
    public boolean isMvmntPossible() {
        return !this.listerCasesDispo().isEmpty();
    }

    //true si le joueur peut assécher une case
    public boolean isAssPossible() {
        return !this.listerTuilesAssechables().isEmpty();
    }

	public boolean isReliquePossible() {
            //couleur relique == couleur cartes && position joueur == tuile relique && nb cartes relique >= 4
            int n = 0;
            if (this.getPosition().getReliqueDispo() != null) {
                Color c = this.getPosition().getReliqueDispo();
                for(CarteTresor carte: this.getMainJoueur()) {
                    //exemple: si TypeCarte == TresorMagenta && couleur case == rouge
                    if ((carte.getType()== TypeCarte.TresorMagenta && c == Color.MAGENTA)
                         ||(carte.getType()== TypeCarte.TresorCyan && c == Color.CYAN)
                         ||(carte.getType()== TypeCarte.TresorGray && c == Color.GRAY)
                         ||(carte.getType()== TypeCarte.TresorOrange && c == Color.ORANGE)) {
                        n++;
                    }
                    
                    /*if (carte.equals(new CarteTresor(c))) {
                        n++;
                    }*/
                }
            }
            return (n>=4);
	}
        
        //true si le don d'une carte est possible
	public boolean isDonPossible() {
            return (!this.getMainJoueur().isEmpty()) && (this.getPosition().getLocataires().size()>1);
	}
        
        //retourne le point d'appartion originel des joueurs en fonction de leur Classe
        public Zone getSpawnPoint() {
            return this.spawnPoint;
        }
        
        //gère la défausse d'une carte
        public void defausserCarte() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
        //gère l'utilisation de cartes spéciales (sac de sable, hélicoptère)
        public void useCarteSpe(CarteTresor c) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    /**
     * @return the vueAventurier
     */
    public VueAventurier getVueAventurier() {
        return vueAventurier;
    }

    /**
     * @param vueAventurier the vueAventurier to set
     */
    public void setVueAventurier(VueAventurier vueAventurier) {
        this.vueAventurier = vueAventurier;
    }

}
