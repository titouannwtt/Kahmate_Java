package ui;

import helper.Console;

import java.awt.image.BufferedImage;

import java.awt.Color;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.File;

import javax.swing.JButton;
import javax.swing.ImageIcon;
/**
 * The class <code>CustomButton</code> create custom button
  * @version 1.0
  * @author Rémy Gaudru 
 */
public class CustomButton extends JButton {

    /**
      * The background image of the button 
    **/
    private BufferedImage buttonIcon;

    /**
     * Constructor of the CustomButton
     * @param text The text that is displayed on the button
     */
    public CustomButton(String text) {
        try {
            this.buttonIcon = ImageIO.read(new File("./rsc/images/button.png"));            
        } catch (IOException loadingImageException) {
            Console.log("Erreur chargment image menu");
            System.exit(1);
        }
        this.setIcon(new ImageIcon(this.buttonIcon));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.CENTER);
        this.setForeground(Color.WHITE);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setText(text);
    }
}