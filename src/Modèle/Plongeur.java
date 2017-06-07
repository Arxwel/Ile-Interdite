package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Plongeur extends Joueur {

    public Plongeur(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDeFer;
        this.setCouleur(Color.BLACK);
    }

    @Override //peut traverser des tuiles inondées et des tuiles sombrées
	public ArrayList<Tuile> listerCasesDispo() {
            ArrayList<Tuile> tuileslibres = new ArrayList<>();

           if (((getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX()-1,getPosition().getCoordonees().getY()) != null))) {
               //tuile à gauche du joueur
               tuileslibres.add(getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX()-1,getPosition().getCoordonees().getY()));
           } else if(((getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX()+1,getPosition().getCoordonees().getY()) != null))) {
               //tuile à droite du joueur
               tuileslibres.add(getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX()+1,getPosition().getCoordonees().getY()));
           } else if(((getPosition().getPlateau().getTuile(getPosition().getCoordonees().getY()-1,getPosition().getCoordonees().getY()) != null))) {
               //tuile en dessous du joueur
               tuileslibres.add(getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX(),getPosition().getCoordonees().getY()-1));
           } else if(((getPosition().getPlateau().getTuile(getPosition().getCoordonees().getY()+1,getPosition().getCoordonees().getY()) != null))){
               //tuile au-dessus du joueur
               tuileslibres.add(getPosition().getPlateau().getTuile(getPosition().getCoordonees().getX(),getPosition().getCoordonees().getY()+1));
           }
           return tuileslibres;
	}

}