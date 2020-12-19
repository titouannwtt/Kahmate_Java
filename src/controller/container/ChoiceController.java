package controller.container;

import model.BoardModel;

import undo.BoardSave;
import undo.UndoRedo;

import ui.container.ChoiceContainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
/**
  * The class <code>ChoiceController</code> has to control the turns
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class ChoiceController implements ActionListener {

    /**
      * Constant used to describe the endTurnButton 
    **/
    private static final String END_TURN_ACTION_COMMAND = "END_TURN_ACION_COMMAND";

    /**
      * Constant used to describe the undoButton 
    **/
    private static final String UNDO_ACTION_COMMAND = "UNDO_ACTION_COMMAND";

    /**
      * Constant used to describe the redo button 
    **/
    private static final String REDO_ACTION_COMMAND = "REDO_ACTION_COMMAND";

    /**
      * The container that this controller controls 
    **/
    private ChoiceContainer container;

    /**
      * The model of the current board 
    **/
    private BoardModel model;

    /**
     * Constructor of the ChoiceController
     * @param container The container controlled by this
     * @param endTurn The end turn button
     * @param undoButton The undo button
     * @param redoButton The redo button
     * @param model The model of the board
     */
    public ChoiceController(ChoiceContainer container, JButton endTurn, JButton undoButton, JButton redoButton, BoardModel model) {
        this.container = container;
        endTurn.setActionCommand(END_TURN_ACTION_COMMAND);
        endTurn.addActionListener(this);

        undoButton.setActionCommand(UNDO_ACTION_COMMAND);
        undoButton.addActionListener(this);

        redoButton.setActionCommand(REDO_ACTION_COMMAND);
        redoButton.addActionListener(this);

        this.model = model;
    }
    
    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        String actionCommand = event.getActionCommand();
        if(actionCommand.equals(END_TURN_ACTION_COMMAND)) {
            this.container.changeMessage();
        } else if(actionCommand.equals(UNDO_ACTION_COMMAND)) {
            this.container.setVersion(UndoRedo.instance().undo(new BoardSave(this.model.getBluePlayer(), this.model.getRedPlayer(), this.model.getBall(), this.model.isRedPlayerPlaying()) ));
        } else if(actionCommand.equals(REDO_ACTION_COMMAND)) {
            this.container.setVersion(UndoRedo.instance().redo(new BoardSave(this.model.getBluePlayer(), this.model.getRedPlayer(), this.model.getBall(), this.model.isRedPlayerPlaying()) ));
        }
    }
}