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
public class VueMonteeEaux extends JFrame{
    private ImageIcon image ;
    private JPanel mainPanel;
    private JLabel imgCarte;
    
    public VueMonteeEaux(int niveau) {
        super();
        this.setSize(310, 700);
        mainPanel = new JPanel();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocation(1550, 20); //dim.height/2-window.getSize().height/2
        image = new ImageIcon(this.getClass().getResource("/resources/niveauDesEaux"+niveau+".png"));
        imgCarte = new JLabel(new ImageIcon(image.getImage().getScaledInstance(320,745, Image.SCALE_DEFAULT)));
        mainPanel.add(imgCarte);
        this.add(mainPanel);
       
    }
    
    public void monteDesEaux(int niveau) {
        this.mainPanel.remove(imgCarte);
        image = new ImageIcon(this.getClass().getResource("/resources/niveauDesEaux"+niveau+".png"));
        imgCarte = new JLabel(new ImageIcon(image.getImage().getScaledInstance(320,745, Image.SCALE_DEFAULT)));
        mainPanel.add(imgCarte);
        this.validate();
        this.repaint();
    }

    /**
     * @param image the image to set
     */
    public void setImage(ImageIcon image) {
        this.image = image;
    }
}
