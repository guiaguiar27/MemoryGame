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

}
