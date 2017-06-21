package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Ingénieur extends Joueur {
    
    public Ingénieur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeBronze;
        this.setCouleur(Color.RED);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Ingenieur.png")));
    }
    
    @Override //peut assecher 2 tuiles a la fois
    public void assecher() {
	// TODO - implement Ingénieur.assécher
	throw new UnsupportedOperationException();
    }

}