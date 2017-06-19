package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Navigateur extends Joueur {
    
    public Navigateur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDOr;
        this.setCouleur(Color.YELLOW);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Navigateur.png")));
    }

}