package controller;

import enums.GameState;

import ui.Window;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeController implements ActionListener {

    /**
      * Constant used to describe the pvp button  
    **/
    private static final String PVP_ACTION_COMMAND = "PVP_ACTION_COMMAND";

    /**
      * Constant used to describe the pve button
    **/
    private static final String PVE_ACTION_COMMAND = "PVE_ACTION_COMMAND";

    /**
      * Constant used to describe the credit button
    **/
    private static final String CREDIT_ACTION_COMMAND = "CREDIT_ACTION_COMMAND";
    
    /**
      * The main view of the application 
    **/
    private Window window;

    /**
     * The constructor of the HomeController
     * @param pvpButton The button to choose the pvp game
     * @param pveButton The button to choose the pve button
     * @param creditButton The button to choose the credit view
     * @param window The main view of the application
     */
    public HomeController(JButton pvpButton, JButton pveButton, JButton creditButton, Window window) {
        pvpButton.setActionCommand(PVP_ACTION_COMMAND);
        pvpButton.addActionListener(this);

        pveButton.setActionCommand(PVE_ACTION_COMMAND);
        pveButton.addActionListener(this);

        creditButton.setActionCommand(CREDIT_ACTION_COMMAND);
        creditButton.addActionListener(this);

        this.window = window;
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(PVP_ACTION_COMMAND)) {
            this.window.changeView(GameState.GAME_VIEW_PVP);
        } else if(actionCommand.equals(PVE_ACTION_COMMAND)) {
            this.window.changeView(GameState.GAME_VIEW_PVE);
        } else {
            this.window.changeView(GameState.CREDIT_VIEW);
        }
    }
}