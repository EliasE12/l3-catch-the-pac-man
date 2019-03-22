package model;

import java.io.Serializable;

public class Score implements Serializable {

    private String playerName;
    private int scoreNumber;

    public Score(){}

    public Score(String playerName, int scoreNumber){
        this.playerName = playerName;
        this.scoreNumber = scoreNumber;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getScoreNumber() {
        return scoreNumber;
    }

    public void setScoreNumber(int scoreNumber) {
        this.scoreNumber = scoreNumber;
    }
}
