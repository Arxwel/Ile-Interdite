package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Pilote extends Joueur {

    public Pilote(String nom, Color couleur) {
        super(nom, couleur);
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
            Coordonnees coor = this.getPosition().getCoordonees();
            
	}

}