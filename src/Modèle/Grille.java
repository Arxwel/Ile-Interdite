package Modèle;

import java.util.*;

public class Grille {

    Tuile[][] tuiles;
        
    public Grille() {
        tuiles = new Tuile[6][6];
        initGrilleTest();
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

    private void initGrilleTest() {
        tuiles[0][3] = new Tuile();
        tuiles[1][2] = ;
        tuiles[1][1] = ;
        tuiles[1][2] = ;
        tuiles[1][3] = ;
        tuiles[1][4] = ;
        tuiles[2][0] = ;
        tuiles[2][1] = ;
        tuiles[2][2] = ;
        tuiles[2][3] = ;
        tuiles[2][4] = ;
        tuiles[2][5] = ;
        tuiles[3][0] = ;
        tuiles[3][1] = ;
        tuiles[3][2] = ;
        tuiles[3][3] = ;
        tuiles[3][4] = ;
        tuiles[3][5] = ;
        tuiles[4][1] = ;
        tuiles[4][2] = ;
        tuiles[4][3] = ;
        tuiles[4][4] = ;
        tuiles[5][2] = ;
        tuiles[5][3] = ;
    }

}