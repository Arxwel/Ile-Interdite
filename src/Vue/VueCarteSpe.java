/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Controleur.Message;
import Controleur.Observateur;
import Controleur.TypeMessage;
import Modèle.CarteTresor;
import Modèle.Joueur;
import Modèle.TypeCarte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import static java.awt.SystemColor.window;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author pasdelor
 */

public class VueCarteSpe { 
    private Observateur o;
    private JFrame window;
    private ArrayList<JButton> cartesBoutons = new ArrayList<>();
    private CarteTresor carteUtilisee;
    
    public VueCarteSpe(Joueur j, ArrayList<CarteTresor> cartesSpeciales) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        o = j.getVueAventurier().getObservateur();
        window.setSize(500, 200);
        window.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setTitle("Utiliser une Carte Spéciale");
        JPanel mainPanel = new JPanel(new BorderLayout());
        window.add(mainPanel);
        
        JPanel cartesAUtiliser = new JPanel(new GridLayout(1,9));
        cartesAUtiliser.setBorder(BorderFactory.createTitledBorder("Choix de la Carte à utiliser"));
        for(CarteTresor ct : cartesSpeciales) {
            JButton carte = new JButton(ct.getImage());
            carte.setEnabled(true);
            cartesAUtiliser.add(carte);
            cartesBoutons.add(carte);
            carte.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    carteUtilisee = ct;
                    //j.useCarteSpe(carteUtilisee);
                    Message msg;
                    if (carteUtilisee.getType() == TypeCarte.SpécialHélicoptère) {
                        msg = new Message(TypeMessage.CarteSpeHelico);
                        System.out.println("[Vue] Hélico");
                    } else {
                        msg = new Message(TypeMessage.CarteSpeSac);
                        System.out.println("[Vue] Sac");
                    }
                    window.dispose();
                    o.traiterMessage(msg);
                }
            });
        }
        
        //mainPanel.add(messageDef, BorderLayout.NORTH);
        mainPanel.add(cartesAUtiliser, BorderLayout.SOUTH);
        mainPanel.repaint();
        
        window.setVisible(true);
    }
}
