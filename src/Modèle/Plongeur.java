package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Plongeur extends Joueur {
    
    public Plongeur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeFer;
        this.setCouleur(Color.BLACK);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Plongeur.png")));
    }

    @Override //peut traverser des tuiles inondées et des tuiles sombrées
	public ArrayList<Tuile> listerCasesDispo() {
            Tuile posJ = this.getPosition();
            ArrayList<Tuile> tuilesATester = new ArrayList<>();
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            tuilesATester.add(posJ);
            Grille g = posJ.getPlateau();
            
            for (Tuile t: tuilesATester) {
                if (t==posJ) {
                    tuilesATester.addAll(tuilesDispo);
                } else {
                    if (t.getEtat()==Etat.Sec) {
                        tuilesDispo.add(t);
                    } else if (t.getEtat()==Etat.Inondé) {
                        tuilesDispo.add(t);
                        for(Tuile tAdj: t.getAdjacent()) {
                            if (!tuilesATester.contains(tAdj)) {
                                tuilesATester.add(tAdj);
                            }
                        }
                    } else if (t.getEtat()==Etat.Sombré) {
                       for(Tuile tAdj: t.getAdjacent()) {
                            if (!tuilesATester.contains(tAdj)) {
                                tuilesATester.add(tAdj);
                            }
                        } 
                    }
                }
            }
            return tuilesDispo;
        }
}