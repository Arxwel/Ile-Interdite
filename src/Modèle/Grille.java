package Modèle;

import java.util.*;

public class Grille {

	Tuile[][] tuiles;
        
        public Grille() {
            tuiles = new Tuile[6][6];
        }
        
        public void initGrille() {
            for (int l = 0; l < 6; l++) {
                for (int c = 0; l < 6; l++) {
                    if ((l == 0 && c == 0) || (l == 0 && c == 1) || (l == 1 && c == 0) || (l == 0 && c == 4) || (l == 0 && c == 5) || (l == 1 && c == 5) || (l == 4 && c == 0) || (l == 5 && c == 0) || (l == 5 && c == 1) || (l == 4 && c == 5) || (l == 5 && c == 4) || (l == 5 && c == 5)) {
                        tuiles[l][c] = null;
                    } else if (l == 0 && c == 2) {
                        
                    }
                }
            }
        }

}