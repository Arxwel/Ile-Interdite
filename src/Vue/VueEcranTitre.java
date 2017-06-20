/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pasdelor
 */
public class VueEcranTitre extends JPanel {

    private Image image ;
    private Integer width, height ;

    public VueEcranTitre() {
        super();
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/resources/planches.png"));
        icon = new ImageIcon(icon.getImage().getScaledInstance(200, 200,Image.SCALE_DEFAULT));
        this.width = 800 ;
        this.height = 650 ;
        JButton jouer = new JButton();
        JButton quitter = new JButton("Quitter");
        quitter.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton regles = new JButton("Règles");
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
    
    public static void main(String[] args) {
        JFrame window = new JFrame() ;
        window.setSize(800, 600);
        // Centrage de la fenêtre sur l'écran
        VueEcranTitre demo = new VueEcranTitre();
        window.add(demo);
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        window.setVisible(true);
        demo.repaint();
    }
    
}