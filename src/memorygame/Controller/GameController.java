package memorygame.Controller;
import memorygame.model.Card;
import memorygame.model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.*;

public class GameController implements ActionListener {
    private Vector turnedCards;

    User Player;

    private int MaxSize;
    private Timer timeToTurnDown;
    private final int TurnDownDelay = 2000;
    private int CardQuantity;

    public GameController() {
        this.Player = new User();
        this.turnedCards = new Vector(2);
        this.MaxSize = 2; // apenas 2 cartas
        this.timeToTurnDown = new Timer(TurnDownDelay, this);
        this.timeToTurnDown.setRepeats(false);
    }

    private int getSize() {
        return this.turnedCards.size();
    }

    public boolean TurnUp(Card C) {
        if (getSize() < MaxSize) return AddCards(C);
        return false;


    }

    private boolean AddCards(Card c) {
        this.turnedCards.add(c);
        // verify again

        if (getSize() == this.MaxSize) {
            Card OtherCardInPair = (Card) this.turnedCards.get(0);


            if (OtherCardInPair.getNum() == c.getNum()) {

                Player.AddPairCards(c,OtherCardInPair);
                System.out.println("Score: "+Player.getScore());


                this.turnedCards.clear();
                if(this.GetWinSinglePlayer()) System.out.println("Ganhou");



            }
        } else
            this.timeToTurnDown.start();
        return true;
    }

    public boolean GetWinSinglePlayer(){
        if(Player.win())
            return true;
        else
            return false;

        }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < getSize(); i++) {
            Card card = (Card) this.turnedCards.get(i);
            card.turnDown();
        }
        this.turnedCards.clear();
    }

}
