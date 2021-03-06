package Modèle;

import Controleur.Controleur;
import Vue.VueAventurier;
import Vue.VueDefausse;
import Vue.VueDonDeCartes;
import java.awt.Color;
import java.util.*;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public abstract class Joueur {

	private Tuile position;
	private ArrayList<CarteTresor> mainJoueur;
	private String nom;
	private Color couleur;
        protected Zone spawnPoint;
        public VueAventurier vueAventurier;
        protected Controleur controleur;
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
               if (t.getEtat()!=Etat.Sombré) {
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
	public void prendreRelique() {
            Color relique =this.getPosition().getReliqueDispo();
            TypeCarte compType;
            ArrayList<CarteTresor> aSupr = new ArrayList<>();
            
            
            if(relique == Color.MAGENTA){
                compType = TypeCarte.TresorMagenta;
                controleur.addRelique(0);
                System.out.println("relique magenta");
            } else if(relique == Color.CYAN){
                compType = TypeCarte.TresorCyan;
                controleur.addRelique(3);
                System.out.println("relique cyan");
            } else if(relique == Color.GRAY){
                compType = TypeCarte.TresorGray;
                controleur.addRelique(2);
                System.out.println("relique gray");
            } else {
                compType = TypeCarte.TresorOrange;
                controleur.addRelique(1);
                System.out.println("relique orange");
            }
            int n = 0;
            for(CarteTresor c: this.getMainJoueur()) {
                if (c.getType() == compType&&n<5){
                    aSupr.add(c);
                    n++;
                }
            }
            for (CarteTresor c: aSupr) {
                this.defausserCarte(c);
            }
            controleur.signalerPriseRelique(compType);
            
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
        Color relique =this.getPosition().getReliqueDispo();
        TypeCarte compType;
        int n = 0;
        if(relique == Color.MAGENTA){
            compType = TypeCarte.TresorMagenta;
            System.out.println("relique magenta");
        } else if(relique == Color.CYAN){
            compType = TypeCarte.TresorCyan;
            System.out.println("relique cyan");
        } else if(relique == Color.GRAY){
            compType = TypeCarte.TresorGray;
            System.out.println("relique gray");
        } else if(relique == Color.ORANGE) {
            compType = TypeCarte.TresorOrange;
            System.out.println("relique orange");
        } else {
            compType = null;
            System.err.println("pas de relique sur cette case");
        }
        for (CarteTresor c: this.getMainJoueur()) {
            if (c.getType() == compType){
                n++;
            }
            System.out.println(n);
        }
        return n>=4;
    }
        
        //true si le don d'une carte est possible
	public boolean isDonPossible() {
            boolean possible = false;
            for (CarteTresor ct : this.getMainJoueur()) {
                if (ct.getType() != TypeCarte.SpécialHélicoptère && ct.getType() != TypeCarte.SpécialSacDeSable) {
                    possible = true;
                }
                
            }
            return (!this.getMainJoueur().isEmpty()) && (this.getPosition().getLocataires().size()>1) && possible;
	}
        
        //true si le joueur possède des cartes spéciales
	public boolean isCSPossible() {
            boolean possible = false;
            for (CarteTresor cs : this.getMainJoueur()) {
                if(cs.getType() == TypeCarte.SpécialHélicoptère || cs.getType() == TypeCarte.SpécialSacDeSable) {
                    possible = true;
                    
                }
                
            }
            
            return possible;
	}
        
        //retourne le point d'appartion originel des joueurs en fonction de leur Classe
        public Zone getSpawnPoint() {
            return this.spawnPoint;
        }
        
        //gère la défausse d'une carte
        public void defausserCarte(CarteTresor c) {
            controleur.getDefausseCarteTresor().add(c);
            this.getMainJoueur().remove(c);            
            this.vueAventurier.getWindow().validate();
            this.vueAventurier.update();
        }
        
        public void defausserCarte() {
            VueDefausse defausse = new VueDefausse(this);
            this.controleur.waitForInput();
        }
      
    public void utiliserHelico() {
        JOptionPane.showMessageDialog(null, "Si vous cliquez sur une tuile inoccupée, cela vous téléportera dessus.\n"
                    + "Si vous souhaitez vous déplacer sur une tuile déjà occupée ou déplacer un groupe de personnes, sélectionnez d'abord la tuile\n"
                    + "initiale où se trouve les personnes à déplacer puis cliquez ensuite sur la tuile de destination"
                    + " pour effectuer le transport.\n");
        ArrayList<Tuile> casesDispo = new ArrayList<>();
        Coordonnees coor = this.getPosition().getCoordonees();
        for (Tuile[] tArr: this.getPosition().getPlateau().getTuiles()) {
            for (Tuile t: tArr) {
                if (t != null && t.getEtat() != Etat.Sombré) {
                    casesDispo.add(t);
                }
            }
        }
        controleur.surligner(casesDispo);
        controleur.waitForInput();
        ArrayList<Joueur> groupe = new ArrayList<>(controleur.getLastCase().getLocataires());
        
        if(groupe.isEmpty()) {
            this.deplacerHelico(controleur.getLastCase());
        } else {
            deplacerHelico(casesDispo, groupe);
        }
        
    }
    
    public void utiliserSac() {
        ArrayList<Tuile> dispo = new ArrayList<>();
        Coordonnees coor = this.getPosition().getCoordonees();
        for (Tuile[] tArr: this.getPosition().getPlateau().getTuiles()) {
            for (Tuile t: tArr) {
                if (t != null && t.getCoordonees() != coor && t.getEtat() == Etat.Inondé) {
                    dispo.add(t);
                }
            }
        }
        System.out.println("Vous êtes ici");
        controleur.surligner(dispo);
        controleur.waitForInput();
        Tuile caseAss = controleur.getLastCase();
        System.out.println("Vous êtes là");
        System.out.println("Asséchance de "+caseAss.getIntitule());
        caseAss.setEtat(Etat.Sec);


        controleur.surligner(new ArrayList<Tuile>());
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
        String choixCarte = null;
        ArrayList<CarteTresor> cSpeciales = new ArrayList<>();
        for (CarteTresor c : this.getMainJoueur()) {
            if(c.getType() == TypeCarte.SpécialHélicoptère || c.getType() == TypeCarte.SpécialSacDeSable) {
                cSpeciales.add(c);
            }
        }
        ArrayList<String> cSpecialesName = new ArrayList<>();
        for (CarteTresor c : cSpeciales) {
           cSpecialesName.add(c.getType().name());
        }
        while(choixCarte == null) {
            String[] bouton = {"OK"};
            JPanel panel = new JPanel();
            JLabel label = new JLabel("Quelle carte spéciale voulez-vous utiliser ?");
            JComboBox txt = new JComboBox(cSpecialesName.toArray());
            panel.add(label);
            panel.add(txt);
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Saisie de Nom", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, bouton , bouton[0]);

            if(selectedOption == 0)
            {
                choixCarte = txt.getSelectedItem().toString();
            }
        }
        int i =0;
        if(choixCarte == TypeCarte.SpécialHélicoptère.name()) {
            while(i <getMainJoueur().size() && getMainJoueur().get(i).getType() != TypeCarte.SpécialHélicoptère) {
                i++;
            }
            getMainJoueur().remove(i);
            getVueAventurier().update();
            this.utiliserHelico();
        } else {
             while(i <getMainJoueur().size() && getMainJoueur().get(i).getType() != TypeCarte.SpécialSacDeSable) {
                i++;
            }
            getMainJoueur().remove(i);
            getVueAventurier().update();
            this.utiliserSac();
        }
        
        getVueAventurier().update();
    }
    public void assecher() {
        ArrayList<Tuile> casesDispo = new ArrayList<>(this.listerTuilesAssechables());
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
    }

    public void donnerCarte() {
        ArrayList<Joueur> joueursechangeables = new ArrayList<>(position.getLocataires());
        VueDonDeCartes don = new VueDonDeCartes(this, joueursechangeables);
    }
    
    public void deplacerHelico(Tuile t) {        
            Tuile tuileQuittee = this.getPosition();
            
            System.out.println("deplacement de "+tuileQuittee.getIntitule()+" a "+t.getIntitule());
            
            tuileQuittee.delLocataire(this);
            this.setPosition(t);
            t.addLocataire(this);
            
            controleur.surligner(new ArrayList<Tuile>());
            System.out.println("Le Joueur est maintenant à"+this.getPosition().getIntitule());
    }
    
    public void deplacerHelico(ArrayList<Tuile> tuileshelico, ArrayList<Joueur> groupe) {
        controleur.surligner(tuileshelico);
        controleur.waitForInput();
        Tuile caseDepl = controleur.getLastCase();


        if (!tuileshelico.contains(caseDepl)) {
            controleur.waitForInput();
            caseDepl = controleur.getLastCase();
        }

        for(Joueur j : groupe) {
        Tuile tuileQuittee = j.getPosition();
        System.out.println("deplacement de "+tuileQuittee.getIntitule()+" a "+caseDepl.getIntitule());
        tuileQuittee.delLocataire(j);
        j.setPosition(caseDepl);
        caseDepl.addLocataire(j);
        System.out.println("Le Joueur est maintenant à"+this.getPosition().getIntitule());
        }

        controleur.surligner(new ArrayList<Tuile>());
    }

    

    
}
