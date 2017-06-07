package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Explorateur extends Joueur {

    public Explorateur(String nom) {
        super(nom);
        this.spawnPoint = Zone.LaPorteDeCuivre;
        this.setCouleur(Color.GREEN);
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            Coordonnees coo = this.getPosition().getCoordonees();
            for(int x=-1; x<=1; x++) {
                for (int y=-1; y<=1; y++) {
                    Tuile t = this.getPosition().getPlateau().getTuile(coo.getX()+x,coo.getY()+y);
                    if ((t!=null)&&!((x==0)&&(y==0))&&t.getEtat()==Etat.Sec) {
                        tuilesDispo.add(this.getPosition().getPlateau().getTuile(coo.getX()+x, coo.getY()+y));
                    }
                }
            }
            return tuilesDispo;
	}
        
    @Override
        public ArrayList<Tuile> listerTuilesAssechables() {
            ArrayList<Tuile> tuilesDispo = new ArrayList<>();
            Coordonnees coo = this.getPosition().getCoordonees();
            for(int x=-1; x<=1; x++) {
                for (int y=-1; y<=1; y++) {
                    Tuile t = this.getPosition().getPlateau().getTuile(coo.getX()+x,coo.getY()+y);
                    if ((t!=null)&&!((x==0)&&(y==0))&&t.getEtat()==Etat.Inondé) {
                        tuilesDispo.add(this.getPosition().getPlateau().getTuile(coo.getX()+x, coo.getY()+y));
                    }
                }
            }
            return tuilesDispo;
        }

    @Override
	public void assécher() {
		// TODO - implement Explorateur.assécher
		throw new UnsupportedOperationException();
	}

}
