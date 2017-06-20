package Vue;


import Controleur.Controleur;
import Controleur.Message;
import Controleur.MessageAventurier;
import Controleur.Observateur;
import Controleur.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import Modèle.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

 
public class VueAventurier  {
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JPanel panelCartes ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnAller  ;
    private final JButton btnAssecher;
    private final JButton btnDonner;
    private final JButton btnRelique;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    
    private static Observateur observateur;
    
    
    public VueAventurier (Joueur j) {

        this.window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(410, 250);

        window.setTitle(j.getNom());
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(j.getCouleur(), 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(j.getCouleur());
        panelAventurier.add(new JLabel(j.getClass().toString().substring(13),SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : Cartes en Main
        this.panelCentre = new JPanel(new BorderLayout());
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, j.getCouleur()));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Cartes", SwingConstants.CENTER),BorderLayout.NORTH);
        panelCartes = new JPanel(new GridLayout(1,5));
        panelCentre.add(panelCartes,BorderLayout.CENTER);
//        for (CarteTresor c : j.getMainJoueur()) {
//            panelCartes.add(new JLabel(c.getType().toString()));
//        }
        for (CarteTresor c : j.getMainJoueur()) {
            JLabel imgCarte = new JLabel(new ImageIcon(c.getImage().getImage().getScaledInstance(77,77, Image.SCALE_DEFAULT)));
            panelCartes.add(imgCarte);
        }
        


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(3,2));
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnAller = new JButton("Déplacer");
        btnAller.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAventurier msg = new MessageAventurier(TypeMessage.Deplacer, j);
                observateur.traiterMessageAventurier(msg);
                System.out.println("Deplacement de " + msg.getJoueur().getNom());

            }
        });
        this.btnAssecher = new JButton("Assécher");
        btnAssecher.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Assechement");
                MessageAventurier msg = new MessageAventurier(TypeMessage.Assecher, j);
                observateur.traiterMessageAventurier(msg);
            }
        });
        this.btnDonner = new JButton("Donner une Carte");
        btnDonner.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Don de carte");
                MessageAventurier msg = new MessageAventurier(TypeMessage.Donner, j);
                observateur.traiterMessageAventurier(msg);
            }
        });
        this.btnRelique = new JButton("Prendre Relique");
        btnRelique.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Prise de relique");
                MessageAventurier msg = new MessageAventurier(TypeMessage.PrendreRelique, j);
                observateur.traiterMessageAventurier(msg);
            }
        });
        this.btnAutreAction = new JButton("AutreAction");
        btnAutreAction.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Autre action");
                MessageAventurier msg = new MessageAventurier(TypeMessage.AutreAction, j);
                observateur.traiterMessageAventurier(msg);
            }
        });
        this.btnTerminerTour = new JButton("Terminer Tour");
        btnTerminerTour.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageAventurier msg = new MessageAventurier(TypeMessage.TerminerTour, j);
                observateur.traiterMessageAventurier(msg);
                System.out.println("Fin de tour de " + msg.getJoueur().getNom());

            }
        });
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnDonner);
        this.panelBoutons.add(btnRelique);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        this.window.setVisible(true);
        mainPanel.repaint();
    }
 

    
     public static void main(String [] args) {
        // Instanciation de la fenêtre 
        //VueAventurier vueAventurier = new VueAventurier (new Messager("Jack"));
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    public void setObservateur(Observateur o) {
        this.observateur = o;
    }
    
    public void desactiverBoutons() {
        btnAller.setEnabled(false);
        btnAssecher.setEnabled(false);
        btnDonner.setEnabled(false);
        btnRelique.setEnabled(false);
        btnAutreAction.setEnabled(false);
        btnTerminerTour.setEnabled(false);
    }
    
    public void activerBoutons() {
        btnAller.setEnabled(true);
        btnAssecher.setEnabled(true);
        btnDonner.setEnabled(true);
        btnRelique.setEnabled(true);
        btnAutreAction.setEnabled(true);
        btnTerminerTour.setEnabled(true);
    }
    
}

 


