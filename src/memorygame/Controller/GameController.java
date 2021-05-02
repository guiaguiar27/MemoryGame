package memorygame.Controller;
import memorygame.model.Card;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

public class GameController implements ActionListener {
    private ArrayList<Card> turnedCards = new ArrayList<Card>();
    private int MaxSize;
    private Timer timeToTurnDown;
    private final int TurnDownDelay = 2000;

    public GameController() {
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
            if (OtherCardInPair.getNum() == c.getNum())
                this.turnedCards.clear();
        } else
            this.timeToTurnDown.start();
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < this.MaxSize; i++) {
            Card card = (Card) this.turnedCards.get(i);
            card.turnDown();
        }
        this.turnedCards.clear();
    }

}
