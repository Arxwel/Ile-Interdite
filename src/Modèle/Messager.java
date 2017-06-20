package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Messager extends Joueur {
    
    public Messager(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDArgent;
        this.setCouleur(Color.WHITE);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Messager.png")));
    }
        @Override //peut donner des cartes a distance
	public void donnerCarte(Joueur jDest) {
		// TODO - implement Messager.donnerCarte
		throw new UnsupportedOperationException();
	}

}