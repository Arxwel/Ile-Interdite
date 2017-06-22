/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rakotomg
 */
public class VueReliques extends JFrame{
    
    private JPanel mainPanel;
    private JLabel imgRelique1;
    private ImageIcon image1;
    private JLabel imgRelique2;
    private ImageIcon image2;
    private JLabel imgRelique3;
    private ImageIcon image3;
    private JLabel imgRelique4;
    private ImageIcon image4;
    private Dimension dim;

    
    public VueReliques() {
        
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(500,300);        
        this.setSize((int) (dim.getWidth()*0.15), (int) (dim.getHeight()*0.4));
        mainPanel = new JPanel(new BorderLayout());
        this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Reliques récupérées");
        this.setLocation((int) (dim.getWidth()*0.8), (int) (dim.getHeight()*0.53));        
        //imgRelique = new JLabel(new ImageIcon(image.getImage().getScaledInstance((int) (dim.getWidth()*0.05), (int) (dim.getHeight()*0.05), Image.SCALE_DEFAULT)));        //mainPanel.add(imgRelique);
        this.add(mainPanel);
        
        JPanel reliques = new JPanel();
        reliques.setLayout(new GridLayout(2,2));;
        image1 = new ImageIcon(this.getClass().getResource("/resources/Trésors/CaliceG.png"));
        image1 = new ImageIcon(image1.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
        imgRelique1 = new JLabel(image1);
        reliques.add(imgRelique1);
        
        image2 = new ImageIcon(this.getClass().getResource("/resources/Trésors/PierreG.png"));
        image2 = new ImageIcon(image2.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
        imgRelique2 = new JLabel(image2);
        reliques.add(imgRelique2);
        
        image3 = new ImageIcon(this.getClass().getResource("/resources/Trésors/StatueG.png"));
        image3 = new ImageIcon(image3.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
        imgRelique3 = new JLabel(image3);
        reliques.add(imgRelique3);
        
        image4 = new ImageIcon(this.getClass().getResource("/resources/Trésors/CristalG.png"));
        image4 = new ImageIcon(image4.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
        imgRelique4 = new JLabel(image4);
        reliques.add(imgRelique4);

                
        mainPanel.add(reliques, BorderLayout.CENTER);
    }
    
    public void update(boolean[] reliquesPrises) {
        System.out.println("Rafraichissement de Vue Reliques");
        
        if (reliquesPrises[0]) {
            image4 = new ImageIcon(this.getClass().getResource("/resources/Trésors/CristalC.png"));
            image4 = new ImageIcon(image4.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
            imgRelique4.setIcon(image4);
        }
        
        if (reliquesPrises[1]) {
            image3 = new ImageIcon(this.getClass().getResource("/resources/Trésors/StatueC.png"));
            image3 = new ImageIcon(image3.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
            imgRelique3.setIcon(image3);
        }
        
        if (reliquesPrises[2]) {
            image2 = new ImageIcon(this.getClass().getResource("/resources/Trésors/PierreC.png"));
            image2 = new ImageIcon(image2.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
            imgRelique2.setIcon(image2);
        }
        
        if (reliquesPrises[3]) {
            image1 = new ImageIcon(this.getClass().getResource("/resources/Trésors/CaliceC.png"));
            image1 = new ImageIcon(image1.getImage().getScaledInstance((int) (dim.getWidth()*0.098), (int) (dim.getHeight()*0.16), Image.SCALE_DEFAULT));
            imgRelique1.setIcon(image1);
        }
        
        this.validate();
        this.repaint();
        
    }                
}

