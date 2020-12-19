package ui.container;

import ui.container.Container;
import model.BoardModel;

import controller.container.SelectionPlayerController;

import javax.swing.JRadioButton;
import javax.swing.JButton; 
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;

/**
  * The class <code>SelectionPlayerContainer</code> is the selection of player container
  * @version 1.0
  * @author Dorian Terbah 
**/

public class SelectionPlayerContainer extends Container {

    /**
      * Button to validate the choice of the first player 
    **/
    private JButton validationButton;
    
    /**
      * RadioButton to choice the blue player as the first player 
    **/
    private JRadioButton bluePlayerRadioButton;
    
    /**
      * RadioButton to choice the red player as the first player 
    **/
    private JRadioButton redPlayerRadioButton;

    /**
      * The controller of this container to controls the different actions 
    **/
    private SelectionPlayerController controller;

    /**
      * Constructor of the SelectionPlayerContainer
      * @param model The model of the Board 
    **/
    public SelectionPlayerContainer(BoardModel model) {
        super();
        this.setLayout(new FlowLayout(FlowLayout.CENTER));

        this.bluePlayerRadioButton = new JRadioButton("Joueur bleu", true); //this radiobutton is by default selected
        this.redPlayerRadioButton = new JRadioButton("Joueur rouge", false); //default value

        this.validationButton = new JButton("Valider choix");

        ButtonGroup buttonGroup = new ButtonGroup();
        //add all components to the view, default composition
        buttonGroup.add(this.bluePlayerRadioButton);
        buttonGroup.add(this.redPlayerRadioButton);

        this.add(this.bluePlayerRadioButton);
        this.add(this.validationButton);
        this.add(this.redPlayerRadioButton);

        this.controller = new SelectionPlayerController(this.bluePlayerRadioButton, this.redPlayerRadioButton, this.validationButton, model);
        
    }
}