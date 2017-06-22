/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Message;
import Controleur.Observateur;
import Controleur.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pasdelor
 */
public class VueEcranTitre extends JPanel {

    private Image image ;
    private Integer width, height ;
    private Observateur observateur;
    private JFrame ecranTitreFenetre;

    public VueEcranTitre() {
        super();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/planches.jpg"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(265, 65,Image.SCALE_DEFAULT));
        this.width = 800 ;
        this.height = 650 ;
        JButton jouer = new JButton(icon);
        icon = new ImageIcon(this.getClass().getResource("/resources/planchesQuitter.jpg"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(265, 65,Image.SCALE_DEFAULT));
        JButton quitter = new JButton(icon);
        quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        icon = new ImageIcon(this.getClass().getResource("/resources/planchesRegles.jpg"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(265, 65,Image.SCALE_DEFAULT));
        JButton regles = new JButton(icon);
        regles.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        try {
            this.image = ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/fond.png"));
        } catch (IOException ex) {
            System.err.println("Erreur de lecture de fond.png");
        }
        
        this.setLayout(new BorderLayout());
        JComponent boutons = new JPanel();
        boutons.setLayout(new GridLayout(10, 3));
        boutons.setOpaque(false);
        boutons.setAlignmentX(Component.CENTER_ALIGNMENT);
        for(int i = 0; i<13; i++) {
            boutons.add(new JLabel(""));
        }
        boutons.add(jouer);
        jouer.setContentAreaFilled(false);
        boutons.add(new JLabel(""));
        boutons.add(new JLabel(""));
        boutons.add(regles);
        boutons.add(new JLabel(""));
        boutons.add(new JLabel(""));
        boutons.add(quitter);
        for(int i = 0; i<10; i++) {
            boutons.add(new JLabel(""));
        }
                

        this.add(boutons, BorderLayout.CENTER);
        jouer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypeMessage.Jouer);
                observateur.traiterMessage(msg);
            }
        });
        
        regles.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().open(new File(System.getProperty("user.dir") + "/src/resources/regles.pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(VueEcranTitre.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        quitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void afficher() {
        ecranTitreFenetre = new JFrame("L'Ile Interdite : Ecran Titre");
        ecranTitreFenetre.setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        ecranTitreFenetre.setLocation(dim.width/2-ecranTitreFenetre.getSize().width/2, dim.height/2-ecranTitreFenetre.getSize().height/2);
        ecranTitreFenetre.add(this);
        ecranTitreFenetre.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        ecranTitreFenetre.setResizable(false);
        ecranTitreFenetre.setVisible(true);
        ecranTitreFenetre.repaint();
    }
    
    

    @Override
    /**
     * paintComponent permet de gérer l'affichage / la mise à jour des
     * images, à condition que le paintComponent de chaque objet soit appelé
     * avec le même contexte graphique (Graphics)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, width, height, null, this);
    }
    
    public void setObservateur(Observateur o) {
        observateur = o;
    }

    public void fermer() {
        ecranTitreFenetre.dispose();
    }
    
}