package Vue;


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
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import CodeFourni.Utils.Pion;
import Modèle.*;

 
public class VueAventurier  {
     
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnAller  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnTerminerTour;
    private final JTextField position;
    
    public VueAventurier (Joueur j){

        this.window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setSize(350, 200);

        window.setTitle(j.getNom());
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(j.getCouleur(), 2)) ;

        // =================================================================================
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(j.getCouleur());
        panelAventurier.add(new JLabel(j.getNom(),SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);
   
        // =================================================================================
        // CENTRE : 1 ligne pour position courante
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, j.getCouleur()));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);
        
        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30); 
        position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


        // =================================================================================
        // SUD : les boutons
        this.panelBoutons = new JPanel(new GridLayout(2,2));
        this.panelBoutons.setOpaque(false);
        mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnAller = new JButton("Aller") ;
        this.btnAssecher = new JButton( "Assecher");
        this.btnAutreAction = new JButton("AutreAction") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.panelBoutons.add(btnAller);
        this.panelBoutons.add(btnAssecher);
        this.panelBoutons.add(btnAutreAction);
        this.panelBoutons.add(btnTerminerTour);

        this.window.setVisible(true);
        mainPanel.repaint();
    }
 

    
     public static void main(String [] args) {
        // Instanciation de la fenêtre 
        VueAventurier vueAventurier = new VueAventurier (new Messager("Jack"));
    }

    /**
     * @return the window
     */
    public JFrame getWindow() {
        return window;
    }
}

 


