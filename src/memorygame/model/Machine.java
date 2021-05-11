package memorygame.model;

import java.util.Random;
import java.util.ArrayList;

public class Machine extends UserMachine {

    Random rand = new Random();
    ArrayList<CardSecond> OpenedCards = new ArrayList<>();
    ArrayList<CardSecond> AllCards = new ArrayList<>();

    public void add_new_card(CardSecond C) {
        if (verify_Deck(C) == true) {
            return;
        } else {
            OpenedCards.add(C);
        }
    }

    private boolean verify_Deck(CardSecond NewCard) {
        for (CardSecond i : OpenedCards) {
            if (NewCard == i) {
                return true;
            }
        }
        return false;
    }

    public CardSecond get_match(CardSecond NewCard) {
        for (CardSecond i : OpenedCards) {
            System.out.println(i.getNum());
            if (NewCard.getNum() == i.getNum()) {
                if (NewCard != i) {
                    System.out.println("Matching");
                    return i;
                }
            }

        }
        return null;
    }

    private int HMuch_cards() {
        return OpenedCards.size();
    }

    public void view_deck() {
        for (CardSecond i : OpenedCards) {
            System.out.println("Deck: " + i.getNum());
        }
    }

    public CardSecond get_random_card_from_deck() {

        int id = rand.nextInt(HMuch_cards());
        view_deck();
        for (CardSecond i : OpenedCards) {
            if (i.getNum() == id) {
                if (i.faceUp) {
                    return null;
                } else {
                    return i;
                }
            }
        }
        return null;
    }

    public void add_to_all_cards(CardSecond NewCard) {
        AllCards.add(NewCard);
    }

    public CardSecond Card_get() {
        int id = rand.nextInt(9);
        for (CardSecond i : AllCards) {
            if (i.getNum() == id) {
                if (i.faceUp) {
                    return null;
                } else {
                    return i;
                }
            }
        }
        return null;
    }

    public void increase_score() {
        int score = getScore();
        score += 1;
        this.setScore(score);
    }

    public boolean verify_win_machine() {
        return this.getScore() >= 6;
    }
}
