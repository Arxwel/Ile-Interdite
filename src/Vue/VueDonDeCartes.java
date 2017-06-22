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
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pasdelor
 */
public class VueDonDeCartes {
  
    private JFrame window;
    private Joueur joueurReceveur;
    private CarteTresor carteEnvoye;
    private ArrayList<JButton> joueursBoutons = new ArrayList<>();
    private ArrayList<JButton> cartesBoutons = new ArrayList<>();
    private Observateur o;
    public VueDonDeCartes(Joueur joueur, ArrayList<Joueur> liste) {
        window = new JFrame();
        o = joueur.getVueAventurier().getObservateur();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 300);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Don de Cartes");
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        JPanel listeJoueurs = new JPanel(new GridLayout(1,3));
        
        for(Joueur j : liste) {
            if( j != joueur) {
            JButton personne = new JButton(j.getImage());
            listeJoueurs.setBorder(BorderFactory.createTitledBorder("Choix du Joueur Receveur"));
            listeJoueurs.add(personne);
            joueursBoutons.add(personne);
            personne.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    joueurReceveur = j;
                    for(JButton bouton : joueursBoutons) {
                        bouton.setEnabled(false);
                    }
                    for(JButton bouton : cartesBoutons) {
                        bouton.setEnabled(true);
                    }
                }
            });
            }
        }
        
        JPanel cartesADonner = new JPanel(new GridLayout(1,9));
        cartesADonner.setBorder(BorderFactory.createTitledBorder("Choix de la Carte à donner"));
        for(CarteTresor ct : joueur.getMainJoueur()) {
            JButton carte = new JButton(ct.getImage());
            carte.setEnabled(false);
            cartesADonner.add(carte);
            cartesBoutons.add(carte);
            carte.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    carteEnvoye = ct;
                    for(JButton bouton : cartesBoutons) {
                        bouton.setEnabled(false);
                    }
                    joueur.donnerCarte(joueurReceveur, carteEnvoye);
                    window.dispose();
                    o.notifier();
                }
            });
        }
        
        mainPanel.add(listeJoueurs, BorderLayout.NORTH);
        mainPanel.add(cartesADonner, BorderLayout.SOUTH);
        
        window.setVisible(true);
    }
}
