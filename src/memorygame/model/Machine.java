package memorygame.model;

import java.util.Random;
import java.util.ArrayList;

public class Machine extends  User{
    Random rand = new Random();
    ArrayList<Card> OpenedCards = new ArrayList<Card>();

    public void add_new_card(Card C) {
        if(verify_Deck(C) == true) return;
        else
            OpenedCards.add(C);
    }
    private boolean verify_Deck(Card NewCard){
        for(Card i: OpenedCards) {
            if(NewCard == i) return true;
        }
        return false;
    }

    public Card verify_card_opened(Card NewCard) {
        if(verify_Deck(NewCard) == true) {
            for (Card i : OpenedCards) {
                System.out.println(i.getNum());
                if (NewCard == i) {
                    System.out.println("Matching");
                    return i;
                }
            }
        }
        return null;
    }
    private int HMuch_cards(){
        return OpenedCards.size();
    }

    private void view_deck(){
        for(Card i: OpenedCards) {
            System.out.print(","+i.getNum());
        }
    }
    public Card get_random_card() {

        int id = rand.nextInt(HMuch_cards());
        view_deck();
        for (Card i : OpenedCards) {
            if(i.getNum() == id) return i;
        }
        return null;
    }
}
