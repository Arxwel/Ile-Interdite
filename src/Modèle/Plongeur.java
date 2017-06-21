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

    @Override //peut traverser des tuiles inondées et des tuiles sombrées et peut s'arrêter sur des tuiles inondées
	public ArrayList<Tuile> listerCasesDispo() {
            Tuile posJ = this.getPosition();
            ArrayList<Tuile> tuilesATester = new ArrayList<>();
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            tuilesATester.add(posJ);
            Grille g = posJ.getPlateau();
            
            for (Tuile t: tuilesATester) {
                if (t==posJ) {
                    tuilesATester.addAll(tuilesDispo);
                    System.out.println("Tuile Départ");
                } else {
                    System.out.println("Tuile "+t.getIntitule());
                    if (t.getEtat()==Etat.Sec) {
                        System.out.println("Sec");
                        tuilesDispo.add(t);
                        System.out.println("ajouté");
                    } else if (t.getEtat()==Etat.Inondé) {
                        System.out.println("inondé");
                        tuilesDispo.add(t);
                        System.out.println("ajouté");
                        for(Tuile tAdj: t.getAdjacent()) {
                            if (!tuilesATester.contains(tAdj)) {
                                tuilesATester.add(tAdj);
                            }
                        }
                    } else if (t.getEtat()==Etat.Sombré) {
                        System.out.println("sombré");
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