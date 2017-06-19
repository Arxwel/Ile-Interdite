package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;

public class Plongeur extends Joueur {
    
    public Plongeur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeFer;
        this.setCouleur(Color.BLACK);
    }

    @Override //peut traverser des tuiles inondées et des tuiles sombrées
	public ArrayList<Tuile> listerCasesDispo() {
            Tuile posJ = this.getPosition();
            ArrayList<Tuile> tuilesATester = new ArrayList<>();
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            tuilesATester.add(posJ);
            Grille g = posJ.getPlateau();
            Coordonnees coo;
            for (Tuile t: tuilesATester) {
                if (t.equals(posJ)) {
                    coo = t.getCoordonees();
                    tuilesATester.add(g.getTuile(coo.getX()+1, coo.getY()));
                    tuilesATester.add(g.getTuile(coo.getX()-1, coo.getY()));
                    tuilesATester.add(g.getTuile(coo.getX(), coo.getY()+1));
                    tuilesATester.add(g.getTuile(coo.getX(), coo.getY()-1));
                } else {
                    if (t.getEtat() == Etat.Sec) {
                        tuilesATester.remove(t);
                        tuilesDispo.add(t);
                    } else {
                        coo = t.getCoordonees();
                    tuilesATester.add(g.getTuile(coo.getX()+1, coo.getY()));
                    tuilesATester.add(g.getTuile(coo.getX()-1, coo.getY()));
                    tuilesATester.add(g.getTuile(coo.getX(), coo.getY()+1));
                    tuilesATester.add(g.getTuile(coo.getX(), coo.getY()-1));
                    }
                }
            }
            return tuilesDispo;
        }
}