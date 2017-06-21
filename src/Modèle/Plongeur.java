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
            System.out.println("ListerCasesDispo Plongeur");
            ArrayList<Tuile> tuilesATester = new ArrayList<>();
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            ArrayList<Tuile> tuilesAdj = new ArrayList<>();
            Tuile positionInitiale = this.getPosition();
            Grille g = positionInitiale.getPlateau();
            
            for (int i=0;i<tuilesATester.size();i++){
                Tuile t = tuilesATester.get(i);
                tuilesAdj = t.getAdjacent();
                for (int n=0;n<tuilesAdj.size();n++){
                    Tuile tuile = tuilesAdj.get(n);
                    System.out.println(tuile.getEtat().toString());
                    if(tuile.getEtat()==Etat.Sec){
                        if(!tuilesDispo.contains(tuile)){
                            tuilesDispo.add(tuile);
                        }
                    } else if(tuile.getEtat()==Etat.Inondé) {
                        if(!tuilesATester.contains(tuile)){
                            tuilesATester.add(tuile);
                            }
                        if(!tuilesDispo.contains(tuile)){
                            tuilesDispo.add(tuile);
                        }
                    } else if(tuile.getEtat() == Etat.Sombré){
                        if(!tuilesATester.contains(tuile)){
                            tuilesATester.add(tuile);
                        }
                    }
                }
            }
            return tuilesDispo;
        }
}