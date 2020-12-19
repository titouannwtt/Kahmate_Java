package ui.container;

import ui.BoardView;

import javax.swing.JButton;

import java.awt.FlowLayout;

import controller.container.StartGameController;

/**
  * The class <code>StartGameContainer</code> is the base of startGame's container
  * @version 1.0
  * @author Dorian Terbah 
**/

public class StartGameContainer extends Container {

    /**
      * BUtton to begin the game  
    **/
    private JButton beginGame;

    /**
      * Controller that controls the different actions on this container 
    **/
    private StartGameController controller;

    /**
      * Constructor of the StartGameContainer
      * @param boardView The representation of the board 
    **/
    public StartGameContainer(BoardView boardview) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.beginGame = new JButton("Commencer partie");
        this.add(this.beginGame);

        this.controller = new StartGameController(this.beginGame, boardview);
    }
}