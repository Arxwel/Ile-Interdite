package Modèle;

import java.awt.Color;

public class CarteRelique extends CarteTresor {

    private Color couleur;
    public CarteRelique(Color c) {
        this.setCouleur(c);
    }

    private void setCouleur(Color c) {
        this.couleur = c;
    }
    
    
    public Color getColor() {
        return this.couleur;
    }
}