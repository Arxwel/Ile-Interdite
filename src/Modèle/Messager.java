package Modèle;

import Controleur.Controleur;
import Vue.VueDonDeCartes;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Messager extends Joueur {
    
    private Controleur controleur;
    
    public Messager(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDArgent;
        this.setCouleur(Color.WHITE);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Messager.png")));
    }
    
    @Override //peut donner des cartes a distance
    public void donnerCarte() {
        ArrayList<Joueur> joueursechangeables = new ArrayList<>(controleur.getJoueurs());
        VueDonDeCartes don = new VueDonDeCartes(this, joueursechangeables);
    }
    
    @Override
    public boolean isDonPossible() {
        return (!this.getMainJoueur().isEmpty());
    }

}