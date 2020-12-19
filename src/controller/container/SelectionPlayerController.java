package controller.container;

import ui.container.SelectionPlayerContainer;

import model.BoardModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;
import javax.swing.JButton;

/**
  * The class <code>SelectionPlayerController</code> has to control the selection of players
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class SelectionPlayerController implements ActionListener {

    /**
      * Constant used to describe the selection of the blue player 
    **/
    private static final String BLUE_PLAYER_ACTION_COMMAND = "BLUE_PLAYER_ACTION_COMMAND";

    /**
      * Constant used to describe the selection of the red player 
    **/
    private static final String RED_PLAYER_ACTION_COMMAND = "RED_PLAYER_ACTION_COMMAND";

    /**
      * Constant used to describe the description button 
    **/
    private static final String VALIDATION_BUTTON_ACTION_COMMAND = "VALIDATION_BUTTON_ACTION_COMMAND";

    /**
      * The model of the board 
    **/
    private BoardModel model;
    
    /**
      * The radio button to choose the blue player as first player 
    **/
    private JRadioButton bluePlayerRadioButton;
    
    /**
      * The radio button to choose the red player as first player 
    **/
    private JRadioButton redPlayerRadioButton;

    /**
     * 
     * @param bluePlayerRadioButton The radio button to choose the blue player
     * @param redPlayerRadioButton The radio button to choose the red player
     * @param validationButton The validation button
     * @param model The model of the board
     */
    public SelectionPlayerController(JRadioButton bluePlayerRadioButton, JRadioButton redPlayerRadioButton, 
                                     JButton validationButton, BoardModel model
    ) {
        bluePlayerRadioButton.setActionCommand(BLUE_PLAYER_ACTION_COMMAND);
        redPlayerRadioButton.setActionCommand(RED_PLAYER_ACTION_COMMAND);
        validationButton.setActionCommand(VALIDATION_BUTTON_ACTION_COMMAND);

        //add the components to the listener
        bluePlayerRadioButton.addActionListener(this);
        redPlayerRadioButton.addActionListener(this);
        validationButton.addActionListener(this);

        this.model = model;

        this.bluePlayerRadioButton = bluePlayerRadioButton;
        this.redPlayerRadioButton = redPlayerRadioButton;
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(VALIDATION_BUTTON_ACTION_COMMAND)) {
          this.model.selectInitialPlayer(this.redPlayerRadioButton.isSelected());
        }
    }
}