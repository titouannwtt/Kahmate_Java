package undo;

import helper.Assert; 

import undo.BoardSave;

import model.Ball;
import model.Player;
import model.Hand;
import model.Card;

import java.util.LinkedList;
/**
  * The class <code>UndoRedo</code> controls the undo and the redo
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class UndoRedo {
    
    /**
      * It's a singleton to avoid multple instance of this class 
    **/
    private static UndoRedo undoRedo;

    /**
      * Stack used for the undo 
    **/
    private LinkedList<BoardSave> undoStack;

    /**
      * Stack used for the redo 
    **/
    private LinkedList<BoardSave> redoStack;

    /**
      * Constructor for the UndoRedo 
    **/
    private UndoRedo() {
        this.undoStack = new LinkedList<BoardSave>();
        this.redoStack = new LinkedList<BoardSave>();
    }

    /**
      * Get the only instance of the class
      * @return The only instance of the class 
    **/
    public static UndoRedo instance() {
        if(Assert.isNull(UndoRedo.undoRedo)) {
            UndoRedo.undoRedo = new UndoRedo();
        }

        return UndoRedo.undoRedo;
    }

    /**
      * Give a previous version of the game 
      * @return The previous version of the game
    **/
    public BoardSave undo(BoardSave currentBoard) {
        if(!this.undoStack.isEmpty()) {
            this.redoStack.add(currentBoard);
            return this.undoStack.removeLast();
        }

        return null;
    }

    /**
      * Give a next version of the game
      * @return The next version of the game
    **/
    public BoardSave redo(BoardSave currentBoard) {
        if(!this.redoStack.isEmpty()) {
            this.undoStack.add(currentBoard);
            return this.redoStack.removeLast();
        }

        return null;
    }

    /**
      * Push a new version of the game
      * @param bluePlayer The blue player
      * @param redPlayer The red player
      * @param ball The ball
      * @param isRedPlayerPlaying If it is the red player that is playing 
    **/
    public void push(Player bluePlayer, Player redPlayer, Ball ball, boolean isRedPlayerPlaying) {
        this.undoStack.add(new BoardSave(bluePlayer, redPlayer, ball, isRedPlayerPlaying));
        this.clearRedoStack();
    }


    /**
      * Clear the redoStack pile
    **/
    private void clearRedoStack() {
    	if(!this.redoStack.isEmpty()) {
    		this.redoStack.clear();
    	}	
    }

    /**
      * Refresh all stacks 
    **/
    public void refreshStacks() {
        this.redoStack.clear();
        this.undoStack.clear();
    }
}