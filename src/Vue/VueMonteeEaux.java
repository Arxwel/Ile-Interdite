/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
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
    JLabel titre ;
    
    public VueMonteeEaux() {
        super();
        image = new ImageIcon(this.getClass().getResource("/resources/Niveau.png"));
        JLabel imgCarte = new JLabel(new ImageIcon(image.getImage().getScaledInstance(200,1000, Image.SCALE_DEFAULT)));
        this.add(imgCarte);
       
    }
    
    
    public static void main(String[] args) {
        JFrame window = new JFrame() ;
        window.setSize(200, 1000);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        window.setLocation(100, dim.height/2-window.getSize().height/2);

        VueMonteeEaux vME = new VueMonteeEaux();
        window.add(vME);

        window.setVisible(true);
        vME.repaint();
    }
}
