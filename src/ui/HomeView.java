package ui;

import ui.Window;
import ui.CustomButton;

import helper.Palette;

import controller.HomeController;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.imageio.ImageIO;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
/**
  * The class <code>HomeView</code> is the representation of the home view
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class HomeView extends JPanel {

    /**
      * A button for choosing a PVP game 
    **/
    private JButton pvpButton;

    /**
      * A button for choosing a PVE game 
    **/
    private JButton pveButton;

    /**
      * A button to access to the CreditView 
    **/
    private JButton creditButton;

    /**
      * The backgroundn image of this view 
    **/
    private BufferedImage backgroundImage;

    /**
     * Constructor of the HomeView
     * @param window The main view of the application
     */
    public HomeView(Window window) {
        //layout menu
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //preparation layout
        
        
        //chargement img menu
        try {
             this.backgroundImage = ImageIO.read(new File("./rsc/images/background-menu2.jpg"));
        } catch(IOException imageLoadingFaliedException) {
            window.close("Error when loading background image for the home view");
        }

        this.setBackground(Color.BLACK);
        
        //pvp button view
        this.pvpButton = new CustomButton("Joueur contre Joueur");

        //pve button view
        this.pveButton = new CustomButton("Joueur contre IA");

        //credit button view
        this.creditButton = new CustomButton("R\u00e8gles / Cr\u00e9dit");

        HomeController controller = new HomeController(this.pvpButton, this.pveButton, this.creditButton, window);

        //invisible element
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH; //etirement
        gbc.anchor = GridBagConstraints.CENTER;//position
        gbc.insets = new Insets(50, 50, 50, 50); //marge
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(new JLabel(),gbc);

        //invisible element
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH; //etirement
        gbc.anchor = GridBagConstraints.CENTER;//position
        gbc.insets = new Insets(50, 50, 50, 50); //marge
        gbc.ipadx = 20;
        gbc.ipady = 20;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(new JLabel(),gbc);

        //add components to view
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE; //etirement
        gbc.anchor = GridBagConstraints.CENTER;//position
        gbc.insets = new Insets(50, 50, 50, 50); //marge
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.pvpButton,gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE; //etirement
        gbc.anchor = GridBagConstraints.CENTER;//position
        gbc.insets = new Insets(50, 50, 50, 50); //marge
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.pveButton,gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE; //etirement
        gbc.anchor = GridBagConstraints.CENTER;//position
        gbc.insets = new Insets(50, 50, 50, 50); //marge
        gbc.ipadx = 50;
        gbc.ipady = 50;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(this.creditButton,gbc);
    }


    /**
     * Draw the background for main menu
     * @param g The graphic tool
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int x = (this.getWidth() - backgroundImage.getWidth(null)) / 2;
        int y = (this.getHeight() - backgroundImage.getHeight(null)) / 2;
        g2d.drawImage(backgroundImage, x, y, null);
    }
}
