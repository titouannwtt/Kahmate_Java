package ui;

import enums.GameState;

import helper.Assert;

import ui.HomeView;
import ui.BoardView;
import ui.CreditView;
import ui.EndGameView;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.io.IOException;

/**
  * The class <code>Window</code> is the graphics interface of the application
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, Rémi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class Window extends JFrame {

    /**
      * Constant that represents the width of the frame 
    **/
    public static final int WIDTH = 900;

    /**
      * Constant that represents the height of the frame 
    **/
    public static final int HEIGHT = 600;

    /**
      * The actual view which is displayed on the application 
    **/
    private JPanel currentView;

    /**
      * The state of the application 
    **/
    private GameState state;

    /**
      * The constructor of the Window 
    **/
    public Window() {
        super("Kamhate");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.currentView = new JPanel(); //initial value

        this.changeView(GameState.HOME_VIEW);
        this.setLocationRelativeTo(null);
    }

    /**
      * Change the current view of the game
      * @param state The tag to describe the futur view
    **/
    public void changeView(GameState state) {
        this.state = state; 
        
        this.remove(this.currentView);
        //show the new view
        if(this.state.equals(GameState.HOME_VIEW)) {
            this.currentView = new HomeView(this);
        } else if(this.state.equals(GameState.GAME_VIEW_PVP)) {
            this.currentView = new BoardView(this, false);
        } else if(this.state.equals(GameState.CREDIT_VIEW)) {
            this.currentView = new CreditView(this);
        }

        //check if the window can be resizable
        this.setResizable(!this.state.equals(GameState.HOME_VIEW));
        this.add(this.currentView, BorderLayout.CENTER);
        this.revalidate();
    }

    /**
	  * Close the window with an error pop-up
	  * @param message The message to display in pop-up
	**/
	public void close(String message){
		(new JOptionPane()).showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
        this.dispose();
        System.exit(1);
    }

    /**
      * Display the end menu
      * @param isRedPlayerWinning  
    **/
    public void displayEndMenu(boolean isRedPlayerWinning) {
        this.state = GameState.END_VIEW;
        this.remove(this.currentView);

        this.currentView = new EndGameView(this, isRedPlayerWinning);

        this.add(this.currentView, BorderLayout.CENTER);
        this.revalidate();
    }
}