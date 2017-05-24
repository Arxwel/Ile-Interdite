package Modèle;

import java.awt.Color;

public class CarteRelique extends CarteTresor {

	private Color couleur;
        public CarteRelique(Color c) {
            super();
            this.setCouleur(c);
        }

    private void setCouleur(Color c) {
        this.couleur = c;
    }

}