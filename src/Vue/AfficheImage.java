/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author pasdelor
 */
public class AfficheImage extends JPanel
{
private Image image;

AfficheImage(String s)
{
image = getToolkit().getImage(s);
}

public void paintComponent(Graphics g)
{
super.paintComponent(g);
g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
}
} 
