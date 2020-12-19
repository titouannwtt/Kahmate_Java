package controller.popup;

import ui.popup.ChoiceCardPopUp;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
/**
  * The class <code>ChocieCardController</code> controls the choiceCardPopup
  * @version 1.0
  * @author Dorian Terbah
**/

//DONT USE !!!
public class ChoiceCardController implements ActionListener {

    private ChoiceCardPopUp popup;

    public ChoiceCardController(ChoiceCardPopUp popup){
        this.popup = popup;
    }

    /**
      * React to an button's event
      * @param event The actual event
    **/
    @Override 
    public void actionPerformed(ActionEvent event) {
        JButton selectedButton = (JButton)event.getSource();
        this.popup.close(Integer.parseInt(selectedButton.getText()));
    }

    /**
      * Add this listener to a button
      * @param cardButton The button 
    **/
    public void addActionListenerTo(JButton cardButton) {
        cardButton.addActionListener(this);
    }
}