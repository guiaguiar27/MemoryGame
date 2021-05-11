package memorygame.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.Timer;
import memorygame.model.CardSecond;
import memorygame.model.Machine;
import memorygame.model.UserMachine;

public class CardControllerMachine implements ActionListener {

    private Vector turnedCards;

    UserMachine Player;
    Machine Jarvis;
    GameControllerMachine g;
    private int MaxSize;
    private Timer timeToTurnDown;
    private final int TurnDownDelay = 2000;
    private int CardQuantity;

    public CardControllerMachine(GameControllerMachine GC, UserMachine Player) {
        this.Player = Player;
        this.Jarvis = new Machine();
        this.turnedCards = new Vector(2);
        this.MaxSize = 2; // apenas 2 cartas
        this.timeToTurnDown = new Timer(TurnDownDelay, this);
        this.timeToTurnDown.setRepeats(false);
        this.g = GC;
    }

    private int getSize() {
        return this.turnedCards.size();
    }

    public boolean TurnUp(CardSecond C) {
        if (getSize() < MaxSize) {
            return AddCards(C);
        }
        return false;

    }

    private boolean AddCards(CardSecond c) {
        this.turnedCards.add(c);
        // verify again

        if (getSize() == this.MaxSize) {
            CardSecond OtherCardInPair = (CardSecond) this.turnedCards.get(0);

            if (OtherCardInPair.getNum() == c.getNum()) {

                Player.AddPairCards(c, OtherCardInPair);
                System.out.println("Score: " + Player.getScore());

                this.turnedCards.clear();

            } else {
                g.pass_new_cards_to_machine(c, OtherCardInPair);
                this.timeToTurnDown.start();
            }

        }

        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < getSize(); i++) {
            CardSecond card = (CardSecond) this.turnedCards.get(i);
            card.turnDown();
        }
        this.turnedCards.clear();
        System.out.println("Limpa as cartas");
        g.machine_controll();
    }
}
