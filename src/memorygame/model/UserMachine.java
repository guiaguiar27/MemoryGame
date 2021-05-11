package memorygame.model;

import java.util.ArrayList;

public class UserMachine {

    private int Score;
    private String Name;
    private String Type; // machine ~ person
    ArrayList<CardSecond> FindedCards = new ArrayList<>();

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }

    private int GetSize() {
        return FindedCards.size();
    }

    public void AddPairCards(CardSecond C1, CardSecond C2) {
        int score;
        FindedCards.add(C1);
        FindedCards.add(C2);
        score = GetSize() / 2;
        setScore(score);

    }

    public int win() {

        if (FindedCards.size() >= 6) {
            return 1;
        } else if (FindedCards.size() == 4) {
            return 2;
        } else {
            return 3;
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
