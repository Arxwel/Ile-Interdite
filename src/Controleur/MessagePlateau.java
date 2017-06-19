package Controleur;

import Modèle.Coordonnees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salmona
 */
public class MessagePlateau extends Message {
    private Coordonnees coo;
    public MessagePlateau(TypeMessage type, Coordonnees coo) {
        super(type);
        this.coo = coo;
    }

    public Coordonnees getCoo() {
        return coo;
    }
    
}
