package controller;

import ui.Window;

import enums.GameState;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>CreditController</code> is the controller of the credit view
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class CreditController implements ActionListener {

    /**
      * Constant to represent the back button 
    **/
    private static final String BACK_ACTION_COMMAND = "BACK_ACTION_COMMAND";

    /**
      * The main view of the application 
    **/
    private Window window;

    /**
     * Constructor of the CreditController
     * @param backButton The button to return at home
     * @param window The main view of the application
     */
    public CreditController(JButton backButton, Window window) {
        backButton.setActionCommand(BACK_ACTION_COMMAND);
        backButton.addActionListener(this);

        this.window = window;
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(BACK_ACTION_COMMAND)) {
            this.window.changeView(GameState.HOME_VIEW);
        }
    }
}