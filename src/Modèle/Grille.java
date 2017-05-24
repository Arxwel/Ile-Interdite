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
    
    public Tuile getTuile(int x, int y) {
        return tuiles[x][y];
    }

    private void initGrilleTest() {
        tuiles[0][3] = new Tuile(Zone.LePontDesAbimes, Etat.Sec);
        tuiles[1][2] = new Tuile(Zone.LaPorteDeBronze, Etat.Inondé);
        tuiles[1][1] = new Tuile(Zone.LaCaverneDesOmbres, Etat.Sec);
        tuiles[1][2] = new Tuile(Zone.LaPorteDeFer, Etat.Sec);
        tuiles[1][3] = new Tuile(Zone.LaPorteDOr, Etat.Sec);
        tuiles[1][4] = new Tuile(Zone.LesFalaisesDeLOubli, Etat.Sec);
        tuiles[2][0] = new Tuile(Zone.LePalaisDeCorail, Etat.Sec);
        tuiles[2][1] = new Tuile(Zone.LaPorteDArgent, Etat.Sec);
        tuiles[2][2] = new Tuile(Zone.LesDunesDeLIllusion, Etat.Sombré);
        tuiles[2][3] = new Tuile(Zone.Heliport, Etat.Sec);
        tuiles[2][4] = new Tuile(Zone.LaPorteDeCuivre, Etat.Sec);
        tuiles[2][5] = new Tuile(Zone.LeJardinDesHurlements, Etat.Sec);
        tuiles[3][0] = new Tuile(Zone.LaForetPourpre, Etat.Sec);
        tuiles[3][1] = new Tuile(Zone.LeLagonPerdu, Etat.Inondé);
        tuiles[3][2] = new Tuile(Zone.LeMaraisBrumeux, Etat.Sombré);
        tuiles[3][3] = new Tuile(Zone.Observatoire, Etat.Inondé);
        tuiles[3][4] = new Tuile(Zone.LeRocherFantome, Etat.Sombré);
        tuiles[3][5] = new Tuile(Zone.LaCaverneDuBrasier, Etat.Inondé);
        tuiles[4][1] = new Tuile(Zone.LeTempleDuSoleil, Etat.Sec);
        tuiles[4][2] = new Tuile(Zone.LeTempleDeLaLune, Etat.Sombré);
        tuiles[4][3] = new Tuile(Zone.LePalaisDesMarees, Etat.Sec);
        tuiles[4][4] = new Tuile(Zone.LeValDuCrepuscule, Etat.Sec);
        tuiles[5][2] = new Tuile(Zone.LaTourDuGuet, Etat.Sec);
        tuiles[5][3] = new Tuile(Zone.LeJardinDesMurmures, Etat.Inondé);
    }

}