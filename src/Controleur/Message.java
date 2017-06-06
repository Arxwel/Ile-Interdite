/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

/**
 *
 * @author pasdelor
 */
public class Message {
    private TypeMessage type;
    
    public Message(TypeMessage type) {
        this.setType(type);
    }
    
    public TypeMessage getType() {
        return type;
    }
    
    public void setType(TypeMessage type) {
        this.type = type;
    }
}
