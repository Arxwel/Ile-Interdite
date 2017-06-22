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
import Vue.VueMonteeEaux;
import Vue.VuePlateau;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author salmona
 */
public class Main {

public static void main(String[] args) {
        // Initalise la Vue Inscription et le controleur et rends la vue inscription visible et fonctionelle
        
        Controleur c = new Controleur();
        
        for (Joueur j: c.getJoueurs()) {
            System.out.println(j.getNom());
            for (CarteTresor ca : j.getMainJoueur()) {
                System.out.println(ca.getType().toString());
            }
        }
        
        
        
        c.play();
    } 

    /**
     * @return the vueInscription
     */
}
