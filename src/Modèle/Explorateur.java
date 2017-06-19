package Modèle;

import Controleur.Controleur;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Explorateur extends Joueur {
    
    public Explorateur(String nom, Controleur controleur) {
        super(nom, controleur);
        this.spawnPoint = Zone.LaPorteDeCuivre;
        this.setCouleur(Color.GREEN);
        this.setImage(new ImageIcon(this.getClass().getResource("/ImagesAventuriers/Explorateur.png")));
    }

    @Override //peut se deplaser en diagonale
	public ArrayList<Tuile> listerCasesDispo() {
           ArrayList<Tuile> tuilesDispo = super.listerCasesDispo();
           Coordonnees coo = this.getPosition().getCoordonees();
           
           Grille g = this.getPosition().getPlateau();
           Tuile t;
           Coordonnees[] cooAdj = new Coordonnees[4];
           cooAdj[0]=new Coordonnees(coo.getX()+1,coo.getY()+1);
           cooAdj[1]=new Coordonnees(coo.getX()-1,coo.getY()+1);
           cooAdj[2]=new Coordonnees(coo.getX()+1,coo.getY()-1);
           cooAdj[3]=new Coordonnees(coo.getX()-1,coo.getY()-1);
           for (Coordonnees c: cooAdj) {
               if (c.getX()>0 && c.getX()<6 && c.getY()>0 && c.getY()<6) {
                   t = g.getTuile(c);
                   if (t!=null && t.getEtat()==Etat.Sec) {
                       tuilesDispo.add(t);
                   }
               }
           }
           
            return tuilesDispo;
	}
        
    @Override //peut assecher des tuiles en diagonale
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

}
