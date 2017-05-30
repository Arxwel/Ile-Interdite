package Modèle;

import java.awt.Color;
import java.util.ArrayList;

public class Pilote extends Joueur {

    public Pilote(String nom, Color couleur) {
        super(nom, couleur);
        this.spawnPoint = Zone.Heliport;
    }

    @Override
	public ArrayList<Tuile> listerCasesDispo() {
            ArrayList<Tuile> dispo = new ArrayList<>();
            Coordonnees coor = this.getPosition().getCoordonees();
            for (Tuile[] tArr: this.getPosition().getPlateau().getTuiles()) {
                for (Tuile t: tArr) {
                    if (t != null && t.getCoordonees() != coor && t.getEtat() == Etat.Sec) {
                        dispo.add(t);
                    }
                }
            }
            return dispo;
	}

}