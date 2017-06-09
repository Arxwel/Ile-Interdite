package Modèle;

import java.util.*;
import java.util.Arrays;

public class Grille {

    Tuile[][] tuiles;
        
    public Grille() {
        tuiles = new Tuile[6][6];
        initGrilleTest();
    }
    
    //crée une grille aléatoirement
    public void initGrille() {
        Random randomGenerator = new Random();
        //On met toutes les valeurs de la classe énumérée Zone dans une arraylist
        ArrayList<Zone> listezones = new ArrayList<Zone>(EnumSet.allOf(Zone.class));
        int index;
        //On initialise les tuiles impossibles à atteindre pour pouvoir faire des tests plus tard
        tuiles[0][0] = null;
        tuiles[0][1] = null;
        tuiles[0][4] = null;
        tuiles[0][5] = null;
        tuiles[1][0] = null;
        tuiles[1][5] = null;
        tuiles[4][0] = null;
        tuiles[4][5] = null;
        tuiles[5][0] = null;
        tuiles[5][1] = null;
        tuiles[5][4] = null;
        tuiles[5][5] = null;
        
        for (int x = 0; x < 6; x=x+5) {
            for (int y = 2; y < 4; y++) {
                index = randomGenerator.nextInt(listezones.size()); //On prends l'index d'une zone au hasard parmi celles qui restent
                tuiles[x][y] = new Tuile(listezones.get(index),Etat.Sec, this, new Coordonnees(x,y)); //On créé la tuile avec la zone récupérée, on initialise à Sec
                listezones.remove(index);
            }
        }
        
        for (int x = 1; x < 5; x=x+3) {
            for (int y = 1; y < 5; y++) {
                index = randomGenerator.nextInt(listezones.size());
                tuiles[x][y] = new Tuile(listezones.get(index),Etat.Sec, this, new Coordonnees(x,y));
                listezones.remove(index);
            }
        }
        
        for (int x = 2; x < 4; x=x++) {
            for (int y = 0; y < 6; y++) {
                index = randomGenerator.nextInt(listezones.size());
                tuiles[x][y] = new Tuile(listezones.get(index),Etat.Sec, this, new Coordonnees(x,y));
                listezones.remove(index);
            }
        }


    }
    
    public Tuile[][] getTuiles() {
        return this.tuiles;
    }
    
    public Tuile getTuile(int x, int y) {
        return tuiles[x][y];
    }
    //crée la grille de test
    private void initGrilleTest() {
        //Tuiles Impossibles
        tuiles[0][0] = null;
        tuiles[0][1] = null;
        tuiles[0][4] = null;
        tuiles[0][5] = null;
        tuiles[1][0] = null;
        tuiles[1][5] = null;
        tuiles[4][0] = null;
        tuiles[4][5] = null;
        tuiles[5][0] = null;
        tuiles[5][1] = null;
        tuiles[5][4] = null;
        tuiles[5][5] = null;
        //Tuiles existantes
        tuiles[0][2] = new Tuile(Zone.LePontDesAbimes, Etat.Sec, this, new Coordonnees(0,2));
        tuiles[0][3] = new Tuile(Zone.LaPorteDeBronze, Etat.Inondé, this, new Coordonnees(0,3));
        tuiles[1][1] = new Tuile(Zone.LaCaverneDesOmbres, Etat.Sec, this, new Coordonnees(1,1));
        tuiles[1][2] = new Tuile(Zone.LaPorteDeFer, Etat.Sec, this, new Coordonnees(1,2));
        tuiles[1][3] = new Tuile(Zone.LaPorteDOr, Etat.Sec, this, new Coordonnees(1,3));
        tuiles[1][4] = new Tuile(Zone.LesFalaisesDeLOubli, Etat.Sec, this, new Coordonnees(1,4));
        tuiles[2][0] = new Tuile(Zone.LePalaisDeCorail, Etat.Sec, this, new Coordonnees(2,0));
        tuiles[2][1] = new Tuile(Zone.LaPorteDArgent, Etat.Sec, this, new Coordonnees(2,1));
        tuiles[2][2] = new Tuile(Zone.LesDunesDeLIllusion, Etat.Sombré, this, new Coordonnees(2,2));
        tuiles[2][3] = new Tuile(Zone.Heliport, Etat.Sec, this, new Coordonnees(2,3));
        tuiles[2][4] = new Tuile(Zone.LaPorteDeCuivre, Etat.Sec, this, new Coordonnees(2,4));
        tuiles[2][5] = new Tuile(Zone.LeJardinDesHurlements, Etat.Sec, this, new Coordonnees(2,5));
        tuiles[3][0] = new Tuile(Zone.LaForetPourpre, Etat.Sec, this, new Coordonnees(3,0));
        tuiles[3][1] = new Tuile(Zone.LeLagonPerdu, Etat.Inondé, this, new Coordonnees(3,1));
        tuiles[3][2] = new Tuile(Zone.LeMaraisBrumeux, Etat.Sombré, this, new Coordonnees(3,2));
        tuiles[3][3] = new Tuile(Zone.Observatoire, Etat.Inondé, this, new Coordonnees(3,3));
        tuiles[3][4] = new Tuile(Zone.LeRocherFantome, Etat.Sombré, this, new Coordonnees(3,4));
        tuiles[3][5] = new Tuile(Zone.LaCaverneDuBrasier, Etat.Inondé, this, new Coordonnees(3,5));
        tuiles[4][1] = new Tuile(Zone.LeTempleDuSoleil, Etat.Sec, this, new Coordonnees(4,1));
        tuiles[4][2] = new Tuile(Zone.LeTempleDeLaLune, Etat.Sombré, this, new Coordonnees(4,2));
        tuiles[4][3] = new Tuile(Zone.LePalaisDesMarees, Etat.Sec, this, new Coordonnees(4,3));
        tuiles[4][4] = new Tuile(Zone.LeValDuCrepuscule, Etat.Sec, this, new Coordonnees(4,4));
        tuiles[5][2] = new Tuile(Zone.LaTourDuGuet, Etat.Sec, this, new Coordonnees(5,2));
        tuiles[5][3] = new Tuile(Zone.LeJardinDesMurmures, Etat.Inondé, this, new Coordonnees(5,3));
        
    }

}
