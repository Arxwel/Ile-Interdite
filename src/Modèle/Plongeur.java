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
            System.out.println("ListerCasesDispo Plongeur");            
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            tuilesDispo = (ArrayList<Tuile>) this.getPosition().tuilesPlongeurs();
            ArrayList<Tuile> casesAEnlever = new ArrayList<>();
            for (int i =0;i<tuilesDispo.size();i++){ 
                if (tuilesDispo.get(i).getEtat().equals(Etat.Sombré)){
                    casesAEnlever.add(tuilesDispo.get(i));
                }
            }
            tuilesDispo.removeAll(casesAEnlever);
            tuilesDispo.remove(this.getPosition());
            return tuilesDispo;            
        }
}