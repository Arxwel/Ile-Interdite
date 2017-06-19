/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Controleur;
import Controleur.Message;
import Controleur.MessagePlateau;
import Controleur.Observateur;
import Controleur.TypeMessage;
import Modèle.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 *
 * @author Alexandre
 */
public class VuePlateau extends JFrame{
    private Controleur controleur;
    private ImageIcon icona;
    private Observateur observateur;
    
    //Affiche le plateau
    public VuePlateau(Controleur c) {
        super("Ile Interdite");
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setControleur(c);
        
        ArrayList<JPanel> caseTuiles = new ArrayList<>();
        ArrayList<JButton> caseButtons = new ArrayList<>();
        JLayeredPane calque = new JLayeredPane();
        calque.setLayout(new BorderLayout());
        calque.setPreferredSize(this.getPreferredSize());
        JPanel mapPanel = new JPanel(new GridLayout(6,6));
        JPanel pionsPlateau = new JPanel(new GridLayout(6,6));
        JPanel calqueButtons = new JPanel(new GridLayout(6,6));
        ArrayList<JButton> buttonsCase = new ArrayList<>();
        
        Color colorBack;
        
        for (int x=0; x<6; x++) {
            for (int y=0; y<6; y++) {
                Grille g = controleur.getGrille();
                Tuile t = g.getTuile(x,y);
                
                if (t==null) {
                    caseTuiles.add(new JPanel(new BorderLayout()));
                    caseTuiles.get(caseTuiles.size()-1).setBackground(new Color(0,191,255)); //deepsky blue
                    if(x== 0 && y ==5) {
                    icona = new ImageIcon(this.getClass().getResource("/ImagesTuiles/EauRoseVent.png"));
                    } else {
                    icona = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
                    }
                    icona = new ImageIcon(icona.getImage().getScaledInstance(180,180, Image.SCALE_DEFAULT));
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(icona), BorderLayout.CENTER);
                } else {
                    if (null==t.getEtat()) {
                        colorBack = Color.LIGHT_GRAY;
                    } else switch (t.getEtat()) {
                        case Inondé:
                            colorBack = new Color(10,110,230); //bleu clair
                            break;
                        case Sec:
                            colorBack = new Color(240,230,140); //jaune sable
                            break;
                        default:
                            colorBack = Color.LIGHT_GRAY;
                            break;
                    }
                    
                    caseTuiles.add(new JPanel(new BorderLayout()));
                    
                    caseTuiles.get(caseTuiles.size()-1).setBackground(colorBack);
                    caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    ImageIcon icon = new ImageIcon(t.getImage().getImage().getScaledInstance(170,170, Image.SCALE_DEFAULT));
                    
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(icon),BorderLayout.CENTER);
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(t.getIntitule().nomEspace()),BorderLayout.SOUTH);
                    
                    
                    
                    
                    
                    if (t.getIntitule() == Zone.Heliport) {
                       caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                /*if (t.getReliqueDispo() != null) {
                    
                }*/
                
                buttonsCase.add(new JButton());
                buttonsCase.get(buttonsCase.size()-1).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MessagePlateau msg = new MessagePlateau(TypeMessage.ClicPlateau, t.getCoordonees());
                        observateur.traiterMessagePlateau(msg);
                        System.out.println(msg.getCoo().getX()+" "+msg.getCoo().getY()+" a été cliqué.");
                    }
                });
                
            }
        }
        for (JPanel jpp: caseTuiles) {
            mapPanel.add(jpp);
        }
        for (JButton jp: buttonsCase) {
            calqueButtons.add(jp);
        }
        //window.setLocationRelativeTo(null);  centre la fenêtre
        calque.add(mapPanel,BorderLayout.CENTER,Integer.valueOf(1));
        calque.setOpaque(true);
        this.add(calque, BorderLayout.CENTER);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
             
    }
    
    public void setControleur(Controleur c) {
        this.controleur=c;    
    }
    
    public void setObservateur(Observateur o) {
        this.observateur = o;
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return this;
    }
}
