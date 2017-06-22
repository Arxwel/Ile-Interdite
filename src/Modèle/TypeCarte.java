/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modèle;

/**
 *
 * @author salmona
 */
public enum TypeCarte {
    TresorMagenta,
    TresorGray,
    TresorCyan,
    TresorOrange,
    SpécialHélicoptère,
    SpécialSacDeSable,
    MontéeEaux;
    
    public String nameEsp() {
        String s = String.valueOf(this.name().charAt(0));
        for(int i = 1; i < this.name().lastIndexOf(""); i++) {
            if(Character.isUpperCase(this.name().charAt(i))) {
                s = s+" "+this.name().charAt(i);
            } else {
                s = s+this.name().charAt(i);
            }
        }
          return s;
    }
}
