package ui.container;

import java.awt.Dimension;

import javax.swing.JPanel;

import java.awt.Color;

import ui.Window;

/**
  * The class <code>Container</code> is the base of board's container
  * @version 1.0
  * @author Dorian Terbah 
**/

public class Container extends JPanel {

    /**
      * Constructor of the Container 
    **/
    public Container() {
        super();
        this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT / 10));
    }
}