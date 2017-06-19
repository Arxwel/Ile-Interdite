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
import javax.swing.JComponent;
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
        ArrayList<JPanel> panelPions = new ArrayList<>();
        ArrayList<JButton> buttonsCase = new ArrayList<>();
        JLayeredPane calque = new JLayeredPane();
        calque.setPreferredSize(this.getPreferredSize());
        JPanel mapPanel = new JPanel(new GridLayout(6,6));
        mapPanel.setBounds(this.getBounds());
        JComponent pionsPlateau = new JPanel(new GridLayout(6,6));
        pionsPlateau.setOpaque(false);
        pionsPlateau.setSize(this.getBounds().getSize());
        JComponent calqueButtons = new JPanel(new GridLayout(6,6));
        Color colorBack;
        
        for (int x=0; x<6; x++) {
            for (int y=0; y<6; y++) {
                Grille g = controleur.getGrille();
                Tuile t = g.getTuile(x,y);
                
                buttonsCase.add(new JButton("YOLOLOLOLOLOLOLOLO"));
                buttonsCase.get(buttonsCase.size()-1).addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MessagePlateau msg = new MessagePlateau(TypeMessage.ClicPlateau, t.getCoordonees());
                        observateur.traiterMessagePlateau(msg);
                        System.out.println(msg.getCoo().getX()+" "+msg.getCoo().getY()+" a été cliqué.");
                    }
                });
                
                if (t==null) {
                    buttonsCase.get(buttonsCase.size()-1).setEnabled(false);
                    caseTuiles.add(new JPanel(new BorderLayout()));
                    panelPions.add(new JPanel(new GridLayout(1,4)));
                    caseTuiles.get(caseTuiles.size()-1).setBackground(new Color(0,191,255)); //deepsky blue
                    panelPions.get(panelPions.size()-1).setOpaque(false);
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
                    panelPions.add(new JPanel(new GridLayout(1,4)));
                    caseTuiles.get(caseTuiles.size()-1).setBackground(colorBack);
                    caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    ImageIcon icon = new ImageIcon(t.getImage().getImage().getScaledInstance(170,170, Image.SCALE_DEFAULT));
                    for(Joueur j : t.getLocataires()) {
                        if(j!= null) {
                           JLabel label = new JLabel(new ImageIcon(j.getImage().getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
                           panelPions.get(panelPions.size()-1).add(label,BorderLayout.NORTH);
                        }
                    }
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(icon),BorderLayout.CENTER);
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(t.getIntitule().nomEspace()),BorderLayout.SOUTH);
                    
                    
                    
                    
                    
                    if (t.getIntitule() == Zone.Heliport) {
                       caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                /*if (t.getReliqueDispo() != null) {
                    
                }*/
                
            }
        }
        for (JPanel jpp: caseTuiles) {
            mapPanel.add(jpp);
        }
        for (JButton jp: buttonsCase) {
            calqueButtons.add(jp);
        }
        for (JPanel jppion : panelPions) {
            jppion.setOpaque(false);
            pionsPlateau.add(jppion);
        }
        //window.setLocationRelativeTo(null);  centre la fenêtre
        calque.add(mapPanel,Integer.valueOf(1));
        calque.add(pionsPlateau,Integer.valueOf(2));
        calque.add(calqueButtons,Integer.valueOf(3));
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
