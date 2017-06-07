/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Modèle.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class VuePlateau {
    private Controleur controleur;
    private JFrame window;
    public static void main(String [] args) {
        VuePlateau v = new VuePlateau();
    }
    public VuePlateau() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(1024, 768);
        window.setBackground(Color.BLUE);
        
        ArrayList<JPanel> caseTuiles = new ArrayList<>();
        ArrayList<JPanel> upGridPanels = new ArrayList<>();
        ArrayList<JPanel> downGridPanels = new ArrayList<>();
        
        
        JPanel mapPanel = new JPanel(new GridLayout(6,6));
        window.add(mapPanel);
        
        Color colorBack;
        
        for (int x=0; x<=5; x++) {
            for (int y=0; y<=5; y++) {
                //Tuile t = controleur.getGrille().getTuile(x, y);
                // Test
                Grille g = new Grille();
                Tuile t = g.getTuile(x,y);
                //fin du test
                if (t==null) {
                    caseTuiles.add(new JPanel());
                    caseTuiles.get(caseTuiles.size()-1).setBackground(new Color(0,191,255)); //deepsky blue
                } else {
                    if (t.getEtat()==Etat.Inondé) {
                        colorBack = new Color(10,110,230); //bleu clair
                    } else if (t.getEtat()==Etat.Sec) {
                        colorBack = new Color(240,230,140); //jaune sable
                    } else {
                        colorBack = Color.LIGHT_GRAY;
                    }
                    caseTuiles.add(new JPanel(new BorderLayout()));
                    //upGridPanels.add(new JPanel(new GridLayout(1,4)));
                    //downGridPanels.add(new JPanel(new BorderLayout(1,3)));
                    
                    caseTuiles.get(caseTuiles.size()-1).setBackground(colorBack);
                    caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(t.getIntitule().toString()),BorderLayout.CENTER);
                    //caseTuiles.get(caseTuiles.size()-1).add(upGridPanels.get(caseTuiles.size()-1),BorderLayout.NORTH);
                    //caseTuiles.get(caseTuiles.size()-1).add(downGridPanels.get(caseTuiles.size()-1),BorderLayout.SOUTH);
                    
                            
                    /*for (Joueur j: t.getLocataires()) {
                        upGridPanels.get(caseTuiles.size()-1).add(new JPanel());//A finir
                    }*/
                    
                    
                    if (t.getIntitule() == Zone.Heliport) {
                       caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                /*if (t.getReliqueDispo() != null) {
                    
                }*/
                
            }
        }
        for (JPanel jpp: caseTuiles) {
            mapPanel.add(jpp);
        }
        window.setVisible(true);
        
    }
    
    public void setControleur(Controleur c) {
        this.controleur=c;    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }
}
