package Modèle;

import Controleur.Controleur;
import Vue.VueAventurier;
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
           ArrayList<Tuile> tuileslibres = new ArrayList<>();

           Coordonnees coo = this.getPosition().getCoordonees();
           Grille g = this.getPosition().getPlateau();
           Tuile t;
           Coordonnees[] cooAdj = new Coordonnees[4];
           cooAdj[0]=new Coordonnees(coo.getX()+1,coo.getY());
           cooAdj[1]=new Coordonnees(coo.getX()-1,coo.getY());
           cooAdj[2]=new Coordonnees(coo.getX(),coo.getY()+1);
           cooAdj[3]=new Coordonnees(coo.getX(),coo.getY()-1);
           for (Coordonnees c: cooAdj) {
               if (c.getX()>0 && c.getX()<6 && c.getY()>0 && c.getY()<6) {
                   t = g.getTuile(c);
                   if (t!=null && t.getEtat()==Etat.Sec) {
                       tuileslibres.add(t);
                   }
               }
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
            
            Tuile tuileAAssecher = tuilesInon.get(Integer.getInteger(choix));            
            tuileAAssecher.setEtat(Etat.Sec);

	}

        //Gère l'action "donner carte"
	public void donnerCarte() {
		// TODO - implement Joueur.prendreTrésor
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
           ArrayList<Tuile> tuilesAss = new ArrayList<>();

           Coordonnees coo = this.getPosition().getCoordonees();
           Grille g = this.getPosition().getPlateau();
           Tuile t;
           Coordonnees[] cooAdj = new Coordonnees[4];
           cooAdj[0]=new Coordonnees(coo.getX()+1,coo.getY());
           cooAdj[1]=new Coordonnees(coo.getX()-1,coo.getY());
           cooAdj[2]=new Coordonnees(coo.getX(),coo.getY()+1);
           cooAdj[3]=new Coordonnees(coo.getX(),coo.getY()-1);
           for (Coordonnees c: cooAdj) {
               if (c.getX()>0 && c.getX()<6 && c.getY()>0 && c.getY()<6) {
                   t = g.getTuile(c);
                   if (t!=null && t.getEtat()==Etat.Inondé) {
                       tuilesAss.add(t);
                   }
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
            //ajouter la carte à la pile de défausse
            this.getMainJoueur().remove(c);
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
                // assécher la tuile désirée
                
                //affichage console
               /* Integer i = 0;
                for (Tuile t: tuilesInon) {
                   System.out.println(i);
                   System.out.println(t.getIntitule().toString());
                   i++;
                }
                System.out.println("Choisissez:");
                String choix = sc.nextLine();
            
                Tuile tuileAAssecher = tuilesInon.get(Integer.getInteger(choix));            
                tuileAAssecher.setEtat(Etat.Sec); */ 
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

}
