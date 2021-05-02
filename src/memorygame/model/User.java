package memorygame.model;
import java.util.ArrayList;

public class User {
    private int Score;
    ArrayList<Card> FindedCards = new ArrayList<Card>();
    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
    private void AddPairCars(Card C1, Card C2){
        FindedCards.add(C1);
        FindedCards.add(C2);
    }
    public boolean win(){
        if(FindedCards.size() == 8) return true;
        else return false;
    }
    private

}
