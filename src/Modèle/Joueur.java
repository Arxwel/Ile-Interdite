package Modèle;

import Controleur.Controleur;
import Vue.VueAventurier;
import Vue.VueEchangeDeCartes;
import java.awt.Color;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public abstract class Joueur {

	private Tuile position;
	private ArrayList<CarteTresor> mainJoueur;
	private String nom;
	private Color couleur;
        protected Zone spawnPoint;
        public VueAventurier vueAventurier;
        private Controleur controleur;
        private ImageIcon image;
        
        
        private Scanner sc = new Scanner(System.in);
        

	public Joueur(String nom, Controleur controleur) {
            mainJoueur = new ArrayList<>();
            this.setNom(nom);
            this.setCouleur(null);
            this.spawnPoint = null;
            this.setPosition(null);
            this.setControleur(controleur);
	}

            //lister les cases sur lesquelles le joueur peut se déplacer en usant une seule action
            //Cette méthodes est redéfinie pour les type de joueurs ayant des mouvements spécifiques
	public ArrayList<Tuile> listerCasesDispo() {
           System.out.println("Lister Cases Dispo pour "+this.getNom());
           ArrayList<Tuile> tuileslibres = new ArrayList<>();

           System.out.println("Tuiles Adj");
           for (Tuile t: this.getPosition().getAdjacent()) {
               System.out.println(t.getIntitule().toString());
               if (t.getEtat()==Etat.Sec) {
                   tuileslibres.add(t);
               }
           }
           return tuileslibres;
           
	}

        //Gère l'action "donner carte"
	public void donnerCarte(Joueur jDest, CarteTresor cRecue) {
		jDest.getMainJoueur().add(cRecue);
                this.getMainJoueur().remove(cRecue);
                this.vueAventurier.getWindow().validate();
                this.vueAventurier.update();
                jDest.vueAventurier.getWindow().validate();
                jDest.vueAventurier.update();
            
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
            ArrayList<Tuile> casesDispo = new ArrayList<>(this.listerCasesDispo());
            
            Integer i = 0;
            controleur.surligner(casesDispo);
            controleur.waitForInput();
            Tuile caseDepl = controleur.getLastCase();
            if (!casesDispo.contains(caseDepl)) {
                controleur.waitForInput();
                caseDepl = controleur.getLastCase();
            }
            Tuile tuileQuittee = this.getPosition();
            
            System.out.println("deplacement de "+tuileQuittee.getIntitule()+" a "+caseDepl.getIntitule());
            
            tuileQuittee.delLocataire(this);
            this.setPosition(caseDepl);
            caseDepl.addLocataire(this);
            
            controleur.surligner(new ArrayList<Tuile>());
            System.out.println("Le Joueur est maintenant à"+this.getPosition().getIntitule());
	}

        //lister les cases que le joueur peut assécher en usant une seule action
        //Cette méthodes est redéfinie pour les type de joueurs ayant des mouvements spécifiques
	public ArrayList<Tuile> listerTuilesAssechables() {
           ArrayList<Tuile> tuilesAss = new ArrayList<>();
           
           if (this.getPosition().getEtat()==Etat.Inondé) {
               tuilesAss.add(this.getPosition());
           }

           for (Tuile t: this.getPosition().getAdjacent()) {
               if (t.getEtat()==Etat.Inondé) {
                   tuilesAss.add(t);
               }
           }
           return tuilesAss;
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
                    //exemple: si TypeCarte == TresorMagenta && couleur case == magenta
                    if ((carte.getType()== TypeCarte.TresorMagenta && c == Color.MAGENTA)
                         ||(carte.getType()== TypeCarte.TresorCyan && c == Color.CYAN)
                         ||(carte.getType()== TypeCarte.TresorGray && c == Color.GRAY)
                         ||(carte.getType()== TypeCarte.TresorOrange && c == Color.ORANGE)) {
                        n++;
                    }
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
        public void defausserCarte(CarteTresor c) {
            this.getMainJoueur().remove(c);
            controleur.getDefausseCarteTresor().add(c);
        }
        
        //gère l'utilisation de cartes spéciales (sac de sable, hélicoptère)
        public void useCarteSpe(CarteTresor c) {
            if (c.getType()==TypeCarte.SpécialSacDeSable) {
                ArrayList<Tuile> tuilesInon = new ArrayList<>();
                Coordonnees coor = new Coordonnees(position.getCoordonees().getX(),position.getCoordonees().getY());
                for (int x=0; x<=5; x++) {
                    for (int y=0; y<=5; y++) {
                        if (((position.getPlateau().getTuile(coor.getX(),coor.getY()) != null)) && (position.getPlateau().getTuile(coor.getX(),coor.getY()).getEtat() == Etat.Inondé)) {
                            tuilesInon.add(position.getPlateau().getTuile(coor.getX(),coor.getY()));
                        }
                    }
                }
                
            } else if (c.getType()==TypeCarte.SpécialHélicoptère) {
                ArrayList<Tuile> tuileslibres = new ArrayList<>();
                for (int x=0; x<=5; x++) {
                    for (int y=0; y<=5; y++) {
                        if (((position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()) != null)) && (position.getPlateau().getTuile(position.getCoordonees().getX()-1,position.getCoordonees().getY()).getEtat() == Etat.Sec)) {
                            tuileslibres.add(position.getPlateau().getTuile(position.getCoordonees().getX(),position.getCoordonees().getY()));
                        } 
                    }
                }
                //déplacer le joueur désiré
            }
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

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @param controleur the controleur to set
     */
    private void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }

    /**
     * @return the image
     */
    public ImageIcon getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    protected void setImage(ImageIcon image) {
        this.image = image;
    }

    public void utiliserCarte() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void assecher() {
        ArrayList<Tuile> casesDispo = new ArrayList<>(this.listerTuilesAssechables());
            
            Integer i = 0;
            controleur.surligner(casesDispo);
            controleur.waitForInput();
            Tuile caseAss = controleur.getLastCase();
            if (!casesDispo.contains(caseAss)) {
                controleur.waitForInput();
                caseAss = controleur.getLastCase();
            }
            
            System.out.println("Asséchance de "+caseAss.getIntitule());
            caseAss.setEtat(Etat.Sec);
            
            
            controleur.surligner(new ArrayList<Tuile>());
            System.out.println("Le Joueur est maintenant à"+this.getPosition().getIntitule());
    }

    public void donnerCarte() {
        ArrayList<Joueur> joueursechangeables = new ArrayList<>(position.getLocataires());
        VueEchangeDeCartes don = new VueEchangeDeCartes(this, joueursechangeables);
    }
}
