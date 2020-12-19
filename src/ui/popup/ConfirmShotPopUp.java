package ui.popup;

import javax.swing.JOptionPane;
/**
  * The class <code>ChoiceCardPopUp</code> enables the choice of card for a player
  * @version 1.0
  * @author Dorian Terbah
**/

public class ConfirmShotPopUp {

    /**
	  * Constructor of the ConfirmShotPopUp 
	**/
    public ConfirmShotPopUp() {

    }    

    /**
      * Display a popup to choose a card
      * @param cards The cards 
      * @param title The title of the popup
    **/
    public static final boolean confirmShot() {
        JOptionPane popup = new JOptionPane();
        
        int retour = popup.showConfirmDialog(null, "Voulez faire un shot en avant ?", 
        "Confirmer shot", JOptionPane.OK_CANCEL_OPTION);

        return (retour == JOptionPane.OK_OPTION) ? true : false;
    }
}