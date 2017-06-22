/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modèle.CarteTresor;
import Modèle.Joueur;

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
