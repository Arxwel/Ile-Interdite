package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Pilote extends Joueur {

    public Pilote(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.Heliport;
        this.setCouleur(Color.BLUE);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Pilote.png")));
    }

    @Override //peut se deplacer sur n'importe quelle case seche du plateau
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