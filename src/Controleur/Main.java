/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Vue.VueInscription;
import Vue.VuePlateau;

/**
 *
 * @author salmona
 */
public class Main {
    private static VueInscription vueInscription;
    private static VuePlateau vuePlateau;
public static void main(String[] args) {
        vueInscription = new VueInscription();
        Controleur c = new Controleur();
        getVueInscription().setObservateur(c);
        getVueInscription().getWindow().setVisible(true);
        while(!c.isSuite()) {
            System.out.print("");
         }
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
