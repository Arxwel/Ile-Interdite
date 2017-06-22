/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Controleur.Message;
import Controleur.Observateur;
import Controleur.TypeMessage;
import Modèle.Joueur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pasdelor
 */
public class VueInscription {
    private final JFrame window ;
    private final JComponent mainPanel;
    
    private final JComponent titrePanel;
    private final JComponent buttPanel;
    private final JComponent centrePanel;
        
    private final JComponent j1Panel;
    private final JComponent j2Panel;
    private final JComponent j3Panel;
    private final JComponent j4Panel;
    
    private final JLabel titreLabel;
    
    private final JButton buttVal;
    private final JButton buttQui;
    
    private final JLabel nomLabel1;
    private final JLabel roleLabel1;
    private final JLabel j1Label;
    private final JComponent j1GridPanel;
    private static JTextField nomFieldJ1;
    private static JComboBox roleComboJ1;
    
    private final JLabel nomLabel2;
    private final JLabel roleLabel2;
    private final JLabel j2Label;
    private final JComponent j2GridPanel;
    private static JTextField nomFieldJ2;
    private static JComboBox roleComboJ2;
    
    private final JLabel nomLabel3;
    private final JLabel roleLabel3;
    private final JLabel j3Label;
    private final JComponent j3GridPanel;
    private static JTextField nomFieldJ3;
    private static JComboBox roleComboJ3;
    
    private final JLabel nomLabel4;
    private final JLabel roleLabel4;
    private final JLabel j4Label;
    private final JComponent j4GridPanel;
    private static JTextField nomFieldJ4;
    private static JComboBox roleComboJ4;
    
    private JComboBox comboDiff;
    private JCheckBox checkDebug;
    
    private static Observateur observateur;
    String rolej1="",rolej2="",rolej3="",rolej4="";
    private String [] roles = {"Aléatoire","Pilote","Messager","Explorateur","Navigateur","Plongeur","Ingénieur", "Vide"};
    private String [] niveauxDiff = {"Novice", "Normal", "Élite", "Légendaire", "Mortel"};
    //crée l'interface permettant l'inscription des joueurs (choix du rôle et du pseudonyme)
    public VueInscription() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/PiecesofEight.ttf")));
        } catch (FontFormatException ex) {
            Logger.getLogger(VueInscription.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(VueInscription.class.getName()).log(Level.SEVERE, null, ex);
        }
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("L'Ile Interdite : Inscription");
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension((int)window.getSize().getWidth()-7, (int)window.getSize().getHeight()-35));
        mainPanel.setBounds(window.getBounds());
        mainPanel.setOpaque(false);
        window.setContentPane(new AfficheImage("src/resources/fondinscription.jpg"));
        this.window.add(mainPanel);
        titrePanel = new JPanel();
        buttPanel = new JPanel(new GridLayout(2,5));
        buttPanel.setOpaque(false);
        centrePanel = new JPanel(new GridLayout(2,2));
        centrePanel.setOpaque(false);
        
        j1Panel = new JPanel(new BorderLayout());
        j1Panel.setOpaque(false);
        j2Panel = new JPanel(new BorderLayout());
        j2Panel.setOpaque(false);
        j3Panel = new JPanel(new BorderLayout());
        j3Panel.setOpaque(false);
        j4Panel = new JPanel(new BorderLayout());
        j4Panel.setOpaque(false);
        
        titreLabel = new JLabel();
        
        buttVal = new JButton();
        buttQui = new JButton();
        
        nomLabel1 = new JLabel();
        roleLabel1 = new JLabel();
        j1Label = new JLabel();
        j1GridPanel = new JPanel(new GridLayout(4,2));
        j1GridPanel.setOpaque(false);
        nomFieldJ1 = new JTextField();
        roleComboJ1 = new JComboBox(roles);
        roleComboJ1.setSelectedItem(roles[0]);
        roleComboJ1.removeItem(roles[7]);
        rolej1 = roles[0];
        
        nomLabel2 = new JLabel();
        roleLabel2= new JLabel();
        j2Label = new JLabel();
        j2GridPanel = new JPanel(new GridLayout(4,2));
        j2GridPanel.setOpaque(false);
        nomFieldJ2 = new JTextField();
        roleComboJ2 = new JComboBox(roles);
        roleComboJ2.setSelectedItem(roles[0]);
        roleComboJ2.removeItem(roles[7]);
        rolej2 = roles[0];
        
        nomLabel3 = new JLabel();
        roleLabel3= new JLabel();
        j3Label = new JLabel();
        j3GridPanel = new JPanel(new GridLayout(4,2));
        j3GridPanel.setOpaque(false);
        nomFieldJ3= new JTextField();
        roleComboJ3= new JComboBox(roles);
        roleComboJ3.setSelectedItem(roles[7]);
        rolej3 = roles[7];
        
        nomLabel4 = new JLabel();
        roleLabel4= new JLabel();
        j4Label = new JLabel();
        j4GridPanel = new JPanel(new GridLayout(4,2));
        j4GridPanel.setOpaque(false);
        nomFieldJ4 = new JTextField();
        roleComboJ4 = new JComboBox(roles);
        roleComboJ4.setSelectedItem(roles[7]);
        rolej4 = roles[7];
        
        
        
        mainPanel.add(titrePanel, BorderLayout.NORTH);
        mainPanel.add(buttPanel, BorderLayout.SOUTH);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        
        centrePanel.add(j1Panel);
        centrePanel.add(j2Panel);
        centrePanel.add(j3Panel);
        centrePanel.add(j4Panel);
        
        titreLabel.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        titreLabel.setText("Inscription de Joueurs");
        titreLabel.setFont(titreLabel.getFont().deriveFont(48.0f));
        titreLabel.setOpaque(false);
        
        
        titrePanel.add(titreLabel);
        titrePanel.setOpaque(false);
        
        buttVal.setText("Valider");
        buttQui.setText("Quitter");

        buttPanel.setOpaque(false);
        JLabel difficulte = new JLabel("Difficulté : ");
        difficulte.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        difficulte.setHorizontalAlignment(JLabel.RIGHT);
        buttPanel.add(difficulte);
        comboDiff = new JComboBox(getNiveauxDiff());
        buttPanel.add(comboDiff);
        
        buttPanel.add(new JLabel(" "));
        checkDebug = new JCheckBox("Mode Debug");
        checkDebug.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        checkDebug.setOpaque(false);
        buttPanel.add(checkDebug);
        buttPanel.add(new JLabel());
        buttPanel.add(new JLabel());
        buttPanel.add(buttVal);
        buttPanel.add(new JLabel());
        buttPanel.add(buttQui);
        buttPanel.add(new JLabel());
        
        // Joueur 1
        j1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel1.setText("     Nom : ");
        nomLabel1.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        roleLabel1.setText("     Role : ");
        roleLabel1.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        nomFieldJ1.setText("Jason");
        j1Label.setText("Joueur 1 :");
        j1Label.setFont(new Font("Pieces of Eight", Font.PLAIN,30));
        
        j1Panel.add(j1Label, BorderLayout.NORTH);
        j1Panel.add(j1GridPanel, BorderLayout.CENTER);
        
        j1GridPanel.add(nomLabel1);
        j1GridPanel.add(nomFieldJ1);
        j1GridPanel.add(new JLabel());
        j1GridPanel.add(new JLabel());
        j1GridPanel.add(roleLabel1);
        j1GridPanel.add(roleComboJ1);
        j1GridPanel.add(new JLabel());
        j1GridPanel.add(new JLabel());
        
        
        // Joueur 2
        j2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel2.setText("     Nom : ");
        nomLabel2.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        roleLabel2.setText("     Role : ");
        roleLabel2.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        nomFieldJ2.setText("Billy");
        j2Label.setText("Joueur 2 :");
        j2Label.setFont(new Font("Pieces of Eight", Font.PLAIN,30));
        j2Panel.add(j2Label, BorderLayout.NORTH);
        j2Panel.add(j2GridPanel, BorderLayout.CENTER);
        
        j2GridPanel.add(nomLabel2);
        j2GridPanel.add(nomFieldJ2);
        j2GridPanel.add(new JLabel());
        j2GridPanel.add(new JLabel());
        j2GridPanel.add(roleLabel2);
        j2GridPanel.add(roleComboJ2);
        j2GridPanel.add(new JLabel());
        j2GridPanel.add(new JLabel());

        // Joueur 3
        j3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel3.setText("     Nom : ");
        nomLabel3.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        roleLabel3.setText("     Role : ");
        roleLabel3.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        nomFieldJ3.setText("Trini");
        j3Label.setText("Joueur 3 :");
        j3Label.setFont(new Font("Pieces of Eight", Font.PLAIN,30));
        
        j3Panel.add(j3Label, BorderLayout.NORTH);
        j3Panel.add(j3GridPanel, BorderLayout.CENTER);
        
        j3GridPanel.add(nomLabel3);
        j3GridPanel.add(nomFieldJ3);
        j3GridPanel.add(new JLabel());
        j3GridPanel.add(new JLabel());
        j3GridPanel.add(roleLabel3);
        j3GridPanel.add(roleComboJ3);
        j3GridPanel.add(new JLabel());
        j3GridPanel.add(new JLabel());
        
        // Joueur 4
        j4Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel4.setText("     Nom : ");
        nomLabel4.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        roleLabel4.setText("     Role : ");
        roleLabel4.setFont(new Font("Pieces of Eight", Font.PLAIN,24));
        nomFieldJ4.setText("Tommy");
        j4Label.setText("Joueur 4 :");
        j4Label.setFont(new Font("Pieces of Eight", Font.PLAIN,30));
        
        j4Panel.add(j4Label, BorderLayout.NORTH);
        j4Panel.add(j4GridPanel, BorderLayout.CENTER);
        
        j4GridPanel.add(nomLabel4);
        j4GridPanel.add(nomFieldJ4);
        j4GridPanel.add(new JLabel());
        j4GridPanel.add(new JLabel());
        j4GridPanel.add(roleLabel4);
        j4GridPanel.add(roleComboJ4);
        j4GridPanel.add(new JLabel());
        j4GridPanel.add(new JLabel());
        
        
        buttVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String [] selection = new String[4];
                boolean continuer = true;
                if(!isAleatoireVide(getRoleComboJ1()) && !isAleatoireVide(getRoleComboJ2())) {
                    if (getRoleComboJ1() == getRoleComboJ2()) {
                        continuer = false;
                    }
                }
                if(!isAleatoireVide(getRoleComboJ1()) && !isAleatoireVide(getRoleComboJ3())) {
                    if (getRoleComboJ1() == getRoleComboJ3()) {
                        continuer = false;
                    }
                }
                if(!isAleatoireVide(getRoleComboJ1()) && !isAleatoireVide(getRoleComboJ4())) {
                    if (getRoleComboJ1() == getRoleComboJ4()) {
                        continuer = false;
                    }
                }
                if(!isAleatoireVide(getRoleComboJ3()) && !isAleatoireVide(getRoleComboJ2())) {
                    if (getRoleComboJ3() == getRoleComboJ2()) {
                        continuer = false;
                    }
                }
                if(!isAleatoireVide(getRoleComboJ4()) && !isAleatoireVide(getRoleComboJ2())) {
                    if (getRoleComboJ4() == getRoleComboJ2()) {
                        continuer = false;
                    }
                }
                if(!isAleatoireVide(getRoleComboJ3()) && !isAleatoireVide(getRoleComboJ4())) {
                    if (getRoleComboJ3() == getRoleComboJ4()) {
                        continuer = false;
                    }
                }
                if (continuer) {
                    Message msg = new Message(TypeMessage.Valider);
                    observateur.traiterMessage(msg);
                } else {
                    fenetreInfo();
                    continuer = true;
                }
            }
            
        });
        
        buttQui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypeMessage.Annuler);
                observateur.traiterMessage(msg);
            }
            
        });
            
    }

    /**
     * @return the nomFieldJ1
     */
    public static String getNomFieldJ1() {
        return nomFieldJ1.getText();
    }

    /**
     * @return the roleComboJ1
     */
    public static String getRoleComboJ1() {
        return roleComboJ1.getSelectedItem().toString();
    }

    /**
     * @return the nomFieldJ2
     */
    public static String getNomFieldJ2() {
        return nomFieldJ2.getText();
    }

    /**
     * @return the roleComboJ2
     */
    public static String getRoleComboJ2() {
        return roleComboJ2.getSelectedItem().toString();
    }

    /**
     * @return the nomFieldJ3
     */
    public static String getNomFieldJ3() {
        return nomFieldJ3.getText();
    }

    /**
     * @return the roleComboJ3
     */
    public static String getRoleComboJ3() {
        return roleComboJ3.getSelectedItem().toString();
    }

    /**
     * @return the nomFieldJ4
     */
    public static String getNomFieldJ4() {
        return nomFieldJ4.getText();
    }

    /**
     * @return the roleComboJ4
     */
    public static String getRoleComboJ4() {
        return roleComboJ4.getSelectedItem().toString();
    }
    
    public static void setObservateur(Observateur o) {
        observateur = o;
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }

    /**
     * @return the roles
     */
    public String[] getRoles() {
        return roles;
    }

    public void afficher() {
        this.window.setVisible(true);
    }
    
    public boolean isAleatoireVide(String s) {
        return s=="Aléatoire" || s=="Vide";
    }
    
   public void fenetreInfo() {
        JFrame frame = new JFrame("Saisie du Nom");
        JOptionPane.showMessageDialog(null, "Plusieurs Joueurs ne peuvent pas avoir le même rôle", "Erreur", JOptionPane.ERROR_MESSAGE); 
    }

    /**
     * @return the comboDiff
     */
    public String getComboDiff() {
        return comboDiff.getSelectedItem().toString();
    }

    /**
     * @return the checkDebug
     */
    public boolean getCheckDebug() {
        return checkDebug.isSelected();
    }

    /**
     * @return the niveauxDiff
     */
    public String[] getNiveauxDiff() {
        return niveauxDiff;
    }
}
