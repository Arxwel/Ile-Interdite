/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author salmona
 */
public class VueMonteeEaux extends JPanel{
    private ImageIcon image ;
    private BufferedImage pointeurNiveau;
    JLabel titre ;
    
    public VueMonteeEaux() {
        super();
        image = new ImageIcon(this.getClass().getResource("/resources/Niveau.png"));
        JLabel imgCarte = new JLabel(new ImageIcon(image.getImage().getScaledInstance(425,850, Image.SCALE_DEFAULT)));
        this.add(imgCarte);
       
    }
    
    
    public static void main(String[] args) {
        JFrame window = new JFrame() ;
        window.setSize(390, 770);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        window.setLocation(1500, 15); //dim.height/2-window.getSize().height/2

        VueMonteeEaux vME = new VueMonteeEaux();
        window.add(vME);

        window.setVisible(true);
        vME.repaint();
    }
    
    public void monteDesEaux(int niveauEau) {
        
    }
}
