/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Modèle.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class VuePlateau {
    Controleur controleur;
    public static void main(String [] args) {
        VuePlateau v = new VuePlateau();
    }
    public VuePlateau() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setBackground(Color.BLUE);
        
        ArrayList<JLabel> labelTuiles = new ArrayList<>();
        
        JPanel mapPanel = new JPanel(new GridLayout(6,6));
        window.add(mapPanel);
        Color colorBack;
        for (int x=0; x<5; x++) {
            for (int y=0; y<5; y++) {
                //Tuile t = controleur.getGrille().getTuile(x, y);
                // Test
                Grille g = new Grille();
                Tuile t = g.getTuile(x,y);
                //fin du test
                if (t==null || t.getEtat()==Etat.Sombré) {
                    mapPanel.add(new JPanel());
                } else {
                    if (t.getEtat()==Etat.Inondé) {
                        colorBack = Color.BLUE;
                    } else {
                        colorBack = Color.YELLOW;
                    }
                    labelTuiles.add(new JLabel(t.getIntitule().toString()));
                    labelTuiles.get(labelTuiles.size()-1).setBackground(colorBack);
                    mapPanel.add(labelTuiles.get(labelTuiles.size()-1));
                }
            }
        }
        window.setVisible(true);
        
    }
    
    public void setControleur(Controleur c) {
        this.controleur=c;    }
}
