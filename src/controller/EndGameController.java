package controller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import ui.Window;

import enums.GameState;
/**
  * The class <code>EndGameController</code> controls the end menu
  * @version 1.0
  * @author Dorian Terbah
**/

public class EndGameController implements ActionListener {

    /**
      * Constant used to represent the end button 
    **/
    private static final String END_BUTTON_ACTION_COMMAND = "END_BUTTON_ACTION_COMMAND";

    /**
      * The main view of the application 
    **/
    private Window window;

    /**
     * The constructor of the EndGameController
     * @param window The main view of the application
     * @param backToMenu The button to return to the home
     */
    public EndGameController(Window window, JButton backToMenu) {
        this.window = window;

        backToMenu.setActionCommand(END_BUTTON_ACTION_COMMAND);
        backToMenu.addActionListener(this);
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(END_BUTTON_ACTION_COMMAND)) {
            this.window.changeView(GameState.HOME_VIEW);
        }
    }
}