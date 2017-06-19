/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modèle.Joueur;

/**
 *
 * @author shermann
 */
public class MessageAventurier extends Message{
    
    private Joueur joueur;
    
    public MessageAventurier(TypeMessage type, Joueur joueur) {
        super(type);
    }
    
    

    /**
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }
    
    
    
}
