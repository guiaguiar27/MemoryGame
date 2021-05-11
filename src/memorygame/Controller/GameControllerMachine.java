package memorygame.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import memorygame.model.CardSecond;
import memorygame.model.Machine;

public class GameControllerMachine implements ActionListener {

    Machine Jarvis = new Machine();
    private final int TurnDownDelay = 2000;
    private javax.swing.Timer timeToTurnDown;
    private CardSecond C1, C2;

    public GameControllerMachine(Machine M) {
        this.Jarvis = M;
        this.timeToTurnDown = new javax.swing.Timer(TurnDownDelay, this);
        this.timeToTurnDown.setRepeats(false);

    }

    public void machine_controll() {
        CardSecond C;
        System.out.println("Jarvis");
        do {
            C = Jarvis.Card_get();
        } while (C == null);
        C.turnUp(true);
        System.out.println("printa: " + C.getNum());
        CardSecond C2 = Jarvis.get_match(C);
        if (C2 != null) {
            System.out.println("Entoru nao nulo no match");
            C2.turnUp(true);
        } else {
            System.out.println("Entoru  nulo no match");
            do {
                C2 = Jarvis.Card_get();
                System.out.println("try");
            } while (C2 == null);
            System.out.println("printa: " + C2.getNum());
            C2.turnUp(true);

        }
        if (C.getNum() == C2.getNum()) {
            Jarvis.increase_score();
        } else {
            this.timeToTurnDown.start();
            this.C1 = C;
            this.C2 = C2;
        }
        System.out.println("Score Jarvis: " + Jarvis.getScore());

    }

    public void pass_to_machine(CardSecond C) {
        Jarvis.add_to_all_cards(C);
    }

    public void pass_new_cards_to_machine(CardSecond C1, CardSecond C2) {
        Jarvis.add_new_card(C1);
        Jarvis.add_new_card(C2);
        Jarvis.view_deck();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Abaixa as cartas");
        C1.turnDown();
        C2.turnDown();
    }
}
