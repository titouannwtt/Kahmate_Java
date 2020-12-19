package controller;

import ui.Cell;
import ui.GoalCell;
import ui.BoardView;

import enums.BoardState;

import helper.Console;

import model.BoardModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
  * The class <code>CellController</code> has to control cells
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/


public class BoardController implements ActionListener {

    /**
      * The view of the board 
    **/
    private BoardView boardView;

    /**
      * The model of the board 
    **/
    private BoardModel boardModel;

    /**
     * Constructor of the BoardController
     * @param boardView The view of the board
     * @param boardModel The model of the board
     */
    public BoardController(BoardView boardView, BoardModel boardModel) {
        this.boardView = boardView;
        this.boardModel = boardModel;
    }

    @Override 
    /**
      * React to an button's event
      * @param event The actual event
    **/
    public void actionPerformed(ActionEvent event) {
        Cell cell = (Cell)event.getSource();

        this.boardModel.setCellSelected(cell);
        if(this.boardModel.getState().equals(BoardState.IN_GAME_STATE)) {
            //this.view.showRange(this.model.getAvailableRange());
        }
    }

    /**
      * Add this listener to a cell
      * @param cell The target  
    **/
    public void addActionListenerTo(Cell cell) {
        cell.addActionListener(this);
    }
}