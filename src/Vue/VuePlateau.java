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
import java.awt.Rectangle;
import java.awt.Toolkit;
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
    
    private ArrayList<JPanel> caseTuiles;
    private ArrayList<JPanel> panelPions;
    private ArrayList<JButton> buttonsCase;
    
    private JLayeredPane calque;
    
    private JPanel mapPanel;
    private JComponent pionsPlateau;
    private JComponent calqueButtons;
    
    private ArrayList<Tuile> aSurligner;
    
    
    //Affiche le plateau
    public VuePlateau(Controleur c) {
        super("L'Ile Interdite : Jeu");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (dimension.getWidth()*0.55), (int) (dimension.getHeight()*0.9));
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setControleur(c);
        
        caseTuiles = new ArrayList<>();
        panelPions = new ArrayList<>();
        buttonsCase = new ArrayList<>();
        
        aSurligner = new ArrayList<>();
        
        calque = new JLayeredPane();
        calque.setPreferredSize(this.getSize());
        
        mapPanel = new JPanel(new GridLayout(6,6));
        mapPanel.setBounds(new Rectangle(new Dimension(this.getBounds().width,this.getBounds().height-33)));
        
        pionsPlateau = new JPanel(new GridLayout(6,6));
        pionsPlateau.setOpaque(false);
        pionsPlateau.setBounds(new Rectangle(new Dimension(this.getBounds().width,this.getBounds().height-33)));
        
        calqueButtons = new JPanel(new GridLayout(6,6));
        calqueButtons.setBounds(new Rectangle(new Dimension(this.getBounds().width,this.getBounds().height-33)));
        calqueButtons.setOpaque(false);
        
        peindreTuiles();
        
        
        
        calque.add(mapPanel,Integer.valueOf(1));
        calque.add(pionsPlateau,Integer.valueOf(2));
        calque.add(calqueButtons,Integer.valueOf(3));
        
        this.add(calque, BorderLayout.CENTER);
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
             
    }
    
    private void peindreTuiles() {
        
        Color colorBack;
        Grille g = controleur.getGrille();
        
        for (int x=0; x<6; x++) {           //Pour toutes les cases de la Grille
            for (int y=0; y<6; y++) {
                
                Tuile t = g.getTuile(x,y);
                
                buttonsCase.add(new JButton(""));   //jouter un bouton sur le calque
                buttonsCase.get(buttonsCase.size()-1).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        MessagePlateau msg = new MessagePlateau(TypeMessage.ClicPlateau, t.getCoordonees());
                        observateur.traiterMessagePlateau(msg);
                    }
                });
                
                
                caseTuiles.add(new JPanel(new BorderLayout()));
                panelPions.add(new JPanel(new GridLayout(1,4)));
                
                if (t==null) {      //Si la case est non jouable
                    buttonsCase.get(buttonsCase.size()-1).setEnabled(false); //désactiver le bouton
                    
                    if(x== 0 && y ==5) {//Afficher Image de Fond
                        icona = new ImageIcon(this.getClass().getResource("/ImagesTuiles/EauRoseVent.png"));
                    } else {
                        icona = new ImageIcon(this.getClass().getResource("/ImagesTuiles/Eau.png"));
                    }
                    
                    icona = new ImageIcon(icona.getImage().getScaledInstance(180,180, Image.SCALE_DEFAULT));
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(icona), BorderLayout.CENTER);
                    
                } else {    //si Case Jouable
                    if (aSurligner.contains(t)) {
                        buttonsCase.get(buttonsCase.size()-1).setBorder(BorderFactory.createLineBorder(Color.GREEN));
                    }
                    
                    if (null==t.getEtat()) {
                        colorBack = Color.LIGHT_GRAY;
                    } else 
                        switch (t.getEtat()) {//choix de la couleur en fonction de l'état
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
                    
                    caseTuiles.get(caseTuiles.size()-1).setBackground(colorBack);//colorer la tuile avec la couleur précédemant choisie
                    caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    
                    ImageIcon icon = new ImageIcon(t.getImage().getImage().getScaledInstance(170,170, Image.SCALE_DEFAULT));//Affichage d'une image de fond
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(icon),BorderLayout.CENTER);
                    
                    for(Joueur j : t.getLocataires()) {//affichage des pions
                        if(j!= null) {
                           JLabel label = new JLabel(new ImageIcon(j.getImage().getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
                           panelPions.get(panelPions.size()-1).add(label,BorderLayout.NORTH);
                        }
                    }
                    
                    caseTuiles.get(caseTuiles.size()-1).add(new JLabel(t.getIntitule().nomEspace()),BorderLayout.SOUTH);//affichage du nom de la case
                    
                    if (t.getIntitule() == Zone.Heliport) {//Mise en surbrillance de l'Héliport
                       caseTuiles.get(caseTuiles.size()-1).setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    }
                    
                    if (aSurligner.contains(t)) {
                        buttonsCase.get(buttonsCase.size()-1).setBorder(BorderFactory.createLineBorder(Color.RED,3));
                    }
                }
            }
        }
        
        for (JPanel jpp: caseTuiles) {//Ajout des cases au plateau
            mapPanel.add(jpp);
        }
        
        for (JButton jp: buttonsCase) {//ajout des boutons au calque Boutons
            jp.setOpaque(false);
            jp.setContentAreaFilled(false);
            calqueButtons.add(jp);
        }
        
        for (JPanel jppion : panelPions) {//ajout des pions au calque Pions
            jppion.setOpaque(false);
            pionsPlateau.add(jppion);
        }
        
    }
    
    
    
    public void surligner(ArrayList<Tuile> tuiles) {
        aSurligner=tuiles;
        
        this.update();
    }
    
    public void afficher() {
        this.setVisible(true);
    }
    
    public void update() {
        System.out.println("Rafraichissement de Vue Plateau");
        
        caseTuiles.clear();
        panelPions.clear();
        buttonsCase.clear();
        
        mapPanel.removeAll();
        pionsPlateau.removeAll();
        calqueButtons.removeAll();
        
        this.peindreTuiles();
        
        this.validate();
        this.repaint();
        
    }
    
    public void setControleur(Controleur c) {
        this.controleur=c;    
    }
    
    public void setObservateur(Observateur o) {
        this.observateur = o;
    }
    
}
