package controller.container;

import ui.BoardView;
import ui.container.StartGameContainer;
import ui.Window;

import enums.BoardState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import helper.Console;
/**
  * The class <code>StartGGameController</code> has to control the start's container
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class StartGameController implements ActionListener {

    /**
      * Constant used to describe the start button 
    **/
    private static final String START_GAME_ACTION_COMMAND = "START_GAME_ACTION_COMMAND";

    /**
      * The view of the board 
    **/
    private BoardView view;

    /**
     * Constructor of the StartGameController
     * @param startButton The button to begin the game
     * @param view The view of the board
     */
    public StartGameController(JButton startButton, BoardView view) {
        startButton.setActionCommand(START_GAME_ACTION_COMMAND);
        startButton.addActionListener(this);

        this.view = view;
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(START_GAME_ACTION_COMMAND)) {
            this.view.changeContainer(BoardState.GAME_IN_PAUSE_STATE);
        }
    }
}