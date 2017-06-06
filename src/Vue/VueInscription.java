/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Modèle.Joueur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class VueInscription {
    private final JFrame window ;
    private final JPanel mainPanel;
    
    private final JPanel titrePanel;
    private final JPanel buttPanel;
    private final JPanel centrePanel;
        
    private final JPanel j1Panel;
    private final JPanel j2Panel;
    private final JPanel j3Panel;
    private final JPanel j4Panel;
    
    private final JLabel titreLabel;
    
    private final JButton buttVal;
    private final JButton buttQui;
    
    private final JLabel nomLabel1;
    private final JLabel roleLabel1;
    private final JLabel j1Label;
    private final JPanel j1GridPanel;
    private final JTextField nomFieldJ1;
    private final JComboBox roleComboJ1;
    
    private final JLabel nomLabel2;
    private final JLabel roleLabel2;
    private final JLabel j2Label;
    private final JPanel j2GridPanel;
    private final JTextField nomFieldJ2;
    private final JComboBox roleComboJ2;
    
    private final JLabel nomLabel3;
    private final JLabel roleLabel3;
    private final JLabel j3Label;
    private final JPanel j3GridPanel;
    private final JTextField nomFieldJ3;
    private final JComboBox roleComboJ3;
    
    private final JLabel nomLabel4;
    private final JLabel roleLabel4;
    private final JLabel j4Label;
    private final JPanel j4GridPanel;
    private final JTextField nomFieldJ4;
    private final JComboBox roleComboJ4;
    
    
    public VueInscription() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Inscription");
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);
        
        String[] roles = {"Aléatoire","Pilote","Messager","Explorateur","Navigateur","Plongeur","Ingénieur", "Vide"};
        
        titrePanel = new JPanel();
        buttPanel = new JPanel(new GridLayout(1,6));
        centrePanel = new JPanel(new GridLayout(2,2));
        
        j1Panel = new JPanel(new BorderLayout());
        j2Panel = new JPanel(new BorderLayout());
        j3Panel = new JPanel(new BorderLayout());
        j4Panel = new JPanel(new BorderLayout());
        
        titreLabel = new JLabel();
        
        buttVal = new JButton();
        buttQui = new JButton();
        
        nomLabel1 = new JLabel();
        roleLabel1 = new JLabel();
        j1Label = new JLabel();
        j1GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ1 = new JTextField();
        roleComboJ1 = new JComboBox(roles);
        
        nomLabel2 = new JLabel();
        roleLabel2= new JLabel();
        j2Label = new JLabel();
        j2GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ2 = new JTextField();
        roleComboJ2 = new JComboBox(roles);
        
        nomLabel3 = new JLabel();
        roleLabel3= new JLabel();
        j3Label = new JLabel();
        j3GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ3= new JTextField();
        roleComboJ3= new JComboBox(roles);
        
        nomLabel4 = new JLabel();
        roleLabel4= new JLabel();
        j4Label = new JLabel();
        j4GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ4 = new JTextField();
        roleComboJ4 = new JComboBox(roles);
        
        
        
        mainPanel.add(titrePanel, BorderLayout.NORTH);
        mainPanel.add(buttPanel, BorderLayout.SOUTH);
        mainPanel.add(centrePanel, BorderLayout.CENTER);
        
        centrePanel.add(j1Panel);
        centrePanel.add(j2Panel);
        centrePanel.add(j3Panel);
        centrePanel.add(j4Panel);
        
        titreLabel.setText("Inscription de Joueurs");
        titreLabel.setFont(new Font("Courier", Font.BOLD,30));
        
        
        titrePanel.add(titreLabel);
        
        buttVal.setText("Valider");
        buttQui.setText("Quitter");
        
        buttPanel.add(new JPanel());
        buttPanel.add(buttVal);
        buttPanel.add(new JPanel());
        buttPanel.add(new JPanel());
        buttPanel.add(buttQui);
        buttPanel.add(new JPanel());
        
        // Joueur 1
        j1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel1.setText("Nom : ");
        roleLabel1.setText("Role : ");
        nomFieldJ1.setText("Joueur");
        j1Label.setText("Joueur 1");
        
        j1Panel.add(j1Label, BorderLayout.NORTH);
        j1Panel.add(j1GridPanel, BorderLayout.CENTER);
        
        j1GridPanel.add(nomLabel1);
        j1GridPanel.add(nomFieldJ1);
        j1GridPanel.add(new JPanel());
        j1GridPanel.add(new JPanel());
        j1GridPanel.add(roleLabel1);
        j1GridPanel.add(roleComboJ1);
        j1GridPanel.add(new JPanel());
        j1GridPanel.add(new JPanel());
        
        
        // Joueur 2
        j2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel2.setText("Nom : ");
        roleLabel2.setText("Role : ");
        nomFieldJ2.setText("Joueur");
        j2Label.setText("Joueur 2");
        j2Panel.add(j2Label, BorderLayout.NORTH);
        j2Panel.add(j2GridPanel, BorderLayout.CENTER);
        
        j2GridPanel.add(nomLabel2);
        j2GridPanel.add(nomFieldJ2);
        j2GridPanel.add(roleLabel2);
        j2GridPanel.add(roleComboJ2);

        // Joueur 3
        j3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel3.setText("Nom : ");
        roleLabel3.setText("Role : ");
        nomFieldJ3.setText("Joueur");
        j3Label.setText("Joueur 3");
        
        j3Panel.add(j3Label, BorderLayout.NORTH);
        j3Panel.add(j3GridPanel, BorderLayout.CENTER);
        
        j3GridPanel.add(nomLabel3);
        j3GridPanel.add(nomFieldJ3);
        j3GridPanel.add(new JPanel());
        j3GridPanel.add(new JPanel());
        j3GridPanel.add(roleLabel3);
        j3GridPanel.add(roleComboJ3);
        j3GridPanel.add(new JPanel());
        j2GridPanel.add(new JPanel());
        
        // Joueur 4
        j4Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel4.setText("Nom : ");
        roleLabel4.setText("Role : ");
        nomFieldJ4.setText("Joueur");
        j4Label.setText("Joueur 4");
        
        j4Panel.add(j4Label, BorderLayout.NORTH);
        j4Panel.add(j4GridPanel, BorderLayout.CENTER);
        
        j4GridPanel.add(nomLabel4);
        j4GridPanel.add(nomFieldJ4);
        j4GridPanel.add(new JPanel());
        j4GridPanel.add(new JPanel());
        j4GridPanel.add(roleLabel4);
        j4GridPanel.add(roleComboJ4);
        j4GridPanel.add(new JPanel());
        j4GridPanel.add(new JPanel());
        
        
        buttVal.addActionListener(Controleur);
        
        window.setVisible(true);
    }
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        VueInscription v = new VueInscription();
    }
}
