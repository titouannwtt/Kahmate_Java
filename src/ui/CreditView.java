package ui;

import ui.Window;

import controller.CreditController;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Dimension;
/**
  * The class <code>CreditView</code> is the representation of the rules and the description of the project
  * @version 1.0
  * @author Dorian Terbah, Pierre Castro, Titouann Wattelet, R\u00e9mi Gaudru, Valentin Froidefond, Lucas Augusto 
**/

public class CreditView extends JPanel {

    /**
      * A label to describe all informations about the application 
    **/
    private JLabel resume;

    /**
      * A button to return to the home view 
    **/
    private JButton backButton;

    /**
      * A scroll pane to go down or go up
    **/
    private JScrollPane scrollPane;

    /**
      * A panel to put the scroll pane 
    **/
    private JPanel pane;

    public CreditView(Window window) {
        //the layout is temporary
        super(new BorderLayout());
        this.backButton = new JButton("Home");
        //set the icon to the back button

        CreditController controller = new CreditController(this.backButton, window);

        String style = new StringBuilder()
                        .append("<style>")
                            .append("td, tr {")
                            .append("border:1px solid black;}")
                        .append("</style>")
                        .toString();
        //the resume is in a html's
        String resumeText = new StringBuilder()
                            .append("<html>")
                            .append(style)
                            .append("Cr\u00e9ateurs du projet : <ul style='list-style:none'>")
                                .append("<li>Dorian Terbah</li>")
                                .append("<li>Pierre Castro</li>")
                                .append("<li>Titouann Wattelet</li>")
                                .append("<li>R\u00e9my Gaudru</li>")
                                .append("<li>Valentin Froidefond</li>")
                                .append("<li>Lucas Augusto</li>")
                            .append("</ul>")
                            .append("<table>")
                                .append("<tr><td>Comment jouer ?</td><td>Les capitaines doivent d\u00e9cider qui jouera en premier. Lors de son tour, un capitaine peut :<br>- D\u00e9placer 1 ou 2 joueurs. Le d\u00e9placement peut être effectu\u00e9 en 2 fois si besoin. Les d\u00e9placements ne sont pas autoris\u00e9s en diagonale.<br>- Faire autant de passes qu'il le d\u00e9sire. Un même joueur peut recevoir le ballon plusieurs fois durant le même tour.<br>- Faire un plaquage (met fin au mouvement de ce joueur).<br>- Marquer un essai (termine le match).</td></tr>")
                            .append("</table>")
                            .append("<table>")
                                .append("<tr><td>Joueurs ordinaires</td><td>Ils peuvent se d\u00e9placer de 3 cases et n’ont pas de bonus offensif ou d\u00e9fensif</td></tr>")
                                .append("<tr><td>1 gros costaud</td><td>Il se d\u00e9place de 2 cases. Il a un bonus de 2 points quand il attaque et de 1 point quand il d\u00e9fend</td></tr>")
                                .append("<tr><td>1 dur</td><td>Il se d\u00e9place de 3 cases et a un bonus de 1 point lorsqu’il attaque</td></tr>")
                                .append("<tr><td>1 rapide</td><td>Il se d\u00e9place de 4 cases et a un malus de 1 point en attaque et en d\u00e9fense</td></tr>")
                                .append("<tr><td>1 fut\u00e9</td><td>Il se d\u00e9place de 3 cases et a un bonus de 1 point lorsqu’il d\u00e9fend</td></tr>")
                            .append("</table>")
                            .append("<table>")
                                .append("<tr><td>Les cartes forme</td><td>La carte 6 indique un joueur au top de sa forme alors que la carte 1 indique un joueur pr\u00eat \u00e0 ̀l'\u00e2me.</td></tr>")
                                .append("<tr><td>R\u00e9solution des actions</td><td>Pour r\u00e9soudre certaines actions (plaquage, forcer le passage, intercepter), les joueurs doivent d\u00e9terminer qui attaque et qui d\u00e9fend).<br>Une fois \u00e9tabli qui est l'attaquant et qui est le d\u00e9fenseur, les deux capitaines jouent une carte forme, le nombre le plus \u00e9lev\u00e9 l'emporte.</td></tr>")
                                .append("<tr><td>Se d\u00e9placer</td><td>Un capitaine peut d\u00e9placer un ou deux joueurs en avant, en arri\u00e8re, \u00e0 droite ou \u00e0 gauche mais pas en diagonal</td>")
                                .append("<tr><td>Forcer le passage</td><td>Lorqu'un joueur A se trouve devant un joueur B, il peut tenter de forcer le passage \u00e0 ce-dernier</td></tr>")
                                .append("<tr><td>Passer</td><td>Le joueur qui poss\u00e8de le ballon peut le passer a un autre joueur. Une passe ne peut avoir lieu que vers l'arri\u00e8re. Un joueur ne peut pas passer le ballon à un joueur devant lui ou sur la même ligne que lui. Le receveur doit être positionn\u00e9 à 1 ou 2 case(s) de distance sur une ligne droite orthogonale ou diagonale</td></tr>")
                                .append("<tr><td>Interception</td><td>Lorsqu'une passe est effectu\u00e9e entre 2 joueurs et qu'un joueur adversaire est pr\u00e9sent entre ceux-ci, l'adversaire peut d\u00e9cider d'intercepter le ballon.</td></tr>")
                                .append("<tr><td>Plaquage</td><td>Pour qu'un plaquage soir possible, le joueur A (celui qui plaque) doit avoir les d\u00e9placements n\u00e9cessaires pour atteindre la case sur laquelle est pr\u00e9sent le joueur B (celui qui est plaqu\u00e9).</td></tr>")
                                .append("<tr><td>Plaquage parfait</td><td>Un plaquage parfait est r\u00e9ussi lorsque le joueur qui plaque d\u00e9passe le joueur plaqu\u00e9 de 2 points lors de la r\u00e9solution de l'action. Dans ce cas, le joueur qui a r\u00e9ussi le plaquage parfait r\u00e9cup\u00e8re le ballon.</td></tr>")
                                .append("<tr><td>Coup de pied à suivre</td><td>Le joueur qui poss\u00e8de le ballon peut effectuer un coup de pied à suivre, c'est le seul moyen d'envoyer le ballon vers l'avant. Le joueur doit \u00eatre positionn\u00e9 'en avant' de son quipe, aucun de ses partenaires ne doit être devant lui. Il peut par contre avoir des partenaires sur la même ligne que lui.</td></tr>")
                                .append("<tr><td>Marquer un essai</td><td>Un essai est marqu\u00e9 lorsque le joueur porteur du ballon finit son d\u00e9placement dans la zone d'en-but. Un essai marque la fin du match. Si les capitaines d\u00e9cident de continuer, ils re-positionnent leurs joueurs et le ballon est re-positionn\u00e9 al\u00e9atoirement au centre du jeu.</td></tr>")   
                            .append("</table>")
                            .append("</html>")
                            .toString();

        this.resume = new JLabel(resumeText);
        this.resume.setVerticalAlignment(JLabel.NORTH);

        this.pane = new JPanel();
        this.pane.setLayout(new BorderLayout());
        this.pane.add(this.resume, BorderLayout.CENTER);
        this.pane.add(this.backButton, BorderLayout.NORTH);

        this.scrollPane = new JScrollPane(this.pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.scrollPane.setPreferredSize(new Dimension(900,600));
        this.add(this.pane);
    }
}