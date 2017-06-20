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
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
    private static JTextField nomFieldJ1;
    private static JComboBox roleComboJ1;
    
    private final JLabel nomLabel2;
    private final JLabel roleLabel2;
    private final JLabel j2Label;
    private final JPanel j2GridPanel;
    private static JTextField nomFieldJ2;
    private static JComboBox roleComboJ2;
    
    private final JLabel nomLabel3;
    private final JLabel roleLabel3;
    private final JLabel j3Label;
    private final JPanel j3GridPanel;
    private static JTextField nomFieldJ3;
    private static JComboBox roleComboJ3;
    
    private final JLabel nomLabel4;
    private final JLabel roleLabel4;
    private final JLabel j4Label;
    private final JPanel j4GridPanel;
    private static JTextField nomFieldJ4;
    private static JComboBox roleComboJ4;
    
    private static Observateur observateur;
    String rolej1="",rolej2="",rolej3="",rolej4="";
    private String [] roles = {"Aléatoire","Pilote","Messager","Explorateur","Navigateur","Plongeur","Ingénieur", "Vide"};
    //crée l'interface permettant l'inscription des joueurs (choix du rôle et du pseudonyme)
    public VueInscription() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 600);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Inscription");
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);
        
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
        roleComboJ1.setSelectedItem(roles[0]);
        roleComboJ1.removeItem(roles[7]);
        rolej1 = roleComboJ1.getSelectedItem().toString();
        
        nomLabel2 = new JLabel();
        roleLabel2= new JLabel();
        j2Label = new JLabel();
        j2GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ2 = new JTextField();
        roleComboJ2 = new JComboBox(roles);
        roleComboJ2.setSelectedItem(roles[0]);
        roleComboJ2.removeItem(roles[7]);
        rolej2 = roleComboJ2.getSelectedItem().toString();
        
        nomLabel3 = new JLabel();
        roleLabel3= new JLabel();
        j3Label = new JLabel();
        j3GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ3= new JTextField();
        roleComboJ3= new JComboBox(roles);
        roleComboJ3.setSelectedItem(roles[0]);
        rolej3 = roleComboJ3.getSelectedItem().toString();
        
        nomLabel4 = new JLabel();
        roleLabel4= new JLabel();
        j4Label = new JLabel();
        j4GridPanel = new JPanel(new GridLayout(4,2));
        nomFieldJ4 = new JTextField();
        roleComboJ4 = new JComboBox(roles);
        roleComboJ4.setSelectedItem(roles[0]);
        rolej4 = roleComboJ4.getSelectedItem().toString();
        
        
        
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
        nomFieldJ1.setText("Jason");
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
        nomFieldJ2.setText("Billy");
        j2Label.setText("Joueur 2");
        j2Panel.add(j2Label, BorderLayout.NORTH);
        j2Panel.add(j2GridPanel, BorderLayout.CENTER);
        
        j2GridPanel.add(nomLabel2);
        j2GridPanel.add(nomFieldJ2);
        j2GridPanel.add(new JPanel());
        j2GridPanel.add(new JPanel());
        j2GridPanel.add(roleLabel2);
        j2GridPanel.add(roleComboJ2);
        j2GridPanel.add(new JPanel());
        j2GridPanel.add(new JPanel());

        // Joueur 3
        j3Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel3.setText("Nom : ");
        roleLabel3.setText("Role : ");
        nomFieldJ3.setText("Trini");
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
        j3GridPanel.add(new JPanel());
        
        // Joueur 4
        j4Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        nomLabel4.setText("Nom : ");
        roleLabel4.setText("Role : ");
        nomFieldJ4.setText("Tommy");
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
        
        
        buttVal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypeMessage.Valider);
                observateur.traiterMessage(msg);
            }
            
        });
        
        buttQui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message msg = new Message(TypeMessage.Annuler);
                observateur.traiterMessage(msg);
            }
            
        });
        
        roleComboJ1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"Vide".equals(rolej1) && !"Aléatoire".equals(rolej1)) {
                    roleComboJ2.addItem(rolej1);
                    roleComboJ3.addItem(rolej1);
                    roleComboJ4.addItem(rolej1);
                }
                    rolej1 = getRoleComboJ1();
                    roleComboJ2.removeItem(rolej1);
                    roleComboJ3.removeItem(rolej1);
                    roleComboJ4.removeItem(rolej1);
            }
        });
        roleComboJ2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"Vide".equals(rolej2) && !"Aléatoire".equals(rolej2)) {
                    roleComboJ1.addItem(rolej2);
                    roleComboJ3.addItem(rolej2);
                    roleComboJ4.addItem(rolej2);
                }
                    rolej2 = getRoleComboJ2();
                    roleComboJ1.removeItem(rolej2);
                    roleComboJ3.removeItem(rolej2);
                    roleComboJ4.removeItem(rolej2);
            }
        });
        
        roleComboJ3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"Vide".equals(rolej3) && !"Aléatoire".equals(rolej3)) {
                    roleComboJ1.addItem(rolej3);
                    roleComboJ2.addItem(rolej3);
                    roleComboJ4.addItem(rolej3);
                }
                    rolej3 = getRoleComboJ3();
                    roleComboJ1.removeItem(rolej3);
                    roleComboJ2.removeItem(rolej3);
                    roleComboJ4.removeItem(rolej3);
            }
        });
        
        roleComboJ4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!"Vide".equals(rolej4) && !"Aléatoire".equals(rolej4)) {
                    roleComboJ1.addItem(rolej4);
                    roleComboJ2.addItem(rolej4);
                    roleComboJ3.addItem(rolej4);
                }
                    rolej4 = getRoleComboJ4();
                    roleComboJ1.removeItem(rolej4);
                    roleComboJ2.removeItem(rolej4);
                    roleComboJ3.removeItem(rolej4);
            }
        });
        
        
    }
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        VueInscription v = new VueInscription();
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
}
