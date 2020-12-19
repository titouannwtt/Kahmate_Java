package ui.container;

import ui.container.Container;

import undo.BoardSave;
import model.BoardModel;

import controller.container.ChoiceController;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.FlowLayout;
/**
  * The class <code>ChoiceContainer</code> is the choice of turn container
  * @version 1.0
  * @author Dorian Terbah 
**/

public class ChoiceContainer extends Container {

    /**
      * The message for the red player 
    **/
    private static final String RED_PLAYER_PLAYING = "Le joueur rouge peut jouer";

    /**
      * The message for the blue player
    **/
    private static final String BLUE_PLAYER_PLAYING = "Le joueur bleu peut jouer";

    /**
      * A label to show to the players who is playing 
    **/
    private JLabel whoMustPlay;
    
    /**
      * A button that enable to the players to pass their round 
    **/
    private JButton endTurn;

    /**
      * A button to load a previous save of the game if it exists 
    **/
    private JButton undoButton;

    /**
      * A button to load a next version of the game if it exists 
    **/
    private JButton redoButton;

    /**
      * The model of the board to give the hand to the next player 
    **/
    private BoardModel model;

    /**
      * The controller to controls the differents actions of this container 
    **/
    private ChoiceController controller;
    
    /**
      * Constructor of the ChoiceContainer
      * @param model The model of the Board
    **/
    public ChoiceContainer(BoardModel model) {
        super();
        this.model = model;

        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        String text = (this.model.isRedPlayerPlaying()) ? RED_PLAYER_PLAYING : BLUE_PLAYER_PLAYING;
        this.whoMustPlay = new JLabel(text);

        this.endTurn = new JButton("Finir tour");
		this.undoButton = new JButton("UNDO");
        this.redoButton = new JButton("REDO");

        this.add(this.whoMustPlay);
        this.add(this.endTurn);
		this.add(this.undoButton);
        this.add(this.redoButton);

        this.controller = new ChoiceController(this, this.endTurn, this.undoButton, this.redoButton, this.model);
    }

    /**
      * Set the previous version to the model 
    **/
    public void setVersion(BoardSave save) {
        this.model.setVersion(save);
    }

    /*********************************      
     *********Change message**********
    *********************************/
    
    /**
      * Change the message according to the player who is playing
    **/
    public void changeMessage() {
        this.model.toggleIsRedPlayerPlaying();
        String text = (this.model.isRedPlayerPlaying()) ? RED_PLAYER_PLAYING : BLUE_PLAYER_PLAYING;
        //refresh movements
        this.model.refreshMovements();
        this.whoMustPlay.setText(text);

        this.model.refreshInactivity();
    }

    /**
      * Change the message according to the player who is playing
      * @param isRedPlayerPlaying If itis the red player to play
    **/
    public void updateRound(boolean isRedPlayerPlaying) {
        String text = (isRedPlayerPlaying) ? RED_PLAYER_PLAYING : BLUE_PLAYER_PLAYING;
        this.whoMustPlay.setText(text);
    }
}