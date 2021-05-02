package memorygame.Controller;
import memorygame.model.Card;
import memorygame.model.User;

import java.awt.desktop.OpenFilesHandler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.Timer;

public class GameController implements ActionListener {
    private Vector turnedCards;
    User Player;


    private int MaxSize;
    private int CountToFinal;
    private Timer timeToTurnDown;
    private final int TurnDownDelay = 2000;


    public GameController() {
        this.Player = new User();
        this.turnedCards = new Vector(2);
        this.MaxSize = 2; // apenas 2 cartas
        this.CountToFinal = 1;
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
// implementado para um jogar

    private boolean AddCards(Card c) {
        this.turnedCards.add(c);
        // verify again
        if (getSize() == this.MaxSize) {
            Card OtherCardInPair = (Card) this.turnedCards.get(0);
            if (OtherCardInPair.getNum() == c.getNum()) {
                this.CountToFinal++;

                Player.AddPairCards(c,OtherCardInPair);
                System.out.println("Score: "+Player.getScore());

                if(Player.win()) {
                    System.out.println("Ganhou");
                }

                this.turnedCards.clear();

            }
        } else
            this.timeToTurnDown.start();
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < getSize(); i++) {
            Card card = (Card) this.turnedCards.get(i);
            card.turnDown();
        }
        this.turnedCards.clear();
    }

    public int getCountToFinal() {
        return CountToFinal;
    }

    public void setCountToFinal(int countToFinal) {
        CountToFinal = countToFinal;
    }
}
