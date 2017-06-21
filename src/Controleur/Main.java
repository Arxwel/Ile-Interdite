/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import static Controleur.Controleur.getJoueurs;
import Modèle.CarteTresor;
import Modèle.Joueur;
import Vue.VueAventurier;
import Vue.VueEcranTitre;
import Vue.VueInscription;
import Vue.VuePlateau;
import java.awt.Color;

/**
 *
 * @author salmona
 */
public class Main {
    
private static VueAventurier vj1,vj2,vj3,vj4;

public static void main(String[] args) {
        // Initalise la Vue Inscription et le controleur et rends la vue inscription visible et fonctionelle
        
        Controleur c = new Controleur();
        
        for (Joueur j: c.getJoueurs()) {
            System.out.println(j.getNom());
            for (CarteTresor ca : j.getMainJoueur()) {
                System.out.println(ca.getType().toString());
            }
        }
        
        
        
        vj1 = new VueAventurier(c.getJoueurs().get(0));
        c.getJoueurs().get(0).setVueAventurier(vj1);
        vj1.getWindow().setLocation(15, 15);
        vj1.getWindow().setVisible(true);
        vj1.setObservateur(c);
        
        vj2 = new VueAventurier(c.getJoueurs().get(1));
        c.getJoueurs().get(1).setVueAventurier(vj2);
        vj2.getWindow().setLocation(15, 265);
        vj2.getWindow().setVisible(true);
        vj2.setObservateur(c);
        
        if(c.getJoueurs().size() >=3) {
            vj3 = new VueAventurier(c.getJoueurs().get(2));
            c.getJoueurs().get(2).setVueAventurier(vj3);
            vj3.getWindow().setLocation(15, 515);
            vj3.getWindow().setVisible(true);
            vj3.setObservateur(c);
        }
        
        if(c.getJoueurs().size() ==4) {
            vj4 = new VueAventurier(c.getJoueurs().get(3));
            c.getJoueurs().get(3).setVueAventurier(vj4);
            vj4.getWindow().setLocation(15, 765);
            vj4.getWindow().setVisible(true);
            vj4.setObservateur(c);
        }

        
        c.play();
    } 

    /**
     * @return the vueInscription
     */
}
