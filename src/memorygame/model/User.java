package memorygame.model;
import java.util.ArrayList;

public class User {
    private int Score;
    private String Name;
    private String Type; // machine ~ person
    ArrayList<Card> FindedCards = new ArrayList<Card>();
    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        this.Score = score;
    }
    private int GetSize(){
        return FindedCards.size();
    }
    public void AddPairCards(Card C1, Card C2){
        int score;
        FindedCards.add(C1);
        FindedCards.add(C2);
        score = GetSize()/2;
        setScore(score);


    }
    public boolean win(){
        if(FindedCards.size() == 8) return true;
        else return false;
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
