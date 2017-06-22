/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Observateur;
import Modèle.CarteTresor;
import Modèle.Joueur;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author AoRailgun
 */
public class VueDefausse {
    
    private JFrame window;
    private CarteTresor carteDefaussee;
    private ArrayList<JButton> cartesBoutons = new ArrayList<>();
    private JLabel messageDef;
    private Observateur o;
    
    public VueDefausse (Joueur joueur) {
        
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        o = joueur.getVueAventurier().getObservateur();
        window.setSize(520, 180);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Défausser");
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        messageDef = new JLabel("Trop de cartes");
        messageDef.setText("Le joueur " + joueur.getNom() + " a trop de cartes en main, il doit en défausser jusqu'à en avoir 5 au plus.");
        
        JPanel cartesADefausser = new JPanel(new GridLayout(1,9));
        cartesADefausser.setBorder(BorderFactory.createTitledBorder("Choisir une Carte à défausser."));
        for(CarteTresor ct : joueur.getMainJoueur()) {
            JButton carte = new JButton(ct.getImage());
            carte.setEnabled(true);
            cartesADefausser.add(carte);
            cartesBoutons.add(carte);
            carte.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    carteDefaussee = ct;
                    for(JButton bouton : cartesBoutons) {
                        bouton.setEnabled(true);
                    }
                    joueur.defausserCarte(carteDefaussee);
                    window.dispose();
                    o.notifier();
                }
            });
        }
        
        mainPanel.add(messageDef, BorderLayout.NORTH);
        mainPanel.add(cartesADefausser, BorderLayout.CENTER);
        mainPanel.repaint();
        
        window.setVisible(true);
    }
}
