package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;

public class Navigateur extends Joueur {
    
    public Navigateur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDOr;
        this.setCouleur(Color.YELLOW);
    }

}