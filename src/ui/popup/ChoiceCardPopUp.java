package ui.popup;

import model.Card;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
/**
  * The class <code>ChoiceCardPopUp</code> enables the choice of card for a player
  * @version 1.0
  * @author Dorian Terbah
**/

public class ChoiceCardPopUp  {

    /**
      * Constructor of the ChoiceCardPopUp 
    **/
    public ChoiceCardPopUp() {

    }    

    /**
      * Display a popup to choose a card
      * @param cards The cards 
      * @param title The title of the popup
    **/
    public static final Card choiceCard(Card[] cards, String title) {
        JOptionPane popup = new JOptionPane();
        
        Card card = (Card)popup.showInputDialog(null,
            "Choix carte",
            title,
            JOptionPane.QUESTION_MESSAGE,
            null,
            cards,
            cards[cards.length - 1]
        );

        return card;
    }
}