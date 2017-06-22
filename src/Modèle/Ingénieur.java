package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Ingénieur extends Joueur {
    
    public Ingénieur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeBronze;
        this.setCouleur(Color.RED);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Ingenieur.png")));
    }
    
    @Override //peut assecher 2 tuiles a la fois
    public void assecher() {
        super.assecher();
        if (this.isAssPossible()) {
        int reponse = JOptionPane.showConfirmDialog(
            null,
            "Voulez-vous assecher une deuxième tuile gratuitement ?",
            "2ème Assechement",
            JOptionPane.YES_NO_OPTION);

        if(reponse == JOptionPane.YES_OPTION){
            super.assecher();
        }
        }
    }

}