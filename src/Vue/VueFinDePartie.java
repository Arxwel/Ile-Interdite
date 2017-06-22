/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Message;
import Controleur.Observateur;
import Controleur.TypeMessage;
import static Vue.VueInscription.getRoleComboJ1;
import static Vue.VueInscription.getRoleComboJ2;
import static Vue.VueInscription.getRoleComboJ3;
import static Vue.VueInscription.getRoleComboJ4;
import java.awt.BorderLayout;
import java.awt.Color;
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
 * @author salmona
 */


public class VueFinDePartie {
    private JFrame window;
    private JPanel mainPanel;
    private JLabel titre;
    private JLabel sousTitre;
    private Observateur observateur;
    
    
    public VueFinDePartie() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/PiecesofEight.ttf")));
        } catch (FontFormatException ex) {
            Logger.getLogger(VueInscription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
        Logger.getLogger(VueInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        window = new JFrame();
        window.setContentPane(new AfficheImage("src/resources/fondinscription.jpg"));
        mainPanel = new JPanel(new GridLayout(3,1));
        titre = new JLabel();
        sousTitre = new JLabel();
        JPanel auxPanel = new JPanel(new GridLayout(2,1));
        JPanel buttPanel = new JPanel(new GridLayout(4,5));
        
        this.window.add(mainPanel);
        
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Fin Du Jeu");
        
        mainPanel.setPreferredSize(new Dimension((int)window.getSize().getWidth()-7, (int)window.getSize().getHeight()-35));
        mainPanel.setBounds(window.getBounds());
        mainPanel.setOpaque(false);
        auxPanel.setOpaque(false);
        buttPanel.setOpaque(false);
        
        
        titre.setFont(new Font("Pieces of Eight", Font.PLAIN,100));
        titre.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(titre);
        mainPanel.add(auxPanel);
        mainPanel.add(buttPanel);
        
        
        sousTitre.setFont(new Font("Pieces of Eight", Font.PLAIN,50));
        sousTitre.setHorizontalAlignment(JLabel.CENTER);
        auxPanel.add(sousTitre);
        auxPanel.add(new JLabel());
        JButton rejouer = new JButton("Rejouer");
        JButton quitter = new JButton("Quitter");
        for(int i =0; i<9; i++) {
            buttPanel.add(new JLabel());
        }
        buttPanel.add(rejouer);
        buttPanel.add(new JLabel());
        buttPanel.add(quitter);
        
        rejouer.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypeMessage.Valider);
                observateur.traiterMessage(msg);
            }
        });
        
        quitter.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }
    
    public void update(int numFin){
        if (numFin == 0) {
            titre.setText(" * Victoire * ");
        }else{
            titre.setText(" $ Défaite $ ");
        }
        
        switch(numFin){
            case(0):
                sousTitre.setText("Les Aventuriers ont pu s'échapper");
                break;
            case(1):
                sousTitre.setText("L'Héliport a Sombré");
                break;
            case(2):
                sousTitre.setText("Un Aventurier est Mort");
                break;
            case(3):
                sousTitre.setText("Une Relique n'est plus récupérable");
                break;
            case(4):
                sousTitre.setText("L'Eau a atteint un niveau mortel");
                break;
        }
    }
    
    public static void main(String [] args) {
        VueFinDePartie vue = new VueFinDePartie();
        vue.update(3);
        vue.afficher();
    }

    public void afficher() {
        window.setVisible(true);
    }
    
    public void setObservateur(Observateur o) {
        observateur = o;
    }
}
