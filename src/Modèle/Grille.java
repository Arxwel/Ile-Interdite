package Modèle;

import java.awt.Color;
import java.util.*;
import java.util.Arrays;

public class Grille {

    Tuile[][] tuiles;
        
    public Grille(boolean modeDebug) {
        tuiles = new Tuile[6][6];
        if (modeDebug) {
            initGrilleTest();
        } else {
            initGrille();
        }
    }
    
    //crée une grille aléatoirement
    public void initGrille() {
        int tuilesJouables[][] = {{0,2},{0,3},{1,1},{1,2},{1,3},{1,4},{2,0},{2,1},{2,2},{2,3},{2,4},{2,5},{3,0},{3,1},{3,2},{3,3},{3,4},{3,5},{4,1},{4,2},{4,3},{4,4},{5,2},{5,3}};
        //On met toutes les valeurs de la classe énumérée Zone dans une arraylist
        ArrayList<Zone> listeZones = new ArrayList<Zone>(EnumSet.allOf(Zone.class));
        Collections.shuffle(listeZones);
        
        ArrayList<Coordonnees> coos = new ArrayList<>();
        
        int index = 0;
        for (int[] inTab: tuilesJouables){
            coos.add(new Coordonnees(inTab[0],inTab[1]));
        }
        
        for (Coordonnees c: coos) {
            tuiles[c.getX()][c.getY()] = new Tuile(listeZones.get(index),Etat.Sec,this,c);
            index++;
        }
    }
    
    //crée la grille de test
    private void initGrilleTest() {
        System.out.println("Initialisation de la grille de test");
        tuiles[0][2] = new Tuile(Zone.LePontDesAbimes, Etat.Sec, this, new Coordonnees(0,2));
        tuiles[0][3] = new Tuile(Zone.LaPorteDeBronze, Etat.Inondé, this, new Coordonnees(0,3));
        tuiles[1][1] = new Tuile(Zone.LaCaverneDesOmbres, Etat.Sec, this, new Coordonnees(1,1), Color.MAGENTA);
        tuiles[1][2] = new Tuile(Zone.LaPorteDeFer, Etat.Sec, this, new Coordonnees(1,2));
        tuiles[1][3] = new Tuile(Zone.LaPorteDOr, Etat.Sec, this, new Coordonnees(1,3));
        tuiles[1][4] = new Tuile(Zone.LesFalaisesDeLOubli, Etat.Sec, this, new Coordonnees(1,4));
        tuiles[2][0] = new Tuile(Zone.LePalaisDeCorail, Etat.Sec, this, new Coordonnees(2,0), Color.CYAN);
        tuiles[2][1] = new Tuile(Zone.LaPorteDArgent, Etat.Sec, this, new Coordonnees(2,1));
        tuiles[2][2] = new Tuile(Zone.LesDunesDeLIllusion, Etat.Sombré, this, new Coordonnees(2,2));
        tuiles[2][3] = new Tuile(Zone.Heliport, Etat.Sec, this, new Coordonnees(2,3));
        tuiles[2][4] = new Tuile(Zone.LaPorteDeCuivre, Etat.Sec, this, new Coordonnees(2,4));
        tuiles[2][5] = new Tuile(Zone.LeJardinDesHurlements, Etat.Sec, this, new Coordonnees(2,5), Color.ORANGE);
        tuiles[3][0] = new Tuile(Zone.LaForetPourpre, Etat.Sec, this, new Coordonnees(3,0));
        tuiles[3][1] = new Tuile(Zone.LeLagonPerdu, Etat.Inondé, this, new Coordonnees(3,1));
        tuiles[3][2] = new Tuile(Zone.LeMaraisBrumeux, Etat.Sombré, this, new Coordonnees(3,2));
        tuiles[3][3] = new Tuile(Zone.Observatoire, Etat.Inondé, this, new Coordonnees(3,3));
        tuiles[3][4] = new Tuile(Zone.LeRocherFantome, Etat.Sombré, this, new Coordonnees(3,4));
        tuiles[3][5] = new Tuile(Zone.LaCaverneDuBrasier, Etat.Inondé, this, new Coordonnees(3,5), Color.MAGENTA);
        tuiles[4][1] = new Tuile(Zone.LeTempleDuSoleil, Etat.Sec, this, new Coordonnees(4,1), Color.GRAY);
        tuiles[4][2] = new Tuile(Zone.LeTempleDeLaLune, Etat.Sombré, this, new Coordonnees(4,2), Color.GRAY);
        tuiles[4][3] = new Tuile(Zone.LePalaisDesMarees, Etat.Sec, this, new Coordonnees(4,3), Color.CYAN);
        tuiles[4][4] = new Tuile(Zone.LeValDuCrepuscule, Etat.Sec, this, new Coordonnees(4,4));
        tuiles[5][2] = new Tuile(Zone.LaTourDuGuet, Etat.Sec, this, new Coordonnees(5,2));
        tuiles[5][3] = new Tuile(Zone.LeJardinDesMurmures, Etat.Inondé, this, new Coordonnees(5,3), Color.ORANGE);
        
    }
    
    public Tuile[][] getTuiles() {
        return this.tuiles;
    }
    
    public Tuile getTuile(int x, int y) {
        return tuiles[x][y];
    }
    
    public Tuile getTuile(Zone libelle) {
        Tuile t = null;
        Tuile tTemp;
        
        for (int x=0;x<6;x++) {
            for (int y=0;y<6;y++) {
                tTemp = this.getTuile(x, y);
                if (tTemp!=null) {
                    if (tTemp.getIntitule()==libelle) {
                        t = tTemp;
                    }
                }
            }
        }
        
        return t;
    }
    
    public Tuile getTuile(Coordonnees coo) {
        return tuiles[coo.getX()][coo.getY()];
    }
    

}
