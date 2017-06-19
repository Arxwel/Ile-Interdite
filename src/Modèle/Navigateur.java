package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Navigateur extends Joueur {

    public Navigateur(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDOr;
        this.setCouleur(Color.YELLOW);
    }

}