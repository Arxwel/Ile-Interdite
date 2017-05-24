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
        tuiles[0][3] = new Tuile(Zone.LePontDesAbimes, Etat.Sec, this);
        tuiles[1][2] = new Tuile(Zone.LaPorteDeBronze, Etat.Inondé, this);
        tuiles[1][1] = new Tuile(Zone.LaCaverneDesOmbres, Etat.Sec, this);
        tuiles[1][2] = new Tuile(Zone.LaPorteDeFer, Etat.Sec, this);
        tuiles[1][3] = new Tuile(Zone.LaPorteDOr, Etat.Sec, this);
        tuiles[1][4] = new Tuile(Zone.LesFalaisesDeLOubli, Etat.Sec, this);
        tuiles[2][0] = new Tuile(Zone.LePalaisDeCorail, Etat.Sec, this);
        tuiles[2][1] = new Tuile(Zone.LaPorteDArgent, Etat.Sec, this);
        tuiles[2][2] = new Tuile(Zone.LesDunesDeLIllusion, Etat.Sombré, this);
        tuiles[2][3] = new Tuile(Zone.Heliport, Etat.Sec, this);
        tuiles[2][4] = new Tuile(Zone.LaPorteDeCuivre, Etat.Sec, this);
        tuiles[2][5] = new Tuile(Zone.LeJardinDesHurlements, Etat.Sec, this);
        tuiles[3][0] = new Tuile(Zone.LaForetPourpre, Etat.Sec, this);
        tuiles[3][1] = new Tuile(Zone.LeLagonPerdu, Etat.Inondé, this);
        tuiles[3][2] = new Tuile(Zone.LeMaraisBrumeux, Etat.Sombré, this);
        tuiles[3][3] = new Tuile(Zone.Observatoire, Etat.Inondé, this);
        tuiles[3][4] = new Tuile(Zone.LeRocherFantome, Etat.Sombré, this);
        tuiles[3][5] = new Tuile(Zone.LaCaverneDuBrasier, Etat.Inondé, this);
        tuiles[4][1] = new Tuile(Zone.LeTempleDuSoleil, Etat.Sec, this);
        tuiles[4][2] = new Tuile(Zone.LeTempleDeLaLune, Etat.Sombré, this);
        tuiles[4][3] = new Tuile(Zone.LePalaisDesMarees, Etat.Sec, this);
        tuiles[4][4] = new Tuile(Zone.LeValDuCrepuscule, Etat.Sec, this);
        tuiles[5][2] = new Tuile(Zone.LaTourDuGuet, Etat.Sec, this);
        tuiles[5][3] = new Tuile(Zone.LeJardinDesMurmures, Etat.Inondé, this);
    }

}