package ui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import ui.Window;

import controller.EndGameController;

/**
  * The class <code>EndGameView</code> is end menu
  * @version 1.0
  * @author Dorian Terbah
**/

public class EndGameView extends JPanel {

    /**
      * A button to return at home 
    **/
    private JButton backToHome;

    /**
      * The background image of the view 
    **/
    private BufferedImage backgroundImage;

    /**
      * The controller to control different actions in this view 
    **/
    private EndGameController controller;

    /**
     * Constructor of the EndGameView
     * @param window The main of the application
     * @param isRedPlayerWinning The winner of the game
     */
    public EndGameView(Window window, boolean isRedPlayerWinning) {
        super();

        String image = "./rsc/images/";
        image += (isRedPlayerWinning) ? "red-victory.png" : "blue-victory.png";

        try {
            this.backgroundImage = ImageIO.read(new File(image));
        } catch(IOException loadingException) {
            System.err.println("Error when loading background image for the end menu");
            System.exit(1);
        }

        this.backToHome = new JButton("Retour accueil");
        this.add(this.backToHome);

        this.controller = new EndGameController(window, this.backToHome);
    }

    /**
     * Draw the background for end menu
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