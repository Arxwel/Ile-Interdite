/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modèle.Joueur;
import Vue.VueAventurier;
import Vue.VueInscription;
import Vue.VuePlateau;
import java.awt.Color;

/**
 *
 * @author salmona
 */
public class Main {
    private static VueInscription vueInscription;
    private static VuePlateau vuePlateau;
    private static VueAventurier vj1,vj2,vj3,vj4;
public static void main(String[] args) {
        // Initalise la Vue Inscription et le controleur et rends la vue inscription visible et fonctionelle
        vueInscription = new VueInscription();
        
        Controleur c = new Controleur();
        
        getVueInscription().setObservateur(c);
        getVueInscription().getWindow().setVisible(true);
        
        // Assure l'inscription est complétée et que le jeu peut commencer
        while(!c.isSuite()) {
            System.out.print("");
        }
        //Crée les vues aventurier et plateau et les afficher
        vj1 = new VueAventurier(c.getJoueurs().get(0));
        vj2 = new VueAventurier(c.getJoueurs().get(1));
        vj3 = new VueAventurier(c.getJoueurs().get(2));
        vj4 = new VueAventurier(c.getJoueurs().get(3));
        c.getJoueurs().get(0).setVueAventurier(vj1);
        c.getJoueurs().get(1).setVueAventurier(vj2);
        c.getJoueurs().get(2).setVueAventurier(vj3);
        c.getJoueurs().get(3).setVueAventurier(vj4);
        vj1.getWindow().setVisible(true);
        vj2.getWindow().setVisible(true);
        vj3.getWindow().setVisible(true);
        vj4.getWindow().setVisible(true);
        vuePlateau = new VuePlateau(c);
        vuePlateau.getWindow().setVisible(true);
        
        c.play();
    } 

    /**
     * @return the vueInscription
     */
    public static VueInscription getVueInscription() {
        return vueInscription;
    }
}
